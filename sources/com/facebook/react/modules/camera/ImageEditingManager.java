package com.facebook.react.modules.camera;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.BitmapRegionDecoder;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.net.Uri;
import android.os.AsyncTask;
import android.text.TextUtils;
import androidx.annotation.Nullable;
import androidx.exifinterface.media.ExifInterface;
import com.facebook.common.logging.FLog;
import com.facebook.common.util.UriUtil;
import com.facebook.infer.annotation.Assertions;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.GuardedAsyncTask;
import com.facebook.react.bridge.JSApplicationIllegalArgumentException;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.common.ReactConstants;
import com.facebook.react.module.annotations.ReactModule;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@ReactModule(name = "ImageEditingManager")
public class ImageEditingManager extends ReactContextBaseJavaModule {
    private static final int COMPRESS_QUALITY = 90;
    @SuppressLint({"InlinedApi"})
    private static final String[] EXIF_ATTRIBUTES = {ExifInterface.TAG_F_NUMBER, ExifInterface.TAG_DATETIME, ExifInterface.TAG_DATETIME_DIGITIZED, ExifInterface.TAG_EXPOSURE_TIME, ExifInterface.TAG_FLASH, ExifInterface.TAG_FOCAL_LENGTH, ExifInterface.TAG_GPS_ALTITUDE, ExifInterface.TAG_GPS_ALTITUDE_REF, ExifInterface.TAG_GPS_DATESTAMP, ExifInterface.TAG_GPS_LATITUDE, ExifInterface.TAG_GPS_LATITUDE_REF, ExifInterface.TAG_GPS_LONGITUDE, ExifInterface.TAG_GPS_LONGITUDE_REF, ExifInterface.TAG_GPS_PROCESSING_METHOD, ExifInterface.TAG_GPS_TIMESTAMP, ExifInterface.TAG_IMAGE_LENGTH, ExifInterface.TAG_IMAGE_WIDTH, ExifInterface.TAG_ISO_SPEED_RATINGS, ExifInterface.TAG_MAKE, ExifInterface.TAG_MODEL, ExifInterface.TAG_ORIENTATION, ExifInterface.TAG_SUBSEC_TIME, ExifInterface.TAG_SUBSEC_TIME_DIGITIZED, ExifInterface.TAG_SUBSEC_TIME_ORIGINAL, ExifInterface.TAG_WHITE_BALANCE};
    private static final List<String> LOCAL_URI_PREFIXES = Arrays.asList(new String[]{"file://", "content://"});
    public static final String NAME = "ImageEditingManager";
    private static final String TEMP_FILE_PREFIX = "ReactNative_cropped_image_";

    private static class CleanTask extends GuardedAsyncTask<Void, Void> {
        private final Context mContext;

        private CleanTask(ReactContext reactContext) {
            super(reactContext);
            this.mContext = reactContext;
        }

        /* access modifiers changed from: protected */
        public void doInBackgroundGuarded(Void... voidArr) {
            cleanDirectory(this.mContext.getCacheDir());
            File externalCacheDir = this.mContext.getExternalCacheDir();
            if (externalCacheDir != null) {
                cleanDirectory(externalCacheDir);
            }
        }

        private void cleanDirectory(File file) {
            File[] listFiles = file.listFiles(new FilenameFilter() {
                public boolean accept(File file, String str) {
                    return str.startsWith(ImageEditingManager.TEMP_FILE_PREFIX);
                }
            });
            if (listFiles != null) {
                for (File delete : listFiles) {
                    delete.delete();
                }
            }
        }
    }

    private static class CropTask extends GuardedAsyncTask<Void, Void> {
        final Context mContext;
        final Callback mError;
        final int mHeight;
        final Callback mSuccess;
        int mTargetHeight;
        int mTargetWidth;
        final String mUri;
        final int mWidth;

        /* renamed from: mX */
        final int f52mX;

        /* renamed from: mY */
        final int f53mY;

        private CropTask(ReactContext reactContext, String str, int i, int i2, int i3, int i4, Callback callback, Callback callback2) {
            super(reactContext);
            this.mTargetWidth = 0;
            this.mTargetHeight = 0;
            if (i < 0 || i2 < 0 || i3 <= 0 || i4 <= 0) {
                throw new JSApplicationIllegalArgumentException(String.format("Invalid crop rectangle: [%d, %d, %d, %d]", new Object[]{Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3), Integer.valueOf(i4)}));
            }
            this.mContext = reactContext;
            this.mUri = str;
            this.f52mX = i;
            this.f53mY = i2;
            this.mWidth = i3;
            this.mHeight = i4;
            this.mSuccess = callback;
            this.mError = callback2;
        }

        public void setTargetSize(int i, int i2) {
            if (i <= 0 || i2 <= 0) {
                throw new JSApplicationIllegalArgumentException(String.format("Invalid target size: [%d, %d]", new Object[]{Integer.valueOf(i), Integer.valueOf(i2)}));
            }
            this.mTargetWidth = i;
            this.mTargetHeight = i2;
        }

        private InputStream openBitmapInputStream() throws IOException {
            InputStream inputStream;
            if (ImageEditingManager.isLocalUri(this.mUri)) {
                inputStream = this.mContext.getContentResolver().openInputStream(Uri.parse(this.mUri));
            } else {
                inputStream = new URL(this.mUri).openConnection().getInputStream();
            }
            if (inputStream != null) {
                return inputStream;
            }
            StringBuilder sb = new StringBuilder();
            sb.append("Cannot open bitmap: ");
            sb.append(this.mUri);
            throw new IOException(sb.toString());
        }

        /* access modifiers changed from: protected */
        public void doInBackgroundGuarded(Void... voidArr) {
            Bitmap bitmap;
            try {
                Options options = new Options();
                if (this.mTargetWidth > 0 && this.mTargetHeight > 0) {
                    bitmap = cropAndResize(this.mTargetWidth, this.mTargetHeight, options);
                } else {
                    bitmap = crop(options);
                }
                String str = options.outMimeType;
                if (str == null || str.isEmpty()) {
                    throw new IOException("Could not determine MIME type");
                }
                File access$300 = ImageEditingManager.createTempFile(this.mContext, str);
                ImageEditingManager.writeCompressedBitmapToFile(bitmap, str, access$300);
                if (str.equals("image/jpeg")) {
                    ImageEditingManager.copyExif(this.mContext, Uri.parse(this.mUri), access$300);
                }
                this.mSuccess.invoke(Uri.fromFile(access$300).toString());
            } catch (Exception e) {
                this.mError.invoke(e.getMessage());
            }
        }

        private Bitmap crop(Options options) throws IOException {
            InputStream openBitmapInputStream = openBitmapInputStream();
            BitmapRegionDecoder newInstance = BitmapRegionDecoder.newInstance(openBitmapInputStream, false);
            try {
                return newInstance.decodeRegion(new Rect(this.f52mX, this.f53mY, this.f52mX + this.mWidth, this.f53mY + this.mHeight), options);
            } finally {
                if (openBitmapInputStream != null) {
                    openBitmapInputStream.close();
                }
                newInstance.recycle();
            }
        }

        /* JADX INFO: finally extract failed */
        private Bitmap cropAndResize(int i, int i2, Options options) throws IOException {
            float f;
            float f2;
            float f3;
            float f4;
            float f5;
            int i3 = i;
            int i4 = i2;
            Options options2 = options;
            Assertions.assertNotNull(options);
            Options options3 = new Options();
            options3.inJustDecodeBounds = true;
            InputStream openBitmapInputStream = openBitmapInputStream();
            try {
                BitmapFactory.decodeStream(openBitmapInputStream, null, options3);
                if (openBitmapInputStream != null) {
                    openBitmapInputStream.close();
                }
                int i5 = this.mWidth;
                float f6 = (float) i5;
                int i6 = this.mHeight;
                float f7 = (float) i3;
                float f8 = (float) i4;
                float f9 = f7 / f8;
                if (f6 / ((float) i6) > f9) {
                    f4 = ((float) i6) * f9;
                    f3 = (float) i6;
                    f = ((float) this.f52mX) + ((((float) i5) - f4) / 2.0f);
                    f5 = (float) this.f53mY;
                    f2 = f8 / ((float) i6);
                } else {
                    f4 = (float) i5;
                    float f10 = ((float) i5) / f9;
                    f = (float) this.f52mX;
                    float f11 = (((float) i6) - f10) / 2.0f;
                    float f12 = f7 / ((float) i5);
                    f3 = f10;
                    f2 = f12;
                    f5 = f11 + ((float) this.f53mY);
                }
                options2.inSampleSize = ImageEditingManager.getDecodeSampleSize(this.mWidth, this.mHeight, i3, i4);
                options3.inJustDecodeBounds = false;
                InputStream openBitmapInputStream2 = openBitmapInputStream();
                try {
                    Bitmap decodeStream = BitmapFactory.decodeStream(openBitmapInputStream2, null, options2);
                    if (decodeStream != null) {
                        if (openBitmapInputStream2 != null) {
                            openBitmapInputStream2.close();
                        }
                        int floor = (int) Math.floor((double) (f / ((float) options2.inSampleSize)));
                        int floor2 = (int) Math.floor((double) (f5 / ((float) options2.inSampleSize)));
                        int floor3 = (int) Math.floor((double) (f4 / ((float) options2.inSampleSize)));
                        int floor4 = (int) Math.floor((double) (f3 / ((float) options2.inSampleSize)));
                        float f13 = f2 * ((float) options2.inSampleSize);
                        Matrix matrix = new Matrix();
                        matrix.setScale(f13, f13);
                        return Bitmap.createBitmap(decodeStream, floor, floor2, floor3, floor4, matrix, true);
                    }
                    StringBuilder sb = new StringBuilder();
                    sb.append("Cannot decode bitmap: ");
                    sb.append(this.mUri);
                    throw new IOException(sb.toString());
                } catch (Throwable th) {
                    if (openBitmapInputStream2 != null) {
                        openBitmapInputStream2.close();
                    }
                    throw th;
                }
            } catch (Throwable th2) {
                Throwable th3 = th2;
                if (openBitmapInputStream != null) {
                    openBitmapInputStream.close();
                }
                throw th3;
            }
        }
    }

    public String getName() {
        return NAME;
    }

    public ImageEditingManager(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        new CleanTask(getReactApplicationContext()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }

    public Map<String, Object> getConstants() {
        return Collections.emptyMap();
    }

    public void onCatalystInstanceDestroy() {
        new CleanTask(getReactApplicationContext()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }

    @ReactMethod
    public void cropImage(String str, ReadableMap readableMap, Callback callback, Callback callback2) {
        ReadableMap readableMap2 = readableMap;
        String str2 = "offset";
        ReadableMap readableMap3 = null;
        ReadableMap map = readableMap2.hasKey(str2) ? readableMap2.getMap(str2) : null;
        String str3 = "size";
        if (readableMap2.hasKey(str3)) {
            readableMap3 = readableMap2.getMap(str3);
        }
        if (!(map == null || readableMap3 == null)) {
            String str4 = "x";
            if (map.hasKey(str4)) {
                String str5 = "y";
                if (map.hasKey(str5)) {
                    String str6 = "width";
                    if (readableMap3.hasKey(str6)) {
                        String str7 = "height";
                        if (readableMap3.hasKey(str7)) {
                            if (str == null || str.isEmpty()) {
                                throw new JSApplicationIllegalArgumentException("Please specify a URI");
                            }
                            CropTask cropTask = new CropTask(getReactApplicationContext(), str, (int) map.getDouble(str4), (int) map.getDouble(str5), (int) readableMap3.getDouble(str6), (int) readableMap3.getDouble(str7), callback, callback2);
                            String str8 = "displaySize";
                            if (readableMap2.hasKey(str8)) {
                                ReadableMap map2 = readableMap2.getMap(str8);
                                cropTask.setTargetSize((int) map2.getDouble(str6), (int) map2.getDouble(str7));
                            }
                            cropTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
                            return;
                        }
                    }
                }
            }
        }
        throw new JSApplicationIllegalArgumentException("Please specify offset and size");
    }

    /* access modifiers changed from: private */
    public static void copyExif(Context context, Uri uri, File file) throws IOException {
        String[] strArr;
        File fileFromUri = getFileFromUri(context, uri);
        if (fileFromUri == null) {
            StringBuilder sb = new StringBuilder();
            sb.append("Couldn't get real path for uri: ");
            sb.append(uri);
            FLog.m84w(ReactConstants.TAG, sb.toString());
            return;
        }
        android.media.ExifInterface exifInterface = new android.media.ExifInterface(fileFromUri.getAbsolutePath());
        android.media.ExifInterface exifInterface2 = new android.media.ExifInterface(file.getAbsolutePath());
        for (String str : EXIF_ATTRIBUTES) {
            String attribute = exifInterface.getAttribute(str);
            if (attribute != null) {
                exifInterface2.setAttribute(str, attribute);
            }
        }
        exifInterface2.saveAttributes();
    }

    @Nullable
    private static File getFileFromUri(Context context, Uri uri) {
        if (uri.getScheme().equals(UriUtil.LOCAL_FILE_SCHEME)) {
            return new File(uri.getPath());
        }
        if (uri.getScheme().equals("content")) {
            Cursor query = context.getContentResolver().query(uri, new String[]{"_data"}, null, null, null);
            if (query != null) {
                try {
                    if (query.moveToFirst()) {
                        String string = query.getString(0);
                        if (!TextUtils.isEmpty(string)) {
                            return new File(string);
                        }
                    }
                    query.close();
                } finally {
                    query.close();
                }
            }
        }
        return null;
    }

    /* access modifiers changed from: private */
    public static boolean isLocalUri(String str) {
        for (String startsWith : LOCAL_URI_PREFIXES) {
            if (str.startsWith(startsWith)) {
                return true;
            }
        }
        return false;
    }

    private static String getFileExtensionForType(@Nullable String str) {
        if ("image/png".equals(str)) {
            return ".png";
        }
        return "image/webp".equals(str) ? ".webp" : ".jpg";
    }

    private static CompressFormat getCompressFormatForType(String str) {
        if ("image/png".equals(str)) {
            return CompressFormat.PNG;
        }
        if ("image/webp".equals(str)) {
            return CompressFormat.WEBP;
        }
        return CompressFormat.JPEG;
    }

    /* access modifiers changed from: private */
    public static void writeCompressedBitmapToFile(Bitmap bitmap, String str, File file) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        try {
            bitmap.compress(getCompressFormatForType(str), 90, fileOutputStream);
        } finally {
            fileOutputStream.close();
        }
    }

    /* access modifiers changed from: private */
    public static File createTempFile(Context context, @Nullable String str) throws IOException {
        File externalCacheDir = context.getExternalCacheDir();
        File cacheDir = context.getCacheDir();
        if (externalCacheDir == null && cacheDir == null) {
            throw new IOException("No cache directory available");
        }
        if (externalCacheDir == null || (cacheDir != null && externalCacheDir.getFreeSpace() <= cacheDir.getFreeSpace())) {
            externalCacheDir = cacheDir;
        }
        return File.createTempFile(TEMP_FILE_PREFIX, getFileExtensionForType(str), externalCacheDir);
    }

    /* access modifiers changed from: private */
    public static int getDecodeSampleSize(int i, int i2, int i3, int i4) {
        int i5 = 1;
        if (i2 > i3 || i > i4) {
            int i6 = i2 / 2;
            int i7 = i / 2;
            while (i7 / i5 >= i3 && i6 / i5 >= i4) {
                i5 *= 2;
            }
        }
        return i5;
    }
}
