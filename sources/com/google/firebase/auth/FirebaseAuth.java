package com.google.firebase.auth;

import android.app.Activity;
import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.firebase_auth.zzey;
import com.google.android.gms.internal.firebase_auth.zzfk;
import com.google.android.gms.internal.firebase_auth.zzgd;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.PhoneAuthProvider.OnVerificationStateChangedCallbacks;
import com.google.firebase.auth.api.internal.zzau;
import com.google.firebase.auth.api.internal.zzdw;
import com.google.firebase.auth.api.internal.zzeh;
import com.google.firebase.auth.api.internal.zzei;
import com.google.firebase.auth.api.internal.zzer;
import com.google.firebase.auth.internal.InternalAuthProvider;
import com.google.firebase.auth.internal.zzab;
import com.google.firebase.auth.internal.zzae;
import com.google.firebase.auth.internal.zzam;
import com.google.firebase.auth.internal.zzas;
import com.google.firebase.auth.internal.zzav;
import com.google.firebase.auth.internal.zzaw;
import com.google.firebase.auth.internal.zzaz;
import com.google.firebase.auth.internal.zzh;
import com.google.firebase.auth.internal.zzn;
import com.google.firebase.auth.internal.zzo;
import com.google.firebase.internal.InternalTokenResult;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

/* compiled from: com.google.firebase:firebase-auth@@18.1.0 */
public class FirebaseAuth implements InternalAuthProvider {
    private FirebaseApp zza;
    /* access modifiers changed from: private */
    public final List<IdTokenListener> zzb;
    /* access modifiers changed from: private */
    public final List<com.google.firebase.auth.internal.IdTokenListener> zzc;
    /* access modifiers changed from: private */
    public List<AuthStateListener> zzd;
    private zzau zze;
    /* access modifiers changed from: private */
    public FirebaseUser zzf;
    /* access modifiers changed from: private */
    public zzo zzg;
    private final Object zzh;
    private String zzi;
    private final Object zzj;
    private String zzk;
    private final zzav zzl;
    private final zzam zzm;
    private com.google.firebase.auth.internal.zzau zzn;
    private zzaw zzo;

    /* compiled from: com.google.firebase:firebase-auth@@18.1.0 */
    public interface AuthStateListener {
        void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth);
    }

    /* compiled from: com.google.firebase:firebase-auth@@18.1.0 */
    public interface IdTokenListener {
        void onIdTokenChanged(@NonNull FirebaseAuth firebaseAuth);
    }

    @VisibleForTesting
    /* compiled from: com.google.firebase:firebase-auth@@18.1.0 */
    class zza implements com.google.firebase.auth.internal.zza {
        zza() {
        }

        public final void zza(@NonNull zzey zzey, @NonNull FirebaseUser firebaseUser) {
            Preconditions.checkNotNull(zzey);
            Preconditions.checkNotNull(firebaseUser);
            firebaseUser.zza(zzey);
            FirebaseAuth.this.zza(firebaseUser, zzey, true);
        }
    }

    @VisibleForTesting
    /* compiled from: com.google.firebase:firebase-auth@@18.1.0 */
    class zzb extends zza implements com.google.firebase.auth.internal.zza, zzae {
        zzb() {
            super();
        }

        public final void zza(Status status) {
            if (status.getStatusCode() == 17011 || status.getStatusCode() == 17021 || status.getStatusCode() == 17005 || status.getStatusCode() == 17091) {
                FirebaseAuth.this.signOut();
            }
        }
    }

    /* compiled from: com.google.firebase:firebase-auth@@18.1.0 */
    class zzc extends zza implements com.google.firebase.auth.internal.zza, zzae {
        zzc(FirebaseAuth firebaseAuth) {
            super();
        }

        public final void zza(Status status) {
        }
    }

    @Keep
    public static FirebaseAuth getInstance() {
        return (FirebaseAuth) FirebaseApp.getInstance().get(FirebaseAuth.class);
    }

    @Keep
    public static FirebaseAuth getInstance(@NonNull FirebaseApp firebaseApp) {
        return (FirebaseAuth) firebaseApp.get(FirebaseAuth.class);
    }

    public FirebaseAuth(FirebaseApp firebaseApp) {
        this(firebaseApp, zzeh.zza(firebaseApp.getApplicationContext(), new zzei(firebaseApp.getOptions().getApiKey()).zza()), new zzav(firebaseApp.getApplicationContext(), firebaseApp.getPersistenceKey()), zzam.zza());
    }

    @VisibleForTesting
    private FirebaseAuth(FirebaseApp firebaseApp, zzau zzau, zzav zzav, zzam zzam) {
        this.zzh = new Object();
        this.zzj = new Object();
        this.zza = (FirebaseApp) Preconditions.checkNotNull(firebaseApp);
        this.zze = (zzau) Preconditions.checkNotNull(zzau);
        this.zzl = (zzav) Preconditions.checkNotNull(zzav);
        this.zzg = new zzo();
        this.zzm = (zzam) Preconditions.checkNotNull(zzam);
        this.zzb = new CopyOnWriteArrayList();
        this.zzc = new CopyOnWriteArrayList();
        this.zzd = new CopyOnWriteArrayList();
        this.zzo = zzaw.zza();
        this.zzf = this.zzl.zza();
        FirebaseUser firebaseUser = this.zzf;
        if (firebaseUser != null) {
            zzey zzb2 = this.zzl.zzb(firebaseUser);
            if (zzb2 != null) {
                zza(this.zzf, zzb2, false);
            }
        }
        this.zzm.zza(this);
    }

    @Nullable
    public FirebaseUser getCurrentUser() {
        return this.zzf;
    }

    @Nullable
    public String getUid() {
        FirebaseUser firebaseUser = this.zzf;
        if (firebaseUser == null) {
            return null;
        }
        return firebaseUser.getUid();
    }

    public final void zza(@NonNull FirebaseUser firebaseUser, @NonNull zzey zzey, boolean z) {
        boolean z2;
        Preconditions.checkNotNull(firebaseUser);
        Preconditions.checkNotNull(zzey);
        FirebaseUser firebaseUser2 = this.zzf;
        boolean z3 = true;
        if (firebaseUser2 == null) {
            z2 = true;
        } else {
            boolean z4 = !firebaseUser2.zze().zzd().equals(zzey.zzd());
            boolean equals = this.zzf.getUid().equals(firebaseUser.getUid());
            z2 = !equals || z4;
            if (equals) {
                z3 = false;
            }
        }
        Preconditions.checkNotNull(firebaseUser);
        FirebaseUser firebaseUser3 = this.zzf;
        if (firebaseUser3 == null) {
            this.zzf = firebaseUser;
        } else {
            firebaseUser3.zza(firebaseUser.getProviderData());
            if (!firebaseUser.isAnonymous()) {
                this.zzf.zzb();
            }
            this.zzf.zzb(firebaseUser.zzh().zza());
        }
        if (z) {
            this.zzl.zza(this.zzf);
        }
        if (z2) {
            FirebaseUser firebaseUser4 = this.zzf;
            if (firebaseUser4 != null) {
                firebaseUser4.zza(zzey);
            }
            zzc(this.zzf);
        }
        if (z3) {
            zzd(this.zzf);
        }
        if (z) {
            this.zzl.zza(firebaseUser, zzey);
        }
        zzd().zza(this.zzf.zze());
    }

    public final void zza() {
        FirebaseUser firebaseUser = this.zzf;
        if (firebaseUser != null) {
            zzav zzav = this.zzl;
            Preconditions.checkNotNull(firebaseUser);
            zzav.zza(String.format("com.google.firebase.auth.GET_TOKEN_RESPONSE.%s", new Object[]{firebaseUser.getUid()}));
            this.zzf = null;
        }
        this.zzl.zza("com.google.firebase.auth.FIREBASE_USER");
        zzc((FirebaseUser) null);
        zzd((FirebaseUser) null);
    }

    @VisibleForTesting
    private final synchronized void zza(com.google.firebase.auth.internal.zzau zzau) {
        this.zzn = zzau;
    }

    @VisibleForTesting
    private final synchronized com.google.firebase.auth.internal.zzau zzd() {
        if (this.zzn == null) {
            zza(new com.google.firebase.auth.internal.zzau(this.zza));
        }
        return this.zzn;
    }

    public FirebaseApp getApp() {
        return this.zza;
    }

    public final FirebaseApp zzb() {
        return this.zza;
    }

    public void addIdTokenListener(@NonNull IdTokenListener idTokenListener) {
        this.zzb.add(idTokenListener);
        this.zzo.execute(new zzl(this, idTokenListener));
    }

    @KeepForSdk
    public void addIdTokenListener(@NonNull com.google.firebase.auth.internal.IdTokenListener idTokenListener) {
        Preconditions.checkNotNull(idTokenListener);
        this.zzc.add(idTokenListener);
        zzd().zza(this.zzc.size());
    }

    public void removeIdTokenListener(@NonNull IdTokenListener idTokenListener) {
        this.zzb.remove(idTokenListener);
    }

    @KeepForSdk
    public void removeIdTokenListener(@NonNull com.google.firebase.auth.internal.IdTokenListener idTokenListener) {
        Preconditions.checkNotNull(idTokenListener);
        this.zzc.remove(idTokenListener);
        zzd().zza(this.zzc.size());
    }

    public void addAuthStateListener(@NonNull AuthStateListener authStateListener) {
        this.zzd.add(authStateListener);
        this.zzo.execute(new zzn(this, authStateListener));
    }

    public void removeAuthStateListener(@NonNull AuthStateListener authStateListener) {
        this.zzd.remove(authStateListener);
    }

    private final void zzc(@Nullable FirebaseUser firebaseUser) {
        String str = "FirebaseAuth";
        if (firebaseUser != null) {
            String uid = firebaseUser.getUid();
            StringBuilder sb = new StringBuilder(String.valueOf(uid).length() + 45);
            sb.append("Notifying id token listeners about user ( ");
            sb.append(uid);
            sb.append(" ).");
            Log.d(str, sb.toString());
        } else {
            Log.d(str, "Notifying id token listeners about a sign-out event.");
        }
        this.zzo.execute(new zzm(this, new InternalTokenResult(firebaseUser != null ? firebaseUser.zzg() : null)));
    }

    private final void zzd(@Nullable FirebaseUser firebaseUser) {
        String str = "FirebaseAuth";
        if (firebaseUser != null) {
            String uid = firebaseUser.getUid();
            StringBuilder sb = new StringBuilder(String.valueOf(uid).length() + 47);
            sb.append("Notifying auth state listeners about user ( ");
            sb.append(uid);
            sb.append(" ).");
            Log.d(str, sb.toString());
        } else {
            Log.d(str, "Notifying auth state listeners about a sign-out event.");
        }
        this.zzo.execute(new zzp(this));
    }

    @NonNull
    public Task<GetTokenResult> getAccessToken(boolean z) {
        return zza(this.zzf, z);
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [com.google.firebase.auth.zzo, com.google.firebase.auth.internal.zzaz] */
    /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r2v0, types: [com.google.firebase.auth.zzo, com.google.firebase.auth.internal.zzaz]
      assigns: [com.google.firebase.auth.zzo]
      uses: [com.google.firebase.auth.internal.zzaz]
      mth insns count: 19
    	at jadx.core.dex.visitors.typeinference.TypeSearch.fillTypeCandidates(TypeSearch.java:237)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
    	at jadx.core.dex.visitors.typeinference.TypeSearch.run(TypeSearch.java:53)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runMultiVariableSearch(TypeInferenceVisitor.java:99)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:92)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
    	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
    	at jadx.core.ProcessClass.process(ProcessClass.java:30)
    	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:311)
    	at jadx.api.JavaClass.decompile(JavaClass.java:62)
    	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:217)
     */
    /* JADX WARNING: Unknown variable types count: 1 */
    @androidx.annotation.NonNull
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.gms.tasks.Task<com.google.firebase.auth.GetTokenResult> zza(@androidx.annotation.Nullable com.google.firebase.auth.FirebaseUser r4, boolean r5) {
        /*
            r3 = this;
            if (r4 != 0) goto L_0x0012
            com.google.android.gms.common.api.Status r4 = new com.google.android.gms.common.api.Status
            r5 = 17495(0x4457, float:2.4516E-41)
            r4.<init>(r5)
            com.google.firebase.FirebaseException r4 = com.google.firebase.auth.api.internal.zzdw.zza(r4)
            com.google.android.gms.tasks.Task r4 = com.google.android.gms.tasks.Tasks.forException(r4)
            return r4
        L_0x0012:
            com.google.android.gms.internal.firebase_auth.zzey r0 = r4.zze()
            boolean r1 = r0.zzb()
            if (r1 == 0) goto L_0x002b
            if (r5 != 0) goto L_0x002b
            java.lang.String r4 = r0.zzd()
            com.google.firebase.auth.GetTokenResult r4 = com.google.firebase.auth.internal.zzap.zza(r4)
            com.google.android.gms.tasks.Task r4 = com.google.android.gms.tasks.Tasks.forResult(r4)
            return r4
        L_0x002b:
            com.google.firebase.auth.api.internal.zzau r5 = r3.zze
            com.google.firebase.FirebaseApp r1 = r3.zza
            java.lang.String r0 = r0.zzc()
            com.google.firebase.auth.zzo r2 = new com.google.firebase.auth.zzo
            r2.<init>(r3)
            com.google.android.gms.tasks.Task r4 = r5.zza(r1, r4, r0, r2)
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.auth.FirebaseAuth.zza(com.google.firebase.auth.FirebaseUser, boolean):com.google.android.gms.tasks.Task");
    }

    @NonNull
    public Task<AuthResult> signInWithCredential(@NonNull AuthCredential authCredential) {
        Preconditions.checkNotNull(authCredential);
        if (authCredential instanceof EmailAuthCredential) {
            EmailAuthCredential emailAuthCredential = (EmailAuthCredential) authCredential;
            if (!emailAuthCredential.zzf()) {
                return this.zze.zzb(this.zza, emailAuthCredential.zza(), emailAuthCredential.zzb(), this.zzk, (com.google.firebase.auth.internal.zza) new zza());
            }
            if (zzb(emailAuthCredential.zzc())) {
                return Tasks.forException(zzdw.zza(new Status(17072)));
            }
            return this.zze.zza(this.zza, emailAuthCredential, (com.google.firebase.auth.internal.zza) new zza());
        } else if (!(authCredential instanceof PhoneAuthCredential)) {
            return this.zze.zza(this.zza, authCredential, this.zzk, (com.google.firebase.auth.internal.zza) new zza());
        } else {
            return this.zze.zza(this.zza, (PhoneAuthCredential) authCredential, this.zzk, (com.google.firebase.auth.internal.zza) new zza());
        }
    }

    /* JADX WARNING: type inference failed for: r5v0, types: [com.google.firebase.auth.FirebaseAuth$zzb, com.google.firebase.auth.internal.zzaz] */
    /* JADX WARNING: type inference failed for: r6v0, types: [com.google.firebase.auth.FirebaseAuth$zzb, com.google.firebase.auth.internal.zzaz] */
    /* JADX WARNING: type inference failed for: r2v2, types: [com.google.firebase.auth.FirebaseAuth$zzb, com.google.firebase.auth.internal.zzaz] */
    /* JADX WARNING: type inference failed for: r7v0, types: [com.google.firebase.auth.FirebaseAuth$zzb, com.google.firebase.auth.internal.zzaz] */
    /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r5v0, types: [com.google.firebase.auth.FirebaseAuth$zzb, com.google.firebase.auth.internal.zzaz]
      assigns: [com.google.firebase.auth.FirebaseAuth$zzb]
      uses: [com.google.firebase.auth.internal.zzaz]
      mth insns count: 51
    	at jadx.core.dex.visitors.typeinference.TypeSearch.fillTypeCandidates(TypeSearch.java:237)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
    	at jadx.core.dex.visitors.typeinference.TypeSearch.run(TypeSearch.java:53)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runMultiVariableSearch(TypeInferenceVisitor.java:99)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:92)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
    	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
    	at jadx.core.ProcessClass.process(ProcessClass.java:30)
    	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:311)
    	at jadx.api.JavaClass.decompile(JavaClass.java:62)
    	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:217)
     */
    /* JADX WARNING: Unknown variable types count: 4 */
    @androidx.annotation.NonNull
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.gms.tasks.Task<java.lang.Void> zza(@androidx.annotation.NonNull com.google.firebase.auth.FirebaseUser r9, @androidx.annotation.NonNull com.google.firebase.auth.AuthCredential r10) {
        /*
            r8 = this;
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r9)
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r10)
            java.lang.Class<com.google.firebase.auth.EmailAuthCredential> r0 = com.google.firebase.auth.EmailAuthCredential.class
            java.lang.Class r1 = r10.getClass()
            boolean r0 = r0.isAssignableFrom(r1)
            if (r0 == 0) goto L_0x0063
            com.google.firebase.auth.EmailAuthCredential r10 = (com.google.firebase.auth.EmailAuthCredential) r10
            java.lang.String r0 = r10.getSignInMethod()
            java.lang.String r1 = "password"
            boolean r0 = r1.equals(r0)
            if (r0 == 0) goto L_0x003b
            com.google.firebase.auth.api.internal.zzau r1 = r8.zze
            com.google.firebase.FirebaseApp r2 = r8.zza
            java.lang.String r4 = r10.zza()
            java.lang.String r5 = r10.zzb()
            java.lang.String r6 = r9.zzd()
            com.google.firebase.auth.FirebaseAuth$zzb r7 = new com.google.firebase.auth.FirebaseAuth$zzb
            r7.<init>()
            r3 = r9
            com.google.android.gms.tasks.Task r9 = r1.zza(r2, r3, r4, r5, r6, r7)
            return r9
        L_0x003b:
            java.lang.String r0 = r10.zzc()
            boolean r0 = r8.zzb(r0)
            if (r0 == 0) goto L_0x0055
            com.google.android.gms.common.api.Status r9 = new com.google.android.gms.common.api.Status
            r10 = 17072(0x42b0, float:2.3923E-41)
            r9.<init>(r10)
            com.google.firebase.FirebaseException r9 = com.google.firebase.auth.api.internal.zzdw.zza(r9)
            com.google.android.gms.tasks.Task r9 = com.google.android.gms.tasks.Tasks.forException(r9)
            return r9
        L_0x0055:
            com.google.firebase.auth.api.internal.zzau r0 = r8.zze
            com.google.firebase.FirebaseApp r1 = r8.zza
            com.google.firebase.auth.FirebaseAuth$zzb r2 = new com.google.firebase.auth.FirebaseAuth$zzb
            r2.<init>()
            com.google.android.gms.tasks.Task r9 = r0.zza(r1, r9, r10, r2)
            return r9
        L_0x0063:
            boolean r0 = r10 instanceof com.google.firebase.auth.PhoneAuthCredential
            if (r0 == 0) goto L_0x007b
            com.google.firebase.auth.api.internal.zzau r1 = r8.zze
            com.google.firebase.FirebaseApp r2 = r8.zza
            r4 = r10
            com.google.firebase.auth.PhoneAuthCredential r4 = (com.google.firebase.auth.PhoneAuthCredential) r4
            java.lang.String r5 = r8.zzk
            com.google.firebase.auth.FirebaseAuth$zzb r6 = new com.google.firebase.auth.FirebaseAuth$zzb
            r6.<init>()
            r3 = r9
            com.google.android.gms.tasks.Task r9 = r1.zza(r2, r3, r4, r5, r6)
            return r9
        L_0x007b:
            com.google.firebase.auth.api.internal.zzau r0 = r8.zze
            com.google.firebase.FirebaseApp r1 = r8.zza
            java.lang.String r4 = r9.zzd()
            com.google.firebase.auth.FirebaseAuth$zzb r5 = new com.google.firebase.auth.FirebaseAuth$zzb
            r5.<init>()
            r2 = r9
            r3 = r10
            com.google.android.gms.tasks.Task r9 = r0.zza(r1, r2, r3, r4, r5)
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.auth.FirebaseAuth.zza(com.google.firebase.auth.FirebaseUser, com.google.firebase.auth.AuthCredential):com.google.android.gms.tasks.Task");
    }

    /* JADX WARNING: type inference failed for: r5v0, types: [com.google.firebase.auth.FirebaseAuth$zzb, com.google.firebase.auth.internal.zzaz] */
    /* JADX WARNING: type inference failed for: r6v0, types: [com.google.firebase.auth.FirebaseAuth$zzb, com.google.firebase.auth.internal.zzaz] */
    /* JADX WARNING: type inference failed for: r2v2, types: [com.google.firebase.auth.FirebaseAuth$zzb, com.google.firebase.auth.internal.zzaz] */
    /* JADX WARNING: type inference failed for: r7v0, types: [com.google.firebase.auth.FirebaseAuth$zzb, com.google.firebase.auth.internal.zzaz] */
    /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r5v0, types: [com.google.firebase.auth.FirebaseAuth$zzb, com.google.firebase.auth.internal.zzaz]
      assigns: [com.google.firebase.auth.FirebaseAuth$zzb]
      uses: [com.google.firebase.auth.internal.zzaz]
      mth insns count: 51
    	at jadx.core.dex.visitors.typeinference.TypeSearch.fillTypeCandidates(TypeSearch.java:237)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
    	at jadx.core.dex.visitors.typeinference.TypeSearch.run(TypeSearch.java:53)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runMultiVariableSearch(TypeInferenceVisitor.java:99)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:92)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
    	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
    	at jadx.core.ProcessClass.process(ProcessClass.java:30)
    	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:311)
    	at jadx.api.JavaClass.decompile(JavaClass.java:62)
    	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:217)
     */
    /* JADX WARNING: Unknown variable types count: 4 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.gms.tasks.Task<com.google.firebase.auth.AuthResult> zzb(@androidx.annotation.NonNull com.google.firebase.auth.FirebaseUser r9, @androidx.annotation.NonNull com.google.firebase.auth.AuthCredential r10) {
        /*
            r8 = this;
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r9)
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r10)
            java.lang.Class<com.google.firebase.auth.EmailAuthCredential> r0 = com.google.firebase.auth.EmailAuthCredential.class
            java.lang.Class r1 = r10.getClass()
            boolean r0 = r0.isAssignableFrom(r1)
            if (r0 == 0) goto L_0x0063
            com.google.firebase.auth.EmailAuthCredential r10 = (com.google.firebase.auth.EmailAuthCredential) r10
            java.lang.String r0 = r10.getSignInMethod()
            java.lang.String r1 = "password"
            boolean r0 = r1.equals(r0)
            if (r0 == 0) goto L_0x003b
            com.google.firebase.auth.api.internal.zzau r1 = r8.zze
            com.google.firebase.FirebaseApp r2 = r8.zza
            java.lang.String r4 = r10.zza()
            java.lang.String r5 = r10.zzb()
            java.lang.String r6 = r9.zzd()
            com.google.firebase.auth.FirebaseAuth$zzb r7 = new com.google.firebase.auth.FirebaseAuth$zzb
            r7.<init>()
            r3 = r9
            com.google.android.gms.tasks.Task r9 = r1.zzb(r2, r3, r4, r5, r6, r7)
            return r9
        L_0x003b:
            java.lang.String r0 = r10.zzc()
            boolean r0 = r8.zzb(r0)
            if (r0 == 0) goto L_0x0055
            com.google.android.gms.common.api.Status r9 = new com.google.android.gms.common.api.Status
            r10 = 17072(0x42b0, float:2.3923E-41)
            r9.<init>(r10)
            com.google.firebase.FirebaseException r9 = com.google.firebase.auth.api.internal.zzdw.zza(r9)
            com.google.android.gms.tasks.Task r9 = com.google.android.gms.tasks.Tasks.forException(r9)
            return r9
        L_0x0055:
            com.google.firebase.auth.api.internal.zzau r0 = r8.zze
            com.google.firebase.FirebaseApp r1 = r8.zza
            com.google.firebase.auth.FirebaseAuth$zzb r2 = new com.google.firebase.auth.FirebaseAuth$zzb
            r2.<init>()
            com.google.android.gms.tasks.Task r9 = r0.zzb(r1, r9, r10, r2)
            return r9
        L_0x0063:
            boolean r0 = r10 instanceof com.google.firebase.auth.PhoneAuthCredential
            if (r0 == 0) goto L_0x007b
            com.google.firebase.auth.api.internal.zzau r1 = r8.zze
            com.google.firebase.FirebaseApp r2 = r8.zza
            r4 = r10
            com.google.firebase.auth.PhoneAuthCredential r4 = (com.google.firebase.auth.PhoneAuthCredential) r4
            java.lang.String r5 = r8.zzk
            com.google.firebase.auth.FirebaseAuth$zzb r6 = new com.google.firebase.auth.FirebaseAuth$zzb
            r6.<init>()
            r3 = r9
            com.google.android.gms.tasks.Task r9 = r1.zzb(r2, r3, r4, r5, r6)
            return r9
        L_0x007b:
            com.google.firebase.auth.api.internal.zzau r0 = r8.zze
            com.google.firebase.FirebaseApp r1 = r8.zza
            java.lang.String r4 = r9.zzd()
            com.google.firebase.auth.FirebaseAuth$zzb r5 = new com.google.firebase.auth.FirebaseAuth$zzb
            r5.<init>()
            r2 = r9
            r3 = r10
            com.google.android.gms.tasks.Task r9 = r0.zzb(r1, r2, r3, r4, r5)
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.auth.FirebaseAuth.zzb(com.google.firebase.auth.FirebaseUser, com.google.firebase.auth.AuthCredential):com.google.android.gms.tasks.Task");
    }

    @NonNull
    public Task<AuthResult> signInWithCustomToken(@NonNull String str) {
        Preconditions.checkNotEmpty(str);
        return this.zze.zza(this.zza, str, this.zzk, (com.google.firebase.auth.internal.zza) new zza());
    }

    @NonNull
    public Task<AuthResult> signInWithEmailAndPassword(@NonNull String str, @NonNull String str2) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        return this.zze.zzb(this.zza, str, str2, this.zzk, (com.google.firebase.auth.internal.zza) new zza());
    }

    @NonNull
    public Task<AuthResult> signInWithEmailLink(@NonNull String str, @NonNull String str2) {
        return signInWithCredential(EmailAuthProvider.getCredentialWithLink(str, str2));
    }

    @NonNull
    public Task<AuthResult> signInAnonymously() {
        FirebaseUser firebaseUser = this.zzf;
        if (firebaseUser == null || !firebaseUser.isAnonymous()) {
            return this.zze.zza(this.zza, (com.google.firebase.auth.internal.zza) new zza(), this.zzk);
        }
        zzn zzn2 = (zzn) this.zzf;
        zzn2.zza(false);
        return Tasks.forResult(new zzh(zzn2));
    }

    public final void zza(@NonNull String str, long j, TimeUnit timeUnit, @NonNull OnVerificationStateChangedCallbacks onVerificationStateChangedCallbacks, @Nullable Activity activity, @NonNull Executor executor, boolean z, @Nullable String str2) {
        OnVerificationStateChangedCallbacks onVerificationStateChangedCallbacks2;
        long j2 = j;
        long convert = TimeUnit.SECONDS.convert(j, timeUnit);
        if (convert < 0 || convert > 120) {
            throw new IllegalArgumentException("We only support 0-120 seconds for sms-auto-retrieval timeout");
        }
        zzfk zzfk = new zzfk(str, convert, z, this.zzi, this.zzk, null);
        if (this.zzg.zzc()) {
            String str3 = str;
            if (str.equals(this.zzg.zza())) {
                onVerificationStateChangedCallbacks2 = new zzr(this, onVerificationStateChangedCallbacks);
                this.zze.zza(this.zza, zzfk, onVerificationStateChangedCallbacks2, activity, executor);
            }
        }
        onVerificationStateChangedCallbacks2 = onVerificationStateChangedCallbacks;
        this.zze.zza(this.zza, zzfk, onVerificationStateChangedCallbacks2, activity, executor);
    }

    /* JADX WARNING: type inference failed for: r0v7, types: [com.google.firebase.auth.FirebaseAuth$zzc, com.google.firebase.auth.internal.zzaz] */
    /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r0v7, types: [com.google.firebase.auth.FirebaseAuth$zzc, com.google.firebase.auth.internal.zzaz]
      assigns: [com.google.firebase.auth.FirebaseAuth$zzc]
      uses: [com.google.firebase.auth.internal.zzaz]
      mth insns count: 39
    	at jadx.core.dex.visitors.typeinference.TypeSearch.fillTypeCandidates(TypeSearch.java:237)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
    	at jadx.core.dex.visitors.typeinference.TypeSearch.run(TypeSearch.java:53)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runMultiVariableSearch(TypeInferenceVisitor.java:99)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:92)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
    	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
    	at jadx.core.ProcessClass.process(ProcessClass.java:30)
    	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:311)
    	at jadx.api.JavaClass.decompile(JavaClass.java:62)
    	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:217)
     */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.google.android.gms.tasks.Task<java.lang.Void> updateCurrentUser(@androidx.annotation.NonNull com.google.firebase.auth.FirebaseUser r4) {
        /*
            r3 = this;
            if (r4 == 0) goto L_0x0076
            java.lang.String r0 = r4.zzd()
            if (r0 == 0) goto L_0x0014
            java.lang.String r0 = r4.zzd()
            java.lang.String r1 = r3.zzk
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x0022
        L_0x0014:
            java.lang.String r0 = r3.zzk
            if (r0 == 0) goto L_0x0032
            java.lang.String r1 = r4.zzd()
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x0032
        L_0x0022:
            com.google.android.gms.common.api.Status r4 = new com.google.android.gms.common.api.Status
            r0 = 17072(0x42b0, float:2.3923E-41)
            r4.<init>(r0)
            com.google.firebase.FirebaseException r4 = com.google.firebase.auth.api.internal.zzdw.zza(r4)
            com.google.android.gms.tasks.Task r4 = com.google.android.gms.tasks.Tasks.forException(r4)
            return r4
        L_0x0032:
            com.google.firebase.FirebaseApp r0 = r4.zzc()
            com.google.firebase.FirebaseOptions r0 = r0.getOptions()
            java.lang.String r0 = r0.getApiKey()
            com.google.firebase.FirebaseApp r1 = r3.zza
            com.google.firebase.FirebaseOptions r1 = r1.getOptions()
            java.lang.String r1 = r1.getApiKey()
            com.google.android.gms.internal.firebase_auth.zzey r2 = r4.zze()
            boolean r2 = r2.zzb()
            if (r2 == 0) goto L_0x006c
            boolean r0 = r1.equals(r0)
            if (r0 == 0) goto L_0x006c
            com.google.firebase.FirebaseApp r0 = r3.zza
            com.google.firebase.auth.FirebaseUser r0 = com.google.firebase.auth.internal.zzn.zza(r0, r4)
            com.google.android.gms.internal.firebase_auth.zzey r4 = r4.zze()
            r1 = 1
            r3.zza(r0, r4, r1)
            r4 = 0
            com.google.android.gms.tasks.Task r4 = com.google.android.gms.tasks.Tasks.forResult(r4)
            return r4
        L_0x006c:
            com.google.firebase.auth.FirebaseAuth$zzc r0 = new com.google.firebase.auth.FirebaseAuth$zzc
            r0.<init>(r3)
            com.google.android.gms.tasks.Task r4 = r3.zza(r4, r0)
            return r4
        L_0x0076:
            java.lang.IllegalArgumentException r4 = new java.lang.IllegalArgumentException
            java.lang.String r0 = "Cannot update current user with null user!"
            r4.<init>(r0)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.auth.FirebaseAuth.updateCurrentUser(com.google.firebase.auth.FirebaseUser):com.google.android.gms.tasks.Task");
    }

    /* JADX WARNING: type inference failed for: r0v0, types: [com.google.firebase.auth.FirebaseAuth$zzb, com.google.firebase.auth.internal.zzaz] */
    /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r0v0, types: [com.google.firebase.auth.FirebaseAuth$zzb, com.google.firebase.auth.internal.zzaz]
      assigns: [com.google.firebase.auth.FirebaseAuth$zzb]
      uses: [com.google.firebase.auth.internal.zzaz]
      mth insns count: 3
    	at jadx.core.dex.visitors.typeinference.TypeSearch.fillTypeCandidates(TypeSearch.java:237)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
    	at jadx.core.dex.visitors.typeinference.TypeSearch.run(TypeSearch.java:53)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runMultiVariableSearch(TypeInferenceVisitor.java:99)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:92)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
    	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
    	at jadx.core.ProcessClass.process(ProcessClass.java:30)
    	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:311)
    	at jadx.api.JavaClass.decompile(JavaClass.java:62)
    	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:217)
     */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.gms.tasks.Task<java.lang.Void> zza(@androidx.annotation.NonNull com.google.firebase.auth.FirebaseUser r2) {
        /*
            r1 = this;
            com.google.firebase.auth.FirebaseAuth$zzb r0 = new com.google.firebase.auth.FirebaseAuth$zzb
            r0.<init>()
            com.google.android.gms.tasks.Task r2 = r1.zza(r2, r0)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.auth.FirebaseAuth.zza(com.google.firebase.auth.FirebaseUser):com.google.android.gms.tasks.Task");
    }

    @NonNull
    private final Task<Void> zza(@NonNull FirebaseUser firebaseUser, zzaz zzaz) {
        Preconditions.checkNotNull(firebaseUser);
        return this.zze.zza(this.zza, firebaseUser, zzaz);
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [com.google.firebase.auth.FirebaseAuth$zzb, com.google.firebase.auth.internal.zzaz] */
    /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r2v0, types: [com.google.firebase.auth.FirebaseAuth$zzb, com.google.firebase.auth.internal.zzaz]
      assigns: [com.google.firebase.auth.FirebaseAuth$zzb]
      uses: [com.google.firebase.auth.internal.zzaz]
      mth insns count: 7
    	at jadx.core.dex.visitors.typeinference.TypeSearch.fillTypeCandidates(TypeSearch.java:237)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
    	at jadx.core.dex.visitors.typeinference.TypeSearch.run(TypeSearch.java:53)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runMultiVariableSearch(TypeInferenceVisitor.java:99)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:92)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
    	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
    	at jadx.core.ProcessClass.process(ProcessClass.java:30)
    	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:311)
    	at jadx.api.JavaClass.decompile(JavaClass.java:62)
    	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:217)
     */
    /* JADX WARNING: Unknown variable types count: 1 */
    @androidx.annotation.NonNull
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.gms.tasks.Task<com.google.firebase.auth.AuthResult> zzc(@androidx.annotation.NonNull com.google.firebase.auth.FirebaseUser r4, @androidx.annotation.NonNull com.google.firebase.auth.AuthCredential r5) {
        /*
            r3 = this;
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r5)
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r4)
            com.google.firebase.auth.api.internal.zzau r0 = r3.zze
            com.google.firebase.FirebaseApp r1 = r3.zza
            com.google.firebase.auth.FirebaseAuth$zzb r2 = new com.google.firebase.auth.FirebaseAuth$zzb
            r2.<init>()
            com.google.android.gms.tasks.Task r4 = r0.zza(r1, r4, r5, r2)
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.auth.FirebaseAuth.zzc(com.google.firebase.auth.FirebaseUser, com.google.firebase.auth.AuthCredential):com.google.android.gms.tasks.Task");
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [com.google.firebase.auth.FirebaseAuth$zzb, com.google.firebase.auth.internal.zzaz] */
    /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r2v0, types: [com.google.firebase.auth.FirebaseAuth$zzb, com.google.firebase.auth.internal.zzaz]
      assigns: [com.google.firebase.auth.FirebaseAuth$zzb]
      uses: [com.google.firebase.auth.internal.zzaz]
      mth insns count: 7
    	at jadx.core.dex.visitors.typeinference.TypeSearch.fillTypeCandidates(TypeSearch.java:237)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
    	at jadx.core.dex.visitors.typeinference.TypeSearch.run(TypeSearch.java:53)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runMultiVariableSearch(TypeInferenceVisitor.java:99)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:92)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
    	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
    	at jadx.core.ProcessClass.process(ProcessClass.java:30)
    	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:311)
    	at jadx.api.JavaClass.decompile(JavaClass.java:62)
    	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:217)
     */
    /* JADX WARNING: Unknown variable types count: 1 */
    @androidx.annotation.NonNull
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.gms.tasks.Task<com.google.firebase.auth.AuthResult> zza(@androidx.annotation.NonNull com.google.firebase.auth.FirebaseUser r4, @androidx.annotation.NonNull java.lang.String r5) {
        /*
            r3 = this;
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r5)
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r4)
            com.google.firebase.auth.api.internal.zzau r0 = r3.zze
            com.google.firebase.FirebaseApp r1 = r3.zza
            com.google.firebase.auth.FirebaseAuth$zzb r2 = new com.google.firebase.auth.FirebaseAuth$zzb
            r2.<init>()
            com.google.android.gms.tasks.Task r4 = r0.zzd(r1, r4, r5, r2)
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.auth.FirebaseAuth.zza(com.google.firebase.auth.FirebaseUser, java.lang.String):com.google.android.gms.tasks.Task");
    }

    @NonNull
    public Task<AuthResult> createUserWithEmailAndPassword(@NonNull String str, @NonNull String str2) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        return this.zze.zza(this.zza, str, str2, this.zzk, (com.google.firebase.auth.internal.zza) new zza());
    }

    @NonNull
    public Task<SignInMethodQueryResult> fetchSignInMethodsForEmail(@NonNull String str) {
        Preconditions.checkNotEmpty(str);
        return this.zze.zza(this.zza, str, this.zzk);
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [com.google.firebase.auth.FirebaseAuth$zzb, com.google.firebase.auth.internal.zzaz] */
    /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r2v0, types: [com.google.firebase.auth.FirebaseAuth$zzb, com.google.firebase.auth.internal.zzaz]
      assigns: [com.google.firebase.auth.FirebaseAuth$zzb]
      uses: [com.google.firebase.auth.internal.zzaz]
      mth insns count: 7
    	at jadx.core.dex.visitors.typeinference.TypeSearch.fillTypeCandidates(TypeSearch.java:237)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
    	at jadx.core.dex.visitors.typeinference.TypeSearch.run(TypeSearch.java:53)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runMultiVariableSearch(TypeInferenceVisitor.java:99)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:92)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
    	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
    	at jadx.core.ProcessClass.process(ProcessClass.java:30)
    	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:311)
    	at jadx.api.JavaClass.decompile(JavaClass.java:62)
    	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:217)
     */
    /* JADX WARNING: Unknown variable types count: 1 */
    @androidx.annotation.NonNull
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.gms.tasks.Task<java.lang.Void> zza(@androidx.annotation.NonNull com.google.firebase.auth.FirebaseUser r4, @androidx.annotation.NonNull com.google.firebase.auth.UserProfileChangeRequest r5) {
        /*
            r3 = this;
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r4)
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r5)
            com.google.firebase.auth.api.internal.zzau r0 = r3.zze
            com.google.firebase.FirebaseApp r1 = r3.zza
            com.google.firebase.auth.FirebaseAuth$zzb r2 = new com.google.firebase.auth.FirebaseAuth$zzb
            r2.<init>()
            com.google.android.gms.tasks.Task r4 = r0.zza(r1, r4, r5, r2)
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.auth.FirebaseAuth.zza(com.google.firebase.auth.FirebaseUser, com.google.firebase.auth.UserProfileChangeRequest):com.google.android.gms.tasks.Task");
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [com.google.firebase.auth.FirebaseAuth$zzb, com.google.firebase.auth.internal.zzaz] */
    /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r2v0, types: [com.google.firebase.auth.FirebaseAuth$zzb, com.google.firebase.auth.internal.zzaz]
      assigns: [com.google.firebase.auth.FirebaseAuth$zzb]
      uses: [com.google.firebase.auth.internal.zzaz]
      mth insns count: 7
    	at jadx.core.dex.visitors.typeinference.TypeSearch.fillTypeCandidates(TypeSearch.java:237)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
    	at jadx.core.dex.visitors.typeinference.TypeSearch.run(TypeSearch.java:53)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runMultiVariableSearch(TypeInferenceVisitor.java:99)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:92)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
    	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
    	at jadx.core.ProcessClass.process(ProcessClass.java:30)
    	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:311)
    	at jadx.api.JavaClass.decompile(JavaClass.java:62)
    	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:217)
     */
    /* JADX WARNING: Unknown variable types count: 1 */
    @androidx.annotation.NonNull
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.gms.tasks.Task<java.lang.Void> zzb(@androidx.annotation.NonNull com.google.firebase.auth.FirebaseUser r4, @androidx.annotation.NonNull java.lang.String r5) {
        /*
            r3 = this;
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r4)
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r5)
            com.google.firebase.auth.api.internal.zzau r0 = r3.zze
            com.google.firebase.FirebaseApp r1 = r3.zza
            com.google.firebase.auth.FirebaseAuth$zzb r2 = new com.google.firebase.auth.FirebaseAuth$zzb
            r2.<init>()
            com.google.android.gms.tasks.Task r4 = r0.zzb(r1, r4, r5, r2)
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.auth.FirebaseAuth.zzb(com.google.firebase.auth.FirebaseUser, java.lang.String):com.google.android.gms.tasks.Task");
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [com.google.firebase.auth.FirebaseAuth$zzb, com.google.firebase.auth.internal.zzaz] */
    /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r2v0, types: [com.google.firebase.auth.FirebaseAuth$zzb, com.google.firebase.auth.internal.zzaz]
      assigns: [com.google.firebase.auth.FirebaseAuth$zzb]
      uses: [com.google.firebase.auth.internal.zzaz]
      mth insns count: 7
    	at jadx.core.dex.visitors.typeinference.TypeSearch.fillTypeCandidates(TypeSearch.java:237)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
    	at jadx.core.dex.visitors.typeinference.TypeSearch.run(TypeSearch.java:53)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runMultiVariableSearch(TypeInferenceVisitor.java:99)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:92)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
    	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
    	at jadx.core.ProcessClass.process(ProcessClass.java:30)
    	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:311)
    	at jadx.api.JavaClass.decompile(JavaClass.java:62)
    	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:217)
     */
    /* JADX WARNING: Unknown variable types count: 1 */
    @androidx.annotation.NonNull
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.gms.tasks.Task<java.lang.Void> zza(@androidx.annotation.NonNull com.google.firebase.auth.FirebaseUser r4, @androidx.annotation.NonNull com.google.firebase.auth.PhoneAuthCredential r5) {
        /*
            r3 = this;
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r4)
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r5)
            com.google.firebase.auth.api.internal.zzau r0 = r3.zze
            com.google.firebase.FirebaseApp r1 = r3.zza
            com.google.firebase.auth.FirebaseAuth$zzb r2 = new com.google.firebase.auth.FirebaseAuth$zzb
            r2.<init>()
            com.google.android.gms.tasks.Task r4 = r0.zza(r1, r4, r5, r2)
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.auth.FirebaseAuth.zza(com.google.firebase.auth.FirebaseUser, com.google.firebase.auth.PhoneAuthCredential):com.google.android.gms.tasks.Task");
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [com.google.firebase.auth.FirebaseAuth$zzb, com.google.firebase.auth.internal.zzaz] */
    /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r2v0, types: [com.google.firebase.auth.FirebaseAuth$zzb, com.google.firebase.auth.internal.zzaz]
      assigns: [com.google.firebase.auth.FirebaseAuth$zzb]
      uses: [com.google.firebase.auth.internal.zzaz]
      mth insns count: 7
    	at jadx.core.dex.visitors.typeinference.TypeSearch.fillTypeCandidates(TypeSearch.java:237)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
    	at jadx.core.dex.visitors.typeinference.TypeSearch.run(TypeSearch.java:53)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runMultiVariableSearch(TypeInferenceVisitor.java:99)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:92)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
    	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
    	at jadx.core.ProcessClass.process(ProcessClass.java:30)
    	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:311)
    	at jadx.api.JavaClass.decompile(JavaClass.java:62)
    	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:217)
     */
    /* JADX WARNING: Unknown variable types count: 1 */
    @androidx.annotation.NonNull
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.gms.tasks.Task<java.lang.Void> zzc(@androidx.annotation.NonNull com.google.firebase.auth.FirebaseUser r4, @androidx.annotation.NonNull java.lang.String r5) {
        /*
            r3 = this;
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r4)
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r5)
            com.google.firebase.auth.api.internal.zzau r0 = r3.zze
            com.google.firebase.FirebaseApp r1 = r3.zza
            com.google.firebase.auth.FirebaseAuth$zzb r2 = new com.google.firebase.auth.FirebaseAuth$zzb
            r2.<init>()
            com.google.android.gms.tasks.Task r4 = r0.zzc(r1, r4, r5, r2)
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.auth.FirebaseAuth.zzc(com.google.firebase.auth.FirebaseUser, java.lang.String):com.google.android.gms.tasks.Task");
    }

    @NonNull
    public Task<Void> sendPasswordResetEmail(@NonNull String str) {
        Preconditions.checkNotEmpty(str);
        return sendPasswordResetEmail(str, null);
    }

    @NonNull
    public Task<Void> sendPasswordResetEmail(@NonNull String str, @Nullable ActionCodeSettings actionCodeSettings) {
        Preconditions.checkNotEmpty(str);
        if (actionCodeSettings == null) {
            actionCodeSettings = ActionCodeSettings.zza();
        }
        String str2 = this.zzi;
        if (str2 != null) {
            actionCodeSettings.zza(str2);
        }
        actionCodeSettings.zza(zzgd.PASSWORD_RESET);
        return this.zze.zza(this.zza, str, actionCodeSettings, this.zzk);
    }

    public Task<Void> sendSignInLinkToEmail(@NonNull String str, @NonNull ActionCodeSettings actionCodeSettings) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(actionCodeSettings);
        if (actionCodeSettings.canHandleCodeInApp()) {
            String str2 = this.zzi;
            if (str2 != null) {
                actionCodeSettings.zza(str2);
            }
            return this.zze.zzb(this.zza, str, actionCodeSettings, this.zzk);
        }
        throw new IllegalArgumentException("You must set canHandleCodeInApp in your ActionCodeSettings to true for Email-Link Sign-in.");
    }

    public boolean isSignInWithEmailLink(@NonNull String str) {
        return EmailAuthCredential.zza(str);
    }

    @NonNull
    public final Task<Void> zza(@Nullable ActionCodeSettings actionCodeSettings, @NonNull String str) {
        Preconditions.checkNotEmpty(str);
        if (this.zzi != null) {
            if (actionCodeSettings == null) {
                actionCodeSettings = ActionCodeSettings.zza();
            }
            actionCodeSettings.zza(this.zzi);
        }
        return this.zze.zza(this.zza, actionCodeSettings, str);
    }

    @NonNull
    public Task<ActionCodeResult> checkActionCode(@NonNull String str) {
        Preconditions.checkNotEmpty(str);
        return this.zze.zzb(this.zza, str, this.zzk);
    }

    @NonNull
    public Task<Void> applyActionCode(@NonNull String str) {
        Preconditions.checkNotEmpty(str);
        return this.zze.zzc(this.zza, str, this.zzk);
    }

    @NonNull
    public Task<String> verifyPasswordResetCode(@NonNull String str) {
        Preconditions.checkNotEmpty(str);
        return this.zze.zzd(this.zza, str, this.zzk);
    }

    @NonNull
    public Task<Void> confirmPasswordReset(@NonNull String str, @NonNull String str2) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        return this.zze.zza(this.zza, str, str2, this.zzk);
    }

    public Task<AuthResult> startActivityForSignInWithProvider(@NonNull Activity activity, @NonNull FederatedAuthProvider federatedAuthProvider) {
        Preconditions.checkNotNull(federatedAuthProvider);
        Preconditions.checkNotNull(activity);
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        if (!this.zzm.zza(activity, taskCompletionSource, this)) {
            return Tasks.forException(zzdw.zza(new Status(17057)));
        }
        zzas.zza(activity.getApplicationContext(), this);
        federatedAuthProvider.zza(activity);
        return taskCompletionSource.getTask();
    }

    public final Task<AuthResult> zza(@NonNull Activity activity, @NonNull FederatedAuthProvider federatedAuthProvider, @NonNull FirebaseUser firebaseUser) {
        Preconditions.checkNotNull(activity);
        Preconditions.checkNotNull(federatedAuthProvider);
        Preconditions.checkNotNull(firebaseUser);
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        if (!this.zzm.zza(activity, taskCompletionSource, this, firebaseUser)) {
            return Tasks.forException(zzdw.zza(new Status(17057)));
        }
        zzas.zza(activity.getApplicationContext(), this, firebaseUser);
        federatedAuthProvider.zzb(activity);
        return taskCompletionSource.getTask();
    }

    public final Task<AuthResult> zzb(@NonNull Activity activity, @NonNull FederatedAuthProvider federatedAuthProvider, @NonNull FirebaseUser firebaseUser) {
        Preconditions.checkNotNull(activity);
        Preconditions.checkNotNull(federatedAuthProvider);
        Preconditions.checkNotNull(firebaseUser);
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        if (!this.zzm.zza(activity, taskCompletionSource, this, firebaseUser)) {
            return Tasks.forException(zzdw.zza(new Status(17057)));
        }
        zzas.zza(activity.getApplicationContext(), this, firebaseUser);
        federatedAuthProvider.zzc(activity);
        return taskCompletionSource.getTask();
    }

    @Nullable
    public Task<AuthResult> getPendingAuthResult() {
        return this.zzm.zzb();
    }

    @NonNull
    public final Task<Void> zzb(@NonNull FirebaseUser firebaseUser) {
        Preconditions.checkNotNull(firebaseUser);
        return this.zze.zza(firebaseUser, (zzab) new zzq(this, firebaseUser));
    }

    public void signOut() {
        zza();
        com.google.firebase.auth.internal.zzau zzau = this.zzn;
        if (zzau != null) {
            zzau.zza();
        }
    }

    public void setLanguageCode(@NonNull String str) {
        Preconditions.checkNotEmpty(str);
        synchronized (this.zzh) {
            this.zzi = str;
        }
    }

    @Nullable
    public String getLanguageCode() {
        String str;
        synchronized (this.zzh) {
            str = this.zzi;
        }
        return str;
    }

    public final void zza(@NonNull String str) {
        Preconditions.checkNotEmpty(str);
        synchronized (this.zzj) {
            this.zzk = str;
        }
    }

    @Nullable
    public final String zzc() {
        String str;
        synchronized (this.zzj) {
            str = this.zzk;
        }
        return str;
    }

    public void useAppLanguage() {
        synchronized (this.zzh) {
            this.zzi = zzer.zza();
        }
    }

    public FirebaseAuthSettings getFirebaseAuthSettings() {
        return this.zzg;
    }

    public Task<Void> setFirebaseUIVersion(@Nullable String str) {
        return this.zze.zza(str);
    }

    private final boolean zzb(String str) {
        zzf zza2 = zzf.zza(str);
        return zza2 != null && !TextUtils.equals(this.zzk, zza2.zzc());
    }
}
