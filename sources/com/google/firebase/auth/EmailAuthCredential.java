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

@Class(creator = "EmailAuthCredentialCreator")
/* compiled from: com.google.firebase:firebase-auth@@18.1.0 */
public class EmailAuthCredential extends AuthCredential {
    public static final Creator<EmailAuthCredential> CREATOR = new zzj();
    @Field(getter = "getEmail", mo15127id = 1)
    private String zza;
    @Field(getter = "getPassword", mo15127id = 2)
    private String zzb;
    @Field(getter = "getSignInLink", mo15127id = 3)
    private final String zzc;
    @Field(getter = "getCachedState", mo15127id = 4)
    private String zzd;
    @Field(getter = "isForLinking", mo15127id = 5)
    private boolean zze;

    @Constructor
    EmailAuthCredential(@Param(mo15130id = 1) @NonNull String str, @Param(mo15130id = 2) @NonNull String str2, @Param(mo15130id = 3) @NonNull String str3, @Param(mo15130id = 4) @NonNull String str4, @Param(mo15130id = 5) @NonNull boolean z) {
        this.zza = Preconditions.checkNotEmpty(str);
        if (!TextUtils.isEmpty(str2) || !TextUtils.isEmpty(str3)) {
            this.zzb = str2;
            this.zzc = str3;
            this.zzd = str4;
            this.zze = z;
            return;
        }
        throw new IllegalArgumentException("Cannot create an EmailAuthCredential without a password or emailLink.");
    }

    @NonNull
    public String getProvider() {
        return "password";
    }

    EmailAuthCredential(String str, String str2) {
        this(str, str2, null, null, false);
    }

    @NonNull
    public final String zza() {
        return this.zza;
    }

    @NonNull
    public final String zzb() {
        return this.zzb;
    }

    @NonNull
    public final String zzc() {
        return this.zzc;
    }

    @Nullable
    public final String zzd() {
        return this.zzd;
    }

    public final boolean zze() {
        return this.zze;
    }

    public final EmailAuthCredential zza(@Nullable FirebaseUser firebaseUser) {
        this.zzd = firebaseUser.zzf();
        this.zze = true;
        return this;
    }

    public String getSignInMethod() {
        return !TextUtils.isEmpty(this.zzb) ? "password" : EmailAuthProvider.EMAIL_LINK_SIGN_IN_METHOD;
    }

    public final boolean zzf() {
        return !TextUtils.isEmpty(this.zzc);
    }

    public static boolean zza(@NonNull String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        zzf zza2 = zzf.zza(str);
        if (zza2 == null || zza2.zza() != 4) {
            return false;
        }
        return true;
    }

    public void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, this.zza, false);
        SafeParcelWriter.writeString(parcel, 2, this.zzb, false);
        SafeParcelWriter.writeString(parcel, 3, this.zzc, false);
        SafeParcelWriter.writeString(parcel, 4, this.zzd, false);
        SafeParcelWriter.writeBoolean(parcel, 5, this.zze);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
