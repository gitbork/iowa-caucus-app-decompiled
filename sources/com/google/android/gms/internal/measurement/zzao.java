package com.google.android.gms.internal.measurement;

import android.os.Bundle;
import android.os.RemoteException;

/* compiled from: com.google.android.gms:play-services-measurement-sdk-api@@17.0.1 */
final class zzao extends zzb {
    private final /* synthetic */ zzl zzc;
    private final /* synthetic */ zzz zzd;

    zzao(zzz zzz, zzl zzl) {
        this.zzd = zzz;
        this.zzc = zzl;
        super(zzz);
    }

    /* access modifiers changed from: 0000 */
    public final void zza() throws RemoteException {
        this.zzd.zzr.getCurrentScreenClass(this.zzc);
    }

    /* access modifiers changed from: protected */
    public final void zzb() {
        this.zzc.zza((Bundle) null);
    }
}
