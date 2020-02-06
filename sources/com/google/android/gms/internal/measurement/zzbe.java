package com.google.android.gms.internal.measurement;

import android.app.Activity;
import android.os.RemoteException;
import com.google.android.gms.dynamic.ObjectWrapper;

/* compiled from: com.google.android.gms:play-services-measurement-sdk-api@@17.0.1 */
final class zzbe extends zzb {
    private final /* synthetic */ Activity zzc;
    private final /* synthetic */ zzc zzd;

    zzbe(zzc zzc2, Activity activity) {
        this.zzd = zzc2;
        this.zzc = activity;
        super(zzz.this);
    }

    /* access modifiers changed from: 0000 */
    public final void zza() throws RemoteException {
        zzz.this.zzr.onActivityPaused(ObjectWrapper.wrap(this.zzc), this.zzb);
    }
}
