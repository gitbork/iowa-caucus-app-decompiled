package com.google.android.gms.internal.measurement;

import android.content.Context;
import android.os.Build;
import android.os.StrictMode;
import android.os.StrictMode.ThreadPolicy;
import android.util.Log;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.0.1 */
final class zzck {
    zzck() {
    }

    static zzch zza(Context context) {
        String str = Build.TYPE;
        String str2 = Build.TAGS;
        String str3 = Build.HARDWARE;
        boolean z = false;
        if ((str.equals("eng") || str.equals("userdebug")) && ((str3.equals("goldfish") || str3.equals("ranchu") || str3.equals("robolectric")) && (str2.contains("dev-keys") || str2.contains("test-keys")))) {
            z = true;
        }
        if (!z) {
            return zzch.zza();
        }
        if (zzby.zza() && !context.isDeviceProtectedStorage()) {
            context = context.createDeviceProtectedStorageContext();
        }
        zzcy zzb = zzb(context);
        if (zzb.zza()) {
            return zza((File) zzb.zzb());
        }
        return zzch.zza();
    }

    private static zzcy<File> zzb(Context context) {
        ThreadPolicy allowThreadDiskReads = StrictMode.allowThreadDiskReads();
        try {
            StrictMode.allowThreadDiskWrites();
            File fileStreamPath = context.getFileStreamPath("hermetic_phenotype_overrides.txt");
            return fileStreamPath.exists() ? zzcy.zza(fileStreamPath) : zzcy.zzc();
        } catch (RuntimeException e) {
            Log.e("HermeticFileOverrides", "no data dir", e);
            return zzcy.zzc();
        } finally {
            StrictMode.setThreadPolicy(allowThreadDiskReads);
        }
    }

    private static zzch zza(File file) {
        BufferedReader bufferedReader;
        Throwable th;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            HashMap hashMap = new HashMap();
            while (true) {
                String readLine = bufferedReader.readLine();
                String str = "HermeticFileOverrides";
                if (readLine != null) {
                    String[] split = readLine.split(" ", 3);
                    if (split.length != 3) {
                        String str2 = "Invalid: ";
                        String valueOf = String.valueOf(readLine);
                        Log.e(str, valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
                    } else {
                        String str3 = split[0];
                        String str4 = split[1];
                        String str5 = split[2];
                        if (!hashMap.containsKey(str3)) {
                            hashMap.put(str3, new HashMap());
                        }
                        ((Map) hashMap.get(str3)).put(str4, str5);
                    }
                } else {
                    String valueOf2 = String.valueOf(file);
                    StringBuilder sb = new StringBuilder(String.valueOf(valueOf2).length() + 7);
                    sb.append("Parsed ");
                    sb.append(valueOf2);
                    Log.i(str, sb.toString());
                    zzch zzch = new zzch(hashMap);
                    bufferedReader.close();
                    return zzch;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (Throwable th2) {
            zzdg.zza(th, th2);
        }
        throw th;
    }
}
