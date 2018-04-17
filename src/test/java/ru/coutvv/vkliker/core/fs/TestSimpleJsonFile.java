package ru.coutvv.vkliker.core.fs;

import com.google.gson.JsonParser;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.testng.Assert.assertEquals;

/**
 * @author coutvv    14.04.2018
 */
public class TestSimpleJsonFile {

    final String TEMPORARY_FILENAME = "test/temporary.json";
    final Path FILE_PATH = Paths.get(TEMPORARY_FILENAME);

    final String TEST_JSON_TEXT = "{\"test\":\"test\"}";


    @Test
    public void checkWriting() throws IOException {
        new SimpleJsonFile(TEMPORARY_FILENAME).write(
                new JsonParser().parse(TEST_JSON_TEXT)
        );
        assertEquals(
                new String(Files.readAllBytes(FILE_PATH)),
                TEST_JSON_TEXT
        );

    }

    @Test
    public void checkReading() throws Exception {
        Files.newOutputStream(FILE_PATH).write(TEST_JSON_TEXT.getBytes());

        assertEquals(
                new SimpleJsonFile(TEMPORARY_FILENAME).content().toString(),
                TEST_JSON_TEXT
        );
    }

    @AfterMethod
    public void removeFile() throws IOException {
        Files.delete(FILE_PATH);
    }
}
