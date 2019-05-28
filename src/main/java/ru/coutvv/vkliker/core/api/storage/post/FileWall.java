package ru.coutvv.vkliker.core.api.storage.post;

import ru.coutvv.vkliker.core.api.entity.post.Post;
import ru.coutvv.vkliker.core.api.entity.post.impl.NewsPost;
import ru.coutvv.vkliker.core.api.storage.PostSource;
import ru.coutvv.vkliker.core.api.support.raw.GsonJson;
import ru.coutvv.vkliker.core.api.support.raw.Json;
import ru.coutvv.vkliker.core.fs.JsonFile;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * get post from local file
 *
 * @author coutvv    17.04.2018
 */
public class FileWall implements PostSource {

	private final JsonFile source;

	public FileWall(JsonFile source) {
		this.source = source;
	}

	private final List<Json> all = new ArrayList<>();

	@Override
	public List<Post> posts(int count, int offset) throws Exception {
		return rawPosts(count, offset).stream().map(NewsPost::new).collect(Collectors.toList());
	}

	@Override
	public List<Json> rawPosts(int count, int offset) throws Exception {
		if (all.size() == 0)
			source.content().getAsJsonArray().forEach(
					jsonElement -> all.add(new GsonJson(jsonElement.getAsJsonObject()))
			);
		return all.subList(offset, count);
	}

	@Override
	public int quantityPosts() {
		throw new IllegalArgumentException("not implemented");
	}
}
