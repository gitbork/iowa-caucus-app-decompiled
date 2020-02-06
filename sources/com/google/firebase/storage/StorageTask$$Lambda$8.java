package com.google.firebase.storage;

/* compiled from: com.google.firebase:firebase-storage@@18.1.1 */
final /* synthetic */ class StorageTask$$Lambda$8 implements OnRaise {
    private static final StorageTask$$Lambda$8 instance = new StorageTask$$Lambda$8();

    private StorageTask$$Lambda$8() {
    }

    public static OnRaise lambdaFactory$() {
        return instance;
    }

    public void raise(Object obj, Object obj2) {
        ((OnPausedListener) obj).onPaused((ProvideError) obj2);
    }
}
