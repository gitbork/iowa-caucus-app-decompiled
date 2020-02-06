package com.google.android.gms.common.api.internal;

import android.os.RemoteException;
import androidx.annotation.Nullable;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.BiConsumer;
import com.google.android.gms.tasks.TaskCompletionSource;

@KeepForSdk
public abstract class TaskApiCall<A extends AnyClient, ResultT> {
    private final Feature[] zake;
    private final boolean zakl;

    @KeepForSdk
    public static class Builder<A extends AnyClient, ResultT> {
        private Feature[] zake;
        private boolean zakl;
        /* access modifiers changed from: private */
        public RemoteCall<A, TaskCompletionSource<ResultT>> zakm;

        private Builder() {
            this.zakl = true;
        }

        @KeepForSdk
        @Deprecated
        public Builder<A, ResultT> execute(BiConsumer<A, TaskCompletionSource<ResultT>> biConsumer) {
            this.zakm = new zacj(biConsumer);
            return this;
        }

        @KeepForSdk
        public Builder<A, ResultT> run(RemoteCall<A, TaskCompletionSource<ResultT>> remoteCall) {
            this.zakm = remoteCall;
            return this;
        }

        @KeepForSdk
        public Builder<A, ResultT> setFeatures(Feature... featureArr) {
            this.zake = featureArr;
            return this;
        }

        @KeepForSdk
        public Builder<A, ResultT> setAutoResolveMissingFeatures(boolean z) {
            this.zakl = z;
            return this;
        }

        @KeepForSdk
        public TaskApiCall<A, ResultT> build() {
            Preconditions.checkArgument(this.zakm != null, "execute parameter required");
            return new zack(this, this.zake, this.zakl);
        }
    }

    @KeepForSdk
    @Deprecated
    public TaskApiCall() {
        this.zake = null;
        this.zakl = false;
    }

    /* access modifiers changed from: protected */
    @KeepForSdk
    public abstract void doExecute(A a, TaskCompletionSource<ResultT> taskCompletionSource) throws RemoteException;

    @KeepForSdk
    private TaskApiCall(Feature[] featureArr, boolean z) {
        this.zake = featureArr;
        this.zakl = z;
    }

    @Nullable
    public final Feature[] zabt() {
        return this.zake;
    }

    @KeepForSdk
    public boolean shouldAutoResolveMissingFeatures() {
        return this.zakl;
    }

    @KeepForSdk
    public static <A extends AnyClient, ResultT> Builder<A, ResultT> builder() {
        return new Builder<>();
    }
}
