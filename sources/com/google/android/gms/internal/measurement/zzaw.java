package com.google.android.gms.internal.measurement;

import android.os.RemoteException;

/* compiled from: com.google.android.gms:play-services-measurement-sdk-api@@17.0.1 */
final class zzaw extends zzb {
    private final /* synthetic */ boolean zzc;
    private final /* synthetic */ zzz zzd;

    zzaw(zzz zzz, boolean z) {
        this.zzd = zzz;
        this.zzc = z;
        super(zzz);
    }

    /* access modifiers changed from: 0000 */
    public final void zza() throws RemoteException {
        this.zzd.zzr.setDataCollectionEnabled(this.zzc);
    }
}
