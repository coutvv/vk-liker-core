package ru.coutvv.vkliker.core.app;

import org.cactoos.scalar.NumberOf;
import ru.coutvv.vkliker.core.App;
import ru.coutvv.vkliker.core.Switcher;
import ru.coutvv.vkliker.core.api.action.impl.RemoteLike;
import ru.coutvv.vkliker.core.api.entity.post.Post;
import ru.coutvv.vkliker.core.api.entity.user.User;
import ru.coutvv.vkliker.core.api.storage.PostSource;
import ru.coutvv.vkliker.core.api.storage.UserStorage;
import ru.coutvv.vkliker.core.api.storage.post.VkWall;
import ru.coutvv.vkliker.core.api.storage.user.FriendList;
import ru.coutvv.vkliker.core.api.support.Delay;

import java.util.List;
import java.util.Properties;

/**
 * like wall of friends of friends
 *
 * @author coutvv    02.07.2018
 */
public class LikeAround extends App {

	private final int friendId;

	public LikeAround(Properties properties) {
		super(properties);
		friendId = new NumberOf(properties.getProperty("friendId")).intValue();
	}

	@Override
	public void run() throws Exception {
		Delay betweenLike = new Delay(7_000);

		UserStorage us = new FriendList(executor, friendId);
		List<User> users = us.users();

		for (User user : users) {
			for (User fOfUser : user.vkFriends(executor)) {
				PostSource src = new VkWall(executor, (int) fOfUser.id());
				for (Post post : src.posts(100, 0)) {
					new RemoteLike(executor).add(post.likable().objectIdentity());
					System.out.println("Post: " + post.toString() + " has liked!");
					betweenLike.apply();
				}
			}
		}
	}
}
