package com.google.android.gms.internal.measurement;

import android.os.Bundle;
import android.os.RemoteException;

/* compiled from: com.google.android.gms:play-services-measurement-sdk-api@@17.0.1 */
final class zzax extends zzb {
    private final /* synthetic */ zzl zzc;
    private final /* synthetic */ int zzd;
    private final /* synthetic */ zzz zze;

    zzax(zzz zzz, zzl zzl, int i) {
        this.zze = zzz;
        this.zzc = zzl;
        this.zzd = i;
        super(zzz);
    }

    /* access modifiers changed from: 0000 */
    public final void zza() throws RemoteException {
        this.zze.zzr.getTestFlag(this.zzc, this.zzd);
    }

    /* access modifiers changed from: protected */
    public final void zzb() {
        this.zzc.zza((Bundle) null);
    }
}
