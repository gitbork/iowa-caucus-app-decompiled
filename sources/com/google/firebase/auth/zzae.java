package com.google.firebase.auth;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.util.Log;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.internal.firebase_auth.zzbq;
import javax.annotation.Nullable;
import org.json.JSONException;
import org.json.JSONObject;

@Class(creator = "PhoneMultiFactorInfoCreator")
/* compiled from: com.google.firebase:firebase-auth@@18.1.0 */
public final class zzae extends zzy {
    public static final Creator<zzae> CREATOR = new zzaf();
    @Field(getter = "getUid", mo15127id = 1)
    private final String zza;
    @Field(getter = "getDisplayName", mo15127id = 2)
    @Nullable
    private final String zzb;
    @Field(getter = "getEnrollmentTimestamp", mo15127id = 3)
    private final long zzc;
    @Field(getter = "getPhoneNumber", mo15127id = 4)
    private final String zzd;

    @Constructor
    public zzae(@Param(mo15130id = 1) String str, @Param(mo15130id = 2) @Nullable String str2, @Param(mo15130id = 3) long j, @Param(mo15130id = 4) String str3) {
        this.zza = Preconditions.checkNotEmpty(str);
        this.zzb = str2;
        this.zzc = j;
        this.zzd = Preconditions.checkNotEmpty(str3);
    }

    public static zzae zza(JSONObject jSONObject) {
        String str = "enrollmentTimestamp";
        if (jSONObject.has(str)) {
            zzae zzae = new zzae(jSONObject.optString("uid"), jSONObject.optString("displayName"), jSONObject.optLong(str), jSONObject.optString("phoneNumber"));
            return zzae;
        }
        throw new IllegalArgumentException("An enrollment timestamp in seconds of UTC time since Unix epoch is required to build a PhoneMultiFactorInfo instance.");
    }

    @Nullable
    public final JSONObject zza() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.putOpt("factorIdKey", "phone");
            jSONObject.putOpt("uid", this.zza);
            jSONObject.putOpt("displayName", this.zzb);
            jSONObject.putOpt("enrollmentTimestamp", Long.valueOf(this.zzc));
            jSONObject.putOpt("phoneNumber", this.zzd);
            return jSONObject;
        } catch (JSONException e) {
            Log.d("PhoneMultiFactorInfo", "Failed to jsonify this object");
            throw new zzbq((Throwable) e);
        }
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, this.zza, false);
        SafeParcelWriter.writeString(parcel, 2, this.zzb, false);
        SafeParcelWriter.writeLong(parcel, 3, this.zzc);
        SafeParcelWriter.writeString(parcel, 4, this.zzd, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
