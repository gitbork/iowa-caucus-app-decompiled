package com.google.firebase.auth.api.internal;

import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.util.Log;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.AndroidUtilsLight;
import com.google.android.gms.common.util.Hex;

/* compiled from: com.google.firebase:firebase-auth@@18.1.0 */
public final class zzfd {
    private final String zza;
    private final String zzb;

    public zzfd(Context context) {
        this(context, context.getPackageName());
    }

    private zzfd(Context context, String str) {
        String str2 = "FBA-PackageInfo";
        Preconditions.checkNotNull(context);
        this.zza = Preconditions.checkNotEmpty(str);
        try {
            byte[] packageCertificateHashBytes = AndroidUtilsLight.getPackageCertificateHashBytes(context, this.zza);
            if (packageCertificateHashBytes == null) {
                String str3 = "single cert required: ";
                String valueOf = String.valueOf(str);
                Log.e(str2, valueOf.length() != 0 ? str3.concat(valueOf) : new String(str3));
                this.zzb = null;
                return;
            }
            this.zzb = Hex.bytesToStringUppercase(packageCertificateHashBytes, false);
        } catch (NameNotFoundException unused) {
            String str4 = "no pkg: ";
            String valueOf2 = String.valueOf(str);
            Log.e(str2, valueOf2.length() != 0 ? str4.concat(valueOf2) : new String(str4));
            this.zzb = null;
        }
    }

    public final String zza() {
        return this.zza;
    }

    @Nullable
    public final String zzb() {
        return this.zzb;
    }
}
