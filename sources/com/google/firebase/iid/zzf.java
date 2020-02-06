package com.google.firebase.iid;

import android.util.Log;

final class zzf implements Runnable {
    private final /* synthetic */ zze zzac;
    private final /* synthetic */ zzg zzad;

    zzf(zzg zzg, zze zze) {
        this.zzad = zzg;
        this.zzac = zze;
    }

    public final void run() {
        String str = "EnhancedIntentService";
        if (Log.isLoggable(str, 3)) {
            Log.d(str, "bg processing of the intent starting now");
        }
        this.zzad.zzae.zzd(this.zzac.intent);
        this.zzac.finish();
    }
}
