package com.google.android.gms.measurement.internal;

import java.util.concurrent.atomic.AtomicReference;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.0.1 */
final class zzgw implements Runnable {
    private final /* synthetic */ AtomicReference zza;
    private final /* synthetic */ boolean zzb;
    private final /* synthetic */ zzgt zzc;

    zzgw(zzgt zzgt, AtomicReference atomicReference, boolean z) {
        this.zzc = zzgt;
        this.zza = atomicReference;
        this.zzb = z;
    }

    public final void run() {
        this.zzc.zzh().zza(this.zza, this.zzb);
    }
}
