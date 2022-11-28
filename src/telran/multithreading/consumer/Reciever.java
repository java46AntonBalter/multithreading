package telran.multithreading.consumer;

import telran.multithreading.MessageBox;

public class Reciever extends Thread {
	private MessageBox messageBox;
	
	public Reciever(MessageBox messageBox) {
		//FIXME - thread should not be a Daemon thread
		setDaemon(true);
		this.messageBox = messageBox;
	}

	@Override
	public void run() {
		while (true) {
			try {
				String message = messageBox.get();
				System.out.println(message + getName());
			} catch (InterruptedException e) {
			}
		}
	}

	
}
