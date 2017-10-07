package new3.code.client;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import new3.controller.ClientController;
import new3.frames.client.ClientFrame;

public class Client {
	
	private Socket socket ;
	private BufferedReader in;
	private PrintWriter out;
	private final int PORT= 9001;
	private final String SERVER_NAME = "localhost";
	private String user;
	
	public Client() throws IOException{
		socket = new Socket(SERVER_NAME,PORT);
		in =new BufferedReader(new InputStreamReader(socket.getInputStream()));
	    out = new PrintWriter(socket.getOutputStream(), true);
	    user = "Hitesh";
	}
	
	public String getUser() {
		return user;
	}
	
	public void sendMessage(String message) throws IOException{
		out.println(user+ "#"+ message+ "#101");
	}
	
	public void closePort() throws IOException {
		socket.close();
	}
	
	public void recieveServerMessages() throws IOException{
		String messageRecieved = "";
		while(!messageRecieved.equalsIgnoreCase("exit")){
			messageRecieved = in.readLine();
			ClientController.sentToFrame(messageRecieved);
		}
	}

}
