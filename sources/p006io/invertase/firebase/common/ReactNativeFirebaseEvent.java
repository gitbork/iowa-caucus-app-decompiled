package p006io.invertase.firebase.common;

import com.facebook.react.bridge.WritableMap;
import p006io.invertase.firebase.interfaces.NativeEvent;

/* renamed from: io.invertase.firebase.common.ReactNativeFirebaseEvent */
public class ReactNativeFirebaseEvent implements NativeEvent {
    private WritableMap eventBody;
    private String eventName;
    private String firebaseAppName;

    public ReactNativeFirebaseEvent(String str, WritableMap writableMap) {
        this.eventName = str;
        this.eventBody = writableMap;
    }

    public ReactNativeFirebaseEvent(String str, WritableMap writableMap, String str2) {
        this.eventName = str;
        this.eventBody = writableMap;
        this.firebaseAppName = str2;
    }

    public String getEventName() {
        return this.eventName;
    }

    public WritableMap getEventBody() {
        return this.eventBody;
    }

    public String getFirebaseAppName() {
        return this.firebaseAppName;
    }
}
