package ru.coutvv.vkliker.core.api.storage;

import ru.coutvv.vkliker.core.api.entity.user.User;

import java.util.List;

public interface UserStorage {

    List<User> users() throws Exception;
}
