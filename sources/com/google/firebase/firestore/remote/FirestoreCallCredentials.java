package com.google.firebase.firestore.remote;

import com.google.common.net.HttpHeaders;
import com.google.firebase.FirebaseApiNotAvailableException;
import com.google.firebase.firestore.auth.CredentialsProvider;
import com.google.firebase.firestore.util.Logger;
import com.google.firebase.internal.api.FirebaseNoSignedInUserException;
import java.util.concurrent.Executor;
import p006io.grpc.CallCredentials;
import p006io.grpc.CallCredentials.MetadataApplier;
import p006io.grpc.CallCredentials.RequestInfo;
import p006io.grpc.Metadata;
import p006io.grpc.Metadata.Key;
import p006io.grpc.Status;

/* compiled from: com.google.firebase:firebase-firestore@@20.2.0 */
final class FirestoreCallCredentials extends CallCredentials {
    private static final Key<String> AUTHORIZATION_HEADER = Key.m370of(HttpHeaders.AUTHORIZATION, Metadata.ASCII_STRING_MARSHALLER);
    private static final String LOG_TAG = "FirestoreCallCredentials";
    private final CredentialsProvider credentialsProvider;

    public void thisUsesUnstableApi() {
    }

    FirestoreCallCredentials(CredentialsProvider credentialsProvider2) {
        this.credentialsProvider = credentialsProvider2;
    }

    public void applyRequestMetadata(RequestInfo requestInfo, Executor executor, MetadataApplier metadataApplier) {
        this.credentialsProvider.getToken().addOnSuccessListener(executor, FirestoreCallCredentials$$Lambda$1.lambdaFactory$(metadataApplier)).addOnFailureListener(executor, FirestoreCallCredentials$$Lambda$2.lambdaFactory$(metadataApplier));
    }

    static /* synthetic */ void lambda$applyRequestMetadata$0(MetadataApplier metadataApplier, String str) {
        Logger.debug(LOG_TAG, "Successfully fetched token.", new Object[0]);
        Metadata metadata = new Metadata();
        if (str != null) {
            Key<String> key = AUTHORIZATION_HEADER;
            StringBuilder sb = new StringBuilder();
            sb.append("Bearer ");
            sb.append(str);
            metadata.put(key, sb.toString());
        }
        metadataApplier.apply(metadata);
    }

    static /* synthetic */ void lambda$applyRequestMetadata$1(MetadataApplier metadataApplier, Exception exc) {
        boolean z = exc instanceof FirebaseApiNotAvailableException;
        String str = LOG_TAG;
        if (z) {
            Logger.debug(str, "Firebase Auth API not available, not using authentication.", new Object[0]);
            metadataApplier.apply(new Metadata());
        } else if (exc instanceof FirebaseNoSignedInUserException) {
            Logger.debug(str, "No user signed in, not using authentication.", new Object[0]);
            metadataApplier.apply(new Metadata());
        } else {
            Logger.warn(str, "Failed to get token: %s.", exc);
            metadataApplier.fail(Status.UNAUTHENTICATED.withCause(exc));
        }
    }
}
