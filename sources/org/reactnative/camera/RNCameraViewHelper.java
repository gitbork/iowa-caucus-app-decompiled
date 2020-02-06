package org.reactnative.camera;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.media.CamcorderProfile;
import android.os.Build.VERSION;
import android.view.ViewGroup;
import androidx.core.view.InputDeviceCompat;
import androidx.core.view.ViewCompat;
import androidx.exifinterface.media.ExifInterface;
import com.drew.metadata.exif.makernotes.OlympusMakernoteDirectory;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.UIManagerModule;
import com.google.zxing.Result;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import org.reactnative.barcodedetector.RNBarcodeDetector;
import org.reactnative.camera.events.BarCodeReadEvent;
import org.reactnative.camera.events.BarcodeDetectionErrorEvent;
import org.reactnative.camera.events.BarcodesDetectedEvent;
import org.reactnative.camera.events.CameraMountErrorEvent;
import org.reactnative.camera.events.CameraReadyEvent;
import org.reactnative.camera.events.FaceDetectionErrorEvent;
import org.reactnative.camera.events.FacesDetectedEvent;
import org.reactnative.camera.events.PictureSavedEvent;
import org.reactnative.camera.events.PictureTakenEvent;
import org.reactnative.camera.events.TextRecognizedEvent;
import org.reactnative.facedetector.RNFaceDetector;

public class RNCameraViewHelper {
    public static final String[][] exifTags;

    private static boolean rotationIsLandscape(int i) {
        return i == 90 || i == 270;
    }

    static {
        String str = "string";
        String str2 = "int";
        String str3 = "double";
        exifTags = new String[][]{new String[]{str, ExifInterface.TAG_ARTIST}, new String[]{str2, ExifInterface.TAG_BITS_PER_SAMPLE}, new String[]{str2, ExifInterface.TAG_COMPRESSION}, new String[]{str, ExifInterface.TAG_COPYRIGHT}, new String[]{str, ExifInterface.TAG_DATETIME}, new String[]{str, ExifInterface.TAG_IMAGE_DESCRIPTION}, new String[]{str2, ExifInterface.TAG_IMAGE_LENGTH}, new String[]{str2, ExifInterface.TAG_IMAGE_WIDTH}, new String[]{str2, ExifInterface.TAG_JPEG_INTERCHANGE_FORMAT}, new String[]{str2, ExifInterface.TAG_JPEG_INTERCHANGE_FORMAT_LENGTH}, new String[]{str, ExifInterface.TAG_MAKE}, new String[]{str, ExifInterface.TAG_MODEL}, new String[]{str2, ExifInterface.TAG_ORIENTATION}, new String[]{str2, ExifInterface.TAG_PHOTOMETRIC_INTERPRETATION}, new String[]{str2, ExifInterface.TAG_PLANAR_CONFIGURATION}, new String[]{str3, ExifInterface.TAG_PRIMARY_CHROMATICITIES}, new String[]{str3, ExifInterface.TAG_REFERENCE_BLACK_WHITE}, new String[]{str2, ExifInterface.TAG_RESOLUTION_UNIT}, new String[]{str2, ExifInterface.TAG_ROWS_PER_STRIP}, new String[]{str2, ExifInterface.TAG_SAMPLES_PER_PIXEL}, new String[]{str, ExifInterface.TAG_SOFTWARE}, new String[]{str2, ExifInterface.TAG_STRIP_BYTE_COUNTS}, new String[]{str2, ExifInterface.TAG_STRIP_OFFSETS}, new String[]{str2, ExifInterface.TAG_TRANSFER_FUNCTION}, new String[]{str3, ExifInterface.TAG_WHITE_POINT}, new String[]{str3, ExifInterface.TAG_X_RESOLUTION}, new String[]{str3, ExifInterface.TAG_Y_CB_CR_COEFFICIENTS}, new String[]{str2, ExifInterface.TAG_Y_CB_CR_POSITIONING}, new String[]{str2, ExifInterface.TAG_Y_CB_CR_SUB_SAMPLING}, new String[]{str3, ExifInterface.TAG_Y_RESOLUTION}, new String[]{str3, ExifInterface.TAG_APERTURE_VALUE}, new String[]{str3, ExifInterface.TAG_BRIGHTNESS_VALUE}, new String[]{str, ExifInterface.TAG_CFA_PATTERN}, new String[]{str2, ExifInterface.TAG_COLOR_SPACE}, new String[]{str, ExifInterface.TAG_COMPONENTS_CONFIGURATION}, new String[]{str3, ExifInterface.TAG_COMPRESSED_BITS_PER_PIXEL}, new String[]{str2, ExifInterface.TAG_CONTRAST}, new String[]{str2, ExifInterface.TAG_CUSTOM_RENDERED}, new String[]{str, ExifInterface.TAG_DATETIME_DIGITIZED}, new String[]{str, ExifInterface.TAG_DATETIME_ORIGINAL}, new String[]{str, ExifInterface.TAG_DEVICE_SETTING_DESCRIPTION}, new String[]{str3, ExifInterface.TAG_DIGITAL_ZOOM_RATIO}, new String[]{str, ExifInterface.TAG_EXIF_VERSION}, new String[]{str3, ExifInterface.TAG_EXPOSURE_BIAS_VALUE}, new String[]{str3, ExifInterface.TAG_EXPOSURE_INDEX}, new String[]{str2, ExifInterface.TAG_EXPOSURE_MODE}, new String[]{str2, ExifInterface.TAG_EXPOSURE_PROGRAM}, new String[]{str3, ExifInterface.TAG_EXPOSURE_TIME}, new String[]{str3, ExifInterface.TAG_F_NUMBER}, new String[]{str, ExifInterface.TAG_FILE_SOURCE}, new String[]{str2, ExifInterface.TAG_FLASH}, new String[]{str3, ExifInterface.TAG_FLASH_ENERGY}, new String[]{str, ExifInterface.TAG_FLASHPIX_VERSION}, new String[]{str3, ExifInterface.TAG_FOCAL_LENGTH}, new String[]{str2, ExifInterface.TAG_FOCAL_LENGTH_IN_35MM_FILM}, new String[]{str2, ExifInterface.TAG_FOCAL_PLANE_RESOLUTION_UNIT}, new String[]{str3, ExifInterface.TAG_FOCAL_PLANE_X_RESOLUTION}, new String[]{str3, ExifInterface.TAG_FOCAL_PLANE_Y_RESOLUTION}, new String[]{str2, ExifInterface.TAG_GAIN_CONTROL}, new String[]{str2, ExifInterface.TAG_ISO_SPEED_RATINGS}, new String[]{str, ExifInterface.TAG_IMAGE_UNIQUE_ID}, new String[]{str2, ExifInterface.TAG_LIGHT_SOURCE}, new String[]{str, ExifInterface.TAG_MAKER_NOTE}, new String[]{str3, ExifInterface.TAG_MAX_APERTURE_VALUE}, new String[]{str2, ExifInterface.TAG_METERING_MODE}, new String[]{str2, ExifInterface.TAG_NEW_SUBFILE_TYPE}, new String[]{str, ExifInterface.TAG_OECF}, new String[]{str2, ExifInterface.TAG_PIXEL_X_DIMENSION}, new String[]{str2, ExifInterface.TAG_PIXEL_Y_DIMENSION}, new String[]{str, ExifInterface.TAG_RELATED_SOUND_FILE}, new String[]{str2, ExifInterface.TAG_SATURATION}, new String[]{str2, ExifInterface.TAG_SCENE_CAPTURE_TYPE}, new String[]{str, ExifInterface.TAG_SCENE_TYPE}, new String[]{str2, ExifInterface.TAG_SENSING_METHOD}, new String[]{str2, ExifInterface.TAG_SHARPNESS}, new String[]{str3, ExifInterface.TAG_SHUTTER_SPEED_VALUE}, new String[]{str, ExifInterface.TAG_SPATIAL_FREQUENCY_RESPONSE}, new String[]{str, ExifInterface.TAG_SPECTRAL_SENSITIVITY}, new String[]{str2, ExifInterface.TAG_SUBFILE_TYPE}, new String[]{str, ExifInterface.TAG_SUBSEC_TIME}, new String[]{str, ExifInterface.TAG_SUBSEC_TIME_DIGITIZED}, new String[]{str, ExifInterface.TAG_SUBSEC_TIME_ORIGINAL}, new String[]{str2, ExifInterface.TAG_SUBJECT_AREA}, new String[]{str3, ExifInterface.TAG_SUBJECT_DISTANCE}, new String[]{str2, ExifInterface.TAG_SUBJECT_DISTANCE_RANGE}, new String[]{str2, ExifInterface.TAG_SUBJECT_LOCATION}, new String[]{str, ExifInterface.TAG_USER_COMMENT}, new String[]{str2, ExifInterface.TAG_WHITE_BALANCE}, new String[]{str2, ExifInterface.TAG_GPS_ALTITUDE_REF}, new String[]{str, ExifInterface.TAG_GPS_AREA_INFORMATION}, new String[]{str3, ExifInterface.TAG_GPS_DOP}, new String[]{str, ExifInterface.TAG_GPS_DATESTAMP}, new String[]{str3, ExifInterface.TAG_GPS_DEST_BEARING}, new String[]{str, ExifInterface.TAG_GPS_DEST_BEARING_REF}, new String[]{str3, ExifInterface.TAG_GPS_DEST_DISTANCE}, new String[]{str, ExifInterface.TAG_GPS_DEST_DISTANCE_REF}, new String[]{str3, ExifInterface.TAG_GPS_DEST_LATITUDE}, new String[]{str, ExifInterface.TAG_GPS_DEST_LATITUDE_REF}, new String[]{str3, ExifInterface.TAG_GPS_DEST_LONGITUDE}, new String[]{str, ExifInterface.TAG_GPS_DEST_LONGITUDE_REF}, new String[]{str2, ExifInterface.TAG_GPS_DIFFERENTIAL}, new String[]{str3, ExifInterface.TAG_GPS_IMG_DIRECTION}, new String[]{str, ExifInterface.TAG_GPS_IMG_DIRECTION_REF}, new String[]{str, ExifInterface.TAG_GPS_LATITUDE_REF}, new String[]{str, ExifInterface.TAG_GPS_LONGITUDE_REF}, new String[]{str, ExifInterface.TAG_GPS_MAP_DATUM}, new String[]{str, ExifInterface.TAG_GPS_MEASURE_MODE}, new String[]{str, ExifInterface.TAG_GPS_PROCESSING_METHOD}, new String[]{str, ExifInterface.TAG_GPS_SATELLITES}, new String[]{str3, ExifInterface.TAG_GPS_SPEED}, new String[]{str, ExifInterface.TAG_GPS_SPEED_REF}, new String[]{str, ExifInterface.TAG_GPS_STATUS}, new String[]{str, ExifInterface.TAG_GPS_TIMESTAMP}, new String[]{str3, ExifInterface.TAG_GPS_TRACK}, new String[]{str, ExifInterface.TAG_GPS_TRACK_REF}, new String[]{str, ExifInterface.TAG_GPS_VERSION_ID}, new String[]{str, ExifInterface.TAG_INTEROPERABILITY_INDEX}, new String[]{str2, ExifInterface.TAG_THUMBNAIL_IMAGE_LENGTH}, new String[]{str2, ExifInterface.TAG_THUMBNAIL_IMAGE_WIDTH}, new String[]{str2, ExifInterface.TAG_DNG_VERSION}, new String[]{str2, ExifInterface.TAG_DEFAULT_CROP_SIZE}, new String[]{str2, ExifInterface.TAG_ORF_PREVIEW_IMAGE_START}, new String[]{str2, ExifInterface.TAG_ORF_PREVIEW_IMAGE_LENGTH}, new String[]{str2, ExifInterface.TAG_ORF_ASPECT_FRAME}, new String[]{str2, ExifInterface.TAG_RW2_SENSOR_BOTTOM_BORDER}, new String[]{str2, ExifInterface.TAG_RW2_SENSOR_LEFT_BORDER}, new String[]{str2, ExifInterface.TAG_RW2_SENSOR_RIGHT_BORDER}, new String[]{str2, ExifInterface.TAG_RW2_SENSOR_TOP_BORDER}, new String[]{str2, ExifInterface.TAG_RW2_ISO}};
    }

    public static void emitMountErrorEvent(final ViewGroup viewGroup, final String str) {
        final ReactContext reactContext = (ReactContext) viewGroup.getContext();
        reactContext.runOnNativeModulesQueueThread(new Runnable() {
            public void run() {
                ((UIManagerModule) reactContext.getNativeModule(UIManagerModule.class)).getEventDispatcher().dispatchEvent(CameraMountErrorEvent.obtain(viewGroup.getId(), str));
            }
        });
    }

    public static void emitCameraReadyEvent(final ViewGroup viewGroup) {
        final ReactContext reactContext = (ReactContext) viewGroup.getContext();
        reactContext.runOnNativeModulesQueueThread(new Runnable() {
            public void run() {
                ((UIManagerModule) reactContext.getNativeModule(UIManagerModule.class)).getEventDispatcher().dispatchEvent(CameraReadyEvent.obtain(viewGroup.getId()));
            }
        });
    }

    public static void emitPictureSavedEvent(final ViewGroup viewGroup, final WritableMap writableMap) {
        final ReactContext reactContext = (ReactContext) viewGroup.getContext();
        reactContext.runOnNativeModulesQueueThread(new Runnable() {
            public void run() {
                ((UIManagerModule) reactContext.getNativeModule(UIManagerModule.class)).getEventDispatcher().dispatchEvent(PictureSavedEvent.obtain(viewGroup.getId(), writableMap));
            }
        });
    }

    public static void emitPictureTakenEvent(final ViewGroup viewGroup) {
        final ReactContext reactContext = (ReactContext) viewGroup.getContext();
        reactContext.runOnNativeModulesQueueThread(new Runnable() {
            public void run() {
                ((UIManagerModule) reactContext.getNativeModule(UIManagerModule.class)).getEventDispatcher().dispatchEvent(PictureTakenEvent.obtain(viewGroup.getId()));
            }
        });
    }

    public static void emitFacesDetectedEvent(final ViewGroup viewGroup, final WritableArray writableArray) {
        final ReactContext reactContext = (ReactContext) viewGroup.getContext();
        reactContext.runOnNativeModulesQueueThread(new Runnable() {
            public void run() {
                ((UIManagerModule) reactContext.getNativeModule(UIManagerModule.class)).getEventDispatcher().dispatchEvent(FacesDetectedEvent.obtain(viewGroup.getId(), writableArray));
            }
        });
    }

    public static void emitFaceDetectionErrorEvent(final ViewGroup viewGroup, final RNFaceDetector rNFaceDetector) {
        final ReactContext reactContext = (ReactContext) viewGroup.getContext();
        reactContext.runOnNativeModulesQueueThread(new Runnable() {
            public void run() {
                ((UIManagerModule) reactContext.getNativeModule(UIManagerModule.class)).getEventDispatcher().dispatchEvent(FaceDetectionErrorEvent.obtain(viewGroup.getId(), rNFaceDetector));
            }
        });
    }

    public static void emitBarcodesDetectedEvent(final ViewGroup viewGroup, final WritableArray writableArray) {
        final ReactContext reactContext = (ReactContext) viewGroup.getContext();
        reactContext.runOnNativeModulesQueueThread(new Runnable() {
            public void run() {
                ((UIManagerModule) reactContext.getNativeModule(UIManagerModule.class)).getEventDispatcher().dispatchEvent(BarcodesDetectedEvent.obtain(viewGroup.getId(), writableArray));
            }
        });
    }

    public static void emitBarcodeDetectionErrorEvent(final ViewGroup viewGroup, final RNBarcodeDetector rNBarcodeDetector) {
        final ReactContext reactContext = (ReactContext) viewGroup.getContext();
        reactContext.runOnNativeModulesQueueThread(new Runnable() {
            public void run() {
                ((UIManagerModule) reactContext.getNativeModule(UIManagerModule.class)).getEventDispatcher().dispatchEvent(BarcodeDetectionErrorEvent.obtain(viewGroup.getId(), rNBarcodeDetector));
            }
        });
    }

    public static void emitBarCodeReadEvent(ViewGroup viewGroup, Result result, int i, int i2) {
        ReactContext reactContext = (ReactContext) viewGroup.getContext();
        final ViewGroup viewGroup2 = viewGroup;
        final Result result2 = result;
        final int i3 = i;
        final int i4 = i2;
        final ReactContext reactContext2 = reactContext;
        C24539 r1 = new Runnable() {
            public void run() {
                ((UIManagerModule) reactContext2.getNativeModule(UIManagerModule.class)).getEventDispatcher().dispatchEvent(BarCodeReadEvent.obtain(viewGroup2.getId(), result2, i3, i4));
            }
        };
        reactContext.runOnNativeModulesQueueThread(r1);
    }

    public static void emitTextRecognizedEvent(final ViewGroup viewGroup, final WritableArray writableArray) {
        final ReactContext reactContext = (ReactContext) viewGroup.getContext();
        reactContext.runOnNativeModulesQueueThread(new Runnable() {
            public void run() {
                ((UIManagerModule) reactContext.getNativeModule(UIManagerModule.class)).getEventDispatcher().dispatchEvent(TextRecognizedEvent.obtain(viewGroup.getId(), writableArray));
            }
        });
    }

    public static int getCorrectCameraRotation(int i, int i2, int i3) {
        if (i2 == 1) {
            return (i3 + i) % 360;
        }
        return ((i3 - i) + (rotationIsLandscape(i) ? 180 : 0)) % 360;
    }

    private static int getCamcorderProfileQualityFromCameraModuleConstant(int i) {
        if (i != 0) {
            if (i != 1) {
                if (i != 2) {
                    return (i == 3 || i == 4) ? 4 : 1;
                }
                return 5;
            }
        } else if (VERSION.SDK_INT >= 21) {
            return 8;
        }
        return 6;
    }

    public static CamcorderProfile getCamcorderProfile(int i) {
        CamcorderProfile camcorderProfile = CamcorderProfile.get(1);
        int camcorderProfileQualityFromCameraModuleConstant = getCamcorderProfileQualityFromCameraModuleConstant(i);
        if (CamcorderProfile.hasProfile(camcorderProfileQualityFromCameraModuleConstant)) {
            camcorderProfile = CamcorderProfile.get(camcorderProfileQualityFromCameraModuleConstant);
            if (i == 4) {
                camcorderProfile.videoFrameWidth = OlympusMakernoteDirectory.TAG_PREVIEW_IMAGE;
            }
        }
        return camcorderProfile;
    }

    public static WritableMap getExifData(ExifInterface exifInterface) {
        String[][] strArr;
        WritableMap createMap = Arguments.createMap();
        for (String[] strArr2 : exifTags) {
            String str = strArr2[1];
            if (exifInterface.getAttribute(str) != null) {
                String str2 = strArr2[0];
                char c = 65535;
                int hashCode = str2.hashCode();
                if (hashCode != -1325958191) {
                    if (hashCode != -891985903) {
                        if (hashCode == 104431 && str2.equals("int")) {
                            c = 1;
                        }
                    } else if (str2.equals("string")) {
                        c = 0;
                    }
                } else if (str2.equals("double")) {
                    c = 2;
                }
                if (c == 0) {
                    createMap.putString(str, exifInterface.getAttribute(str));
                } else if (c == 1) {
                    createMap.putInt(str, exifInterface.getAttributeInt(str, 0));
                } else if (c == 2) {
                    createMap.putDouble(str, exifInterface.getAttributeDouble(str, 0.0d));
                }
            }
        }
        double[] latLong = exifInterface.getLatLong();
        if (latLong != null) {
            createMap.putDouble(ExifInterface.TAG_GPS_LATITUDE, latLong[0]);
            createMap.putDouble(ExifInterface.TAG_GPS_LONGITUDE, latLong[1]);
            createMap.putDouble(ExifInterface.TAG_GPS_ALTITUDE, exifInterface.getAltitude(0.0d));
        }
        return createMap;
    }

    public static void setExifData(ExifInterface exifInterface, ReadableMap readableMap) {
        String[][] strArr;
        for (String[] strArr2 : exifTags) {
            String str = strArr2[1];
            if (readableMap.hasKey(str)) {
                String str2 = strArr2[0];
                char c = 65535;
                int hashCode = str2.hashCode();
                if (hashCode != -1325958191) {
                    if (hashCode != -891985903) {
                        if (hashCode == 104431 && str2.equals("int")) {
                            c = 1;
                        }
                    } else if (str2.equals("string")) {
                        c = 0;
                    }
                } else if (str2.equals("double")) {
                    c = 2;
                }
                if (c == 0) {
                    exifInterface.setAttribute(str, readableMap.getString(str));
                } else if (c == 1) {
                    exifInterface.setAttribute(str, Integer.toString(readableMap.getInt(str)));
                    readableMap.getInt(str);
                } else if (c == 2) {
                    exifInterface.setAttribute(str, Double.toString(readableMap.getDouble(str)));
                    readableMap.getDouble(str);
                }
            }
        }
        String str3 = ExifInterface.TAG_GPS_LATITUDE;
        if (readableMap.hasKey(str3)) {
            String str4 = ExifInterface.TAG_GPS_LONGITUDE;
            if (readableMap.hasKey(str4)) {
                exifInterface.setLatLong(readableMap.getDouble(str3), readableMap.getDouble(str4));
            }
        }
        String str5 = ExifInterface.TAG_GPS_ALTITUDE;
        if (readableMap.hasKey(str5)) {
            exifInterface.setAltitude(readableMap.getDouble(str5));
        }
    }

    public static void clearExifData(ExifInterface exifInterface) {
        for (String[] strArr : exifTags) {
            exifInterface.setAttribute(strArr[1], null);
        }
        exifInterface.setAttribute(ExifInterface.TAG_GPS_LATITUDE, null);
        exifInterface.setAttribute(ExifInterface.TAG_GPS_LONGITUDE, null);
        exifInterface.setAttribute(ExifInterface.TAG_GPS_ALTITUDE, null);
    }

    public static Bitmap generateSimulatorPhoto(int i, int i2) {
        Bitmap createBitmap = Bitmap.createBitmap(i, i2, Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        Paint paint = new Paint();
        paint.setColor(ViewCompat.MEASURED_STATE_MASK);
        float f = (float) i;
        float f2 = (float) i2;
        canvas.drawRect(0.0f, 0.0f, f, f2, paint);
        Paint paint2 = new Paint();
        paint2.setColor(InputDeviceCompat.SOURCE_ANY);
        paint2.setTextSize(35.0f);
        Calendar instance = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd G '->' HH:mm:ss z");
        canvas.drawText(simpleDateFormat.format(instance.getTime()), 0.1f * f, f2 * 0.2f, paint2);
        canvas.drawText(simpleDateFormat.format(instance.getTime()), 0.2f * f, f2 * 0.4f, paint2);
        canvas.drawText(simpleDateFormat.format(instance.getTime()), 0.3f * f, 0.6f * f2, paint2);
        canvas.drawText(simpleDateFormat.format(instance.getTime()), f * 0.4f, f2 * 0.8f, paint2);
        return createBitmap;
    }
}
