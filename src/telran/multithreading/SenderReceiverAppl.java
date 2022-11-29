package telran.multithreading;

import java.util.stream.IntStream;

import telran.multithreading.consumer.Receiver;
import telran.multithreading.producer.Sender;

public class SenderReceiverAppl {

	private static final int N_RECEIVERS = 10;
	private static final int N_MESSAGES = 15000;

	public static void main(String[] args) throws InterruptedException {
		Receiver receivers[] = new Receiver[N_RECEIVERS];
		MessageBox messageBox = new MessageBox();
		startReceivers(receivers, messageBox);

		Sender sender = new Sender(messageBox, N_MESSAGES);
		sender.start();
		sender.join();
		
		if (messageBox.take() == null) {
			IntStream.range(0, N_RECEIVERS).forEach(i -> {
				receivers[i].interrupt();
			});
		};
		System.out.printf("Number of messages processed is: %d", Receiver.getMessagesCounter());

	}

	private static void startReceivers(Receiver[] receivers, MessageBox messageBox) {
		IntStream.range(0, N_RECEIVERS).forEach(i -> {
			receivers[i] = new Receiver(messageBox);
			receivers[i].start();
		});

	}

}