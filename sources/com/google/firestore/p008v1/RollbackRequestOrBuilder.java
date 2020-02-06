package com.google.firestore.p008v1;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLiteOrBuilder;

/* renamed from: com.google.firestore.v1.RollbackRequestOrBuilder */
/* compiled from: com.google.firebase:firebase-firestore@@20.2.0 */
public interface RollbackRequestOrBuilder extends MessageLiteOrBuilder {
    String getDatabase();

    ByteString getDatabaseBytes();

    ByteString getTransaction();
}
