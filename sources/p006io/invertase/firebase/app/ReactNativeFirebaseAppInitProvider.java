package p006io.invertase.firebase.app;

import p006io.invertase.firebase.common.ReactNativeFirebaseInitProvider;

/* renamed from: io.invertase.firebase.app.ReactNativeFirebaseAppInitProvider */
public class ReactNativeFirebaseAppInitProvider extends ReactNativeFirebaseInitProvider {
    private static final String EMPTY_APPLICATION_ID_PROVIDER_AUTHORITY = "io.invertase.firebase.reactnativefirebaseappinitprovider";

    public String getEmptyProviderAuthority() {
        return EMPTY_APPLICATION_ID_PROVIDER_AUTHORITY;
    }
}
