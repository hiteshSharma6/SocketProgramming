package new3.controller;

import java.io.IOException;

import new3.code.server.Server;
import new3.frames.server.ServerFrame;

public class ServerController {
	
	static ServerFrame serverFrame;
	static Server server;
	
	public static void sendToServer(String message) throws IOException {
		server.sendMessage(message);
	}
	
	public static void sendToFrame(String message) {
		serverFrame.printClientMessages(message);
	}
	
//	public static void sendError(String error) {
//		serverFrame.printError(error);
//	}

	public static void main(String[] args) {
	    try {
			server = new Server();
	    	serverFrame = new ServerFrame();
			serverFrame.setVisible(true);
			serverFrame.initialize();
			server.addUsers();
		    server.recieveClientMessages();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				server.closePort();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
