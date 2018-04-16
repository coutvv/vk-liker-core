package ru.coutvv.vkliker.core.api.storage;

import ru.coutvv.vkliker.core.api.support.raw.Json;

import java.util.List;

public interface Newsfeed {

    List<Json> lastRawPosts() throws Exception;

}

