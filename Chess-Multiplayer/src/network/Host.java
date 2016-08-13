package network;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;

public class Host extends NetworkInstance{

	ServerSocket serverSocket;

	public Host() {
		super(NetworkRole.HOST, true);
	}

	public void openServer(int port) {

		try {
			serverSocket = new ServerSocket(port);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void waitForClient() {

			try {
				socket = serverSocket.accept();
				input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				output = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
	
	public void closeServer() {
		try {
			serverSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
