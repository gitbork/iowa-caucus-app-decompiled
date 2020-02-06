package p006io.invertase.firebase.firestore;

import android.util.Base64;
import android.util.Log;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableMapKeySetIterator;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.firebase.Timestamp;
import com.google.firebase.analytics.FirebaseAnalytics.Param;
import com.google.firebase.firestore.Blob;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentChange.Type;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldPath;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.GeoPoint;
import com.google.firebase.firestore.MetadataChanges;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.SnapshotMetadata;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.annotation.Nullable;
import p006io.invertase.firebase.common.RCTConvertFirebase;

/* renamed from: io.invertase.firebase.firestore.ReactNativeFirebaseFirestoreSerialize */
class ReactNativeFirebaseFirestoreSerialize {
    private static final String CHANGE_ADDED = "a";
    private static final String CHANGE_MODIFIED = "m";
    private static final String CHANGE_REMOVED = "r";
    private static final int INT_ARRAY = 10;
    private static final int INT_BLOB = 14;
    private static final int INT_BOOLEAN_FALSE = 6;
    private static final int INT_BOOLEAN_TRUE = 5;
    private static final int INT_DOCUMENTID = 4;
    private static final int INT_FIELDVALUE = 15;
    private static final int INT_GEOPOINT = 12;
    private static final int INT_NAN = 0;
    private static final int INT_NEGATIVE_INFINITY = 1;
    private static final int INT_NULL = 3;
    private static final int INT_NUMBER = 7;
    private static final int INT_OBJECT = 16;
    private static final int INT_POSITIVE_INFINITY = 2;
    private static final int INT_REFERENCE = 11;
    private static final int INT_STRING = 8;
    private static final int INT_STRING_EMPTY = 9;
    private static final int INT_TIMESTAMP = 13;
    private static final int INT_UNKNOWN = -999;
    private static final String KEY_CHANGES = "changes";
    private static final String KEY_DATA = "data";
    private static final String KEY_DOCUMENTS = "documents";
    private static final String KEY_DOC_CHANGE_DOCUMENT = "doc";
    private static final String KEY_DOC_CHANGE_NEW_INDEX = "ni";
    private static final String KEY_DOC_CHANGE_OLD_INDEX = "oi";
    private static final String KEY_DOC_CHANGE_TYPE = "type";
    private static final String KEY_EXISTS = "exists";
    private static final String KEY_META = "metadata";
    private static final String KEY_OPTIONS = "options";
    private static final String KEY_PATH = "path";
    private static final String TAG = "FirestoreSerialize";
    private static final String TYPE = "type";

    /* renamed from: io.invertase.firebase.firestore.ReactNativeFirebaseFirestoreSerialize$1 */
    static /* synthetic */ class C22641 {
        static final /* synthetic */ int[] $SwitchMap$com$google$firebase$firestore$DocumentChange$Type = new int[Type.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|8) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001f */
        static {
            /*
                com.google.firebase.firestore.DocumentChange$Type[] r0 = com.google.firebase.firestore.DocumentChange.Type.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$google$firebase$firestore$DocumentChange$Type = r0
                int[] r0 = $SwitchMap$com$google$firebase$firestore$DocumentChange$Type     // Catch:{ NoSuchFieldError -> 0x0014 }
                com.google.firebase.firestore.DocumentChange$Type r1 = com.google.firebase.firestore.DocumentChange.Type.ADDED     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                int[] r0 = $SwitchMap$com$google$firebase$firestore$DocumentChange$Type     // Catch:{ NoSuchFieldError -> 0x001f }
                com.google.firebase.firestore.DocumentChange$Type r1 = com.google.firebase.firestore.DocumentChange.Type.MODIFIED     // Catch:{ NoSuchFieldError -> 0x001f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                int[] r0 = $SwitchMap$com$google$firebase$firestore$DocumentChange$Type     // Catch:{ NoSuchFieldError -> 0x002a }
                com.google.firebase.firestore.DocumentChange$Type r1 = com.google.firebase.firestore.DocumentChange.Type.REMOVED     // Catch:{ NoSuchFieldError -> 0x002a }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002a }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002a }
            L_0x002a:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: p006io.invertase.firebase.firestore.ReactNativeFirebaseFirestoreSerialize.C22641.<clinit>():void");
        }
    }

    ReactNativeFirebaseFirestoreSerialize() {
    }

    /* access modifiers changed from: 0000 */
    public static WritableMap snapshotToWritableMap(DocumentSnapshot documentSnapshot) {
        WritableArray createArray = Arguments.createArray();
        WritableMap createMap = Arguments.createMap();
        SnapshotMetadata metadata = documentSnapshot.getMetadata();
        createArray.pushBoolean(metadata.isFromCache());
        createArray.pushBoolean(metadata.hasPendingWrites());
        createMap.putArray(KEY_META, createArray);
        createMap.putString(KEY_PATH, documentSnapshot.getReference().getPath());
        createMap.putBoolean(KEY_EXISTS, documentSnapshot.exists());
        if (documentSnapshot.exists() && documentSnapshot.getData() != null) {
            createMap.putMap("data", objectMapToWritable(documentSnapshot.getData()));
        }
        return createMap;
    }

    /* access modifiers changed from: 0000 */
    public static WritableMap snapshotToWritableMap(String str, QuerySnapshot querySnapshot, @Nullable MetadataChanges metadataChanges) {
        WritableMap createMap = Arguments.createMap();
        createMap.putString(Param.SOURCE, str);
        WritableArray createArray = Arguments.createArray();
        WritableArray createArray2 = Arguments.createArray();
        List documentChanges = querySnapshot.getDocumentChanges();
        String str2 = KEY_CHANGES;
        String str3 = "excludesMetadataChanges";
        if (metadataChanges == null || metadataChanges == MetadataChanges.EXCLUDE) {
            createMap.putBoolean(str3, true);
            createMap.putArray(str2, documentChangesToWritableArray(documentChanges, null));
        } else {
            createMap.putBoolean(str3, false);
            createMap.putArray(str2, documentChangesToWritableArray(querySnapshot.getDocumentChanges(MetadataChanges.INCLUDE), documentChanges));
        }
        SnapshotMetadata metadata = querySnapshot.getMetadata();
        for (DocumentSnapshot snapshotToWritableMap : querySnapshot.getDocuments()) {
            createArray2.pushMap(snapshotToWritableMap(snapshotToWritableMap));
        }
        createMap.putArray(KEY_DOCUMENTS, createArray2);
        createArray.pushBoolean(metadata.isFromCache());
        createArray.pushBoolean(metadata.hasPendingWrites());
        createMap.putArray(KEY_META, createArray);
        return createMap;
    }

    private static WritableArray documentChangesToWritableArray(List<DocumentChange> list, @Nullable List<DocumentChange> list2) {
        WritableArray createArray = Arguments.createArray();
        boolean z = list2 != null;
        for (DocumentChange documentChange : list) {
            createArray.pushMap(documentChangeToWritableMap(documentChange, z && ((DocumentChange) Iterables.tryFind(list2, new Predicate(documentChange.hashCode()) {
                private final /* synthetic */ int f$0;

                public final 
/*
Method generation error in method: io.invertase.firebase.firestore.-$$Lambda$ReactNativeFirebaseFirestoreSerialize$SJLEVITK9bBq-kePk0M2CePhXXw.apply(java.lang.Object):null, dex: classes2.dex
                java.lang.NullPointerException
                	at jadx.core.codegen.ClassGen.useType(ClassGen.java:442)
                	at jadx.core.codegen.MethodGen.addDefinition(MethodGen.java:109)
                	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:311)
                	at jadx.core.codegen.ClassGen.addMethods(ClassGen.java:262)
                	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:225)
                	at jadx.core.codegen.InsnGen.inlineAnonymousConstructor(InsnGen.java:661)
                	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:595)
                	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:353)
                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:223)
                	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:105)
                	at jadx.core.codegen.InsnGen.generateMethodArguments(InsnGen.java:773)
                	at jadx.core.codegen.InsnGen.makeInvoke(InsnGen.java:713)
                	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:357)
                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:223)
                	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:105)
                	at jadx.core.codegen.InsnGen.addArgDot(InsnGen.java:88)
                	at jadx.core.codegen.InsnGen.makeInvoke(InsnGen.java:682)
                	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:357)
                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:223)
                	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:105)
                	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:280)
                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:223)
                	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:105)
                	at jadx.core.codegen.ConditionGen.addCompare(ConditionGen.java:129)
                	at jadx.core.codegen.ConditionGen.add(ConditionGen.java:57)
                	at jadx.core.codegen.ConditionGen.wrap(ConditionGen.java:84)
                	at jadx.core.codegen.ConditionGen.addAndOr(ConditionGen.java:151)
                	at jadx.core.codegen.ConditionGen.add(ConditionGen.java:70)
                	at jadx.core.codegen.ConditionGen.add(ConditionGen.java:46)
                	at jadx.core.codegen.InsnGen.makeTernary(InsnGen.java:910)
                	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:465)
                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:223)
                	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:105)
                	at jadx.core.codegen.InsnGen.generateMethodArguments(InsnGen.java:773)
                	at jadx.core.codegen.InsnGen.makeInvoke(InsnGen.java:713)
                	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:357)
                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:223)
                	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:105)
                	at jadx.core.codegen.InsnGen.generateMethodArguments(InsnGen.java:773)
                	at jadx.core.codegen.InsnGen.makeInvoke(InsnGen.java:713)
                	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:357)
                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:239)
                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:213)
                	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:109)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:55)
                	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
                	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:98)
                	at jadx.core.codegen.RegionGen.makeLoop(RegionGen.java:220)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
                	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:210)
                	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:203)
                	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:316)
                	at jadx.core.codegen.ClassGen.addMethods(ClassGen.java:262)
                	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:225)
                	at jadx.core.codegen.ClassGen.addClassCode(ClassGen.java:110)
                	at jadx.core.codegen.ClassGen.makeClass(ClassGen.java:76)
                	at jadx.core.codegen.CodeGen.wrapCodeGen(CodeGen.java:44)
                	at jadx.core.codegen.CodeGen.generateJavaCode(CodeGen.java:32)
                	at jadx.core.codegen.CodeGen.generate(CodeGen.java:20)
                	at jadx.core.ProcessClass.process(ProcessClass.java:36)
                	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:311)
                	at jadx.api.JavaClass.decompile(JavaClass.java:62)
                	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:217)
                
*/
            }).orNull()) == null));
        }
        return createArray;
    }

    static /* synthetic */ boolean lambda$documentChangesToWritableArray$0(int i, DocumentChange documentChange) {
        return documentChange.hashCode() == i;
    }

    private static WritableMap documentChangeToWritableMap(DocumentChange documentChange, boolean z) {
        WritableMap createMap = Arguments.createMap();
        createMap.putBoolean("isMetadataChange", z);
        int i = C22641.$SwitchMap$com$google$firebase$firestore$DocumentChange$Type[documentChange.getType().ordinal()];
        String str = "type";
        if (i == 1) {
            createMap.putString(str, CHANGE_ADDED);
        } else if (i == 2) {
            createMap.putString(str, CHANGE_MODIFIED);
        } else if (i == 3) {
            createMap.putString(str, CHANGE_REMOVED);
        }
        createMap.putMap(KEY_DOC_CHANGE_DOCUMENT, snapshotToWritableMap(documentChange.getDocument()));
        createMap.putInt(KEY_DOC_CHANGE_NEW_INDEX, documentChange.getNewIndex());
        createMap.putInt(KEY_DOC_CHANGE_OLD_INDEX, documentChange.getOldIndex());
        return createMap;
    }

    private static WritableMap objectMapToWritable(Map<String, Object> map) {
        WritableMap createMap = Arguments.createMap();
        for (Entry entry : map.entrySet()) {
            createMap.putArray((String) entry.getKey(), buildTypeMap(entry.getValue()));
        }
        return createMap;
    }

    private static WritableArray objectArrayToWritable(Object[] objArr) {
        WritableArray createArray = Arguments.createArray();
        for (Object buildTypeMap : objArr) {
            createArray.pushArray(buildTypeMap(buildTypeMap));
        }
        return createArray;
    }

    private static WritableArray buildTypeMap(Object obj) {
        WritableArray createArray = Arguments.createArray();
        if (obj == null) {
            createArray.pushInt(3);
            return createArray;
        } else if (obj instanceof Boolean) {
            if (((Boolean) obj).booleanValue()) {
                createArray.pushInt(5);
            } else {
                createArray.pushInt(6);
            }
            return createArray;
        } else if (obj instanceof Integer) {
            createArray.pushInt(7);
            createArray.pushDouble(((Integer) obj).doubleValue());
            return createArray;
        } else if (obj instanceof Double) {
            Double d = (Double) obj;
            if (Double.isInfinite(d.doubleValue())) {
                if (d.equals(Double.valueOf(Double.NEGATIVE_INFINITY))) {
                    createArray.pushInt(1);
                    return createArray;
                } else if (d.equals(Double.valueOf(Double.POSITIVE_INFINITY))) {
                    createArray.pushInt(2);
                    return createArray;
                }
            }
            if (Double.isNaN(d.doubleValue())) {
                createArray.pushInt(0);
                return createArray;
            }
            createArray.pushInt(7);
            createArray.pushDouble(d.doubleValue());
            return createArray;
        } else if (obj instanceof Float) {
            createArray.pushInt(7);
            createArray.pushDouble(((Float) obj).doubleValue());
            return createArray;
        } else if (obj instanceof Long) {
            createArray.pushInt(7);
            createArray.pushDouble(((Long) obj).doubleValue());
            return createArray;
        } else if (obj instanceof String) {
            if (obj == "") {
                createArray.pushInt(9);
            } else {
                createArray.pushInt(8);
                createArray.pushString((String) obj);
            }
            return createArray;
        } else if (Map.class.isAssignableFrom(obj.getClass())) {
            createArray.pushInt(16);
            createArray.pushMap(objectMapToWritable((Map) obj));
            return createArray;
        } else if (List.class.isAssignableFrom(obj.getClass())) {
            createArray.pushInt(10);
            List list = (List) obj;
            createArray.pushArray(objectArrayToWritable(list.toArray(new Object[list.size()])));
            return createArray;
        } else if (obj instanceof DocumentReference) {
            createArray.pushInt(11);
            createArray.pushString(((DocumentReference) obj).getPath());
            return createArray;
        } else if (obj instanceof Timestamp) {
            createArray.pushInt(13);
            WritableArray createArray2 = Arguments.createArray();
            Timestamp timestamp = (Timestamp) obj;
            createArray2.pushDouble((double) timestamp.getSeconds());
            createArray2.pushInt(timestamp.getNanoseconds());
            createArray.pushArray(createArray2);
            return createArray;
        } else if (obj instanceof GeoPoint) {
            createArray.pushInt(12);
            WritableArray createArray3 = Arguments.createArray();
            GeoPoint geoPoint = (GeoPoint) obj;
            createArray3.pushDouble(geoPoint.getLatitude());
            createArray3.pushDouble(geoPoint.getLongitude());
            createArray.pushArray(createArray3);
            return createArray;
        } else if (obj instanceof Blob) {
            createArray.pushInt(14);
            createArray.pushString(Base64.encodeToString(((Blob) obj).toBytes(), 2));
            return createArray;
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("Unknown object of type ");
            sb.append(obj.getClass());
            Log.w(TAG, sb.toString());
            createArray.pushInt(INT_UNKNOWN);
            return createArray;
        }
    }

    /* access modifiers changed from: 0000 */
    public static Map<String, Object> parseReadableMap(FirebaseFirestore firebaseFirestore, @Nullable ReadableMap readableMap) {
        HashMap hashMap = new HashMap();
        if (readableMap == null) {
            return hashMap;
        }
        ReadableMapKeySetIterator keySetIterator = readableMap.keySetIterator();
        while (keySetIterator.hasNextKey()) {
            String nextKey = keySetIterator.nextKey();
            hashMap.put(nextKey, parseTypeMap(firebaseFirestore, readableMap.getArray(nextKey)));
        }
        return hashMap;
    }

    static List<Object> parseReadableArray(FirebaseFirestore firebaseFirestore, @Nullable ReadableArray readableArray) {
        ArrayList arrayList = new ArrayList();
        if (readableArray == null) {
            return arrayList;
        }
        for (int i = 0; i < readableArray.size(); i++) {
            arrayList.add(parseTypeMap(firebaseFirestore, readableArray.getArray(i)));
        }
        return arrayList;
    }

    static Object parseTypeMap(FirebaseFirestore firebaseFirestore, ReadableArray readableArray) {
        switch (readableArray.getInt(0)) {
            case 0:
                return Double.valueOf(Double.NaN);
            case 1:
                return Double.valueOf(Double.NEGATIVE_INFINITY);
            case 2:
                return Double.valueOf(Double.POSITIVE_INFINITY);
            case 3:
                return null;
            case 4:
                return FieldPath.documentId();
            case 5:
                return Boolean.valueOf(true);
            case 6:
                return Boolean.valueOf(false);
            case 7:
                return Double.valueOf(readableArray.getDouble(1));
            case 8:
                return readableArray.getString(1);
            case 9:
                return "";
            case 10:
                return parseReadableArray(firebaseFirestore, readableArray.getArray(1));
            case 11:
                String string = readableArray.getString(1);
                string.getClass();
                return firebaseFirestore.document(string);
            case 12:
                ReadableArray array = readableArray.getArray(1);
                array.getClass();
                return new GeoPoint(array.getDouble(0), array.getDouble(1));
            case 13:
                ReadableArray array2 = readableArray.getArray(1);
                array2.getClass();
                return new Timestamp((long) array2.getDouble(0), array2.getInt(1));
            case 14:
                return Blob.fromBytes(Base64.decode(readableArray.getString(1), 2));
            case 15:
                ReadableArray array3 = readableArray.getArray(1);
                array3.getClass();
                String string2 = array3.getString(0);
                string2.getClass();
                String str = string2;
                if (str.equals("timestamp")) {
                    return FieldValue.serverTimestamp();
                }
                string2.getClass();
                if (str.equals("increment")) {
                    return FieldValue.increment(array3.getDouble(1));
                }
                string2.getClass();
                if (str.equals("delete")) {
                    return FieldValue.delete();
                }
                string2.getClass();
                if (str.equals("array_union")) {
                    return FieldValue.arrayUnion(parseReadableArray(firebaseFirestore, array3.getArray(1)).toArray());
                }
                string2.getClass();
                if (str.equals("array_remove")) {
                    return FieldValue.arrayRemove(parseReadableArray(firebaseFirestore, array3.getArray(1)).toArray());
                }
                break;
            case 16:
                break;
            default:
                return null;
        }
        return parseReadableMap(firebaseFirestore, readableArray.getMap(1));
    }

    /* access modifiers changed from: 0000 */
    public static List<Object> parseDocumentBatches(FirebaseFirestore firebaseFirestore, ReadableArray readableArray) {
        ArrayList arrayList = new ArrayList(readableArray.size());
        for (int i = 0; i < readableArray.size(); i++) {
            HashMap hashMap = new HashMap();
            ReadableMap map = readableArray.getMap(i);
            String str = "type";
            hashMap.put(str, map.getString(str));
            String str2 = KEY_PATH;
            hashMap.put(str2, map.getString(str2));
            String str3 = "data";
            if (map.hasKey(str3)) {
                hashMap.put(str3, parseReadableMap(firebaseFirestore, map.getMap(str3)));
            }
            String str4 = KEY_OPTIONS;
            if (map.hasKey(str4)) {
                hashMap.put(str4, RCTConvertFirebase.toHashMap(map.getMap(str4)));
            }
            arrayList.add(hashMap);
        }
        return arrayList;
    }
}
