package new3.code.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ChatHandler extends Thread {

    String line;
    BufferedReader in;
    PrintWriter out;
    Socket socket;

    public ChatHandler(Socket s) {
        this.socket = s;
    }

    public void run() {
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream());

        } catch (IOException e) {
            System.out.println("IO error in server thread");
        }

        try {
            line = in.readLine();
            while (line.compareTo("QUIT") != 0) {
                out.println(line);
                out.flush();
                System.out.println("Response to Client  :  " + line);
                line = in.readLine();
            }
        } catch (IOException e) {
            line = this.getName(); //reused String line for getting thread name
            System.out.println("IO Error/ Client " + line + " terminated abruptly");
        } catch (NullPointerException e) {
            line = this.getName(); //reused String line for getting thread name
            System.out.println("Client " + line + " Closed");
        } finally {
            try {
                System.out.println("Connection Closing..");
                if (in != null) {
                    in.close();
                    System.out.println(" Socket Input Stream Closed");
                }
                if (out != null) {
                    out.close();
                    System.out.println("Socket Out Closed");
                }
                if (socket != null) {
                    socket.close();
                    System.out.println("Socket Closed");
                }

            } catch (IOException e) {
                System.out.println("Socket Close Error");
            }
        } //end finally
    }
}
