package com.google.android.gms.internal.firebase_auth;

import com.google.android.gms.internal.firebase_auth.zzhx.zzd;
import java.io.IOException;
import java.util.Map.Entry;

/* compiled from: com.google.firebase:firebase-auth@@18.1.0 */
final class zzho extends zzhm<Object> {
    zzho() {
    }

    /* access modifiers changed from: 0000 */
    public final boolean zza(zzjg zzjg) {
        return zzjg instanceof zzd;
    }

    /* access modifiers changed from: 0000 */
    public final zzhq<Object> zza(Object obj) {
        return ((zzd) obj).zzc;
    }

    /* access modifiers changed from: 0000 */
    public final zzhq<Object> zzb(Object obj) {
        zzd zzd = (zzd) obj;
        if (zzd.zzc.zzc()) {
            zzd.zzc = (zzhq) zzd.zzc.clone();
        }
        return zzd.zzc;
    }

    /* access modifiers changed from: 0000 */
    public final void zzc(Object obj) {
        zza(obj).zzb();
    }

    /* access modifiers changed from: 0000 */
    public final <UT, UB> UB zza(zzjx zzjx, Object obj, zzhk zzhk, zzhq<Object> zzhq, UB ub, zzks<UT, UB> zzks) throws IOException {
        throw new NoSuchMethodError();
    }

    /* access modifiers changed from: 0000 */
    public final int zza(Entry<?, ?> entry) {
        entry.getKey();
        throw new NoSuchMethodError();
    }

    /* access modifiers changed from: 0000 */
    public final void zza(zzll zzll, Entry<?, ?> entry) throws IOException {
        entry.getKey();
        throw new NoSuchMethodError();
    }

    /* access modifiers changed from: 0000 */
    public final Object zza(zzhk zzhk, zzjg zzjg, int i) {
        return zzhk.zza(zzjg, i);
    }

    /* access modifiers changed from: 0000 */
    public final void zza(zzjx zzjx, Object obj, zzhk zzhk, zzhq<Object> zzhq) throws IOException {
        throw new NoSuchMethodError();
    }

    /* access modifiers changed from: 0000 */
    public final void zza(zzgm zzgm, Object obj, zzhk zzhk, zzhq<Object> zzhq) throws IOException {
        throw new NoSuchMethodError();
    }
}
