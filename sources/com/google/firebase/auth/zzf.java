package com.google.firebase.auth;

import android.net.Uri;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.firebase_auth.zzbd;
import com.google.android.gms.internal.firebase_auth.zzbe;
import java.util.Set;

/* compiled from: com.google.firebase:firebase-auth@@18.1.0 */
public final class zzf {
    private static final zzbe<String, Integer> zzg;
    private final String zza;
    private final String zzb;
    private final String zzc;
    @Nullable
    private final String zzd;
    @Nullable
    private final String zze;
    @Nullable
    private final String zzf;

    private zzf(String str) {
        String str2 = "apiKey";
        this.zza = zza(str, str2);
        String str3 = "oobCode";
        this.zzb = zza(str, str3);
        String str4 = "mode";
        this.zzc = zza(str, str4);
        if (this.zza == null || this.zzb == null || this.zzc == null) {
            throw new IllegalArgumentException(String.format("%s, %s and %s are required in a valid action code URL", new Object[]{str2, str3, str4}));
        }
        this.zzd = zza(str, "continueUrl");
        this.zze = zza(str, "languageCode");
        this.zzf = zza(str, "tenantId");
    }

    @Nullable
    public static zzf zza(@NonNull String str) {
        Preconditions.checkNotEmpty(str);
        try {
            return new zzf(str);
        } catch (IllegalArgumentException unused) {
            return null;
        }
    }

    public final int zza() {
        return ((Integer) zzg.getOrDefault(this.zzc, Integer.valueOf(3))).intValue();
    }

    @NonNull
    public final String zzb() {
        return this.zzb;
    }

    @Nullable
    public final String zzc() {
        return this.zzf;
    }

    private static String zza(String str, String str2) {
        String str3 = "link";
        Uri parse = Uri.parse(str);
        try {
            Set queryParameterNames = parse.getQueryParameterNames();
            if (queryParameterNames.contains(str2)) {
                return parse.getQueryParameter(str2);
            }
            if (queryParameterNames.contains(str3)) {
                return Uri.parse(parse.getQueryParameter(str3)).getQueryParameter(str2);
            }
            return null;
        } catch (UnsupportedOperationException unused) {
        }
    }

    static {
        String str = "resetPassword";
        String str2 = "signIn";
        String str3 = "verifyEmail";
        String str4 = "verifyBeforeChangeEmail";
        String str5 = "revertSecondFactorAddition";
        zzg = new zzbd().zza("recoverEmail", Integer.valueOf(2)).zza(str, Integer.valueOf(0)).zza(str2, Integer.valueOf(4)).zza(str3, Integer.valueOf(1)).zza(str4, Integer.valueOf(5)).zza(str5, Integer.valueOf(6)).zza();
    }
}
