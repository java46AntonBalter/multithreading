package telran.multithreading.games;

import java.time.Instant;
import java.util.ArrayList;

class RaceStats {
	private int racerNumber;
	private Instant finishTime;
	
	public RaceStats(int racerNumber, Instant finishTime) {
		this.racerNumber = racerNumber;
		this.finishTime = finishTime;
	}

	public int getRacerNumber() {
		return racerNumber;
	}

	public Instant getFinishTime() {
		return finishTime;
	}		
}

public class Race {
	private int distance;
	private int minSleep;
	private int maxSleep;
	private static ArrayList <RaceStats> resultsTable = new ArrayList<>();
	public Race(int distance, int minSleep, int maxSleep) {
		this.distance = distance;
		this.minSleep = minSleep;
		this.maxSleep = maxSleep;
	}
	public ArrayList <RaceStats> getResultsTable() {
		return resultsTable;
	}
	synchronized static void setResultsToTable(int racerNumber, Instant finishTime) {
		resultsTable.add(new RaceStats(racerNumber, finishTime));
	}
	public int getDistance() {
		return distance;
	}
	public int getMinSleep() {
		return minSleep;
	}
	public int getMaxSleep() {
		return maxSleep;
	}
	
}
