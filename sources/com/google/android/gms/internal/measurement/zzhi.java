package com.google.android.gms.internal.measurement;

import java.util.Iterator;
import java.util.Map.Entry;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.0.1 */
final class zzhi extends zzho {
    private final /* synthetic */ zzhh zza;

    private zzhi(zzhh zzhh) {
        this.zza = zzhh;
        super(zzhh, null);
    }

    public final Iterator<Entry<K, V>> iterator() {
        return new zzhj(this.zza, null);
    }

    /* synthetic */ zzhi(zzhh zzhh, zzhg zzhg) {
        this(zzhh);
    }
}
