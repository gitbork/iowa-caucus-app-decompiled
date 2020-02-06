package p006io.sentry.context;

/* renamed from: io.sentry.context.ThreadLocalContextManager */
public class ThreadLocalContextManager implements ContextManager {
    private final ThreadLocal<Context> context = new ThreadLocal<Context>() {
        /* access modifiers changed from: protected */
        public Context initialValue() {
            return new Context();
        }
    };

    public Context getContext() {
        return (Context) this.context.get();
    }

    public void clear() {
        this.context.remove();
    }
}
