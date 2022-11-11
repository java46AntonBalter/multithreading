package telran.multithreading;

import java.util.Collection;
import java.util.SplittableRandom;

public class Racer extends Thread {
	
	private int dystance;
	private int symb;
	Collection<Integer> syncPositions;
	
	
	
	public Racer(int dystance, int symb, Collection<Integer> syncPositions) {
		this.dystance = dystance;
		this.symb = symb;
		this.syncPositions = syncPositions;
	}


	@Override
	public void run() {
		for(int i = 0; i < dystance; i++) {
			try {
				sleep(new SplittableRandom().nextInt(2, 6));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(symb);
		}
		syncPositions.add(symb);
	}

}
