package com.google.android.gms.internal.measurement;

import android.os.RemoteException;
import com.google.android.gms.dynamic.ObjectWrapper;

/* compiled from: com.google.android.gms:play-services-measurement-sdk-api@@17.0.1 */
final class zzbb extends zzb {
    private final /* synthetic */ String zzc;
    private final /* synthetic */ String zzd;
    private final /* synthetic */ Object zze;
    private final /* synthetic */ boolean zzf;
    private final /* synthetic */ zzz zzg;

    zzbb(zzz zzz, String str, String str2, Object obj, boolean z) {
        this.zzg = zzz;
        this.zzc = str;
        this.zzd = str2;
        this.zze = obj;
        this.zzf = z;
        super(zzz);
    }

    /* access modifiers changed from: 0000 */
    public final void zza() throws RemoteException {
        this.zzg.zzr.setUserProperty(this.zzc, this.zzd, ObjectWrapper.wrap(this.zze), this.zzf, this.zza);
    }
}
