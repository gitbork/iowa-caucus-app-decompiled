package org.reactnative.camera;

import android.annotation.SuppressLint;
import android.media.CamcorderProfile;
import android.media.MediaActionSound;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.view.View;
import androidx.core.content.ContextCompat;
import androidx.core.view.ViewCompat;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.LifecycleEventListener;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.ThemedReactContext;
import com.google.android.cameraview.CameraView;
import com.google.android.cameraview.CameraView.Callback;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.DecodeHintType;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.Result;
import java.io.File;
import java.io.IOException;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import org.reactnative.barcodedetector.RNBarcodeDetector;
import org.reactnative.camera.tasks.BarCodeScannerAsyncTask;
import org.reactnative.camera.tasks.BarCodeScannerAsyncTaskDelegate;
import org.reactnative.camera.tasks.BarcodeDetectorAsyncTask;
import org.reactnative.camera.tasks.BarcodeDetectorAsyncTaskDelegate;
import org.reactnative.camera.tasks.FaceDetectorAsyncTask;
import org.reactnative.camera.tasks.FaceDetectorAsyncTaskDelegate;
import org.reactnative.camera.tasks.PictureSavedDelegate;
import org.reactnative.camera.tasks.ResolveTakenPictureAsyncTask;
import org.reactnative.camera.tasks.TextRecognizerAsyncTask;
import org.reactnative.camera.tasks.TextRecognizerAsyncTaskDelegate;
import org.reactnative.camera.utils.RNFileUtils;
import org.reactnative.facedetector.RNFaceDetector;

public class RNCameraView extends CameraView implements LifecycleEventListener, BarCodeScannerAsyncTaskDelegate, FaceDetectorAsyncTaskDelegate, BarcodeDetectorAsyncTaskDelegate, TextRecognizerAsyncTaskDelegate, PictureSavedDelegate {
    public volatile boolean barCodeScannerTaskLock;
    public volatile boolean faceDetectorTaskLock;
    public volatile boolean googleBarcodeDetectorTaskLock;
    /* access modifiers changed from: private */
    public boolean invertImageData;
    private List<String> mBarCodeTypes = null;
    private int mFaceDetectionClassifications;
    private int mFaceDetectionLandmarks;
    /* access modifiers changed from: private */
    public RNFaceDetector mFaceDetector;
    private int mFaceDetectorMode;
    /* access modifiers changed from: private */
    public RNBarcodeDetector mGoogleBarcodeDetector;
    /* access modifiers changed from: private */
    public int mGoogleVisionBarCodeMode;
    private int mGoogleVisionBarCodeType;
    /* access modifiers changed from: private */
    public boolean mIsNew;
    /* access modifiers changed from: private */
    public boolean mIsPaused;
    /* access modifiers changed from: private */
    public Boolean mIsRecording;
    /* access modifiers changed from: private */
    public Boolean mIsRecordingInterrupted;
    /* access modifiers changed from: private */
    public MultiFormatReader mMultiFormatReader;
    /* access modifiers changed from: private */
    public int mPaddingX;
    /* access modifiers changed from: private */
    public int mPaddingY;
    /* access modifiers changed from: private */
    public Map<Promise, File> mPictureTakenDirectories = new ConcurrentHashMap();
    /* access modifiers changed from: private */
    public Map<Promise, ReadableMap> mPictureTakenOptions = new ConcurrentHashMap();
    /* access modifiers changed from: private */
    public Queue<Promise> mPictureTakenPromises = new ConcurrentLinkedQueue();
    /* access modifiers changed from: private */
    public Boolean mPlaySoundOnCapture;
    /* access modifiers changed from: private */
    public boolean mShouldDetectFaces;
    /* access modifiers changed from: private */
    public boolean mShouldGoogleDetectBarcodes;
    /* access modifiers changed from: private */
    public boolean mShouldRecognizeText;
    /* access modifiers changed from: private */
    public boolean mShouldScanBarCodes;
    /* access modifiers changed from: private */
    public ThemedReactContext mThemedReactContext;
    private boolean mTrackingEnabled;
    /* access modifiers changed from: private */
    public Promise mVideoRecordedPromise;
    public volatile boolean textRecognizerTaskLock;

    @SuppressLint({"all"})
    public void requestLayout() {
    }

    public RNCameraView(ThemedReactContext themedReactContext) {
        super(themedReactContext, true);
        Boolean valueOf = Boolean.valueOf(false);
        this.mPlaySoundOnCapture = valueOf;
        this.mIsPaused = false;
        this.mIsNew = true;
        this.invertImageData = false;
        this.mIsRecording = valueOf;
        this.mIsRecordingInterrupted = valueOf;
        this.barCodeScannerTaskLock = false;
        this.faceDetectorTaskLock = false;
        this.googleBarcodeDetectorTaskLock = false;
        this.textRecognizerTaskLock = false;
        this.mShouldDetectFaces = false;
        this.mShouldGoogleDetectBarcodes = false;
        this.mShouldScanBarCodes = false;
        this.mShouldRecognizeText = false;
        this.mFaceDetectorMode = RNFaceDetector.FAST_MODE;
        this.mFaceDetectionLandmarks = RNFaceDetector.NO_LANDMARKS;
        this.mFaceDetectionClassifications = RNFaceDetector.NO_CLASSIFICATIONS;
        this.mGoogleVisionBarCodeType = RNBarcodeDetector.ALL_FORMATS;
        this.mGoogleVisionBarCodeMode = RNBarcodeDetector.NORMAL_MODE;
        this.mTrackingEnabled = true;
        this.mThemedReactContext = themedReactContext;
        themedReactContext.addLifecycleEventListener(this);
        addCallback(new Callback() {
            public void onCameraOpened(CameraView cameraView) {
                RNCameraViewHelper.emitCameraReadyEvent(cameraView);
            }

            public void onMountError(CameraView cameraView) {
                RNCameraViewHelper.emitMountErrorEvent(cameraView, "Camera view threw an error - component could not be rendered.");
            }

            public void onPictureTaken(CameraView cameraView, byte[] bArr, int i) {
                Promise promise = (Promise) RNCameraView.this.mPictureTakenPromises.poll();
                ReadableMap readableMap = (ReadableMap) RNCameraView.this.mPictureTakenOptions.remove(promise);
                String str = "fastMode";
                if (readableMap.hasKey(str) && readableMap.getBoolean(str)) {
                    promise.resolve(null);
                }
                File file = (File) RNCameraView.this.mPictureTakenDirectories.remove(promise);
                if (VERSION.SDK_INT >= 11) {
                    ResolveTakenPictureAsyncTask resolveTakenPictureAsyncTask = new ResolveTakenPictureAsyncTask(bArr, promise, readableMap, file, i, RNCameraView.this);
                    resolveTakenPictureAsyncTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
                } else {
                    ResolveTakenPictureAsyncTask resolveTakenPictureAsyncTask2 = new ResolveTakenPictureAsyncTask(bArr, promise, readableMap, file, i, RNCameraView.this);
                    resolveTakenPictureAsyncTask2.execute(new Void[0]);
                }
                RNCameraViewHelper.emitPictureTakenEvent(cameraView);
            }

            public void onVideoRecorded(CameraView cameraView, String str, int i, int i2) {
                if (RNCameraView.this.mVideoRecordedPromise != null) {
                    if (str != null) {
                        WritableMap createMap = Arguments.createMap();
                        createMap.putBoolean("isRecordingInterrupted", RNCameraView.this.mIsRecordingInterrupted.booleanValue());
                        createMap.putInt("videoOrientation", i);
                        createMap.putInt("deviceOrientation", i2);
                        createMap.putString("uri", RNFileUtils.uriFromFile(new File(str)).toString());
                        RNCameraView.this.mVideoRecordedPromise.resolve(createMap);
                    } else {
                        RNCameraView.this.mVideoRecordedPromise.reject("E_RECORDING", "Couldn't stop recording - there is none in progress");
                    }
                    RNCameraView.this.mIsRecording = Boolean.valueOf(false);
                    RNCameraView.this.mIsRecordingInterrupted = Boolean.valueOf(false);
                    RNCameraView.this.mVideoRecordedPromise = null;
                }
            }

            public void onFramePreview(CameraView cameraView, byte[] bArr, int i, int i2, int i3) {
                boolean z;
                CameraView cameraView2 = cameraView;
                byte[] bArr2 = bArr;
                int correctCameraRotation = RNCameraViewHelper.getCorrectCameraRotation(i3, RNCameraView.this.getFacing(), RNCameraView.this.getCameraOrientation());
                boolean z2 = RNCameraView.this.mShouldScanBarCodes && !RNCameraView.this.barCodeScannerTaskLock && (cameraView2 instanceof BarCodeScannerAsyncTaskDelegate);
                boolean z3 = RNCameraView.this.mShouldDetectFaces && !RNCameraView.this.faceDetectorTaskLock && (cameraView2 instanceof FaceDetectorAsyncTaskDelegate);
                boolean z4 = RNCameraView.this.mShouldGoogleDetectBarcodes && !RNCameraView.this.googleBarcodeDetectorTaskLock && (cameraView2 instanceof BarcodeDetectorAsyncTaskDelegate);
                boolean z5 = RNCameraView.this.mShouldRecognizeText && !RNCameraView.this.textRecognizerTaskLock && (cameraView2 instanceof TextRecognizerAsyncTaskDelegate);
                if (z2 || z3 || z4 || z5) {
                    double length = (double) bArr2.length;
                    double d = (double) i;
                    Double.isNaN(d);
                    double d2 = d * 1.5d;
                    double d3 = (double) i2;
                    Double.isNaN(d3);
                    if (length >= d2 * d3) {
                        if (z2) {
                            RNCameraView rNCameraView = RNCameraView.this;
                            rNCameraView.barCodeScannerTaskLock = true;
                            BarCodeScannerAsyncTask barCodeScannerAsyncTask = new BarCodeScannerAsyncTask((BarCodeScannerAsyncTaskDelegate) cameraView2, rNCameraView.mMultiFormatReader, bArr, i, i2);
                            barCodeScannerAsyncTask.execute(new Void[0]);
                        }
                        if (z3) {
                            RNCameraView rNCameraView2 = RNCameraView.this;
                            rNCameraView2.faceDetectorTaskLock = true;
                            FaceDetectorAsyncTask faceDetectorAsyncTask = r2;
                            z = false;
                            FaceDetectorAsyncTask faceDetectorAsyncTask2 = new FaceDetectorAsyncTask((FaceDetectorAsyncTaskDelegate) cameraView2, rNCameraView2.mFaceDetector, bArr, i, i2, correctCameraRotation, RNCameraView.this.getResources().getDisplayMetrics().density, RNCameraView.this.getFacing(), RNCameraView.this.getWidth(), RNCameraView.this.getHeight(), RNCameraView.this.mPaddingX, RNCameraView.this.mPaddingY);
                            faceDetectorAsyncTask.execute(new Void[0]);
                        } else {
                            z = false;
                        }
                        if (z4) {
                            RNCameraView rNCameraView3 = RNCameraView.this;
                            rNCameraView3.googleBarcodeDetectorTaskLock = true;
                            if (rNCameraView3.mGoogleVisionBarCodeMode == RNBarcodeDetector.NORMAL_MODE) {
                                RNCameraView.this.invertImageData = z;
                            } else if (RNCameraView.this.mGoogleVisionBarCodeMode == RNBarcodeDetector.ALTERNATE_MODE) {
                                RNCameraView rNCameraView4 = RNCameraView.this;
                                rNCameraView4.invertImageData = !rNCameraView4.invertImageData;
                            } else if (RNCameraView.this.mGoogleVisionBarCodeMode == RNBarcodeDetector.INVERTED_MODE) {
                                RNCameraView.this.invertImageData = true;
                            }
                            if (RNCameraView.this.invertImageData) {
                                byte[] bArr3 = bArr;
                                for (int i4 = 0; i4 < bArr3.length; i4++) {
                                    bArr3[i4] = (byte) (bArr3[i4] ^ -1);
                                }
                            } else {
                                byte[] bArr4 = bArr;
                            }
                            BarcodeDetectorAsyncTask barcodeDetectorAsyncTask = r2;
                            BarcodeDetectorAsyncTask barcodeDetectorAsyncTask2 = new BarcodeDetectorAsyncTask((BarcodeDetectorAsyncTaskDelegate) cameraView, RNCameraView.this.mGoogleBarcodeDetector, bArr, i, i2, correctCameraRotation, RNCameraView.this.getResources().getDisplayMetrics().density, RNCameraView.this.getFacing(), RNCameraView.this.getWidth(), RNCameraView.this.getHeight(), RNCameraView.this.mPaddingX, RNCameraView.this.mPaddingY);
                            barcodeDetectorAsyncTask.execute(new Void[z]);
                        } else {
                            byte[] bArr5 = bArr;
                        }
                        if (z5) {
                            RNCameraView rNCameraView5 = RNCameraView.this;
                            rNCameraView5.textRecognizerTaskLock = true;
                            TextRecognizerAsyncTask textRecognizerAsyncTask = new TextRecognizerAsyncTask((TextRecognizerAsyncTaskDelegate) cameraView, rNCameraView5.mThemedReactContext, bArr, i, i2, correctCameraRotation, RNCameraView.this.getResources().getDisplayMetrics().density, RNCameraView.this.getFacing(), RNCameraView.this.getWidth(), RNCameraView.this.getHeight(), RNCameraView.this.mPaddingX, RNCameraView.this.mPaddingY);
                            textRecognizerAsyncTask.execute(new Void[(z ? 1 : 0)]);
                        }
                    }
                }
            }
        });
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int i5;
        int i6;
        View view = getView();
        if (view != null) {
            float f = (float) (i3 - i);
            float f2 = (float) (i4 - i2);
            float f3 = getAspectRatio().toFloat();
            int i7 = getResources().getConfiguration().orientation;
            setBackgroundColor(ViewCompat.MEASURED_STATE_MASK);
            if (i7 == 2) {
                float f4 = f3 * f2;
                if (f4 < f) {
                    i6 = (int) (f / f3);
                } else {
                    i5 = (int) f4;
                    i6 = (int) f2;
                    int i8 = (int) ((f - ((float) i5)) / 2.0f);
                    int i9 = (int) ((f2 - ((float) i6)) / 2.0f);
                    this.mPaddingX = i8;
                    this.mPaddingY = i9;
                    view.layout(i8, i9, i5 + i8, i6 + i9);
                }
            } else {
                float f5 = f3 * f;
                if (f5 > f2) {
                    i6 = (int) f5;
                } else {
                    i5 = (int) (f2 / f3);
                    i6 = (int) f2;
                    int i82 = (int) ((f - ((float) i5)) / 2.0f);
                    int i92 = (int) ((f2 - ((float) i6)) / 2.0f);
                    this.mPaddingX = i82;
                    this.mPaddingY = i92;
                    view.layout(i82, i92, i5 + i82, i6 + i92);
                }
            }
            i5 = (int) f;
            int i822 = (int) ((f - ((float) i5)) / 2.0f);
            int i922 = (int) ((f2 - ((float) i6)) / 2.0f);
            this.mPaddingX = i822;
            this.mPaddingY = i922;
            view.layout(i822, i922, i5 + i822, i6 + i922);
        }
    }

    public void setBarCodeTypes(List<String> list) {
        this.mBarCodeTypes = list;
        initBarcodeReader();
    }

    public void setPlaySoundOnCapture(Boolean bool) {
        this.mPlaySoundOnCapture = bool;
    }

    public void takePicture(final ReadableMap readableMap, final Promise promise, final File file) {
        this.mBgHandler.post(new Runnable() {
            public void run() {
                RNCameraView.this.mPictureTakenPromises.add(promise);
                RNCameraView.this.mPictureTakenOptions.put(promise, readableMap);
                RNCameraView.this.mPictureTakenDirectories.put(promise, file);
                if (RNCameraView.this.mPlaySoundOnCapture.booleanValue()) {
                    new MediaActionSound().play(0);
                }
                try {
                    RNCameraView.super.takePicture(readableMap);
                } catch (Exception e) {
                    RNCameraView.this.mPictureTakenPromises.remove(promise);
                    RNCameraView.this.mPictureTakenOptions.remove(promise);
                    RNCameraView.this.mPictureTakenDirectories.remove(promise);
                    promise.reject("E_TAKE_PICTURE_FAILED", e.getMessage());
                }
            }
        });
    }

    public void onPictureSaved(WritableMap writableMap) {
        RNCameraViewHelper.emitPictureSavedEvent(this, writableMap);
    }

    public void record(final ReadableMap readableMap, final Promise promise, final File file) {
        this.mBgHandler.post(new Runnable() {
            public void run() {
                String str = "orientation";
                String str2 = "mute";
                String str3 = "videoBitrate";
                String str4 = "quality";
                String str5 = "maxFileSize";
                String str6 = "maxDuration";
                String str7 = "E_RECORDING_FAILED";
                String str8 = "path";
                try {
                    String string = readableMap.hasKey(str8) ? readableMap.getString(str8) : RNFileUtils.getOutputFilePath(file, ".mp4");
                    int i = readableMap.hasKey(str6) ? readableMap.getInt(str6) : -1;
                    int i2 = readableMap.hasKey(str5) ? readableMap.getInt(str5) : -1;
                    CamcorderProfile camcorderProfile = readableMap.hasKey(str4) ? RNCameraViewHelper.getCamcorderProfile(readableMap.getInt(str4)) : CamcorderProfile.get(1);
                    if (readableMap.hasKey(str3)) {
                        camcorderProfile.videoBitRate = readableMap.getInt(str3);
                    }
                    if (RNCameraView.super.record(string, i * 1000, i2, readableMap.hasKey(str2) ? !readableMap.getBoolean(str2) : true, camcorderProfile, readableMap.hasKey(str) ? readableMap.getInt(str) : 0)) {
                        RNCameraView.this.mIsRecording = Boolean.valueOf(true);
                        RNCameraView.this.mVideoRecordedPromise = promise;
                        return;
                    }
                    promise.reject(str7, "Starting video recording failed. Another recording might be in progress.");
                } catch (IOException unused) {
                    promise.reject(str7, "Starting video recording failed - could not create video file.");
                }
            }
        });
    }

    private void initBarcodeReader() {
        this.mMultiFormatReader = new MultiFormatReader();
        EnumMap enumMap = new EnumMap(DecodeHintType.class);
        EnumSet noneOf = EnumSet.noneOf(BarcodeFormat.class);
        List<String> list = this.mBarCodeTypes;
        if (list != null) {
            for (String str : list) {
                String str2 = (String) CameraModule.VALID_BARCODE_TYPES.get(str);
                if (str2 != null) {
                    noneOf.add(BarcodeFormat.valueOf(str2));
                }
            }
        }
        enumMap.put(DecodeHintType.POSSIBLE_FORMATS, noneOf);
        this.mMultiFormatReader.setHints(enumMap);
    }

    public void setShouldScanBarCodes(boolean z) {
        if (z && this.mMultiFormatReader == null) {
            initBarcodeReader();
        }
        this.mShouldScanBarCodes = z;
        setScanning(this.mShouldDetectFaces || this.mShouldGoogleDetectBarcodes || this.mShouldScanBarCodes || this.mShouldRecognizeText);
    }

    public void onBarCodeRead(Result result, int i, int i2) {
        String barcodeFormat = result.getBarcodeFormat().toString();
        if (this.mShouldScanBarCodes && this.mBarCodeTypes.contains(barcodeFormat)) {
            RNCameraViewHelper.emitBarCodeReadEvent(this, result, i, i2);
        }
    }

    public void onBarCodeScanningTaskCompleted() {
        this.barCodeScannerTaskLock = false;
        MultiFormatReader multiFormatReader = this.mMultiFormatReader;
        if (multiFormatReader != null) {
            multiFormatReader.reset();
        }
    }

    private void setupFaceDetector() {
        this.mFaceDetector = new RNFaceDetector(this.mThemedReactContext);
        this.mFaceDetector.setMode(this.mFaceDetectorMode);
        this.mFaceDetector.setLandmarkType(this.mFaceDetectionLandmarks);
        this.mFaceDetector.setClassificationType(this.mFaceDetectionClassifications);
        this.mFaceDetector.setTracking(this.mTrackingEnabled);
    }

    public void setFaceDetectionLandmarks(int i) {
        this.mFaceDetectionLandmarks = i;
        RNFaceDetector rNFaceDetector = this.mFaceDetector;
        if (rNFaceDetector != null) {
            rNFaceDetector.setLandmarkType(i);
        }
    }

    public void setFaceDetectionClassifications(int i) {
        this.mFaceDetectionClassifications = i;
        RNFaceDetector rNFaceDetector = this.mFaceDetector;
        if (rNFaceDetector != null) {
            rNFaceDetector.setClassificationType(i);
        }
    }

    public void setFaceDetectionMode(int i) {
        this.mFaceDetectorMode = i;
        RNFaceDetector rNFaceDetector = this.mFaceDetector;
        if (rNFaceDetector != null) {
            rNFaceDetector.setMode(i);
        }
    }

    public void setTracking(boolean z) {
        this.mTrackingEnabled = z;
        RNFaceDetector rNFaceDetector = this.mFaceDetector;
        if (rNFaceDetector != null) {
            rNFaceDetector.setTracking(z);
        }
    }

    public void setShouldDetectFaces(boolean z) {
        if (z && this.mFaceDetector == null) {
            setupFaceDetector();
        }
        this.mShouldDetectFaces = z;
        setScanning(this.mShouldDetectFaces || this.mShouldGoogleDetectBarcodes || this.mShouldScanBarCodes || this.mShouldRecognizeText);
    }

    public void onFacesDetected(WritableArray writableArray) {
        if (this.mShouldDetectFaces) {
            RNCameraViewHelper.emitFacesDetectedEvent(this, writableArray);
        }
    }

    public void onFaceDetectionError(RNFaceDetector rNFaceDetector) {
        if (this.mShouldDetectFaces) {
            RNCameraViewHelper.emitFaceDetectionErrorEvent(this, rNFaceDetector);
        }
    }

    public void onFaceDetectingTaskCompleted() {
        this.faceDetectorTaskLock = false;
    }

    private void setupBarcodeDetector() {
        this.mGoogleBarcodeDetector = new RNBarcodeDetector(this.mThemedReactContext);
        this.mGoogleBarcodeDetector.setBarcodeType(this.mGoogleVisionBarCodeType);
    }

    public void setShouldGoogleDetectBarcodes(boolean z) {
        if (z && this.mGoogleBarcodeDetector == null) {
            setupBarcodeDetector();
        }
        this.mShouldGoogleDetectBarcodes = z;
        setScanning(this.mShouldDetectFaces || this.mShouldGoogleDetectBarcodes || this.mShouldScanBarCodes || this.mShouldRecognizeText);
    }

    public void setGoogleVisionBarcodeType(int i) {
        this.mGoogleVisionBarCodeType = i;
        RNBarcodeDetector rNBarcodeDetector = this.mGoogleBarcodeDetector;
        if (rNBarcodeDetector != null) {
            rNBarcodeDetector.setBarcodeType(i);
        }
    }

    public void setGoogleVisionBarcodeMode(int i) {
        this.mGoogleVisionBarCodeMode = i;
    }

    public void onBarcodesDetected(WritableArray writableArray) {
        if (this.mShouldGoogleDetectBarcodes) {
            RNCameraViewHelper.emitBarcodesDetectedEvent(this, writableArray);
        }
    }

    public void onBarcodeDetectionError(RNBarcodeDetector rNBarcodeDetector) {
        if (this.mShouldGoogleDetectBarcodes) {
            RNCameraViewHelper.emitBarcodeDetectionErrorEvent(this, rNBarcodeDetector);
        }
    }

    public void onBarcodeDetectingTaskCompleted() {
        this.googleBarcodeDetectorTaskLock = false;
    }

    public void setShouldRecognizeText(boolean z) {
        this.mShouldRecognizeText = z;
        setScanning(this.mShouldDetectFaces || this.mShouldGoogleDetectBarcodes || this.mShouldScanBarCodes || this.mShouldRecognizeText);
    }

    public void onTextRecognized(WritableArray writableArray) {
        if (this.mShouldRecognizeText) {
            RNCameraViewHelper.emitTextRecognizedEvent(this, writableArray);
        }
    }

    public void onTextRecognizerTaskCompleted() {
        this.textRecognizerTaskLock = false;
    }

    public void onHostResume() {
        if (hasCameraPermissions()) {
            this.mBgHandler.post(new Runnable() {
                public void run() {
                    if ((RNCameraView.this.mIsPaused && !RNCameraView.this.isCameraOpened()) || RNCameraView.this.mIsNew) {
                        RNCameraView.this.mIsPaused = false;
                        RNCameraView.this.mIsNew = false;
                        RNCameraView.this.start();
                    }
                }
            });
        } else {
            RNCameraViewHelper.emitMountErrorEvent(this, "Camera permissions not granted - component could not be rendered.");
        }
    }

    public void onHostPause() {
        if (this.mIsRecording.booleanValue()) {
            this.mIsRecordingInterrupted = Boolean.valueOf(true);
        }
        if (!this.mIsPaused && isCameraOpened()) {
            this.mIsPaused = true;
            stop();
        }
    }

    public void onHostDestroy() {
        RNFaceDetector rNFaceDetector = this.mFaceDetector;
        if (rNFaceDetector != null) {
            rNFaceDetector.release();
        }
        RNBarcodeDetector rNBarcodeDetector = this.mGoogleBarcodeDetector;
        if (rNBarcodeDetector != null) {
            rNBarcodeDetector.release();
        }
        this.mMultiFormatReader = null;
        stop();
        this.mThemedReactContext.removeLifecycleEventListener(this);
        cleanup();
    }

    private boolean hasCameraPermissions() {
        if (VERSION.SDK_INT < 23 || ContextCompat.checkSelfPermission(getContext(), "android.permission.CAMERA") == 0) {
            return true;
        }
        return false;
    }
}
