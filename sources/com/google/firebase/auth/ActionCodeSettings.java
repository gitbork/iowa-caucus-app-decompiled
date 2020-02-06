package com.google.firebase.auth;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.internal.firebase_auth.zzgd;

@Class(creator = "ActionCodeSettingsCreator")
/* compiled from: com.google.firebase:firebase-auth@@18.1.0 */
public class ActionCodeSettings extends AbstractSafeParcelable {
    public static final Creator<ActionCodeSettings> CREATOR = new zze();
    @Field(getter = "getUrl", mo15127id = 1)
    private final String zza;
    @Field(getter = "getIOSBundle", mo15127id = 2)
    private final String zzb;
    @Field(getter = "getIOSAppStoreId", mo15127id = 3)
    private final String zzc;
    @Field(getter = "getAndroidPackageName", mo15127id = 4)
    private final String zzd;
    @Field(getter = "getAndroidInstallApp", mo15127id = 5)
    private final boolean zze;
    @Field(getter = "getAndroidMinimumVersion", mo15127id = 6)
    private final String zzf;
    @Field(getter = "canHandleCodeInApp", mo15127id = 7)
    private final boolean zzg;
    @Field(getter = "getLocaleHeader", mo15127id = 8)
    private String zzh;
    @Field(getter = "getRequestType", mo15127id = 9)
    private int zzi;
    @Field(getter = "getDynamicLinkDomain", mo15127id = 10)
    private String zzj;

    /* compiled from: com.google.firebase:firebase-auth@@18.1.0 */
    public static class Builder {
        /* access modifiers changed from: private */
        public String zza;
        /* access modifiers changed from: private */
        public String zzb;
        /* access modifiers changed from: private */
        public String zzc;
        /* access modifiers changed from: private */
        public boolean zzd;
        /* access modifiers changed from: private */
        public String zze;
        /* access modifiers changed from: private */
        public boolean zzf;
        /* access modifiers changed from: private */
        public String zzg;

        private Builder() {
            this.zzf = false;
        }

        public Builder setUrl(@NonNull String str) {
            this.zza = str;
            return this;
        }

        public Builder setIOSBundleId(@NonNull String str) {
            this.zzb = str;
            return this;
        }

        public Builder setAndroidPackageName(@NonNull String str, boolean z, @Nullable String str2) {
            this.zzc = str;
            this.zzd = z;
            this.zze = str2;
            return this;
        }

        public Builder setHandleCodeInApp(boolean z) {
            this.zzf = z;
            return this;
        }

        public Builder setDynamicLinkDomain(String str) {
            this.zzg = str;
            return this;
        }

        public ActionCodeSettings build() {
            if (this.zza != null) {
                return new ActionCodeSettings(this);
            }
            throw new IllegalArgumentException("Cannot build ActionCodeSettings with null URL. Call #setUrl(String) before calling build()");
        }
    }

    @Constructor
    ActionCodeSettings(@Param(mo15130id = 1) String str, @Param(mo15130id = 2) String str2, @Param(mo15130id = 3) String str3, @Param(mo15130id = 4) String str4, @Param(mo15130id = 5) boolean z, @Param(mo15130id = 6) String str5, @Param(mo15130id = 7) boolean z2, @Param(mo15130id = 8) String str6, @Param(mo15130id = 9) int i, @Param(mo15130id = 10) String str7) {
        this.zza = str;
        this.zzb = str2;
        this.zzc = str3;
        this.zzd = str4;
        this.zze = z;
        this.zzf = str5;
        this.zzg = z2;
        this.zzh = str6;
        this.zzi = i;
        this.zzj = str7;
    }

    private ActionCodeSettings(Builder builder) {
        this.zza = builder.zza;
        this.zzb = builder.zzb;
        this.zzc = null;
        this.zzd = builder.zzc;
        this.zze = builder.zzd;
        this.zzf = builder.zze;
        this.zzg = builder.zzf;
        this.zzj = builder.zzg;
    }

    public static ActionCodeSettings zza() {
        return new ActionCodeSettings(new Builder());
    }

    public String getUrl() {
        return this.zza;
    }

    public String getIOSBundle() {
        return this.zzb;
    }

    public final String zzb() {
        return this.zzc;
    }

    public String getAndroidPackageName() {
        return this.zzd;
    }

    public boolean getAndroidInstallApp() {
        return this.zze;
    }

    public String getAndroidMinimumVersion() {
        return this.zzf;
    }

    public boolean canHandleCodeInApp() {
        return this.zzg;
    }

    public final void zza(@NonNull String str) {
        this.zzh = str;
    }

    public final String zzc() {
        return this.zzh;
    }

    public final void zza(@NonNull zzgd zzgd) {
        this.zzi = zzgd.zza();
    }

    public final int zzd() {
        return this.zzi;
    }

    public final String zze() {
        return this.zzj;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, getUrl(), false);
        SafeParcelWriter.writeString(parcel, 2, getIOSBundle(), false);
        SafeParcelWriter.writeString(parcel, 3, this.zzc, false);
        SafeParcelWriter.writeString(parcel, 4, getAndroidPackageName(), false);
        SafeParcelWriter.writeBoolean(parcel, 5, getAndroidInstallApp());
        SafeParcelWriter.writeString(parcel, 6, getAndroidMinimumVersion(), false);
        SafeParcelWriter.writeBoolean(parcel, 7, canHandleCodeInApp());
        SafeParcelWriter.writeString(parcel, 8, this.zzh, false);
        SafeParcelWriter.writeInt(parcel, 9, this.zzi);
        SafeParcelWriter.writeString(parcel, 10, this.zzj, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
