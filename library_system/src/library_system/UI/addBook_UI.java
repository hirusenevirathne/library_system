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
import javax.swing.border.EmptyBorder;

import library_system.booksUI;
import library_system.homeUI;
import library_system.home_menu;
import library_system.sqlConnection;
import net.proteanit.sql.DbUtils;

import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.JTable;
import javax.swing.JScrollPane;

public class addBook_UI extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldBookID;
	private JTextField textFieldBookName;
	private JTextField textFieldAuthorID;
	private JTextField textFieldPubYear;
	private JTextField textFieldOtherDetails;
	
	
	private String bookID = "";
	private String authorID = "";
	private String bookName = "";
	private String publishYear = "";
	private String otherDetails = "";
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					addBook_UI frame = new addBook_UI();
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
					+ "book_ID AS \"Book ID\",\r\n"
					+ "A_F_name AS \"Author First Name\",\r\n"
					+ "A_L_name AS \"Author Last Name\",\r\n"
					+ "B_name AS \"Book Name\",\r\n"
					+ "publish_year AS \"Published Year\",\r\n"
					+ "Other_details AS \"Other Details\",\r\n"
					+ "state AS \"Status\"\r\n"
					+ "FROM books\r\n"
					+ "JOIN author\r\n"
					+ "	ON books.Author_ID = author.Author_id ;";
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
	
	
	public void save() {
		
		try {
			bookID = textFieldBookID.getText();
			authorID = textFieldAuthorID.getText();
			bookName = textFieldBookName.getText();
			publishYear = textFieldPubYear.getText();
			otherDetails = textFieldOtherDetails.getText();
			
			System.out.println(bookID);
			System.out.println(authorID);
			System.out.println(bookName);
			System.out.println(publishYear);
			System.out.println(otherDetails);
			
			String queryString = "INSERT INTO `library_system`.`books` (\r\n"
					+ "`book_ID`, \r\n"
					+ "`Author_ID`, \r\n"
					+ "`B_name`, \r\n"
					+ "`publish_year`, \r\n"
					+ "`Other_details`\r\n"
					+ ") \r\n"
					+ "VALUES (\r\n"
					+ "'"+bookID+"', \r\n"
					+ "'"+authorID+"', \r\n"
					+ "'"+bookName+"', \r\n"
					+ "'"+publishYear+"', \r\n"
					+ "'"+otherDetails+"'\r\n"
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
	

	/**
	 * Create the frame.
	 */
	public addBook_UI() {
		
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
				
				JLabel lblbookID = new JLabel("Book ID *   :");
				lblbookID.setFont(new Font("Trebuchet MS", Font.BOLD, 19));
				lblbookID.setBounds(44, 167, 126, 27);
				backgroundpanel.add(lblbookID);
				
				textFieldBookID = new JTextField();
				textFieldBookID.setBackground(SystemColor.inactiveCaptionBorder);
				textFieldBookID.setFont(new Font("Trebuchet MS", Font.PLAIN, 19));
				textFieldBookID.setBounds(180, 167, 246, 27);
				backgroundpanel.add(textFieldBookID);
				textFieldBookID.setColumns(10);
				
				JLabel lblbookName = new JLabel("Book Name *  :");
				lblbookName.setFont(new Font("Trebuchet MS", Font.BOLD, 19));
				lblbookName.setBounds(44, 225, 157, 27);
				backgroundpanel.add(lblbookName);
				
				textFieldBookName = new JTextField();
				textFieldBookName.setFont(new Font("Trebuchet MS", Font.PLAIN, 19));
				textFieldBookName.setColumns(10);
				textFieldBookName.setBackground(SystemColor.inactiveCaptionBorder);
				textFieldBookName.setBounds(180, 225, 246, 27);
				backgroundpanel.add(textFieldBookName);
				
				JLabel lblAuthorID = new JLabel("Author ID *   :");
				lblAuthorID.setFont(new Font("Trebuchet MS", Font.BOLD, 19));
				lblAuthorID.setBounds(524, 167, 126, 27);
				backgroundpanel.add(lblAuthorID);
				
				textFieldAuthorID = new JTextField();
				textFieldAuthorID.setFont(new Font("Trebuchet MS", Font.PLAIN, 19));
				textFieldAuthorID.setColumns(10);
				textFieldAuthorID.setBackground(SystemColor.inactiveCaptionBorder);
				textFieldAuthorID.setBounds(660, 167, 246, 27);
				backgroundpanel.add(textFieldAuthorID);
				
				JLabel lblPubYear = new JLabel("Published Year * :");
				lblPubYear.setFont(new Font("Trebuchet MS", Font.BOLD, 19));
				lblPubYear.setBounds(524, 225, 173, 27);
				backgroundpanel.add(lblPubYear);
				
				textFieldPubYear = new JTextField();
				textFieldPubYear.setFont(new Font("Trebuchet MS", Font.PLAIN, 19));
				textFieldPubYear.setColumns(10);
				textFieldPubYear.setBackground(SystemColor.inactiveCaptionBorder);
				textFieldPubYear.setBounds(686, 225, 220, 27);
				backgroundpanel.add(textFieldPubYear);
				
				JLabel lblbookID_4 = new JLabel("Other Details :");
				lblbookID_4.setFont(new Font("Trebuchet MS", Font.BOLD, 19));
				lblbookID_4.setBounds(44, 287, 131, 27);
				backgroundpanel.add(lblbookID_4);
				
				textFieldOtherDetails = new JTextField();
				textFieldOtherDetails.setFont(new Font("Trebuchet MS", Font.PLAIN, 19));
				textFieldOtherDetails.setColumns(10);
				textFieldOtherDetails.setBackground(SystemColor.inactiveCaptionBorder);
				textFieldOtherDetails.setBounds(180, 287, 726, 56);
				backgroundpanel.add(textFieldOtherDetails);
				
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
						save();
						showTable();
					}
				});
				panelSave.setBackground(SystemColor.controlHighlight);
				panelSave.setBounds(991, 167, 252, 83);
				backgroundpanel.add(panelSave);
				panelSave.setLayout(null);
				
				JLabel lblSave = new JLabel("Save");
				lblSave.setBounds(92, 11, 58, 42);
				lblSave.setFont(new Font("Trebuchet MS", Font.BOLD, 27));
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
				});
				panelCancel.setBackground(UIManager.getColor("CheckBox.background"));
				panelCancel.setBounds(991, 293, 252, 49);
				backgroundpanel.add(panelCancel);
				panelCancel.setLayout(null);
				
				JLabel lblCancel = new JLabel("Cancel");
				lblCancel.setBounds(93, 11, 61, 24);
				lblCancel.setFont(new Font("Trebuchet MS", Font.PLAIN, 20));
				panelCancel.add(lblCancel);
				
				JScrollPane scrollPane = new JScrollPane();
				scrollPane.setBounds(44, 400, 1196, 273);
				backgroundpanel.add(scrollPane);
				
				table = new JTable();
				scrollPane.setViewportView(table);
				showTable();
				//Back to Home BUtton ENDS
	}
}
