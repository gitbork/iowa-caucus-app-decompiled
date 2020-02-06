package p006io.opencensus.metrics.data;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import javax.annotation.concurrent.Immutable;
import p006io.opencensus.common.Timestamp;
import p006io.opencensus.internal.Utils;

@Immutable
/* renamed from: io.opencensus.metrics.data.Exemplar */
public abstract class Exemplar {
    public abstract Map<String, AttachmentValue> getAttachments();

    public abstract Timestamp getTimestamp();

    public abstract double getValue();

    Exemplar() {
    }

    public static Exemplar create(double d, Timestamp timestamp, Map<String, AttachmentValue> map) {
        Utils.checkNotNull(map, "attachments");
        Map unmodifiableMap = Collections.unmodifiableMap(new HashMap(map));
        for (Entry entry : unmodifiableMap.entrySet()) {
            Utils.checkNotNull(entry.getKey(), "key of attachments");
            Utils.checkNotNull(entry.getValue(), "value of attachments");
        }
        return new AutoValue_Exemplar(d, timestamp, unmodifiableMap);
    }
}
