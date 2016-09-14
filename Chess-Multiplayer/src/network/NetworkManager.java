package network;

import javax.swing.JOptionPane;

public class NetworkManager {

	public static NetworkInstance selectRole() {

		Object[] options = { "Host", "Client", "Abbrechen" };

		int n = JOptionPane
				.showOptionDialog(null, "Möchtest du Host (weiß) oder Client (schwarz) sein?",
						"Network Role Selection", JOptionPane.YES_NO_CANCEL_OPTION,
						JOptionPane.QUESTION_MESSAGE, null, options, null);
		NetworkInstance networkInstance = null;

		switch (n) {
		case 0:
			networkInstance = createHost();
			break;
		case 1:
			networkInstance = createClient();
			break;
		default:
			System.exit(0);
		}
		return networkInstance;
	}

	private static NetworkInstance createHost() {
		Host host = new Host();
		host.openServer(9595);
		host.waitForClient();
		return host;
	}

	private static NetworkInstance createClient() {
		String host = JOptionPane.showInputDialog("Bitte die Adresse eines Hostes angeben",
				"localhost");

		Client client = new Client();
		int status = client.connect(host, 9595);
		int retryCounter = 0;
		while (status != 0 && retryCounter < 3) {
			if (status == 2) {
				host = JOptionPane.showInputDialog("Bitte die Adresse eines Hostes angeben", host);
			}

			retryCounter++;
			status = client.connect(host, 9595);
		}
		if (retryCounter >= 3) {
			JOptionPane.showMessageDialog(null,
					"Die Verbindung konnte nicht hergestellt werden. Das Programm wird beendet.",
					"Verbindungsaufbau nicht m�glich", JOptionPane.ERROR_MESSAGE);
			System.exit(1);

		}
		return client;
	}

}
