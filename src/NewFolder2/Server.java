package NewFolder2;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		ServerSocket server = new ServerSocket(9001);
		System.out.println("Server Start...");
		Socket socket = server.accept();
		System.out.println("Here Comes the Client...");
		System.out.println("Enter the Message to Send...");
		String message = new Scanner(System.in).nextLine();
		socket.getOutputStream().write(message.getBytes());
		System.out.println("Message Send to Client...");
		socket.close();
		
	}

}
