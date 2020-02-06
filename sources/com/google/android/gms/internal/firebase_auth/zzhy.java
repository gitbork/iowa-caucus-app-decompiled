package com.google.android.gms.internal.firebase_auth;

import com.google.android.gms.internal.firebase_auth.zzhx.zze;

/* compiled from: com.google.firebase:firebase-auth@@18.1.0 */
final class zzhy implements zzjh {
    private static final zzhy zza = new zzhy();

    private zzhy() {
    }

    public static zzhy zza() {
        return zza;
    }

    public final boolean zza(Class<?> cls) {
        return zzhx.class.isAssignableFrom(cls);
    }

    public final zzje zzb(Class<?> cls) {
        if (!zzhx.class.isAssignableFrom(cls)) {
            String str = "Unsupported message type: ";
            String valueOf = String.valueOf(cls.getName());
            throw new IllegalArgumentException(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
        }
        try {
            return (zzje) zzhx.zza(cls.asSubclass(zzhx.class)).zza(zze.zzc, (Object) null, (Object) null);
        } catch (Exception e) {
            String str2 = "Unable to get message info for ";
            String valueOf2 = String.valueOf(cls.getName());
            throw new RuntimeException(valueOf2.length() != 0 ? str2.concat(valueOf2) : new String(str2), e);
        }
    }
}
