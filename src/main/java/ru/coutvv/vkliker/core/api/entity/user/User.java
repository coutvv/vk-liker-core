package ru.coutvv.vkliker.core.api.entity.user;

import ru.coutvv.vkliker.core.api.entity.user.parts.Identity;
import ru.coutvv.vkliker.core.api.storage.user.FriendList;
import ru.coutvv.vkliker.core.api.support.ScriptExecutor;
import ru.coutvv.vkliker.core.api.support.raw.Json;

import java.util.List;

/**
 * @author coutvv    02.07.2018
 */
public class User {
    final Identity identity;

    public User(Json json) {
        this(new Identity(json));
    }

    public User(Identity identity) {
        this.identity = identity;
    }

    @Override
    public String toString() {
        return identity.toString();
    }

    public List<User> vkFriends(ScriptExecutor executorService) throws Exception {
        return new FriendList(executorService, (int)identity.getId()).users();
    }

    public long id() {
        return identity.getId();
    }
}
