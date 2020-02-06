package com.google.android.gms.internal.measurement;

import android.app.Activity;
import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.dynamic.ObjectWrapper;

/* compiled from: com.google.android.gms:play-services-measurement-sdk-api@@17.0.1 */
final class zzbd extends zzb {
    private final /* synthetic */ Activity zzc;
    private final /* synthetic */ Bundle zzd;
    private final /* synthetic */ zzc zze;

    zzbd(zzc zzc2, Activity activity, Bundle bundle) {
        this.zze = zzc2;
        this.zzc = activity;
        this.zzd = bundle;
        super(zzz.this);
    }

    /* access modifiers changed from: 0000 */
    public final void zza() throws RemoteException {
        zzz.this.zzr.onActivityCreated(ObjectWrapper.wrap(this.zzc), this.zzd, this.zzb);
    }
}
