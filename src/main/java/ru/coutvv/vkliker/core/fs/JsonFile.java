package ru.coutvv.vkliker.core.fs;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.stream.JsonReader;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Class implemented file with alone JSON Object
 * @author coutvv
 */
public class JsonFile {

    private final File file;

    public JsonFile(String filename) throws IOException {
        file = new File(filename);
    }

    public void write(JsonElement json) throws IOException {
        if(!file.exists())
            Files.createDirectories(Paths.get(file.getParent()));
        FileWriter writer = new FileWriter(file);
        try(writer) {
            writer.write(json.toString());
            writer.flush();
        }
    }

    /**
     * @return null if file doesn't exists or empty
     * @throws IOException if can't get access to fs or other io problem
     */
    public JsonElement read() throws IOException {
        JsonElement result = null;
        if(file.exists()) {
            FileReader reader = new FileReader(file);
            try(reader) {
                JsonReader jsonReader = new JsonReader(reader);
                try(jsonReader) {
                    result = new Gson().fromJson(jsonReader, JsonElement.class);
                }
            }
        }
        return result;
    }

    public boolean isEmpty() throws IOException {
        return read() == null;
    }
}
