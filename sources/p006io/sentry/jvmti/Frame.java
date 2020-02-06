package p006io.sentry.jvmti;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* renamed from: io.sentry.jvmti.Frame */
public final class Frame {
    private final LocalVariable[] locals;
    private Method method;

    /* renamed from: io.sentry.jvmti.Frame$LocalVariable */
    public static final class LocalVariable {
        final String name;
        final Object value;

        public LocalVariable(String str, Object obj) {
            this.name = str;
            this.value = obj;
        }

        public String getName() {
            return this.name;
        }

        public Object getValue() {
            return this.value;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("LocalVariable{name='");
            sb.append(this.name);
            sb.append('\'');
            sb.append(", value=");
            sb.append(this.value);
            sb.append('}');
            return sb.toString();
        }
    }

    public Frame(Method method2, LocalVariable[] localVariableArr) {
        this.method = method2;
        this.locals = localVariableArr;
    }

    public Method getMethod() {
        return this.method;
    }

    public Map<String, Object> getLocals() {
        LocalVariable[] localVariableArr;
        LocalVariable[] localVariableArr2 = this.locals;
        if (localVariableArr2 == null || localVariableArr2.length == 0) {
            return Collections.emptyMap();
        }
        HashMap hashMap = new HashMap();
        for (LocalVariable localVariable : this.locals) {
            if (localVariable != null) {
                hashMap.put(localVariable.getName(), localVariable.getValue());
            }
        }
        return hashMap;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Frame{, locals=");
        sb.append(Arrays.toString(this.locals));
        sb.append('}');
        return sb.toString();
    }
}
