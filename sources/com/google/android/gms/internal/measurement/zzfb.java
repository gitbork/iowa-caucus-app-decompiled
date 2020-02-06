package com.google.android.gms.internal.measurement;

import com.google.android.gms.internal.measurement.zzfd.zzd;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.0.1 */
final class zzfb implements zzgk {
    private static final zzfb zza = new zzfb();

    private zzfb() {
    }

    public static zzfb zza() {
        return zza;
    }

    public final boolean zza(Class<?> cls) {
        return zzfd.class.isAssignableFrom(cls);
    }

    public final zzgl zzb(Class<?> cls) {
        if (!zzfd.class.isAssignableFrom(cls)) {
            String str = "Unsupported message type: ";
            String valueOf = String.valueOf(cls.getName());
            throw new IllegalArgumentException(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
        }
        try {
            return (zzgl) zzfd.zza(cls.asSubclass(zzfd.class)).zza(zzd.zzc, (Object) null, (Object) null);
        } catch (Exception e) {
            String str2 = "Unable to get message info for ";
            String valueOf2 = String.valueOf(cls.getName());
            throw new RuntimeException(valueOf2.length() != 0 ? str2.concat(valueOf2) : new String(str2), e);
        }
    }
}
