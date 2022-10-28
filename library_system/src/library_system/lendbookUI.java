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
import java.sql.SQLException;
import java.sql.Savepoint;
import java.text.SimpleDateFormat;

import javax.swing.JTextField;
import javax.swing.UIManager;

import com.mysql.cj.exceptions.WrongArgumentException;
import com.toedter.calendar.JDateChooser;

import net.proteanit.sql.DbUtils;

import javax.swing.JTable;
import javax.swing.JScrollPane;

public class lendbookUI extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldBookID;
	private JTextField textFieldMemberID;
	private JTable table;
	private JDateChooser dateChooser_Lend;
	private JDateChooser dateChooser_Resubmit;
	private String bookID = "";
	private String memberID = "";
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					lendbookUI frame = new lendbookUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	
	Connection connection = null; //create the connection variable
	//private JTable table;
	
	public void setColouronMouse( JPanel panel) { //create a method to change button color when mouse is on it
		panel.setBackground(new java.awt.Color(115, 163, 239));		
	}
	public void reSetColouronMouse( JPanel panel) {//create a method to change original button color when mouse is not on it
		panel.setBackground(SystemColor.controlHighlight);		
	}
	
	
	public void showTable() { //method for show the table ------------------------------------------
		try {
			
			String queryString = "SELECT \r\n"
					+ "B.book_ID AS 'Book ID',\r\n"
					+ "M.Mem_ID AS 'Member ID',\r\n"
					+ "F_name AS 'First Name',\r\n"
					+ "L_name AS 'Last Name',\r\n"
					+ "Contact_no AS 'Contact Number',\r\n"
					+ "lend_date AS 'Lend Date',\r\n"
					+ "resubmit_date AS 'Re Submit Date',\r\n"
					+ "B_name AS 'Book Name',\r\n"
					+ "publish_year AS 'Published Year',\r\n"
					+ "state AS 'Book Status'\r\n"
					+ "\r\n"
					+ "FROM lending L\r\n"
					+ "JOIN books B\r\n"
					+ "	ON L.book_ID = B.book_ID\r\n"
					+ "JOIN members M\r\n"
					+ "	ON L.Mem_ID = M.Mem_ID \r\n"
					+ "ORDER BY L.Mem_ID;";
					
										
			//System.out.println(queryString);
			
			PreparedStatement pStatement = connection.prepareStatement(queryString);
			ResultSet rsResultset = pStatement.executeQuery(); 
			table.setModel(DbUtils.resultSetToTableModel(rsResultset));
			
			
			pStatement.close();
			rsResultset.close();
			
			
		} catch (Exception e2) {
			System.out.println(e2);
			System.out.println("thiss error in book button");
			// TODO: handle exception
		}
		
	}
	
	
	
	
	//----------------------check all the fields are filled----------------------------------------------
	
	
	public Boolean validate_TextFeilds() { 
		boolean textFeildState = false;
		//String selectLendDateString = ((JTextField)dateChooser_Lend.getDateEditor().getUiComponent()).getText();
		//String selectResubmitDateString = ((JTextField)dateChooser_Resubmit.getDateEditor().getUiComponent()).getText();
		
		if (textFieldBookID.getText().equals("")) {
			
			textFeildState = false;
			JOptionPane.showMessageDialog(null, "Book ID Required !");
			textFieldBookID.requestFocus();
			
		}else if (textFieldMemberID.getText().equals("")) {
			
			textFeildState = false;
			JOptionPane.showMessageDialog(null, "Member ID Required !");
			textFieldMemberID.requestFocus();
			/*
		}else if ( selectLendDateString != null ) {
			
			textFeildState = false;
			JOptionPane.showMessageDialog(null, "Lend Date Required !");
			
			
		}else if (selectResubmitDateString != null ) {
			
			textFeildState = false;
			JOptionPane.showMessageDialog(null, "Resubmit Date  Required !");
			*/
			
		}
		else {
			textFeildState = true;
			System.out.println("Pass the validation ");

		}
		
		
		return textFeildState;
	}
	
	
	//---------------------------validate_TextFeilds Method Ends--------------------------------------
	
		 
	
	
	



	
	/**
	 * Create the frame.
	 */
	public lendbookUI() {
		
		connection = sqlConnection.dbConnector(); //connect with SQL server
		
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
				lblbookID.setBounds(43, 173, 126, 27);
				backgroundpanel.add(lblbookID);
				
				textFieldBookID = new JTextField();
				textFieldBookID.setFont(new Font("Trebuchet MS", Font.PLAIN, 19));
				textFieldBookID.setColumns(10);
				textFieldBookID.setBackground(SystemColor.inactiveCaptionBorder);
				textFieldBookID.setBounds(179, 173, 246, 27);
				backgroundpanel.add(textFieldBookID);
				
				JLabel lblMemberId = new JLabel("Member ID *  :");
				lblMemberId.setFont(new Font("Trebuchet MS", Font.BOLD, 19));
				lblMemberId.setBounds(43, 231, 133, 27);
				backgroundpanel.add(lblMemberId);
				
				textFieldMemberID = new JTextField();
				textFieldMemberID.setFont(new Font("Trebuchet MS", Font.PLAIN, 19));
				textFieldMemberID.setColumns(10);
				textFieldMemberID.setBackground(SystemColor.inactiveCaptionBorder);
				textFieldMemberID.setBounds(179, 231, 246, 27);
				backgroundpanel.add(textFieldMemberID);
				
				JLabel lblbookID_1_1 = new JLabel("Lend Date*   :");
				lblbookID_1_1.setFont(new Font("Trebuchet MS", Font.BOLD, 19));
				lblbookID_1_1.setBounds(43, 283, 126, 27);
				backgroundpanel.add(lblbookID_1_1);
				
				JLabel lblbookID_1_2 = new JLabel("Resubmit Date * :");
				lblbookID_1_2.setFont(new Font("Trebuchet MS", Font.BOLD, 19));
				lblbookID_1_2.setBounds(43, 335, 157, 27);
				backgroundpanel.add(lblbookID_1_2);
				
				JDateChooser dateChooser_Lend = new JDateChooser();
				dateChooser_Lend.setDateFormatString("yyyy-MM-dd");
				dateChooser_Lend.setBounds(209, 283, 216, 27);
				backgroundpanel.add(dateChooser_Lend);
				
				JDateChooser dateChooser_Resubmit = new JDateChooser();
				dateChooser_Resubmit.setDateFormatString("yyyy-MM-dd");
				dateChooser_Resubmit.setBounds(209, 335, 216, 27);
				backgroundpanel.add(dateChooser_Resubmit);
				
				
				JPanel panelSave = new JPanel();
				panelSave.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseEntered(MouseEvent e) {
						setColouronMouse(panelSave);
					}
					@Override
					public void mouseExited(MouseEvent e) {
						panelSave.setBackground(SystemColor.controlHighlight);
						
					}
					
					
					@Override
					public void mouseClicked(MouseEvent e) { //save button click ------------------------------------
						
						
						bookID = textFieldBookID.getText();
			            memberID = textFieldMemberID.getText();
			            
			            String selectLendDateString = ((JTextField)dateChooser_Lend.getDateEditor().getUiComponent()).getText(); 
			            String selectResubmitDateString = ((JTextField)dateChooser_Resubmit.getDateEditor().getUiComponent()).getText(); 
			            bookID = textFieldBookID.getText();
			            memberID = textFieldMemberID.getText();
			            boolean status = false;
			            
			            int result = JOptionPane.showConfirmDialog(null,
			                    "Are your Sure! Do you want to Lend This Book "+bookID+" to This Member "+memberID+" ?",
			                    "Confromation Message !",
			                    JOptionPane.YES_NO_OPTION,
			                    JOptionPane.WARNING_MESSAGE
			                    );
			            
			            if (result == JOptionPane.YES_OPTION) { //take the confirmation from the user
			            	status = true;
						}else {
							JOptionPane.showMessageDialog(null, "Okay! data not added to server !" );
						}
			            
			            
			            while (status) {
			            	
			            	try {
				            	
				            	//update the status in book table as "NOT_AVALIABLE"
				            	String queryString1 = "UPDATE `library_system`.`books` \r\n"
				            			+ "SET \r\n"
				            			+ "state = 'NOT_AVAILABLE' \r\n"
				            			+ "WHERE `book_ID` = '"+bookID+"'\r\n"
				            			+ "AND state = 'AVAILABLE'\r\n"
				            			+ ";";
								
								System.out.println(queryString1);//use this to check the errors in query
								System.out.println("");System.out.println("");
								

								PreparedStatement pStatement1 = connection.prepareStatement(queryString1);
								pStatement1.executeUpdate(queryString1);
								JOptionPane.showMessageDialog(null, "Book Status Update Successfully.");
								
								pStatement1.close();
						
								
							} catch (Exception e2) {
								// TODO: handle exception
								System.out.println(e2);
								JOptionPane.showMessageDialog(null, "Book ID Is Not Availble !");
								status = false;
							}
			            	
			            	
			            	if (status) {
								
			            		try {
									
									//add data in to lending table
									String queryString2 = "INSERT INTO \r\n"
											+ "`library_system`.`lending` (\r\n"
											+ "`book_ID`, \r\n"
											+ "`Mem_ID`, \r\n"
											+ "`lend_date`, \r\n"
											+ "`resubmit_date`\r\n"
											+ ") \r\n"
											+ "VALUES (\r\n"
											+ "'"+bookID+"', \r\n"
											+ "'"+memberID+"', \r\n"
											+ "'"+selectLendDateString+"', \r\n"
											+ "'"+selectResubmitDateString+"'\r\n"
											+ ");";
					                System.out.println(queryString2); //use this to check the errors in query
					                System.out.println(""); System.out.println("");
									

					    			PreparedStatement pStatement2 = connection.prepareStatement(queryString2);
					    			pStatement2.executeUpdate(queryString2);
					    			JOptionPane.showMessageDialog(null, "Book Lending data Saved Successfully.");
					    			
					    			pStatement2.close();
					                
								} catch (Exception e2) {
									// TODO: handle exception
									System.out.println(e2);
									JOptionPane.showMessageDialog(null, "Member ID Is Not Availble !");
									status = false;
								}
							}
			            	
			            	status = false;
			            	
						}
			            
			            
			            
			            
						
						
						
						
			          
		                
		                
						
						showTable();
					}
				});
				panelSave.setLayout(null);
				panelSave.setBackground(SystemColor.controlHighlight);
				panelSave.setBounds(617, 219, 252, 83);
				backgroundpanel.add(panelSave);
				
				JLabel lblSave = new JLabel("Save");
				lblSave.setFont(new Font("Trebuchet MS", Font.BOLD, 27));
				lblSave.setBounds(92, 11, 58, 42);
				panelSave.add(lblSave);
				
				JPanel panelCancel = new JPanel();
				panelCancel.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseEntered(MouseEvent e) {
						panelCancel.setBackground(SystemColor.info);
					}
					@Override
					public void mouseExited(MouseEvent e) {
						panelCancel.setBackground(UIManager.getColor("CheckBox.background"));
					}
					@Override
					public void mouseClicked(MouseEvent e) {
						
						textFieldBookID.setText("");
						textFieldMemberID.setText("");
						
					}
				});
				panelCancel.setLayout(null);
				panelCancel.setBackground(SystemColor.menu);
				panelCancel.setBounds(968, 253, 252, 49);
				backgroundpanel.add(panelCancel);
				
				JLabel lblCancel = new JLabel("Clear");
				lblCancel.setFont(new Font("Trebuchet MS", Font.PLAIN, 20));
				lblCancel.setBounds(93, 11, 61, 24);
				panelCancel.add(lblCancel);
				
				JLabel lblNewLabel = new JLabel("* Please only enter valid Data values");
				lblNewLabel.setFont(new Font("Trebuchet MS", Font.BOLD, 20));
				lblNewLabel.setBounds(560, 329, 361, 33);
				backgroundpanel.add(lblNewLabel);
				
				JScrollPane scrollPane = new JScrollPane();
				scrollPane.setBounds(10, 382, 1261, 302);
				backgroundpanel.add(scrollPane);
				
				table = new JTable();
				scrollPane.setViewportView(table);
				
				//String selectLendDateString = ((JTextField)dateChooser_Lend.getDateEditor().getUiComponent()).getText();
				//String selectResubmitDateString = ((JTextField)dateChooser_Resubmit.getDateEditor().getUiComponent()).getText();
				
				showTable();
				//Back to Home BUtton ENDS 
				
				
				
	}
}
