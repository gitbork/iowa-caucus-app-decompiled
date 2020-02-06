package com.google.firebase.auth.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.firebase.auth.AdditionalUserInfo;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.zzg;
import java.util.List;

@Class(creator = "DefaultAuthResultCreator")
/* compiled from: com.google.firebase:firebase-auth@@18.1.0 */
public final class zzh implements AuthResult {
    public static final Creator<zzh> CREATOR = new zzk();
    @Field(getter = "getUser", mo15127id = 1)
    private zzn zza;
    @Field(getter = "getAdditionalUserInfo", mo15127id = 2)
    private zzf zzb;
    @Field(getter = "getOAuthCredential", mo15127id = 3)
    private zzg zzc;

    @Constructor
    zzh(@Param(mo15130id = 1) zzn zzn, @Param(mo15130id = 2) zzf zzf, @Param(mo15130id = 3) zzg zzg) {
        this.zza = zzn;
        this.zzb = zzf;
        this.zzc = zzg;
    }

    public final int describeContents() {
        return 0;
    }

    public zzh(zzn zzn) {
        this.zza = (zzn) Preconditions.checkNotNull(zzn);
        List zzi = this.zza.zzi();
        this.zzb = null;
        for (int i = 0; i < zzi.size(); i++) {
            if (!TextUtils.isEmpty(((zzj) zzi.get(i)).zza())) {
                this.zzb = new zzf(((zzj) zzi.get(i)).getProviderId(), ((zzj) zzi.get(i)).zza(), zzn.zzj());
            }
        }
        if (this.zzb == null) {
            this.zzb = new zzf(zzn.zzj());
        }
        this.zzc = zzn.zzk();
    }

    @Nullable
    public final FirebaseUser getUser() {
        return this.zza;
    }

    @Nullable
    public final AdditionalUserInfo getAdditionalUserInfo() {
        return this.zzb;
    }

    public final AuthCredential getCredential() {
        return this.zzc;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, getUser(), i, false);
        SafeParcelWriter.writeParcelable(parcel, 2, getAdditionalUserInfo(), i, false);
        SafeParcelWriter.writeParcelable(parcel, 3, this.zzc, i, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
