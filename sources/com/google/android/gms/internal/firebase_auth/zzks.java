package com.google.android.gms.internal.firebase_auth;

import java.io.IOException;

/* compiled from: com.google.firebase:firebase-auth@@18.1.0 */
abstract class zzks<T, B> {
    zzks() {
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
    public abstract void zza(B b, int i, zzgm zzgm);

    /* access modifiers changed from: 0000 */
    public abstract void zza(B b, int i, T t);

    /* access modifiers changed from: 0000 */
    public abstract void zza(T t, zzll zzll) throws IOException;

    /* access modifiers changed from: 0000 */
    public abstract void zza(Object obj, T t);

    /* access modifiers changed from: 0000 */
    public abstract boolean zza(zzjx zzjx);

    /* access modifiers changed from: 0000 */
    public abstract T zzb(Object obj);

    /* access modifiers changed from: 0000 */
    public abstract void zzb(B b, int i, long j);

    /* access modifiers changed from: 0000 */
    public abstract void zzb(T t, zzll zzll) throws IOException;

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
    public final boolean zza(B b, zzjx zzjx) throws IOException {
        int zzb = zzjx.zzb();
        int i = zzb >>> 3;
        int i2 = zzb & 7;
        if (i2 == 0) {
            zza(b, i, zzjx.zzg());
            return true;
        } else if (i2 == 1) {
            zzb(b, i, zzjx.zzi());
            return true;
        } else if (i2 == 2) {
            zza(b, i, zzjx.zzn());
            return true;
        } else if (i2 == 3) {
            Object zza = zza();
            int i3 = 4 | (i << 3);
            while (zzjx.zza() != Integer.MAX_VALUE) {
                if (!zza((B) zza, zzjx)) {
                    break;
                }
            }
            if (i3 == zzjx.zzb()) {
                zza(b, i, (T) zza((B) zza));
                return true;
            }
            throw zzig.zze();
        } else if (i2 == 4) {
            return false;
        } else {
            if (i2 == 5) {
                zza(b, i, zzjx.zzj());
                return true;
            }
            throw zzig.zzf();
        }
    }
}
