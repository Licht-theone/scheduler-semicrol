package main;

import java.text.SimpleDateFormat;

public class GUI {

	public static void main(String[] args) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		MainWindow frame = new MainWindow();
		frame.setVisible(true);
	}

}
