package com.google.firestore.p008v1;

import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Timestamp;
import java.util.List;

/* renamed from: com.google.firestore.v1.WriteResultOrBuilder */
/* compiled from: com.google.firebase:firebase-firestore@@20.2.0 */
public interface WriteResultOrBuilder extends MessageLiteOrBuilder {
    Value getTransformResults(int i);

    int getTransformResultsCount();

    List<Value> getTransformResultsList();

    Timestamp getUpdateTime();

    boolean hasUpdateTime();
}
