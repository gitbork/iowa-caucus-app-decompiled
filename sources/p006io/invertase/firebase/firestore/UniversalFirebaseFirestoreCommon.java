package p006io.invertase.firebase.firestore;

import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings.Builder;
import com.google.firebase.firestore.Query;
import java.util.HashMap;
import p006io.invertase.firebase.common.UniversalFirebasePreferences;

/* renamed from: io.invertase.firebase.firestore.UniversalFirebaseFirestoreCommon */
public class UniversalFirebaseFirestoreCommon {
    private static HashMap<String, Boolean> settingsLock = new HashMap<>();

    static FirebaseFirestore getFirestoreForApp(String str) {
        FirebaseFirestore instance = FirebaseFirestore.getInstance(FirebaseApp.getInstance(str));
        setFirestoreSettings(instance, str);
        return instance;
    }

    private static void setFirestoreSettings(FirebaseFirestore firebaseFirestore, String str) {
        if (!settingsLock.containsKey(str)) {
            UniversalFirebasePreferences sharedInstance = UniversalFirebasePreferences.getSharedInstance();
            Builder builder = new Builder();
            StringBuilder sb = new StringBuilder();
            sb.append(UniversalFirebaseFirestoreStatics.FIRESTORE_CACHE_SIZE);
            String str2 = "_";
            sb.append(str2);
            sb.append(str);
            int intValue = sharedInstance.getIntValue(sb.toString(), (int) firebaseFirestore.getFirestoreSettings().getCacheSizeBytes());
            StringBuilder sb2 = new StringBuilder();
            sb2.append(UniversalFirebaseFirestoreStatics.FIRESTORE_HOST);
            sb2.append(str2);
            sb2.append(str);
            String stringValue = sharedInstance.getStringValue(sb2.toString(), firebaseFirestore.getFirestoreSettings().getHost());
            StringBuilder sb3 = new StringBuilder();
            sb3.append(UniversalFirebaseFirestoreStatics.FIRESTORE_PERSISTENCE);
            sb3.append(str2);
            sb3.append(str);
            boolean booleanValue = sharedInstance.getBooleanValue(sb3.toString(), firebaseFirestore.getFirestoreSettings().isPersistenceEnabled());
            StringBuilder sb4 = new StringBuilder();
            sb4.append(UniversalFirebaseFirestoreStatics.FIRESTORE_SSL);
            sb4.append(str2);
            sb4.append(str);
            boolean booleanValue2 = sharedInstance.getBooleanValue(sb4.toString(), firebaseFirestore.getFirestoreSettings().isSslEnabled());
            if (intValue == -1) {
                builder.setCacheSizeBytes(-1);
            } else {
                builder.setCacheSizeBytes((long) intValue);
            }
            builder.setHost(stringValue);
            builder.setPersistenceEnabled(booleanValue);
            builder.setSslEnabled(booleanValue2);
            firebaseFirestore.setFirestoreSettings(builder.build());
            settingsLock.put(str, Boolean.valueOf(true));
        }
    }

    static Query getQueryForFirestore(FirebaseFirestore firebaseFirestore, String str, String str2) {
        if ("collectionGroup".equals(str2)) {
            return firebaseFirestore.collectionGroup(str);
        }
        return firebaseFirestore.collection(str);
    }

    static DocumentReference getDocumentForFirestore(FirebaseFirestore firebaseFirestore, String str) {
        return firebaseFirestore.document(str);
    }
}
