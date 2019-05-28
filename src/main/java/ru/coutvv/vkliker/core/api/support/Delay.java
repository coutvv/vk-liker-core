package ru.coutvv.vkliker.core.api.support;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author coutvv    17.04.2018
 */
public class Delay {

	private final int ms;

	private final AtomicInteger delayTime = new AtomicInteger(0);


	public Delay(int approximateMillis) {
		this.ms = approximateMillis / 2;
	}

	public void apply() throws InterruptedException {
		int thisDelay = new Random().nextInt(ms) + ms;

		Thread.sleep(thisDelay);
		delayTime.addAndGet(thisDelay);
	}

	/**
	 * whole time that was delayed
	 *
	 * @return
	 */
	public Integer delayMillis() {
		return delayTime.intValue();
	}
}
