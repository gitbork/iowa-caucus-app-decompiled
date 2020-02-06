package com.google.android.gms.internal.measurement;

import android.app.Activity;
import android.os.RemoteException;
import com.google.android.gms.dynamic.ObjectWrapper;

/* compiled from: com.google.android.gms:play-services-measurement-sdk-api@@17.0.1 */
final class zzbg extends zzb {
    private final /* synthetic */ Activity zzc;
    private final /* synthetic */ zzl zzd;
    private final /* synthetic */ zzc zze;

    zzbg(zzc zzc2, Activity activity, zzl zzl) {
        this.zze = zzc2;
        this.zzc = activity;
        this.zzd = zzl;
        super(zzz.this);
    }

    /* access modifiers changed from: 0000 */
    public final void zza() throws RemoteException {
        zzz.this.zzr.onActivitySaveInstanceState(ObjectWrapper.wrap(this.zzc), this.zzd, this.zzb);
    }
}
