package main;

import java.time.LocalDateTime;

public enum Period {
	Daily

	{
		@Override
		public LocalDateTime calcularSiguiente(LocalDateTime actual, int n) {
			return actual.plusDays(n);
		}

		@Override
		public String stringPeriodo() {
			return "day(s).";
		}
	};
	public abstract LocalDateTime calcularSiguiente(LocalDateTime actual, int n);
	
	public abstract String stringPeriodo();
}
