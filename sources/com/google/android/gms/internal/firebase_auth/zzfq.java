package com.google.android.gms.internal.firebase_auth;

import androidx.annotation.NonNull;
import com.google.android.gms.common.util.Strings;
import com.google.android.gms.internal.firebase_auth.zzp.C2472zzp;
import com.google.firebase.auth.api.internal.zzea;

/* compiled from: com.google.firebase:firebase-auth@@18.1.0 */
public final class zzfq implements zzea<zzfq, C2472zzp> {
    private String zza;
    private String zzb;
    private String zzc;
    private String zzd;
    private long zze;

    public final String zzb() {
        return this.zza;
    }

    @NonNull
    public final String zzc() {
        return this.zzd;
    }

    public final long zzd() {
        return this.zze;
    }

    public final zzjq<C2472zzp> zza() {
        return C2472zzp.zzf();
    }

    public final /* synthetic */ zzea zza(zzjg zzjg) {
        if (zzjg instanceof C2472zzp) {
            C2472zzp zzp = (C2472zzp) zzjg;
            this.zza = Strings.emptyToNull(zzp.zza());
            this.zzb = Strings.emptyToNull(zzp.zzb());
            this.zzc = Strings.emptyToNull(zzp.zzc());
            this.zzd = Strings.emptyToNull(zzp.zzd());
            this.zze = zzp.zze();
            return this;
        }
        throw new IllegalArgumentException("The passed proto must be an instance of SignUpNewUserResponse.");
    }
}
