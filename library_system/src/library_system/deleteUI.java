package library_system;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.TextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.UIManager;

public class deleteUI extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldBookID;
	private JTextField textFieldMemID;
	private String bookID = "";
	private String memberID = "";
	
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					deleteUI frame = new deleteUI();
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
	
	
	
	
	
	public Boolean validate_TextFeild(JTextField textFieldBookID2) { //--------------------check the fields is filled----------------------------------------------
		boolean textFeildState = false;
				
				
		String textFieldCheck = textFieldBookID2.getText();
				
		if (textFieldCheck.equals("")) {
						
			textFeildState = false;
					
			JOptionPane.showMessageDialog(null, "Search Box ID Required !");
			textFieldBookID2.requestFocus();
		
		}
		else {
			textFeildState = true;
					
			System.out.println("Pass the validation ");

		}
					
				
		return textFeildState;
	}//----------------------------------------------------------------------------------validate_TextFeild Method Ends--------------------------------------
	
	
	
	public Boolean bookValidationBoolean() {//this check if the book is available or not--------------(Validation Part)
		
		boolean statusBook = false;
		bookID = textFieldBookID.getText();
		
		try {
			
        	String queryString3 = "SELECT \r\n"
        			+ "book_ID \r\n"
        			+ "FROM library_system.books\r\n"
        			+ "WHERE `book_ID` = '"+bookID+"'\r\n"
        			+ ";\r\n"
        			+ "";
			
			//System.out.println(queryString); //use this to check the errors in query
			//System.out.println("");System.out.println("");
			
			PreparedStatement pStatement = connection.prepareStatement(queryString3);
			ResultSet rsResultset = pStatement.executeQuery(); 
			
			if (rsResultset.next() == false) {
		        System.out.println("ResultSet in empty in Java");
		        JOptionPane.showMessageDialog(null, "Book Is Not Availble !");
		        statusBook = false;
		        
		      } else {
		    	  statusBook = true;
		      }

			pStatement.close();
			rsResultset.close();
        	
        	
		} catch (Exception e2) {
			// TODO: handle exception
			System.out.println(e2);
		}
		
		return statusBook;
        
	}//---------------------------------------------------------------------------END of Book validation part
	
	
	
	public Boolean memberValidationBoolean() {//this check if the Member is available or not--------------(Validation Part)
		
		boolean statusMember = false;
		memberID = textFieldMemID.getText();
		
		try {
			
        	String queryString4 = "SELECT \r\n"
        			+ "Mem_ID\r\n"
        			+ "FROM members\r\n"
        			+ "WHERE Mem_ID = '"+memberID+"'  \r\n"
        			+ ";";
			
			//System.out.println(queryString); //use this to check the errors in query
			//System.out.println("");System.out.println("");
			
			PreparedStatement pStatement = connection.prepareStatement(queryString4);
			ResultSet rsResultset = pStatement.executeQuery(); 
			
			if (rsResultset.next() == false) {
		        System.out.println("ResultSet in empty in Java");
		        JOptionPane.showMessageDialog(null, "Member Is Not Availble !");
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
        
	}//---------------------------------------------------------------------------END of Member validation part
			
			
			
	
	public void showBookTable() {// Show the data about typed book id before delete it
		
		bookID = textFieldBookID.getText();
		
		try { 
			
			
			
			String queryString = "SELECT \r\n"
					+ "book_ID AS \"Book ID\",\r\n"
					+ "B_name AS \"Book Name\",\r\n"
					+ "publish_year AS \"Published Year\",\r\n"
					+ "A.author_id AS \"Author ID\",\r\n"
					+ "A_F_name AS \"Author First Name\",\r\n"
					+ "A_L_name AS \"Author Last Name\",\r\n"
					+ "Other_details AS \"Other Details\",\r\n"
					+ "state AS \"Book States\"\r\n"
					+ "FROM books B\r\n"
					+ "JOIN author A\r\n"
					+ "	ON B.Author_ID = A.Author_id\r\n"
					+ "WHERE book_ID LIKE "+bookID+" \r\n"
					+ "ORDER BY B_name;";
			//System.out.println(queryString); //use this to check the errors in query
			//System.out.println("");System.out.println("");
			
			PreparedStatement pStatement = connection.prepareStatement(queryString);
			ResultSet rsResultset = pStatement.executeQuery(); 
			table.setModel(DbUtils.resultSetToTableModel(rsResultset));
			
			
			pStatement.close();
			rsResultset.close();
			
		} catch (Exception e2) {
			// TODO: handle exception
			System.out.println(e2);
		}
	}
	
	public void showMemberTable() {
		
		memberID = textFieldMemID.getText();
		
		try { // Show the data in typed book id before delete it
			
			
			
			String queryString = "SELECT \r\n"
					+ "Mem_ID AS \"Member ID\",\r\n"
					+ "F_name AS \"First Name\",\r\n"
					+ "L_name AS \"Last Name\",\r\n"
					+ "Contact_no AS \"Contact Number\" \r\n"
					+ "FROM \r\n"
					+ "library_system.members\r\n"
					+ "WHERE Mem_ID LIKE "+memberID+"\r\n"
					+ "ORDER BY Mem_ID\r\n"
					+ ";";
			//System.out.println(queryString); //use this to check the errors in query
			//System.out.println("");System.out.println("");
			
			PreparedStatement pStatement = connection.prepareStatement(queryString);
			ResultSet rsResultset = pStatement.executeQuery(); 
			table.setModel(DbUtils.resultSetToTableModel(rsResultset));
			
			
			pStatement.close();
			rsResultset.close();
			
		} catch (Exception e2) {
			// TODO: handle exception
			System.out.println(e2);
		}
	}
	
	
	public void deleteBook() { //book Deleting Method
		
		bookID = textFieldBookID.getText();
		
		try {
			
			
			
			//add data in to Receive table
			String queryString2 = "DELETE FROM `library_system`.`books` \r\n"
					+ "WHERE (\r\n"
					+ "`book_ID` = '"+bookID+"' \r\n"
					+ ");";
            //System.out.println(queryString2); //use this to check the errors in query
            //System.out.println(""); System.out.println("");
			

			PreparedStatement pStatement2 = connection.prepareStatement(queryString2);
			pStatement2.executeUpdate(queryString2);
			//JOptionPane.showMessageDialog(null, "Book Lending data Saved Successfully.");
			
			pStatement2.close();
			JOptionPane.showMessageDialog(null, "Okay! Book ID : "+bookID+" data Delete From server !" );
            
		} catch (Exception e2) {
			// TODO: handle exception
			System.out.println(e2);
			JOptionPane.showMessageDialog(null, "Book ID Is Not Availble !");
			
			
		}
		
		
	}//book Deleting Method ENDs
	
	
	
	public void deleteMember() { //Member Deleting Method
		
		memberID = textFieldMemID.getText();
		
		try {
			
			
			
			//add data in to Receive table
			String queryString2 = "DELETE FROM `library_system`.`members` \r\n"
					+ "WHERE (\r\n"
					+ "`Mem_ID` = '"+memberID+"'  \r\n"
					+ ");";
            //System.out.println(queryString2); //use this to check the errors in query
            //System.out.println(""); System.out.println("");
			

			PreparedStatement pStatement2 = connection.prepareStatement(queryString2);
			pStatement2.executeUpdate(queryString2);
			//JOptionPane.showMessageDialog(null, "Book Lending data Saved Successfully.");
			
			pStatement2.close();
			JOptionPane.showMessageDialog(null, "Okay! Member ID : "+memberID+" data Delete From  server !" );
            
		} catch (Exception e2) {
			// TODO: handle exception
			System.out.println(e2);
			JOptionPane.showMessageDialog(null, "Member ID Is Not Availble !");
			
			
		}
		
		
	}//Member Deleting Method ENDs
	
	
	/**
	 * Create the frame.
	 */
	public deleteUI() {
		
		connection = sqlConnection.dbConnector(); //connect with sql server
		
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
				
				JLabel lblbookID = new JLabel("Book ID *   :");
				lblbookID.setFont(new Font("Trebuchet MS", Font.BOLD, 19));
				lblbookID.setBounds(50, 213, 126, 27);
				backgroundpanel.add(lblbookID);
				
				textFieldBookID = new JTextField();
				textFieldBookID.setFont(new Font("Trebuchet MS", Font.PLAIN, 19));
				textFieldBookID.setColumns(10);
				textFieldBookID.setBackground(SystemColor.inactiveCaptionBorder);
				textFieldBookID.setBounds(186, 213, 246, 27);
				backgroundpanel.add(textFieldBookID);
				
				JPanel panelBookDelete = new JPanel();
				panelBookDelete.addMouseListener(new MouseAdapter() { //--------BOOK DELETE BUTTON ----------------------------
					@Override
					public void mouseEntered(MouseEvent e) {
						
						panelBookDelete.setBackground(new Color(255, 102, 102));
					}
					@Override
					public void mouseExited(MouseEvent e) {
						panelBookDelete.setBackground(UIManager.getColor("CheckBox.background"));
					}
					@Override
					public void mouseClicked(MouseEvent e) {
						
						bookID = textFieldBookID.getText();
						
						int result = JOptionPane.showConfirmDialog(null,
			                    "Are your Sure! Do you want to Delete This Book ID : "+bookID+"  ?",
			                    "Confromation Message !",
			                    JOptionPane.YES_NO_OPTION,
			                    JOptionPane.WARNING_MESSAGE
			                    );
						
						if (result == JOptionPane.YES_OPTION) {//take the confirmation from the user
							
							
							
							if (validate_TextFeild(textFieldBookID)) {
								
								if (bookValidationBoolean()) {
									
									System.out.println("book in the System");
									deleteBook();
								}
							}else {
								JOptionPane.showMessageDialog(null, "Somthing went Wrong Try Again Later");
							}
							
						}

						
						
						
					}
				}); //--------------------------------------------------------BOOK DELETE BUTTON END----------------------------
				
				
				
				panelBookDelete.setLayout(null);
				panelBookDelete.setBackground(SystemColor.controlHighlight);
				panelBookDelete.setBounds(783, 185, 252, 55);
				backgroundpanel.add(panelBookDelete);
				
				JLabel lblDelete = new JLabel("Delete");
				lblDelete.setFont(new Font("Trebuchet MS", Font.BOLD, 27));
				lblDelete.setBounds(75, 11, 101, 33);
				panelBookDelete.add(lblDelete);
				
				JPanel panelBookIDSearch = new JPanel();
				panelBookIDSearch.addMouseListener(new MouseAdapter() { //--------BOOK SEARCH BUTTON ----------------------------
					@Override
					public void mouseEntered(MouseEvent e) {
						setColouronMouse(panelBookIDSearch);
					}
					@Override
					public void mouseExited(MouseEvent e) {
						panelBookIDSearch.setBackground(SystemColor.controlHighlight);
						
					}
										
					@Override
					public void mouseClicked(MouseEvent e) {
						
						Boolean check = validate_TextFeild(textFieldBookID);
						
						if (check) {
							
							showBookTable();
						}
					}
				});//--------------------------------------------------------BOOK SEARCH BUTTON END----------------------------
				
				
				
				panelBookIDSearch.setLayout(null);
				panelBookIDSearch.setBackground(SystemColor.menu);
				panelBookIDSearch.setBounds(477, 185, 252, 55);
				backgroundpanel.add(panelBookIDSearch);
				
				JLabel lblSearch = new JLabel("Search");
				lblSearch.setFont(new Font("Trebuchet MS", Font.PLAIN, 20));
				lblSearch.setBounds(93, 11, 61, 24);
				panelBookIDSearch.add(lblSearch);
				
				JLabel lblMemberId = new JLabel("Member ID *   :");
				lblMemberId.setFont(new Font("Trebuchet MS", Font.BOLD, 19));
				lblMemberId.setBounds(50, 327, 138, 27);
				backgroundpanel.add(lblMemberId);
				
				textFieldMemID = new JTextField();
				textFieldMemID.setFont(new Font("Trebuchet MS", Font.PLAIN, 19));
				textFieldMemID.setColumns(10);
				textFieldMemID.setBackground(SystemColor.inactiveCaptionBorder);
				textFieldMemID.setBounds(186, 327, 246, 27);
				backgroundpanel.add(textFieldMemID);
				
				JPanel panelMemIDSearch = new JPanel();
				panelMemIDSearch.addMouseListener(new MouseAdapter() {//--------MEMBER SEARCH BUTTON ----------------------------
					@Override
					public void mouseEntered(MouseEvent e) {
						setColouronMouse(panelMemIDSearch);
					}
					@Override
					public void mouseExited(MouseEvent e) {
						panelMemIDSearch.setBackground(SystemColor.controlHighlight);
						
					}
					
					@Override
					public void mouseClicked(MouseEvent e) {
						
						Boolean check = validate_TextFeild(textFieldMemID);
						
						if (check) {
							showMemberTable();
						}
					}
				});//--------------------------------------------------------MEMBER SEARCH BUTTON END----------------------------
				
				
				panelMemIDSearch.setLayout(null);
				panelMemIDSearch.setBackground(SystemColor.menu);
				panelMemIDSearch.setBounds(477, 299, 252, 55);
				backgroundpanel.add(panelMemIDSearch);
				
				JLabel lblSearch_1 = new JLabel("Search");
				lblSearch_1.setFont(new Font("Trebuchet MS", Font.PLAIN, 20));
				lblSearch_1.setBounds(93, 11, 61, 24);
				panelMemIDSearch.add(lblSearch_1);
				
				JPanel panelMemDelete = new JPanel();
				panelMemDelete.addMouseListener(new MouseAdapter() {//--------MEMBER DELETE BUTTON ----------------------------
					@Override
					public void mouseEntered(MouseEvent e) {
						
						panelMemDelete.setBackground(new Color(255, 102, 102));
					}
					@Override
					public void mouseExited(MouseEvent e) {
						panelMemDelete.setBackground(UIManager.getColor("CheckBox.background"));
					}
					@Override
					public void mouseClicked(MouseEvent e) {
						
						memberID = textFieldMemID.getText();
						
						int result = JOptionPane.showConfirmDialog(null,
			                    "Are your Sure! Do you want to Delete This Member ID : "+memberID+"  ?",
			                    "Confromation Message !",
			                    JOptionPane.YES_NO_OPTION,
			                    JOptionPane.WARNING_MESSAGE
			                    );
						
						if (result == JOptionPane.YES_OPTION) {//take the confirmation from the user
							
							
							
							if (validate_TextFeild(textFieldMemID)) {
								
								if (memberValidationBoolean()) {
									
									System.out.println("Member in the System");
									deleteMember();;
								}
							}else {
								JOptionPane.showMessageDialog(null, "Somthing went Wrong Try Again Later");
							}
							
						}else {
							JOptionPane.showMessageDialog(null, "Okay Data not deleted ");
						}
						
						
						
						
					}
				});//--------------------------------------------------------MEMBER DELETE BUTTON END----------------------------
				
				
				panelMemDelete.setLayout(null);
				panelMemDelete.setBackground(SystemColor.controlHighlight);
				panelMemDelete.setBounds(783, 299, 252, 55);
				backgroundpanel.add(panelMemDelete);
				
				JLabel lblDelete_1 = new JLabel("Delete");
				lblDelete_1.setFont(new Font("Trebuchet MS", Font.BOLD, 27));
				lblDelete_1.setBounds(76, 11, 101, 33);
				panelMemDelete.add(lblDelete_1);
				
				JLabel lblNewLabel = new JLabel("Warning This action will permanently Delete the Data Record From the Database");
				lblNewLabel.setFont(new Font("Trebuchet MS", Font.BOLD, 13));
				lblNewLabel.setBounds(371, 435, 506, 27);
				backgroundpanel.add(lblNewLabel);
				
				JScrollPane scrollPane = new JScrollPane();
				scrollPane.setBounds(10, 583, 1261, 101);
				backgroundpanel.add(scrollPane);
				
				table = new JTable();
				scrollPane.setViewportView(table);
				//Back to Home BUtton ENDS 
				
				
				
	}
}
