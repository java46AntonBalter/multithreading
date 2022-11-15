package telran.multithreading;

import java.util.stream.IntStream;

public class SynchronizedPrinting {
	private static final int N_PRINTERS = 4;
	private static final int N_NUMBERS = 100;
	private static final int N_PORTIONS = 10;
	static Printer[] printers = new Printer[N_PRINTERS];

	public static void main(String[] args) {
		getArrayOfPrinters();

		IntStream.range(0, N_PRINTERS).forEach(i -> {
			if (i < N_PRINTERS - 1) {
				printers[i].setNextPrinter(printers[i + 1]);
			} else {
				printers[i].setNextPrinter(printers[0]);
			}
			printers[i].start();
		});

		printers[0].interrupt();
	}

	private static void getArrayOfPrinters() {
		IntStream.range(0, N_PRINTERS).forEach(i -> printers[i] = new Printer(i + 1, N_NUMBERS, N_PORTIONS));
	}

}
