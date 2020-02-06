package com.google.android.gms.internal.firebase_auth;

import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.firebase_auth.zzp.zza;
import com.google.android.gms.internal.firebase_auth.zzp.zza.C2471zza;
import com.google.firebase.auth.api.internal.zzfk;

/* compiled from: com.google.firebase:firebase-auth@@18.1.0 */
public final class zzeg implements zzfk<zza> {
    private String zza;
    private String zzb = "http://localhost";
    @Nullable
    private final String zzc;

    public zzeg(String str, @Nullable String str2) {
        this.zza = Preconditions.checkNotEmpty(str);
        this.zzc = str2;
    }

    public final /* synthetic */ zzjg zza() {
        C2471zza zzb2 = zza.zza().zza(this.zza).zzb(this.zzb);
        String str = this.zzc;
        if (str != null) {
            zzb2.zzc(str);
        }
        return (zza) ((zzhx) zzb2.zzf());
    }
}
