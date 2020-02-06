package com.google.android.gms.internal.vision;

import com.google.android.gms.internal.vision.zzfy.zzd;
import com.google.android.gms.internal.vision.zzfy.zzf;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

final class zzfm extends zzfl<zze> {
    zzfm() {
    }

    /* access modifiers changed from: 0000 */
    public final boolean zze(zzhf zzhf) {
        return zzhf instanceof zzd;
    }

    /* access modifiers changed from: 0000 */
    public final zzfp<zze> zzc(Object obj) {
        return ((zzd) obj).zzwp;
    }

    /* access modifiers changed from: 0000 */
    public final void zza(Object obj, zzfp<zze> zzfp) {
        ((zzd) obj).zzwp = zzfp;
    }

    /* access modifiers changed from: 0000 */
    public final zzfp<zze> zzd(Object obj) {
        zzfp<zze> zzc = zzc(obj);
        if (!zzc.isImmutable()) {
            return zzc;
        }
        zzfp<zze> zzfp = (zzfp) zzc.clone();
        zza(obj, zzfp);
        return zzfp;
    }

    /* access modifiers changed from: 0000 */
    public final void zze(Object obj) {
        zzc(obj).zzci();
    }

    /* access modifiers changed from: 0000 */
    public final <UT, UB> UB zza(zzhv zzhv, Object obj, zzfk zzfk, zzfp<zze> zzfp, UB ub, zzio<UT, UB> zzio) throws IOException {
        ArrayList arrayList;
        zzf zzf = (zzf) obj;
        int i = zzf.zzww.number;
        if (!zzf.zzww.zzws || !zzf.zzww.zzwt) {
            Object obj2 = null;
            if (zzf.zzww.zzwr != zzjd.ENUM) {
                switch (zzfn.zzrr[zzf.zzww.zzwr.ordinal()]) {
                    case 1:
                        obj2 = Double.valueOf(zzhv.readDouble());
                        break;
                    case 2:
                        obj2 = Float.valueOf(zzhv.readFloat());
                        break;
                    case 3:
                        obj2 = Long.valueOf(zzhv.zzcq());
                        break;
                    case 4:
                        obj2 = Long.valueOf(zzhv.zzcp());
                        break;
                    case 5:
                        obj2 = Integer.valueOf(zzhv.zzcr());
                        break;
                    case 6:
                        obj2 = Long.valueOf(zzhv.zzcs());
                        break;
                    case 7:
                        obj2 = Integer.valueOf(zzhv.zzct());
                        break;
                    case 8:
                        obj2 = Boolean.valueOf(zzhv.zzcu());
                        break;
                    case 9:
                        obj2 = Integer.valueOf(zzhv.zzcx());
                        break;
                    case 10:
                        obj2 = Integer.valueOf(zzhv.zzcz());
                        break;
                    case 11:
                        obj2 = Long.valueOf(zzhv.zzda());
                        break;
                    case 12:
                        obj2 = Integer.valueOf(zzhv.zzdb());
                        break;
                    case 13:
                        obj2 = Long.valueOf(zzhv.zzdc());
                        break;
                    case 14:
                        throw new IllegalStateException("Shouldn't reach here.");
                    case 15:
                        obj2 = zzhv.zzcw();
                        break;
                    case 16:
                        obj2 = zzhv.readString();
                        break;
                    case 17:
                        obj2 = zzhv.zzb(zzf.zzwv.getClass(), zzfk);
                        break;
                    case 18:
                        obj2 = zzhv.zza(zzf.zzwv.getClass(), zzfk);
                        break;
                }
            } else {
                int zzcr = zzhv.zzcr();
                if (zzf.zzww.zzwq.zzf(zzcr) == null) {
                    return zzhy.zza(i, zzcr, ub, zzio);
                }
                obj2 = Integer.valueOf(zzcr);
            }
            if (zzf.zzww.zzws) {
                zzfp.zzb(zzf.zzww, obj2);
            } else {
                int i2 = zzfn.zzrr[zzf.zzww.zzwr.ordinal()];
                if (i2 == 17 || i2 == 18) {
                    Object zza = zzfp.zza(zzf.zzww);
                    if (zza != null) {
                        obj2 = zzga.zza(zza, obj2);
                    }
                }
                zzfp.zza(zzf.zzww, obj2);
            }
        } else {
            switch (zzfn.zzrr[zzf.zzww.zzwr.ordinal()]) {
                case 1:
                    arrayList = new ArrayList();
                    zzhv.zza(arrayList);
                    break;
                case 2:
                    arrayList = new ArrayList();
                    zzhv.zzb(arrayList);
                    break;
                case 3:
                    arrayList = new ArrayList();
                    zzhv.zzd(arrayList);
                    break;
                case 4:
                    arrayList = new ArrayList();
                    zzhv.zzc(arrayList);
                    break;
                case 5:
                    arrayList = new ArrayList();
                    zzhv.zze(arrayList);
                    break;
                case 6:
                    arrayList = new ArrayList();
                    zzhv.zzf(arrayList);
                    break;
                case 7:
                    arrayList = new ArrayList();
                    zzhv.zzg(arrayList);
                    break;
                case 8:
                    arrayList = new ArrayList();
                    zzhv.zzh(arrayList);
                    break;
                case 9:
                    arrayList = new ArrayList();
                    zzhv.zzk(arrayList);
                    break;
                case 10:
                    arrayList = new ArrayList();
                    zzhv.zzm(arrayList);
                    break;
                case 11:
                    arrayList = new ArrayList();
                    zzhv.zzn(arrayList);
                    break;
                case 12:
                    arrayList = new ArrayList();
                    zzhv.zzo(arrayList);
                    break;
                case 13:
                    arrayList = new ArrayList();
                    zzhv.zzp(arrayList);
                    break;
                case 14:
                    arrayList = new ArrayList();
                    zzhv.zzl(arrayList);
                    ub = zzhy.zza(i, (List<Integer>) arrayList, zzf.zzww.zzwq, ub, zzio);
                    break;
                default:
                    String valueOf = String.valueOf(zzf.zzww.zzwr);
                    StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 23);
                    sb.append("Type cannot be packed: ");
                    sb.append(valueOf);
                    throw new IllegalStateException(sb.toString());
            }
            zzfp.zza(zzf.zzww, (Object) arrayList);
        }
        return ub;
    }

    /* access modifiers changed from: 0000 */
    public final int zza(Entry<?, ?> entry) {
        return ((zze) entry.getKey()).number;
    }

    /* access modifiers changed from: 0000 */
    public final void zza(zzjj zzjj, Entry<?, ?> entry) throws IOException {
        zze zze = (zze) entry.getKey();
        if (zze.zzws) {
            switch (zzfn.zzrr[zze.zzwr.ordinal()]) {
                case 1:
                    zzhy.zza(zze.number, (List) entry.getValue(), zzjj, zze.zzwt);
                    break;
                case 2:
                    zzhy.zzb(zze.number, (List) entry.getValue(), zzjj, zze.zzwt);
                    return;
                case 3:
                    zzhy.zzc(zze.number, (List) entry.getValue(), zzjj, zze.zzwt);
                    return;
                case 4:
                    zzhy.zzd(zze.number, (List) entry.getValue(), zzjj, zze.zzwt);
                    return;
                case 5:
                    zzhy.zzh(zze.number, (List) entry.getValue(), zzjj, zze.zzwt);
                    return;
                case 6:
                    zzhy.zzf(zze.number, (List) entry.getValue(), zzjj, zze.zzwt);
                    return;
                case 7:
                    zzhy.zzk(zze.number, (List) entry.getValue(), zzjj, zze.zzwt);
                    return;
                case 8:
                    zzhy.zzn(zze.number, (List) entry.getValue(), zzjj, zze.zzwt);
                    return;
                case 9:
                    zzhy.zzi(zze.number, (List) entry.getValue(), zzjj, zze.zzwt);
                    return;
                case 10:
                    zzhy.zzl(zze.number, (List) entry.getValue(), zzjj, zze.zzwt);
                    return;
                case 11:
                    zzhy.zzg(zze.number, (List) entry.getValue(), zzjj, zze.zzwt);
                    return;
                case 12:
                    zzhy.zzj(zze.number, (List) entry.getValue(), zzjj, zze.zzwt);
                    return;
                case 13:
                    zzhy.zze(zze.number, (List) entry.getValue(), zzjj, zze.zzwt);
                    return;
                case 14:
                    zzhy.zzh(zze.number, (List) entry.getValue(), zzjj, zze.zzwt);
                    return;
                case 15:
                    zzhy.zzb(zze.number, (List) entry.getValue(), zzjj);
                    return;
                case 16:
                    zzhy.zza(zze.number, (List) entry.getValue(), zzjj);
                    return;
                case 17:
                    List list = (List) entry.getValue();
                    if (list != null && !list.isEmpty()) {
                        zzhy.zzb(zze.number, (List) entry.getValue(), zzjj, zzhs.zzgl().zzf(list.get(0).getClass()));
                    }
                    return;
                case 18:
                    List list2 = (List) entry.getValue();
                    if (list2 != null && !list2.isEmpty()) {
                        zzhy.zza(zze.number, (List) entry.getValue(), zzjj, zzhs.zzgl().zzf(list2.get(0).getClass()));
                        break;
                    }
            }
            return;
        }
        switch (zzfn.zzrr[zze.zzwr.ordinal()]) {
            case 1:
                zzjj.zza(zze.number, ((Double) entry.getValue()).doubleValue());
                break;
            case 2:
                zzjj.zza(zze.number, ((Float) entry.getValue()).floatValue());
                return;
            case 3:
                zzjj.zzi(zze.number, ((Long) entry.getValue()).longValue());
                return;
            case 4:
                zzjj.zza(zze.number, ((Long) entry.getValue()).longValue());
                return;
            case 5:
                zzjj.zze(zze.number, ((Integer) entry.getValue()).intValue());
                return;
            case 6:
                zzjj.zzc(zze.number, ((Long) entry.getValue()).longValue());
                return;
            case 7:
                zzjj.zzh(zze.number, ((Integer) entry.getValue()).intValue());
                return;
            case 8:
                zzjj.zzb(zze.number, ((Boolean) entry.getValue()).booleanValue());
                return;
            case 9:
                zzjj.zzf(zze.number, ((Integer) entry.getValue()).intValue());
                return;
            case 10:
                zzjj.zzo(zze.number, ((Integer) entry.getValue()).intValue());
                return;
            case 11:
                zzjj.zzj(zze.number, ((Long) entry.getValue()).longValue());
                return;
            case 12:
                zzjj.zzg(zze.number, ((Integer) entry.getValue()).intValue());
                return;
            case 13:
                zzjj.zzb(zze.number, ((Long) entry.getValue()).longValue());
                return;
            case 14:
                zzjj.zze(zze.number, ((Integer) entry.getValue()).intValue());
                return;
            case 15:
                zzjj.zza(zze.number, (zzeo) entry.getValue());
                return;
            case 16:
                zzjj.zza(zze.number, (String) entry.getValue());
                return;
            case 17:
                zzjj.zzb(zze.number, entry.getValue(), zzhs.zzgl().zzf(entry.getValue().getClass()));
                return;
            case 18:
                zzjj.zza(zze.number, entry.getValue(), zzhs.zzgl().zzf(entry.getValue().getClass()));
                break;
        }
    }

    /* access modifiers changed from: 0000 */
    public final Object zza(zzfk zzfk, zzhf zzhf, int i) {
        return zzfk.zza(zzhf, i);
    }

    /* access modifiers changed from: 0000 */
    public final void zza(zzhv zzhv, Object obj, zzfk zzfk, zzfp<zze> zzfp) throws IOException {
        zzf zzf = (zzf) obj;
        zzfp.zza(zzf.zzww, zzhv.zza(zzf.zzwv.getClass(), zzfk));
    }

    /* access modifiers changed from: 0000 */
    public final void zza(zzeo zzeo, Object obj, zzfk zzfk, zzfp<zze> zzfp) throws IOException {
        byte[] bArr;
        zzf zzf = (zzf) obj;
        zzhf zzff = zzf.zzwv.zzfa().zzff();
        int size = zzeo.size();
        if (size == 0) {
            bArr = zzga.zzxn;
        } else {
            byte[] bArr2 = new byte[size];
            zzeo.zza(bArr2, 0, 0, size);
            bArr = bArr2;
        }
        ByteBuffer wrap = ByteBuffer.wrap(bArr);
        if (wrap.hasArray()) {
            zzel zzel = new zzel(wrap, true);
            zzhs.zzgl().zzs(zzff).zza(zzff, zzel, zzfk);
            zzfp.zza(zzf.zzww, (Object) zzff);
            if (zzel.zzcn() != Integer.MAX_VALUE) {
                throw zzgf.zzfl();
            }
            return;
        }
        throw new IllegalArgumentException("Direct buffers not yet supported");
    }
}
