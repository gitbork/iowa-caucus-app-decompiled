package com.google.firebase.auth.internal;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.exifinterface.media.ExifInterface;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.internal.firebase_auth.zzaz;
import com.google.android.gms.internal.firebase_auth.zzey;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuthProvider;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.FirebaseUserMetadata;
import com.google.firebase.auth.GetTokenResult;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.auth.zzg;
import com.google.firebase.auth.zzy;
import com.google.firebase.auth.zzz;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Class(creator = "DefaultFirebaseUserCreator")
/* compiled from: com.google.firebase:firebase-auth@@18.1.0 */
public class zzn extends FirebaseUser {
    public static final Creator<zzn> CREATOR = new zzq();
    @Field(getter = "getCachedTokenState", mo15127id = 1)
    private zzey zza;
    @Field(getter = "getDefaultAuthUserInfo", mo15127id = 2)
    private zzj zzb;
    @Field(getter = "getFirebaseAppName", mo15127id = 3)
    private String zzc;
    @Field(getter = "getUserType", mo15127id = 4)
    private String zzd;
    @Field(getter = "getUserInfos", mo15127id = 5)
    private List<zzj> zze;
    @Field(getter = "getProviders", mo15127id = 6)
    private List<String> zzf;
    @Field(getter = "getCurrentVersion", mo15127id = 7)
    private String zzg;
    @Field(getter = "isAnonymous", mo15127id = 8)
    private Boolean zzh;
    @Field(getter = "getMetadata", mo15127id = 9)
    private zzp zzi;
    @Field(getter = "isNewUser", mo15127id = 10)
    private boolean zzj;
    @Field(getter = "getDefaultOAuthCredential", mo15127id = 11)
    private zzg zzk;
    @Field(getter = "getMultiFactorInfoList", mo15127id = 12)
    private zzaq zzl;

    @Constructor
    zzn(@Param(mo15130id = 1) zzey zzey, @Param(mo15130id = 2) zzj zzj2, @Param(mo15130id = 3) String str, @Param(mo15130id = 4) String str2, @Param(mo15130id = 5) List<zzj> list, @Param(mo15130id = 6) List<String> list2, @Param(mo15130id = 7) String str3, @Param(mo15130id = 8) Boolean bool, @Param(mo15130id = 9) zzp zzp, @Param(mo15130id = 10) boolean z, @Param(mo15130id = 11) zzg zzg2, @Param(mo15130id = 12) zzaq zzaq) {
        this.zza = zzey;
        this.zzb = zzj2;
        this.zzc = str;
        this.zzd = str2;
        this.zze = list;
        this.zzf = list2;
        this.zzg = str3;
        this.zzh = bool;
        this.zzi = zzp;
        this.zzj = z;
        this.zzk = zzg2;
        this.zzl = zzaq;
    }

    public zzn(FirebaseApp firebaseApp, List<? extends UserInfo> list) {
        Preconditions.checkNotNull(firebaseApp);
        this.zzc = firebaseApp.getName();
        this.zzd = "com.google.firebase.auth.internal.DefaultFirebaseUser";
        this.zzg = ExifInterface.GPS_MEASUREMENT_2D;
        zza(list);
    }

    @Nullable
    public String getDisplayName() {
        return this.zzb.getDisplayName();
    }

    @Nullable
    public Uri getPhotoUrl() {
        return this.zzb.getPhotoUrl();
    }

    @Nullable
    public String getEmail() {
        return this.zzb.getEmail();
    }

    @Nullable
    public String getPhoneNumber() {
        return this.zzb.getPhoneNumber();
    }

    @Nullable
    public final String zzd() {
        zzey zzey = this.zza;
        if (zzey == null || zzey.zzd() == null) {
            return null;
        }
        Map map = (Map) zzap.zza(this.zza.zzd()).getClaims().get(FirebaseAuthProvider.PROVIDER_ID);
        if (map != null) {
            return (String) map.get("tenant");
        }
        return null;
    }

    public final zzn zza(String str) {
        this.zzg = str;
        return this;
    }

    @NonNull
    public String getProviderId() {
        return this.zzb.getProviderId();
    }

    @NonNull
    public final FirebaseApp zzc() {
        return FirebaseApp.getInstance(this.zzc);
    }

    public final List<zzj> zzi() {
        return this.zze;
    }

    @NonNull
    public String getUid() {
        return this.zzb.getUid();
    }

    public boolean isAnonymous() {
        Boolean bool = this.zzh;
        if (bool == null || bool.booleanValue()) {
            zzey zzey = this.zza;
            String str = "";
            if (zzey != null) {
                GetTokenResult zza2 = zzap.zza(zzey.zzd());
                if (zza2 != null) {
                    str = zza2.getSignInProvider();
                }
            }
            boolean z = true;
            if (getProviderData().size() > 1 || (str != null && str.equals("custom"))) {
                z = false;
            }
            this.zzh = Boolean.valueOf(z);
        }
        return this.zzh.booleanValue();
    }

    @Nullable
    public final List<String> zza() {
        return this.zzf;
    }

    @NonNull
    public final FirebaseUser zza(List<? extends UserInfo> list) {
        Preconditions.checkNotNull(list);
        this.zze = new ArrayList(list.size());
        this.zzf = new ArrayList(list.size());
        for (int i = 0; i < list.size(); i++) {
            UserInfo userInfo = (UserInfo) list.get(i);
            if (userInfo.getProviderId().equals(FirebaseAuthProvider.PROVIDER_ID)) {
                this.zzb = (zzj) userInfo;
            } else {
                this.zzf.add(userInfo.getProviderId());
            }
            this.zze.add((zzj) userInfo);
        }
        if (this.zzb == null) {
            this.zzb = (zzj) this.zze.get(0);
        }
        return this;
    }

    @NonNull
    public List<? extends UserInfo> getProviderData() {
        return this.zze;
    }

    @NonNull
    public final zzey zze() {
        return this.zza;
    }

    @NonNull
    public final String zzg() {
        return zze().zzd();
    }

    @NonNull
    public final String zzf() {
        return this.zza.zzh();
    }

    public final void zza(zzey zzey) {
        this.zza = (zzey) Preconditions.checkNotNull(zzey);
    }

    public boolean isEmailVerified() {
        return this.zzb.isEmailVerified();
    }

    public final void zza(zzp zzp) {
        this.zzi = zzp;
    }

    public FirebaseUserMetadata getMetadata() {
        return this.zzi;
    }

    public final void zza(boolean z) {
        this.zzj = z;
    }

    public final boolean zzj() {
        return this.zzj;
    }

    public final void zza(zzg zzg2) {
        this.zzk = zzg2;
    }

    @Nullable
    public final zzg zzk() {
        return this.zzk;
    }

    public final void zzb(List<zzy> list) {
        this.zzl = zzaq.zza(list);
    }

    @Nullable
    public final List<zzy> zzl() {
        zzaq zzaq = this.zzl;
        if (zzaq != null) {
            return zzaq.zza();
        }
        return zzaz.zza();
    }

    public void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, zze(), i, false);
        SafeParcelWriter.writeParcelable(parcel, 2, this.zzb, i, false);
        SafeParcelWriter.writeString(parcel, 3, this.zzc, false);
        SafeParcelWriter.writeString(parcel, 4, this.zzd, false);
        SafeParcelWriter.writeTypedList(parcel, 5, this.zze, false);
        SafeParcelWriter.writeStringList(parcel, 6, zza(), false);
        SafeParcelWriter.writeString(parcel, 7, this.zzg, false);
        SafeParcelWriter.writeBooleanObject(parcel, 8, Boolean.valueOf(isAnonymous()), false);
        SafeParcelWriter.writeParcelable(parcel, 9, getMetadata(), i, false);
        SafeParcelWriter.writeBoolean(parcel, 10, this.zzj);
        SafeParcelWriter.writeParcelable(parcel, 11, this.zzk, i, false);
        SafeParcelWriter.writeParcelable(parcel, 12, this.zzl, i, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public static FirebaseUser zza(FirebaseApp firebaseApp, FirebaseUser firebaseUser) {
        zzn zzn = new zzn(firebaseApp, firebaseUser.getProviderData());
        if (firebaseUser instanceof zzn) {
            zzn zzn2 = (zzn) firebaseUser;
            zzn.zzg = zzn2.zzg;
            zzn.zzd = zzn2.zzd;
            zzn.zzi = (zzp) zzn2.getMetadata();
        } else {
            zzn.zzi = null;
        }
        if (firebaseUser.zze() != null) {
            zzn.zza(firebaseUser.zze());
        }
        if (!firebaseUser.isAnonymous()) {
            zzn.zzb();
        }
        return zzn;
    }

    public final /* synthetic */ zzz zzh() {
        return new zzr(this);
    }

    public final /* synthetic */ FirebaseUser zzb() {
        this.zzh = Boolean.valueOf(false);
        return this;
    }
}
