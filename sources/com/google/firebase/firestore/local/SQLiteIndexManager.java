package com.google.firebase.firestore.local;

import com.google.firebase.firestore.model.ResourcePath;
import com.google.firebase.firestore.util.Assert;
import java.util.ArrayList;
import java.util.List;

/* compiled from: com.google.firebase:firebase-firestore@@20.2.0 */
final class SQLiteIndexManager implements IndexManager {
    private final MemoryCollectionParentIndex collectionParentsCache = new MemoryCollectionParentIndex();

    /* renamed from: db */
    private final SQLitePersistence f245db;

    SQLiteIndexManager(SQLitePersistence sQLitePersistence) {
        this.f245db = sQLitePersistence;
    }

    public void addToCollectionParentIndex(ResourcePath resourcePath) {
        Assert.hardAssert(resourcePath.length() % 2 == 1, "Expected a collection path.", new Object[0]);
        if (this.collectionParentsCache.add(resourcePath)) {
            this.f245db.execute("INSERT OR REPLACE INTO collection_parents (collection_id, parent) VALUES (?, ?)", resourcePath.getLastSegment(), EncodedPath.encode((ResourcePath) resourcePath.popLast()));
        }
    }

    public List<ResourcePath> getCollectionParents(String str) {
        ArrayList arrayList = new ArrayList();
        this.f245db.query("SELECT parent FROM collection_parents WHERE collection_id = ?").binding(str).forEach(SQLiteIndexManager$$Lambda$1.lambdaFactory$(arrayList));
        return arrayList;
    }
}
