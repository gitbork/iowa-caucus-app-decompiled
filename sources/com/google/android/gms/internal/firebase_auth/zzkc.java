package com.google.android.gms.internal.firebase_auth;

import java.util.Iterator;
import java.util.Map.Entry;

/* compiled from: com.google.firebase:firebase-auth@@18.1.0 */
final class zzkc extends zzki {
    private final /* synthetic */ zzkb zza;

    private zzkc(zzkb zzkb) {
        this.zza = zzkb;
        super(zzkb, null);
    }

    public final Iterator<Entry<K, V>> iterator() {
        return new zzkd(this.zza, null);
    }

    /* synthetic */ zzkc(zzkb zzkb, zzka zzka) {
        this(zzkb);
    }
}
