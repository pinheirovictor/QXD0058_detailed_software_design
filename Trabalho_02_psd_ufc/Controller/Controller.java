package Controller;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import Service.Receive;
import Service.Send;

public class Controller {

	public static void runCli() {

		Socket s = null;
		try {
			int serverPort = 7896;
			s = new Socket("127.0.0.1", serverPort);
			Send envia = new Send(s);
			Receive recebe = new Receive(s);

		} catch (IOException e) {
			System.out.println("IO:" + e.getMessage());
		}
	}
	
	public static void runServe() {
		try {
			int serverPort = 7896;
			ServerSocket listenSocket = new ServerSocket(serverPort);
			while (true) {
				Socket clientSocket = listenSocket.accept();
				Receive r = new Receive(clientSocket);
				Send s = new Send(clientSocket);
			}
		} catch (IOException e) {
			System.out.println("Listen :" + e.getMessage());
		}
	}

}
