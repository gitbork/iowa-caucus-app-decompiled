package com.google.android.gms.internal.measurement;

import android.os.Bundle;
import android.os.RemoteException;

/* compiled from: com.google.android.gms:play-services-measurement-sdk-api@@17.0.1 */
final class zzat extends zzb {
    private final /* synthetic */ Bundle zzc;
    private final /* synthetic */ zzl zzd;
    private final /* synthetic */ zzz zze;

    zzat(zzz zzz, Bundle bundle, zzl zzl) {
        this.zze = zzz;
        this.zzc = bundle;
        this.zzd = zzl;
        super(zzz);
    }

    /* access modifiers changed from: 0000 */
    public final void zza() throws RemoteException {
        this.zze.zzr.performAction(this.zzc, this.zzd, this.zza);
    }

    /* access modifiers changed from: protected */
    public final void zzb() {
        this.zzd.zza((Bundle) null);
    }
}
