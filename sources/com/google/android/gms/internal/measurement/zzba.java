package com.google.android.gms.internal.measurement;

import android.os.Bundle;
import android.os.RemoteException;

/* compiled from: com.google.android.gms:play-services-measurement-sdk-api@@17.0.1 */
final class zzba extends zzb {
    private final /* synthetic */ Bundle zzc;
    private final /* synthetic */ zzz zzd;

    zzba(zzz zzz, Bundle bundle) {
        this.zzd = zzz;
        this.zzc = bundle;
        super(zzz);
    }

    /* access modifiers changed from: 0000 */
    public final void zza() throws RemoteException {
        this.zzd.zzr.setConditionalUserProperty(this.zzc, this.zza);
    }
}
