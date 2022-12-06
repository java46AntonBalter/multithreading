package telran.multithreading.executers;

import java.util.*;
import java.util.concurrent.*;

public class ServerClientControllerAppl {

	private static final int N_REQUESTS = 1000000;

	public static void main(String[] args) throws InterruptedException {
		BlockingQueue<Request> queue = new LinkedBlockingQueue<>() ;
		ClientImitator client = new ClientImitator(N_REQUESTS, queue);
		client.start();
		ServerImitator server = new ServerImitator(queue);
		server.start();
		client.join();
		server.interrupt();
		server.executor.awaitTermination(5, TimeUnit.SECONDS);
		List<Runnable> nonprocessedRequests = server.executor.shutdownNow();
		System.out.println("counter of the processed requests is " + Request.getRequestCounter());
		System.out.println("counter of the unprocessed requests is " + nonprocessedRequests.size());

	}

}
