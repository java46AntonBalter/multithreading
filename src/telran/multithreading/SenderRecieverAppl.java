package telran.multithreading;

import java.util.stream.IntStream;

import telran.multithreading.consumer.Reciever;
import telran.multithreading.producer.Sender;

public class SenderRecieverAppl {
	
	private static final int N_RECEIVERS = 10;
	private static final int N_MESSAGES = 20;

	public static void main(String[] args) throws InterruptedException {
		Reciever receivers[] = new Reciever[N_RECEIVERS];
		MessageBox messageBox = new MessageBox();
		startReceivers(receivers, messageBox);
		Sender sender = new Sender(messageBox, N_MESSAGES);
		sender.start();
		sender.join();

	}

	private static void startReceivers(Reciever[] receivers, MessageBox messageBox) {
		IntStream.range(0, N_RECEIVERS).forEach(i -> {
			receivers[i] = new Reciever(messageBox);
			receivers[i].start();					
		});
	}

}
