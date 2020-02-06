package p006io.opencensus.stats;

import javax.annotation.concurrent.NotThreadSafe;
import p006io.opencensus.internal.Utils;
import p006io.opencensus.metrics.data.AttachmentValue;
import p006io.opencensus.metrics.data.AttachmentValue.AttachmentValueString;
import p006io.opencensus.stats.Measure.MeasureDouble;
import p006io.opencensus.stats.Measure.MeasureLong;
import p006io.opencensus.tags.TagContext;

@NotThreadSafe
/* renamed from: io.opencensus.stats.MeasureMap */
public abstract class MeasureMap {
    public abstract MeasureMap put(MeasureDouble measureDouble, double d);

    public abstract MeasureMap put(MeasureLong measureLong, long j);

    public abstract void record();

    public abstract void record(TagContext tagContext);

    @Deprecated
    public MeasureMap putAttachment(String str, String str2) {
        return putAttachment(str, (AttachmentValue) AttachmentValueString.create(str2));
    }

    public MeasureMap putAttachment(String str, AttachmentValue attachmentValue) {
        Utils.checkNotNull(str, "key");
        Utils.checkNotNull(attachmentValue, "value");
        return this;
    }
}
