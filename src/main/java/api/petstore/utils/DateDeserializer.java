package api.petstore.utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;

public class DateDeserializer extends StdDeserializer<OffsetDateTime> {

    protected DateDeserializer() {
        this(null);
    }

    protected DateDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public OffsetDateTime deserialize(JsonParser jsonParser, DeserializationContext context) throws IOException {
        String value = jsonParser.getText();
        if (!"".equals(value)) {
            DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                    .appendPattern("yyyy-MM-dd'T'HH:mm:ss.SSSZ")
                    .toFormatter()
                    .withZone(ZoneOffset.UTC);
            return OffsetDateTime.parse(value, formatter);
        }
        return null;
    }

}



