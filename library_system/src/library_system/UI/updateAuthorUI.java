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

import library_system.homeUI;
import library_system.home_menu;
import library_system.sqlConnection;
import library_system.updateUI;

public class updateAuthorUI extends JFrame {

	private JPanel contentPane;
	Connection connection = null; //create the connection variable
	private JTextField textField_ID;
	private JTextField textField_Fname;
	private JTextField textField_Lname;
	private JTable table;
	private String authorID = "";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					updateAuthorUI frame = new updateAuthorUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
	
	public Boolean memberValidationBoolean() {//this check if the Author is available or not--------------(Validation Part)
		
		boolean statusMember = false;
		authorID = textField_ID.getText();
		
		try {
			
        	String queryString4 = "SELECT \r\n"
        			+ "Author_id\r\n"
        			+ "FROM library_system.author\r\n"
        			+ "WHERE Author_id = '"+authorID+"'  \r\n"
        			+ ";";
			
			//System.out.println(queryString); //use this to check the errors in query
			//System.out.println("");System.out.println("");
			
			PreparedStatement pStatement = connection.prepareStatement(queryString4);
			ResultSet rsResultset = pStatement.executeQuery(); 
			
			if (rsResultset.next() == false) {
		        System.out.println("ResultSet in empty in Java");
		        JOptionPane.showMessageDialog(null, "Author Is Not Availble !");
		        statusMember = false;
		        
		      } else {
		    	  statusMember = true;
		      }

			pStatement.close();
			rsResultset.close();
        	
        	
		} catch (Exception e2) {
			// TODO: handle exception
			System.out.println(e2);
		}
		
		return statusMember;
        
	}//---------------------------------------------------------------------------END of Author validation part
	

	/**
	 * Create the frame.
	 */
	public updateAuthorUI() {
		

		
		connection = sqlConnection.dbConnector(); //connect with sql server
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1297, 734);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel backgroundpanel = new JPanel();
		backgroundpanel.setBackground(SystemColor.menu);
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
						new home_menu().show();
						dispose(); //close the currant window
					}
				});
				homebtn.setBackground(SystemColor.menu);
				homebtn.setBounds(1110, 70, 150, 53);
				topblue_panel.add(homebtn);
				homebtn.setLayout(null);
				
				JLabel lblbacktohome = new JLabel("Back to Home");
				lblbacktohome.setBounds(44, 15, 88, 20);
				lblbacktohome.setFont(new Font("Tahoma", Font.BOLD, 13));
				homebtn.add(lblbacktohome);
				
				JLabel lblbackico = new JLabel("");
				lblbackico.setIcon(new ImageIcon(homeUI.class.getResource("/main/images/back small.png")));
				lblbackico.setBounds(10, 11, 32, 31);
				homebtn.add(lblbackico);
				
				JPanel panelbackground = new JPanel();
				panelbackground.setLayout(null);
				panelbackground.setBackground(SystemColor.menu);
				panelbackground.setBounds(0, 133, 1281, 60);
				backgroundpanel.add(panelbackground);
				
				JPanel panelAuthorSelectUpdate = new JPanel();
				panelAuthorSelectUpdate.setLayout(null);
				panelAuthorSelectUpdate.setBackground(SystemColor.inactiveCaptionBorder);
				panelAuthorSelectUpdate.setBounds(450, 11, 380, 49);
				panelbackground.add(panelAuthorSelectUpdate);
				
				JLabel lblAuthors = new JLabel("Authors");
				lblAuthors.setFont(new Font("Trebuchet MS", Font.BOLD, 22));
				lblAuthors.setBounds(161, 11, 87, 27);
				panelAuthorSelectUpdate.add(lblAuthors);
				
				JPanel panelMembersSelectUpdate = new JPanel();
				panelMembersSelectUpdate.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseEntered(MouseEvent e) {
						panelMembersSelectUpdate.setBackground(new java.awt.Color(115, 163, 239));
					}
					@Override
					public void mouseExited(MouseEvent e) {
						panelMembersSelectUpdate.setBackground(SystemColor.controlHighlight);
					}
					@SuppressWarnings("deprecation")
					@Override
					public void mouseClicked(MouseEvent e) {
						
						new updateMemberUI().show();
						dispose();
					}
				});
				panelMembersSelectUpdate.setLayout(null);
				panelMembersSelectUpdate.setBackground(SystemColor.controlHighlight);
				panelMembersSelectUpdate.setBounds(867, 11, 380, 49);
				panelbackground.add(panelMembersSelectUpdate);
				
				JLabel lblMembers = new JLabel("Members");
				lblMembers.setFont(new Font("Trebuchet MS", Font.BOLD, 22));
				lblMembers.setBounds(152, 11, 92, 27);
				panelMembersSelectUpdate.add(lblMembers);
				
				JPanel panelBooksSelectUpdate = new JPanel();
				panelBooksSelectUpdate.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseEntered(MouseEvent e) {
						panelBooksSelectUpdate.setBackground(new java.awt.Color(115, 163, 239));
					}
					@Override
					public void mouseExited(MouseEvent e) {
						panelBooksSelectUpdate.setBackground(SystemColor.controlHighlight);
					}
					@SuppressWarnings("deprecation")
					@Override
					public void mouseClicked(MouseEvent e) {
						
						new updateUI().show();
						dispose();
					}
				});
				panelBooksSelectUpdate.setLayout(null);
				panelBooksSelectUpdate.setBackground(SystemColor.controlHighlight);
				panelBooksSelectUpdate.setBounds(36, 11, 380, 49);
				panelbackground.add(panelBooksSelectUpdate);
				
				JLabel lblBooks = new JLabel("Books");
				lblBooks.setFont(new Font("Trebuchet MS", Font.BOLD, 22));
				lblBooks.setBounds(145, 11, 63, 27);
				panelBooksSelectUpdate.add(lblBooks);
				
				JPanel panelbody = new JPanel();
				panelbody.setLayout(null);
				panelbody.setBackground(SystemColor.inactiveCaptionBorder);
				panelbody.setBounds(10, 190, 1261, 505);
				backgroundpanel.add(panelbody);
				
				textField_ID = new JTextField();
				textField_ID.setFont(new Font("Trebuchet MS", Font.PLAIN, 19));
				textField_ID.setColumns(10);
				textField_ID.setBackground(SystemColor.inactiveCaptionBorder);
				textField_ID.setBounds(168, 43, 246, 27);
				panelbody.add(textField_ID);
				
				JLabel lblbookID = new JLabel("Author ID *   :");
				lblbookID.setFont(new Font("Trebuchet MS", Font.BOLD, 19));
				lblbookID.setBounds(32, 43, 126, 27);
				panelbody.add(lblbookID);
				
				JPanel panelSearch = new JPanel();
				panelSearch.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseEntered(MouseEvent e) {
						panelSearch.setBackground(new java.awt.Color(115, 163, 239));
					}
					@Override
					public void mouseExited(MouseEvent e) {
						panelSearch.setBackground(SystemColor.controlHighlight);
					}
				});
				panelSearch.setLayout(null);
				panelSearch.setBackground(SystemColor.menu);
				panelSearch.setBounds(540, 43, 252, 55);
				panelbody.add(panelSearch);
				
				JLabel lblSearch = new JLabel("Search");
				lblSearch.setFont(new Font("Trebuchet MS", Font.PLAIN, 20));
				lblSearch.setBounds(93, 11, 61, 24);
				panelSearch.add(lblSearch);
				
				JPanel panelUpdate = new JPanel();
				panelUpdate.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseEntered(MouseEvent e) {
						panelUpdate.setBackground(SystemColor.info);
					}
					@Override
					public void mouseExited(MouseEvent e) {
						panelUpdate.setBackground(UIManager.getColor("CheckBox.background"));
					}
				});
				panelUpdate.setLayout(null);
				panelUpdate.setBackground(SystemColor.controlHighlight);
				panelUpdate.setBounds(891, 43, 252, 55);
				panelbody.add(panelUpdate);
				
				JLabel lblUpdate = new JLabel("Update");
				lblUpdate.setFont(new Font("Trebuchet MS", Font.BOLD, 27));
				lblUpdate.setBounds(75, 11, 101, 33);
				panelUpdate.add(lblUpdate);
				
				textField_Fname = new JTextField();
				textField_Fname.setFont(new Font("Trebuchet MS", Font.PLAIN, 19));
				textField_Fname.setColumns(10);
				textField_Fname.setBackground(SystemColor.inactiveCaptionBorder);
				textField_Fname.setBounds(238, 178, 246, 27);
				panelbody.add(textField_Fname);
				
				JLabel lblBookName = new JLabel("Author First Name   :");
				lblBookName.setFont(new Font("Trebuchet MS", Font.BOLD, 19));
				lblBookName.setBounds(32, 178, 207, 27);
				panelbody.add(lblBookName);
				
				textField_Lname = new JTextField();
				textField_Lname.setFont(new Font("Trebuchet MS", Font.PLAIN, 19));
				textField_Lname.setColumns(10);
				textField_Lname.setBackground(SystemColor.inactiveCaptionBorder);
				textField_Lname.setBounds(238, 228, 246, 27);
				panelbody.add(textField_Lname);
				
				JLabel lblPublishYear = new JLabel("Author Last Name   :");
				lblPublishYear.setFont(new Font("Trebuchet MS", Font.BOLD, 19));
				lblPublishYear.setBounds(32, 228, 196, 27);
				panelbody.add(lblPublishYear);
				
				JScrollPane scrollPane = new JScrollPane();
				scrollPane.setBounds(10, 374, 1241, 120);
				panelbody.add(scrollPane);
				
				table = new JTable();
				scrollPane.setViewportView(table);
				
				JLabel lblNewLabel = new JLabel("Add New Data Here");
				lblNewLabel.setFont(new Font("Trebuchet MS", Font.BOLD, 17));
				lblNewLabel.setBounds(228, 135, 169, 30);
				panelbody.add(lblNewLabel);
				
				JLabel lblNewLabel_1 = new JLabel("");
				lblNewLabel_1.setIcon(new ImageIcon(updateUI.class.getResource("/main/images/icons8-below-32.png")));
				lblNewLabel_1.setBounds(192, 135, 32, 30);
				panelbody.add(lblNewLabel_1);
				//Back to Home BUtton ENDS 
	}

}
