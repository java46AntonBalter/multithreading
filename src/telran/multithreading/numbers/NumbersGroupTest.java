package telran.multithreading.numbers;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

public class NumbersGroupTest {

	public static void main(String[] args) throws InterruptedException {
		functionalTest();
		performanceTest();
	}

	private static void functionalTest() throws InterruptedException {
		int[] arr = {2,2,2};
		OneGroupSum worker = new OneGroupSum(arr);
		Thread t = new Thread(worker);
		t.start();
		t.join();
		System.out.println(worker.getRes());
		
		int[][] arr2 = {{2,2,2},{2,2,2},{2,2,2},{2,2,2}};
		System.out.println(new NumberGroups(arr2).computeSum());
		
	}
	
	private static void performanceTest() throws InterruptedException {
		NumberGroups groups = new NumberGroups(getGroups());
		Instant start = Instant.now();
		groups.setnThreads(10000);
		System.out.println(groups.computeSum());
		System.out.printf("\n time computing - %d", Duration.between(start, Instant.now()).toMillis());
	}

	private static int[][] getGroups() {
		int nGroups = ThreadLocalRandom.current().nextInt(1000, 2000);
		int[][] arr = new int [nGroups][nGroups]; 
		IntStream.range(0, nGroups).forEach(i -> {
			arr[i] = new int[nGroups]; 
			IntStream.range(0, nGroups).forEach(j ->{
				arr[i][j] = ThreadLocalRandom.current().nextInt(Integer.MIN_VALUE, Integer.MAX_VALUE);
			});
		});
		return arr;
		
	}

}
