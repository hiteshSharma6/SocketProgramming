package new3.code.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import new3.controller.ServerController;
import new3.dataStructure.MobileNumber;

public class Server {
	private ServerSocket serverSocket;
	private BufferedReader in;
	private PrintWriter out;
	private final int PORT= 9001;
	
	Map<MobileNumber, ArrayList<String>> users = new HashMap<MobileNumber, ArrayList<String>>();
	Map<String, ArrayList<String>> chatGroups = new HashMap<String, ArrayList<String>>(); // String is ID of groups 
		
	public Server() throws Exception{
		serverSocket = new ServerSocket(PORT);
	}
	
	public void addUsers() throws Exception {
		Socket socket = serverSocket.accept();
	    in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	    out = new PrintWriter(socket.getOutputStream(), true);
	    while (true) {
            try {
                socket = serverSocket.accept();
                System.out.println("Connection Established");
                ChatHandler chatHandler = new ChatHandler(socket);
                chatHandler.start();
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Connection Error");
            }
        }
	}
	
	public void sendMessage(String message) throws IOException{
		out.println(message);
	}
	
	public void recieveClientMessages() throws IOException{
		String messageRecieved = "";
		while(!messageRecieved.equalsIgnoreCase("exit")) {
			messageRecieved = in.readLine();
			String[] mmessageRecieved = messageRecieved.split("#");
			System.out.println(mmessageRecieved[0]+mmessageRecieved[1]+mmessageRecieved[2]+ " and "+ messageRecieved);
			ServerController.sendToFrame(messageRecieved);
		}
	}
	
	public void closePort() throws IOException {
		serverSocket.close();
	}
	
//	public static void main(String args[]) throws NotAMobileNumberException {
//		MobileNumber m = new MobileNumber("123456789");
//	}

}

