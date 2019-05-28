package ru.coutvv.vkliker.core.api.support.raw;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.List;

/**
 * @author coutvv    16.04.2018
 */
public class GsonJson implements Json {

	private final JsonObject jsonObject;

	public GsonJson(String json) {
		this(json, new JsonParser());
	}

	public GsonJson(String json, JsonParser parser) {
		this(parser.parse(json).getAsJsonObject());

	}

	public GsonJson(JsonObject jsonObject) {
		this.jsonObject = jsonObject;
	}

	@Override
	public Json objField(String fieldName) {
		return new GsonJson(jsonObject.get(fieldName).toString());
	}

	@Override
	public List<Json> arrField(String fieldName) {
		List<Json> result = new ArrayList<>();
		JsonElement array = jsonObject.get(fieldName);
		if (array != null) {
			array.getAsJsonArray().iterator().forEachRemaining(jsonElement -> result.add(new GsonJson(jsonElement.toString())));
		}
		return result;
	}

	@Override
	public String stringField(String fieldName) {
		JsonElement result = jsonObject.get(fieldName);
		return result == null ? "" : result.getAsString();
	}

	@Override
	public Long longField(String fieldName) {
		return jsonObject.get(fieldName).getAsLong();
	}

	@Override
	public String toString() {
		return jsonObject.toString();
	}
}
