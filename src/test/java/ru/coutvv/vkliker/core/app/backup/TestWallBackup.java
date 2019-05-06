package ru.coutvv.vkliker.core.app.backup;

import org.junit.Test;
import ru.coutvv.vkliker.core.App;
import ru.coutvv.vkliker.core.api.support.Delay;
import ru.coutvv.vkliker.core.app.TestApp;

import java.io.IOException;
import java.util.concurrent.Executors;

public class TestWallBackup extends TestApp {
    @Test
    public void simpleTest() throws IOException, InterruptedException {
        App app = new WallBackup(testProps());

        Executors.newSingleThreadExecutor().execute(() -> {
            try {
                app.run();
            } catch (Exception e) {
                // once recovery place
            }
        });

        new Delay(100_000).apply(); // wait a little and turn app off
        app.control().off();
    }

}
