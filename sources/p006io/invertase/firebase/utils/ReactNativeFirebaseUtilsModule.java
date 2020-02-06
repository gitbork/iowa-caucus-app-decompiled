package p006io.invertase.firebase.utils;

import android.app.Activity;
import android.content.IntentSender.SendIntentException;
import android.os.Build.VERSION;
import android.os.Environment;
import android.provider.Settings.System;
import android.util.Log;
import androidx.core.app.NotificationCompat;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableMap;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import p006io.invertase.firebase.app.ReactNativeFirebaseApp;
import p006io.invertase.firebase.common.ReactNativeFirebaseModule;

/* renamed from: io.invertase.firebase.utils.ReactNativeFirebaseUtilsModule */
public class ReactNativeFirebaseUtilsModule extends ReactNativeFirebaseModule {
    private static final String FIREBASE_TEST_LAB = "firebase.test.lab";
    private static final String KEY_CACHE_DIRECTORY = "CACHES_DIRECTORY";
    private static final String KEY_DOCUMENT_DIRECTORY = "DOCUMENT_DIRECTORY";
    private static final String KEY_EXTERNAL_DIRECTORY = "EXTERNAL_DIRECTORY";
    private static final String KEY_EXT_STORAGE_DIRECTORY = "EXTERNAL_STORAGE_DIRECTORY";
    private static final String KEY_LIBRARY_DIRECTORY = "LIBRARY_DIRECTORY";
    private static final String KEY_MAIN_BUNDLE = "MAIN_BUNDLE";
    private static final String KEY_MOVIES_DIRECTORY = "MOVIES_DIRECTORY";
    private static final String KEY_PICS_DIRECTORY = "PICTURES_DIRECTORY";
    private static final String KEY_TEMP_DIRECTORY = "TEMP_DIRECTORY";
    private static final String TAG = "Utils";

    public ReactNativeFirebaseUtilsModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext, TAG);
    }

    private static Boolean isRunningInTestLab() {
        return Boolean.valueOf("true".equals(System.getString(ReactNativeFirebaseApp.getApplicationContext().getContentResolver(), FIREBASE_TEST_LAB)));
    }

    @ReactMethod
    public void androidGetPlayServicesStatus(Promise promise) {
        promise.resolve(getPlayServicesStatusMap());
    }

    @ReactMethod
    public void androidPromptForPlayServices() {
        int isGooglePlayServicesAvailable = isGooglePlayServicesAvailable();
        GoogleApiAvailability instance = GoogleApiAvailability.getInstance();
        if (isGooglePlayServicesAvailable != 0 && instance.isUserResolvableError(isGooglePlayServicesAvailable)) {
            Activity activity = getActivity();
            if (activity != null) {
                instance.getErrorDialog(activity, isGooglePlayServicesAvailable, isGooglePlayServicesAvailable).show();
            }
        }
    }

    @ReactMethod
    public void androidResolutionForPlayServices() {
        int isGooglePlayServicesAvailable = isGooglePlayServicesAvailable();
        ConnectionResult connectionResult = new ConnectionResult(isGooglePlayServicesAvailable);
        if (!connectionResult.isSuccess() && connectionResult.hasResolution()) {
            Activity activity = getActivity();
            if (activity != null) {
                try {
                    connectionResult.startResolutionForResult(activity, isGooglePlayServicesAvailable);
                } catch (SendIntentException e) {
                    Log.d(TAG, "resolutionForPlayServices", e);
                }
            }
        }
    }

    @ReactMethod
    public void androidMakePlayServicesAvailable() {
        if (isGooglePlayServicesAvailable() != 0) {
            Activity activity = getActivity();
            if (activity != null) {
                GoogleApiAvailability.getInstance().makeGooglePlayServicesAvailable(activity);
            }
        }
    }

    private int isGooglePlayServicesAvailable() {
        return GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(getContext());
    }

    private WritableMap getPlayServicesStatusMap() {
        WritableMap createMap = Arguments.createMap();
        GoogleApiAvailability instance = GoogleApiAvailability.getInstance();
        int isGooglePlayServicesAvailable = instance.isGooglePlayServicesAvailable(getContext());
        createMap.putInt(NotificationCompat.CATEGORY_STATUS, isGooglePlayServicesAvailable);
        String str = "isAvailable";
        if (isGooglePlayServicesAvailable == 0) {
            createMap.putBoolean(str, true);
        } else {
            createMap.putBoolean(str, false);
            createMap.putString("error", instance.getErrorString(isGooglePlayServicesAvailable));
            createMap.putBoolean("isUserResolvableError", instance.isUserResolvableError(isGooglePlayServicesAvailable));
            createMap.putBoolean("hasResolution", new ConnectionResult(isGooglePlayServicesAvailable).hasResolution());
        }
        return createMap;
    }

    public Map<String, Object> getConstants() {
        HashMap hashMap = new HashMap();
        hashMap.put("isRunningInTestLab", isRunningInTestLab());
        hashMap.put("androidPlayServices", getPlayServicesStatusMap());
        ReactApplicationContext reactApplicationContext = getReactApplicationContext();
        hashMap.put(KEY_MAIN_BUNDLE, "");
        hashMap.put(KEY_LIBRARY_DIRECTORY, reactApplicationContext.getFilesDir().getAbsolutePath());
        hashMap.put(KEY_TEMP_DIRECTORY, reactApplicationContext.getCacheDir().getAbsolutePath());
        hashMap.put(KEY_CACHE_DIRECTORY, reactApplicationContext.getCacheDir().getAbsolutePath());
        int i = VERSION.SDK_INT;
        String str = KEY_DOCUMENT_DIRECTORY;
        if (i >= 19) {
            hashMap.put(str, Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS).getAbsolutePath());
        } else {
            hashMap.put(str, reactApplicationContext.getFilesDir().getAbsolutePath());
        }
        hashMap.put(KEY_PICS_DIRECTORY, Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getAbsolutePath());
        hashMap.put(KEY_MOVIES_DIRECTORY, Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MOVIES).getAbsolutePath());
        File externalStorageDirectory = Environment.getExternalStorageDirectory();
        if (externalStorageDirectory != null) {
            hashMap.put(KEY_EXT_STORAGE_DIRECTORY, externalStorageDirectory.getAbsolutePath());
        }
        File externalFilesDir = reactApplicationContext.getExternalFilesDir(null);
        if (externalFilesDir != null) {
            hashMap.put(KEY_EXTERNAL_DIRECTORY, externalFilesDir.getAbsolutePath());
        }
        return hashMap;
    }
}
