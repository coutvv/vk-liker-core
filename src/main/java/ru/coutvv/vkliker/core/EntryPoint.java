package ru.coutvv.vkliker.core;

import org.cactoos.io.InputStreamOf;
import org.cactoos.scalar.PropertiesOf;
import org.cactoos.text.TextOf;
import ru.coutvv.vkliker.core.api.support.Delay;
import ru.coutvv.vkliker.core.app.LimitlessLike;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author coutvv
 */
public class EntryPoint {
    public static void main(String[] args) throws Exception {

        final Properties props = new PropertiesOf(
                new TextOf(
                        new InputStreamOf(
                                new File("app.properties")
                        )
                )
        ).value();

        App app = new LimitlessLike(props);
        Switcher control = app.control();
        ExecutorService core = Executors.newSingleThreadExecutor();

        core.execute(() -> {
            try {
                app.run();
            } catch (Exception e) {
                // once recovery place
            }
        });

        new Delay(10_000).apply(); // wait a little and turn app off
        System.out.println("Press enter to quit: ");

        String fromConsole = new BufferedReader(new InputStreamReader(System.in)).readLine();
        System.out.println("Entered: " + fromConsole);

        control.off();
        core.shutdown();

        System.exit(0);
    }
}
