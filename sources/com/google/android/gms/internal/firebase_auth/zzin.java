package com.google.android.gms.internal.firebase_auth;

import java.util.Map.Entry;

/* compiled from: com.google.firebase:firebase-auth@@18.1.0 */
final class zzin<K> implements Entry<K, Object> {
    private Entry<K, zzil> zza;

    private zzin(Entry<K, zzil> entry) {
        this.zza = entry;
    }

    public final K getKey() {
        return this.zza.getKey();
    }

    public final Object getValue() {
        if (((zzil) this.zza.getValue()) == null) {
            return null;
        }
        return zzil.zza();
    }

    public final zzil zza() {
        return (zzil) this.zza.getValue();
    }

    public final Object setValue(Object obj) {
        if (obj instanceof zzjg) {
            return ((zzil) this.zza.getValue()).zza((zzjg) obj);
        }
        throw new IllegalArgumentException("LazyField now only used for MessageSet, and the value of MessageSet must be an instance of MessageLite");
    }
}
