package com.google.firebase.storage;

import com.google.android.gms.tasks.OnCanceledListener;

/* compiled from: com.google.firebase:firebase-storage@@18.1.1 */
final /* synthetic */ class StorageTask$$Lambda$6 implements OnRaise {
    private final StorageTask arg$1;

    private StorageTask$$Lambda$6(StorageTask storageTask) {
        this.arg$1 = storageTask;
    }

    public static OnRaise lambdaFactory$(StorageTask storageTask) {
        return new StorageTask$$Lambda$6(storageTask);
    }

    public void raise(Object obj, Object obj2) {
        StorageTask.lambda$new$3(this.arg$1, (OnCanceledListener) obj, (ProvideError) obj2);
    }
}
