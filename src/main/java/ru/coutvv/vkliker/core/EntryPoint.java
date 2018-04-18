package ru.coutvv.vkliker.core;

import ru.coutvv.vkliker.core.api.support.Delay;
import ru.coutvv.vkliker.core.app.App;
import ru.coutvv.vkliker.core.app.LimitlessLike;
import ru.coutvv.vkliker.core.app.Switch;

import java.io.*;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author coutvv
 */
public class EntryPoint {
    public static void main(String[] args) throws Exception {
        final int userId;
        final String token;
        try(InputStream in = new FileInputStream(new File("app.properties"))) {
            Properties props = new Properties();
            props.load(in);
            userId = Integer.parseInt(props.getProperty("userId"));
            token = props.getProperty("token");
        } catch (Exception e) {
            throw new Exception("can't load app.properties", e);
        }

        App app = new LimitlessLike(userId, token);
        Switch control = app.control();
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
    }
}
