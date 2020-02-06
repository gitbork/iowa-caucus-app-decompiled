package com.google.android.gms.internal.measurement;

import android.os.RemoteException;
import com.google.android.gms.measurement.internal.zzgo;

/* compiled from: com.google.android.gms:play-services-measurement-sdk-api@@17.0.1 */
final class zzal extends zzb {
    private final /* synthetic */ zzgo zzc;
    private final /* synthetic */ zzz zzd;

    zzal(zzz zzz, zzgo zzgo) {
        this.zzd = zzz;
        this.zzc = zzgo;
        super(zzz);
    }

    /* access modifiers changed from: 0000 */
    public final void zza() throws RemoteException {
        this.zzd.zzr.setEventInterceptor(new zza(this.zzc));
    }
}
