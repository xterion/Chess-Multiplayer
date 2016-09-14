package network;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JLabel;

import game.Board;
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

	// TODO
	public void makeTurn(int fromX, int fromY, int toX, int toY) {
		try {
			output.write(fromX + " " + fromY + " " + toX + " " + toY);
			
			output.newLine();
			output.flush();
			System.out.println("made move");
		} catch (IOException e) {
			e.printStackTrace();
		}
		myTurn = false;
	}

	// TODO
	public void checkInput() {
		try {
			if (input.ready()) {

				String s = input.readLine();
				System.out.println(s);

				String[] strings = s.split(" ");

				Game.board.move(Integer.valueOf(strings[0]), Integer.valueOf(strings[1]), Integer.valueOf(strings[2]),
						Integer.valueOf(strings[3]));
				System.out.println("received");

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
