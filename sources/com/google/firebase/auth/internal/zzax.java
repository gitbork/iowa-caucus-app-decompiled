package com.google.firebase.auth.internal;

import com.google.android.gms.common.api.internal.BackgroundDetector.BackgroundStateChangeListener;

/* compiled from: com.google.firebase:firebase-auth@@18.1.0 */
final class zzax implements BackgroundStateChangeListener {
    private final /* synthetic */ zzau zza;

    zzax(zzau zzau) {
        this.zza = zzau;
    }

    public final void onBackgroundStateChanged(boolean z) {
        if (z) {
            this.zza.zzc = true;
            this.zza.zza();
            return;
        }
        this.zza.zzc = false;
        if (this.zza.zzb()) {
            this.zza.zzb.zza();
        }
    }
}
