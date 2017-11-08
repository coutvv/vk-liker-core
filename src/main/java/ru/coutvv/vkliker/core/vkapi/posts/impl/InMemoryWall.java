package ru.coutvv.vkliker.core.vkapi.posts.impl;

import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import ru.coutvv.vkliker.core.vkapi.json.VkRequest;
import ru.coutvv.vkliker.core.vkapi.posts.AbstractWall;

/**
 * just store wall in RAM
 */
public class InMemoryWall extends AbstractWall {

    public InMemoryWall(String userId, VkRequest vk) throws ClientException, ApiException {
        super(userId, vk);
        fillWholeWall();//init wall
    }

}
