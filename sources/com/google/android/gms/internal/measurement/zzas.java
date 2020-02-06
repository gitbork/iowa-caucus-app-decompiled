package com.google.android.gms.internal.measurement;

import android.os.Bundle;
import android.os.RemoteException;

/* compiled from: com.google.android.gms:play-services-measurement-sdk-api@@17.0.1 */
final class zzas extends zzb {
    private final /* synthetic */ String zzc;
    private final /* synthetic */ zzl zzd;
    private final /* synthetic */ zzz zze;

    zzas(zzz zzz, String str, zzl zzl) {
        this.zze = zzz;
        this.zzc = str;
        this.zzd = zzl;
        super(zzz);
    }

    /* access modifiers changed from: 0000 */
    public final void zza() throws RemoteException {
        this.zze.zzr.getMaxUserProperties(this.zzc, this.zzd);
    }

    /* access modifiers changed from: protected */
    public final void zzb() {
        this.zzd.zza((Bundle) null);
    }
}
