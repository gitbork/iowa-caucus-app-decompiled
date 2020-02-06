package p006io.opencensus.tags;

import javax.annotation.concurrent.Immutable;
import p006io.opencensus.internal.StringUtils;
import p006io.opencensus.internal.Utils;

@Immutable
/* renamed from: io.opencensus.tags.TagKey */
public abstract class TagKey {
    public static final int MAX_LENGTH = 255;

    public abstract String getName();

    TagKey() {
    }

    public static TagKey create(String str) {
        Utils.checkArgument(isValid(str), "Invalid TagKey name: %s", str);
        return new AutoValue_TagKey(str);
    }

    private static boolean isValid(String str) {
        return !str.isEmpty() && str.length() <= 255 && StringUtils.isPrintableString(str);
    }
}
