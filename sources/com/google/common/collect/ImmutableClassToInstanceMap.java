package com.google.common.collect;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;
import com.google.common.primitives.Primitives;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.io.Serializable;
import java.util.Map;
import java.util.Map.Entry;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

@GwtIncompatible
public final class ImmutableClassToInstanceMap<B> extends ForwardingMap<Class<? extends B>, B> implements ClassToInstanceMap<B>, Serializable {
    private static final ImmutableClassToInstanceMap<Object> EMPTY = new ImmutableClassToInstanceMap<>(ImmutableMap.m236of());
    private final ImmutableMap<Class<? extends B>, B> delegate;

    public static final class Builder<B> {
        private final com.google.common.collect.ImmutableMap.Builder<Class<? extends B>, B> mapBuilder = ImmutableMap.builder();

        @CanIgnoreReturnValue
        public <T extends B> Builder<B> put(Class<T> cls, T t) {
            this.mapBuilder.put(cls, t);
            return this;
        }

        @CanIgnoreReturnValue
        public <T extends B> Builder<B> putAll(Map<? extends Class<? extends T>, ? extends T> map) {
            for (Entry entry : map.entrySet()) {
                Class cls = (Class) entry.getKey();
                this.mapBuilder.put(cls, cast(cls, entry.getValue()));
            }
            return this;
        }

        private static <B, T extends B> T cast(Class<T> cls, B b) {
            return Primitives.wrap(cls).cast(b);
        }

        public ImmutableClassToInstanceMap<B> build() {
            ImmutableMap build = this.mapBuilder.build();
            if (build.isEmpty()) {
                return ImmutableClassToInstanceMap.m215of();
            }
            return new ImmutableClassToInstanceMap<>(build);
        }
    }

    /* renamed from: of */
    public static <B> ImmutableClassToInstanceMap<B> m215of() {
        return EMPTY;
    }

    /* renamed from: of */
    public static <B, T extends B> ImmutableClassToInstanceMap<B> m216of(Class<T> cls, T t) {
        return new ImmutableClassToInstanceMap<>(ImmutableMap.m237of(cls, t));
    }

    public static <B> Builder<B> builder() {
        return new Builder<>();
    }

    public static <B, S extends B> ImmutableClassToInstanceMap<B> copyOf(Map<? extends Class<? extends S>, ? extends S> map) {
        if (map instanceof ImmutableClassToInstanceMap) {
            return (ImmutableClassToInstanceMap) map;
        }
        return new Builder().putAll(map).build();
    }

    private ImmutableClassToInstanceMap(ImmutableMap<Class<? extends B>, B> immutableMap) {
        this.delegate = immutableMap;
    }

    /* access modifiers changed from: protected */
    public Map<Class<? extends B>, B> delegate() {
        return this.delegate;
    }

    @NullableDecl
    public <T extends B> T getInstance(Class<T> cls) {
        return this.delegate.get(Preconditions.checkNotNull(cls));
    }

    @CanIgnoreReturnValue
    @Deprecated
    public <T extends B> T putInstance(Class<T> cls, T t) {
        throw new UnsupportedOperationException();
    }

    /* access modifiers changed from: 0000 */
    public Object readResolve() {
        return isEmpty() ? m215of() : this;
    }
}
