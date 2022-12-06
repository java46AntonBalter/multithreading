package telran.multithreading.numbers;

import java.util.Arrays;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;

public class NumberGroups {
	private int[][] groups;
	private int nThreads = 4;
	ExecutorService executor = Executors.newFixedThreadPool(nThreads);
	private static AtomicLong sum = new AtomicLong();

	public NumberGroups(int[][] groups) {
		this.groups = groups;
	}

	public int getnThreads() {
		return nThreads;
	}

	public void setnThreads(int nThreads) {
		this.nThreads = nThreads;
	}

	public long computeSum() throws InterruptedException {
		Arrays.stream(groups).parallel().forEach(g -> {
			OneGroupSum worker = new OneGroupSum(g);
			executor.execute(worker);
			try {
				executor.awaitTermination(1, TimeUnit.MILLISECONDS);
			} catch (InterruptedException e) {
			}
			sum.addAndGet(worker.getRes());
		});
		executor.shutdown();
		return sum.get();
	}

}
