package com.google.android.gms.internal.firebase_auth;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* compiled from: com.google.firebase:firebase-auth@@18.1.0 */
public abstract class zzbe<K, V> implements Serializable, Map<K, V> {
    private static final Entry<?, ?>[] zza = new Entry[0];
    private transient zzbg<Entry<K, V>> zzb;
    private transient zzbg<K> zzc;
    private transient zzba<V> zzd;

    zzbe() {
    }

    public abstract V get(@NullableDecl Object obj);

    /* access modifiers changed from: 0000 */
    public abstract zzbg<Entry<K, V>> zza();

    /* access modifiers changed from: 0000 */
    public abstract zzbg<K> zzb();

    /* access modifiers changed from: 0000 */
    public abstract zzba<V> zzc();

    @Deprecated
    public final V put(K k, V v) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public final V remove(Object obj) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public final void putAll(Map<? extends K, ? extends V> map) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public final void clear() {
        throw new UnsupportedOperationException();
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public boolean containsKey(@NullableDecl Object obj) {
        return get(obj) != null;
    }

    public boolean containsValue(@NullableDecl Object obj) {
        return ((zzba) values()).contains(obj);
    }

    public final V getOrDefault(@NullableDecl Object obj, @NullableDecl V v) {
        V v2 = get(obj);
        return v2 != null ? v2 : v;
    }

    public boolean equals(@NullableDecl Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Map)) {
            return false;
        }
        return entrySet().equals(((Map) obj).entrySet());
    }

    public int hashCode() {
        return zzbl.zza((zzbg) entrySet());
    }

    public String toString() {
        int size = size();
        if (size >= 0) {
            StringBuilder sb = new StringBuilder((int) Math.min(((long) size) << 3, 1073741824));
            sb.append('{');
            boolean z = true;
            for (Entry entry : entrySet()) {
                if (!z) {
                    sb.append(", ");
                }
                z = false;
                sb.append(entry.getKey());
                sb.append('=');
                sb.append(entry.getValue());
            }
            sb.append('}');
            return sb.toString();
        }
        String str = "size";
        StringBuilder sb2 = new StringBuilder(str.length() + 40);
        sb2.append(str);
        sb2.append(" cannot be negative but was: ");
        sb2.append(size);
        throw new IllegalArgumentException(sb2.toString());
    }

    public /* synthetic */ Set entrySet() {
        zzbg<Entry<K, V>> zzbg = this.zzb;
        if (zzbg != null) {
            return zzbg;
        }
        zzbg<Entry<K, V>> zza2 = zza();
        this.zzb = zza2;
        return zza2;
    }

    public /* synthetic */ Collection values() {
        zzba<V> zzba = this.zzd;
        if (zzba != null) {
            return zzba;
        }
        zzba<V> zzc2 = zzc();
        this.zzd = zzc2;
        return zzc2;
    }

    public /* synthetic */ Set keySet() {
        zzbg<K> zzbg = this.zzc;
        if (zzbg != null) {
            return zzbg;
        }
        zzbg<K> zzb2 = zzb();
        this.zzc = zzb2;
        return zzb2;
    }
}
