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
	 * Metodo que gestiona la logica de periodicidad
	 * @param startDate fecha de inicio
	 * @param dateTime fecha de ejecucion
	 * @param endDate fecha final de ser necesaria
	 * @param next siguiente fecha de ejecucion
	 * @param p periodo a usar
	 * @param n dias, meses, años, semanas, segun el periodo, numero entero
	 * @return el string a visualizar
	 */
	public String occursRecurringText(LocalDateTime currentDateTime) {
		LocalDateTime next = calculateNextExcetutionTime(currentDateTime);
		String desc = "Occurs every " + numPeriod + " " + period.stringPeriodo();
		desc = desc + " Schedule will be used on " + DateFormatter.dateStringGetter(next, false, false) + 
				" starting on " + DateFormatter.dateStringGetter(startDateTime, false, false);
		return desc;
	}
	
	/**
	 * Metodo que gestiona tareas de una sola vez
	 * @param startDate fecha de inicio
	 * @param dateTime fecha de ejecucion
	 * @return string formateado a mostrar
	 */
	public String occursOnceLogic(LocalDateTime dateTime) {
		String desc = "Occurs once. Schedule will be used on ";
		desc = desc + DateFormatter.dateStringGetter(dateTime, true, true);
		desc = desc + " starting on " + DateFormatter.dateStringGetter(startDateTime, false, false);
		return desc;
	}
	
	/**
	 * Metodo que en base a la periodicidad calcula la proxima fecha de ejecucion
	 * @param currentDateTime fecha actual
	 * @return next execution time como localdatetime o null
	 */
	public LocalDateTime calculateNextExcetutionTime(LocalDateTime currentDateTime) {
		return period.calcularSiguiente(currentDateTime, numPeriod);
	}
	
}
