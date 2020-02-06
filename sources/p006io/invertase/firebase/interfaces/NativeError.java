package p006io.invertase.firebase.interfaces;

import com.facebook.react.bridge.WritableMap;

/* renamed from: io.invertase.firebase.interfaces.NativeError */
public interface NativeError {
    String getErrorCode();

    String getErrorMessage();

    String getFirebaseAppName();

    String getFirebaseServiceName();

    WritableMap getUserInfo();
}
