package com.google.firebase.auth.internal;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import androidx.annotation.Nullable;
import androidx.exifinterface.media.ExifInterface;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.logging.Logger;
import com.google.android.gms.internal.firebase_auth.zzbq;
import com.google.android.gms.internal.firebase_auth.zzey;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.zzae;
import com.google.firebase.auth.zzy;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: com.google.firebase:firebase-auth@@18.1.0 */
public final class zzav {
    private Context zza;
    private String zzb;
    private SharedPreferences zzc = this.zza.getSharedPreferences(String.format("com.google.firebase.auth.api.Store.%s", new Object[]{this.zzb}), 0);
    private Logger zzd = new Logger("StorageHelpers", new String[0]);

    public zzav(Context context, String str) {
        Preconditions.checkNotNull(context);
        this.zzb = Preconditions.checkNotEmpty(str);
        this.zza = context.getApplicationContext();
    }

    public final void zza(FirebaseUser firebaseUser) {
        Preconditions.checkNotNull(firebaseUser);
        String zzc2 = zzc(firebaseUser);
        if (!TextUtils.isEmpty(zzc2)) {
            this.zzc.edit().putString("com.google.firebase.auth.FIREBASE_USER", zzc2).apply();
        }
    }

    @Nullable
    public final FirebaseUser zza() {
        String str = "type";
        String string = this.zzc.getString("com.google.firebase.auth.FIREBASE_USER", null);
        if (TextUtils.isEmpty(string)) {
            return null;
        }
        try {
            JSONObject jSONObject = new JSONObject(string);
            if (jSONObject.has(str)) {
                if ("com.google.firebase.auth.internal.DefaultFirebaseUser".equalsIgnoreCase(jSONObject.optString(str))) {
                    return zza(jSONObject);
                }
            }
        } catch (Exception unused) {
        }
        return null;
    }

    public final void zza(FirebaseUser firebaseUser, zzey zzey) {
        Preconditions.checkNotNull(firebaseUser);
        Preconditions.checkNotNull(zzey);
        this.zzc.edit().putString(String.format("com.google.firebase.auth.GET_TOKEN_RESPONSE.%s", new Object[]{firebaseUser.getUid()}), zzey.zzh()).apply();
    }

    public final zzey zzb(FirebaseUser firebaseUser) {
        Preconditions.checkNotNull(firebaseUser);
        String string = this.zzc.getString(String.format("com.google.firebase.auth.GET_TOKEN_RESPONSE.%s", new Object[]{firebaseUser.getUid()}), null);
        if (string != null) {
            return zzey.zzb(string);
        }
        return null;
    }

    public final void zza(String str) {
        this.zzc.edit().remove(str).apply();
    }

    @Nullable
    private final String zzc(FirebaseUser firebaseUser) {
        JSONObject jSONObject = new JSONObject();
        if (!zzn.class.isAssignableFrom(firebaseUser.getClass())) {
            return null;
        }
        zzn zzn = (zzn) firebaseUser;
        try {
            jSONObject.put("cachedTokenState", zzn.zzf());
            jSONObject.put("applicationName", zzn.zzc().getName());
            jSONObject.put("type", "com.google.firebase.auth.internal.DefaultFirebaseUser");
            if (zzn.zzi() != null) {
                JSONArray jSONArray = new JSONArray();
                List zzi = zzn.zzi();
                for (int i = 0; i < zzi.size(); i++) {
                    jSONArray.put(((zzj) zzi.get(i)).zzb());
                }
                jSONObject.put("userInfos", jSONArray);
            }
            jSONObject.put("anonymous", zzn.isAnonymous());
            jSONObject.put("version", ExifInterface.GPS_MEASUREMENT_2D);
            if (zzn.getMetadata() != null) {
                jSONObject.put("userMetadata", ((zzp) zzn.getMetadata()).zza());
            }
            List zza2 = ((zzr) zzn.zzh()).zza();
            if (zza2 != null && !zza2.isEmpty()) {
                JSONArray jSONArray2 = new JSONArray();
                for (int i2 = 0; i2 < zza2.size(); i2++) {
                    jSONArray2.put(((zzy) zza2.get(i2)).zza());
                }
                jSONObject.put("userMultiFactorInfo", jSONArray2);
            }
            return jSONObject.toString();
        } catch (Exception e) {
            this.zzd.wtf("Failed to turn object into JSON", e, new Object[0]);
            throw new zzbq((Throwable) e);
        }
    }

    private final zzn zza(JSONObject jSONObject) {
        String str = "userMultiFactorInfo";
        String str2 = "userMetadata";
        try {
            String string = jSONObject.getString("cachedTokenState");
            String string2 = jSONObject.getString("applicationName");
            boolean z = jSONObject.getBoolean("anonymous");
            String str3 = ExifInterface.GPS_MEASUREMENT_2D;
            String string3 = jSONObject.getString("version");
            if (string3 != null) {
                str3 = string3;
            }
            JSONArray jSONArray = jSONObject.getJSONArray("userInfos");
            int length = jSONArray.length();
            ArrayList arrayList = new ArrayList(length);
            for (int i = 0; i < length; i++) {
                arrayList.add(zzj.zza(jSONArray.getString(i)));
            }
            zzn zzn = new zzn(FirebaseApp.getInstance(string2), arrayList);
            if (!TextUtils.isEmpty(string)) {
                zzn.zza(zzey.zzb(string));
            }
            if (!z) {
                zzn.zzb();
            }
            zzn.zza(str3);
            if (jSONObject.has(str2)) {
                zzp zza2 = zzp.zza(jSONObject.getJSONObject(str2));
                if (zza2 != null) {
                    zzn.zza(zza2);
                }
            }
            if (jSONObject.has(str)) {
                JSONArray jSONArray2 = jSONObject.getJSONArray(str);
                if (jSONArray2 != null) {
                    ArrayList arrayList2 = new ArrayList();
                    for (int i2 = 0; i2 < jSONArray2.length(); i2++) {
                        JSONObject jSONObject2 = new JSONObject(jSONArray2.getString(i2));
                        arrayList2.add("phone".equals(jSONObject2.optString("factorIdKey")) ? zzae.zza(jSONObject2) : null);
                    }
                    zzn.zzb(arrayList2);
                }
            }
            return zzn;
        } catch (zzbq | ArrayIndexOutOfBoundsException | IllegalArgumentException | JSONException e) {
            this.zzd.wtf(e);
            return null;
        }
    }
}
