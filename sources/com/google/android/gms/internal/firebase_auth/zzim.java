package com.google.android.gms.internal.firebase_auth;

import java.util.Iterator;
import java.util.Map.Entry;

/* compiled from: com.google.firebase:firebase-auth@@18.1.0 */
final class zzim<K> implements Iterator<Entry<K, Object>> {
    private Iterator<Entry<K, Object>> zza;

    public zzim(Iterator<Entry<K, Object>> it) {
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
        return entry.getValue() instanceof zzil ? new zzin(entry) : entry;
    }
}
