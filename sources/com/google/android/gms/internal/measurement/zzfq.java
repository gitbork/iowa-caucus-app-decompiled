package com.google.android.gms.internal.measurement;

import java.util.Map.Entry;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.0.1 */
final class zzfq<K> implements Entry<K, Object> {
    private Entry<K, zzfo> zza;

    private zzfq(Entry<K, zzfo> entry) {
        this.zza = entry;
    }

    public final K getKey() {
        return this.zza.getKey();
    }

    public final Object getValue() {
        if (((zzfo) this.zza.getValue()) == null) {
            return null;
        }
        return zzfo.zza();
    }

    public final zzfo zza() {
        return (zzfo) this.zza.getValue();
    }

    public final Object setValue(Object obj) {
        if (obj instanceof zzgn) {
            return ((zzfo) this.zza.getValue()).zza((zzgn) obj);
        }
        throw new IllegalArgumentException("LazyField now only used for MessageSet, and the value of MessageSet must be an instance of MessageLite");
    }
}
