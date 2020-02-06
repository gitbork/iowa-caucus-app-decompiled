package p006io.sentry.event.helper;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import p006io.sentry.SentryClient;
import p006io.sentry.context.Context;
import p006io.sentry.event.EventBuilder;
import p006io.sentry.event.User;
import p006io.sentry.event.interfaces.UserInterface;

/* renamed from: io.sentry.event.helper.ContextBuilderHelper */
public class ContextBuilderHelper implements EventBuilderHelper {
    private SentryClient sentryClient;

    public ContextBuilderHelper(SentryClient sentryClient2) {
        this.sentryClient = sentryClient2;
    }

    public void helpBuildingEvent(EventBuilder eventBuilder) {
        Context context = this.sentryClient.getContext();
        List breadcrumbs = context.getBreadcrumbs();
        if (!breadcrumbs.isEmpty()) {
            eventBuilder.withBreadcrumbs(breadcrumbs);
        }
        if (context.getHttp() != null) {
            eventBuilder.withSentryInterface(context.getHttp());
        }
        if (context.getUser() != null) {
            eventBuilder.withSentryInterface(fromUser(context.getUser()));
        }
        Map tags = context.getTags();
        if (!tags.isEmpty()) {
            for (Entry entry : tags.entrySet()) {
                eventBuilder.withTag((String) entry.getKey(), (String) entry.getValue());
            }
        }
        Map extra = context.getExtra();
        if (!extra.isEmpty()) {
            for (Entry entry2 : extra.entrySet()) {
                eventBuilder.withExtra((String) entry2.getKey(), entry2.getValue());
            }
        }
    }

    private UserInterface fromUser(User user) {
        UserInterface userInterface = new UserInterface(user.getId(), user.getUsername(), user.getIpAddress(), user.getEmail(), user.getData());
        return userInterface;
    }
}
