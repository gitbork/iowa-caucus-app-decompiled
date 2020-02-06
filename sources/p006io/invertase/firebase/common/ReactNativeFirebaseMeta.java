package p006io.invertase.firebase.common;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import p006io.invertase.firebase.app.ReactNativeFirebaseApp;

/* renamed from: io.invertase.firebase.common.ReactNativeFirebaseMeta */
public class ReactNativeFirebaseMeta {
    private static final String META_PREFIX = "rnfirebase_";
    private static final String TAG = "RNFBMetaProvider";
    private static ReactNativeFirebaseMeta sharedInstance = new ReactNativeFirebaseMeta();

    public static ReactNativeFirebaseMeta getSharedInstance() {
        return sharedInstance;
    }

    private Bundle getMetaData() {
        Bundle bundle = null;
        try {
            Context applicationContext = ReactNativeFirebaseApp.getApplicationContext();
            PackageManager packageManager = applicationContext.getPackageManager();
            if (packageManager == null) {
                return null;
            }
            ApplicationInfo applicationInfo = packageManager.getApplicationInfo(applicationContext.getPackageName(), 128);
            if (applicationInfo != null) {
                bundle = applicationInfo.metaData;
            }
            return bundle;
        } catch (NameNotFoundException unused) {
        }
    }

    public boolean contains(String str) {
        Bundle metaData = getMetaData();
        if (metaData == null) {
            return false;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(META_PREFIX);
        sb.append(str);
        return metaData.containsKey(sb.toString());
    }

    public boolean getBooleanValue(String str, boolean z) {
        Bundle metaData = getMetaData();
        if (metaData == null) {
            return z;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(META_PREFIX);
        sb.append(str);
        return metaData.getBoolean(sb.toString(), z);
    }

    public String getStringValue(String str, String str2) {
        Bundle metaData = getMetaData();
        if (metaData == null) {
            return str2;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(META_PREFIX);
        sb.append(str);
        return metaData.getString(sb.toString(), str2);
    }

    public WritableMap getAll() {
        Bundle metaData = getMetaData();
        WritableMap createMap = Arguments.createMap();
        if (metaData == null) {
            return createMap;
        }
        for (String str : metaData.keySet()) {
            if (str.startsWith(META_PREFIX)) {
                Object obj = metaData.get(str);
                if (obj == null) {
                    createMap.putNull(str);
                } else if (obj instanceof String) {
                    createMap.putString(str, (String) obj);
                } else if (obj instanceof Boolean) {
                    createMap.putBoolean(str, ((Boolean) obj).booleanValue());
                }
            }
        }
        return createMap;
    }
}
