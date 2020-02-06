package com.google.firestore.p008v1;

import com.google.firestore.p008v1.ListenResponse.ResponseTypeCase;
import com.google.protobuf.MessageLiteOrBuilder;

/* renamed from: com.google.firestore.v1.ListenResponseOrBuilder */
/* compiled from: com.google.firebase:firebase-firestore@@20.2.0 */
public interface ListenResponseOrBuilder extends MessageLiteOrBuilder {
    DocumentChange getDocumentChange();

    DocumentDelete getDocumentDelete();

    DocumentRemove getDocumentRemove();

    ExistenceFilter getFilter();

    ResponseTypeCase getResponseTypeCase();

    TargetChange getTargetChange();
}
