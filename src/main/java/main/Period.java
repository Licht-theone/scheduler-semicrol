package main;

import java.time.LocalDateTime;

/**
 * Enum that also helps calculate next execution date. can be scaled to any desired frecuency
 * @author aaron
 */
public enum Period {
	Daily

	{
		@Override
		public LocalDateTime calculateNext(LocalDateTime current, LocalDateTime start, int n) {
			LocalDateTime siguiente = start;
			while (siguiente.isBefore(current)) {
				siguiente = siguiente.plusDays(n);
			}
			return siguiente;
		}

		@Override
		public String periodString() {
			return "day(s).";
		}
	};
	
	/**
	 * method that calculates the next execution time based on the period and N (number of days, months...)
	 * @param current current date time
	 * @param start start date time
	 * @param n number of days, weeks months...
	 * @return next execution date
	 */
	public abstract LocalDateTime calculateNext(LocalDateTime current, LocalDateTime start, int n);
	
	/**
	 * Method that returns the string to use on the display text
	 * @return string (day(s). etc)
	 */
	public abstract String periodString();
}
