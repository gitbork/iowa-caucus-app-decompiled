package com.google.firebase.auth.internal;

import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.collection.ArrayMap;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.logging.Logger;
import com.google.android.gms.common.util.Base64Utils;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.firebase_auth.zzan;
import com.google.android.gms.internal.firebase_auth.zzbq;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: com.google.firebase:firebase-auth@@18.1.0 */
final class zzao {
    private static final Logger zza = new Logger("JSONParser", new String[0]);

    @NonNull
    public static Map<String, Object> zza(String str) {
        Preconditions.checkNotEmpty(str);
        List zza2 = zzan.zza('.').zza((CharSequence) str);
        if (zza2.size() < 2) {
            Logger logger = zza;
            String str2 = "Invalid idToken ";
            String valueOf = String.valueOf(str);
            logger.mo15179e(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2), new Object[0]);
            return Collections.EMPTY_MAP;
        }
        try {
            Map<String, Object> zzb = zzb(new String(Base64Utils.decodeUrlSafeNoPadding((String) zza2.get(1)), "UTF-8"));
            if (zzb == null) {
                zzb = Collections.EMPTY_MAP;
            }
            return zzb;
        } catch (UnsupportedEncodingException e) {
            zza.mo15178e("Unable to decode token", e, new Object[0]);
            return Collections.EMPTY_MAP;
        }
    }

    @Nullable
    public static Map<String, Object> zzb(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject != JSONObject.NULL) {
                return zza(jSONObject);
            }
            return null;
        } catch (Exception e) {
            Log.d("JSONParser", "Failed to parse JSONObject into Map.");
            throw new zzbq((Throwable) e);
        }
    }

    @VisibleForTesting
    private static Map<String, Object> zza(JSONObject jSONObject) throws JSONException {
        ArrayMap arrayMap = new ArrayMap();
        Iterator keys = jSONObject.keys();
        while (keys.hasNext()) {
            String str = (String) keys.next();
            Object obj = jSONObject.get(str);
            if (obj instanceof JSONArray) {
                obj = zza((JSONArray) obj);
            } else if (obj instanceof JSONObject) {
                obj = zza((JSONObject) obj);
            }
            arrayMap.put(str, obj);
        }
        return arrayMap;
    }

    @VisibleForTesting
    private static List<Object> zza(JSONArray jSONArray) throws JSONException {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < jSONArray.length(); i++) {
            Object obj = jSONArray.get(i);
            if (obj instanceof JSONArray) {
                obj = zza((JSONArray) obj);
            } else if (obj instanceof JSONObject) {
                obj = zza((JSONObject) obj);
            }
            arrayList.add(obj);
        }
        return arrayList;
    }
}
