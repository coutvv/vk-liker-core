package ru.coutvv.vkliker.core.api.storage.user;

import ru.coutvv.vkliker.core.api.entity.user.User;
import ru.coutvv.vkliker.core.api.storage.UserStorage;
import ru.coutvv.vkliker.core.api.support.ScriptExecutor;
import ru.coutvv.vkliker.core.api.support.raw.Json;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author coutvv    02.07.2018
 */
public class FriendList implements UserStorage {

    public final ScriptExecutor executor;
    public final int ownerId;

    public FriendList(ScriptExecutor executor, int ownerId) {
        this.executor = executor;
        this.ownerId = ownerId;
    }

    private static final String SCRIPT_TEMPLATE = "return API.friends.get({\"user_id\"  : %1$s, \"order\" : \"name\" , \"offset\" : %2$d, \"fields\":\"nickname,domain,sex,bdate,city,country,timezone,photo_50,photo_100,photo_200_orig,has_mobile,contacts,education,online,relation,last_seen,status,can_write_private_message,can_see_all_posts,can_post,universities\"});";

    @Override
    public List<User> users() throws Exception {
        Json json = executor.raw(String.format(SCRIPT_TEMPLATE, ownerId, 100));
        return json.arrField("items").stream().map(raw -> new User(raw)).collect(Collectors.toList());
    }
}
