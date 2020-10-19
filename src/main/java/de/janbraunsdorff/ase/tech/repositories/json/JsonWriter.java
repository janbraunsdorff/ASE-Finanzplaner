package de.janbraunsdorff.ase.tech.repositories.json;

import com.google.gson.Gson;
import de.janbraunsdorff.ase.tech.repositories.BankEntity;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

public class JsonWriter {
    private final JsonReader reader;

    public JsonWriter(JsonReader reader) {
        this.reader = reader;
    }

    public void write(BankEntity bankEntity, String path) throws IOException {
        File f = new File(path);
        if (!f.exists()) {
            createNew(bankEntity, path, f);
        }else {
            append(bankEntity, path);
        }
    }



    private void createNew(BankEntity bankEntity, String path, File f) throws IOException {
        f.createNewFile();
        List<BankEntity> obj = Collections.singletonList(bankEntity);
        update(obj, path);
    }

    private void append(BankEntity bankEntity, String path) throws IOException {
        List<BankEntity> bankEntities = reader.readBanks(path);
        bankEntities.add(bankEntity);
        update(bankEntities, path);
    }

    public void update(List<BankEntity> banks, String path) throws IOException {
        Gson gson = new Gson();
        Files.write(Paths.get(path), gson.toJson(banks).getBytes());
    }
}
