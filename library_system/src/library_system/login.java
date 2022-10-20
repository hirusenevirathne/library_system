package library_system;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JTextField;

import com.mysql.cj.protocol.Resultset;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class login {

	private JFrame frame;
	private JTextField textFieldusername;
	private JTextField textFieldpassword;
	
	
	
	void clearform() {
		textFieldusername.setText("");
		textFieldpassword.setText("");
	}
	/**
	 * Launch the application.
	 * 
	 * resolution is 1024 * 640
	 * 
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					login window = new login();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	
	Connection connection = null; //create a connection object first
	 
	
	
	public login() {
		initialize();
		connection = sqlConnection.dbConnector();
		JOptionPane.showMessageDialog(null, "Connect to the Database Sucessfully !");
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1297, 734);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel background_panel = new JPanel();
		background_panel.setBorder(null);
		background_panel.setBackground(new Color(255, 255, 255));
		background_panel.setBounds(0, 0, 1281, 695);
		frame.getContentPane().add(background_panel);
		background_panel.setLayout(null);
		
		JPanel topblue_panel = new JPanel();
		topblue_panel.setBackground(new Color(0, 153, 255));
		topblue_panel.setBounds(0, 0, 1281, 134);
		background_panel.add(topblue_panel);
		topblue_panel.setLayout(null);
		
		JLabel lbllibrarysystem = new JLabel("Library Management System");
		lbllibrarysystem.setForeground(new Color(255, 255, 255));
		lbllibrarysystem.setFont(new Font("Times New Roman", Font.BOLD, 40));
		lbllibrarysystem.setBounds(49, 0, 624, 99);
		topblue_panel.add(lbllibrarysystem);
		
		JLabel lblimage1 = new JLabel("");
		lblimage1.setForeground(new Color(255, 255, 255));
		lblimage1.setBackground(new Color(255, 255, 255));
		lblimage1.setIcon(new ImageIcon(login.class.getResource("/main/images/01.01.jpg")));
		lblimage1.setBounds(30, 145, 290, 515);
		background_panel.add(lblimage1);
		
		JLabel lblLogin = new JLabel("Login");
		lblLogin.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblLogin.setBounds(809, 156, 130, 75);
		background_panel.add(lblLogin);
		
		JLabel lblinfoText = new JLabel("Please enter your loging and password");
		lblinfoText.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblinfoText.setBounds(740, 214, 342, 27);
		background_panel.add(lblinfoText);
		
		textFieldusername = new JTextField();
		textFieldusername.setForeground(Color.BLACK);
		textFieldusername.setBounds(688, 293, 327, 38);
		background_panel.add(textFieldusername);
		textFieldusername.setColumns(10);
		
		textFieldpassword = new JPasswordField();
		textFieldpassword.setForeground(Color.BLACK);
		textFieldpassword.setColumns(10);
		textFieldpassword.setBounds(688, 389, 327, 38);
		background_panel.add(textFieldpassword);
		
		JLabel lblusername = new JLabel("Username");
		lblusername.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblusername.setBounds(688, 275, 108, 14);
		background_panel.add(lblusername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblPassword.setBounds(688, 374, 108, 14);
		background_panel.add(lblPassword);
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					
					String queryString = "SELECT * FROM admin WHERE username = ? AND password = ? "; //SQL Query
					PreparedStatement pStatement = connection.prepareStatement(queryString);
					pStatement.setString(1, textFieldusername.getText()); //get the username and add it to the query
					pStatement.setString(2, textFieldpassword.getText()); //get the password and add it to the query
					Resultset rsResultset = (Resultset) pStatement.executeQuery(); //type cast and execute query
					
					
					int count = 0;
					while (((ResultSet) rsResultset).next()) { //check the results and count them
						count++;
					}
					if (count == 1) { //if username and password correct
						JOptionPane.showMessageDialog(null,"Username And Password is Valid !");
						clearform();
					} else if (count > 1) {//if username and password repeat
						JOptionPane.showMessageDialog(null,"Duplicate Username And Password !");
					}else {//if username and password incorrect
						JOptionPane.showMessageDialog(null,"Username And Password is InValid !");
					}
					//  ((Connection) rsResultset).close(); //close the connection with database
					pStatement.close(); //and let ohter method to access it
					connection.close();
					
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "somthing went wrong Try Again Later !");
					
				}
				}
				
			}
		);
		btnNewButton.setForeground(new Color(0, 0, 0));
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton.setBounds(688, 491, 327, 38);
		background_panel.add(btnNewButton);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearform();
			}
		});
		btnCancel.setForeground(Color.BLACK);
		btnCancel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnCancel.setBounds(738, 574, 232, 38);
		background_panel.add(btnCancel);
	}
}
