package telran.multithreading;

import java.util.concurrent.TimeUnit;

public class RaceGameAppl {

	public static void main(String[] args) {
		System.out.println("Ladies aaaaand Gentlemen!!! \nWelcome to the one and only Java Grand Prix!!!!");
		int[] params = enterNumbers(new ConsoleInputOutput());
		Race race1 = new Race(params[0], params[1]);
		System.out.println("OKAY, we are ready to begin. \n Racers, ready, set");
		try {
			TimeUnit.SECONDS.sleep(3);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("GOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO!!!!");
		race1.start();

	}
	
	private static int[] enterNumbers(InputOutput io) {
		int res[] = new int[2];
		res[0] = io.readInt("enter number of racers from 3 to 10", "no number", 3, 10);
		res[1] = io.readInt("enter dystance of the race from 100 to 3500", "no number", 100, 3500);
		return res;
	}

}
