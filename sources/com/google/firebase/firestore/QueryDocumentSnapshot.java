package com.google.firebase.firestore;

import androidx.annotation.NonNull;
import com.google.common.base.Preconditions;
import com.google.firebase.annotations.PublicApi;
import com.google.firebase.firestore.DocumentSnapshot.ServerTimestampBehavior;
import com.google.firebase.firestore.model.Document;
import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.util.Assert;
import java.util.Map;
import javax.annotation.Nullable;

@PublicApi
/* compiled from: com.google.firebase:firebase-firestore@@20.2.0 */
public class QueryDocumentSnapshot extends DocumentSnapshot {
    private QueryDocumentSnapshot(FirebaseFirestore firebaseFirestore, DocumentKey documentKey, @Nullable Document document, boolean z, boolean z2) {
        super(firebaseFirestore, documentKey, document, z, z2);
    }

    static QueryDocumentSnapshot fromDocument(FirebaseFirestore firebaseFirestore, Document document, boolean z, boolean z2) {
        QueryDocumentSnapshot queryDocumentSnapshot = new QueryDocumentSnapshot(firebaseFirestore, document.getKey(), document, z, z2);
        return queryDocumentSnapshot;
    }

    @PublicApi
    @NonNull
    public Map<String, Object> getData() {
        Map<String, Object> data = super.getData();
        Assert.hardAssert(data != null, "Data in a QueryDocumentSnapshot should be non-null", new Object[0]);
        return data;
    }

    @PublicApi
    @NonNull
    public Map<String, Object> getData(@NonNull ServerTimestampBehavior serverTimestampBehavior) {
        Preconditions.checkNotNull(serverTimestampBehavior, "Provided serverTimestampBehavior value must not be null.");
        Map<String, Object> data = super.getData(serverTimestampBehavior);
        Assert.hardAssert(data != null, "Data in a QueryDocumentSnapshot should be non-null", new Object[0]);
        return data;
    }

    @PublicApi
    @NonNull
    public <T> T toObject(@NonNull Class<T> cls) {
        T object = super.toObject(cls);
        Assert.hardAssert(object != null, "Object in a QueryDocumentSnapshot should be non-null", new Object[0]);
        return object;
    }

    @PublicApi
    @NonNull
    public <T> T toObject(@NonNull Class<T> cls, @NonNull ServerTimestampBehavior serverTimestampBehavior) {
        Preconditions.checkNotNull(serverTimestampBehavior, "Provided serverTimestampBehavior value must not be null.");
        T object = super.toObject(cls, serverTimestampBehavior);
        Assert.hardAssert(object != null, "Object in a QueryDocumentSnapshot should be non-null", new Object[0]);
        return object;
    }
}
