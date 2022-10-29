package library_system;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import com.toedter.calendar.JDateChooser;

import library_system.UI.updateAuthorUI;
import library_system.UI.updateMemberUI;
import net.proteanit.sql.DbUtils;

import javax.swing.JTable;

public class updateUI extends JFrame {

	private JPanel contentPane;
	Connection connection = null; //create the connection variable
	private JTextField textField_ID;
	private JTextField textField_Name;
	private JTextField textField_Year;
	private JTextField textField_Details;
	private JTable table;
	private String bookID = "";
	private String bName = "";
	private String bYear = "";
	private String bDetails = "";
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					updateUI frame = new updateUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void showBookTable() {// Show the data about typed book id before delete it
		
		bookID = textField_ID.getText();
		
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
	
	
	public Boolean bookValidationBoolean() {//this check if the book is available or not--------------(Validation Part)
		
		boolean statusBook = false;
		bookID = textField_ID.getText();
		
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
	
	
	public void updateData(String sqlQuary) {//-------------method for run SQL quarry
		
		bookID = textField_ID.getText();
		
		try {
			
			PreparedStatement pStatement2 = connection.prepareStatement(sqlQuary);
			pStatement2.executeUpdate(sqlQuary);
			//JOptionPane.showMessageDialog(null, "Book Lending data Saved Successfully.");
			
			pStatement2.close();
			JOptionPane.showMessageDialog(null, "Okay! Book ID : "+bookID+" data Updated From server !" );
            
		} catch (Exception e2) {
			// TODO: handle exception
			System.out.println(e2);
			JOptionPane.showMessageDialog(null, "Book ID Is Not Availble !");
			
			
		}
		
	}//------------------------------------------------------ END of SQL quarry run Method

	
	
	public String sqlQuaryString() { //find the correct sql Query
		
		boolean nameAvailabe = textField_Name.getText().length()!=0 ;
		boolean yearAvailabe = textField_Year.getText().length()!=0 ;
		boolean detailsAvailabe = textField_Details.getText().length()!=0 ;
		
		
		bookID = textField_ID.getText();
		String sqlQuery ="";
		
		if (nameAvailabe && yearAvailabe && detailsAvailabe) {
			
			bName = textField_Name.getText();
			bYear = textField_Year.getText();
			bDetails = textField_Details.getText();
			
			sqlQuery = "UPDATE `library_system`.`books` \r\n"
					+ "SET \r\n"
					+ "`B_name` = '"+bName+"', \r\n"
					+ "`publish_year` = '"+bYear+"', \r\n"
					+ "`Other_details` = '"+bDetails+"' \r\n"
					+ "WHERE (\r\n"
					+ "`book_ID` = '"+bookID+"'\r\n"
					+ ");\r\n"
					+ "";
			return sqlQuery;
			
			
		}else if (nameAvailabe && yearAvailabe ) {
			bName = textField_Name.getText();
			bYear = textField_Year.getText();
			
			sqlQuery = "UPDATE `library_system`.`books` \r\n"
					+ "SET \r\n"
					+ "`B_name` = '"+bName+"', \r\n"
					+ "`publish_year` = '"+bYear+"' \r\n"
					+ "WHERE (\r\n"
					+ "`book_ID` = '"+bookID+"'\r\n"
					+ ");";
			return sqlQuery;
			
		}else if (nameAvailabe && detailsAvailabe) {
			bName = textField_Name.getText();
			bDetails = textField_Details.getText();
			
			sqlQuery = "UPDATE `library_system`.`books` \r\n"
					+ "SET \r\n"
					+ "`B_name` = '"+bName+"', \r\n"
					+ "`Other_details` = '"+bDetails+"' \r\n"
					+ "WHERE (\r\n"
					+ "`book_ID` = '"+bookID+"'\r\n"
					+ ");";
			return sqlQuery;
			
		}else if (yearAvailabe && detailsAvailabe) {
			bYear = textField_Year.getText();
			bDetails = textField_Details.getText();
			
			sqlQuery = "UPDATE `library_system`.`books` \r\n"
					+ "SET \r\n"
					+ "`publish_year` = '"+bYear+"', \r\n"
					+ "`Other_details` = '"+bDetails+"' \r\n"
					+ "WHERE (\r\n"
					+ "`book_ID` = '"+bookID+"'\r\n"
					+ ");";
			return sqlQuery;
			
		}else if (nameAvailabe) {
			bName = textField_Name.getText();
			
			sqlQuery = "UPDATE `library_system`.`books` \r\n"
					+ "SET `B_name` = '"+bName+"' \r\n"
					+ "WHERE (\r\n"
					+ "`book_ID` = '"+bookID+"'\r\n"
					+ ");";
			return sqlQuery;
			
		}else if (yearAvailabe) {
			bYear = textField_Year.getText();
			
			sqlQuery = "UPDATE `library_system`.`books` \r\n"
					+ "SET `publish_year` = '"+bYear+"' \r\n"
					+ "WHERE (\r\n"
					+ "`book_ID` = '"+bookID+"'\r\n"
					+ ");";
			return sqlQuery;
			
			
		}else if (detailsAvailabe) {
			bDetails = textField_Details.getText();
			
			sqlQuery = "UPDATE `library_system`.`books` \r\n"
					+ "SET `Other_details` = '"+bDetails+"' \r\n"
					+ "WHERE (\r\n"
					+ "`book_ID` = '"+bookID+"'\r\n"
					+ ");";
			return sqlQuery;
			
			
		}else {
			return sqlQuery;
		}
		
		
	}

	/**
	 * Create the frame.
	 */
	public updateUI() {
		
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
				panelAuthorSelectUpdate.addMouseListener(new MouseAdapter() {//Author Tab Selection--------------------------
					@Override
					public void mouseEntered(MouseEvent e) {
						panelAuthorSelectUpdate.setBackground(new java.awt.Color(115, 163, 239));
					}
					@Override
					public void mouseExited(MouseEvent e) {
						panelAuthorSelectUpdate.setBackground(SystemColor.controlHighlight);
					}
					@SuppressWarnings("deprecation")
					@Override
					public void mouseClicked(MouseEvent e) {
						
						new updateAuthorUI().show();
						dispose();
					}
				});
				
				
				
				
				panelAuthorSelectUpdate.setLayout(null);
				panelAuthorSelectUpdate.setBackground(SystemColor.controlHighlight);
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
				panelBooksSelectUpdate.setLayout(null);
				panelBooksSelectUpdate.setBackground(SystemColor.inactiveCaptionBorder);
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
				
				JLabel lblbookID = new JLabel("Book ID *   :");
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
					@Override
					public void mouseClicked(MouseEvent e) {
						
						if (bookValidationBoolean()) {
							
							showBookTable();
							
						}
						
						
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
					@Override
					public void mouseClicked(MouseEvent e) {
						
						bookID = textField_ID.getText();
						
						if (bookValidationBoolean()) {
							
							int result = JOptionPane.showConfirmDialog(null,
				                    "Are your Sure! Do you want to Update This Book ID : "+bookID+"  ?",
				                    "Confromation Message !",
				                    JOptionPane.YES_NO_OPTION,
				                    JOptionPane.WARNING_MESSAGE
				                    );
							
							if (result == JOptionPane.YES_OPTION) {//take the confirmation from the user
								
								//System.out.println(sqlQuaryString());
								
								updateData(sqlQuaryString());
								
							}else {
								JOptionPane.showMessageDialog(null, "Okay Data not Updated !");
							}
							
						}
						showBookTable();
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
				
				textField_Name = new JTextField();
				textField_Name.setFont(new Font("Trebuchet MS", Font.PLAIN, 19));
				textField_Name.setColumns(10);
				textField_Name.setBackground(SystemColor.inactiveCaptionBorder);
				textField_Name.setBounds(192, 178, 246, 27);
				panelbody.add(textField_Name);
				
				JLabel lblBookName = new JLabel("Book Name     :");
				lblBookName.setFont(new Font("Trebuchet MS", Font.BOLD, 19));
				lblBookName.setBounds(32, 178, 150, 27);
				panelbody.add(lblBookName);
				
				textField_Year = new JTextField();
				textField_Year.setFont(new Font("Trebuchet MS", Font.PLAIN, 19));
				textField_Year.setColumns(10);
				textField_Year.setBackground(SystemColor.inactiveCaptionBorder);
				textField_Year.setBounds(192, 228, 246, 27);
				panelbody.add(textField_Year);
				
				JLabel lblPublishYear = new JLabel("Publish Year   :");
				lblPublishYear.setFont(new Font("Trebuchet MS", Font.BOLD, 19));
				lblPublishYear.setBounds(32, 228, 150, 27);
				panelbody.add(lblPublishYear);
				
				JLabel lblOtherDetails = new JLabel("Other Details  :");
				lblOtherDetails.setFont(new Font("Trebuchet MS", Font.BOLD, 19));
				lblOtherDetails.setBounds(32, 282, 150, 27);
				panelbody.add(lblOtherDetails);
				
				textField_Details = new JTextField();
				textField_Details.setFont(new Font("Trebuchet MS", Font.PLAIN, 19));
				textField_Details.setColumns(10);
				textField_Details.setBackground(SystemColor.inactiveCaptionBorder);
				textField_Details.setBounds(192, 282, 951, 55);
				panelbody.add(textField_Details);
				
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
