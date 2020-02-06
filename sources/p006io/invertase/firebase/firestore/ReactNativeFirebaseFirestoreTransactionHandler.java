package p006io.invertase.firebase.firestore;

import com.facebook.react.bridge.ReadableArray;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Transaction;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/* renamed from: io.invertase.firebase.firestore.ReactNativeFirebaseFirestoreTransactionHandler */
class ReactNativeFirebaseFirestoreTransactionHandler {
    boolean aborted = false;
    private String appName;
    private ReadableArray commandBuffer;
    private final Condition condition;
    private Transaction firestoreTransaction;
    private final ReentrantLock lock;
    boolean timeout = false;
    private long timeoutAt;
    private int transactionId;

    ReactNativeFirebaseFirestoreTransactionHandler(String str, int i) {
        this.appName = str;
        this.transactionId = i;
        updateInternalTimeout();
        this.lock = new ReentrantLock();
        this.condition = this.lock.newCondition();
    }

    /* access modifiers changed from: 0000 */
    public void abort() {
        this.aborted = true;
        safeUnlock();
    }

    /* access modifiers changed from: 0000 */
    public void resetState(Transaction transaction) {
        this.commandBuffer = null;
        this.firestoreTransaction = transaction;
    }

    /* access modifiers changed from: 0000 */
    public void signalBufferReceived(ReadableArray readableArray) {
        this.lock.lock();
        try {
            this.commandBuffer = readableArray;
            this.condition.signalAll();
        } finally {
            safeUnlock();
        }
    }

    /* access modifiers changed from: 0000 */
    public void await() {
        this.lock.lock();
        updateInternalTimeout();
        while (!this.aborted && !this.timeout && !this.condition.await(10, TimeUnit.MILLISECONDS)) {
            try {
                if (System.currentTimeMillis() > this.timeoutAt) {
                    this.timeout = true;
                }
            } catch (InterruptedException unused) {
            } catch (Throwable th) {
                safeUnlock();
                throw th;
            }
        }
        safeUnlock();
    }

    /* access modifiers changed from: 0000 */
    public ReadableArray getCommandBuffer() {
        return this.commandBuffer;
    }

    /* access modifiers changed from: 0000 */
    public int getTransactionId() {
        return this.transactionId;
    }

    /* access modifiers changed from: 0000 */
    public String getAppName() {
        return this.appName;
    }

    /* access modifiers changed from: 0000 */
    public DocumentSnapshot getDocument(DocumentReference documentReference) throws FirebaseFirestoreException {
        updateInternalTimeout();
        return this.firestoreTransaction.get(documentReference);
    }

    private void safeUnlock() {
        if (this.lock.isLocked()) {
            this.lock.unlock();
        }
    }

    private void updateInternalTimeout() {
        this.timeoutAt = System.currentTimeMillis() + 15000;
    }
}
