package main;

import java.time.LocalDateTime;

public enum Period {
	Daily

	{
		@Override
		public LocalDateTime calcularSiguiente(LocalDateTime actual, LocalDateTime inicio, int n) {
			LocalDateTime siguiente = inicio;
			while (siguiente.isBefore(actual)) {
				siguiente = siguiente.plusDays(n);
			}
			return siguiente;
		}

		@Override
		public String stringPeriodo() {
			return "day(s).";
		}
	};
	public abstract LocalDateTime calcularSiguiente(LocalDateTime actual, LocalDateTime inicio, int n);
	
	public abstract String stringPeriodo();
}
