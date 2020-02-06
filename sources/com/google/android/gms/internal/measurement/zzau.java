package com.google.android.gms.internal.measurement;

import android.os.RemoteException;
import android.util.Log;
import android.util.Pair;
import com.google.android.gms.measurement.internal.zzgr;

/* compiled from: com.google.android.gms:play-services-measurement-sdk-api@@17.0.1 */
final class zzau extends zzb {
    private final /* synthetic */ zzgr zzc;
    private final /* synthetic */ zzz zzd;

    zzau(zzz zzz, zzgr zzgr) {
        this.zzd = zzz;
        this.zzc = zzgr;
        super(zzz);
    }

    /* access modifiers changed from: 0000 */
    public final void zza() throws RemoteException {
        for (int i = 0; i < this.zzd.zzf.size(); i++) {
            if (this.zzc.equals(((Pair) this.zzd.zzf.get(i)).first)) {
                Log.w(this.zzd.zzc, "OnEventListener already registered.");
                return;
            }
        }
        zzd zzd2 = new zzd(this.zzc);
        this.zzd.zzf.add(new Pair(this.zzc, zzd2));
        this.zzd.zzr.registerOnMeasurementEventListener(zzd2);
    }
}
