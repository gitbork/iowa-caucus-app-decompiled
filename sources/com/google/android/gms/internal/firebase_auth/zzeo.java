package com.google.android.gms.internal.firebase_auth;

import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.firebase_auth.zzlo.zza;
import com.google.firebase.auth.api.internal.zzfk;

/* compiled from: com.google.firebase:firebase-auth@@18.1.0 */
public final class zzeo implements zzfk<zza> {
    private String zza;
    private String zzb;
    @Nullable
    private final String zzc;

    public zzeo(String str) {
        this(str, null);
    }

    private zzeo(String str, @Nullable String str2) {
        this.zza = zzel.REFRESH_TOKEN.toString();
        this.zzb = Preconditions.checkNotEmpty(str);
        this.zzc = null;
    }

    public final /* synthetic */ zzjg zza() {
        return (zza) ((zzhx) zza.zza().zza(this.zza).zzb(this.zzb).zzf());
    }
}
