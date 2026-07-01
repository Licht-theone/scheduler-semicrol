package main;

import java.time.LocalDateTime;
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
	 * Method that generates the string to display for recurring tasks
	 * @param currentDateTime current date to calculate next execution date
	 * @return formatted string to display
	 */
	public String occursRecurringText(LocalDateTime currentDateTime) {
		LocalDateTime next = calculateNextExcetutionTime(currentDateTime);
		if (next == null) {
			return "Task has expired and won't execute again";
		}
		String desc = "Occurs every " + numPeriod + " " + period.periodString();
		desc = desc + " Schedule will be used on " + DateFormatter.dateStringGetter(next, true, true) + 
				" starting on " + DateFormatter.dateStringGetter(startDateTime, false, false);
		return desc;
	}
	
	/**
	 * Method that generates the string for a one-off task
	 * @param dateTime date the task will be executed on
	 * @return formatted string to display
	 */
	public String occursOnceLogic(LocalDateTime dateTime) {
		String desc = "Occurs once. Schedule will be used on ";
		desc = desc + DateFormatter.dateStringGetter(dateTime, true, true);
		desc = desc + " starting on " + DateFormatter.dateStringGetter(startDateTime, false, false);
		return desc;
	}
	
	/**
	 * Methond that calculates next execution date for a recurring task
	 * @param currentDateTime current date time
	 * @return next execution date or null if the task has already concluded (end date)
	 */
	public LocalDateTime calculateNextExcetutionTime(LocalDateTime currentDateTime) {
		LocalDateTime next = period.calculateNext(currentDateTime, startDateTime,numPeriod);
		if (finishDateTime != null && next.isAfter(finishDateTime)) {
			next = null;
		}
		return next;
	}
	
}
