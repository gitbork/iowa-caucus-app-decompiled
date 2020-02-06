package com.google.android.gms.internal.measurement;

import android.os.RemoteException;
import com.google.android.gms.dynamic.ObjectWrapper;

/* compiled from: com.google.android.gms:play-services-measurement-sdk-api@@17.0.1 */
final class zzaq extends zzb {
    private final /* synthetic */ int zzc = 5;
    private final /* synthetic */ String zzd;
    private final /* synthetic */ Object zze;
    private final /* synthetic */ Object zzf;
    private final /* synthetic */ Object zzg;
    private final /* synthetic */ zzz zzh;

    zzaq(zzz zzz, boolean z, int i, String str, Object obj, Object obj2, Object obj3) {
        this.zzh = zzz;
        this.zzd = str;
        this.zze = obj;
        this.zzf = null;
        this.zzg = null;
        super(false);
    }

    /* access modifiers changed from: 0000 */
    public final void zza() throws RemoteException {
        this.zzh.zzr.logHealthData(this.zzc, this.zzd, ObjectWrapper.wrap(this.zze), ObjectWrapper.wrap(this.zzf), ObjectWrapper.wrap(this.zzg));
    }
}
