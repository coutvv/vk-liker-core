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
 * <p>
 * TODO: rewrite with using Cactoos
 *
 * @author coutvv
 */
public class SimpleJsonFile implements JsonFile {

	private final String filename;

	public SimpleJsonFile(String filename) {
		this.filename = filename;
	}

	@Override
	public void write(JsonElement json) throws IOException {
		File file = new File(filename);
		if (!file.exists())
			Files.createDirectories(Paths.get(file.getParent()));

		try (FileWriter writer = new FileWriter(file)) {
			writer.write(json.toString());
			writer.flush();
		}
	}

	@Override
	public JsonElement content() throws IOException {
		File file = new File(filename);
		try (FileReader reader = new FileReader(file)) {
			try (JsonReader jsonReader = new JsonReader(reader)) {
				return new Gson().fromJson(jsonReader, JsonElement.class);
			}
		}
	}

}
