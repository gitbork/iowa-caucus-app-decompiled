package com.google.android.gms.internal.measurement;

import com.google.android.gms.internal.measurement.zzfd.zzb;
import com.google.android.gms.internal.measurement.zzfd.zze;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map.Entry;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.0.1 */
final class zzgt<T> implements zzhc<T> {
    private final zzgn zza;
    private final zzhu<?, ?> zzb;
    private final boolean zzc;
    private final zzes<?> zzd;

    private zzgt(zzhu<?, ?> zzhu, zzes<?> zzes, zzgn zzgn) {
        this.zzb = zzhu;
        this.zzc = zzes.zza(zzgn);
        this.zzd = zzes;
        this.zza = zzgn;
    }

    static <T> zzgt<T> zza(zzhu<?, ?> zzhu, zzes<?> zzes, zzgn zzgn) {
        return new zzgt<>(zzhu, zzes, zzgn);
    }

    public final T zza() {
        return this.zza.zzbq().zzt();
    }

    public final boolean zza(T t, T t2) {
        if (!this.zzb.zzb(t).equals(this.zzb.zzb(t2))) {
            return false;
        }
        if (this.zzc) {
            return this.zzd.zza((Object) t).equals(this.zzd.zza((Object) t2));
        }
        return true;
    }

    public final int zza(T t) {
        int hashCode = this.zzb.zzb(t).hashCode();
        return this.zzc ? (hashCode * 53) + this.zzd.zza((Object) t).hashCode() : hashCode;
    }

    public final void zzb(T t, T t2) {
        zzhe.zza(this.zzb, t, t2);
        if (this.zzc) {
            zzhe.zza(this.zzd, t, t2);
        }
    }

    public final void zza(T t, zzir zzir) throws IOException {
        Iterator zzd2 = this.zzd.zza((Object) t).zzd();
        while (zzd2.hasNext()) {
            Entry entry = (Entry) zzd2.next();
            zzev zzev = (zzev) entry.getKey();
            if (zzev.zzc() != zzio.MESSAGE || zzev.zzd() || zzev.zze()) {
                throw new IllegalStateException("Found invalid MessageSet item.");
            } else if (entry instanceof zzfq) {
                zzir.zza(zzev.zza(), (Object) ((zzfq) entry).zza().zzc());
            } else {
                zzir.zza(zzev.zza(), entry.getValue());
            }
        }
        zzhu<?, ?> zzhu = this.zzb;
        zzhu.zzb(zzhu.zzb(t), zzir);
    }

    public final void zza(T t, byte[] bArr, int i, int i2, zzdq zzdq) throws IOException {
        zzfd zzfd = (zzfd) t;
        zzhx zzhx = zzfd.zzb;
        if (zzhx == zzhx.zza()) {
            zzhx = zzhx.zzb();
            zzfd.zzb = zzhx;
        }
        ((zzb) t).zza();
        zze zze = null;
        while (i < i2) {
            int zza2 = zzdr.zza(bArr, i, zzdq);
            int i3 = zzdq.zza;
            if (i3 == 11) {
                int i4 = 0;
                Object obj = null;
                while (zza2 < i2) {
                    zza2 = zzdr.zza(bArr, zza2, zzdq);
                    int i5 = zzdq.zza;
                    int i6 = i5 >>> 3;
                    int i7 = i5 & 7;
                    if (i6 != 2) {
                        if (i6 == 3) {
                            if (zze != null) {
                                zzgy.zza();
                                throw new NoSuchMethodError();
                            } else if (i7 == 2) {
                                zza2 = zzdr.zze(bArr, zza2, zzdq);
                                obj = (zzdv) zzdq.zzc;
                            }
                        }
                    } else if (i7 == 0) {
                        zza2 = zzdr.zza(bArr, zza2, zzdq);
                        i4 = zzdq.zza;
                        zze = (zze) this.zzd.zza(zzdq.zzd, this.zza, i4);
                    }
                    if (i5 == 12) {
                        break;
                    }
                    zza2 = zzdr.zza(i5, bArr, zza2, i2, zzdq);
                }
                if (obj != null) {
                    zzhx.zza((i4 << 3) | 2, obj);
                }
                i = zza2;
            } else if ((i3 & 7) == 2) {
                zze = (zze) this.zzd.zza(zzdq.zzd, this.zza, i3 >>> 3);
                if (zze == null) {
                    i = zzdr.zza(i3, bArr, zza2, i2, zzhx, zzdq);
                } else {
                    zzgy.zza();
                    throw new NoSuchMethodError();
                }
            } else {
                i = zzdr.zza(i3, bArr, zza2, i2, zzdq);
            }
        }
        if (i != i2) {
            throw zzfn.zzg();
        }
    }

    public final void zza(T t, zzhd zzhd, zzeq zzeq) throws IOException {
        boolean z;
        zzhu<?, ?> zzhu = this.zzb;
        zzes<?> zzes = this.zzd;
        Object zzc2 = zzhu.zzc(t);
        zzet zzb2 = zzes.zzb(t);
        do {
            try {
                if (zzhd.zza() == Integer.MAX_VALUE) {
                    zzhu.zzb((Object) t, zzc2);
                    return;
                }
                int zzb3 = zzhd.zzb();
                if (zzb3 == 11) {
                    int i = 0;
                    Object obj = null;
                    zzdv zzdv = null;
                    while (zzhd.zza() != Integer.MAX_VALUE) {
                        int zzb4 = zzhd.zzb();
                        if (zzb4 == 16) {
                            i = zzhd.zzo();
                            obj = zzes.zza(zzeq, this.zza, i);
                        } else if (zzb4 == 26) {
                            if (obj != null) {
                                zzes.zza(zzhd, obj, zzeq, zzb2);
                            } else {
                                zzdv = zzhd.zzn();
                            }
                        } else if (!zzhd.zzc()) {
                            break;
                        }
                    }
                    if (zzhd.zzb() != 12) {
                        throw zzfn.zze();
                    } else if (zzdv != null) {
                        if (obj != null) {
                            zzes.zza(zzdv, obj, zzeq, zzb2);
                        } else {
                            zzhu.zza(zzc2, i, zzdv);
                        }
                    }
                } else if ((zzb3 & 7) == 2) {
                    Object zza2 = zzes.zza(zzeq, this.zza, zzb3 >>> 3);
                    if (zza2 != null) {
                        zzes.zza(zzhd, zza2, zzeq, zzb2);
                    } else {
                        z = zzhu.zza(zzc2, zzhd);
                        continue;
                    }
                } else {
                    z = zzhd.zzc();
                    continue;
                }
                z = true;
                continue;
            } finally {
                zzhu.zzb((Object) t, zzc2);
            }
        } while (z);
    }

    public final void zzc(T t) {
        this.zzb.zzd(t);
        this.zzd.zzc(t);
    }

    public final boolean zzd(T t) {
        return this.zzd.zza((Object) t).zzf();
    }

    public final int zzb(T t) {
        zzhu<?, ?> zzhu = this.zzb;
        int zze = zzhu.zze(zzhu.zzb(t)) + 0;
        return this.zzc ? zze + this.zzd.zza((Object) t).zzg() : zze;
    }
}
