package com.google.firestore.p008v1;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Timestamp;
import java.util.List;

/* renamed from: com.google.firestore.v1.DocumentDeleteOrBuilder */
/* compiled from: com.google.firebase:firebase-firestore@@20.2.0 */
public interface DocumentDeleteOrBuilder extends MessageLiteOrBuilder {
    String getDocument();

    ByteString getDocumentBytes();

    Timestamp getReadTime();

    int getRemovedTargetIds(int i);

    int getRemovedTargetIdsCount();

    List<Integer> getRemovedTargetIdsList();

    boolean hasReadTime();
}
