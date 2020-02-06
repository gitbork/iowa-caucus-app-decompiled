package com.google.android.gms.internal.firebase_auth;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.firebase.auth.ActionCodeSettings;

@Class(creator = "SendEmailVerificationWithSettingsAidlRequestCreator")
/* compiled from: com.google.firebase:firebase-auth@@18.1.0 */
public final class zzct extends AbstractSafeParcelable {
    public static final Creator<zzct> CREATOR = new zzcw();
    @Field(getter = "getToken", mo15127id = 1)
    private final String zza;
    @Field(getter = "getActionCodeSettings", mo15127id = 2)
    @Nullable
    private final ActionCodeSettings zzb;

    @Constructor
    public zzct(@Param(mo15130id = 1) String str, @Param(mo15130id = 2) @Nullable ActionCodeSettings actionCodeSettings) {
        this.zza = str;
        this.zzb = actionCodeSettings;
    }

    public final String zza() {
        return this.zza;
    }

    @Nullable
    public final ActionCodeSettings zzb() {
        return this.zzb;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, this.zza, false);
        SafeParcelWriter.writeParcelable(parcel, 2, this.zzb, i, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
