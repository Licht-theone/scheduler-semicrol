package main;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Class that implements the necessary methods to calculate next execution date
 * @author aaron
 */
public class Scheduler {
	private LocalDateTime startDateTime;
	private LocalDateTime finishDateTime;
	private Period period;
	private int numPeriod; //Every N days, months, weeks, etc
	
	/**
	 * Constructor for scheduler class that calculates the next execution time
	 * @param startDateTime start date of the task
	 * @param finishDateTime end date of the task
	 * @param period enum for the period (once, daily...)
	 * @param numPeriod every N days, weeks, months, years...
	 */
	public Scheduler(LocalDateTime startDateTime, LocalDateTime finishDateTime, Period period, int numPeriod) {
		super();
		this.startDateTime = startDateTime;
		this.finishDateTime = finishDateTime;
		this.period = period;
		this.numPeriod = numPeriod;
	}

	public LocalDateTime getStartDateTime() {
		return startDateTime;
	}

	public void setStartDateTime(LocalDateTime startDateTime) {
		this.startDateTime = startDateTime;
	}

	public LocalDateTime getFinishDateTime() {
		return finishDateTime;
	}

	public void setFinishDateTime(LocalDateTime finishDateTime) {
		this.finishDateTime = finishDateTime;
	}

	public Period getPeriod() {
		return period;
	}

	public void setPeriod(Period period) {
		this.period = period;
	}

	public int getNumPeriod() {
		return numPeriod;
	}

	public void setNumPeriod(int numPeriod) {
		this.numPeriod = numPeriod;
	}
	
	/**
	 * Metodo que en base a la periodicidad calcula la proxima fecha de ejecucion
	 * @param currentDateTime fecha actual
	 * @return next execution time como localdatetime o null
	 */
	public LocalDateTime calculateNextExcetutionTime(LocalDateTime currentDateTime) {
		if (period == Period.Daily) {
			return currentDateTime.plusDays(numPeriod);
		}
		return null;
	}
	
}
