package com.google.firestore.p008v1;

import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Timestamp;
import java.util.List;

/* renamed from: com.google.firestore.v1.CommitResponseOrBuilder */
/* compiled from: com.google.firebase:firebase-firestore@@20.2.0 */
public interface CommitResponseOrBuilder extends MessageLiteOrBuilder {
    Timestamp getCommitTime();

    WriteResult getWriteResults(int i);

    int getWriteResultsCount();

    List<WriteResult> getWriteResultsList();

    boolean hasCommitTime();
}
