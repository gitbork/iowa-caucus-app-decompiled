package com.google.android.gms.internal.measurement;

import android.os.Bundle;
import android.os.RemoteException;

/* compiled from: com.google.android.gms:play-services-measurement-sdk-api@@17.0.1 */
final class zzay extends zzb {
    private final /* synthetic */ Long zzc;
    private final /* synthetic */ String zzd;
    private final /* synthetic */ String zze;
    private final /* synthetic */ Bundle zzf;
    private final /* synthetic */ boolean zzg;
    private final /* synthetic */ boolean zzh;
    private final /* synthetic */ zzz zzi;

    zzay(zzz zzz, Long l, String str, String str2, Bundle bundle, boolean z, boolean z2) {
        this.zzi = zzz;
        this.zzc = l;
        this.zzd = str;
        this.zze = str2;
        this.zzf = bundle;
        this.zzg = z;
        this.zzh = z2;
        super(zzz);
    }

    /* access modifiers changed from: 0000 */
    public final void zza() throws RemoteException {
        Long l = this.zzc;
        this.zzi.zzr.logEvent(this.zzd, this.zze, this.zzf, this.zzg, this.zzh, l == null ? this.zza : l.longValue());
    }
}
