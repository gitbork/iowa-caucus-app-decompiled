package p006io.invertase.firebase.firestore;

import android.util.SparseArray;
import androidx.core.p003os.EnvironmentCompat;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableMap;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.analytics.FirebaseAnalytics.Param;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.ListenerRegistration;
import com.google.firebase.firestore.MetadataChanges;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.Source;
import java.util.concurrent.Callable;
import p006io.invertase.firebase.common.ReactNativeFirebaseEventEmitter;
import p006io.invertase.firebase.common.ReactNativeFirebaseModule;
import p006io.sentry.marshaller.json.JsonMarshaller;

/* renamed from: io.invertase.firebase.firestore.ReactNativeFirebaseFirestoreCollectionModule */
public class ReactNativeFirebaseFirestoreCollectionModule extends ReactNativeFirebaseModule {
    private static final String SERVICE_NAME = "FirestoreCollection";
    private static SparseArray<ListenerRegistration> collectionSnapshotListeners = new SparseArray<>();

    ReactNativeFirebaseFirestoreCollectionModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext, SERVICE_NAME);
    }

    public void onCatalystInstanceDestroy() {
        super.onCatalystInstanceDestroy();
        int size = collectionSnapshotListeners.size();
        for (int i = 0; i < size; i++) {
            ((ListenerRegistration) collectionSnapshotListeners.get(collectionSnapshotListeners.keyAt(i))).remove();
        }
        collectionSnapshotListeners.clear();
    }

    @ReactMethod
    public void collectionOnSnapshot(String str, String str2, String str3, ReadableArray readableArray, ReadableArray readableArray2, ReadableMap readableMap, int i, ReadableMap readableMap2) {
        MetadataChanges metadataChanges;
        if (collectionSnapshotListeners.get(i) == null) {
            ReactNativeFirebaseFirestoreQuery reactNativeFirebaseFirestoreQuery = new ReactNativeFirebaseFirestoreQuery(UniversalFirebaseFirestoreCommon.getQueryForFirestore(UniversalFirebaseFirestoreCommon.getFirestoreForApp(str), str2, str3), readableArray, readableArray2, readableMap);
            if (readableMap2 != null) {
                String str4 = "includeMetadataChanges";
                if (readableMap2.hasKey(str4) && readableMap2.getBoolean(str4)) {
                    metadataChanges = MetadataChanges.INCLUDE;
                    collectionSnapshotListeners.put(i, reactNativeFirebaseFirestoreQuery.query.addSnapshotListener(metadataChanges, (EventListener<QuerySnapshot>) new EventListener(i, str, metadataChanges) {
                        private final /* synthetic */ int f$1;
                        private final /* synthetic */ String f$2;
                        private final /* synthetic */ MetadataChanges f$3;

                        {
                            this.f$1 = r2;
                            this.f$2 = r3;
                            this.f$3 = r4;
                        }

                        public final void onEvent(Object obj, FirebaseFirestoreException firebaseFirestoreException) {
                            ReactNativeFirebaseFirestoreCollectionModule.this.mo35579x1943bbd2(this.f$1, this.f$2, this.f$3, (QuerySnapshot) obj, firebaseFirestoreException);
                        }
                    }));
                }
            }
            metadataChanges = MetadataChanges.EXCLUDE;
            collectionSnapshotListeners.put(i, reactNativeFirebaseFirestoreQuery.query.addSnapshotListener(metadataChanges, (EventListener<QuerySnapshot>) new EventListener(i, str, metadataChanges) {
                private final /* synthetic */ int f$1;
                private final /* synthetic */ String f$2;
                private final /* synthetic */ MetadataChanges f$3;

                {
                    this.f$1 = r2;
                    this.f$2 = r3;
                    this.f$3 = r4;
                }

                public final void onEvent(Object obj, FirebaseFirestoreException firebaseFirestoreException) {
                    ReactNativeFirebaseFirestoreCollectionModule.this.mo35579x1943bbd2(this.f$1, this.f$2, this.f$3, (QuerySnapshot) obj, firebaseFirestoreException);
                }
            }));
        }
    }

    /* renamed from: lambda$collectionOnSnapshot$0$ReactNativeFirebaseFirestoreCollectionModule */
    public /* synthetic */ void mo35579x1943bbd2(int i, String str, MetadataChanges metadataChanges, QuerySnapshot querySnapshot, FirebaseFirestoreException firebaseFirestoreException) {
        if (firebaseFirestoreException != null) {
            ListenerRegistration listenerRegistration = (ListenerRegistration) collectionSnapshotListeners.get(i);
            if (listenerRegistration != null) {
                listenerRegistration.remove();
                collectionSnapshotListeners.remove(i);
            }
            sendOnSnapshotError(str, i, firebaseFirestoreException);
            return;
        }
        sendOnSnapshotEvent(str, i, querySnapshot, metadataChanges);
    }

    @ReactMethod
    public void collectionOffSnapshot(String str, int i) {
        ListenerRegistration listenerRegistration = (ListenerRegistration) collectionSnapshotListeners.get(i);
        if (listenerRegistration != null) {
            listenerRegistration.remove();
            collectionSnapshotListeners.remove(i);
        }
    }

    @ReactMethod
    public void collectionGet(String str, String str2, String str3, ReadableArray readableArray, ReadableArray readableArray2, ReadableMap readableMap, ReadableMap readableMap2, Promise promise) {
        Source source;
        ReactNativeFirebaseFirestoreQuery reactNativeFirebaseFirestoreQuery = new ReactNativeFirebaseFirestoreQuery(UniversalFirebaseFirestoreCommon.getQueryForFirestore(UniversalFirebaseFirestoreCommon.getFirestoreForApp(str), str2, str3), readableArray, readableArray2, readableMap);
        if (readableMap2 != null) {
            String str4 = Param.SOURCE;
            if (readableMap2.hasKey(str4)) {
                String string = readableMap2.getString(str4);
                if ("server".equals(string)) {
                    source = Source.SERVER;
                } else if ("cache".equals(string)) {
                    source = Source.CACHE;
                } else {
                    source = Source.DEFAULT;
                }
                reactNativeFirebaseFirestoreQuery.get(getExecutor(), source).addOnCompleteListener(new OnCompleteListener() {
                    public final void onComplete(Task task) {
                        ReactNativeFirebaseFirestoreCollectionModule.lambda$collectionGet$1(Promise.this, task);
                    }
                });
            }
        }
        source = Source.DEFAULT;
        reactNativeFirebaseFirestoreQuery.get(getExecutor(), source).addOnCompleteListener(new OnCompleteListener() {
            public final void onComplete(Task task) {
                ReactNativeFirebaseFirestoreCollectionModule.lambda$collectionGet$1(Promise.this, task);
            }
        });
    }

    static /* synthetic */ void lambda$collectionGet$1(Promise promise, Task task) {
        if (task.isSuccessful()) {
            promise.resolve(task.getResult());
        } else {
            ReactNativeFirebaseFirestoreCommon.rejectPromiseFirestoreException(promise, task.getException());
        }
    }

    private void sendOnSnapshotEvent(String str, int i, QuerySnapshot querySnapshot, MetadataChanges metadataChanges) {
        Tasks.call(getExecutor(), new Callable(metadataChanges) {
            private final /* synthetic */ MetadataChanges f$1;

            {
                this.f$1 = r2;
            }

            public final Object call() {
                return ReactNativeFirebaseFirestoreSerialize.snapshotToWritableMap("onSnapshot", QuerySnapshot.this, this.f$1);
            }
        }).addOnCompleteListener(new OnCompleteListener(str, i) {
            private final /* synthetic */ String f$1;
            private final /* synthetic */ int f$2;

            {
                this.f$1 = r2;
                this.f$2 = r3;
            }

            public final void onComplete(Task task) {
                ReactNativeFirebaseFirestoreCollectionModule.this.mo35580xe1edca5b(this.f$1, this.f$2, task);
            }
        });
    }

    /* renamed from: lambda$sendOnSnapshotEvent$3$ReactNativeFirebaseFirestoreCollectionModule */
    public /* synthetic */ void mo35580xe1edca5b(String str, int i, Task task) {
        if (task.isSuccessful()) {
            WritableMap createMap = Arguments.createMap();
            createMap.putMap("snapshot", (ReadableMap) task.getResult());
            ReactNativeFirebaseEventEmitter.getSharedInstance().sendEvent(new ReactNativeFirebaseFirestoreEvent("firestore_collection_sync_event", createMap, str, i));
            return;
        }
        sendOnSnapshotError(str, i, task.getException());
    }

    private void sendOnSnapshotError(String str, int i, Exception exc) {
        WritableMap createMap = Arguments.createMap();
        WritableMap createMap2 = Arguments.createMap();
        boolean z = exc instanceof FirebaseFirestoreException;
        String str2 = JsonMarshaller.MESSAGE;
        String str3 = "code";
        if (z) {
            UniversalFirebaseFirestoreException universalFirebaseFirestoreException = new UniversalFirebaseFirestoreException((FirebaseFirestoreException) exc, exc.getCause());
            createMap2.putString(str3, universalFirebaseFirestoreException.getCode());
            createMap2.putString(str2, universalFirebaseFirestoreException.getMessage());
        } else {
            createMap2.putString(str3, EnvironmentCompat.MEDIA_UNKNOWN);
            createMap2.putString(str2, "An unknown error occurred");
        }
        createMap.putMap("error", createMap2);
        ReactNativeFirebaseEventEmitter.getSharedInstance().sendEvent(new ReactNativeFirebaseFirestoreEvent("firestore_collection_sync_event", createMap, str, i));
    }
}
