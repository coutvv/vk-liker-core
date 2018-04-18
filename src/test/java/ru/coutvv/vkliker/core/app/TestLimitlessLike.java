package ru.coutvv.vkliker.core.app;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.coutvv.vkliker.core.api.support.Delay;
import ru.coutvv.vkliker.core.support.VKUserTestData;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * @author coutvv    18.04.2018
 */
public class TestLimitlessLike {

    VKUserTestData userData;

    @BeforeClass
    public void setup() throws Exception {
        userData = new VKUserTestData();
    }

    @Test
    public void testApp() throws Exception {
        App app = new LimitlessLike(userData.vkScriptExecutor());
        Switch control = app.control();
        Executor core = Executors.newSingleThreadExecutor();
        core.execute(() -> {
            try {
                app.run();
            } catch (Exception e) {
                // once recovery place
            }
        });
        new Delay(10_000).apply(); // wait a little and turn app off
        control.off();
    }
}
