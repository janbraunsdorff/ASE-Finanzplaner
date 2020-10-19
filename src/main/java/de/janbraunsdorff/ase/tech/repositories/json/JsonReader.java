package de.janbraunsdorff.ase.tech.repositories.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import de.janbraunsdorff.ase.tech.repositories.BankEntity;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

public class JsonReader {
    List<BankEntity> readBanks(String path){
        try {
            String json =  Files.readAllLines(Paths.get(path)).stream().reduce(" ", String::concat);
            Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").create();
            Type type = new TypeToken<List<BankEntity>>(){}.getType();
            return gson.fromJson(json, type);

        } catch (IOException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }


    }
}
