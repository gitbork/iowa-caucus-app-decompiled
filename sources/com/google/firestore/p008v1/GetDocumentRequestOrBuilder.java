package com.google.firestore.p008v1;

import com.google.firestore.p008v1.GetDocumentRequest.ConsistencySelectorCase;
import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Timestamp;

/* renamed from: com.google.firestore.v1.GetDocumentRequestOrBuilder */
/* compiled from: com.google.firebase:firebase-firestore@@20.2.0 */
public interface GetDocumentRequestOrBuilder extends MessageLiteOrBuilder {
    ConsistencySelectorCase getConsistencySelectorCase();

    DocumentMask getMask();

    String getName();

    ByteString getNameBytes();

    Timestamp getReadTime();

    ByteString getTransaction();

    boolean hasMask();
}
