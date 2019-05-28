package ru.coutvv.vkliker.core.api.entity.post;

import ru.coutvv.vkliker.core.api.entity.Likable;
import ru.coutvv.vkliker.core.api.entity.LikableObject;
import ru.coutvv.vkliker.core.api.entity.post.parts.Content;
import ru.coutvv.vkliker.core.api.entity.post.parts.LikeInfo;
import ru.coutvv.vkliker.core.api.entity.post.parts.Meta;
import ru.coutvv.vkliker.core.api.support.raw.Json;

/**
 * @author coutvv    14.04.2018
 */
public abstract class Post implements Likable {
	private Meta meta;
	private Content content;
	private LikeInfo likeInfo;

	public Post(Json json) {
		this(new Meta(json),
				new Content(json),
				new LikeInfo(json.objField("likes")));
	}

	public Post(Json json, LikeInfo likeInfo) {

		this(new Meta(json),
				new Content(json),
				likeInfo);
	}

	public Post(Meta meta, Content content, LikeInfo likeInfo) {
		this.meta = meta;
		this.content = content;
		this.likeInfo = likeInfo;
	}

	@Override
	public LikableObject likable() {
		return meta.likableMetadata("post", "");
	}

	@Override
	public String toString() {
		return "Post{" +
				"meta=" + meta +
				"\n, content=" + content +
				"\n, likeInfo=" + likeInfo +
				'}';
	}

	@Override
	public boolean liked() {
		return likeInfo.liked();
	}
}



