package ru.coutvv.vkliker.core.app.backup;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.cactoos.scalar.NumberOf;
import ru.coutvv.vkliker.core.App;
import ru.coutvv.vkliker.core.api.storage.PostSource;
import ru.coutvv.vkliker.core.api.storage.post.VkWall;
import ru.coutvv.vkliker.core.api.support.Delay;
import ru.coutvv.vkliker.core.api.support.backup.BackupContent;
import ru.coutvv.vkliker.core.api.support.raw.Json;
import ru.coutvv.vkliker.core.fs.SimpleJsonFile;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

/**
 * save all contents from the Wall
 *
 * @author coutvv
 */
public class WallBackup extends App {

	private static final String NOT_FOUND_URLS_FILE = "404_urls.txt";

	private final int wallOwnerId;
	private final String backupPath;

	public WallBackup(Properties properties) {
		super(properties);
		wallOwnerId = new NumberOf(properties.getProperty("wallOwnerId")).intValue();
		backupPath = properties.getProperty("backupPath") + wallOwnerId + "/";
	}

	@Override
	public void run() throws Exception {
		JsonArray root = getPosts(backupPath + "wall.json");

		Set<String> notFoundUrls = load404Links(backupPath + NOT_FOUND_URLS_FILE);
		BackupContent backupContent = new BackupContent(backupPath, notFoundUrls, NOT_FOUND_URLS_FILE);
		for (int i = 0; i < root.size(); i++) {
			JsonObject post = root.get(i).getAsJsonObject();
			backupContent.recursiveBackup(post);
		}
		System.out.println("Backup wall of " + wallOwnerId + " is done!");
	}

	private JsonArray getPosts(String filePath) throws Exception {
		JsonArray result;
		if (new File(filePath).exists()) {
			result = new SimpleJsonFile(filePath).content().getAsJsonArray();
		} else {
			List<Json> wholeWall = allPosts();
			result = new JsonArray();
			JsonParser parser = new JsonParser();
			for (int i = wholeWall.size() - 1; i >= 0; i--) {
				JsonElement post = parser.parse(wholeWall.get(i).toString());
				result.add(post);
			}
			new SimpleJsonFile(filePath)
					.write(result);
		}
		return result;
	}

	private Set<String> load404Links(String filePath) throws IOException {
		File file = new File(filePath);
		return file.exists()
				? new HashSet<>(Files.readAllLines(Paths.get(filePath), Charset.defaultCharset()))
				: new HashSet<>();
	}

	private List<Json> allPosts() throws Exception {
		PostSource src = new VkWall(executor, wallOwnerId);
		final int postCount = src.quantityPosts();
		List<Json> result = new ArrayList<>(postCount);
		Delay fiveDelay = new Delay(5_000);
		for (int offset = 0; offset < postCount; offset += 100) {
			result.addAll(src.rawPosts(100, offset));
			fiveDelay.apply();
		}
		return result;
	}
}
