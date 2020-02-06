package com.google.android.gms.internal.measurement;

import java.io.IOException;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.0.1 */
abstract class zzhu<T, B> {
    zzhu() {
    }

    /* access modifiers changed from: 0000 */
    public abstract B zza();

    /* access modifiers changed from: 0000 */
    public abstract T zza(B b);

    /* access modifiers changed from: 0000 */
    public abstract void zza(B b, int i, int i2);

    /* access modifiers changed from: 0000 */
    public abstract void zza(B b, int i, long j);

    /* access modifiers changed from: 0000 */
    public abstract void zza(B b, int i, zzdv zzdv);

    /* access modifiers changed from: 0000 */
    public abstract void zza(B b, int i, T t);

    /* access modifiers changed from: 0000 */
    public abstract void zza(T t, zzir zzir) throws IOException;

    /* access modifiers changed from: 0000 */
    public abstract void zza(Object obj, T t);

    /* access modifiers changed from: 0000 */
    public abstract boolean zza(zzhd zzhd);

    /* access modifiers changed from: 0000 */
    public abstract T zzb(Object obj);

    /* access modifiers changed from: 0000 */
    public abstract void zzb(B b, int i, long j);

    /* access modifiers changed from: 0000 */
    public abstract void zzb(T t, zzir zzir) throws IOException;

    /* access modifiers changed from: 0000 */
    public abstract void zzb(Object obj, B b);

    /* access modifiers changed from: 0000 */
    public abstract B zzc(Object obj);

    /* access modifiers changed from: 0000 */
    public abstract T zzc(T t, T t2);

    /* access modifiers changed from: 0000 */
    public abstract void zzd(Object obj);

    /* access modifiers changed from: 0000 */
    public abstract int zze(T t);

    /* access modifiers changed from: 0000 */
    public abstract int zzf(T t);

    /* access modifiers changed from: 0000 */
    public final boolean zza(B b, zzhd zzhd) throws IOException {
        int zzb = zzhd.zzb();
        int i = zzb >>> 3;
        int i2 = zzb & 7;
        if (i2 == 0) {
            zza(b, i, zzhd.zzg());
            return true;
        } else if (i2 == 1) {
            zzb(b, i, zzhd.zzi());
            return true;
        } else if (i2 == 2) {
            zza(b, i, zzhd.zzn());
            return true;
        } else if (i2 == 3) {
            Object zza = zza();
            int i3 = 4 | (i << 3);
            while (zzhd.zza() != Integer.MAX_VALUE) {
                if (!zza((B) zza, zzhd)) {
                    break;
                }
            }
            if (i3 == zzhd.zzb()) {
                zza(b, i, (T) zza((B) zza));
                return true;
            }
            throw zzfn.zze();
        } else if (i2 == 4) {
            return false;
        } else {
            if (i2 == 5) {
                zza(b, i, zzhd.zzj());
                return true;
            }
            throw zzfn.zzf();
        }
    }
}
