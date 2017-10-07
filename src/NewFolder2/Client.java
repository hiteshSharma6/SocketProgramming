package NewFolder2;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

	public static void main(String[] args) throws UnknownHostException, IOException {
		// TODO Auto-generated method stub
		Socket socket = new Socket("localhost",9001);
		System.out.println("Client Join...");
		InputStream is = socket.getInputStream();
		int singleByte = is.read();
		while(singleByte!=-1){
			System.out.print((char)singleByte);
			singleByte = is.read();
		}
		is.close();
		socket.close();

	}

}
