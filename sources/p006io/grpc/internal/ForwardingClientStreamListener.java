package p006io.grpc.internal;

import com.google.common.base.MoreObjects;
import p006io.grpc.Metadata;
import p006io.grpc.Status;
import p006io.grpc.internal.ClientStreamListener.RpcProgress;
import p006io.grpc.internal.StreamListener.MessageProducer;

/* renamed from: io.grpc.internal.ForwardingClientStreamListener */
abstract class ForwardingClientStreamListener implements ClientStreamListener {
    /* access modifiers changed from: protected */
    public abstract ClientStreamListener delegate();

    ForwardingClientStreamListener() {
    }

    public void headersRead(Metadata metadata) {
        delegate().headersRead(metadata);
    }

    public void closed(Status status, Metadata metadata) {
        delegate().closed(status, metadata);
    }

    public void closed(Status status, RpcProgress rpcProgress, Metadata metadata) {
        delegate().closed(status, rpcProgress, metadata);
    }

    public void messagesAvailable(MessageProducer messageProducer) {
        delegate().messagesAvailable(messageProducer);
    }

    public void onReady() {
        delegate().onReady();
    }

    public String toString() {
        return MoreObjects.toStringHelper((Object) this).add("delegate", (Object) delegate()).toString();
    }
}
