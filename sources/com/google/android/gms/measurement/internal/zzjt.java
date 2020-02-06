package com.google.android.gms.measurement.internal;

import android.content.Context;
import android.text.TextUtils;
import androidx.annotation.Nullable;
import androidx.annotation.WorkerThread;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.internal.measurement.zzbj;
import com.google.android.gms.internal.measurement.zzbj.zzb;
import com.google.android.gms.internal.measurement.zzbj.zzd;
import com.google.android.gms.internal.measurement.zzbr;
import com.google.android.gms.internal.measurement.zzbr.zzc;
import com.google.android.gms.internal.measurement.zzbr.zze;
import com.google.android.gms.internal.measurement.zzbr.zzf;
import com.google.android.gms.internal.measurement.zzbr.zzg;
import com.google.android.gms.internal.measurement.zzbr.zzi;
import com.google.android.gms.internal.measurement.zzbr.zzj;
import com.google.android.gms.internal.measurement.zzbr.zzk;
import com.google.android.gms.internal.measurement.zzbr.zzk.zza;
import com.google.android.gms.measurement.api.AppMeasurementSdk.ConditionalUserProperty;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import p006io.sentry.marshaller.json.JsonMarshaller;

/* compiled from: com.google.android.gms:play-services-measurement@@17.0.1 */
public final class zzjt extends zzjm {
    zzjt(zzjp zzjp) {
        super(zzjp);
    }

    /* access modifiers changed from: protected */
    public final boolean zze() {
        return false;
    }

    /* access modifiers changed from: 0000 */
    public final void zza(zza zza, Object obj) {
        Preconditions.checkNotNull(obj);
        zza.zza().zzb().zzc();
        if (obj instanceof String) {
            zza.zzb((String) obj);
        } else if (obj instanceof Long) {
            zza.zzb(((Long) obj).longValue());
        } else if (obj instanceof Double) {
            zza.zza(((Double) obj).doubleValue());
        } else {
            zzr().zzf().zza("Ignoring invalid (type) user attribute value", obj);
        }
    }

    /* access modifiers changed from: 0000 */
    public final void zza(zze.zza zza, Object obj) {
        Preconditions.checkNotNull(obj);
        zza.zza().zzb().zzc();
        if (obj instanceof String) {
            zza.zzb((String) obj);
        } else if (obj instanceof Long) {
            zza.zza(((Long) obj).longValue());
        } else if (obj instanceof Double) {
            zza.zza(((Double) obj).doubleValue());
        } else {
            zzr().zzf().zza("Ignoring invalid (type) event param value", obj);
        }
    }

    static zze zza(zzc zzc, String str) {
        for (zze zze : zzc.zza()) {
            if (zze.zza().equals(str)) {
                return zze;
            }
        }
        return null;
    }

    static Object zzb(zzc zzc, String str) {
        zze zza = zza(zzc, str);
        if (zza != null) {
            if (zza.zzb()) {
                return zza.zzc();
            }
            if (zza.zzd()) {
                return Long.valueOf(zza.zze());
            }
            if (zza.zzf()) {
                return Double.valueOf(zza.zzg());
            }
        }
        return null;
    }

    static void zza(zzc.zza zza, String str, Object obj) {
        List zza2 = zza.zza();
        int i = 0;
        while (true) {
            if (i >= zza2.size()) {
                i = -1;
                break;
            } else if (str.equals(((zze) zza2.get(i)).zza())) {
                break;
            } else {
                i++;
            }
        }
        zze.zza zza3 = zze.zzh().zza(str);
        if (obj instanceof Long) {
            zza3.zza(((Long) obj).longValue());
        } else if (obj instanceof String) {
            zza3.zzb((String) obj);
        } else if (obj instanceof Double) {
            zza3.zza(((Double) obj).doubleValue());
        }
        if (i >= 0) {
            zza.zza(i, zza3);
        } else {
            zza.zza(zza3);
        }
    }

    /* access modifiers changed from: 0000 */
    public final String zza(zzf zzf) {
        if (zzf == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("\nbatch {\n");
        Iterator it = zzf.zza().iterator();
        while (true) {
            String str = "}\n";
            if (it.hasNext()) {
                zzg zzg = (zzg) it.next();
                if (zzg != null) {
                    zza(sb, 1);
                    sb.append("bundle {\n");
                    if (zzg.zza()) {
                        zza(sb, 1, "protocol_version", (Object) Integer.valueOf(zzg.zzb()));
                    }
                    zza(sb, 1, JsonMarshaller.PLATFORM, (Object) zzg.zzq());
                    if (zzg.zzz()) {
                        zza(sb, 1, "gmp_version", (Object) Long.valueOf(zzg.zzaa()));
                    }
                    if (zzg.zzab()) {
                        zza(sb, 1, "uploading_gmp_version", (Object) Long.valueOf(zzg.zzac()));
                    }
                    if (zzg.zzbc()) {
                        zza(sb, 1, "dynamite_version", (Object) Long.valueOf(zzg.zzbd()));
                    }
                    if (zzg.zzau()) {
                        zza(sb, 1, "config_version", (Object) Long.valueOf(zzg.zzav()));
                    }
                    zza(sb, 1, "gmp_app_id", (Object) zzg.zzam());
                    zza(sb, 1, "admob_app_id", (Object) zzg.zzbb());
                    zza(sb, 1, "app_id", (Object) zzg.zzx());
                    zza(sb, 1, "app_version", (Object) zzg.zzy());
                    if (zzg.zzar()) {
                        zza(sb, 1, "app_version_major", (Object) Integer.valueOf(zzg.zzas()));
                    }
                    zza(sb, 1, "firebase_instance_id", (Object) zzg.zzaq());
                    if (zzg.zzah()) {
                        zza(sb, 1, "dev_cert_hash", (Object) Long.valueOf(zzg.zzai()));
                    }
                    zza(sb, 1, "app_store", (Object) zzg.zzw());
                    if (zzg.zzg()) {
                        zza(sb, 1, "upload_timestamp_millis", (Object) Long.valueOf(zzg.zzh()));
                    }
                    if (zzg.zzi()) {
                        zza(sb, 1, "start_timestamp_millis", (Object) Long.valueOf(zzg.zzj()));
                    }
                    if (zzg.zzk()) {
                        zza(sb, 1, "end_timestamp_millis", (Object) Long.valueOf(zzg.zzl()));
                    }
                    if (zzg.zzm()) {
                        zza(sb, 1, "previous_bundle_start_timestamp_millis", (Object) Long.valueOf(zzg.zzn()));
                    }
                    if (zzg.zzo()) {
                        zza(sb, 1, "previous_bundle_end_timestamp_millis", (Object) Long.valueOf(zzg.zzp()));
                    }
                    zza(sb, 1, "app_instance_id", (Object) zzg.zzag());
                    zza(sb, 1, "resettable_device_id", (Object) zzg.zzad());
                    zza(sb, 1, "device_id", (Object) zzg.zzat());
                    zza(sb, 1, "ds_id", (Object) zzg.zzay());
                    if (zzg.zzae()) {
                        zza(sb, 1, "limited_ad_tracking", (Object) Boolean.valueOf(zzg.zzaf()));
                    }
                    zza(sb, 1, "os_version", (Object) zzg.zzr());
                    zza(sb, 1, "device_model", (Object) zzg.zzs());
                    zza(sb, 1, "user_default_language", (Object) zzg.zzt());
                    if (zzg.zzu()) {
                        zza(sb, 1, "time_zone_offset_minutes", (Object) Integer.valueOf(zzg.mo17159g_()));
                    }
                    if (zzg.zzaj()) {
                        zza(sb, 1, "bundle_sequential_index", (Object) Integer.valueOf(zzg.zzak()));
                    }
                    if (zzg.zzan()) {
                        zza(sb, 1, "service_upload", (Object) Boolean.valueOf(zzg.zzao()));
                    }
                    zza(sb, 1, "health_monitor", (Object) zzg.zzal());
                    if (zzg.zzaw() && zzg.zzax() != 0) {
                        zza(sb, 1, "android_id", (Object) Long.valueOf(zzg.zzax()));
                    }
                    if (zzg.zzaz()) {
                        zza(sb, 1, "retry_counter", (Object) Integer.valueOf(zzg.zzba()));
                    }
                    List<zzk> zze = zzg.zze();
                    String str2 = "double_value";
                    String str3 = "int_value";
                    String str4 = "string_value";
                    String str5 = ConditionalUserProperty.NAME;
                    int i = 2;
                    if (zze != null) {
                        for (zzk zzk : zze) {
                            if (zzk != null) {
                                zza(sb, 2);
                                sb.append("user_property {\n");
                                zza(sb, 2, "set_timestamp_millis", (Object) zzk.zza() ? Long.valueOf(zzk.zzb()) : null);
                                zza(sb, 2, str5, (Object) zzo().zzc(zzk.zzc()));
                                zza(sb, 2, str4, (Object) zzk.zze());
                                zza(sb, 2, str3, (Object) zzk.zzf() ? Long.valueOf(zzk.zzg()) : null);
                                zza(sb, 2, str2, (Object) zzk.zzh() ? Double.valueOf(zzk.zzi()) : null);
                                zza(sb, 2);
                                sb.append(str);
                            }
                        }
                    }
                    List<zzbr.zza> zzap = zzg.zzap();
                    String zzx = zzg.zzx();
                    if (zzap != null) {
                        for (zzbr.zza zza : zzap) {
                            if (zza != null) {
                                zza(sb, i);
                                sb.append("audience_membership {\n");
                                if (zza.zza()) {
                                    zza(sb, i, "audience_id", (Object) Integer.valueOf(zza.zzb()));
                                }
                                if (zza.zzf()) {
                                    zza(sb, i, "new_audience", (Object) Boolean.valueOf(zza.zzg()));
                                }
                                StringBuilder sb2 = sb;
                                String str6 = zzx;
                                zza(sb2, 2, "current_data", zza.zzc(), str6);
                                zza(sb2, 2, "previous_data", zza.zze(), str6);
                                zza(sb, 2);
                                sb.append(str);
                                i = 2;
                            }
                        }
                    }
                    List<zzc> zzc = zzg.zzc();
                    if (zzc != null) {
                        for (zzc zzc2 : zzc) {
                            if (zzc2 != null) {
                                zza(sb, 2);
                                sb.append("event {\n");
                                zza(sb, 2, str5, (Object) zzo().zza(zzc2.zzc()));
                                if (zzc2.zzd()) {
                                    zza(sb, 2, "timestamp_millis", (Object) Long.valueOf(zzc2.zze()));
                                }
                                if (zzc2.zzf()) {
                                    zza(sb, 2, "previous_timestamp_millis", (Object) Long.valueOf(zzc2.zzg()));
                                }
                                if (zzc2.zzh()) {
                                    zza(sb, 2, "count", (Object) Integer.valueOf(zzc2.zzi()));
                                }
                                if (zzc2.zzb() != 0) {
                                    List<zze> zza2 = zzc2.zza();
                                    if (zza2 != null) {
                                        for (zze zze2 : zza2) {
                                            if (zze2 != null) {
                                                zza(sb, 3);
                                                sb.append("param {\n");
                                                zza(sb, 3, str5, (Object) zzo().zzb(zze2.zza()));
                                                zza(sb, 3, str4, (Object) zze2.zzc());
                                                zza(sb, 3, str3, (Object) zze2.zzd() ? Long.valueOf(zze2.zze()) : null);
                                                zza(sb, 3, str2, (Object) zze2.zzf() ? Double.valueOf(zze2.zzg()) : null);
                                                zza(sb, 3);
                                                sb.append(str);
                                            }
                                        }
                                    }
                                }
                                zza(sb, 2);
                                sb.append(str);
                            }
                        }
                    }
                    zza(sb, 1);
                    sb.append(str);
                }
            } else {
                sb.append(str);
                return sb.toString();
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public final String zza(zzb zzb) {
        if (zzb == null) {
            return "null";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("\nevent_filter {\n");
        if (zzb.zza()) {
            zza(sb, 0, "filter_id", (Object) Integer.valueOf(zzb.zzb()));
        }
        zza(sb, 0, "event_name", (Object) zzo().zza(zzb.zzc()));
        String zza = zza(zzb.zzh(), zzb.zzi(), zzb.zzk());
        if (!zza.isEmpty()) {
            zza(sb, 0, "filter_type", (Object) zza);
        }
        zza(sb, 1, "event_count_filter", zzb.zzg());
        sb.append("  filters {\n");
        for (zzbj.zzc zza2 : zzb.zzd()) {
            zza(sb, 2, zza2);
        }
        zza(sb, 1);
        sb.append("}\n}\n");
        return sb.toString();
    }

    /* access modifiers changed from: 0000 */
    public final String zza(zzbj.zze zze) {
        if (zze == null) {
            return "null";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("\nproperty_filter {\n");
        if (zze.zza()) {
            zza(sb, 0, "filter_id", (Object) Integer.valueOf(zze.zzb()));
        }
        zza(sb, 0, "property_name", (Object) zzo().zzc(zze.zzc()));
        String zza = zza(zze.zze(), zze.zzf(), zze.zzh());
        if (!zza.isEmpty()) {
            zza(sb, 0, "filter_type", (Object) zza);
        }
        zza(sb, 1, zze.zzd());
        sb.append("}\n");
        return sb.toString();
    }

    private static String zza(boolean z, boolean z2, boolean z3) {
        StringBuilder sb = new StringBuilder();
        if (z) {
            sb.append("Dynamic ");
        }
        if (z2) {
            sb.append("Sequence ");
        }
        if (z3) {
            sb.append("Session-Scoped ");
        }
        return sb.toString();
    }

    private final void zza(StringBuilder sb, int i, String str, zzi zzi, String str2) {
        if (zzi != null) {
            zza(sb, 3);
            sb.append(str);
            sb.append(" {\n");
            String str3 = ", ";
            if (zzi.zzd() != 0) {
                zza(sb, 4);
                sb.append("results: ");
                int i2 = 0;
                for (Long l : zzi.zzc()) {
                    int i3 = i2 + 1;
                    if (i2 != 0) {
                        sb.append(str3);
                    }
                    sb.append(l);
                    i2 = i3;
                }
                sb.append(10);
            }
            if (zzi.zzb() != 0) {
                zza(sb, 4);
                sb.append("status: ");
                int i4 = 0;
                for (Long l2 : zzi.zza()) {
                    int i5 = i4 + 1;
                    if (i4 != 0) {
                        sb.append(str3);
                    }
                    sb.append(l2);
                    i4 = i5;
                }
                sb.append(10);
            }
            boolean zzi2 = zzt().zzi(str2);
            String str4 = "}\n";
            if (zzi2) {
                if (zzi.zzf() != 0) {
                    zza(sb, 4);
                    sb.append("dynamic_filter_timestamps: {");
                    int i6 = 0;
                    for (zzbr.zzb zzb : zzi.zze()) {
                        int i7 = i6 + 1;
                        if (i6 != 0) {
                            sb.append(str3);
                        }
                        sb.append(zzb.zza() ? Integer.valueOf(zzb.zzb()) : null);
                        sb.append(":");
                        sb.append(zzb.zzc() ? Long.valueOf(zzb.zzd()) : null);
                        i6 = i7;
                    }
                    sb.append(str4);
                }
                if (zzi.zzh() != 0) {
                    zza(sb, 4);
                    sb.append("sequence_filter_timestamps: {");
                    int i8 = 0;
                    for (zzj zzj : zzi.zzg()) {
                        int i9 = i8 + 1;
                        if (i8 != 0) {
                            sb.append(str3);
                        }
                        sb.append(zzj.zza() ? Integer.valueOf(zzj.zzb()) : null);
                        sb.append(": [");
                        int i10 = 0;
                        for (Long longValue : zzj.zzc()) {
                            long longValue2 = longValue.longValue();
                            int i11 = i10 + 1;
                            if (i10 != 0) {
                                sb.append(str3);
                            }
                            sb.append(longValue2);
                            i10 = i11;
                        }
                        sb.append("]");
                        i8 = i9;
                    }
                    sb.append(str4);
                }
            }
            zza(sb, 3);
            sb.append(str4);
        }
    }

    private final void zza(StringBuilder sb, int i, String str, zzd zzd) {
        if (zzd != null) {
            zza(sb, i);
            sb.append(str);
            sb.append(" {\n");
            if (zzd.zza()) {
                zza(sb, i, "comparison_type", (Object) zzd.zzb().name());
            }
            if (zzd.zzc()) {
                zza(sb, i, "match_as_float", (Object) Boolean.valueOf(zzd.zzd()));
            }
            zza(sb, i, "comparison_value", (Object) zzd.zzf());
            zza(sb, i, "min_comparison_value", (Object) zzd.zzh());
            zza(sb, i, "max_comparison_value", (Object) zzd.zzj());
            zza(sb, i);
            sb.append("}\n");
        }
    }

    private final void zza(StringBuilder sb, int i, zzbj.zzc zzc) {
        if (zzc != null) {
            zza(sb, i);
            sb.append("filter {\n");
            if (zzc.zze()) {
                zza(sb, i, "complement", (Object) Boolean.valueOf(zzc.zzf()));
            }
            zza(sb, i, "param_name", (Object) zzo().zzb(zzc.zzg()));
            int i2 = i + 1;
            zzbj.zzf zzb = zzc.zzb();
            String str = "}\n";
            if (zzb != null) {
                zza(sb, i2);
                sb.append("string_filter");
                sb.append(" {\n");
                if (zzb.zza()) {
                    zza(sb, i2, "match_type", (Object) zzb.zzb().name());
                }
                zza(sb, i2, "expression", (Object) zzb.zzd());
                if (zzb.zze()) {
                    zza(sb, i2, "case_sensitive", (Object) Boolean.valueOf(zzb.zzf()));
                }
                if (zzb.zzh() > 0) {
                    zza(sb, i2 + 1);
                    sb.append("expression_list {\n");
                    for (String str2 : zzb.zzg()) {
                        zza(sb, i2 + 2);
                        sb.append(str2);
                        sb.append(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
                    }
                    sb.append(str);
                }
                zza(sb, i2);
                sb.append(str);
            }
            zza(sb, i2, "number_filter", zzc.zzd());
            zza(sb, i);
            sb.append(str);
        }
    }

    private static void zza(StringBuilder sb, int i) {
        for (int i2 = 0; i2 < i; i2++) {
            sb.append("  ");
        }
    }

    private static void zza(StringBuilder sb, int i, String str, Object obj) {
        if (obj != null) {
            zza(sb, i + 1);
            sb.append(str);
            sb.append(": ");
            sb.append(obj);
            sb.append(10);
        }
    }

    /* access modifiers changed from: 0000 */
    /* JADX WARNING: Code restructure failed: missing block: B:10:?, code lost:
        zzr().zzf().zza("Failed to load parcelable from buffer");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x002c, code lost:
        return null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x002d, code lost:
        r1.recycle();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0030, code lost:
        throw r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x001a, code lost:
        r5 = move-exception;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x001c */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final <T extends android.os.Parcelable> T zza(byte[] r5, android.os.Parcelable.Creator<T> r6) {
        /*
            r4 = this;
            r0 = 0
            if (r5 != 0) goto L_0x0004
            return r0
        L_0x0004:
            android.os.Parcel r1 = android.os.Parcel.obtain()
            int r2 = r5.length     // Catch:{ ParseException -> 0x001c }
            r3 = 0
            r1.unmarshall(r5, r3, r2)     // Catch:{ ParseException -> 0x001c }
            r1.setDataPosition(r3)     // Catch:{ ParseException -> 0x001c }
            java.lang.Object r5 = r6.createFromParcel(r1)     // Catch:{ ParseException -> 0x001c }
            android.os.Parcelable r5 = (android.os.Parcelable) r5     // Catch:{ ParseException -> 0x001c }
            r1.recycle()
            return r5
        L_0x001a:
            r5 = move-exception
            goto L_0x002d
        L_0x001c:
            com.google.android.gms.measurement.internal.zzej r5 = r4.zzr()     // Catch:{ all -> 0x001a }
            com.google.android.gms.measurement.internal.zzel r5 = r5.zzf()     // Catch:{ all -> 0x001a }
            java.lang.String r6 = "Failed to load parcelable from buffer"
            r5.zza(r6)     // Catch:{ all -> 0x001a }
            r1.recycle()
            return r0
        L_0x002d:
            r1.recycle()
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzjt.zza(byte[], android.os.Parcelable$Creator):android.os.Parcelable");
    }

    /* access modifiers changed from: 0000 */
    @WorkerThread
    public final boolean zza(zzai zzai, zzn zzn) {
        Preconditions.checkNotNull(zzai);
        Preconditions.checkNotNull(zzn);
        if (!TextUtils.isEmpty(zzn.zzb) || !TextUtils.isEmpty(zzn.zzr)) {
            return true;
        }
        zzu();
        return false;
    }

    static boolean zza(String str) {
        return str != null && str.matches("([+-])?([0-9]+\\.?[0-9]*|[0-9]*\\.?[0-9]+)") && str.length() <= 310;
    }

    static boolean zza(List<Long> list, int i) {
        if (i < (list.size() << 6)) {
            if (((1 << (i % 64)) & ((Long) list.get(i / 64)).longValue()) != 0) {
                return true;
            }
        }
        return false;
    }

    static List<Long> zza(BitSet bitSet) {
        int length = (bitSet.length() + 63) / 64;
        ArrayList arrayList = new ArrayList(length);
        for (int i = 0; i < length; i++) {
            long j = 0;
            for (int i2 = 0; i2 < 64; i2++) {
                int i3 = (i << 6) + i2;
                if (i3 >= bitSet.length()) {
                    break;
                }
                if (bitSet.get(i3)) {
                    j |= 1 << i2;
                }
            }
            arrayList.add(Long.valueOf(j));
        }
        return arrayList;
    }

    /* access modifiers changed from: 0000 */
    public final List<Long> zza(List<Long> list, List<Integer> list2) {
        int i;
        ArrayList arrayList = new ArrayList(list);
        for (Integer num : list2) {
            if (num.intValue() < 0) {
                zzr().zzi().zza("Ignoring negative bit index to be cleared", num);
            } else {
                int intValue = num.intValue() / 64;
                if (intValue >= arrayList.size()) {
                    zzr().zzi().zza("Ignoring bit index greater than bitSet size", num, Integer.valueOf(arrayList.size()));
                } else {
                    arrayList.set(intValue, Long.valueOf(((Long) arrayList.get(intValue)).longValue() & ((1 << (num.intValue() % 64)) ^ -1)));
                }
            }
        }
        int size = arrayList.size();
        int size2 = arrayList.size() - 1;
        while (true) {
            int i2 = size2;
            i = size;
            size = i2;
            if (size >= 0 && ((Long) arrayList.get(size)).longValue() == 0) {
                size2 = size - 1;
            }
        }
        return arrayList.subList(0, i);
    }

    /* access modifiers changed from: 0000 */
    public final boolean zza(long j, long j2) {
        return j == 0 || j2 <= 0 || Math.abs(zzm().currentTimeMillis() - j) > j2;
    }

    /* access modifiers changed from: 0000 */
    @WorkerThread
    public final long zza(byte[] bArr) {
        Preconditions.checkNotNull(bArr);
        zzp().zzd();
        MessageDigest zzi = zzjx.zzi();
        if (zzi != null) {
            return zzjx.zza(zzi.digest(bArr));
        }
        zzr().zzf().zza("Failed to get MD5");
        return 0;
    }

    /* access modifiers changed from: 0000 */
    public final byte[] zzb(byte[] bArr) throws IOException {
        try {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
            GZIPInputStream gZIPInputStream = new GZIPInputStream(byteArrayInputStream);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] bArr2 = new byte[1024];
            while (true) {
                int read = gZIPInputStream.read(bArr2);
                if (read > 0) {
                    byteArrayOutputStream.write(bArr2, 0, read);
                } else {
                    gZIPInputStream.close();
                    byteArrayInputStream.close();
                    return byteArrayOutputStream.toByteArray();
                }
            }
        } catch (IOException e) {
            zzr().zzf().zza("Failed to ungzip content", e);
            throw e;
        }
    }

    /* access modifiers changed from: 0000 */
    public final byte[] zzc(byte[] bArr) throws IOException {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            GZIPOutputStream gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream);
            gZIPOutputStream.write(bArr);
            gZIPOutputStream.close();
            byteArrayOutputStream.close();
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            zzr().zzf().zza("Failed to gzip content", e);
            throw e;
        }
    }

    /* access modifiers changed from: 0000 */
    @Nullable
    public final List<Integer> zzf() {
        Map zza = zzak.zza(this.zza.zzn());
        if (zza == null || zza.size() == 0) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        int intValue = ((Integer) zzak.zzap.zza(null)).intValue();
        Iterator it = zza.entrySet().iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            Entry entry = (Entry) it.next();
            if (((String) entry.getKey()).startsWith("measurement.id.")) {
                try {
                    int parseInt = Integer.parseInt((String) entry.getValue());
                    if (parseInt != 0) {
                        arrayList.add(Integer.valueOf(parseInt));
                        if (arrayList.size() >= intValue) {
                            zzr().zzi().zza("Too many experiment IDs. Number of IDs", Integer.valueOf(arrayList.size()));
                            break;
                        }
                    } else {
                        continue;
                    }
                } catch (NumberFormatException e) {
                    zzr().zzi().zza("Experiment ID NumberFormatException", e);
                }
            }
        }
        if (arrayList.size() == 0) {
            return null;
        }
        return arrayList;
    }

    public final /* bridge */ /* synthetic */ zzjt zzg() {
        return super.zzg();
    }

    /* renamed from: e_ */
    public final /* bridge */ /* synthetic */ zzp mo18756e_() {
        return super.mo18756e_();
    }

    public final /* bridge */ /* synthetic */ zzx zzi() {
        return super.zzi();
    }

    public final /* bridge */ /* synthetic */ zzfh zzj() {
        return super.zzj();
    }

    public final /* bridge */ /* synthetic */ void zza() {
        super.zza();
    }

    public final /* bridge */ /* synthetic */ void zzb() {
        super.zzb();
    }

    public final /* bridge */ /* synthetic */ void zzc() {
        super.zzc();
    }

    public final /* bridge */ /* synthetic */ void zzd() {
        super.zzd();
    }

    public final /* bridge */ /* synthetic */ zzac zzl() {
        return super.zzl();
    }

    public final /* bridge */ /* synthetic */ Clock zzm() {
        return super.zzm();
    }

    public final /* bridge */ /* synthetic */ Context zzn() {
        return super.zzn();
    }

    public final /* bridge */ /* synthetic */ zzeh zzo() {
        return super.zzo();
    }

    public final /* bridge */ /* synthetic */ zzjx zzp() {
        return super.zzp();
    }

    public final /* bridge */ /* synthetic */ zzfg zzq() {
        return super.zzq();
    }

    public final /* bridge */ /* synthetic */ zzej zzr() {
        return super.zzr();
    }

    public final /* bridge */ /* synthetic */ zzes zzs() {
        return super.zzs();
    }

    public final /* bridge */ /* synthetic */ zzs zzt() {
        return super.zzt();
    }

    public final /* bridge */ /* synthetic */ zzr zzu() {
        return super.zzu();
    }
}
