package p006io.invertase.firebase.firestore;

import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableMap;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.Query.Direction;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.Source;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import p006io.invertase.firebase.common.RCTConvertFirebase;

/* renamed from: io.invertase.firebase.firestore.ReactNativeFirebaseFirestoreQuery */
public class ReactNativeFirebaseFirestoreQuery {
    Query query;

    ReactNativeFirebaseFirestoreQuery(Query query2, ReadableArray readableArray, ReadableArray readableArray2, ReadableMap readableMap) {
        this.query = query2;
        applyFilters(readableArray);
        applyOrders(readableArray2);
        applyOptions(readableMap);
    }

    public Task<WritableMap> get(Executor executor, Source source) {
        return Tasks.call(executor, new Callable(source) {
            private final /* synthetic */ Source f$1;

            {
                this.f$1 = r2;
            }

            public final Object call() {
                return ReactNativeFirebaseFirestoreQuery.this.lambda$get$0$ReactNativeFirebaseFirestoreQuery(this.f$1);
            }
        });
    }

    public /* synthetic */ WritableMap lambda$get$0$ReactNativeFirebaseFirestoreQuery(Source source) throws Exception {
        return ReactNativeFirebaseFirestoreSerialize.snapshotToWritableMap("get", (QuerySnapshot) Tasks.await(this.query.get(source)), null);
    }

    private void applyFilters(ReadableArray readableArray) {
        for (int i = 0; i < readableArray.size(); i++) {
            ReadableMap map = readableArray.getMap(i);
            map.getClass();
            String string = map.getString("fieldPath");
            String string2 = map.getString("operator");
            ReadableArray array = map.getArray("value");
            FirebaseFirestore firestore = this.query.getFirestore();
            array.getClass();
            Object parseTypeMap = ReactNativeFirebaseFirestoreSerialize.parseTypeMap(firestore, array);
            string2.getClass();
            String str = string2;
            char c = 65535;
            switch (str.hashCode()) {
                case -2081783184:
                    if (str.equals("LESS_THAN_OR_EQUAL")) {
                        c = 4;
                        break;
                    }
                    break;
                case -1112834937:
                    if (str.equals("LESS_THAN")) {
                        c = 3;
                        break;
                    }
                    break;
                case 66219796:
                    if (str.equals("EQUAL")) {
                        c = 0;
                        break;
                    }
                    break;
                case 67210597:
                    if (str.equals("ARRAY_CONTAINS")) {
                        c = 5;
                        break;
                    }
                    break;
                case 972152550:
                    if (str.equals("GREATER_THAN")) {
                        c = 1;
                        break;
                    }
                    break;
                case 989027057:
                    if (str.equals("GREATER_THAN_OR_EQUAL")) {
                        c = 2;
                        break;
                    }
                    break;
            }
            if (c == 0) {
                Query query2 = this.query;
                string.getClass();
                this.query = query2.whereEqualTo(string, parseTypeMap);
            } else if (c == 1) {
                Query query3 = this.query;
                string.getClass();
                String str2 = string;
                parseTypeMap.getClass();
                this.query = query3.whereGreaterThan(str2, parseTypeMap);
            } else if (c == 2) {
                Query query4 = this.query;
                string.getClass();
                String str3 = string;
                parseTypeMap.getClass();
                this.query = query4.whereGreaterThanOrEqualTo(str3, parseTypeMap);
            } else if (c == 3) {
                Query query5 = this.query;
                string.getClass();
                String str4 = string;
                parseTypeMap.getClass();
                this.query = query5.whereLessThan(str4, parseTypeMap);
            } else if (c == 4) {
                Query query6 = this.query;
                string.getClass();
                String str5 = string;
                parseTypeMap.getClass();
                this.query = query6.whereLessThanOrEqualTo(str5, parseTypeMap);
            } else if (c == 5) {
                Query query7 = this.query;
                string.getClass();
                String str6 = string;
                parseTypeMap.getClass();
                this.query = query7.whereArrayContains(str6, parseTypeMap);
            }
        }
    }

    private void applyOrders(ReadableArray readableArray) {
        for (Map map : RCTConvertFirebase.toArrayList(readableArray)) {
            String str = (String) map.get("fieldPath");
            String str2 = (String) map.get("direction");
            Query query2 = this.query;
            str.getClass();
            this.query = query2.orderBy(str, Direction.valueOf(str2));
        }
    }

    private void applyOptions(ReadableMap readableMap) {
        String str = "limit";
        if (readableMap.hasKey(str)) {
            this.query = this.query.limit((long) readableMap.getInt(str));
        }
        String str2 = "startAt";
        if (readableMap.hasKey(str2)) {
            List parseReadableArray = ReactNativeFirebaseFirestoreSerialize.parseReadableArray(this.query.getFirestore(), readableMap.getArray(str2));
            Query query2 = this.query;
            Object[] array = parseReadableArray.toArray();
            array.getClass();
            this.query = query2.startAt(array);
        }
        String str3 = "startAfter";
        if (readableMap.hasKey(str3)) {
            List parseReadableArray2 = ReactNativeFirebaseFirestoreSerialize.parseReadableArray(this.query.getFirestore(), readableMap.getArray(str3));
            Query query3 = this.query;
            Object[] array2 = parseReadableArray2.toArray();
            array2.getClass();
            this.query = query3.startAfter(array2);
        }
        String str4 = "endAt";
        if (readableMap.hasKey(str4)) {
            List parseReadableArray3 = ReactNativeFirebaseFirestoreSerialize.parseReadableArray(this.query.getFirestore(), readableMap.getArray(str4));
            Query query4 = this.query;
            Object[] array3 = parseReadableArray3.toArray();
            array3.getClass();
            this.query = query4.endAt(array3);
        }
        String str5 = "endBefore";
        if (readableMap.hasKey(str5)) {
            List parseReadableArray4 = ReactNativeFirebaseFirestoreSerialize.parseReadableArray(this.query.getFirestore(), readableMap.getArray(str5));
            Query query5 = this.query;
            Object[] array4 = parseReadableArray4.toArray();
            array4.getClass();
            this.query = query5.endBefore(array4);
        }
    }
}
