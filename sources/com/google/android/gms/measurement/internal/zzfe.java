package com.google.android.gms.measurement.internal;

import android.content.BroadcastReceiver.PendingResult;
import android.content.Context;
import android.content.Intent;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.0.1 */
public interface zzfe {
    PendingResult doGoAsync();

    void doStartService(Context context, Intent intent);
}
