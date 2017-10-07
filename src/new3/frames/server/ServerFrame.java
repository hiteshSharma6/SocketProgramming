package new3.frames.server;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

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

import new3.controller.ServerController;

public class ServerFrame extends JFrame {

	private JTextField textField;
	private JTextArea textArea;
	
	/**
	 * Create the frame.
	 */
	public ServerFrame() {
		setTitle("CHAT-SERVER");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 672, 518);
		
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(new LineBorder(new Color(0, 0, 255), 3, true));
		scrollPane.setBounds(31, 23, 572, 288);
		contentPane.add(scrollPane);
		
		textArea = new JTextArea();
		textArea.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		scrollPane.setViewportView(textArea);
		
		textField = new JTextField();
		textField.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		textField.setBounds(31, 376, 559, 56);
		textField.setColumns(10);
		contentPane.add(textField);
		
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
	
	public void initialize() throws IOException {
		textArea.setText("Waiting for the Client to Join the Chat.....('exit' to leave)");
	}
	
	public void printClientMessages(String message) {
		textArea.setText(textArea.getText()+"\n Client :: "+message);
	}
	
	public void sendMessage() {
		String message = textField.getText();
		try {
			ServerController.sendToServer(message);
			textArea.setText(textArea.getText()+"\n Server :: "+ message);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			JOptionPane.showMessageDialog(this, "No one connected yet....");			
		}
	}
	
	public void printError(String error) {
	}
	
}
