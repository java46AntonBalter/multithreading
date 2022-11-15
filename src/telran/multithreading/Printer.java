package telran.multithreading;

public class Printer extends Thread {
	private String symbol;
	private int reps;
	private int repetitionTimes;
	private Printer nextPrinter;
	
	public Printer(int symbol, int nSymbols, int increment) {
		this.symbol = "" + symbol;
		this.reps = nSymbols/increment;
		this.repetitionTimes = increment;
	}

	public void setNextPrinter(Printer nextPrinter) {
		this.nextPrinter = nextPrinter;
	}

	@Override
	public void run() {
		while(repetitionTimes > 0) {
			try {
				sleep(1000);
			} catch (InterruptedException e) {
				System.out.println(symbol.repeat(reps));
				nextPrinter.interrupt();
				--repetitionTimes;
			}
		}
	}
}
