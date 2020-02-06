package p006io.invertase.firebase.interfaces;

import com.facebook.react.bridge.WritableMap;

/* renamed from: io.invertase.firebase.interfaces.NativeEvent */
public interface NativeEvent {
    WritableMap getEventBody();

    String getEventName();

    String getFirebaseAppName();
}
