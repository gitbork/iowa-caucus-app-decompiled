package p006io.opencensus.trace.internal;

import androidx.core.app.NotificationCompat;
import p006io.opencensus.internal.Utils;
import p006io.opencensus.trace.BaseMessageEvent;
import p006io.opencensus.trace.MessageEvent;
import p006io.opencensus.trace.MessageEvent.Type;
import p006io.opencensus.trace.NetworkEvent;

/* renamed from: io.opencensus.trace.internal.BaseMessageEventUtils */
public final class BaseMessageEventUtils {
    public static MessageEvent asMessageEvent(BaseMessageEvent baseMessageEvent) {
        Type type;
        Utils.checkNotNull(baseMessageEvent, NotificationCompat.CATEGORY_EVENT);
        if (baseMessageEvent instanceof MessageEvent) {
            return (MessageEvent) baseMessageEvent;
        }
        NetworkEvent networkEvent = (NetworkEvent) baseMessageEvent;
        if (networkEvent.getType() == NetworkEvent.Type.RECV) {
            type = Type.RECEIVED;
        } else {
            type = Type.SENT;
        }
        return MessageEvent.builder(type, networkEvent.getMessageId()).setUncompressedMessageSize(networkEvent.getUncompressedMessageSize()).setCompressedMessageSize(networkEvent.getCompressedMessageSize()).build();
    }

    public static NetworkEvent asNetworkEvent(BaseMessageEvent baseMessageEvent) {
        NetworkEvent.Type type;
        Utils.checkNotNull(baseMessageEvent, NotificationCompat.CATEGORY_EVENT);
        if (baseMessageEvent instanceof NetworkEvent) {
            return (NetworkEvent) baseMessageEvent;
        }
        MessageEvent messageEvent = (MessageEvent) baseMessageEvent;
        if (messageEvent.getType() == Type.RECEIVED) {
            type = NetworkEvent.Type.RECV;
        } else {
            type = NetworkEvent.Type.SENT;
        }
        return NetworkEvent.builder(type, messageEvent.getMessageId()).setUncompressedMessageSize(messageEvent.getUncompressedMessageSize()).setCompressedMessageSize(messageEvent.getCompressedMessageSize()).build();
    }

    private BaseMessageEventUtils() {
    }
}
