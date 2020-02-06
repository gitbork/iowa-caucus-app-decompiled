package com.google.firestore.p008v1;

import com.google.firestore.p008v1.RunQueryRequest.ConsistencySelectorCase;
import com.google.firestore.p008v1.RunQueryRequest.QueryTypeCase;
import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Timestamp;

/* renamed from: com.google.firestore.v1.RunQueryRequestOrBuilder */
/* compiled from: com.google.firebase:firebase-firestore@@20.2.0 */
public interface RunQueryRequestOrBuilder extends MessageLiteOrBuilder {
    ConsistencySelectorCase getConsistencySelectorCase();

    TransactionOptions getNewTransaction();

    String getParent();

    ByteString getParentBytes();

    QueryTypeCase getQueryTypeCase();

    Timestamp getReadTime();

    StructuredQuery getStructuredQuery();

    ByteString getTransaction();
}
