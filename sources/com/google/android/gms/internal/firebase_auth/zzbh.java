package com.google.android.gms.internal.firebase_auth;

import java.util.Iterator;
import java.util.Map.Entry;

/* compiled from: com.google.firebase:firebase-auth@@18.1.0 */
final class zzbh<K, V> extends zzbg<Entry<K, V>> {
    private final transient zzbe<K, V> zza;
    /* access modifiers changed from: private */
    public final transient Object[] zzb;
    private final transient int zzc = 0;
    /* access modifiers changed from: private */
    public final transient int zzd;

    zzbh(zzbe<K, V> zzbe, Object[] objArr, int i, int i2) {
        this.zza = zzbe;
        this.zzb = objArr;
        this.zzd = i2;
    }

    public final zzbo<Entry<K, V>> zzb() {
        return (zzbo) zzc().iterator();
    }

    /* access modifiers changed from: 0000 */
    public final int zza(Object[] objArr, int i) {
        return zzc().zza(objArr, i);
    }

    /* access modifiers changed from: 0000 */
    public final zzaz<Entry<K, V>> zza() {
        return new zzbk(this);
    }

    public final boolean contains(Object obj) {
        if (obj instanceof Entry) {
            Entry entry = (Entry) obj;
            Object key = entry.getKey();
            Object value = entry.getValue();
            if (value != null && value.equals(this.zza.get(key))) {
                return true;
            }
        }
        return false;
    }

    public final int size() {
        return this.zzd;
    }

    public final /* synthetic */ Iterator iterator() {
        return iterator();
    }
}
