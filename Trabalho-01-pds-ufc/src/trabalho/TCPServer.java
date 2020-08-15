package trabalho;

import java.net.*;
import java.io.*;

import java.net.*;
import java.io.*;
import java.util.Scanner;



public class TCPServer {

	public static void main(String args[]) {
		try {
			@SuppressWarnings("resource")
			Scanner s = new Scanner(System.in);
			System.out.println("informe a porta que o servidor vai operar:");
			int serverPort = s.nextInt();

			ServerSocket listenSocket = new ServerSocket(serverPort);
			System.out.println("Porta " + serverPort + " aberta!");
			while (true) {
				Socket clientSocket = listenSocket.accept();
				System.out.println(
						"Conexão estabelecida com o cliente " + clientSocket.getInetAddress().getHostAddress());
				Connection c = new Connection(clientSocket);
			}
		} catch (IOException e) {
			System.out.println("Listen socket:" + e.getMessage());
		}
	}
}

class Connection extends Thread {

	private DataInputStream in;
	private DataOutputStream out;
	private Socket clientSocket;

	public Connection(Socket aClientSocket) {
		try {
			clientSocket = aClientSocket;
			in = new DataInputStream(clientSocket.getInputStream());
			out = new DataOutputStream(clientSocket.getOutputStream());

			this.start();
		} catch (IOException e) {
			System.out.println("Connection:" + e.getMessage());
			System.out.println("Coenxão encerrada");
		}
	}

	public void run() {
		try {
			@SuppressWarnings("resource")
			Scanner s = new Scanner(System.in);
			while (true) {
				String data = in.readUTF();
				System.out.println("Cliente responde:" + data);
				System.out.print("Servidor envia: ");
				data = s.nextLine();
				out.writeUTF(data);
			}
		} catch (EOFException e) {
			//System.out.println("EOF:" + e.getMessage());
			System.out.println("Conexão encerrada");
		} catch (IOException e) {
			System.out.println("readline:" + e.getMessage());
		} finally {
			try {
				clientSocket.close();
				//System.out.println("Coenxão encerrada");
			} catch (IOException e) {/* close failed */
			}
		}

	}
}
