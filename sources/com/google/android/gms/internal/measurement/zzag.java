package com.google.android.gms.internal.measurement;

import android.os.RemoteException;

/* compiled from: com.google.android.gms:play-services-measurement-sdk-api@@17.0.1 */
final class zzag extends zzb {
    private final /* synthetic */ long zzc;
    private final /* synthetic */ zzz zzd;

    zzag(zzz zzz, long j) {
        this.zzd = zzz;
        this.zzc = j;
        super(zzz);
    }

    /* access modifiers changed from: 0000 */
    public final void zza() throws RemoteException {
        this.zzd.zzr.setSessionTimeoutDuration(this.zzc);
    }
}
