package com.google.android.gms.internal.firebase_auth;

import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Map.Entry;

/* compiled from: com.google.firebase:firebase-auth@@18.1.0 */
class zzki extends AbstractSet<Entry<K, V>> {
    private final /* synthetic */ zzkb zza;

    private zzki(zzkb zzkb) {
        this.zza = zzkb;
    }

    public Iterator<Entry<K, V>> iterator() {
        return new zzkj(this.zza, null);
    }

    public int size() {
        return this.zza.size();
    }

    public boolean contains(Object obj) {
        Entry entry = (Entry) obj;
        Object obj2 = this.zza.get(entry.getKey());
        Object value = entry.getValue();
        return obj2 == value || (obj2 != null && obj2.equals(value));
    }

    public boolean remove(Object obj) {
        Entry entry = (Entry) obj;
        if (!contains(entry)) {
            return false;
        }
        this.zza.remove(entry.getKey());
        return true;
    }

    public void clear() {
        this.zza.clear();
    }

    public /* synthetic */ boolean add(Object obj) {
        Entry entry = (Entry) obj;
        if (contains(entry)) {
            return false;
        }
        this.zza.put((Comparable) entry.getKey(), entry.getValue());
        return true;
    }

    /* synthetic */ zzki(zzkb zzkb, zzka zzka) {
        this(zzkb);
    }
}
