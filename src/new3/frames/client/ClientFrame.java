package new3.frames.client;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import new3.controller.ClientController;

public class ClientFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	JTextArea textArea = new JTextArea();
	
	/**
	 * Create the frame.
	 */
	public ClientFrame() {
		setTitle("Chat Client");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 672, 518);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(new LineBorder(new Color(0, 0, 255), 3, true));
		scrollPane.setBounds(31, 23, 572, 288);
		contentPane.add(scrollPane);
		
		textArea.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		scrollPane.setViewportView(textArea);
		
		textField = new JTextField();
		textField.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		textField.setBounds(31, 376, 559, 56);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblEnterMessageTo = new JLabel("Enter Message to Send");
		lblEnterMessageTo.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		lblEnterMessageTo.setBounds(32, 344, 231, 20);
		contentPane.add(lblEnterMessageTo);
		
		JButton btnSend = new JButton("Send");
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			sendMessage();
			}
		});
		btnSend.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		btnSend.setBounds(473, 443, 117, 29);
		contentPane.add(btnSend);
	}
	
	public void initialize() {
		textArea.setText("Connected to Server.....(type 'exit' to leave)");
	}
	
	public void printServerMessages(String message) {
		textArea.setText(textArea.getText()+"\n Server :: "+message);
	}
	
	public void printConnectionError(String error) {
		JOptionPane.showMessageDialog(this, error);
	}
	
	public int reconnect() {
		return JOptionPane.showConfirmDialog(this, "Try to reconnect..", "Reconnect", JOptionPane.YES_NO_OPTION);
	}
	
	public void sendMessage(){
		String message = textField.getText();
		try {
			ClientController.sendToClient(message);
			textArea.setText(textArea.getText()+"\n Client :: "+message);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}