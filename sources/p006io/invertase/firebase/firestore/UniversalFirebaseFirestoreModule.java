package p006io.invertase.firebase.firestore;

import android.content.Context;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import java.util.Map;
import java.util.concurrent.Callable;
import p006io.invertase.firebase.common.UniversalFirebaseModule;
import p006io.invertase.firebase.common.UniversalFirebasePreferences;

/* renamed from: io.invertase.firebase.firestore.UniversalFirebaseFirestoreModule */
public class UniversalFirebaseFirestoreModule extends UniversalFirebaseModule {
    UniversalFirebaseFirestoreModule(Context context, String str) {
        super(context, str);
    }

    /* access modifiers changed from: 0000 */
    public Task<Void> disableNetwork(String str) {
        return UniversalFirebaseFirestoreCommon.getFirestoreForApp(str).disableNetwork();
    }

    /* access modifiers changed from: 0000 */
    public Task<Void> enableNetwork(String str) {
        return UniversalFirebaseFirestoreCommon.getFirestoreForApp(str).enableNetwork();
    }

    /* access modifiers changed from: 0000 */
    public Task<Void> settings(String str, Map<String, Object> map) {
        return Tasks.call(getExecutor(), new Callable(map, str) {
            private final /* synthetic */ Map f$0;
            private final /* synthetic */ String f$1;

            {
                this.f$0 = r1;
                this.f$1 = r2;
            }

            public final Object call() {
                return UniversalFirebaseFirestoreModule.lambda$settings$0(this.f$0, this.f$1);
            }
        });
    }

    static /* synthetic */ Void lambda$settings$0(Map map, String str) throws Exception {
        String str2 = "cacheSizeBytes";
        String str3 = "_";
        if (map.containsKey(str2)) {
            Double d = (Double) map.get(str2);
            UniversalFirebasePreferences sharedInstance = UniversalFirebasePreferences.getSharedInstance();
            StringBuilder sb = new StringBuilder();
            sb.append(UniversalFirebaseFirestoreStatics.FIRESTORE_CACHE_SIZE);
            sb.append(str3);
            sb.append(str);
            String sb2 = sb.toString();
            d.getClass();
            sharedInstance.setIntValue(sb2, d.intValue());
        }
        String str4 = "host";
        if (map.containsKey(str4)) {
            UniversalFirebasePreferences sharedInstance2 = UniversalFirebasePreferences.getSharedInstance();
            StringBuilder sb3 = new StringBuilder();
            sb3.append(UniversalFirebaseFirestoreStatics.FIRESTORE_HOST);
            sb3.append(str3);
            sb3.append(str);
            sharedInstance2.setStringValue(sb3.toString(), (String) map.get(str4));
        }
        String str5 = "persistence";
        if (map.containsKey(str5)) {
            UniversalFirebasePreferences sharedInstance3 = UniversalFirebasePreferences.getSharedInstance();
            StringBuilder sb4 = new StringBuilder();
            sb4.append(UniversalFirebaseFirestoreStatics.FIRESTORE_PERSISTENCE);
            sb4.append(str3);
            sb4.append(str);
            sharedInstance3.setBooleanValue(sb4.toString(), ((Boolean) map.get(str5)).booleanValue());
        }
        String str6 = "ssl";
        if (map.containsKey(str6)) {
            UniversalFirebasePreferences sharedInstance4 = UniversalFirebasePreferences.getSharedInstance();
            StringBuilder sb5 = new StringBuilder();
            sb5.append(UniversalFirebaseFirestoreStatics.FIRESTORE_SSL);
            sb5.append(str3);
            sb5.append(str);
            sharedInstance4.setBooleanValue(sb5.toString(), ((Boolean) map.get(str6)).booleanValue());
        }
        return null;
    }
}
