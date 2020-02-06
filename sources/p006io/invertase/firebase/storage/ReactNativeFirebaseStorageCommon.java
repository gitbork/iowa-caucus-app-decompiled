package p006io.invertase.firebase.storage;

import android.net.Uri;
import android.os.Environment;
import android.webkit.MimeTypeMap;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.google.firebase.storage.ListResult;
import com.google.firebase.storage.StorageException;
import com.google.firebase.storage.StorageMetadata;
import com.google.firebase.storage.StorageMetadata.Builder;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import java.util.Map.Entry;
import javax.annotation.Nullable;
import p006io.invertase.firebase.app.ReactNativeFirebaseApp;
import p006io.invertase.firebase.common.ReactNativeFirebaseModule;
import p006io.invertase.firebase.common.SharedUtils;

/* renamed from: io.invertase.firebase.storage.ReactNativeFirebaseStorageCommon */
class ReactNativeFirebaseStorageCommon {
    private static final String CODE_BUCKET_NOT_FOUND = "bucket-not-found";
    static final String CODE_CANCELLED = "cancelled";
    private static final String CODE_FILE_NOT_FOUND = "file-not-found";
    private static final String CODE_NON_MATCHING_CHECKSUM = "non-matching-checksum";
    private static final String CODE_OBJECT_NOT_FOUND = "object-not-found";
    private static final String CODE_PROJECT_NOT_FOUND = "project-not-found";
    private static final String CODE_QUOTA_EXCEEDED = "quota-exceeded";
    private static final String CODE_RETRY_LIMIT_EXCEEDED = "retry-limit-exceeded";
    private static final String CODE_UNAUTHENTICATED = "unauthenticated";
    private static final String CODE_UNAUTHORIZED = "unauthorized";
    private static final String KEY_BUCKET = "bucket";
    private static final String KEY_CACHE_CONTROL = "cacheControl";
    private static final String KEY_CONTENT_DISPOSITION = "contentDisposition";
    private static final String KEY_CONTENT_ENCODING = "contentEncoding";
    private static final String KEY_CONTENT_LANG = "contentLanguage";
    private static final String KEY_CONTENT_TYPE = "contentType";
    private static final String KEY_CUSTOM_META = "customMetadata";
    private static final String KEY_FULL_PATH = "fullPath";
    private static final String KEY_GENERATION = "generation";
    private static final String KEY_MD5_HASH = "md5Hash";
    private static final String KEY_META_GENERATION = "metageneration";
    private static final String KEY_NAME = "name";
    private static final String KEY_SIZE = "size";
    private static final String KEY_TIME_CREATED = "timeCreated";
    private static final String KEY_UPDATED = "updated";
    static final String STATUS_CANCELLED = "cancelled";
    static final String STATUS_ERROR = "error";
    private static final String STATUS_PAUSED = "paused";
    private static final String STATUS_RUNNING = "running";
    private static final String STATUS_SUCCESS = "success";
    private static final String STATUS_UNKNOWN = "unknown";

    ReactNativeFirebaseStorageCommon() {
    }

    static String getTaskStatus(@Nullable StorageTask<?> storageTask) {
        String str = "unknown";
        if (storageTask == null) {
            return str;
        }
        if (storageTask.isInProgress()) {
            return STATUS_RUNNING;
        }
        if (storageTask.isPaused()) {
            return STATUS_PAUSED;
        }
        if (storageTask.isSuccessful() || storageTask.isComplete()) {
            return "success";
        }
        if (storageTask.isCanceled()) {
            return "cancelled";
        }
        return storageTask.getException() != null ? STATUS_ERROR : str;
    }

    static StorageMetadata buildMetadataFromMap(ReadableMap readableMap, @Nullable Uri uri) {
        Builder builder = new Builder();
        if (readableMap != null) {
            String str = KEY_CUSTOM_META;
            if (readableMap.hasKey(str)) {
                ReadableMap map = readableMap.getMap(str);
                map.getClass();
                for (Entry entry : map.toHashMap().entrySet()) {
                    builder.setCustomMetadata((String) entry.getKey(), (String) entry.getValue());
                }
            }
            String str2 = KEY_CACHE_CONTROL;
            if (readableMap.hasKey(str2)) {
                builder.setCacheControl(readableMap.getString(str2));
            }
            String str3 = KEY_CONTENT_ENCODING;
            if (readableMap.hasKey(str3)) {
                builder.setContentEncoding(readableMap.getString(str3));
            }
            String str4 = KEY_CONTENT_LANG;
            if (readableMap.hasKey(str4)) {
                builder.setContentLanguage(readableMap.getString(str4));
            }
            String str5 = KEY_CONTENT_DISPOSITION;
            if (readableMap.hasKey(str5)) {
                builder.setContentDisposition(readableMap.getString(str5));
            }
        }
        if (readableMap != null) {
            String str6 = KEY_CONTENT_TYPE;
            if (readableMap.hasKey(str6)) {
                builder.setContentType(readableMap.getString(str6));
                return builder.build();
            }
        }
        if (uri != null) {
            String str7 = null;
            String scheme = uri.getScheme();
            if (scheme != null && scheme.equals("content")) {
                str7 = ReactNativeFirebaseApp.getApplicationContext().getContentResolver().getType(uri);
            }
            if (str7 == null) {
                str7 = MimeTypeMap.getSingleton().getMimeTypeFromExtension(MimeTypeMap.getFileExtensionFromUrl(uri.toString()).toLowerCase());
            }
            if (str7 != null) {
                builder.setContentType(str7);
            }
        }
        return builder.build();
    }

    static WritableMap getMetadataAsMap(@Nullable StorageMetadata storageMetadata) {
        if (storageMetadata == null) {
            return null;
        }
        WritableMap createMap = Arguments.createMap();
        createMap.putString(KEY_BUCKET, storageMetadata.getBucket());
        createMap.putString(KEY_GENERATION, storageMetadata.getGeneration());
        createMap.putString(KEY_META_GENERATION, storageMetadata.getMetadataGeneration());
        createMap.putString(KEY_FULL_PATH, storageMetadata.getPath());
        createMap.putString("name", storageMetadata.getName());
        createMap.putDouble(KEY_SIZE, (double) storageMetadata.getSizeBytes());
        createMap.putString(KEY_TIME_CREATED, SharedUtils.timestampToUTC(storageMetadata.getCreationTimeMillis() / 1000));
        createMap.putString(KEY_UPDATED, SharedUtils.timestampToUTC(storageMetadata.getUpdatedTimeMillis() / 1000));
        createMap.putString(KEY_MD5_HASH, storageMetadata.getMd5Hash());
        String cacheControl = storageMetadata.getCacheControl();
        String str = KEY_CACHE_CONTROL;
        if (cacheControl == null || storageMetadata.getCacheControl().length() <= 0) {
            createMap.putNull(str);
        } else {
            createMap.putString(str, storageMetadata.getCacheControl());
        }
        String contentLanguage = storageMetadata.getContentLanguage();
        String str2 = KEY_CONTENT_LANG;
        if (contentLanguage == null || storageMetadata.getContentLanguage().length() <= 0) {
            createMap.putNull(str2);
        } else {
            createMap.putString(str2, storageMetadata.getContentLanguage());
        }
        createMap.putString(KEY_CONTENT_DISPOSITION, storageMetadata.getContentDisposition());
        createMap.putString(KEY_CONTENT_ENCODING, storageMetadata.getContentEncoding());
        createMap.putString(KEY_CONTENT_TYPE, storageMetadata.getContentType());
        int size = storageMetadata.getCustomMetadataKeys().size();
        String str3 = KEY_CUSTOM_META;
        if (size > 0) {
            WritableMap createMap2 = Arguments.createMap();
            for (String str4 : storageMetadata.getCustomMetadataKeys()) {
                createMap2.putString(str4, storageMetadata.getCustomMetadata(str4));
            }
            createMap.putMap(str3, createMap2);
        } else {
            createMap.putNull(str3);
        }
        return createMap;
    }

    static WritableMap getListResultAsMap(ListResult listResult) {
        WritableMap createMap = Arguments.createMap();
        createMap.putString("nextPageToken", listResult.getPageToken());
        WritableArray createArray = Arguments.createArray();
        WritableArray createArray2 = Arguments.createArray();
        for (StorageReference path : listResult.getItems()) {
            createArray.pushString(path.getPath());
        }
        for (StorageReference path2 : listResult.getPrefixes()) {
            createArray2.pushString(path2.getPath());
        }
        createMap.putArray("items", createArray);
        createMap.putArray("prefixes", createArray2);
        return createMap;
    }

    static String[] getExceptionCodeAndMessage(@Nullable Exception exc) {
        String str;
        String str2 = "No object exists at the desired reference.";
        String str3 = CODE_OBJECT_NOT_FOUND;
        String str4 = "unknown";
        if (exc != null) {
            String message = exc.getMessage();
            if (exc instanceof StorageException) {
                StorageException storageException = (StorageException) exc;
                Throwable cause = storageException.getCause();
                int errorCode = storageException.getErrorCode();
                if (errorCode == -13040) {
                    str = "cancelled";
                    message = "User cancelled the operation.";
                } else if (errorCode == -13031) {
                    str = CODE_NON_MATCHING_CHECKSUM;
                    message = "File on the client does not match the checksum of the file received by the server.";
                } else if (errorCode == -13030) {
                    str = CODE_RETRY_LIMIT_EXCEEDED;
                    message = "The maximum time limit on an operation (upload, download, delete, etc.) has been exceeded.";
                } else if (errorCode == -13021) {
                    str = CODE_UNAUTHORIZED;
                    message = "User is not authorized to perform the desired action.";
                } else if (errorCode != -13020) {
                    switch (errorCode) {
                        case StorageException.ERROR_QUOTA_EXCEEDED /*-13013*/:
                            str = CODE_QUOTA_EXCEEDED;
                            message = "Quota on your Firebase Storage bucket has been exceeded.";
                            break;
                        case StorageException.ERROR_PROJECT_NOT_FOUND /*-13012*/:
                            str = CODE_PROJECT_NOT_FOUND;
                            message = "No project is configured for Firebase Storage.";
                            break;
                        case StorageException.ERROR_BUCKET_NOT_FOUND /*-13011*/:
                            str = CODE_BUCKET_NOT_FOUND;
                            message = "No bucket is configured for Firebase Storage.";
                            break;
                        case StorageException.ERROR_OBJECT_NOT_FOUND /*-13010*/:
                            message = str2;
                            str = str3;
                            break;
                        default:
                            str = str4;
                            break;
                    }
                } else {
                    str = CODE_UNAUTHENTICATED;
                    message = "User is unauthenticated. Authenticate and try again.";
                }
                if (str.equals(str4) && cause != null) {
                    if (cause.getMessage().contains("No such file or directory")) {
                        str3 = CODE_FILE_NOT_FOUND;
                        str2 = "The local file specified does not exist on the device.";
                    } else if (!cause.getMessage().contains("Not Found.  Could not get object")) {
                        str2 = cause.getMessage();
                    }
                    str = str3;
                }
            } else {
                str = str4;
            }
            str2 = message;
        } else {
            str2 = "An unknown error has occurred.";
            str = str4;
        }
        return new String[]{str, str2};
    }

    static void promiseRejectStorageException(Promise promise, @Nullable Exception exc) {
        String[] exceptionCodeAndMessage = getExceptionCodeAndMessage(exc);
        ReactNativeFirebaseModule.rejectPromiseWithCodeAndMessage(promise, exceptionCodeAndMessage[0], exceptionCodeAndMessage[1]);
    }

    static boolean isExternalStorageWritable() {
        boolean z;
        boolean z2;
        String externalStorageState = Environment.getExternalStorageState();
        if ("mounted".equals(externalStorageState)) {
            z2 = true;
            z = true;
        } else {
            z2 = "mounted_ro".equals(externalStorageState);
            z = false;
        }
        if (!z2 || !z) {
            return false;
        }
        return true;
    }
}
