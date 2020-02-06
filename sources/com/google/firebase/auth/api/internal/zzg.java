package com.google.firebase.auth.api.internal;

import androidx.annotation.Nullable;
import com.google.android.gms.internal.firebase_auth.zzem;
import com.google.android.gms.internal.firebase_auth.zzey;
import com.google.firebase.auth.internal.zzy;

/* compiled from: com.google.firebase:firebase-auth@@18.1.0 */
final class zzg implements zzfe<zzem> {
    private final /* synthetic */ zzdr zza;
    private final /* synthetic */ zza zzb;

    zzg(zza zza2, zzdr zzdr) {
        this.zzb = zza2;
        this.zza = zzdr;
    }

    public final void zza(@Nullable String str) {
        this.zza.zza(zzy.zza(str));
    }

    public final /* synthetic */ void zza(Object obj) {
        zzem zzem = (zzem) obj;
        this.zzb.zza(new zzey(zzem.zzc(), zzem.zzb(), Long.valueOf(zzem.zze()), "Bearer"), null, null, Boolean.valueOf(zzem.zzd()), null, this.zza, this);
    }
}
