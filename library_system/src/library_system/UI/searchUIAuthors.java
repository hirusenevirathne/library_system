package library_system.UI;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import library_system.homeUI;
import library_system.home_menu;
import library_system.searchUI;
import library_system.sqlConnection;
import net.proteanit.sql.DbUtils;

public class searchUIAuthors extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					searchUIAuthors frame = new searchUIAuthors();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
	//----------------------------------------------------
	

	Connection connection = null;
	private JTextField textFieldSearch;
	private JTable table;
	private String searchByString = "Author_id";
	private String serchString = "";
	
	public void setColouronMouse( JPanel panel) { //create a method to change button color when mouse is on it
		panel.setBackground(new java.awt.Color(115, 163, 239));		
	}
	public void reSetColouronMouse( JPanel panel) {//create a method to change original button color when mouse is not on it
		panel.setBackground(SystemColor.controlHighlight);		
	}
	
	public void showTable() { //method for show the table 
		try {
			
			String queryString =  "SELECT \r\n"
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
	
	
	public void search() {
		
		try {
			serchString = textFieldSearch.getText();
			
			if (searchByString == "A_F_name" || searchByString == "A_L_name" ) {
				
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
						+ "WHERE A."+searchByString+" LIKE \"%"+serchString+"%\"\r\n"
						+ "ORDER BY A_F_name;";
				
				//System.out.println(queryString); //use this to check the errors in query
				PreparedStatement pStatement = connection.prepareStatement(queryString);
				ResultSet rsResultset = pStatement.executeQuery(); 
				table.setModel(DbUtils.resultSetToTableModel(rsResultset));
				
				
				pStatement.close();
				rsResultset.close();
				
			}else {
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
						+ "WHERE B."+searchByString+" LIKE \"%"+serchString+"%\"\r\n"
						+ "ORDER BY A_F_name;";
				
				//System.out.println(queryString); //use this to check the errors in query
				PreparedStatement pStatement = connection.prepareStatement(queryString);
				ResultSet rsResultset = pStatement.executeQuery(); 
				table.setModel(DbUtils.resultSetToTableModel(rsResultset));
				
				
				pStatement.close();
				rsResultset.close();
			}
			
			
		} catch (Exception e) {
			
			System.out.println(e);
			System.out.println("this error in book button");
			// TODO: handle exception
			// TODO: handle exception
		}
		
	}
	
	
	//------------------------------------------------------------------------------------

	/**
	 * Create the frame.
	 */
	public searchUIAuthors() {
		
		connection = sqlConnection.dbConnector(); //connect with sql server
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1297, 734);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel backgroundpanel = new JPanel();
		backgroundpanel.setBackground(SystemColor.control);
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
				panelbackground.setBackground(SystemColor.control);
				panelbackground.setBounds(0, 133, 1281, 60);
				backgroundpanel.add(panelbackground);
				panelbackground.setLayout(null);
				
				JPanel panelBooksSelectSearch = new JPanel();
				panelBooksSelectSearch.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseEntered(MouseEvent e) {
						setColouronMouse(panelBooksSelectSearch);
					}
					@Override
					public void mouseExited(MouseEvent e) {
						panelBooksSelectSearch.setBackground(SystemColor.controlHighlight);
					}
					@SuppressWarnings("deprecation")
					@Override
					public void mouseClicked(MouseEvent e) {
						new searchUI().show();
						dispose();
					}
				});
				panelBooksSelectSearch.setBackground(SystemColor.controlHighlight);
				panelBooksSelectSearch.setBounds(20, 11, 292, 49);
				panelbackground.add(panelBooksSelectSearch);
				panelBooksSelectSearch.setLayout(null);
				
				JLabel lblNewLabel = new JLabel("Books");
				lblNewLabel.setFont(new Font("Trebuchet MS", Font.BOLD, 22));
				lblNewLabel.setBounds(114, 11, 74, 27);
				panelBooksSelectSearch.add(lblNewLabel);
				
				JPanel panelAuthorSelectSearch = new JPanel();
				panelAuthorSelectSearch.setBackground(SystemColor.inactiveCaptionBorder);
				panelAuthorSelectSearch.setBounds(334, 11, 292, 49);
				panelbackground.add(panelAuthorSelectSearch);
				panelAuthorSelectSearch.setLayout(null);
				
				JLabel lblAuthors = new JLabel("Authors");
				lblAuthors.setFont(new Font("Trebuchet MS", Font.BOLD, 22));
				lblAuthors.setBounds(110, 11, 87, 27);
				panelAuthorSelectSearch.add(lblAuthors);
				
				JPanel panelMembersSelectSearch = new JPanel();
				panelMembersSelectSearch.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseEntered(MouseEvent e) {
						setColouronMouse(panelMembersSelectSearch);
					}
					@Override
					public void mouseExited(MouseEvent e) {
						panelMembersSelectSearch.setBackground(SystemColor.controlHighlight);
					}
					@SuppressWarnings("deprecation")
					@Override
					public void mouseClicked(MouseEvent e) {
						new searchUIMembers().show();
						dispose();
					}
				});
				panelMembersSelectSearch.setBackground(SystemColor.controlHighlight);
				panelMembersSelectSearch.setBounds(651, 11, 292, 49);
				panelbackground.add(panelMembersSelectSearch);
				panelMembersSelectSearch.setLayout(null);
				
				JLabel lblMembers = new JLabel("Members");
				lblMembers.setFont(new Font("Trebuchet MS", Font.BOLD, 22));
				lblMembers.setBounds(108, 11, 92, 27);
				panelMembersSelectSearch.add(lblMembers);
				
				JPanel panelLendingSelectSearch = new JPanel();
				panelLendingSelectSearch.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseEntered(MouseEvent e) {
						setColouronMouse(panelLendingSelectSearch);
					}
					@Override
					public void mouseExited(MouseEvent e) {
						panelLendingSelectSearch.setBackground(SystemColor.controlHighlight);
					}
					@SuppressWarnings("deprecation")
					@Override
					public void mouseClicked(MouseEvent e) {
						new searchUILending().show();
						dispose();
					}
				});
				panelLendingSelectSearch.setBackground(SystemColor.controlHighlight);
				panelLendingSelectSearch.setBounds(966, 11, 292, 49);
				panelbackground.add(panelLendingSelectSearch);
				panelLendingSelectSearch.setLayout(null);
				
				JLabel lblLending = new JLabel("Lending");
				lblLending.setFont(new Font("Trebuchet MS", Font.BOLD, 22));
				lblLending.setBounds(102, 11, 101, 27);
				panelLendingSelectSearch.add(lblLending);
				
				JPanel panelbody = new JPanel();
				panelbody.setBackground(SystemColor.inactiveCaptionBorder);
				panelbody.setBounds(10, 190, 1261, 505);
				backgroundpanel.add(panelbody);
				panelbody.setLayout(null);
				
				textFieldSearch = new JTextField();
				textFieldSearch.setColumns(10);
				textFieldSearch.setBounds(26, 23, 668, 47);
				panelbody.add(textFieldSearch);
				
				JPanel searchpanel = new JPanel();
				searchpanel.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseEntered(MouseEvent e) {
						setColouronMouse(searchpanel);
					}
					
					@Override
					public void mouseExited(MouseEvent e) {
						searchpanel.setBackground(SystemColor.controlHighlight);
					}
					@Override
					public void mouseClicked(MouseEvent e) {
						search(); 
					}
				});
				searchpanel.setLayout(null);
				searchpanel.setBackground(SystemColor.controlHighlight);
				searchpanel.setBounds(834, 23, 254, 47);
				panelbody.add(searchpanel);
				
				JLabel lblNewLabel_1 = new JLabel("Search");
				lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 17));
				lblNewLabel_1.setBounds(98, 11, 58, 31);
				searchpanel.add(lblNewLabel_1);
				
				JComboBox<String> comboBoxSearchTable = new JComboBox<String>();
				comboBoxSearchTable.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						searchByString = comboBoxSearchTable.getSelectedItem().toString();
						
						if (searchByString == "Author ID") {
							searchByString = "Author_id";
						}else if (searchByString == "Book Name") {
							searchByString = "B_name";
						}else if (searchByString =="Author First Name") {
							searchByString = "A_F_name";
						}else if (searchByString == "Author Last Name") {
							searchByString = "A_L_name";
						}else if (searchByString == "Published Year") {
							searchByString = "publish_year";
						}
						else {
							searchByString = "Author_id";
						}	
						
					}
				});
				comboBoxSearchTable.setFont(new Font("Tahoma", Font.BOLD, 17));
				comboBoxSearchTable.setBackground(Color.WHITE);
				comboBoxSearchTable.setBounds(26, 90, 308, 47);
				panelbody.add(comboBoxSearchTable);
				
				comboBoxSearchTable.addItem("Author ID");
				comboBoxSearchTable.addItem("Book Name");
				comboBoxSearchTable.addItem("Author First Name");
				comboBoxSearchTable.addItem("Author Last Name");
				comboBoxSearchTable.addItem("Published Year");
				comboBoxSearchTable.setSelectedItem("Author_id");
				
				JScrollPane scrollPane = new JScrollPane();
				scrollPane.setBounds(10, 157, 1241, 337);
				panelbody.add(scrollPane);
				
				table = new JTable();
				scrollPane.setViewportView(table);
				showTable();
				//Back to Home BUtton ENDS ---------------------------------------------------------------------
				
				
				
	}
}
