package p006io.invertase.firebase.analytics;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.Set;
import java.util.concurrent.Callable;
import javax.annotation.Nullable;
import p006io.invertase.firebase.common.UniversalFirebaseModule;

/* renamed from: io.invertase.firebase.analytics.UniversalFirebaseAnalyticsModule */
public class UniversalFirebaseAnalyticsModule extends UniversalFirebaseModule {
    UniversalFirebaseAnalyticsModule(Context context, String str) {
        super(context, str);
    }

    /* access modifiers changed from: 0000 */
    public Task<Void> logEvent(String str, Bundle bundle) {
        return Tasks.call(new Callable(str, bundle) {
            private final /* synthetic */ String f$1;
            private final /* synthetic */ Bundle f$2;

            {
                this.f$1 = r2;
                this.f$2 = r3;
            }

            public final Object call() {
                return UniversalFirebaseAnalyticsModule.this.lambda$logEvent$0$UniversalFirebaseAnalyticsModule(this.f$1, this.f$2);
            }
        });
    }

    public /* synthetic */ Void lambda$logEvent$0$UniversalFirebaseAnalyticsModule(String str, Bundle bundle) throws Exception {
        FirebaseAnalytics.getInstance(getContext()).logEvent(str, bundle);
        return null;
    }

    /* access modifiers changed from: 0000 */
    public Task<Void> setAnalyticsCollectionEnabled(Boolean bool) {
        return Tasks.call(new Callable(bool) {
            private final /* synthetic */ Boolean f$1;

            {
                this.f$1 = r2;
            }

            public final Object call() {
                return UniversalFirebaseAnalyticsModule.this.mo35415xd3b42215(this.f$1);
            }
        });
    }

    /* renamed from: lambda$setAnalyticsCollectionEnabled$1$UniversalFirebaseAnalyticsModule */
    public /* synthetic */ Void mo35415xd3b42215(Boolean bool) throws Exception {
        FirebaseAnalytics.getInstance(getContext()).setAnalyticsCollectionEnabled(bool.booleanValue());
        return null;
    }

    /* access modifiers changed from: 0000 */
    public Task<Void> setAnalyticsCollectionEnabled(Activity activity, String str, @Nullable String str2) {
        return Tasks.call(new Callable(activity, str, str2) {
            private final /* synthetic */ Activity f$1;
            private final /* synthetic */ String f$2;
            private final /* synthetic */ String f$3;

            {
                this.f$1 = r2;
                this.f$2 = r3;
                this.f$3 = r4;
            }

            public final Object call() {
                return UniversalFirebaseAnalyticsModule.this.mo35416x10d3e634(this.f$1, this.f$2, this.f$3);
            }
        });
    }

    /* renamed from: lambda$setAnalyticsCollectionEnabled$2$UniversalFirebaseAnalyticsModule */
    public /* synthetic */ Void mo35416x10d3e634(Activity activity, String str, String str2) throws Exception {
        if (activity == null) {
            return null;
        }
        FirebaseAnalytics.getInstance(getContext()).setCurrentScreen(activity, str, str2);
        return null;
    }

    /* access modifiers changed from: 0000 */
    public Task<Void> setMinimumSessionDuration(long j) {
        return Tasks.call(new Callable(j) {
            private final /* synthetic */ long f$1;

            {
                this.f$1 = r2;
            }

            public final Object call() {
                return UniversalFirebaseAnalyticsModule.this.mo35417xbdfe16f4(this.f$1);
            }
        });
    }

    /* renamed from: lambda$setMinimumSessionDuration$3$UniversalFirebaseAnalyticsModule */
    public /* synthetic */ Void mo35417xbdfe16f4(long j) throws Exception {
        FirebaseAnalytics.getInstance(getContext()).setMinimumSessionDuration(j);
        return null;
    }

    /* access modifiers changed from: 0000 */
    public Task<Void> setSessionTimeoutDuration(long j) {
        return Tasks.call(new Callable(j) {
            private final /* synthetic */ long f$1;

            {
                this.f$1 = r2;
            }

            public final Object call() {
                return UniversalFirebaseAnalyticsModule.this.mo35418x59809ff0(this.f$1);
            }
        });
    }

    /* renamed from: lambda$setSessionTimeoutDuration$4$UniversalFirebaseAnalyticsModule */
    public /* synthetic */ Void mo35418x59809ff0(long j) throws Exception {
        FirebaseAnalytics.getInstance(getContext()).setSessionTimeoutDuration(j);
        return null;
    }

    /* access modifiers changed from: 0000 */
    public Task<Void> setUserId(String str) {
        return Tasks.call(new Callable(str) {
            private final /* synthetic */ String f$1;

            {
                this.f$1 = r2;
            }

            public final Object call() {
                return UniversalFirebaseAnalyticsModule.this.lambda$setUserId$5$UniversalFirebaseAnalyticsModule(this.f$1);
            }
        });
    }

    public /* synthetic */ Void lambda$setUserId$5$UniversalFirebaseAnalyticsModule(String str) throws Exception {
        FirebaseAnalytics.getInstance(getContext()).setUserId(str);
        return null;
    }

    /* access modifiers changed from: 0000 */
    public Task<Void> setUserProperty(String str, String str2) {
        return Tasks.call(new Callable(str, str2) {
            private final /* synthetic */ String f$1;
            private final /* synthetic */ String f$2;

            {
                this.f$1 = r2;
                this.f$2 = r3;
            }

            public final Object call() {
                return UniversalFirebaseAnalyticsModule.this.lambda$setUserProperty$6$UniversalFirebaseAnalyticsModule(this.f$1, this.f$2);
            }
        });
    }

    public /* synthetic */ Void lambda$setUserProperty$6$UniversalFirebaseAnalyticsModule(String str, String str2) throws Exception {
        FirebaseAnalytics.getInstance(getContext()).setUserProperty(str, str2);
        return null;
    }

    /* access modifiers changed from: 0000 */
    public Task<Void> setUserProperties(Bundle bundle) {
        return Tasks.call(new Callable(bundle) {
            private final /* synthetic */ Bundle f$1;

            {
                this.f$1 = r2;
            }

            public final Object call() {
                return UniversalFirebaseAnalyticsModule.this.lambda$setUserProperties$7$UniversalFirebaseAnalyticsModule(this.f$1);
            }
        });
    }

    public /* synthetic */ Void lambda$setUserProperties$7$UniversalFirebaseAnalyticsModule(Bundle bundle) throws Exception {
        Set<String> keySet = bundle.keySet();
        FirebaseAnalytics instance = FirebaseAnalytics.getInstance(getContext());
        for (String str : keySet) {
            instance.setUserProperty(str, (String) bundle.get(str));
        }
        return null;
    }

    /* access modifiers changed from: 0000 */
    public Task<Void> resetAnalyticsData() {
        return Tasks.call(new Callable() {
            public final Object call() {
                return UniversalFirebaseAnalyticsModule.this.lambda$resetAnalyticsData$8$UniversalFirebaseAnalyticsModule();
            }
        });
    }

    public /* synthetic */ Void lambda$resetAnalyticsData$8$UniversalFirebaseAnalyticsModule() throws Exception {
        FirebaseAnalytics.getInstance(getContext()).resetAnalyticsData();
        return null;
    }
}
