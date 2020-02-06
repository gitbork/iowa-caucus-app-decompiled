package com.google.firebase.auth.api.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.drew.metadata.exif.makernotes.NikonType2MakernoteDirectory;
import com.drew.metadata.exif.makernotes.PanasonicMakernoteDirectory;
import com.google.android.gms.internal.firebase_auth.zza;
import com.google.android.gms.internal.firebase_auth.zzbp;
import com.google.android.gms.internal.firebase_auth.zzbr;
import com.google.android.gms.internal.firebase_auth.zzbt;
import com.google.android.gms.internal.firebase_auth.zzbv;
import com.google.android.gms.internal.firebase_auth.zzbx;
import com.google.android.gms.internal.firebase_auth.zzbz;
import com.google.android.gms.internal.firebase_auth.zzcb;
import com.google.android.gms.internal.firebase_auth.zzcd;
import com.google.android.gms.internal.firebase_auth.zzcf;
import com.google.android.gms.internal.firebase_auth.zzch;
import com.google.android.gms.internal.firebase_auth.zzcj;
import com.google.android.gms.internal.firebase_auth.zzcl;
import com.google.android.gms.internal.firebase_auth.zzcn;
import com.google.android.gms.internal.firebase_auth.zzcp;
import com.google.android.gms.internal.firebase_auth.zzcr;
import com.google.android.gms.internal.firebase_auth.zzct;
import com.google.android.gms.internal.firebase_auth.zzcv;
import com.google.android.gms.internal.firebase_auth.zzcx;
import com.google.android.gms.internal.firebase_auth.zzcz;
import com.google.android.gms.internal.firebase_auth.zzd;
import com.google.android.gms.internal.firebase_auth.zzdb;
import com.google.android.gms.internal.firebase_auth.zzdd;
import com.google.android.gms.internal.firebase_auth.zzdf;
import com.google.android.gms.internal.firebase_auth.zzdh;
import com.google.android.gms.internal.firebase_auth.zzdj;
import com.google.android.gms.internal.firebase_auth.zzdl;
import com.google.android.gms.internal.firebase_auth.zzdn;
import com.google.android.gms.internal.firebase_auth.zzdp;
import com.google.android.gms.internal.firebase_auth.zzdr;
import com.google.android.gms.internal.firebase_auth.zzdt;
import com.google.android.gms.internal.firebase_auth.zzdv;
import com.google.android.gms.internal.firebase_auth.zzdx;
import com.google.android.gms.internal.firebase_auth.zzdz;
import com.google.android.gms.internal.firebase_auth.zzfk;
import com.google.android.gms.internal.firebase_auth.zzfr;
import com.google.firebase.auth.ActionCodeSettings;
import com.google.firebase.auth.EmailAuthCredential;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.UserProfileChangeRequest;

/* compiled from: com.google.firebase:firebase-auth@@18.1.0 */
public abstract class zzed extends zza implements zzee {
    public zzed() {
        super("com.google.firebase.auth.api.internal.IFirebaseAuthService");
    }

    /* access modifiers changed from: protected */
    public final boolean zza(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        zzdz zzdz = null;
        String str = "com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks";
        switch (i) {
            case 1:
                String readString = parcel.readString();
                IBinder readStrongBinder = parcel.readStrongBinder();
                if (readStrongBinder != null) {
                    IInterface queryLocalInterface = readStrongBinder.queryLocalInterface(str);
                    if (queryLocalInterface instanceof zzdz) {
                        zzdz = (zzdz) queryLocalInterface;
                    } else {
                        zzdz = new zzeb(readStrongBinder);
                    }
                }
                zza(readString, zzdz);
                break;
            case 2:
                String readString2 = parcel.readString();
                IBinder readStrongBinder2 = parcel.readStrongBinder();
                if (readStrongBinder2 != null) {
                    IInterface queryLocalInterface2 = readStrongBinder2.queryLocalInterface(str);
                    if (queryLocalInterface2 instanceof zzdz) {
                        zzdz = (zzdz) queryLocalInterface2;
                    } else {
                        zzdz = new zzeb(readStrongBinder2);
                    }
                }
                zzb(readString2, zzdz);
                break;
            case 3:
                zzfr zzfr = (zzfr) zzd.zza(parcel, zzfr.CREATOR);
                IBinder readStrongBinder3 = parcel.readStrongBinder();
                if (readStrongBinder3 != null) {
                    IInterface queryLocalInterface3 = readStrongBinder3.queryLocalInterface(str);
                    if (queryLocalInterface3 instanceof zzdz) {
                        zzdz = (zzdz) queryLocalInterface3;
                    } else {
                        zzdz = new zzeb(readStrongBinder3);
                    }
                }
                zza(zzfr, zzdz);
                break;
            case 4:
                String readString3 = parcel.readString();
                UserProfileChangeRequest userProfileChangeRequest = (UserProfileChangeRequest) zzd.zza(parcel, UserProfileChangeRequest.CREATOR);
                IBinder readStrongBinder4 = parcel.readStrongBinder();
                if (readStrongBinder4 != null) {
                    IInterface queryLocalInterface4 = readStrongBinder4.queryLocalInterface(str);
                    if (queryLocalInterface4 instanceof zzdz) {
                        zzdz = (zzdz) queryLocalInterface4;
                    } else {
                        zzdz = new zzeb(readStrongBinder4);
                    }
                }
                zza(readString3, userProfileChangeRequest, zzdz);
                break;
            case 5:
                String readString4 = parcel.readString();
                String readString5 = parcel.readString();
                IBinder readStrongBinder5 = parcel.readStrongBinder();
                if (readStrongBinder5 != null) {
                    IInterface queryLocalInterface5 = readStrongBinder5.queryLocalInterface(str);
                    if (queryLocalInterface5 instanceof zzdz) {
                        zzdz = (zzdz) queryLocalInterface5;
                    } else {
                        zzdz = new zzeb(readStrongBinder5);
                    }
                }
                zza(readString4, readString5, zzdz);
                break;
            case 6:
                String readString6 = parcel.readString();
                String readString7 = parcel.readString();
                IBinder readStrongBinder6 = parcel.readStrongBinder();
                if (readStrongBinder6 != null) {
                    IInterface queryLocalInterface6 = readStrongBinder6.queryLocalInterface(str);
                    if (queryLocalInterface6 instanceof zzdz) {
                        zzdz = (zzdz) queryLocalInterface6;
                    } else {
                        zzdz = new zzeb(readStrongBinder6);
                    }
                }
                zzb(readString6, readString7, zzdz);
                break;
            case 7:
                String readString8 = parcel.readString();
                String readString9 = parcel.readString();
                IBinder readStrongBinder7 = parcel.readStrongBinder();
                if (readStrongBinder7 != null) {
                    IInterface queryLocalInterface7 = readStrongBinder7.queryLocalInterface(str);
                    if (queryLocalInterface7 instanceof zzdz) {
                        zzdz = (zzdz) queryLocalInterface7;
                    } else {
                        zzdz = new zzeb(readStrongBinder7);
                    }
                }
                zzc(readString8, readString9, zzdz);
                break;
            case 8:
                String readString10 = parcel.readString();
                String readString11 = parcel.readString();
                IBinder readStrongBinder8 = parcel.readStrongBinder();
                if (readStrongBinder8 != null) {
                    IInterface queryLocalInterface8 = readStrongBinder8.queryLocalInterface(str);
                    if (queryLocalInterface8 instanceof zzdz) {
                        zzdz = (zzdz) queryLocalInterface8;
                    } else {
                        zzdz = new zzeb(readStrongBinder8);
                    }
                }
                zzd(readString10, readString11, zzdz);
                break;
            case 9:
                String readString12 = parcel.readString();
                IBinder readStrongBinder9 = parcel.readStrongBinder();
                if (readStrongBinder9 != null) {
                    IInterface queryLocalInterface9 = readStrongBinder9.queryLocalInterface(str);
                    if (queryLocalInterface9 instanceof zzdz) {
                        zzdz = (zzdz) queryLocalInterface9;
                    } else {
                        zzdz = new zzeb(readStrongBinder9);
                    }
                }
                zzc(readString12, zzdz);
                break;
            case 10:
                String readString13 = parcel.readString();
                IBinder readStrongBinder10 = parcel.readStrongBinder();
                if (readStrongBinder10 != null) {
                    IInterface queryLocalInterface10 = readStrongBinder10.queryLocalInterface(str);
                    if (queryLocalInterface10 instanceof zzdz) {
                        zzdz = (zzdz) queryLocalInterface10;
                    } else {
                        zzdz = new zzeb(readStrongBinder10);
                    }
                }
                zzd(readString13, zzdz);
                break;
            case 11:
                String readString14 = parcel.readString();
                String readString15 = parcel.readString();
                String readString16 = parcel.readString();
                IBinder readStrongBinder11 = parcel.readStrongBinder();
                if (readStrongBinder11 != null) {
                    IInterface queryLocalInterface11 = readStrongBinder11.queryLocalInterface(str);
                    if (queryLocalInterface11 instanceof zzdz) {
                        zzdz = (zzdz) queryLocalInterface11;
                    } else {
                        zzdz = new zzeb(readStrongBinder11);
                    }
                }
                zza(readString14, readString15, readString16, zzdz);
                break;
            case 12:
                String readString17 = parcel.readString();
                zzfr zzfr2 = (zzfr) zzd.zza(parcel, zzfr.CREATOR);
                IBinder readStrongBinder12 = parcel.readStrongBinder();
                if (readStrongBinder12 != null) {
                    IInterface queryLocalInterface12 = readStrongBinder12.queryLocalInterface(str);
                    if (queryLocalInterface12 instanceof zzdz) {
                        zzdz = (zzdz) queryLocalInterface12;
                    } else {
                        zzdz = new zzeb(readStrongBinder12);
                    }
                }
                zza(readString17, zzfr2, zzdz);
                break;
            case 13:
                String readString18 = parcel.readString();
                IBinder readStrongBinder13 = parcel.readStrongBinder();
                if (readStrongBinder13 != null) {
                    IInterface queryLocalInterface13 = readStrongBinder13.queryLocalInterface(str);
                    if (queryLocalInterface13 instanceof zzdz) {
                        zzdz = (zzdz) queryLocalInterface13;
                    } else {
                        zzdz = new zzeb(readStrongBinder13);
                    }
                }
                zze(readString18, zzdz);
                break;
            case 14:
                String readString19 = parcel.readString();
                String readString20 = parcel.readString();
                IBinder readStrongBinder14 = parcel.readStrongBinder();
                if (readStrongBinder14 != null) {
                    IInterface queryLocalInterface14 = readStrongBinder14.queryLocalInterface(str);
                    if (queryLocalInterface14 instanceof zzdz) {
                        zzdz = (zzdz) queryLocalInterface14;
                    } else {
                        zzdz = new zzeb(readStrongBinder14);
                    }
                }
                zze(readString19, readString20, zzdz);
                break;
            case 15:
                String readString21 = parcel.readString();
                IBinder readStrongBinder15 = parcel.readStrongBinder();
                if (readStrongBinder15 != null) {
                    IInterface queryLocalInterface15 = readStrongBinder15.queryLocalInterface(str);
                    if (queryLocalInterface15 instanceof zzdz) {
                        zzdz = (zzdz) queryLocalInterface15;
                    } else {
                        zzdz = new zzeb(readStrongBinder15);
                    }
                }
                zzf(readString21, zzdz);
                break;
            case 16:
                IBinder readStrongBinder16 = parcel.readStrongBinder();
                if (readStrongBinder16 != null) {
                    IInterface queryLocalInterface16 = readStrongBinder16.queryLocalInterface(str);
                    if (queryLocalInterface16 instanceof zzdz) {
                        zzdz = (zzdz) queryLocalInterface16;
                    } else {
                        zzdz = new zzeb(readStrongBinder16);
                    }
                }
                zza(zzdz);
                break;
            case 17:
                String readString22 = parcel.readString();
                IBinder readStrongBinder17 = parcel.readStrongBinder();
                if (readStrongBinder17 != null) {
                    IInterface queryLocalInterface17 = readStrongBinder17.queryLocalInterface(str);
                    if (queryLocalInterface17 instanceof zzdz) {
                        zzdz = (zzdz) queryLocalInterface17;
                    } else {
                        zzdz = new zzeb(readStrongBinder17);
                    }
                }
                zzg(readString22, zzdz);
                break;
            case 18:
                String readString23 = parcel.readString();
                IBinder readStrongBinder18 = parcel.readStrongBinder();
                if (readStrongBinder18 != null) {
                    IInterface queryLocalInterface18 = readStrongBinder18.queryLocalInterface(str);
                    if (queryLocalInterface18 instanceof zzdz) {
                        zzdz = (zzdz) queryLocalInterface18;
                    } else {
                        zzdz = new zzeb(readStrongBinder18);
                    }
                }
                zzh(readString23, zzdz);
                break;
            case 19:
                String readString24 = parcel.readString();
                IBinder readStrongBinder19 = parcel.readStrongBinder();
                if (readStrongBinder19 != null) {
                    IInterface queryLocalInterface19 = readStrongBinder19.queryLocalInterface(str);
                    if (queryLocalInterface19 instanceof zzdz) {
                        zzdz = (zzdz) queryLocalInterface19;
                    } else {
                        zzdz = new zzeb(readStrongBinder19);
                    }
                }
                zzi(readString24, zzdz);
                break;
            case 20:
                String readString25 = parcel.readString();
                IBinder readStrongBinder20 = parcel.readStrongBinder();
                if (readStrongBinder20 != null) {
                    IInterface queryLocalInterface20 = readStrongBinder20.queryLocalInterface(str);
                    if (queryLocalInterface20 instanceof zzdz) {
                        zzdz = (zzdz) queryLocalInterface20;
                    } else {
                        zzdz = new zzeb(readStrongBinder20);
                    }
                }
                zzj(readString25, zzdz);
                break;
            case 21:
                String readString26 = parcel.readString();
                String readString27 = parcel.readString();
                IBinder readStrongBinder21 = parcel.readStrongBinder();
                if (readStrongBinder21 != null) {
                    IInterface queryLocalInterface21 = readStrongBinder21.queryLocalInterface(str);
                    if (queryLocalInterface21 instanceof zzdz) {
                        zzdz = (zzdz) queryLocalInterface21;
                    } else {
                        zzdz = new zzeb(readStrongBinder21);
                    }
                }
                zzf(readString26, readString27, zzdz);
                break;
            case 22:
                zzfk zzfk = (zzfk) zzd.zza(parcel, zzfk.CREATOR);
                IBinder readStrongBinder22 = parcel.readStrongBinder();
                if (readStrongBinder22 != null) {
                    IInterface queryLocalInterface22 = readStrongBinder22.queryLocalInterface(str);
                    if (queryLocalInterface22 instanceof zzdz) {
                        zzdz = (zzdz) queryLocalInterface22;
                    } else {
                        zzdz = new zzeb(readStrongBinder22);
                    }
                }
                zza(zzfk, zzdz);
                break;
            case 23:
                PhoneAuthCredential phoneAuthCredential = (PhoneAuthCredential) zzd.zza(parcel, PhoneAuthCredential.CREATOR);
                IBinder readStrongBinder23 = parcel.readStrongBinder();
                if (readStrongBinder23 != null) {
                    IInterface queryLocalInterface23 = readStrongBinder23.queryLocalInterface(str);
                    if (queryLocalInterface23 instanceof zzdz) {
                        zzdz = (zzdz) queryLocalInterface23;
                    } else {
                        zzdz = new zzeb(readStrongBinder23);
                    }
                }
                zza(phoneAuthCredential, zzdz);
                break;
            case 24:
                String readString28 = parcel.readString();
                PhoneAuthCredential phoneAuthCredential2 = (PhoneAuthCredential) zzd.zza(parcel, PhoneAuthCredential.CREATOR);
                IBinder readStrongBinder24 = parcel.readStrongBinder();
                if (readStrongBinder24 != null) {
                    IInterface queryLocalInterface24 = readStrongBinder24.queryLocalInterface(str);
                    if (queryLocalInterface24 instanceof zzdz) {
                        zzdz = (zzdz) queryLocalInterface24;
                    } else {
                        zzdz = new zzeb(readStrongBinder24);
                    }
                }
                zza(readString28, phoneAuthCredential2, zzdz);
                break;
            case 25:
                String readString29 = parcel.readString();
                ActionCodeSettings actionCodeSettings = (ActionCodeSettings) zzd.zza(parcel, ActionCodeSettings.CREATOR);
                IBinder readStrongBinder25 = parcel.readStrongBinder();
                if (readStrongBinder25 != null) {
                    IInterface queryLocalInterface25 = readStrongBinder25.queryLocalInterface(str);
                    if (queryLocalInterface25 instanceof zzdz) {
                        zzdz = (zzdz) queryLocalInterface25;
                    } else {
                        zzdz = new zzeb(readStrongBinder25);
                    }
                }
                zza(readString29, actionCodeSettings, zzdz);
                break;
            case 26:
                String readString30 = parcel.readString();
                ActionCodeSettings actionCodeSettings2 = (ActionCodeSettings) zzd.zza(parcel, ActionCodeSettings.CREATOR);
                IBinder readStrongBinder26 = parcel.readStrongBinder();
                if (readStrongBinder26 != null) {
                    IInterface queryLocalInterface26 = readStrongBinder26.queryLocalInterface(str);
                    if (queryLocalInterface26 instanceof zzdz) {
                        zzdz = (zzdz) queryLocalInterface26;
                    } else {
                        zzdz = new zzeb(readStrongBinder26);
                    }
                }
                zzb(readString30, actionCodeSettings2, zzdz);
                break;
            case 27:
                String readString31 = parcel.readString();
                IBinder readStrongBinder27 = parcel.readStrongBinder();
                if (readStrongBinder27 != null) {
                    IInterface queryLocalInterface27 = readStrongBinder27.queryLocalInterface(str);
                    if (queryLocalInterface27 instanceof zzdz) {
                        zzdz = (zzdz) queryLocalInterface27;
                    } else {
                        zzdz = new zzeb(readStrongBinder27);
                    }
                }
                zzk(readString31, zzdz);
                break;
            case 28:
                String readString32 = parcel.readString();
                ActionCodeSettings actionCodeSettings3 = (ActionCodeSettings) zzd.zza(parcel, ActionCodeSettings.CREATOR);
                IBinder readStrongBinder28 = parcel.readStrongBinder();
                if (readStrongBinder28 != null) {
                    IInterface queryLocalInterface28 = readStrongBinder28.queryLocalInterface(str);
                    if (queryLocalInterface28 instanceof zzdz) {
                        zzdz = (zzdz) queryLocalInterface28;
                    } else {
                        zzdz = new zzeb(readStrongBinder28);
                    }
                }
                zzc(readString32, actionCodeSettings3, zzdz);
                break;
            case 29:
                EmailAuthCredential emailAuthCredential = (EmailAuthCredential) zzd.zza(parcel, EmailAuthCredential.CREATOR);
                IBinder readStrongBinder29 = parcel.readStrongBinder();
                if (readStrongBinder29 != null) {
                    IInterface queryLocalInterface29 = readStrongBinder29.queryLocalInterface(str);
                    if (queryLocalInterface29 instanceof zzdz) {
                        zzdz = (zzdz) queryLocalInterface29;
                    } else {
                        zzdz = new zzeb(readStrongBinder29);
                    }
                }
                zza(emailAuthCredential, zzdz);
                break;
            default:
                switch (i) {
                    case 101:
                        zzch zzch = (zzch) zzd.zza(parcel, zzch.CREATOR);
                        IBinder readStrongBinder30 = parcel.readStrongBinder();
                        if (readStrongBinder30 != null) {
                            IInterface queryLocalInterface30 = readStrongBinder30.queryLocalInterface(str);
                            if (queryLocalInterface30 instanceof zzdz) {
                                zzdz = (zzdz) queryLocalInterface30;
                            } else {
                                zzdz = new zzeb(readStrongBinder30);
                            }
                        }
                        zza(zzch, zzdz);
                        break;
                    case 102:
                        zzdf zzdf = (zzdf) zzd.zza(parcel, zzdf.CREATOR);
                        IBinder readStrongBinder31 = parcel.readStrongBinder();
                        if (readStrongBinder31 != null) {
                            IInterface queryLocalInterface31 = readStrongBinder31.queryLocalInterface(str);
                            if (queryLocalInterface31 instanceof zzdz) {
                                zzdz = (zzdz) queryLocalInterface31;
                            } else {
                                zzdz = new zzeb(readStrongBinder31);
                            }
                        }
                        zza(zzdf, zzdz);
                        break;
                    case 103:
                        zzdd zzdd = (zzdd) zzd.zza(parcel, zzdd.CREATOR);
                        IBinder readStrongBinder32 = parcel.readStrongBinder();
                        if (readStrongBinder32 != null) {
                            IInterface queryLocalInterface32 = readStrongBinder32.queryLocalInterface(str);
                            if (queryLocalInterface32 instanceof zzdz) {
                                zzdz = (zzdz) queryLocalInterface32;
                            } else {
                                zzdz = new zzeb(readStrongBinder32);
                            }
                        }
                        zza(zzdd, zzdz);
                        break;
                    case 104:
                        zzdx zzdx = (zzdx) zzd.zza(parcel, zzdx.CREATOR);
                        IBinder readStrongBinder33 = parcel.readStrongBinder();
                        if (readStrongBinder33 != null) {
                            IInterface queryLocalInterface33 = readStrongBinder33.queryLocalInterface(str);
                            if (queryLocalInterface33 instanceof zzdz) {
                                zzdz = (zzdz) queryLocalInterface33;
                            } else {
                                zzdz = new zzeb(readStrongBinder33);
                            }
                        }
                        zza(zzdx, zzdz);
                        break;
                    case 105:
                        zzbr zzbr = (zzbr) zzd.zza(parcel, zzbr.CREATOR);
                        IBinder readStrongBinder34 = parcel.readStrongBinder();
                        if (readStrongBinder34 != null) {
                            IInterface queryLocalInterface34 = readStrongBinder34.queryLocalInterface(str);
                            if (queryLocalInterface34 instanceof zzdz) {
                                zzdz = (zzdz) queryLocalInterface34;
                            } else {
                                zzdz = new zzeb(readStrongBinder34);
                            }
                        }
                        zza(zzbr, zzdz);
                        break;
                    case 106:
                        zzbt zzbt = (zzbt) zzd.zza(parcel, zzbt.CREATOR);
                        IBinder readStrongBinder35 = parcel.readStrongBinder();
                        if (readStrongBinder35 != null) {
                            IInterface queryLocalInterface35 = readStrongBinder35.queryLocalInterface(str);
                            if (queryLocalInterface35 instanceof zzdz) {
                                zzdz = (zzdz) queryLocalInterface35;
                            } else {
                                zzdz = new zzeb(readStrongBinder35);
                            }
                        }
                        zza(zzbt, zzdz);
                        break;
                    case 107:
                        zzbz zzbz = (zzbz) zzd.zza(parcel, zzbz.CREATOR);
                        IBinder readStrongBinder36 = parcel.readStrongBinder();
                        if (readStrongBinder36 != null) {
                            IInterface queryLocalInterface36 = readStrongBinder36.queryLocalInterface(str);
                            if (queryLocalInterface36 instanceof zzdz) {
                                zzdz = (zzdz) queryLocalInterface36;
                            } else {
                                zzdz = new zzeb(readStrongBinder36);
                            }
                        }
                        zza(zzbz, zzdz);
                        break;
                    case 108:
                        zzdh zzdh = (zzdh) zzd.zza(parcel, zzdh.CREATOR);
                        IBinder readStrongBinder37 = parcel.readStrongBinder();
                        if (readStrongBinder37 != null) {
                            IInterface queryLocalInterface37 = readStrongBinder37.queryLocalInterface(str);
                            if (queryLocalInterface37 instanceof zzdz) {
                                zzdz = (zzdz) queryLocalInterface37;
                            } else {
                                zzdz = new zzeb(readStrongBinder37);
                            }
                        }
                        zza(zzdh, zzdz);
                        break;
                    case 109:
                        zzcj zzcj = (zzcj) zzd.zza(parcel, zzcj.CREATOR);
                        IBinder readStrongBinder38 = parcel.readStrongBinder();
                        if (readStrongBinder38 != null) {
                            IInterface queryLocalInterface38 = readStrongBinder38.queryLocalInterface(str);
                            if (queryLocalInterface38 instanceof zzdz) {
                                zzdz = (zzdz) queryLocalInterface38;
                            } else {
                                zzdz = new zzeb(readStrongBinder38);
                            }
                        }
                        zza(zzcj, zzdz);
                        break;
                    default:
                        switch (i) {
                            case 111:
                                zzcl zzcl = (zzcl) zzd.zza(parcel, zzcl.CREATOR);
                                IBinder readStrongBinder39 = parcel.readStrongBinder();
                                if (readStrongBinder39 != null) {
                                    IInterface queryLocalInterface39 = readStrongBinder39.queryLocalInterface(str);
                                    if (queryLocalInterface39 instanceof zzdz) {
                                        zzdz = (zzdz) queryLocalInterface39;
                                    } else {
                                        zzdz = new zzeb(readStrongBinder39);
                                    }
                                }
                                zza(zzcl, zzdz);
                                break;
                            case 112:
                                zzcn zzcn = (zzcn) zzd.zza(parcel, zzcn.CREATOR);
                                IBinder readStrongBinder40 = parcel.readStrongBinder();
                                if (readStrongBinder40 != null) {
                                    IInterface queryLocalInterface40 = readStrongBinder40.queryLocalInterface(str);
                                    if (queryLocalInterface40 instanceof zzdz) {
                                        zzdz = (zzdz) queryLocalInterface40;
                                    } else {
                                        zzdz = new zzeb(readStrongBinder40);
                                    }
                                }
                                zza(zzcn, zzdz);
                                break;
                            case 113:
                                zzdt zzdt = (zzdt) zzd.zza(parcel, zzdt.CREATOR);
                                IBinder readStrongBinder41 = parcel.readStrongBinder();
                                if (readStrongBinder41 != null) {
                                    IInterface queryLocalInterface41 = readStrongBinder41.queryLocalInterface(str);
                                    if (queryLocalInterface41 instanceof zzdz) {
                                        zzdz = (zzdz) queryLocalInterface41;
                                    } else {
                                        zzdz = new zzeb(readStrongBinder41);
                                    }
                                }
                                zza(zzdt, zzdz);
                                break;
                            case 114:
                                zzdv zzdv = (zzdv) zzd.zza(parcel, zzdv.CREATOR);
                                IBinder readStrongBinder42 = parcel.readStrongBinder();
                                if (readStrongBinder42 != null) {
                                    IInterface queryLocalInterface42 = readStrongBinder42.queryLocalInterface(str);
                                    if (queryLocalInterface42 instanceof zzdz) {
                                        zzdz = (zzdz) queryLocalInterface42;
                                    } else {
                                        zzdz = new zzeb(readStrongBinder42);
                                    }
                                }
                                zza(zzdv, zzdz);
                                break;
                            case 115:
                                zzcr zzcr = (zzcr) zzd.zza(parcel, zzcr.CREATOR);
                                IBinder readStrongBinder43 = parcel.readStrongBinder();
                                if (readStrongBinder43 != null) {
                                    IInterface queryLocalInterface43 = readStrongBinder43.queryLocalInterface(str);
                                    if (queryLocalInterface43 instanceof zzdz) {
                                        zzdz = (zzdz) queryLocalInterface43;
                                    } else {
                                        zzdz = new zzeb(readStrongBinder43);
                                    }
                                }
                                zza(zzcr, zzdz);
                                break;
                            case 116:
                                zzdb zzdb = (zzdb) zzd.zza(parcel, zzdb.CREATOR);
                                IBinder readStrongBinder44 = parcel.readStrongBinder();
                                if (readStrongBinder44 != null) {
                                    IInterface queryLocalInterface44 = readStrongBinder44.queryLocalInterface(str);
                                    if (queryLocalInterface44 instanceof zzdz) {
                                        zzdz = (zzdz) queryLocalInterface44;
                                    } else {
                                        zzdz = new zzeb(readStrongBinder44);
                                    }
                                }
                                zza(zzdb, zzdz);
                                break;
                            case 117:
                                zzcb zzcb = (zzcb) zzd.zza(parcel, zzcb.CREATOR);
                                IBinder readStrongBinder45 = parcel.readStrongBinder();
                                if (readStrongBinder45 != null) {
                                    IInterface queryLocalInterface45 = readStrongBinder45.queryLocalInterface(str);
                                    if (queryLocalInterface45 instanceof zzdz) {
                                        zzdz = (zzdz) queryLocalInterface45;
                                    } else {
                                        zzdz = new zzeb(readStrongBinder45);
                                    }
                                }
                                zza(zzcb, zzdz);
                                break;
                            default:
                                switch (i) {
                                    case 119:
                                        zzbv zzbv = (zzbv) zzd.zza(parcel, zzbv.CREATOR);
                                        IBinder readStrongBinder46 = parcel.readStrongBinder();
                                        if (readStrongBinder46 != null) {
                                            IInterface queryLocalInterface46 = readStrongBinder46.queryLocalInterface(str);
                                            if (queryLocalInterface46 instanceof zzdz) {
                                                zzdz = (zzdz) queryLocalInterface46;
                                            } else {
                                                zzdz = new zzeb(readStrongBinder46);
                                            }
                                        }
                                        zza(zzbv, zzdz);
                                        break;
                                    case 120:
                                        zzbp zzbp = (zzbp) zzd.zza(parcel, zzbp.CREATOR);
                                        IBinder readStrongBinder47 = parcel.readStrongBinder();
                                        if (readStrongBinder47 != null) {
                                            IInterface queryLocalInterface47 = readStrongBinder47.queryLocalInterface(str);
                                            if (queryLocalInterface47 instanceof zzdz) {
                                                zzdz = (zzdz) queryLocalInterface47;
                                            } else {
                                                zzdz = new zzeb(readStrongBinder47);
                                            }
                                        }
                                        zza(zzbp, zzdz);
                                        break;
                                    case PanasonicMakernoteDirectory.TAG_INTELLIGENT_D_RANGE /*121*/:
                                        zzbx zzbx = (zzbx) zzd.zza(parcel, zzbx.CREATOR);
                                        IBinder readStrongBinder48 = parcel.readStrongBinder();
                                        if (readStrongBinder48 != null) {
                                            IInterface queryLocalInterface48 = readStrongBinder48.queryLocalInterface(str);
                                            if (queryLocalInterface48 instanceof zzdz) {
                                                zzdz = (zzdz) queryLocalInterface48;
                                            } else {
                                                zzdz = new zzeb(readStrongBinder48);
                                            }
                                        }
                                        zza(zzbx, zzdz);
                                        break;
                                    case 122:
                                        zzcx zzcx = (zzcx) zzd.zza(parcel, zzcx.CREATOR);
                                        IBinder readStrongBinder49 = parcel.readStrongBinder();
                                        if (readStrongBinder49 != null) {
                                            IInterface queryLocalInterface49 = readStrongBinder49.queryLocalInterface(str);
                                            if (queryLocalInterface49 instanceof zzdz) {
                                                zzdz = (zzdz) queryLocalInterface49;
                                            } else {
                                                zzdz = new zzeb(readStrongBinder49);
                                            }
                                        }
                                        zza(zzcx, zzdz);
                                        break;
                                    case 123:
                                        zzdl zzdl = (zzdl) zzd.zza(parcel, zzdl.CREATOR);
                                        IBinder readStrongBinder50 = parcel.readStrongBinder();
                                        if (readStrongBinder50 != null) {
                                            IInterface queryLocalInterface50 = readStrongBinder50.queryLocalInterface(str);
                                            if (queryLocalInterface50 instanceof zzdz) {
                                                zzdz = (zzdz) queryLocalInterface50;
                                            } else {
                                                zzdz = new zzeb(readStrongBinder50);
                                            }
                                        }
                                        zza(zzdl, zzdz);
                                        break;
                                    case PanasonicMakernoteDirectory.TAG_CLEAR_RETOUCH /*124*/:
                                        zzcp zzcp = (zzcp) zzd.zza(parcel, zzcp.CREATOR);
                                        IBinder readStrongBinder51 = parcel.readStrongBinder();
                                        if (readStrongBinder51 != null) {
                                            IInterface queryLocalInterface51 = readStrongBinder51.queryLocalInterface(str);
                                            if (queryLocalInterface51 instanceof zzdz) {
                                                zzdz = (zzdz) queryLocalInterface51;
                                            } else {
                                                zzdz = new zzeb(readStrongBinder51);
                                            }
                                        }
                                        zza(zzcp, zzdz);
                                        break;
                                    default:
                                        switch (i) {
                                            case 126:
                                                zzct zzct = (zzct) zzd.zza(parcel, zzct.CREATOR);
                                                IBinder readStrongBinder52 = parcel.readStrongBinder();
                                                if (readStrongBinder52 != null) {
                                                    IInterface queryLocalInterface52 = readStrongBinder52.queryLocalInterface(str);
                                                    if (queryLocalInterface52 instanceof zzdz) {
                                                        zzdz = (zzdz) queryLocalInterface52;
                                                    } else {
                                                        zzdz = new zzeb(readStrongBinder52);
                                                    }
                                                }
                                                zza(zzct, zzdz);
                                                break;
                                            case 127:
                                                zzcz zzcz = (zzcz) zzd.zza(parcel, zzcz.CREATOR);
                                                IBinder readStrongBinder53 = parcel.readStrongBinder();
                                                if (readStrongBinder53 != null) {
                                                    IInterface queryLocalInterface53 = readStrongBinder53.queryLocalInterface(str);
                                                    if (queryLocalInterface53 instanceof zzdz) {
                                                        zzdz = (zzdz) queryLocalInterface53;
                                                    } else {
                                                        zzdz = new zzeb(readStrongBinder53);
                                                    }
                                                }
                                                zza(zzcz, zzdz);
                                                break;
                                            case 128:
                                                zzcv zzcv = (zzcv) zzd.zza(parcel, zzcv.CREATOR);
                                                IBinder readStrongBinder54 = parcel.readStrongBinder();
                                                if (readStrongBinder54 != null) {
                                                    IInterface queryLocalInterface54 = readStrongBinder54.queryLocalInterface(str);
                                                    if (queryLocalInterface54 instanceof zzdz) {
                                                        zzdz = (zzdz) queryLocalInterface54;
                                                    } else {
                                                        zzdz = new zzeb(readStrongBinder54);
                                                    }
                                                }
                                                zza(zzcv, zzdz);
                                                break;
                                            case 129:
                                                zzdj zzdj = (zzdj) zzd.zza(parcel, zzdj.CREATOR);
                                                IBinder readStrongBinder55 = parcel.readStrongBinder();
                                                if (readStrongBinder55 != null) {
                                                    IInterface queryLocalInterface55 = readStrongBinder55.queryLocalInterface(str);
                                                    if (queryLocalInterface55 instanceof zzdz) {
                                                        zzdz = (zzdz) queryLocalInterface55;
                                                    } else {
                                                        zzdz = new zzeb(readStrongBinder55);
                                                    }
                                                }
                                                zza(zzdj, zzdz);
                                                break;
                                            case NikonType2MakernoteDirectory.TAG_ADAPTER /*130*/:
                                                zzdn zzdn = (zzdn) zzd.zza(parcel, zzdn.CREATOR);
                                                IBinder readStrongBinder56 = parcel.readStrongBinder();
                                                if (readStrongBinder56 != null) {
                                                    IInterface queryLocalInterface56 = readStrongBinder56.queryLocalInterface(str);
                                                    if (queryLocalInterface56 instanceof zzdz) {
                                                        zzdz = (zzdz) queryLocalInterface56;
                                                    } else {
                                                        zzdz = new zzeb(readStrongBinder56);
                                                    }
                                                }
                                                zza(zzdn, zzdz);
                                                break;
                                            case 131:
                                                zzdr zzdr = (zzdr) zzd.zza(parcel, zzdr.CREATOR);
                                                IBinder readStrongBinder57 = parcel.readStrongBinder();
                                                if (readStrongBinder57 != null) {
                                                    IInterface queryLocalInterface57 = readStrongBinder57.queryLocalInterface(str);
                                                    if (queryLocalInterface57 instanceof zzdz) {
                                                        zzdz = (zzdz) queryLocalInterface57;
                                                    } else {
                                                        zzdz = new zzeb(readStrongBinder57);
                                                    }
                                                }
                                                zza(zzdr, zzdz);
                                                break;
                                            case NikonType2MakernoteDirectory.TAG_LENS /*132*/:
                                                zzcd zzcd = (zzcd) zzd.zza(parcel, zzcd.CREATOR);
                                                IBinder readStrongBinder58 = parcel.readStrongBinder();
                                                if (readStrongBinder58 != null) {
                                                    IInterface queryLocalInterface58 = readStrongBinder58.queryLocalInterface(str);
                                                    if (queryLocalInterface58 instanceof zzdz) {
                                                        zzdz = (zzdz) queryLocalInterface58;
                                                    } else {
                                                        zzdz = new zzeb(readStrongBinder58);
                                                    }
                                                }
                                                zza(zzcd, zzdz);
                                                break;
                                            case NikonType2MakernoteDirectory.TAG_MANUAL_FOCUS_DISTANCE /*133*/:
                                                zzdp zzdp = (zzdp) zzd.zza(parcel, zzdp.CREATOR);
                                                IBinder readStrongBinder59 = parcel.readStrongBinder();
                                                if (readStrongBinder59 != null) {
                                                    IInterface queryLocalInterface59 = readStrongBinder59.queryLocalInterface(str);
                                                    if (queryLocalInterface59 instanceof zzdz) {
                                                        zzdz = (zzdz) queryLocalInterface59;
                                                    } else {
                                                        zzdz = new zzeb(readStrongBinder59);
                                                    }
                                                }
                                                zza(zzdp, zzdz);
                                                break;
                                            case NikonType2MakernoteDirectory.TAG_DIGITAL_ZOOM /*134*/:
                                                zzcf zzcf = (zzcf) zzd.zza(parcel, zzcf.CREATOR);
                                                IBinder readStrongBinder60 = parcel.readStrongBinder();
                                                if (readStrongBinder60 != null) {
                                                    IInterface queryLocalInterface60 = readStrongBinder60.queryLocalInterface(str);
                                                    if (queryLocalInterface60 instanceof zzdz) {
                                                        zzdz = (zzdz) queryLocalInterface60;
                                                    } else {
                                                        zzdz = new zzeb(readStrongBinder60);
                                                    }
                                                }
                                                zza(zzcf, zzdz);
                                                break;
                                            case NikonType2MakernoteDirectory.TAG_FLASH_USED /*135*/:
                                                zzdz zzdz2 = (zzdz) zzd.zza(parcel, zzdz.CREATOR);
                                                IBinder readStrongBinder61 = parcel.readStrongBinder();
                                                if (readStrongBinder61 != null) {
                                                    IInterface queryLocalInterface61 = readStrongBinder61.queryLocalInterface(str);
                                                    if (queryLocalInterface61 instanceof zzdz) {
                                                        zzdz = (zzdz) queryLocalInterface61;
                                                    } else {
                                                        zzdz = new zzeb(readStrongBinder61);
                                                    }
                                                }
                                                zza(zzdz2, zzdz);
                                                break;
                                            default:
                                                return false;
                                        }
                                }
                        }
                }
        }
        parcel2.writeNoException();
        return true;
    }
}
