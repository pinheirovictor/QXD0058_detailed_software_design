package Service;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Send extends Thread {

	DataOutputStream out;
	Socket clientSocket;

	public Send(Socket aClientSocket) {
		try {
			clientSocket = aClientSocket;
			out = new DataOutputStream(clientSocket.getOutputStream());
			this.start();
		} catch (IOException e) {
			System.out.println("Connection:" + e.getMessage());
		}
	}

	public void run() {
		try { // an echo server
			while (true) {
				BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));
				String msg = teclado.readLine();
				out.writeUTF(msg);
			}
		} catch (EOFException e) {
			System.out.println("EOF:" + e.getMessage());
		} catch (IOException e) {
			System.out.println("IO:" + e.getMessage());
		} finally {
			try {
				clientSocket.close();
			} catch (IOException e) {
				/* close failed */}
		}
	}
}
