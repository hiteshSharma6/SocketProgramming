package new2;

import java.io.*;
import java.net.*;
import java.util.*;

public class ChatServer{
  Socket s;
  ArrayList<ChatHandler>handlers;
  public ChatServer(){
try{
  ServerSocket ss = new ServerSocket(2020);
  handlers = new ArrayList<ChatHandler>();
  for(;;){
s = ss.accept();//wait for a new client to connect
new ChatHandler(s, handlers).start();// spawn a handler for client
  }
}catch(IOException ioe){
  System.out.println(ioe.getMessage());
}
  }
  public static void main(String[] args){
ChatServer tes = new ChatServer(); 
  }

}
class ChatHandler extends Thread{
  Socket s1;
  BufferedReader br;
  PrintWriter pw;
  String temp;
  ArrayList<ChatHandler>handlers;
  String nickName;
  List<String> myList = new ArrayList<String>();

  public ChatHandler(Socket s, ArrayList<ChatHandler>handlers){
    this.s1 = s1;
    this.handlers = handlers;
    this.nickName = nickName;
  }


  public void run(){
    try{
      handlers.add(this);// add myself to list
      br = new BufferedReader(new InputStreamReader(s1.getInputStream()));
      pw = new PrintWriter(s1.getOutputStream(), true);
      temp = "";
      System.out.println("Enter a username: ");
      nickName = br.readLine();
      myList.add(nickName);
      System.out.println(nickName+" joined the chat!");
      while((temp = br.readLine()) != null){
        for (ChatHandler ch : handlers){// broadcast to all clients
             ch.pw.println(nickName+":"+" "+temp); //tell each handler in turn to send message to its client
    }
    System.out.println(temp);
      }
    }catch(IOException ioe){
      System.out.println(ioe.getMessage());      
    }finally{
      handlers.remove(this);//remove myself from list
    }
  }
}
