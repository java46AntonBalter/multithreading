package telran.multithreading;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Race extends Thread {
	private int nRacers;
	private int dystance;
	List<Racer> racers = new ArrayList<>();
	List<Integer> positions = new ArrayList<>();
	Collection<Integer> syncPositions = Collections.synchronizedCollection(positions);
	
	
	public Race(int nRacers, int dystance) {
		this.nRacers = nRacers;
		this.dystance = dystance;
	}


	@Override
	public void run() {
		for(int i = 1; i <= nRacers; i++) {
			racers.add(new Racer(dystance, i, syncPositions));			
		}
		for(Racer i: racers) {
			i.start();
		}
		for(Racer i: racers) {
			try {
				i.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.printf("AAaaaaand the winner is %d", positions.get(0));
	}
}
