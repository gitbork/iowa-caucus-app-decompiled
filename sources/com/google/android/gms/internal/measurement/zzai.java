package com.google.android.gms.internal.measurement;

import android.os.RemoteException;

/* compiled from: com.google.android.gms:play-services-measurement-sdk-api@@17.0.1 */
final class zzai extends zzb {
    private final /* synthetic */ String zzc;
    private final /* synthetic */ zzz zzd;

    zzai(zzz zzz, String str) {
        this.zzd = zzz;
        this.zzc = str;
        super(zzz);
    }

    /* access modifiers changed from: 0000 */
    public final void zza() throws RemoteException {
        this.zzd.zzr.endAdUnitExposure(this.zzc, this.zzb);
    }
}
