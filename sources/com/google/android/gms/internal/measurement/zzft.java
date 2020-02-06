package com.google.android.gms.internal.measurement;

import java.util.Iterator;
import java.util.Map.Entry;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.0.1 */
final class zzft<K> implements Iterator<Entry<K, Object>> {
    private Iterator<Entry<K, Object>> zza;

    public zzft(Iterator<Entry<K, Object>> it) {
        this.zza = it;
    }

    public final boolean hasNext() {
        return this.zza.hasNext();
    }

    public final void remove() {
        this.zza.remove();
    }

    public final /* synthetic */ Object next() {
        Entry entry = (Entry) this.zza.next();
        return entry.getValue() instanceof zzfo ? new zzfq(entry) : entry;
    }
}
