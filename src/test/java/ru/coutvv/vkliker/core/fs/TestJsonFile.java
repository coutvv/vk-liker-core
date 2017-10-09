package ru.coutvv.vkliker.core.fs;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

/**
 * @author coutvv
 */
public class TestJsonFile {

    @Test
    public void testCreationFile() throws IOException {
        String filename = "gp/dir1/dir2/fuck.json";

        JsonFile json = new JsonFile(filename);
    }

    @Test
    public void testWriteRead() throws IOException {
        final String source =  "{\"test\":\"test\"}";
        JsonElement jsonElement = new Gson().fromJson(source, JsonElement.class);

        JsonFile file = new JsonFile("gp/test/noway.json");
        file.write(jsonElement);

        JsonElement fromFile = file.read();

        assertEquals(fromFile.toString(), source);
    }

    @Test
    public void testReadExist() throws IOException {
        final String name = "src/test/resources/exist.json";
        final String tasty = "test";

        JsonFile file = new JsonFile(name);

        assertEquals(file.read().getAsJsonObject().get("tasty").getAsString(), tasty);
    }

    @Test
    public void testNonExistsFile() throws IOException {
        final String filename = "gp/asdf/adsf/as/dfa/sdf/asdf/fukc.json";
        JsonFile file = new JsonFile(filename);

        assertTrue(file.isEmpty());
    }

    @Test
    public void testEmptyFile() throws IOException {
        final String filename = "src/test/resources/empty.json";
        JsonFile file = new JsonFile(filename);

        assertTrue(file.isEmpty());
    }
}
