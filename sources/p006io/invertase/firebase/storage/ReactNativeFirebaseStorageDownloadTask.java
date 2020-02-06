package p006io.invertase.firebase.storage;

import android.util.Log;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.WritableMap;
import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FileDownloadTask.TaskSnapshot;
import com.google.firebase.storage.OnPausedListener;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import java.io.File;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import javax.annotation.Nullable;
import p006io.invertase.firebase.common.ReactNativeFirebaseEventEmitter;
import p006io.invertase.firebase.common.ReactNativeFirebaseModule;

/* renamed from: io.invertase.firebase.storage.ReactNativeFirebaseStorageDownloadTask */
class ReactNativeFirebaseStorageDownloadTask extends ReactNativeFirebaseStorageTask {
    private static final String TAG = "RNFBStorageDownload";
    private FileDownloadTask fileDownloadTask;

    ReactNativeFirebaseStorageDownloadTask(int i, StorageReference storageReference, String str) {
        super(i, storageReference, str);
    }

    private static WritableMap buildDownloadSnapshotMap(@Nullable TaskSnapshot taskSnapshot) {
        WritableMap createMap = Arguments.createMap();
        String str = "state";
        String str2 = "bytesTransferred";
        String str3 = "totalBytes";
        if (taskSnapshot != null) {
            createMap.putDouble(str3, (double) taskSnapshot.getTotalByteCount());
            createMap.putDouble(str2, (double) taskSnapshot.getBytesTransferred());
            createMap.putString(str, ReactNativeFirebaseStorageCommon.getTaskStatus(taskSnapshot.getTask()));
        } else {
            createMap.putDouble(str3, 0.0d);
            createMap.putDouble(str2, 0.0d);
            createMap.putString(str, ReactNativeFirebaseStorageCommon.getTaskStatus(null));
        }
        return createMap;
    }

    private String getPath(String str) {
        String str2 = "/";
        int lastIndexOf = str.lastIndexOf(str2);
        if (lastIndexOf <= 0) {
            return str2;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(str.substring(0, lastIndexOf));
        sb.append(str2);
        return sb.toString();
    }

    private String getFileName(String str) {
        int lastIndexOf = str.lastIndexOf("/");
        return lastIndexOf > 0 ? str.substring(lastIndexOf + 1) : str;
    }

    /* access modifiers changed from: 0000 */
    public void addOnCompleteListener(ExecutorService executorService, Promise promise) {
        FileDownloadTask fileDownloadTask2 = this.fileDownloadTask;
        if (fileDownloadTask2 == null) {
            ReactNativeFirebaseModule.rejectPromiseWithCodeAndMessage(promise, "error-creating-directory", "Unable to create the directory specified as the download path for your file.");
        } else {
            fileDownloadTask2.addOnCompleteListener((Executor) executorService, (OnCompleteListener) new OnCompleteListener(promise) {
                private final /* synthetic */ Promise f$1;

                {
                    this.f$1 = r2;
                }

                public final void onComplete(Task task) {
                    ReactNativeFirebaseStorageDownloadTask.this.mo35623xf411e933(this.f$1, task);
                }
            });
        }
    }

    /* renamed from: lambda$addOnCompleteListener$0$ReactNativeFirebaseStorageDownloadTask */
    public /* synthetic */ void mo35623xf411e933(Promise promise, Task task) {
        destroyTask();
        boolean isSuccessful = task.isSuccessful();
        String str = "state_changed";
        String str2 = TAG;
        if (isSuccessful) {
            StringBuilder sb = new StringBuilder();
            sb.append("onComplete:success ");
            sb.append(this.storageReference.toString());
            Log.d(str2, sb.toString());
            WritableMap buildDownloadSnapshotMap = buildDownloadSnapshotMap((TaskSnapshot) task.getResult());
            ReactNativeFirebaseEventEmitter sharedInstance = ReactNativeFirebaseEventEmitter.getSharedInstance();
            sharedInstance.sendEvent(new ReactNativeFirebaseStorageEvent(buildDownloadSnapshotMap, str, this.appName, this.taskId));
            sharedInstance.sendEvent(new ReactNativeFirebaseStorageEvent(buildDownloadSnapshotMap((TaskSnapshot) task.getResult()), "download_success", this.appName, this.taskId));
            promise.resolve(buildDownloadSnapshotMap((TaskSnapshot) task.getResult()));
            return;
        }
        StringBuilder sb2 = new StringBuilder();
        sb2.append("onComplete:failure ");
        sb2.append(this.storageReference.toString());
        Log.d(str2, sb2.toString());
        ReactNativeFirebaseEventEmitter sharedInstance2 = ReactNativeFirebaseEventEmitter.getSharedInstance();
        WritableMap buildErrorSnapshotMap = buildErrorSnapshotMap(task.getException(), buildDownloadSnapshotMap((TaskSnapshot) this.fileDownloadTask.getSnapshot()), true);
        if (buildErrorSnapshotMap != null) {
            sharedInstance2.sendEvent(new ReactNativeFirebaseStorageEvent(buildErrorSnapshotMap, str, this.appName, this.taskId));
        }
        sharedInstance2.sendEvent(new ReactNativeFirebaseStorageEvent(buildErrorSnapshotMap(task.getException(), buildDownloadSnapshotMap((TaskSnapshot) this.fileDownloadTask.getSnapshot()), false), "download_failure", this.appName, this.taskId));
        ReactNativeFirebaseStorageCommon.promiseRejectStorageException(promise, task.getException());
    }

    private void addEventListeners(ExecutorService executorService) {
        this.fileDownloadTask.addOnProgressListener((Executor) executorService, (OnProgressListener) new OnProgressListener() {
            public final void onProgress(Object obj) {
                ReactNativeFirebaseStorageDownloadTask.this.mo35620x92c00479((TaskSnapshot) obj);
            }
        });
        this.fileDownloadTask.addOnCanceledListener((Executor) executorService, (OnCanceledListener) new OnCanceledListener() {
            public final void onCanceled() {
                ReactNativeFirebaseStorageDownloadTask.this.mo35621xe9ddf558();
            }
        });
        this.fileDownloadTask.addOnPausedListener((Executor) executorService, (OnPausedListener) new OnPausedListener() {
            public final void onPaused(Object obj) {
                ReactNativeFirebaseStorageDownloadTask.this.mo35622x40fbe637((TaskSnapshot) obj);
            }
        });
    }

    /* renamed from: lambda$addEventListeners$1$ReactNativeFirebaseStorageDownloadTask */
    public /* synthetic */ void mo35620x92c00479(TaskSnapshot taskSnapshot) {
        StringBuilder sb = new StringBuilder();
        sb.append("onProgress ");
        sb.append(this.storageReference.toString());
        Log.d(TAG, sb.toString());
        ReactNativeFirebaseEventEmitter.getSharedInstance().sendEvent(new ReactNativeFirebaseStorageEvent(buildDownloadSnapshotMap(taskSnapshot), "state_changed", this.appName, this.taskId));
    }

    /* renamed from: lambda$addEventListeners$2$ReactNativeFirebaseStorageDownloadTask */
    public /* synthetic */ void mo35621xe9ddf558() {
        StringBuilder sb = new StringBuilder();
        sb.append("onCancelled ");
        sb.append(this.storageReference.toString());
        Log.d(TAG, sb.toString());
        ReactNativeFirebaseEventEmitter.getSharedInstance().sendEvent(new ReactNativeFirebaseStorageEvent(buildCancelledSnapshotMap(buildDownloadSnapshotMap((TaskSnapshot) this.fileDownloadTask.getSnapshot())), "state_changed", this.appName, this.taskId));
    }

    /* renamed from: lambda$addEventListeners$3$ReactNativeFirebaseStorageDownloadTask */
    public /* synthetic */ void mo35622x40fbe637(TaskSnapshot taskSnapshot) {
        StringBuilder sb = new StringBuilder();
        sb.append("onPaused ");
        sb.append(this.storageReference.toString());
        Log.d(TAG, sb.toString());
        ReactNativeFirebaseEventEmitter.getSharedInstance().sendEvent(new ReactNativeFirebaseStorageEvent(buildDownloadSnapshotMap(taskSnapshot), "state_changed", this.appName, this.taskId));
    }

    /* access modifiers changed from: 0000 */
    public void begin(ExecutorService executorService, String str) {
        String path = getPath(str);
        File file = new File(path);
        if (!file.exists() ? file.mkdirs() : true) {
            this.fileDownloadTask = this.storageReference.getFile(new File(path, getFileName(str)));
            addEventListeners(executorService);
            setStorageTask(this.fileDownloadTask);
        }
    }
}
