package telran.multithreading.executers;

import java.util.concurrent.*;

public class ServerImitator extends Thread {
	private static final int N_THREADS = 1000;
	BlockingQueue<Request> queue;
	public ExecutorService executor = Executors.newFixedThreadPool(N_THREADS);

	public ServerImitator(BlockingQueue<Request> queue) {
		this.queue = queue;
	}
	
	@Override
	public void run() {
		
		while(true) {
			try {
				Request request = queue.take();
				executor.execute(request);
			} catch (InterruptedException e) {
				processRequests(executor);
				break;
			}
		}
	}

	private void processRequests(ExecutorService executor) {
		Request request = null;
		while((request = queue.poll()) != null) {
			executor.execute(request);
		}
	}

}
