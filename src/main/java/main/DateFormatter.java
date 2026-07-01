package main;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateFormatter {

	/**
	 * Metodo que devuelve el string formateado para una fecha
	 * @param date la fecha a formatear
	 * @param timeSeparator añade un at entre la fecha y la hora si true
	 * @param withTime si se quiere hora o no
	 * @return el string de la fecha
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
	 * @param date date como string en formato dd/MM/yyyy HH:mm format 
	 * (HH:mm opcional, se usara una por defecto de ser necesario)
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
