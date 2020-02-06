package com.google.firebase.auth;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;

@Class(creator = "PhoneAuthCredentialCreator")
/* compiled from: com.google.firebase:firebase-auth@@18.1.0 */
public class PhoneAuthCredential extends AuthCredential implements Cloneable {
    public static final Creator<PhoneAuthCredential> CREATOR = new zzad();
    @Field(getter = "getSessionInfo", mo15127id = 1)
    private String zza;
    @Field(getter = "getSmsCode", mo15127id = 2)
    private String zzb;
    @Field(getter = "getHasVerificationProof", mo15127id = 3)
    private boolean zzc;
    @Field(getter = "getPhoneNumber", mo15127id = 4)
    private String zzd;
    @Field(getter = "getAutoCreate", mo15127id = 5)
    private boolean zze;
    @Field(getter = "getTemporaryProof", mo15127id = 6)
    private String zzf;
    @Field(getter = "getMfaEnrollmentId", mo15127id = 7)
    @Nullable
    private String zzg;

    @Constructor
    PhoneAuthCredential(@Param(mo15130id = 1) @Nullable String str, @Param(mo15130id = 2) @Nullable String str2, @Param(mo15130id = 3) boolean z, @Param(mo15130id = 4) @Nullable String str3, @Param(mo15130id = 5) boolean z2, @Param(mo15130id = 6) @Nullable String str4, @Param(mo15130id = 7) @Nullable String str5) {
        Preconditions.checkArgument((z && !TextUtils.isEmpty(str3) && TextUtils.isEmpty(str5)) || (z && TextUtils.isEmpty(str3) && !TextUtils.isEmpty(str5)) || ((!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) || (!TextUtils.isEmpty(str3) && !TextUtils.isEmpty(str4))), "Cannot create PhoneAuthCredential without either verificationProof, sessionInfo, temporary proof, or enrollment ID.");
        this.zza = str;
        this.zzb = str2;
        this.zzc = z;
        this.zzd = str3;
        this.zze = z2;
        this.zzf = str4;
        this.zzg = str5;
    }

    @NonNull
    public String getProvider() {
        return "phone";
    }

    public String getSignInMethod() {
        return "phone";
    }

    public static PhoneAuthCredential zza(@NonNull String str, @NonNull String str2) {
        PhoneAuthCredential phoneAuthCredential = new PhoneAuthCredential(null, null, false, str, true, str2, null);
        return phoneAuthCredential;
    }

    public final String zza() {
        return this.zza;
    }

    @Nullable
    public String getSmsCode() {
        return this.zzb;
    }

    public final String zzb() {
        return this.zzd;
    }

    public final PhoneAuthCredential zza(boolean z) {
        this.zze = false;
        return this;
    }

    public final boolean zzc() {
        return this.zze;
    }

    public final String zzd() {
        return this.zzf;
    }

    public void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, this.zza, false);
        SafeParcelWriter.writeString(parcel, 2, getSmsCode(), false);
        SafeParcelWriter.writeBoolean(parcel, 3, this.zzc);
        SafeParcelWriter.writeString(parcel, 4, this.zzd, false);
        SafeParcelWriter.writeBoolean(parcel, 5, this.zze);
        SafeParcelWriter.writeString(parcel, 6, this.zzf, false);
        SafeParcelWriter.writeString(parcel, 7, this.zzg, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public /* synthetic */ Object clone() throws CloneNotSupportedException {
        PhoneAuthCredential phoneAuthCredential = new PhoneAuthCredential(this.zza, getSmsCode(), this.zzc, this.zzd, this.zze, this.zzf, this.zzg);
        return phoneAuthCredential;
    }
}
