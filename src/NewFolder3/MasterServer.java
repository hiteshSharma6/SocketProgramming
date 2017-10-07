package NewFolder3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
/*
 * 1.Manageuser is server thread
 * 2.
 */

public class MasterServer {
	public static void main(String[] args) throws Exception {
        new MasterServer().createserver();
}
	ArrayList<String> users = new ArrayList<String>();
    ArrayList<Manageuser> clients = new ArrayList<Manageuser>();

    public void createserver() throws Exception {
        ServerSocket server = new ServerSocket(80,10);
        System.out.println("Server is now Running");
        while (true) {
            Socket client = server.accept();
            Manageuser c = new Manageuser(client);
            clients.add(c);
        }
    }

    

    public void sendtoall(String user, String message) {
        System.out.println(user+"::"+message);
        for (Manageuser c : clients) {
            if (!c.getchatusers().equals(user)) {
                c.sendMessage(user, message);
            }
        }
    }

    class Manageuser extends Thread {

        String gotuser = "";
        BufferedReader input;
        PrintWriter output;

        public Manageuser(Socket client) throws Exception {

            input = new BufferedReader(new InputStreamReader(client.getInputStream()));
            output = new PrintWriter(client.getOutputStream(), true);

            gotuser = input.readLine();//name of user
            users.add(gotuser); //users is the arraylist of clients.
            	start();
        }

        public void sendMessage(String chatuser, String chatmsg) {
            output.println(chatuser + " Says:" + chatmsg);
        }

        public String getchatusers() {
            return gotuser;
        }

        @Override
        public void run() {
            String line;
            try {
                while (true) {
                    line = input.readLine();
                    if (line.equals("end")) {
                        clients.remove(this);
                        users.remove(gotuser);
                        break;
                    }
                    sendtoall(gotuser, line); 
                }
            } 
            catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        } 
    } 
} 
