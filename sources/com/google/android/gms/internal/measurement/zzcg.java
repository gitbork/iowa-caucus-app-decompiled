package com.google.android.gms.internal.measurement;

import android.content.Context;
import android.database.ContentObserver;
import android.util.Log;
import androidx.annotation.GuardedBy;
import androidx.core.content.PermissionChecker;
import javax.annotation.Nullable;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.0.1 */
final class zzcg implements zzcb {
    @GuardedBy("GservicesLoader.class")
    private static zzcg zza;
    @Nullable
    private final Context zzb;
    @Nullable
    private final ContentObserver zzc;

    static zzcg zza(Context context) {
        zzcg zzcg;
        synchronized (zzcg.class) {
            if (zza == null) {
                zza = PermissionChecker.checkSelfPermission(context, "com.google.android.providers.gsf.permission.READ_GSERVICES") == 0 ? new zzcg(context) : new zzcg();
            }
            zzcg = zza;
        }
        return zzcg;
    }

    private zzcg(Context context) {
        this.zzb = context;
        this.zzc = new zzci(this, null);
        context.getContentResolver().registerContentObserver(zzbw.zza, true, this.zzc);
    }

    private zzcg() {
        this.zzb = null;
        this.zzc = null;
    }

    /* access modifiers changed from: private */
    /* renamed from: zzc */
    public final String zza(String str) {
        if (this.zzb == null) {
            return null;
        }
        try {
            return (String) zzce.zza(new zzcf(this, str));
        } catch (SecurityException e) {
            String str2 = "Unable to read GServices for: ";
            String valueOf = String.valueOf(str);
            Log.e("GservicesLoader", valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2), e);
            return null;
        }
    }

    static synchronized void zza() {
        synchronized (zzcg.class) {
            if (!(zza == null || zza.zzb == null || zza.zzc == null)) {
                zza.zzb.getContentResolver().unregisterContentObserver(zza.zzc);
            }
            zza = null;
        }
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ String zzb(String str) {
        return zzbw.zza(this.zzb.getContentResolver(), str, (String) null);
    }
}
