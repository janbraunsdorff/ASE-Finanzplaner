package de.janbraunsdorff.ase.tech.repositories.json;

import de.janbraunsdorff.ase.tech.repositories.BankEntity;

import java.io.File;
import java.io.IOException;

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
    }
}
