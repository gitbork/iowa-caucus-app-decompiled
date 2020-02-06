package com.google.firebase.firestore.local;

import android.database.Cursor;
import com.google.firebase.database.collection.ImmutableSortedMap;
import com.google.firebase.firestore.core.Query;
import com.google.firebase.firestore.model.Document;
import com.google.firebase.firestore.model.DocumentCollections;
import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.model.MaybeDocument;
import com.google.firebase.firestore.model.ResourcePath;
import com.google.firebase.firestore.util.Assert;
import com.google.firebase.firestore.util.BackgroundQueue;
import com.google.protobuf.InvalidProtocolBufferException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Nullable;

/* compiled from: com.google.firebase:firebase-firestore@@20.2.0 */
final class SQLiteRemoteDocumentCache implements RemoteDocumentCache {

    /* renamed from: db */
    private final SQLitePersistence f251db;
    private final LocalSerializer serializer;

    SQLiteRemoteDocumentCache(SQLitePersistence sQLitePersistence, LocalSerializer localSerializer) {
        this.f251db = sQLitePersistence;
        this.serializer = localSerializer;
    }

    public void add(MaybeDocument maybeDocument) {
        String pathForKey = pathForKey(maybeDocument.getKey());
        com.google.firebase.firestore.proto.MaybeDocument encodeMaybeDocument = this.serializer.encodeMaybeDocument(maybeDocument);
        this.f251db.execute("INSERT OR REPLACE INTO remote_documents (path, contents) VALUES (?, ?)", pathForKey, encodeMaybeDocument.toByteArray());
        this.f251db.getIndexManager().addToCollectionParentIndex((ResourcePath) maybeDocument.getKey().getPath().popLast());
    }

    public void remove(DocumentKey documentKey) {
        String pathForKey = pathForKey(documentKey);
        this.f251db.execute("DELETE FROM remote_documents WHERE path = ?", pathForKey);
    }

    @Nullable
    public MaybeDocument get(DocumentKey documentKey) {
        String pathForKey = pathForKey(documentKey);
        return (MaybeDocument) this.f251db.query("SELECT contents FROM remote_documents WHERE path = ?").binding(pathForKey).firstValue(SQLiteRemoteDocumentCache$$Lambda$1.lambdaFactory$(this));
    }

    public Map<DocumentKey, MaybeDocument> getAll(Iterable<DocumentKey> iterable) {
        ArrayList arrayList = new ArrayList();
        for (DocumentKey path : iterable) {
            arrayList.add(EncodedPath.encode(path.getPath()));
        }
        HashMap hashMap = new HashMap();
        for (DocumentKey put : iterable) {
            hashMap.put(put, null);
        }
        LongQuery longQuery = new LongQuery(this.f251db, "SELECT contents FROM remote_documents WHERE path IN (", arrayList, ") ORDER BY path");
        while (longQuery.hasMoreSubqueries()) {
            longQuery.performNextSubquery().forEach(SQLiteRemoteDocumentCache$$Lambda$2.lambdaFactory$(this, hashMap));
        }
        return hashMap;
    }

    static /* synthetic */ void lambda$getAll$1(SQLiteRemoteDocumentCache sQLiteRemoteDocumentCache, Map map, Cursor cursor) {
        MaybeDocument decodeMaybeDocument = sQLiteRemoteDocumentCache.decodeMaybeDocument(cursor.getBlob(0));
        map.put(decodeMaybeDocument.getKey(), decodeMaybeDocument);
    }

    public ImmutableSortedMap<DocumentKey, Document> getAllDocumentsMatchingQuery(Query query) {
        Assert.hardAssert(!query.isCollectionGroupQuery(), "CollectionGroup queries should be handled in LocalDocumentsView", new Object[0]);
        ResourcePath path = query.getPath();
        int length = path.length() + 1;
        String encode = EncodedPath.encode(path);
        String prefixSuccessor = EncodedPath.prefixSuccessor(encode);
        BackgroundQueue backgroundQueue = new BackgroundQueue();
        ImmutableSortedMap<DocumentKey, Document>[] immutableSortedMapArr = {DocumentCollections.emptyDocumentMap()};
        this.f251db.query("SELECT path, contents FROM remote_documents WHERE path >= ? AND path < ?").binding(encode, prefixSuccessor).forEach(SQLiteRemoteDocumentCache$$Lambda$3.lambdaFactory$(this, length, backgroundQueue, query, immutableSortedMapArr));
        try {
            backgroundQueue.drain();
        } catch (InterruptedException e) {
            Assert.fail("Interrupted while deserializing documents", e);
        }
        return immutableSortedMapArr[0];
    }

    /* JADX WARNING: Incorrect type for immutable var: ssa=com.google.firebase.firestore.util.BackgroundQueue, code=java.util.concurrent.Executor, for r3v0, types: [com.google.firebase.firestore.util.BackgroundQueue, java.util.concurrent.Executor] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static /* synthetic */ void lambda$getAllDocumentsMatchingQuery$3(com.google.firebase.firestore.local.SQLiteRemoteDocumentCache r1, int r2, java.util.concurrent.Executor r3, com.google.firebase.firestore.core.Query r4, com.google.firebase.database.collection.ImmutableSortedMap[] r5, android.database.Cursor r6) {
        /*
            r0 = 0
            java.lang.String r0 = r6.getString(r0)
            com.google.firebase.firestore.model.ResourcePath r0 = com.google.firebase.firestore.local.EncodedPath.decodeResourcePath(r0)
            int r0 = r0.length()
            if (r0 == r2) goto L_0x0010
            return
        L_0x0010:
            r2 = 1
            byte[] r2 = r6.getBlob(r2)
            boolean r6 = r6.isLast()
            if (r6 == 0) goto L_0x001d
            java.util.concurrent.Executor r3 = com.google.firebase.firestore.util.Executors.DIRECT_EXECUTOR
        L_0x001d:
            java.lang.Runnable r1 = com.google.firebase.firestore.local.SQLiteRemoteDocumentCache$$Lambda$4.lambdaFactory$(r1, r2, r4, r5)
            r3.execute(r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.firestore.local.SQLiteRemoteDocumentCache.lambda$getAllDocumentsMatchingQuery$3(com.google.firebase.firestore.local.SQLiteRemoteDocumentCache, int, com.google.firebase.firestore.util.BackgroundQueue, com.google.firebase.firestore.core.Query, com.google.firebase.database.collection.ImmutableSortedMap[], android.database.Cursor):void");
    }

    static /* synthetic */ void lambda$getAllDocumentsMatchingQuery$2(SQLiteRemoteDocumentCache sQLiteRemoteDocumentCache, byte[] bArr, Query query, ImmutableSortedMap[] immutableSortedMapArr) {
        MaybeDocument decodeMaybeDocument = sQLiteRemoteDocumentCache.decodeMaybeDocument(bArr);
        if ((decodeMaybeDocument instanceof Document) && query.matches((Document) decodeMaybeDocument)) {
            synchronized (sQLiteRemoteDocumentCache) {
                immutableSortedMapArr[0] = immutableSortedMapArr[0].insert(decodeMaybeDocument.getKey(), (Document) decodeMaybeDocument);
            }
        }
    }

    private String pathForKey(DocumentKey documentKey) {
        return EncodedPath.encode(documentKey.getPath());
    }

    /* access modifiers changed from: private */
    public MaybeDocument decodeMaybeDocument(byte[] bArr) {
        try {
            return this.serializer.decodeMaybeDocument(com.google.firebase.firestore.proto.MaybeDocument.parseFrom(bArr));
        } catch (InvalidProtocolBufferException e) {
            throw Assert.fail("MaybeDocument failed to parse: %s", e);
        }
    }
}
