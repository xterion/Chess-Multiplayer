package network;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.net.Socket;

import javax.swing.JOptionPane;

import game.Game;

public class NetworkInstance {

	private final NetworkRole role;

	private boolean myTurn;

	Socket socket;

	BufferedReader input;

	BufferedWriter output;

	public NetworkInstance(NetworkRole role, boolean myTurn) {
		this.role = role;
		this.myTurn = myTurn;
	}

	public int makeTurn(int fromX, int fromY, int toX, int toY) {
		try {
			output.write(fromX + " " + fromY + " " + toX + " " + toY);
			output.newLine();
			output.flush();
		} catch (IOException e) {
			Object[] options = { "Neues Spiel", "Beenden" };
			int i = JOptionPane.showOptionDialog(null, "Die Verbindung zum Mitspieler wurde unterbrochen.",
					"Verbindungsabbruch", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE, null, options, null);
			switch (i) {
			case 0:
				try {
					socket.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				Game.restart();
				return 1;
			default:
				System.exit(1);
				break;
			}
			
		}
		myTurn = false;
		return 0;
	}

	// TODO
	public void checkInput() {
		try {
			if (input.ready()) {

				String s = input.readLine();
				String[] strings = s.split(" ");

				Game.board.move(Integer.valueOf(strings[0]), Integer.valueOf(strings[1]), Integer.valueOf(strings[2]),
						Integer.valueOf(strings[3]));

				myTurn = true;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void closeConnection() {
		try {
			input.close();
			output.close();
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public NetworkRole getRole() {
		return role;
	}

	public boolean isMyTurn() {
		return myTurn;
	}

}
