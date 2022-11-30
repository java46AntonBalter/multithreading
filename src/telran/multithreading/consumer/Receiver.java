package telran.multithreading.consumer;

import java.util.concurrent.atomic.AtomicInteger;

import telran.multithreading.MessageBox;

public class Receiver extends Thread {
	private MessageBox messageBox;
	private static AtomicInteger messagesCounter = new AtomicInteger(0);

	public Receiver(MessageBox messageBox) {
		this.messageBox = messageBox;

	}

	public static int getMessagesCounter() {
		return messagesCounter.get();
	}

	@Override
	public void run() {
		while (true) {
			try {
				String message = messageBox.get();
				System.out.println(message + getName());
				messagesCounter.incrementAndGet();
			} catch (InterruptedException e) {
				String message = messageBox.take();
				if (message != null) {
					System.out.println(message + getName());
					messagesCounter.incrementAndGet();
				}
				break;
			}
		}
	}

}