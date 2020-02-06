package com.google.firestore.p008v1;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Timestamp;
import java.util.List;

/* renamed from: com.google.firestore.v1.WriteResponseOrBuilder */
/* compiled from: com.google.firebase:firebase-firestore@@20.2.0 */
public interface WriteResponseOrBuilder extends MessageLiteOrBuilder {
    Timestamp getCommitTime();

    String getStreamId();

    ByteString getStreamIdBytes();

    ByteString getStreamToken();

    WriteResult getWriteResults(int i);

    int getWriteResultsCount();

    List<WriteResult> getWriteResultsList();

    boolean hasCommitTime();
}
