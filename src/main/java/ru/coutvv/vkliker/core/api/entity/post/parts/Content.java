package ru.coutvv.vkliker.core.api.entity.post.parts;

import ru.coutvv.vkliker.core.api.support.raw.Json;
import ru.coutvv.vkliker.core.api.entity.post.Post;
import ru.coutvv.vkliker.core.api.entity.post.impl.Repost;
import ru.coutvv.vkliker.core.api.entity.post.parts.content.Attachment;

import java.util.ArrayList;
import java.util.List;

/**
 * @author coutvv    15.04.2018
 */
public class Content {
	private String text;


	private List<Attachment> attachments = new ArrayList<>();
	private List<Post> repost = new ArrayList<>();

	public Content(Json json) {
		this(
				json.stringField("text"),
				createAttachments(json),
				createRepost(json)
		);
	}

	private static List<Attachment> createAttachments(Json json) {
		List<Attachment> result = new ArrayList<>();
		json.arrField("attachments").stream()
				.map(jsonItem -> new Attachment(jsonItem))
				.iterator()
				.forEachRemaining(attachment -> result.add(attachment));
		return result;
	}

	private static List<Post> createRepost(Json json) {
		List<Post> result = new ArrayList<>();
		json.arrField("copy_history").stream()
				.map(jsonItem -> new Repost(jsonItem))
				.iterator()
				.forEachRemaining(post -> result.add(post));
		return result;
	}

	public Content(String text, List<Attachment> attachments, List<Post> repost) {
		this.text = text;
		this.attachments = attachments;
		this.repost = repost;
	}


	@Override
	public String toString() {
		return "Content{" +
				"text='" + text + '\'' +
				", attachments=" + attachments.size() +
				", repost=" + repost.size() +
				'}';
	}
}
