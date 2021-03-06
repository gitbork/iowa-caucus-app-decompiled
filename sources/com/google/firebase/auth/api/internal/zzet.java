package com.google.firebase.auth.api.internal;

import com.google.firebase.auth.PhoneAuthProvider.ForceResendingToken;
import com.google.firebase.auth.PhoneAuthProvider.OnVerificationStateChangedCallbacks;

/* compiled from: com.google.firebase:firebase-auth@@18.1.0 */
final class zzet implements zzfa {
    private final /* synthetic */ String zza;

    zzet(zzeu zzeu, String str) {
        this.zza = str;
    }

    public final void zza(OnVerificationStateChangedCallbacks onVerificationStateChangedCallbacks, Object... objArr) {
        onVerificationStateChangedCallbacks.onCodeSent(this.zza, ForceResendingToken.zza());
    }
}
