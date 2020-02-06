package com.google.android.gms.internal.firebase_auth;

import com.google.android.gms.internal.firebase_auth.zzp.zzl;
import com.google.firebase.auth.api.internal.zzea;

/* compiled from: com.google.firebase:firebase-auth@@18.1.0 */
public final class zzfm implements zzea<zzfm, zzl> {
    private String zza;

    public final String zzb() {
        return this.zza;
    }

    public final zzjq<zzl> zza() {
        return zzl.zzb();
    }

    public final /* synthetic */ zzea zza(zzjg zzjg) {
        if (zzjg instanceof zzl) {
            this.zza = ((zzl) zzjg).zza();
            return this;
        }
        throw new IllegalArgumentException("The passed proto must be an instance of SendVerificationCodeResponse.");
    }
}
