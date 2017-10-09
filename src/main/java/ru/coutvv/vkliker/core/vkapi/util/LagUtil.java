package ru.coutvv.vkliker.core.vkapi.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Random;

/**
 * @author coutvv
 */
public interface LagUtil {

    Log log = LogFactory.getLog(LagUtil.class);
    long TIMEOUT = 500;
    Random rand = new Random();

    /**
     * delay 500+-250 ms
     */
    static void lag() {
        long delta = rand.nextInt(250);
        delta = (rand.nextBoolean() ? delta : -delta);//various time
        lag(TIMEOUT + delta);
    }

    static void lagX(int n) {
        for(int i = 0; i < n; i++)
            lag();
    }

    static void lag(long timeout) {
        try { //waiting
            Thread.sleep(timeout);
        } catch (InterruptedException e) {
            log.error("sleep interrupt", e);
        }
    }

}
