package de.janbraunsdorff.ase.tech.repositories.json;

import com.google.gson.Gson;
import de.janbraunsdorff.ase.tech.repositories.BankEntity;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class JsonWriter {
    JsonReader reader;

    public JsonWriter(JsonReader reader) {
        this.reader = reader;
    }

    public void write(BankEntity bankEntity, String path) throws IOException {
        File f = new File(path);
        if (!f.exists()) {
            f.createNewFile();
        }

        if (bankEntity.getId() == null || bankEntity.getId().isEmpty()) {
            bankEntity.setId(UUID.randomUUID().toString());
        }

        List<BankEntity> obj = Collections.singletonList(bankEntity);
        Gson gson = new Gson();
        Files.write(Paths.get(path), gson.toJson(obj).getBytes());
    }
}
