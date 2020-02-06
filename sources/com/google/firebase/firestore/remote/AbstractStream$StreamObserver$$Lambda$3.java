package com.google.firebase.firestore.remote;

/* compiled from: com.google.firebase:firebase-firestore@@20.2.0 */
final /* synthetic */ class AbstractStream$StreamObserver$$Lambda$3 implements Runnable {
    private final StreamObserver arg$1;

    private AbstractStream$StreamObserver$$Lambda$3(StreamObserver streamObserver) {
        this.arg$1 = streamObserver;
    }

    public static Runnable lambdaFactory$(StreamObserver streamObserver) {
        return new AbstractStream$StreamObserver$$Lambda$3(streamObserver);
    }

    public void run() {
        StreamObserver.lambda$onOpen$2(this.arg$1);
    }
}
