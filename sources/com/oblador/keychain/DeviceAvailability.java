package com.oblador.keychain;

import android.app.KeyguardManager;
import android.content.Context;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build.VERSION;
import p006io.sentry.marshaller.json.JsonMarshaller;

public class DeviceAvailability {
    public static boolean isFingerprintAuthAvailable(Context context) {
        if (VERSION.SDK_INT < 23) {
            return false;
        }
        FingerprintManager fingerprintManager = (FingerprintManager) context.getSystemService(JsonMarshaller.FINGERPRINT);
        if (fingerprintManager == null || !fingerprintManager.isHardwareDetected() || !fingerprintManager.hasEnrolledFingerprints()) {
            return false;
        }
        return true;
    }

    public static boolean isDeviceSecure(Context context) {
        KeyguardManager keyguardManager = (KeyguardManager) context.getSystemService("keyguard");
        return VERSION.SDK_INT >= 23 && keyguardManager != null && keyguardManager.isDeviceSecure();
    }
}
