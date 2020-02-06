package com.google.firebase.firestore;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.common.base.Preconditions;
import com.google.firebase.FirebaseApp;
import com.google.firebase.annotations.PublicApi;
import com.google.firebase.auth.internal.InternalAuthProvider;
import com.google.firebase.firestore.FirebaseFirestoreException.Code;
import com.google.firebase.firestore.FirebaseFirestoreSettings.Builder;
import com.google.firebase.firestore.Transaction.Function;
import com.google.firebase.firestore.auth.CredentialsProvider;
import com.google.firebase.firestore.auth.EmptyCredentialsProvider;
import com.google.firebase.firestore.auth.FirebaseAuthCredentialsProvider;
import com.google.firebase.firestore.core.DatabaseInfo;
import com.google.firebase.firestore.core.FirestoreClient;
import com.google.firebase.firestore.core.Query;
import com.google.firebase.firestore.core.Transaction;
import com.google.firebase.firestore.local.SQLitePersistence;
import com.google.firebase.firestore.model.DatabaseId;
import com.google.firebase.firestore.model.ResourcePath;
import com.google.firebase.firestore.util.AsyncQueue;
import com.google.firebase.firestore.util.Logger;
import com.google.firebase.firestore.util.Logger.Level;
import java.util.concurrent.Executor;

@PublicApi
/* compiled from: com.google.firebase:firebase-firestore@@20.2.0 */
public class FirebaseFirestore {
    private static final String TAG = "FirebaseFirestore";
    private final AsyncQueue asyncQueue;
    private volatile FirestoreClient client;
    private final Context context;
    private final CredentialsProvider credentialsProvider;
    private final UserDataConverter dataConverter;
    private final DatabaseId databaseId;
    private final FirebaseApp firebaseApp;
    private final String persistenceKey;
    private FirebaseFirestoreSettings settings = new Builder().build();

    @PublicApi
    @NonNull
    public static FirebaseFirestore getInstance() {
        FirebaseApp instance = FirebaseApp.getInstance();
        if (instance != null) {
            return getInstance(instance, "(default)");
        }
        throw new IllegalStateException("You must call FirebaseApp.initializeApp first.");
    }

    @PublicApi
    @NonNull
    public static FirebaseFirestore getInstance(@NonNull FirebaseApp firebaseApp2) {
        return getInstance(firebaseApp2, "(default)");
    }

    @NonNull
    private static FirebaseFirestore getInstance(@NonNull FirebaseApp firebaseApp2, @NonNull String str) {
        Preconditions.checkNotNull(firebaseApp2, "Provided FirebaseApp must not be null.");
        FirestoreMultiDbComponent firestoreMultiDbComponent = (FirestoreMultiDbComponent) firebaseApp2.get(FirestoreMultiDbComponent.class);
        Preconditions.checkNotNull(firestoreMultiDbComponent, "Firestore component is not present.");
        return firestoreMultiDbComponent.get(str);
    }

    @NonNull
    static FirebaseFirestore newInstance(@NonNull Context context2, @NonNull FirebaseApp firebaseApp2, @Nullable InternalAuthProvider internalAuthProvider, @NonNull String str) {
        CredentialsProvider credentialsProvider2;
        String projectId = firebaseApp2.getOptions().getProjectId();
        if (projectId != null) {
            DatabaseId forDatabase = DatabaseId.forDatabase(projectId, str);
            AsyncQueue asyncQueue2 = new AsyncQueue();
            if (internalAuthProvider == null) {
                Logger.debug(TAG, "Firebase Auth not available, falling back to unauthenticated usage.", new Object[0]);
                credentialsProvider2 = new EmptyCredentialsProvider();
            } else {
                credentialsProvider2 = new FirebaseAuthCredentialsProvider(internalAuthProvider);
            }
            FirebaseFirestore firebaseFirestore = new FirebaseFirestore(context2, forDatabase, firebaseApp2.getName(), credentialsProvider2, asyncQueue2, firebaseApp2);
            return firebaseFirestore;
        }
        throw new IllegalArgumentException("FirebaseOptions.getProjectId() cannot be null");
    }

    @VisibleForTesting
    FirebaseFirestore(Context context2, DatabaseId databaseId2, String str, CredentialsProvider credentialsProvider2, AsyncQueue asyncQueue2, @Nullable FirebaseApp firebaseApp2) {
        this.context = (Context) Preconditions.checkNotNull(context2);
        this.databaseId = (DatabaseId) Preconditions.checkNotNull((DatabaseId) Preconditions.checkNotNull(databaseId2));
        this.dataConverter = new UserDataConverter(databaseId2);
        this.persistenceKey = (String) Preconditions.checkNotNull(str);
        this.credentialsProvider = (CredentialsProvider) Preconditions.checkNotNull(credentialsProvider2);
        this.asyncQueue = (AsyncQueue) Preconditions.checkNotNull(asyncQueue2);
        this.firebaseApp = firebaseApp2;
    }

    @PublicApi
    @NonNull
    public FirebaseFirestoreSettings getFirestoreSettings() {
        return this.settings;
    }

    @PublicApi
    public void setFirestoreSettings(@NonNull FirebaseFirestoreSettings firebaseFirestoreSettings) {
        synchronized (this.databaseId) {
            Preconditions.checkNotNull(firebaseFirestoreSettings, "Provided settings must not be null.");
            if (this.client != null) {
                if (!this.settings.equals(firebaseFirestoreSettings)) {
                    throw new IllegalStateException("FirebaseFirestore has already been started and its settings can no longer be changed. You can only call setFirestoreSettings() before calling any other methods on a FirebaseFirestore object.");
                }
            }
            this.settings = firebaseFirestoreSettings;
        }
    }

    private void ensureClientConfigured() {
        if (this.client == null) {
            synchronized (this.databaseId) {
                if (this.client == null) {
                    FirestoreClient firestoreClient = new FirestoreClient(this.context, new DatabaseInfo(this.databaseId, this.persistenceKey, this.settings.getHost(), this.settings.isSslEnabled()), this.settings, this.credentialsProvider, this.asyncQueue);
                    this.client = firestoreClient;
                }
            }
        }
    }

    @PublicApi
    @NonNull
    public FirebaseApp getApp() {
        return this.firebaseApp;
    }

    @PublicApi
    @NonNull
    public CollectionReference collection(@NonNull String str) {
        Preconditions.checkNotNull(str, "Provided collection path must not be null.");
        ensureClientConfigured();
        return new CollectionReference(ResourcePath.fromString(str), this);
    }

    @PublicApi
    @NonNull
    public DocumentReference document(@NonNull String str) {
        Preconditions.checkNotNull(str, "Provided document path must not be null.");
        ensureClientConfigured();
        return DocumentReference.forPath(ResourcePath.fromString(str), this);
    }

    @PublicApi
    @NonNull
    public Query collectionGroup(@NonNull String str) {
        Preconditions.checkNotNull(str, "Provided collection ID must not be null.");
        if (!str.contains("/")) {
            ensureClientConfigured();
            return new Query(new Query(ResourcePath.EMPTY, str), this);
        }
        throw new IllegalArgumentException(String.format("Invalid collectionId '%s'. Collection IDs must not contain '/'.", new Object[]{str}));
    }

    private <TResult> Task<TResult> runTransaction(Function<TResult> function, Executor executor) {
        ensureClientConfigured();
        return this.client.transaction(FirebaseFirestore$$Lambda$1.lambdaFactory$(this, executor, function), 5);
    }

    @PublicApi
    @NonNull
    public <TResult> Task<TResult> runTransaction(@NonNull Function<TResult> function) {
        Preconditions.checkNotNull(function, "Provided transaction update function must not be null.");
        return runTransaction(function, Transaction.getDefaultExecutor());
    }

    @PublicApi
    @NonNull
    public WriteBatch batch() {
        ensureClientConfigured();
        return new WriteBatch(this);
    }

    @PublicApi
    @NonNull
    public Task<Void> runBatch(@NonNull WriteBatch.Function function) {
        WriteBatch batch = batch();
        function.apply(batch);
        return batch.commit();
    }

    /* access modifiers changed from: 0000 */
    @VisibleForTesting
    public Task<Void> shutdown() {
        ensureClientConfigured();
        return this.client.shutdown();
    }

    /* access modifiers changed from: 0000 */
    @VisibleForTesting
    public AsyncQueue getAsyncQueue() {
        return this.asyncQueue;
    }

    @PublicApi
    public Task<Void> enableNetwork() {
        ensureClientConfigured();
        return this.client.enableNetwork();
    }

    @PublicApi
    public Task<Void> disableNetwork() {
        ensureClientConfigured();
        return this.client.disableNetwork();
    }

    @PublicApi
    public static void setLoggingEnabled(boolean z) {
        if (z) {
            Logger.setLogLevel(Level.DEBUG);
        } else {
            Logger.setLogLevel(Level.WARN);
        }
    }

    @PublicApi
    public Task<Void> clearPersistence() {
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        this.asyncQueue.enqueueAndForget(FirebaseFirestore$$Lambda$2.lambdaFactory$(this, taskCompletionSource));
        return taskCompletionSource.getTask();
    }

    static /* synthetic */ void lambda$clearPersistence$2(FirebaseFirestore firebaseFirestore, TaskCompletionSource taskCompletionSource) {
        try {
            if (firebaseFirestore.client != null) {
                if (!firebaseFirestore.client.isShutdown()) {
                    throw new FirebaseFirestoreException("Persistence cannot be cleared while the firestore instance is running.", Code.FAILED_PRECONDITION);
                }
            }
            SQLitePersistence.clearPersistence(firebaseFirestore.context, firebaseFirestore.databaseId, firebaseFirestore.persistenceKey);
            taskCompletionSource.setResult(null);
        } catch (FirebaseFirestoreException e) {
            taskCompletionSource.setException(e);
        }
    }

    /* access modifiers changed from: 0000 */
    public FirestoreClient getClient() {
        return this.client;
    }

    /* access modifiers changed from: 0000 */
    public DatabaseId getDatabaseId() {
        return this.databaseId;
    }

    /* access modifiers changed from: 0000 */
    public UserDataConverter getDataConverter() {
        return this.dataConverter;
    }

    /* access modifiers changed from: 0000 */
    public void validateReference(DocumentReference documentReference) {
        Preconditions.checkNotNull(documentReference, "Provided DocumentReference must not be null.");
        if (documentReference.getFirestore() != this) {
            throw new IllegalArgumentException("Provided document reference is from a different Firestore instance.");
        }
    }
}
