package library_system.UI;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import library_system.booksUI;
import library_system.homeUI;
import library_system.sqlConnection;
import net.proteanit.sql.DbUtils;

public class addMem_UI extends JFrame {

	private JPanel contentPane;
	private JTextField textFAuthorID;
	private JTextField textFAFname;
	private JTextField textFALname;
	
	private String authorID = "";
	private String aFname = "";
	private String aLname = "";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					addMem_UI frame = new addMem_UI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
	
	Connection connection = null; //create the connection variable
	private JTable table;
	
	public void setColouronMouse( JPanel panel) { //create a method to change button color when mouse is on it
		panel.setBackground(new java.awt.Color(115, 163, 239));		
	}
	public void reSetColouronMouse( JPanel panel) {//create a method to change original button color when mouse is not on it
		panel.setBackground(SystemColor.controlHighlight);		
	}
	
	public void showTable() { //method for show the table 
		try {
			
			String queryString = "SELECT \r\n"
					+ "Author_id AS 'Author ID',\r\n"
					+ "A_F_name AS 'Author First Name',\r\n"
					+ "A_L_name AS 'Author Last Name'\r\n"
					+ "FROM \r\n"
					+ "library_system.author\r\n"
					+ ";";
			
			//System.out.println(queryString);
			
			
			PreparedStatement pStatement = connection.prepareStatement(queryString);
			ResultSet rsResultset = pStatement.executeQuery(); 
			table.setModel(DbUtils.resultSetToTableModel(rsResultset));
			
			
			pStatement.close();
			rsResultset.close();
			
			
			
		} catch (Exception e2) {
			System.out.println(e2);
			System.out.println("this error in Save button");
			// TODO: handle exception
		}
		
	}
	
	
	//Save and Validation methods
public void save() {
		
		try {
			authorID = textFAuthorID.getText();
			aFname = textFAFname.getText();
			aLname = textFALname.getText();
			
			
			System.out.println(authorID);
			System.out.println(aFname);
			System.out.println(aLname);
			
			
			String queryString = "INSERT INTO `library_system`.`author` (\r\n"
					+ "`Author_id`, \r\n"
					+ "`A_F_name`, \r\n"
					+ "`A_L_name`\r\n"
					+ ") \r\n"
					+ "VALUES (\r\n"
					+ "'"+authorID+"', \r\n"
					+ "'"+aFname+"', \r\n"
					+ "'"+aLname+"'\r\n"
					+ ");";
			//System.out.println(queryString); //use this to check the errors in query
			
			
			
			PreparedStatement pStatement = connection.prepareStatement(queryString);
			pStatement.executeUpdate(queryString);
			JOptionPane.showMessageDialog(null, "Data Saved Successfully.");
			
			pStatement.close();
			
			
			
			
		} catch (Exception e2) {
			JOptionPane.showMessageDialog(null, "Something went wrong try again later.");
			System.out.println(e2);
			System.out.println("this error in Save button");
			// TODO: handle exception
		}
		
	}
	
	
	public Boolean validate_Save() {
		boolean textFeildState = false;
		
		if (textFAuthorID.getText().equals("")) {
			
			textFeildState = false;
			JOptionPane.showMessageDialog(null, "Author ID Required !");
			textFAuthorID.requestFocus();
			
		}else if (textFAFname.getText().equals("")) {
			
			textFeildState = false;
			JOptionPane.showMessageDialog(null, "Author First Name Required !");
			textFAFname.requestFocus();
			
		}else if (textFALname.getText().equals("")) {
			
			textFeildState = false;
			JOptionPane.showMessageDialog(null, "Author Last Name Required !");
			textFALname.requestFocus();
			
		}else {
			textFeildState = true;

		}
		
		
		return textFeildState;
	}
	
	
	
	
	
	
	

	/**
	 * Create the frame.
	 */
	public addMem_UI() {
		
		connection = sqlConnection.dbConnector(); //call the sql connection method
		

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1297, 734);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel backgroundpanel = new JPanel();
		backgroundpanel.setBackground(SystemColor.window);
		backgroundpanel.setBounds(0, 0, 1281, 695);
		contentPane.add(backgroundpanel);
		backgroundpanel.setLayout(null);
		
		JPanel topblue_panel = new JPanel();
		topblue_panel.setBackground(new Color(0, 153, 255));
		topblue_panel.setBounds(0, 0, 1281, 134);
		backgroundpanel.add(topblue_panel);
		topblue_panel.setLayout(null);
		
		JLabel lbllibrarysystem = new JLabel("Library Management System");
		lbllibrarysystem.setForeground(Color.WHITE);
		lbllibrarysystem.setFont(new Font("Times New Roman", Font.BOLD, 40));
		lbllibrarysystem.setBounds(34, 0, 624, 99);
		topblue_panel.add(lbllibrarysystem);
		
		//Back to Home Button 
				JPanel homebtn = new JPanel();
				homebtn.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseEntered(MouseEvent e) {
						homebtn.setBackground(SystemColor.scrollbar);
					}
					@Override
					public void mouseExited(MouseEvent e) {
						homebtn.setBackground(SystemColor.menu);
					}
					@SuppressWarnings("deprecation")
					@Override
					public void mouseClicked(MouseEvent e) {
						new booksUI().show();
						dispose(); //close the currant window
					}
				});
				homebtn.setBackground(SystemColor.menu);
				homebtn.setBounds(1110, 70, 150, 53);
				topblue_panel.add(homebtn);
				homebtn.setLayout(null);
				
				JLabel lblbacktohome = new JLabel("Back to Books");
				lblbacktohome.setBounds(44, 15, 96, 20);
				lblbacktohome.setFont(new Font("Tahoma", Font.BOLD, 13));
				homebtn.add(lblbacktohome);
				
				JLabel lblbackico = new JLabel("");
				lblbackico.setIcon(new ImageIcon(homeUI.class.getResource("/main/images/back small.png")));
				lblbackico.setBounds(10, 11, 32, 31);
				homebtn.add(lblbackico);
				
				JLabel lblAuthorID = new JLabel("Author ID *   :");
				lblAuthorID.setFont(new Font("Trebuchet MS", Font.BOLD, 19));
				lblAuthorID.setBounds(75, 184, 126, 27);
				backgroundpanel.add(lblAuthorID);
				
				textFAuthorID = new JTextField();
				textFAuthorID.setFont(new Font("Trebuchet MS", Font.PLAIN, 19));
				textFAuthorID.setColumns(10);
				textFAuthorID.setBackground(SystemColor.inactiveCaptionBorder);
				textFAuthorID.setBounds(285, 184, 246, 27);
				backgroundpanel.add(textFAuthorID);
				
				JLabel lblAuthorFirstName = new JLabel("Author First Name *   :");
				lblAuthorFirstName.setFont(new Font("Trebuchet MS", Font.BOLD, 19));
				lblAuthorFirstName.setBounds(75, 239, 208, 27);
				backgroundpanel.add(lblAuthorFirstName);
				
				textFAFname = new JTextField();
				textFAFname.setFont(new Font("Trebuchet MS", Font.PLAIN, 19));
				textFAFname.setColumns(10);
				textFAFname.setBackground(SystemColor.inactiveCaptionBorder);
				textFAFname.setBounds(285, 239, 246, 27);
				backgroundpanel.add(textFAFname);
				
				textFALname = new JTextField();
				textFALname.setFont(new Font("Trebuchet MS", Font.PLAIN, 19));
				textFALname.setColumns(10);
				textFALname.setBackground(SystemColor.inactiveCaptionBorder);
				textFALname.setBounds(285, 294, 246, 27);
				backgroundpanel.add(textFALname);
				
				JLabel lblAuthorLastName = new JLabel("Author Last Name *   :");
				lblAuthorLastName.setFont(new Font("Trebuchet MS", Font.BOLD, 19));
				lblAuthorLastName.setBounds(75, 294, 208, 27);
				backgroundpanel.add(lblAuthorLastName);
				
				JPanel panelSave = new JPanel();
				panelSave.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseEntered(MouseEvent e) {
						setColouronMouse(panelSave);
					}
					
					@Override
					public void mouseExited(MouseEvent e) {
						reSetColouronMouse(panelSave);
					}
					
					@Override
					public void mouseClicked(MouseEvent e) {
						if (validate_Save()) {
							
							save();
							
						}
						showTable();
					}
				});
				panelSave.setLayout(null);
				panelSave.setBackground(SystemColor.controlHighlight);
				panelSave.setBounds(641, 212, 252, 83);
				backgroundpanel.add(panelSave);
				
				JLabel lblSave = new JLabel("Save");
				lblSave.setFont(new Font("Trebuchet MS", Font.BOLD, 27));
				lblSave.setBounds(92, 11, 58, 42);
				panelSave.add(lblSave);
				
				JPanel panelCancel = new JPanel();
				panelCancel.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseEntered(MouseEvent e) {
						//panel.setBackground(new java.awt.Color(115, 163, 239));
						panelCancel.setBackground(SystemColor.info);
					}
					@Override
					public void mouseExited(MouseEvent e) {
						panelCancel.setBackground(UIManager.getColor("CheckBox.background"));
					}
					@Override
					public void mouseClicked(MouseEvent e) {
						
						
						textFAuthorID.setText("");
						textFAFname.setText("");
						textFALname.setText("");
						
					}
				});
				panelCancel.setLayout(null);
				panelCancel.setBackground(SystemColor.menu);
				panelCancel.setBounds(991, 246, 252, 49);
				backgroundpanel.add(panelCancel);
				
				JLabel lblCancel = new JLabel("Clear");
				lblCancel.setFont(new Font("Trebuchet MS", Font.PLAIN, 20));
				lblCancel.setBounds(93, 11, 61, 24);
				panelCancel.add(lblCancel);
				
				JLabel lblNewLabel = new JLabel("* Please only enter valid Data values");
				lblNewLabel.setFont(new Font("Trebuchet MS", Font.BOLD, 20));
				lblNewLabel.setBounds(150, 367, 361, 33);
				backgroundpanel.add(lblNewLabel);
				
				JScrollPane scrollPane = new JScrollPane();
				scrollPane.setBounds(28, 410, 1225, 260);
				backgroundpanel.add(scrollPane);
				
				table = new JTable();
				scrollPane.setViewportView(table);
				showTable();
				//Back to Home BUtton ENDS
	}
}
