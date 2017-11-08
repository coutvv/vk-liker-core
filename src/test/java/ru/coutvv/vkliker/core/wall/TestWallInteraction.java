package ru.coutvv.vkliker.core.wall;

import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import org.apache.commons.io.FileUtils;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.coutvv.vkliker.core.VKUserTestData;
import ru.coutvv.vkliker.core.vkapi.json.VkRequest;
import ru.coutvv.vkliker.core.vkapi.posts.Wall;
import ru.coutvv.vkliker.core.vkapi.posts.entity.Post;
import ru.coutvv.vkliker.core.vkapi.posts.impl.CacheWallImpl;
import ru.coutvv.vkliker.core.vkapi.posts.impl.InMemoryWall;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;

import static org.testng.Assert.assertEquals;

public class TestWallInteraction {


    private final VKUserTestData testData = VKUserTestData.getInstance();
    private final VkRequest vk = new VkRequest(testData.actor, testData.vk);
    private final String userId = "1";

    @Test
    public void testInMemoryWall() throws ClientException, ApiException {
        Wall wall = new InMemoryWall(userId, vk);
        testDurovWall(wall);
    }

    private final String EXIST_FILE_TARGET = "test/1.json";

    @BeforeClass
    public void init() throws IOException {
        File testDir = new File("test");
        if(testDir.exists())
            FileUtils.deleteDirectory(testDir);

        final String EXIST_FILE_PATH = "src/test/resources/cache/1.json";
        Path filePath = Paths.get(EXIST_FILE_TARGET);

        Files.createDirectories(filePath.getParent());
        Files.copy(Paths.get(EXIST_FILE_PATH), filePath);
    }

    @Test
    public void testCacheWall() throws ClientException, ApiException, IOException {
        CacheWallImpl.setCacheStoragePath("test/%s.json");
        Wall cacheWall = new CacheWallImpl(userId, vk);
        testDurovWall(cacheWall);
        CacheWallImpl.setCacheStoragePath("test/cache/%s.json");
        Wall nonCachedWall = new CacheWallImpl(userId, vk);
        testDurovWall(nonCachedWall);
    }

    private void testDurovWall(Wall wall) throws ClientException, ApiException {
        wall.getAll();
        List<Post> last20 = wall.getPosts(20);
        assertEquals(last20.size(), 20);
        wall.refresh();
        wall.getPostsSince(LocalDate.of(2015, 1, 30));
        LocalDate start = LocalDate.of(2010, 1, 1),
                end = LocalDate.of(2011, 1,1);
        int count2010 = wall.getPostsBetween(start, end).size();
        System.out.println(count2010);
        assertEquals(count2010, 3);
        wall.getAll();
    }
}
