package p006io.invertase.firebase.storage;

import android.util.Log;
import android.util.SparseArray;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import javax.annotation.Nullable;

/* renamed from: io.invertase.firebase.storage.ReactNativeFirebaseStorageTask */
class ReactNativeFirebaseStorageTask {
    static final String KEY_BYTES_TRANSFERRED = "bytesTransferred";
    private static final String KEY_CODE = "code";
    private static final String KEY_ERROR = "error";
    private static final String KEY_MESSAGE = "message";
    static final String KEY_META_DATA = "metadata";
    private static final String KEY_NATIVE_ERROR_MESSAGE = "nativeErrorMessage";
    static final String KEY_STATE = "state";
    static final String KEY_TOTAL_BYTES = "totalBytes";
    private static final SparseArray<ReactNativeFirebaseStorageTask> PENDING_TASKS = new SparseArray<>();
    private static final String TAG = "RNFBStorageTask";
    String appName;
    StorageReference storageReference;
    private StorageTask storageTask;
    int taskId;

    ReactNativeFirebaseStorageTask(int i, StorageReference storageReference2, String str) {
        this.taskId = i;
        this.storageReference = storageReference2;
        this.appName = str;
        PENDING_TASKS.put(i, this);
    }

    static boolean pauseTaskById(int i) {
        ReactNativeFirebaseStorageTask reactNativeFirebaseStorageTask = (ReactNativeFirebaseStorageTask) PENDING_TASKS.get(i);
        if (reactNativeFirebaseStorageTask != null) {
            return reactNativeFirebaseStorageTask.pause();
        }
        return false;
    }

    static boolean resumeTaskById(int i) {
        ReactNativeFirebaseStorageTask reactNativeFirebaseStorageTask = (ReactNativeFirebaseStorageTask) PENDING_TASKS.get(i);
        if (reactNativeFirebaseStorageTask != null) {
            return reactNativeFirebaseStorageTask.resume();
        }
        return false;
    }

    static boolean cancelTaskById(int i) {
        ReactNativeFirebaseStorageTask reactNativeFirebaseStorageTask = (ReactNativeFirebaseStorageTask) PENDING_TASKS.get(i);
        if (reactNativeFirebaseStorageTask != null) {
            return reactNativeFirebaseStorageTask.cancel();
        }
        return false;
    }

    static void destroyAllTasks() {
        int size = PENDING_TASKS.size();
        for (int i = 0; i < size; i++) {
            ((ReactNativeFirebaseStorageTask) PENDING_TASKS.get(PENDING_TASKS.keyAt(i))).cancel();
        }
        PENDING_TASKS.clear();
    }

    static WritableMap buildCancelledSnapshotMap(WritableMap writableMap) {
        writableMap.putString(KEY_STATE, "cancelled");
        return writableMap;
    }

    static WritableMap buildErrorSnapshotMap(@Nullable Exception exc, WritableMap writableMap, boolean z) {
        WritableMap createMap = Arguments.createMap();
        String[] exceptionCodeAndMessage = ReactNativeFirebaseStorageCommon.getExceptionCodeAndMessage(exc);
        if (z && exceptionCodeAndMessage[0].equals("cancelled")) {
            return null;
        }
        createMap.putString(KEY_CODE, exceptionCodeAndMessage[0]);
        createMap.putString("message", exceptionCodeAndMessage[1]);
        if (exc != null) {
            createMap.putString(KEY_NATIVE_ERROR_MESSAGE, exc.getMessage());
        }
        String str = KEY_ERROR;
        writableMap.putMap(str, createMap);
        writableMap.putString(KEY_STATE, str);
        return writableMap;
    }

    private boolean pause() {
        StringBuilder sb = new StringBuilder();
        sb.append("pausing task for ");
        sb.append(this.storageReference.toString());
        Log.d(TAG, sb.toString());
        if (this.storageTask.isPaused() || !this.storageTask.isInProgress()) {
            return false;
        }
        return this.storageTask.pause();
    }

    private boolean resume() {
        StringBuilder sb = new StringBuilder();
        sb.append("resuming task for ");
        sb.append(this.storageReference.toString());
        Log.d(TAG, sb.toString());
        if (this.storageTask.isPaused()) {
            return this.storageTask.resume();
        }
        return false;
    }

    private boolean cancel() {
        StringBuilder sb = new StringBuilder();
        sb.append("cancelling task for ");
        sb.append(this.storageReference.toString());
        Log.d(TAG, sb.toString());
        if (this.storageTask.isCanceled() || !this.storageTask.isInProgress()) {
            return false;
        }
        destroyTask();
        return this.storageTask.cancel();
    }

    /* access modifiers changed from: 0000 */
    public void destroyTask() {
        PENDING_TASKS.remove(this.taskId);
        StringBuilder sb = new StringBuilder();
        sb.append("destroyed completed task for ");
        sb.append(this.storageReference.toString());
        Log.d(TAG, sb.toString());
    }

    /* access modifiers changed from: 0000 */
    public void setStorageTask(StorageTask storageTask2) {
        this.storageTask = storageTask2;
    }
}
