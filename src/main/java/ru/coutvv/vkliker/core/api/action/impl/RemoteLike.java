package ru.coutvv.vkliker.core.api.action.impl;

import ru.coutvv.vkliker.core.api.action.Like;
import ru.coutvv.vkliker.core.api.support.ScriptExecutor;
import ru.coutvv.vkliker.core.api.support.raw.Json;

/**
 * @author coutvv    16.04.2018
 */
public class RemoteLike implements Like {

	private final ScriptExecutor executor;

	public RemoteLike(ScriptExecutor executor) {
		this.executor = executor;
	}

	private final static String ADD = "return API.likes.add(%s);",
			REMOVE = "return API.likes.delete(%s);",
			LIKED = "return API.likes.isLiked(%s);";

	@Override
	public void add(Json objectIdentity) throws Exception {
		executor.raw(String.format(ADD, objectIdentity.toString()));
	}

	@Override
	public void remove(Json objectIdentity) throws Exception {
		executor.raw(String.format(REMOVE, objectIdentity.toString()));
	}

	@Override
	public boolean liked(Json objectIdentity) throws Exception {
		return executor.raw(String.format(LIKED, objectIdentity.toString())).longField("liked") == 1;
	}
}
