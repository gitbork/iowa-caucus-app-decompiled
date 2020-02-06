package p006io.invertase.firebase.common;

import android.content.SharedPreferences;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import java.util.Map.Entry;
import p006io.invertase.firebase.app.ReactNativeFirebaseApp;

/* renamed from: io.invertase.firebase.common.ReactNativeFirebasePreferences */
public class ReactNativeFirebasePreferences {
    private static final String PREFERENCES_FILE = "io.invertase.firebase";
    private static ReactNativeFirebasePreferences sharedInstance = new ReactNativeFirebasePreferences();
    private SharedPreferences preferences;

    public static ReactNativeFirebasePreferences getSharedInstance() {
        return sharedInstance;
    }

    public boolean contains(String str) {
        return getPreferences().contains(str);
    }

    public void setBooleanValue(String str, boolean z) {
        getPreferences().edit().putBoolean(str, z).apply();
    }

    public boolean getBooleanValue(String str, boolean z) {
        return getPreferences().getBoolean(str, z);
    }

    public void setLongValue(String str, long j) {
        getPreferences().edit().putLong(str, j).apply();
    }

    public long getLongValue(String str, long j) {
        return getPreferences().getLong(str, j);
    }

    public void setStringValue(String str, String str2) {
        getPreferences().edit().putString(str, str2).apply();
    }

    public String getStringValue(String str, String str2) {
        return getPreferences().getString(str, str2);
    }

    public WritableMap getAll() {
        WritableMap createMap = Arguments.createMap();
        for (Entry entry : getPreferences().getAll().entrySet()) {
            SharedUtils.mapPutValue((String) entry.getKey(), entry.getValue(), createMap);
        }
        return createMap;
    }

    public void clearAll() {
        getPreferences().edit().clear().apply();
    }

    private SharedPreferences getPreferences() {
        if (this.preferences == null) {
            this.preferences = ReactNativeFirebaseApp.getApplicationContext().getSharedPreferences("io.invertase.firebase", 0);
        }
        return this.preferences;
    }
}
