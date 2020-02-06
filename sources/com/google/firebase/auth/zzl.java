package com.google.firebase.auth;

import com.google.firebase.auth.FirebaseAuth.IdTokenListener;

/* compiled from: com.google.firebase:firebase-auth@@18.1.0 */
final class zzl implements Runnable {
    private final /* synthetic */ IdTokenListener zza;
    private final /* synthetic */ FirebaseAuth zzb;

    zzl(FirebaseAuth firebaseAuth, IdTokenListener idTokenListener) {
        this.zzb = firebaseAuth;
        this.zza = idTokenListener;
    }

    public final void run() {
        this.zza.onIdTokenChanged(this.zzb);
    }
}
