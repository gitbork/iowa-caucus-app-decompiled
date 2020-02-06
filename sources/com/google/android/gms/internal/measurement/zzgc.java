package com.google.android.gms.internal.measurement;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.0.1 */
final class zzgc implements zzgk {
    private zzgk[] zza;

    zzgc(zzgk... zzgkArr) {
        this.zza = zzgkArr;
    }

    public final boolean zza(Class<?> cls) {
        for (zzgk zza2 : this.zza) {
            if (zza2.zza(cls)) {
                return true;
            }
        }
        return false;
    }

    public final zzgl zzb(Class<?> cls) {
        zzgk[] zzgkArr;
        for (zzgk zzgk : this.zza) {
            if (zzgk.zza(cls)) {
                return zzgk.zzb(cls);
            }
        }
        String str = "No factory is available for message type: ";
        String valueOf = String.valueOf(cls.getName());
        throw new UnsupportedOperationException(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
    }
}
