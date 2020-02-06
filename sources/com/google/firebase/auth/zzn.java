package com.google.firebase.auth;

import com.google.firebase.auth.FirebaseAuth.AuthStateListener;

/* compiled from: com.google.firebase:firebase-auth@@18.1.0 */
final class zzn implements Runnable {
    private final /* synthetic */ AuthStateListener zza;
    private final /* synthetic */ FirebaseAuth zzb;

    zzn(FirebaseAuth firebaseAuth, AuthStateListener authStateListener) {
        this.zzb = firebaseAuth;
        this.zza = authStateListener;
    }

    public final void run() {
        this.zza.onAuthStateChanged(this.zzb);
    }
}
