package p006io.sentry.context;

/* renamed from: io.sentry.context.SingletonContextManager */
public class SingletonContextManager implements ContextManager {
    private final Context context = new Context();

    public Context getContext() {
        return this.context;
    }

    public void clear() {
        this.context.clear();
    }
}
