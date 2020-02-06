package com.google.android.gms.internal.firebase_auth;

import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.firebase_auth.zzp.zzs;
import com.google.android.gms.internal.firebase_auth.zzp.zzs.zza;
import com.google.firebase.auth.api.internal.zzfk;

/* compiled from: com.google.firebase:firebase-auth@@18.1.0 */
public final class zzfw implements zzfk<zzs> {
    private String zza;
    @Nullable
    private String zzb;

    public zzfw(String str, @Nullable String str2) {
        this.zza = Preconditions.checkNotEmpty(str);
        this.zzb = str2;
    }

    public final /* synthetic */ zzjg zza() {
        zza zza2 = zzs.zza().zza(this.zza).zza(true);
        String str = this.zzb;
        if (str != null) {
            zza2.zzb(str);
        }
        return (zzs) ((zzhx) zza2.zzf());
    }
}
