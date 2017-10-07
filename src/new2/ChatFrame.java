package new2;

import java.io.*;
import java.net.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Scanner;

public class ChatFrame extends Frame{
    public ChatFrame(){
        setSize(500,500);
        setTitle("Chatting with myself");
        addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent we){
                System.exit(0);
            }
        });
        ChatPanel sp = new ChatPanel();
        add(sp, BorderLayout.CENTER);
        setVisible(true);
    }

    public static void main(String[] args){
        ChatFrame sf = new ChatFrame();
    }
}

class ChatPanel extends Panel implements ActionListener, Runnable{
    Thread t;
    TextField tf;
    TextArea ta;
    List lst;
    Button b;
    Socket s;
BufferedReader br;
PrintWriter pw;
String temp;
boolean connected;

public ChatPanel(){
    setLayout(new BorderLayout());
    tf = new TextField();
    tf.addActionListener(this);
    add(tf, BorderLayout.NORTH);
    ta = new TextArea();
    add(ta, BorderLayout.WEST);
    lst = new List();
    add(lst, BorderLayout.EAST);
    b = new Button("Connect");
    b.addActionListener(this);
    add(b, BorderLayout.SOUTH);
    //t = new Thread(this);
}


public void actionPerformed(ActionEvent ae){
    if((ae.getSource() == b) && (!connected)){
        try{
            s = new Socket("127.0.0.1", 2020);
            pw = new PrintWriter(s.getOutputStream(), true);
        }catch(UnknownHostException uhe){
            System.out.println(uhe.getMessage());
        }catch(IOException ioe){
            System.out.println(ioe.getMessage());
        }
        connected = true;
        t = new Thread(this);
        //b.setEnabled(false);
        b.setLabel("Disconnect");
        t.start();
    }else if((ae.getSource() == b) && (connected)){
        connected = false;
        try{
            s.close(); //no buffering so, ok
        }catch(IOException ioe){
            System.out.println(ioe.getMessage());
        }
        //System.exit(0);
        b.setLabel("Connect");
    }else{
        temp = tf.getText();
        pw.println(temp);
        tf.setText("");
    }
}
public void run(){
    try{
        BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
        ta.append("Please enter a username"+"\n");
        while(((temp = br.readLine()) != null) && connected){
            ta.append(temp + "\n");
            temp = "";


    }}catch(IOException ioe){
        System.out.println(ioe.getMessage());

    }
}
}