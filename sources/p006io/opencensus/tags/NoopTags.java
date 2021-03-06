package p006io.opencensus.tags;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.ThreadSafe;
import p006io.opencensus.common.Scope;
import p006io.opencensus.internal.NoopScope;
import p006io.opencensus.internal.Utils;
import p006io.opencensus.tags.propagation.TagContextBinarySerializer;
import p006io.opencensus.tags.propagation.TagContextDeserializationException;
import p006io.opencensus.tags.propagation.TagContextSerializationException;
import p006io.opencensus.tags.propagation.TagContextTextFormat;
import p006io.opencensus.tags.propagation.TagContextTextFormat.Getter;
import p006io.opencensus.tags.propagation.TagContextTextFormat.Setter;
import p006io.opencensus.tags.propagation.TagPropagationComponent;

/* renamed from: io.opencensus.tags.NoopTags */
final class NoopTags {

    @Immutable
    /* renamed from: io.opencensus.tags.NoopTags$NoopTagContext */
    private static final class NoopTagContext extends TagContext {
        static final TagContext INSTANCE = new NoopTagContext();

        private NoopTagContext() {
        }

        /* access modifiers changed from: protected */
        public Iterator<Tag> getIterator() {
            return Collections.emptySet().iterator();
        }
    }

    @Immutable
    /* renamed from: io.opencensus.tags.NoopTags$NoopTagContextBinarySerializer */
    private static final class NoopTagContextBinarySerializer extends TagContextBinarySerializer {
        static final byte[] EMPTY_BYTE_ARRAY = new byte[0];
        static final TagContextBinarySerializer INSTANCE = new NoopTagContextBinarySerializer();

        private NoopTagContextBinarySerializer() {
        }

        public byte[] toByteArray(TagContext tagContext) {
            Utils.checkNotNull(tagContext, "tags");
            return EMPTY_BYTE_ARRAY;
        }

        public TagContext fromByteArray(byte[] bArr) {
            Utils.checkNotNull(bArr, "bytes");
            return NoopTags.getNoopTagContext();
        }
    }

    @Immutable
    /* renamed from: io.opencensus.tags.NoopTags$NoopTagContextBuilder */
    private static final class NoopTagContextBuilder extends TagContextBuilder {
        static final TagContextBuilder INSTANCE = new NoopTagContextBuilder();

        private NoopTagContextBuilder() {
        }

        public TagContextBuilder put(TagKey tagKey, TagValue tagValue) {
            Utils.checkNotNull(tagKey, "key");
            Utils.checkNotNull(tagValue, "value");
            return this;
        }

        public TagContextBuilder put(TagKey tagKey, TagValue tagValue, TagMetadata tagMetadata) {
            Utils.checkNotNull(tagKey, "key");
            Utils.checkNotNull(tagValue, "value");
            Utils.checkNotNull(tagMetadata, "tagMetadata");
            return this;
        }

        public TagContextBuilder remove(TagKey tagKey) {
            Utils.checkNotNull(tagKey, "key");
            return this;
        }

        public TagContext build() {
            return NoopTags.getNoopTagContext();
        }

        public Scope buildScoped() {
            return NoopScope.getInstance();
        }
    }

    @Immutable
    /* renamed from: io.opencensus.tags.NoopTags$NoopTagContextTextFormat */
    private static final class NoopTagContextTextFormat extends TagContextTextFormat {
        static final NoopTagContextTextFormat INSTANCE = new NoopTagContextTextFormat();

        private NoopTagContextTextFormat() {
        }

        public List<String> fields() {
            return Collections.emptyList();
        }

        public <C> void inject(TagContext tagContext, C c, Setter<C> setter) throws TagContextSerializationException {
            Utils.checkNotNull(tagContext, "tagContext");
            Utils.checkNotNull(c, "carrier");
            Utils.checkNotNull(setter, "setter");
        }

        public <C> TagContext extract(C c, Getter<C> getter) throws TagContextDeserializationException {
            Utils.checkNotNull(c, "carrier");
            Utils.checkNotNull(getter, "getter");
            return NoopTags.getNoopTagContext();
        }
    }

    @Immutable
    /* renamed from: io.opencensus.tags.NoopTags$NoopTagPropagationComponent */
    private static final class NoopTagPropagationComponent extends TagPropagationComponent {
        static final TagPropagationComponent INSTANCE = new NoopTagPropagationComponent();

        private NoopTagPropagationComponent() {
        }

        public TagContextBinarySerializer getBinarySerializer() {
            return NoopTags.getNoopTagContextBinarySerializer();
        }

        public TagContextTextFormat getCorrelationContextFormat() {
            return NoopTags.getNoopTagContextTextSerializer();
        }
    }

    @Immutable
    /* renamed from: io.opencensus.tags.NoopTags$NoopTagger */
    private static final class NoopTagger extends Tagger {
        static final Tagger INSTANCE = new NoopTagger();

        private NoopTagger() {
        }

        public TagContext empty() {
            return NoopTags.getNoopTagContext();
        }

        public TagContext getCurrentTagContext() {
            return NoopTags.getNoopTagContext();
        }

        public TagContextBuilder emptyBuilder() {
            return NoopTags.getNoopTagContextBuilder();
        }

        public TagContextBuilder toBuilder(TagContext tagContext) {
            Utils.checkNotNull(tagContext, "tags");
            return NoopTags.getNoopTagContextBuilder();
        }

        public TagContextBuilder currentBuilder() {
            return NoopTags.getNoopTagContextBuilder();
        }

        public Scope withTagContext(TagContext tagContext) {
            Utils.checkNotNull(tagContext, "tags");
            return NoopScope.getInstance();
        }
    }

    @ThreadSafe
    /* renamed from: io.opencensus.tags.NoopTags$NoopTagsComponent */
    private static final class NoopTagsComponent extends TagsComponent {
        private volatile boolean isRead;

        private NoopTagsComponent() {
        }

        public Tagger getTagger() {
            return NoopTags.getNoopTagger();
        }

        public TagPropagationComponent getTagPropagationComponent() {
            return NoopTags.getNoopTagPropagationComponent();
        }

        public TaggingState getState() {
            this.isRead = true;
            return TaggingState.DISABLED;
        }

        @Deprecated
        public void setState(TaggingState taggingState) {
            Utils.checkNotNull(taggingState, "state");
            Utils.checkState(!this.isRead, "State was already read, cannot set state.");
        }
    }

    private NoopTags() {
    }

    static TagsComponent newNoopTagsComponent() {
        return new NoopTagsComponent();
    }

    static Tagger getNoopTagger() {
        return NoopTagger.INSTANCE;
    }

    static TagContextBuilder getNoopTagContextBuilder() {
        return NoopTagContextBuilder.INSTANCE;
    }

    static TagContext getNoopTagContext() {
        return NoopTagContext.INSTANCE;
    }

    static TagPropagationComponent getNoopTagPropagationComponent() {
        return NoopTagPropagationComponent.INSTANCE;
    }

    static TagContextBinarySerializer getNoopTagContextBinarySerializer() {
        return NoopTagContextBinarySerializer.INSTANCE;
    }

    static TagContextTextFormat getNoopTagContextTextSerializer() {
        return NoopTagContextTextFormat.INSTANCE;
    }
}
