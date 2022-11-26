package telran.multithreading;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Truck extends Thread {
	private int load;
	private int nLoads;
	private static long elevator1;
	private static long elevator2;
	public final static Lock lock = new ReentrantLock(true);
	public final static Lock lock2 = new ReentrantLock(true);
	private static AtomicInteger waitingCounter = new AtomicInteger(0);

	public Truck(int load, int nLoads) {
		this.load = load;
		this.nLoads = nLoads;
	}

	@Override
	public void run() {
		for (int i = 0; i < nLoads; i++) {
			while (!lock.tryLock()) {
				waitingCounter.incrementAndGet();
			}
			try {
				loadElevator1(load);
				//loadElevator2(load);
			} finally {
				lock.unlock();
			}

			while (!lock2.tryLock()) {
				waitingCounter.incrementAndGet();
			}
			try {
				loadElevator2(load);
			} finally {
				lock2.unlock();
			}
		}
	}

	public static int getWaitingCounter() {
		return waitingCounter.get();
	}

	static private void loadElevator1(int load) {

		elevator1 += load;
	}

	static private void loadElevator2(int load) {

		elevator2 += load;

	}

	public static long getElevator1() {
		return elevator1;
	}

	public static long getElevator2() {
		return elevator2;
	}
}
