package com.google.firebase.auth.api.internal;

import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.tasks.TaskCompletionSource;

/* compiled from: com.google.firebase:firebase-auth@@18.1.0 */
final /* synthetic */ class zzcb implements RemoteCall {
    private final zzcc zza;

    zzcb(zzcc zzcc) {
        this.zza = zzcc;
    }

    public final void accept(Object obj, Object obj2) {
        this.zza.zza((zzdu) obj, (TaskCompletionSource) obj2);
    }
}
