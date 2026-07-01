package main;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Custom class to format the dates to the necessary text formats
 * @author aaron
 */
public class DateFormatter {

	/**
	 * Method that returns the formatted string for a date according to the needs
	 * @param date date to format
	 * @param timeSeparator if true adds 'at' between date and time
	 * @param withTime if true the returned date format will have time
	 * @return formatted string
	 */
	public static String dateStringGetter(LocalDateTime date, boolean timeSeparator, boolean withTime) {
		DateTimeFormatter formatter;
		if (withTime) {
			if (timeSeparator) {
				formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy 'at' HH:mm");
			} else {
				formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");	
			}
		} else {
			formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		}
		return date.format(formatter);
	}
	
	/**
	 * String date time parser and formatter
	 * @param date date as string dd/MM/yyyy HH:mm format 
	 * (HH:mm optional, default time 00:00 will be used if necessary)
	 * @return LocalDateTime formated dateTime
	 */
	public static LocalDateTime dateFormatterParser(String date) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
		if (!date.contains(":")) {
			//si la fecha no contiene hora se añade una hora por defecto
			String time = " 00:00";
			date = date + time;
		}
		LocalDateTime currentDate = LocalDateTime.parse(date, formatter);
		return currentDate;
	}
}
