package telran.multithreading;

public class Printer extends Thread {
	private static final long TIMEOUT = 200000;
	private String symbol;
	private int reps;
	private int repetitionTimes;
	private Printer nextPrinter;
	
	public Printer(int symbol, int reps, int increment) {
		this.symbol = "" + symbol;
		this.reps = reps/increment;
		this.repetitionTimes = increment;
	}

	public void setNextPrinter(Printer nextPrinter) {
		this.nextPrinter = nextPrinter;
	}

	@Override
	public void run() {
		while(repetitionTimes > 0) {
			try {
				sleep(TIMEOUT);
			} catch (InterruptedException e) {
				System.out.println(symbol.repeat(reps));
				nextPrinter.interrupt();
				--repetitionTimes;
			}
		}
	}
}