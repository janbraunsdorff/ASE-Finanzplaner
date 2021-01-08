package de.janbraunsdorff.ase.layer.persistence.json;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LocalDateSerializer implements JsonDeserializer<LocalDate>, JsonSerializer<LocalDate> {
    @Override
    public LocalDate deserialize(JsonElement json, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        int year = json.getAsJsonObject().get("year").getAsInt();
        int month = json.getAsJsonObject().get("month").getAsInt();
        int day = json.getAsJsonObject().get("day").getAsInt();
        return LocalDate.of(year, month, day);
    }

    @Override
    public JsonElement serialize(LocalDate src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject json = new JsonObject();
        json.addProperty("year", src.getYear());
        json.addProperty("day", src.getDayOfMonth());
        json.addProperty("month", src.getMonthValue());

        return json;
    }
}
