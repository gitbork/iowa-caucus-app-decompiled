package p006io.opencensus.stats;

import javax.annotation.concurrent.Immutable;
import p006io.opencensus.common.Function;
import p006io.opencensus.internal.StringUtils;
import p006io.opencensus.internal.Utils;

@Immutable
/* renamed from: io.opencensus.stats.Measure */
public abstract class Measure {
    private static final String ERROR_MESSAGE_INVALID_NAME = "Name should be a ASCII string with a length no greater than 255 characters.";
    static final int NAME_MAX_LENGTH = 255;

    @Immutable
    /* renamed from: io.opencensus.stats.Measure$MeasureDouble */
    public static abstract class MeasureDouble extends Measure {
        public abstract String getDescription();

        public abstract String getName();

        public abstract String getUnit();

        MeasureDouble() {
            super();
        }

        public static MeasureDouble create(String str, String str2, String str3) {
            Utils.checkArgument(StringUtils.isPrintableString(str) && str.length() <= 255, Measure.ERROR_MESSAGE_INVALID_NAME);
            return new AutoValue_Measure_MeasureDouble(str, str2, str3);
        }

        public <T> T match(Function<? super MeasureDouble, T> function, Function<? super MeasureLong, T> function2, Function<? super Measure, T> function3) {
            return function.apply(this);
        }
    }

    @Immutable
    /* renamed from: io.opencensus.stats.Measure$MeasureLong */
    public static abstract class MeasureLong extends Measure {
        public abstract String getDescription();

        public abstract String getName();

        public abstract String getUnit();

        MeasureLong() {
            super();
        }

        public static MeasureLong create(String str, String str2, String str3) {
            Utils.checkArgument(StringUtils.isPrintableString(str) && str.length() <= 255, Measure.ERROR_MESSAGE_INVALID_NAME);
            return new AutoValue_Measure_MeasureLong(str, str2, str3);
        }

        public <T> T match(Function<? super MeasureDouble, T> function, Function<? super MeasureLong, T> function2, Function<? super Measure, T> function3) {
            return function2.apply(this);
        }
    }

    public abstract String getDescription();

    public abstract String getName();

    public abstract String getUnit();

    public abstract <T> T match(Function<? super MeasureDouble, T> function, Function<? super MeasureLong, T> function2, Function<? super Measure, T> function3);

    private Measure() {
    }
}
