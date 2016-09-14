package network;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

import javax.swing.JOptionPane;

public class Client extends NetworkInstance {

	public Client() {
		super(NetworkRole.CLIENT, false);
	}

	public int connect(String host, int port) {
		try {
			socket = new Socket(host, port);
			input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			output = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

		} catch (IOException e) {
			Object[] options = { "Ja", "Host ändern", "Nein" };
			int n = JOptionPane
					.showOptionDialog(
							null,
							"Es gab einen Fehler beim Verbinden zum Server, möchten sie es erneut versuchen?",
							"Verbindungsfehler", JOptionPane.YES_NO_OPTION,
							JOptionPane.ERROR_MESSAGE, null, options, null);
			if (n == 2) {
				System.exit(0);
			} else {
				return n + 1;
			}
		}
		return 0;

	}
}
