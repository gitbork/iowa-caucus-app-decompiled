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
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.analytics.FirebaseAnalytics.Param;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.ListenerRegistration;
import com.google.firebase.firestore.MetadataChanges;
import com.google.firebase.firestore.SetOptions;
import com.google.firebase.firestore.Source;
import com.google.firebase.firestore.WriteBatch;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import p006io.invertase.firebase.common.ReactNativeFirebaseEventEmitter;
import p006io.invertase.firebase.common.ReactNativeFirebaseModule;
import p006io.sentry.marshaller.json.JsonMarshaller;

/* renamed from: io.invertase.firebase.firestore.ReactNativeFirebaseFirestoreDocumentModule */
public class ReactNativeFirebaseFirestoreDocumentModule extends ReactNativeFirebaseModule {
    private static final String SERVICE_NAME = "FirestoreDocument";
    private static SparseArray<ListenerRegistration> documentSnapshotListeners = new SparseArray<>();

    ReactNativeFirebaseFirestoreDocumentModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext, SERVICE_NAME);
    }

    public void onCatalystInstanceDestroy() {
        super.onCatalystInstanceDestroy();
        int size = documentSnapshotListeners.size();
        for (int i = 0; i < size; i++) {
            ((ListenerRegistration) documentSnapshotListeners.get(documentSnapshotListeners.keyAt(i))).remove();
        }
        documentSnapshotListeners.clear();
    }

    @ReactMethod
    public void documentOnSnapshot(String str, String str2, int i, ReadableMap readableMap) {
        MetadataChanges metadataChanges;
        if (documentSnapshotListeners.get(i) == null) {
            DocumentReference documentForFirestore = UniversalFirebaseFirestoreCommon.getDocumentForFirestore(UniversalFirebaseFirestoreCommon.getFirestoreForApp(str), str2);
            C2241xadd2eb3f r0 = new EventListener(i, str) {
                private final /* synthetic */ int f$1;
                private final /* synthetic */ String f$2;

                {
                    this.f$1 = r2;
                    this.f$2 = r3;
                }

                public final void onEvent(Object obj, FirebaseFirestoreException firebaseFirestoreException) {
                    ReactNativeFirebaseFirestoreDocumentModule.this.mo35588xfbfac832(this.f$1, this.f$2, (DocumentSnapshot) obj, firebaseFirestoreException);
                }
            };
            if (readableMap != null) {
                String str3 = "includeMetadataChanges";
                if (readableMap.hasKey(str3) && readableMap.getBoolean(str3)) {
                    metadataChanges = MetadataChanges.INCLUDE;
                    documentSnapshotListeners.put(i, documentForFirestore.addSnapshotListener(metadataChanges, (EventListener<DocumentSnapshot>) r0));
                }
            }
            metadataChanges = MetadataChanges.EXCLUDE;
            documentSnapshotListeners.put(i, documentForFirestore.addSnapshotListener(metadataChanges, (EventListener<DocumentSnapshot>) r0));
        }
    }

    /* renamed from: lambda$documentOnSnapshot$0$ReactNativeFirebaseFirestoreDocumentModule */
    public /* synthetic */ void mo35588xfbfac832(int i, String str, DocumentSnapshot documentSnapshot, FirebaseFirestoreException firebaseFirestoreException) {
        if (firebaseFirestoreException != null) {
            ListenerRegistration listenerRegistration = (ListenerRegistration) documentSnapshotListeners.get(i);
            if (listenerRegistration != null) {
                listenerRegistration.remove();
                documentSnapshotListeners.remove(i);
            }
            sendOnSnapshotError(str, i, firebaseFirestoreException);
            return;
        }
        sendOnSnapshotEvent(str, i, documentSnapshot);
    }

    @ReactMethod
    public void documentOffSnapshot(String str, int i) {
        ListenerRegistration listenerRegistration = (ListenerRegistration) documentSnapshotListeners.get(i);
        if (listenerRegistration != null) {
            listenerRegistration.remove();
            documentSnapshotListeners.remove(i);
        }
    }

    @ReactMethod
    public void documentGet(String str, String str2, ReadableMap readableMap, Promise promise) {
        Source source;
        DocumentReference documentForFirestore = UniversalFirebaseFirestoreCommon.getDocumentForFirestore(UniversalFirebaseFirestoreCommon.getFirestoreForApp(str), str2);
        if (readableMap != null) {
            String str3 = Param.SOURCE;
            if (readableMap.hasKey(str3)) {
                String string = readableMap.getString(str3);
                if ("server".equals(string)) {
                    source = Source.SERVER;
                } else if ("cache".equals(string)) {
                    source = Source.CACHE;
                } else {
                    source = Source.DEFAULT;
                }
                Tasks.call(getExecutor(), new Callable(source) {
                    private final /* synthetic */ Source f$1;

                    {
                        this.f$1 = r2;
                    }

                    public final Object call() {
                        return ReactNativeFirebaseFirestoreSerialize.snapshotToWritableMap((DocumentSnapshot) Tasks.await(DocumentReference.this.get(this.f$1)));
                    }
                }).addOnCompleteListener(new OnCompleteListener() {
                    public final void onComplete(Task task) {
                        ReactNativeFirebaseFirestoreDocumentModule.lambda$documentGet$2(Promise.this, task);
                    }
                });
            }
        }
        source = Source.DEFAULT;
        Tasks.call(getExecutor(), new Callable(source) {
            private final /* synthetic */ Source f$1;

            {
                this.f$1 = r2;
            }

            public final Object call() {
                return ReactNativeFirebaseFirestoreSerialize.snapshotToWritableMap((DocumentSnapshot) Tasks.await(DocumentReference.this.get(this.f$1)));
            }
        }).addOnCompleteListener(new OnCompleteListener() {
            public final void onComplete(Task task) {
                ReactNativeFirebaseFirestoreDocumentModule.lambda$documentGet$2(Promise.this, task);
            }
        });
    }

    static /* synthetic */ void lambda$documentGet$2(Promise promise, Task task) {
        if (task.isSuccessful()) {
            promise.resolve(task.getResult());
        } else {
            ReactNativeFirebaseFirestoreCommon.rejectPromiseFirestoreException(promise, task.getException());
        }
    }

    @ReactMethod
    public void documentDelete(String str, String str2, Promise promise) {
        DocumentReference documentForFirestore = UniversalFirebaseFirestoreCommon.getDocumentForFirestore(UniversalFirebaseFirestoreCommon.getFirestoreForApp(str), str2);
        ExecutorService executor = getExecutor();
        documentForFirestore.getClass();
        Tasks.call(executor, new Callable() {
            public final Object call() {
                return DocumentReference.this.delete();
            }
        }).addOnCompleteListener(new OnCompleteListener() {
            public final void onComplete(Task task) {
                ReactNativeFirebaseFirestoreDocumentModule.lambda$documentDelete$3(Promise.this, task);
            }
        });
    }

    static /* synthetic */ void lambda$documentDelete$3(Promise promise, Task task) {
        if (task.isSuccessful()) {
            promise.resolve(null);
        } else {
            ReactNativeFirebaseFirestoreCommon.rejectPromiseFirestoreException(promise, task.getException());
        }
    }

    @ReactMethod
    public void documentSet(String str, String str2, ReadableMap readableMap, ReadableMap readableMap2, Promise promise) {
        FirebaseFirestore firestoreForApp = UniversalFirebaseFirestoreCommon.getFirestoreForApp(str);
        Tasks.call(getExecutor(), new Callable(readableMap) {
            private final /* synthetic */ ReadableMap f$1;

            {
                this.f$1 = r2;
            }

            public final Object call() {
                return ReactNativeFirebaseFirestoreSerialize.parseReadableMap(FirebaseFirestore.this, this.f$1);
            }
        }).continueWithTask(getExecutor(), new Continuation(UniversalFirebaseFirestoreCommon.getDocumentForFirestore(firestoreForApp, str2)) {
            private final /* synthetic */ DocumentReference f$1;

            {
                this.f$1 = r2;
            }

            public final Object then(Task task) {
                return ReactNativeFirebaseFirestoreDocumentModule.lambda$documentSet$5(ReadableMap.this, this.f$1, task);
            }
        }).addOnCompleteListener(new OnCompleteListener() {
            public final void onComplete(Task task) {
                ReactNativeFirebaseFirestoreDocumentModule.lambda$documentSet$6(Promise.this, task);
            }
        });
    }

    static /* synthetic */ Task lambda$documentSet$5(ReadableMap readableMap, DocumentReference documentReference, Task task) throws Exception {
        Object result = task.getResult();
        result.getClass();
        Map map = (Map) result;
        String str = "merge";
        if (readableMap.hasKey(str) && readableMap.getBoolean(str)) {
            return documentReference.set(map, SetOptions.merge());
        }
        String str2 = "mergeFields";
        if (!readableMap.hasKey(str2)) {
            return documentReference.set(map);
        }
        ArrayList arrayList = new ArrayList();
        ReadableArray array = readableMap.getArray(str2);
        array.getClass();
        Iterator it = array.toArrayList().iterator();
        while (it.hasNext()) {
            arrayList.add((String) it.next());
        }
        return documentReference.set(map, SetOptions.mergeFields((List<String>) arrayList));
    }

    static /* synthetic */ void lambda$documentSet$6(Promise promise, Task task) {
        if (task.isSuccessful()) {
            promise.resolve(null);
        } else {
            ReactNativeFirebaseFirestoreCommon.rejectPromiseFirestoreException(promise, task.getException());
        }
    }

    @ReactMethod
    public void documentUpdate(String str, String str2, ReadableMap readableMap, Promise promise) {
        FirebaseFirestore firestoreForApp = UniversalFirebaseFirestoreCommon.getFirestoreForApp(str);
        Tasks.call(getExecutor(), new Callable(readableMap) {
            private final /* synthetic */ ReadableMap f$1;

            {
                this.f$1 = r2;
            }

            public final Object call() {
                return ReactNativeFirebaseFirestoreSerialize.parseReadableMap(FirebaseFirestore.this, this.f$1);
            }
        }).continueWithTask(getExecutor(), new Continuation() {
            public final Object then(Task task) {
                return ReactNativeFirebaseFirestoreDocumentModule.lambda$documentUpdate$8(DocumentReference.this, task);
            }
        }).addOnCompleteListener(new OnCompleteListener() {
            public final void onComplete(Task task) {
                ReactNativeFirebaseFirestoreDocumentModule.lambda$documentUpdate$9(Promise.this, task);
            }
        });
    }

    static /* synthetic */ Task lambda$documentUpdate$8(DocumentReference documentReference, Task task) throws Exception {
        Object result = task.getResult();
        result.getClass();
        return documentReference.update((Map) result);
    }

    static /* synthetic */ void lambda$documentUpdate$9(Promise promise, Task task) {
        if (task.isSuccessful()) {
            promise.resolve(null);
        } else {
            ReactNativeFirebaseFirestoreCommon.rejectPromiseFirestoreException(promise, task.getException());
        }
    }

    @ReactMethod
    public void documentBatch(String str, ReadableArray readableArray, Promise promise) {
        FirebaseFirestore firestoreForApp = UniversalFirebaseFirestoreCommon.getFirestoreForApp(str);
        Tasks.call(getExecutor(), new Callable(readableArray) {
            private final /* synthetic */ ReadableArray f$1;

            {
                this.f$1 = r2;
            }

            public final Object call() {
                return ReactNativeFirebaseFirestoreSerialize.parseDocumentBatches(FirebaseFirestore.this, this.f$1);
            }
        }).continueWithTask(getExecutor(), new Continuation() {
            public final Object then(Task task) {
                return ReactNativeFirebaseFirestoreDocumentModule.lambda$documentBatch$11(FirebaseFirestore.this, task);
            }
        }).addOnCompleteListener(new OnCompleteListener() {
            public final void onComplete(Task task) {
                ReactNativeFirebaseFirestoreDocumentModule.lambda$documentBatch$12(Promise.this, task);
            }
        });
    }

    static /* synthetic */ Task lambda$documentBatch$11(FirebaseFirestore firebaseFirestore, Task task) throws Exception {
        WriteBatch batch = firebaseFirestore.batch();
        for (Map map : (List) task.getResult()) {
            String str = (String) map.get("type");
            Map map2 = (Map) map.get("data");
            DocumentReference documentForFirestore = UniversalFirebaseFirestoreCommon.getDocumentForFirestore(firebaseFirestore, (String) map.get("path"));
            str.getClass();
            String str2 = str;
            char c = 65535;
            int hashCode = str2.hashCode();
            if (hashCode != -1785516855) {
                if (hashCode != 81986) {
                    if (hashCode == 2012838315 && str2.equals("DELETE")) {
                        c = 0;
                    }
                } else if (str2.equals("SET")) {
                    c = 2;
                }
            } else if (str2.equals("UPDATE")) {
                c = 1;
            }
            if (c == 0) {
                batch = batch.delete(documentForFirestore);
            } else if (c == 1) {
                map2.getClass();
                batch = batch.update(documentForFirestore, map2);
            } else if (c == 2) {
                Map map3 = (Map) map.get("options");
                map3.getClass();
                String str3 = "merge";
                if (!map3.containsKey(str3) || !((Boolean) map3.get(str3)).booleanValue()) {
                    String str4 = "mergeFields";
                    if (map3.containsKey(str4)) {
                        ArrayList arrayList = new ArrayList();
                        List<String> list = (List) map3.get(str4);
                        list.getClass();
                        for (String add : list) {
                            arrayList.add(add);
                        }
                        map2.getClass();
                        batch = batch.set(documentForFirestore, map2, SetOptions.mergeFields((List<String>) arrayList));
                    } else {
                        map2.getClass();
                        batch = batch.set(documentForFirestore, map2);
                    }
                } else {
                    map2.getClass();
                    batch = batch.set(documentForFirestore, map2, SetOptions.merge());
                }
            }
        }
        return batch.commit();
    }

    static /* synthetic */ void lambda$documentBatch$12(Promise promise, Task task) {
        if (task.isSuccessful()) {
            promise.resolve(null);
        } else {
            ReactNativeFirebaseFirestoreCommon.rejectPromiseFirestoreException(promise, task.getException());
        }
    }

    private void sendOnSnapshotEvent(String str, int i, DocumentSnapshot documentSnapshot) {
        Tasks.call(getExecutor(), new Callable() {
            public final Object call() {
                return ReactNativeFirebaseFirestoreSerialize.snapshotToWritableMap(DocumentSnapshot.this);
            }
        }).addOnCompleteListener(new OnCompleteListener(str, i) {
            private final /* synthetic */ String f$1;
            private final /* synthetic */ int f$2;

            {
                this.f$1 = r2;
                this.f$2 = r3;
            }

            public final void onComplete(Task task) {
                ReactNativeFirebaseFirestoreDocumentModule.this.mo35589x328d9804(this.f$1, this.f$2, task);
            }
        });
    }

    /* renamed from: lambda$sendOnSnapshotEvent$14$ReactNativeFirebaseFirestoreDocumentModule */
    public /* synthetic */ void mo35589x328d9804(String str, int i, Task task) {
        if (task.isSuccessful()) {
            WritableMap createMap = Arguments.createMap();
            createMap.putMap("snapshot", (ReadableMap) task.getResult());
            ReactNativeFirebaseEventEmitter.getSharedInstance().sendEvent(new ReactNativeFirebaseFirestoreEvent("firestore_document_sync_event", createMap, str, i));
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
        ReactNativeFirebaseEventEmitter.getSharedInstance().sendEvent(new ReactNativeFirebaseFirestoreEvent("firestore_document_sync_event", createMap, str, i));
    }
}
