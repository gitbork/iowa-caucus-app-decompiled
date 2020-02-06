package com.google.firebase.iid;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;
import androidx.annotation.GuardedBy;
import androidx.collection.ArrayMap;
import androidx.core.content.ContextCompat;
import java.io.File;
import java.io.IOException;
import java.util.Map;

final class zzav {
    private final Context zzag;
    private final SharedPreferences zzdc;
    private final zzz zzdd;
    @GuardedBy("this")
    private final Map<String, zzy> zzde;

    public zzav(Context context) {
        this(context, new zzz());
    }

    private zzav(Context context, zzz zzz) {
        String str = "FirebaseInstanceId";
        this.zzde = new ArrayMap();
        this.zzag = context;
        this.zzdc = context.getSharedPreferences("com.google.android.gms.appid", 0);
        this.zzdd = zzz;
        File file = new File(ContextCompat.getNoBackupFilesDir(this.zzag), "com.google.android.gms.appid-no-backup");
        if (!file.exists()) {
            try {
                if (file.createNewFile() && !isEmpty()) {
                    Log.i(str, "App restored, clearing state");
                    zzaj();
                    FirebaseInstanceId.getInstance().zzn();
                }
            } catch (IOException e) {
                if (Log.isLoggable(str, 3)) {
                    String str2 = "Error creating file in no backup dir: ";
                    String valueOf = String.valueOf(e.getMessage());
                    Log.d(str, valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
                }
            }
        }
    }

    public final synchronized String zzai() {
        return this.zzdc.getString("topic_operation_queue", "");
    }

    public final synchronized void zzf(String str) {
        this.zzdc.edit().putString("topic_operation_queue", str).apply();
    }

    private final synchronized boolean isEmpty() {
        return this.zzdc.getAll().isEmpty();
    }

    private static String zza(String str, String str2, String str3) {
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 4 + String.valueOf(str2).length() + String.valueOf(str3).length());
        sb.append(str);
        sb.append("|T|");
        sb.append(str2);
        sb.append("|");
        sb.append(str3);
        return sb.toString();
    }

    static String zzd(String str, String str2) {
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 3 + String.valueOf(str2).length());
        sb.append(str);
        sb.append("|S|");
        sb.append(str2);
        return sb.toString();
    }

    public final synchronized void zzaj() {
        this.zzde.clear();
        zzz.zza(this.zzag);
        this.zzdc.edit().clear().commit();
    }

    public final synchronized zzay zzb(String str, String str2, String str3) {
        return zzay.zzi(this.zzdc.getString(zza(str, str2, str3), null));
    }

    public final synchronized void zza(String str, String str2, String str3, String str4, String str5) {
        String zza = zzay.zza(str4, str5, System.currentTimeMillis());
        if (zza != null) {
            Editor edit = this.zzdc.edit();
            edit.putString(zza(str, str2, str3), zza);
            edit.commit();
        }
    }

    public final synchronized void zzc(String str, String str2, String str3) {
        String zza = zza(str, str2, str3);
        Editor edit = this.zzdc.edit();
        edit.remove(zza);
        edit.commit();
    }

    public final synchronized zzy zzg(String str) {
        zzy zzy;
        zzy zzy2 = (zzy) this.zzde.get(str);
        if (zzy2 != null) {
            return zzy2;
        }
        try {
            zzy = this.zzdd.zzb(this.zzag, str);
        } catch (zzaa unused) {
            Log.w("FirebaseInstanceId", "Stored data is corrupt, generating new identity");
            FirebaseInstanceId.getInstance().zzn();
            zzy = this.zzdd.zzc(this.zzag, str);
        }
        this.zzde.put(str, zzy);
        return zzy;
    }

    public final synchronized void zzh(String str) {
        String concat = String.valueOf(str).concat("|T|");
        Editor edit = this.zzdc.edit();
        for (String str2 : this.zzdc.getAll().keySet()) {
            if (str2.startsWith(concat)) {
                edit.remove(str2);
            }
        }
        edit.commit();
    }
}
