package network;

import javax.swing.JOptionPane;

public class WaitForConnectionThread extends Thread {

	public void run() {

		Object[] options = { "Beenden" };
		// Reponse Window
		int i = JOptionPane
				.showOptionDialog(
						null,
						"Es wird momentan auf eine eingehende Verbindung gewartet. Sobald diese hergestellt ist, beginnt das Spiel automatisch.",
						"Warte auf Gegner...", JOptionPane.CLOSED_OPTION,
						JOptionPane.INFORMATION_MESSAGE, null, options, null);
		if (i == 0) {
			System.exit(0);
		}

	}

}
