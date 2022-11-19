package telran.multithreading.games;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.stream.IntStream;

import telran.view.*;

public class RaceAppl {

	private static final int MAX_THREADS = 20;
	private static final int MIN_DISTANCE = 10;
	private static final int MAX_DISTANCE = 1000;
	private static final int MIN_SLEEP = 2;
	private static final int MAX_SLEEP = 5;

	public static void main(String[] args) {
		InputOutput io = new ConsoleInputOutput();
		Item[] items = getItems();
		Menu menu = new Menu("Race Game", items);
		menu.perform(io);

	}

	private static Item[] getItems() {
		Item[] res = { Item.of("Start new game", RaceAppl::startGame), Item.exit() };
		return res;
	}

	static void startGame(InputOutput io) {
		int nThreads = io.readInt("Enter number of the runners", "Wrong number of the runners", 2, MAX_THREADS);
		int distance = io.readInt("Enter distance", "Wrong Distance", MIN_DISTANCE, MAX_DISTANCE);
		Race race = new Race(distance, MIN_SLEEP, MAX_SLEEP);
		Runner[] runners = new Runner[nThreads];
		Instant start = Instant.now();
		startRunners(runners, race);
		joinRunners(runners);
		displayResultsTable(race, start);
	}

	private static void displayResultsTable(Race race, Instant start) {
		ArrayList<RaceStats> results = race.getResultsTable();
		String header = "place    racer number    time";
		System.out.println("*".repeat(header.length()));
		System.out.println(header);
		System.out.println("*".repeat(header.length()));
		IntStream.range(1, (results.size() + 1))
				.forEach(i -> {
					int racerNumber = results.get(i - 1).getRacerNumber();
					System.out.print(i);
					int repeatSpace = i < 10 ? 13 : 12;
					System.out.print(" ".repeat(repeatSpace) + racerNumber);
					repeatSpace = racerNumber < 10 ? 9 : 8;
					System.out.print(" ".repeat(repeatSpace) 
							+ ChronoUnit.MILLIS.between(start, results.get(i - 1).getFinishTime()) + "\n");
				});
	}

	private static void joinRunners(Runner[] runners) {
		IntStream.range(0, runners.length).forEach(i -> {
			try {
				runners[i].join();
			} catch (InterruptedException e) {
				throw new IllegalStateException();
			}
		});

	}

	private static void startRunners(Runner[] runners, Race race) {
		IntStream.range(0, runners.length).forEach(i -> {
			runners[i] = new Runner(race, i + 1);
			runners[i].start();
		});

	}

}
