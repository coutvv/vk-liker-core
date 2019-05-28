package ru.coutvv.vkliker.core.fs;

import com.google.gson.JsonElement;

import java.io.IOException;

/**
 *
 */
public interface JsonFile {
	void write(JsonElement json) throws IOException;

	JsonElement content() throws IOException;
}
