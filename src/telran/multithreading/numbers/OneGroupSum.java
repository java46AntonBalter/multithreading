package telran.multithreading.numbers;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicLong;

public class OneGroupSum implements Runnable {
	private int[] group;
	private AtomicLong res = new AtomicLong(0); 
	
	public OneGroupSum(int[] group) {
		this.group = group;
	}
	
	@Override
	public void run() {
		res.addAndGet(Arrays.stream(group).sum());		
	}

	public long getRes() {
		return res.get();
	}

}
