package ru.coutvv.vkliker.core.api.storage.post;

import ru.coutvv.vkliker.core.api.entity.post.Post;
import ru.coutvv.vkliker.core.api.entity.post.impl.NewsPost;
import ru.coutvv.vkliker.core.api.storage.PostSource;
import ru.coutvv.vkliker.core.api.support.ScriptExecutor;
import ru.coutvv.vkliker.core.api.support.raw.Json;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author coutvv    16.04.2018
 */
public class VkFeed implements PostSource {

	private final ScriptExecutor executor;
	private final Map<Integer, String> nextKeys = new HashMap<>();

	public VkFeed(ScriptExecutor executor) {
		this.executor = executor;
	}

	private static final String script = "return API.newsfeed.get({\"filters\"  : \"post\", \"count\" : 100, });";

	@Override
	public List<Post> posts(int count, int offset) throws Exception {
		return rawPosts(count, offset).stream().map(NewsPost::new).collect(Collectors.toList());
	}

	@Override
	public List<Json> rawPosts(int count, int offset) throws Exception {
		Json response = executor.raw(script); //TODO: use count and offset mechanism here
		return response.arrField("items");
	}

	@Override
	public int quantityPosts() {
		throw new IllegalArgumentException("not implemented");
	}
}
