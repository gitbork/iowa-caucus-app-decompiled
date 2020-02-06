package com.google.firebase.firestore.proto;

import com.google.firebase.firestore.proto.Target.TargetTypeCase;
import com.google.firestore.p008v1.Target.DocumentsTarget;
import com.google.firestore.p008v1.Target.QueryTarget;
import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Timestamp;

/* compiled from: com.google.firebase:firebase-firestore@@20.2.0 */
public interface TargetOrBuilder extends MessageLiteOrBuilder {
    DocumentsTarget getDocuments();

    long getLastListenSequenceNumber();

    QueryTarget getQuery();

    ByteString getResumeToken();

    Timestamp getSnapshotVersion();

    int getTargetId();

    TargetTypeCase getTargetTypeCase();

    boolean hasSnapshotVersion();
}
