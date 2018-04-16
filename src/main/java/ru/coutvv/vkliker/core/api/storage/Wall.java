package ru.coutvv.vkliker.core.api.storage;

import ru.coutvv.vkliker.core.api.support.raw.Json;

import java.util.List;

/**
 * @author coutvv    15.04.2018
 */
public interface Wall {

    List<Json> lastHundredPosts(Integer id) throws Exception;

}

