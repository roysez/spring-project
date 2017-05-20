package org.roysez.app.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.roysez.app.model.User;

import java.io.IOException;

/**
 * Created by roysez on 20.05.2017.
 * 0:55
 * Package : org.roysez.app.util
 */
public class JsonUserSerializer extends JsonSerializer<User> {



    @Override
    public void serialize(User user, JsonGenerator gen, SerializerProvider provider)
            throws IOException, JsonProcessingException {
        String username = user.getSsoId();
        gen.writeString(username);
    }
}
