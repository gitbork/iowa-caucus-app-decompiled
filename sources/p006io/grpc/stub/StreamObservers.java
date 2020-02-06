package p006io.grpc.stub;

import com.facebook.react.uimanager.events.TouchesHelper;
import com.google.common.base.Preconditions;
import com.google.firebase.analytics.FirebaseAnalytics.Param;
import java.util.Iterator;
import p006io.grpc.ExperimentalApi;

@ExperimentalApi("https://github.com/grpc/grpc-java/issues/4694")
/* renamed from: io.grpc.stub.StreamObservers */
public final class StreamObservers {
    public static <V> void copyWithFlowControl(final Iterator<V> it, final CallStreamObserver<V> callStreamObserver) {
        Preconditions.checkNotNull(it, Param.SOURCE);
        Preconditions.checkNotNull(callStreamObserver, TouchesHelper.TARGET_KEY);
        callStreamObserver.setOnReadyHandler(new Runnable() {
            private boolean completed;

            public void run() {
                if (!this.completed) {
                    while (CallStreamObserver.this.isReady() && it.hasNext()) {
                        CallStreamObserver.this.onNext(it.next());
                    }
                    if (!it.hasNext()) {
                        this.completed = true;
                        CallStreamObserver.this.onCompleted();
                    }
                }
            }
        });
    }

    public static <V> void copyWithFlowControl(Iterable<V> iterable, CallStreamObserver<V> callStreamObserver) {
        Preconditions.checkNotNull(iterable, Param.SOURCE);
        copyWithFlowControl(iterable.iterator(), callStreamObserver);
    }
}
