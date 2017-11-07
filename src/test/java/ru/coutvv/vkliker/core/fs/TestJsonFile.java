package ru.coutvv.vkliker.core.fs;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import org.apache.commons.io.FileUtils;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

/**
 * @author coutvv
 */
public class TestJsonFile {

    private final String EXIST_FILE_TARGET = "test/exist.json";

    @BeforeClass
    public void init() throws IOException {
        File testDir = new File("test");
        if(testDir.exists())
            FileUtils.deleteDirectory(testDir);

        final String EXIST_FILE_PATH = "src/test/resources/exist.json";
        Path filePath = Paths.get(EXIST_FILE_TARGET);

        Files.createDirectories(filePath.getParent());
        Files.copy(Paths.get(EXIST_FILE_PATH), filePath);
    }

    @Test
    public void testCreateInstance() throws IOException {
        final String NON_EXIST_FILE = "test/nonexist/file.json";

        JsonFile jsonFile = new JsonFile(NON_EXIST_FILE);
        //check for non create file
        assertTrue(Files.notExists(Paths.get(NON_EXIST_FILE)));
        assertTrue(jsonFile.isEmpty());

        final String JSON_SRC =  "{\"test\":\"test\"}";
        final JsonElement jsonElement = new Gson().fromJson(JSON_SRC, JsonElement.class);
        jsonFile.write(jsonElement);
        assertEquals(jsonFile.read().toString(), JSON_SRC);
    }


    @Test
    public void testExistJsonFile() throws IOException {
        final String TASTY_VALUE = "test";

        JsonFile file = new JsonFile(EXIST_FILE_TARGET);
        assertEquals(getStringProperty("tasty", file), TASTY_VALUE);

        final String ADD_PROP_NAME  = "addition_property";
        JsonElement json = file.read();
        json.getAsJsonObject().addProperty(ADD_PROP_NAME, "addition_value");
        file.write(json);
        assertEquals(getStringProperty(ADD_PROP_NAME, file), "addition_value");
    }

    private String getStringProperty(final String propertyName, final JsonFile jsonFile) throws IOException {
        JsonElement json = jsonFile.read();
        return json.getAsJsonObject().get(propertyName).getAsString();
    }

    @AfterClass
    public void endTest() throws IOException {
        File testDir = new File("test");
        if(testDir.exists())
            FileUtils.deleteDirectory(testDir);
    }
}
