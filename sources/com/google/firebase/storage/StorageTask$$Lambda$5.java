package com.google.firebase.storage;

import com.google.android.gms.tasks.OnCompleteListener;

/* compiled from: com.google.firebase:firebase-storage@@18.1.1 */
final /* synthetic */ class StorageTask$$Lambda$5 implements OnRaise {
    private final StorageTask arg$1;

    private StorageTask$$Lambda$5(StorageTask storageTask) {
        this.arg$1 = storageTask;
    }

    public static OnRaise lambdaFactory$(StorageTask storageTask) {
        return new StorageTask$$Lambda$5(storageTask);
    }

    public void raise(Object obj, Object obj2) {
        StorageTask.lambda$new$2(this.arg$1, (OnCompleteListener) obj, (ProvideError) obj2);
    }
}
