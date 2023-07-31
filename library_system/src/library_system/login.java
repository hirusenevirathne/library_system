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
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

public class login extends JFrame {

	
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
					window.setVisible(true);
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
		//JOptionPane.showMessageDialog(null, "Connect to the Database Sucessfully !");
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		setBounds(100, 100, 1297, 734);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JPanel background_panel = new JPanel();
		background_panel.setBorder(null);
		background_panel.setBackground(new Color(255, 255, 255));
		background_panel.setBounds(0, 0, 1281, 695);
		getContentPane().add(background_panel);
		
		JPanel topblue_panel = new JPanel();
		topblue_panel.setBackground(new Color(0, 153, 255));
		topblue_panel.setLayout(null);
		
		JLabel lbllibrarysystem = new JLabel("Library Management System");
		lbllibrarysystem.setForeground(new Color(255, 255, 255));
		lbllibrarysystem.setFont(new Font("Times New Roman", Font.BOLD, 40));
		lbllibrarysystem.setBounds(34, 0, 624, 99);
		topblue_panel.add(lbllibrarysystem);
		
		JLabel lblimage1 = new JLabel("");
		lblimage1.setForeground(new Color(255, 255, 255));
		lblimage1.setBackground(new Color(255, 255, 255));
		lblimage1.setIcon(new ImageIcon(login.class.getResource("/main/images/01.01.jpg")));
		
		JLabel lblLogin = new JLabel("Login");
		lblLogin.setFont(new Font("Tahoma", Font.BOLD, 30));
		
		JLabel lblinfoText = new JLabel("Please enter your loging and password");
		lblinfoText.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		textFieldusername = new JTextField();
		textFieldusername.setForeground(Color.BLACK);
		textFieldusername.setColumns(10);
		
		textFieldpassword = new JPasswordField();
		textFieldpassword.setForeground(Color.BLACK);
		textFieldpassword.setColumns(10);
		
		JLabel lblusername = new JLabel("Username");
		lblusername.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					
					String queryString = "SELECT * FROM admin WHERE username = ? AND password = ? "; //SQL Query
					PreparedStatement pStatement = connection.prepareStatement(queryString);
					pStatement.setString(1, textFieldusername.getText()); //get the username and add it to the query
					pStatement.setString(2, textFieldpassword.getText()); //get the password and add it to the query
					Resultset rsResultset = (Resultset) pStatement.executeQuery(); //type cast and execute query
					
					
					//test code loging
					//JOptionPane.showMessageDialog(null,"Username And Password is Valid !");
					home_menu home_men = new home_menu();
					home_men.setVisible(true);
					setVisible(false); //close the current frame
					//((Connection) rsResultset).close(); //close the connection with database
					pStatement.close(); //and let ohter method to access it
					connection.close();
					
					//
					
					int count = 0;
					while (((ResultSet) rsResultset).next()) { //check the results and count them
						count++;
					}
					
					
					
					
					if (count == 1) { //if username and password correct
						//JOptionPane.showMessageDialog(null,"Username And Password is Valid !");
						home_menu home_men = new home_menu();
						home_men.setVisible(true);
						setVisible(false); //close the current frame
						//((Connection) rsResultset).close(); //close the connection with database
						pStatement.close(); //and let ohter method to access it
						connection.close();
						
					} else if (count > 1) {//if username and password repeat
						JOptionPane.showMessageDialog(null,"Duplicate Username And Password !");
					}else {//if username and password incorrect
						JOptionPane.showMessageDialog(null,"Username And Password is InValid !");
					}
					
					
					} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "somthing went wrong Try Again Later !");
					System.out.println(e2);
					
				}
				
				}
				
			}
		);
		btnNewButton.setForeground(new Color(0, 0, 0));
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearform();
			}
		});
		btnCancel.setForeground(Color.BLACK);
		btnCancel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GroupLayout gl_background_panel = new GroupLayout(background_panel);
		gl_background_panel.setHorizontalGroup(
			gl_background_panel.createParallelGroup(Alignment.LEADING)
				.addComponent(topblue_panel, GroupLayout.PREFERRED_SIZE, 1281, GroupLayout.PREFERRED_SIZE)
				.addGroup(gl_background_panel.createSequentialGroup()
					.addGap(30)
					.addComponent(lblimage1)
					.addGap(368)
					.addGroup(gl_background_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_background_panel.createSequentialGroup()
							.addGap(52)
							.addGroup(gl_background_panel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_background_panel.createSequentialGroup()
									.addGap(69)
									.addComponent(lblLogin, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE))
								.addComponent(lblinfoText, GroupLayout.PREFERRED_SIZE, 342, GroupLayout.PREFERRED_SIZE)))
						.addComponent(lblusername, GroupLayout.PREFERRED_SIZE, 108, GroupLayout.PREFERRED_SIZE)
						.addComponent(textFieldusername, GroupLayout.PREFERRED_SIZE, 327, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblPassword, GroupLayout.PREFERRED_SIZE, 108, GroupLayout.PREFERRED_SIZE)
						.addComponent(textFieldpassword, GroupLayout.PREFERRED_SIZE, 327, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 327, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_background_panel.createSequentialGroup()
							.addGap(50)
							.addComponent(btnCancel, GroupLayout.PREFERRED_SIZE, 232, GroupLayout.PREFERRED_SIZE))))
		);
		gl_background_panel.setVerticalGroup(
			gl_background_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_background_panel.createSequentialGroup()
					.addComponent(topblue_panel, GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE)
					.addGap(11)
					.addGroup(gl_background_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblimage1)
						.addGroup(gl_background_panel.createSequentialGroup()
							.addGap(11)
							.addGroup(gl_background_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblLogin, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_background_panel.createSequentialGroup()
									.addGap(58)
									.addComponent(lblinfoText, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)))
							.addGap(34)
							.addComponent(lblusername, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)
							.addGap(4)
							.addComponent(textFieldusername, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
							.addGap(43)
							.addComponent(lblPassword, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)
							.addGap(1)
							.addComponent(textFieldpassword, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
							.addGap(64)
							.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
							.addGap(45)
							.addComponent(btnCancel, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE))))
		);
		background_panel.setLayout(gl_background_panel);
	}
}
