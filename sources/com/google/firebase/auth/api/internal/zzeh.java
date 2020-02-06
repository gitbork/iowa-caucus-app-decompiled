package com.google.firebase.auth.api.internal;

import android.content.Context;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.AbstractClientBuilder;
import com.google.android.gms.common.api.Api.ClientKey;

/* compiled from: com.google.firebase:firebase-auth@@18.1.0 */
public final class zzeh {
    public static final Api<zzej> zza = new Api<>("InternalFirebaseAuth.FIREBASE_AUTH_API", zzc, zzb);
    private static final ClientKey<zzdu> zzb = new ClientKey<>();
    private static final AbstractClientBuilder<zzdu, zzej> zzc = new zzeg();

    public static zzau zza(Context context, zzej zzej) {
        return new zzau(context, zzej);
    }
}
