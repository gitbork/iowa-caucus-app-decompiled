package com.google.firebase.auth.api.internal;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.internal.firebase_auth.zza;
import com.google.android.gms.internal.firebase_auth.zzd;
import com.google.android.gms.internal.firebase_auth.zzeb;
import com.google.android.gms.internal.firebase_auth.zzed;
import com.google.android.gms.internal.firebase_auth.zzei;
import com.google.android.gms.internal.firebase_auth.zzes;
import com.google.android.gms.internal.firebase_auth.zzey;
import com.google.android.gms.internal.firebase_auth.zzff;
import com.google.firebase.auth.PhoneAuthCredential;

/* compiled from: com.google.firebase:firebase-auth@@18.1.0 */
public abstract class zzec extends zza implements zzdz {
    public zzec() {
        super("com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks");
    }

    /* access modifiers changed from: protected */
    public final boolean zza(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        switch (i) {
            case 1:
                zza((zzey) zzd.zza(parcel, zzey.CREATOR));
                break;
            case 2:
                zza((zzey) zzd.zza(parcel, zzey.CREATOR), (zzes) zzd.zza(parcel, zzes.CREATOR));
                break;
            case 3:
                zza((zzei) zzd.zza(parcel, zzei.CREATOR));
                break;
            case 4:
                zza((zzff) zzd.zza(parcel, zzff.CREATOR));
                break;
            case 5:
                zza((Status) zzd.zza(parcel, Status.CREATOR));
                break;
            case 6:
                mo26569a_();
                break;
            case 7:
                zzb();
                break;
            case 8:
                zza(parcel.readString());
                break;
            case 9:
                zzb(parcel.readString());
                break;
            case 10:
                zza((PhoneAuthCredential) zzd.zza(parcel, PhoneAuthCredential.CREATOR));
                break;
            case 11:
                zzc(parcel.readString());
                break;
            case 12:
                zza((Status) zzd.zza(parcel, Status.CREATOR), (PhoneAuthCredential) zzd.zza(parcel, PhoneAuthCredential.CREATOR));
                break;
            case 13:
                zzc();
                break;
            case 14:
                zza((zzeb) zzd.zza(parcel, zzeb.CREATOR));
                break;
            case 15:
                zza((zzed) zzd.zza(parcel, zzed.CREATOR));
                break;
            default:
                return false;
        }
        return true;
    }
}
