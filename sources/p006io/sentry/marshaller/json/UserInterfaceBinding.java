package p006io.sentry.marshaller.json;

import com.fasterxml.jackson.core.JsonGenerator;
import java.io.IOException;
import java.util.Map.Entry;
import p006io.sentry.event.interfaces.UserInterface;

/* renamed from: io.sentry.marshaller.json.UserInterfaceBinding */
public class UserInterfaceBinding implements InterfaceBinding<UserInterface> {
    private static final String DATA = "data";
    private static final String EMAIL = "email";

    /* renamed from: ID */
    private static final String f481ID = "id";
    private static final String IP_ADDRESS = "ip_address";
    private static final String USERNAME = "username";

    public void writeInterface(JsonGenerator jsonGenerator, UserInterface userInterface) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField(f481ID, userInterface.getId());
        jsonGenerator.writeStringField(USERNAME, userInterface.getUsername());
        jsonGenerator.writeStringField("email", userInterface.getEmail());
        jsonGenerator.writeStringField(IP_ADDRESS, userInterface.getIpAddress());
        if (userInterface.getData() != null && !userInterface.getData().isEmpty()) {
            jsonGenerator.writeObjectFieldStart("data");
            for (Entry entry : userInterface.getData().entrySet()) {
                String str = (String) entry.getKey();
                Object value = entry.getValue();
                if (value == null) {
                    jsonGenerator.writeNullField(str);
                } else {
                    jsonGenerator.writeObjectField(str, value);
                }
            }
            jsonGenerator.writeEndObject();
        }
        jsonGenerator.writeEndObject();
    }
}
