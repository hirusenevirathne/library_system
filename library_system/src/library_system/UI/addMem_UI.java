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
import library_system.memberUI;
import library_system.sqlConnection;
import net.proteanit.sql.DbUtils;

public class addMem_UI extends JFrame {

	private JPanel contentPane;
	private JTextField textMemberID;
	private JTextField textMFname;
	private JTextField textMLname;
	
	private String memberID = "";
	private String mFname = "";
	private String mLname = "";
	private String contactNo = "";

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
	private JTextField textContactNo;
	
	public void setColouronMouse( JPanel panel) { //create a method to change button color when mouse is on it
		panel.setBackground(new java.awt.Color(115, 163, 239));		
	}
	public void reSetColouronMouse( JPanel panel) {//create a method to change original button color when mouse is not on it
		panel.setBackground(SystemColor.controlHighlight);		
	}
	
	public void showTable() { //method for show the table 
		try {
			
			String queryString = "SELECT \r\n"
					+ "Mem_ID AS 'Member ID',\r\n"
					+ "F_name AS 'Member First Name',\r\n"
					+ "L_name AS 'Member Last Name',\r\n"
					+ "Contact_no AS 'Contact Number'\r\n"
					+ "FROM library_system.members;";
			
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
			memberID = textMemberID.getText();
			mFname = textMFname.getText();
			mLname = textMLname.getText();
			contactNo = textContactNo.getText();
			
			String queryString = "INSERT INTO `library_system`.`members` (\r\n"
					+ "`Mem_ID`, \r\n"
					+ "`F_name`, \r\n"
					+ "`L_name`, \r\n"
					+ "`Contact_no`\r\n"
					+ ") \r\n"
					+ "VALUES (\r\n"
					+ "'"+memberID+"', \r\n"
					+ "'"+mFname+"', \r\n"
					+ "'"+mLname+"', \r\n"
					+ "'"+contactNo+"'\r\n"
					+ ");";
			System.out.println(queryString); //use this to check the errors in query
			
			
			
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
		
		if (textMemberID.getText().equals("")) {
			
			textFeildState = false;
			JOptionPane.showMessageDialog(null, "Member ID Required !");
			textMemberID.requestFocus();
			
		}else if (textMFname.getText().equals("")) {
			
			textFeildState = false;
			JOptionPane.showMessageDialog(null, "Member First Name Required !");
			textMFname.requestFocus();
			
		}else if (textMLname.getText().equals("")) {
			
			textFeildState = false;
			JOptionPane.showMessageDialog(null, "Member Last Name Required !");
			textMLname.requestFocus();
			
		}else if (textContactNo.getText().equals("")) {
			
			textFeildState = false;
			JOptionPane.showMessageDialog(null, "Contact Number Required !");
			textContactNo.requestFocus();
			
		}
		else {
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
						new memberUI().show();
						dispose(); //close the currant window
					}
				});
				homebtn.setBackground(SystemColor.menu);
				homebtn.setBounds(1110, 70, 150, 53);
				topblue_panel.add(homebtn);
				homebtn.setLayout(null);
				
				JLabel lblbacktohome = new JLabel("Back to Member");
				lblbacktohome.setBounds(36, 15, 104, 20);
				lblbacktohome.setFont(new Font("Tahoma", Font.BOLD, 13));
				homebtn.add(lblbacktohome);
				
				JLabel lblbackico = new JLabel("");
				lblbackico.setIcon(new ImageIcon(homeUI.class.getResource("/main/images/back small.png")));
				lblbackico.setBounds(10, 11, 32, 31);
				homebtn.add(lblbackico);
				
				JLabel lblmemberID = new JLabel("Member ID *   :");
				lblmemberID.setFont(new Font("Trebuchet MS", Font.BOLD, 19));
				lblmemberID.setBounds(75, 184, 184, 27);
				backgroundpanel.add(lblmemberID);
				
				textMemberID = new JTextField();
				textMemberID.setFont(new Font("Trebuchet MS", Font.PLAIN, 19));
				textMemberID.setColumns(10);
				textMemberID.setBackground(SystemColor.inactiveCaptionBorder);
				textMemberID.setBounds(299, 184, 246, 27);
				backgroundpanel.add(textMemberID);
				
				JLabel lblAuthorFirstName = new JLabel("Member First Name *   :");
				lblAuthorFirstName.setFont(new Font("Trebuchet MS", Font.BOLD, 19));
				lblAuthorFirstName.setBounds(75, 239, 224, 27);
				backgroundpanel.add(lblAuthorFirstName);
				
				textMFname = new JTextField();
				textMFname.setFont(new Font("Trebuchet MS", Font.PLAIN, 19));
				textMFname.setColumns(10);
				textMFname.setBackground(SystemColor.inactiveCaptionBorder);
				textMFname.setBounds(299, 239, 246, 27);
				backgroundpanel.add(textMFname);
				
				textMLname = new JTextField();
				textMLname.setFont(new Font("Trebuchet MS", Font.PLAIN, 19));
				textMLname.setColumns(10);
				textMLname.setBackground(SystemColor.inactiveCaptionBorder);
				textMLname.setBounds(299, 294, 246, 27);
				backgroundpanel.add(textMLname);
				
				JLabel lblAuthorLastName = new JLabel("Member Last Name *   :");
				lblAuthorLastName.setFont(new Font("Trebuchet MS", Font.BOLD, 19));
				lblAuthorLastName.setBounds(75, 294, 224, 27);
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
							showTable();
						}
						
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
						
						
						textMemberID.setText("");
						textMFname.setText("");
						textMLname.setText("");
						textContactNo.setText("");
						
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
				lblNewLabel.setBounds(613, 339, 361, 33);
				backgroundpanel.add(lblNewLabel);
				
				JScrollPane scrollPane = new JScrollPane();
				scrollPane.setBounds(28, 410, 1225, 260);
				backgroundpanel.add(scrollPane);
				
				table = new JTable();
				scrollPane.setViewportView(table);
				
				textContactNo = new JTextField();
				textContactNo.setFont(new Font("Trebuchet MS", Font.PLAIN, 19));
				textContactNo.setColumns(10);
				textContactNo.setBackground(SystemColor.inactiveCaptionBorder);
				textContactNo.setBounds(299, 342, 246, 27);
				backgroundpanel.add(textContactNo);
				
				JLabel lblContactNumber = new JLabel("Contact Number*    :");
				lblContactNumber.setFont(new Font("Trebuchet MS", Font.BOLD, 19));
				lblContactNumber.setBounds(75, 342, 208, 27);
				backgroundpanel.add(lblContactNumber);
				showTable();
				//Back to Home BUtton ENDS
	}
}
