package new3.controller;

import java.io.IOException;
import java.net.ConnectException;

import javax.swing.JOptionPane;

import new3.code.client.Client;
import new3.frames.client.ClientFrame;

public class ClientController {
	static ClientFrame clientFrame;
	static Client client;
	
	public static void sendToClient(String message) throws IOException{
		client.sendMessage(message);
	}
	
	public static void sentToFrame(String message) {
		clientFrame.printServerMessages(message);
	}

	public static void main(String[] args) {
		clientFrame = new ClientFrame();
		clientFrame.setVisible(true);
		clientFrame.initialize();
		try {
			client = new Client();
			client.recieveServerMessages();
		} catch (ConnectException e) {
			clientFrame.printConnectionError("Cannot connect to the Server... \n ");
			int answer = clientFrame.reconnect();
			if(answer == JOptionPane.NO_OPTION) {
				System.exit(0);
			}
			try {
				client.closePort();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} finally {
				clientFrame.dispose();
				main(null);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
