package trabalho;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class TCPClient {

	public static void main(String args[]) {

		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);

		System.out.println("Informe o IP do cliente:");
		String ipClient = scan.nextLine();

		System.out.println(
				"Informe a porta do servidor que o cliente vai operar:");
		int serverPort = scan.nextInt();

		Socket socket = null;

		try {

			socket = new Socket(ipClient, serverPort);
			DataInputStream in = new DataInputStream(socket.getInputStream());
			DataOutputStream out = new DataOutputStream(socket.getOutputStream());

			while (true) {
				System.out.print("Cliente envia: "+ "\n");
				String msg = scan.nextLine();
				out.writeUTF(msg);
				System.out.println(in.readUTF());
			}

		} catch (UnknownHostException e) {
			System.out.println("Socket:" + e.getMessage());
			System.out.println("conexão encerrada");
		} catch (EOFException e) {
			System.out.println("EOF:" + e.getMessage());
			System.out.println("conexão encerrada");
		} catch (IOException e) {
			System.out.println("readline:" + e.getMessage());
			System.out.println("conexão encerrada");
		} finally {
			if (socket != null) {
				try {
					socket.close();
					System.out.println("conexão encerrada");
				} catch (IOException e) {
					System.out.println("close:" + e.getMessage());
					System.out.println("conexão encerrada");
				}
			}
		}
	}

}