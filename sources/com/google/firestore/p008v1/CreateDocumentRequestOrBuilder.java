package com.google.firestore.p008v1;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLiteOrBuilder;

/* renamed from: com.google.firestore.v1.CreateDocumentRequestOrBuilder */
/* compiled from: com.google.firebase:firebase-firestore@@20.2.0 */
public interface CreateDocumentRequestOrBuilder extends MessageLiteOrBuilder {
    String getCollectionId();

    ByteString getCollectionIdBytes();

    Document getDocument();

    String getDocumentId();

    ByteString getDocumentIdBytes();

    DocumentMask getMask();

    String getParent();

    ByteString getParentBytes();

    boolean hasDocument();

    boolean hasMask();
}
