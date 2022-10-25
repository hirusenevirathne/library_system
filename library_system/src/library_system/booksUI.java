package library_system;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import library_system.UI.addAuthor_UI;
import library_system.UI.addBook_UI;
import net.proteanit.sql.DbUtils;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class booksUI extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldSearch;
	private JTable table;
	private String searchByString = "book_ID";
	private String serchString = "1";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					booksUI frame = new booksUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
	Connection connection = null;
	
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
	
	
	
	/**
	 * Create the frame.
	 */
	public booksUI() {
		
		connection = sqlConnection.dbConnector();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1297, 734);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel backgroundpanel = new JPanel();
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
				
				textFieldSearch = new JTextField();
				textFieldSearch.addKeyListener(new KeyAdapter() {
					
				});
				textFieldSearch.setBounds(55, 173, 523, 47);
				backgroundpanel.add(textFieldSearch);
				textFieldSearch.setColumns(10);
				
				JScrollPane scrollPane = new JScrollPane();
				scrollPane.setBounds(22, 408, 1238, 276);
				backgroundpanel.add(scrollPane);
				
				table = new JTable();
				scrollPane.setViewportView(table);
				
				JComboBox<String> comboBoxSearchTable = new JComboBox<String>();
				comboBoxSearchTable.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						

						searchByString = comboBoxSearchTable.getSelectedItem().toString();
						
						if (searchByString == "Book ID") {
							searchByString = "book_ID";
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
							searchByString = "book_ID";
						}					
						
					}
				});
				comboBoxSearchTable.addMouseListener(new MouseAdapter() {
					
				});
				comboBoxSearchTable.setFont(new Font("Tahoma", Font.BOLD, 17));
				comboBoxSearchTable.setBackground(SystemColor.window);
				comboBoxSearchTable.setBounds(622, 173, 308, 47);
				backgroundpanel.add(comboBoxSearchTable);
				
				
				comboBoxSearchTable.addItem("Book ID");
				comboBoxSearchTable.addItem("Book Name");
				comboBoxSearchTable.addItem("Author First Name");
				comboBoxSearchTable.addItem("Author Last Name");
				comboBoxSearchTable.addItem("Published Year");
				comboBoxSearchTable.setSelectedItem("Book_ID");
				
				
				//add action for the Button
				JPanel searchpanel = new JPanel();
				searchpanel.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseEntered(MouseEvent e) { //change the button color when mouse on it
						setColouronMouse(searchpanel);
					}
					
					@Override
					public void mouseExited(MouseEvent e) { //change the button color back to normal
						reSetColouronMouse(searchpanel);
					}
					
					
					
					@Override
					public void mouseClicked(MouseEvent e) {
						
						try {
							serchString = textFieldSearch.getText();
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
									+ "WHERE "+searchByString+" LIKE \"%"+serchString+"%\"\r\n"
									+ "ORDER BY B_name;\r\n"
									+ "";
							//System.out.println(queryString); //use this to check the errors in query
							
							PreparedStatement pStatement = connection.prepareStatement(queryString);
							ResultSet rsResultset = pStatement.executeQuery(); 
							table.setModel(DbUtils.resultSetToTableModel(rsResultset));
							
							
							pStatement.close();
							rsResultset.close();
							
							
							
						} catch (Exception e2) {
							System.out.println(e2);
							System.out.println("this error in book button");
							// TODO: handle exception
						}
						
					}
				});
				searchpanel.setBackground(SystemColor.controlHighlight);
				searchpanel.setBounds(986, 173, 254, 47);
				backgroundpanel.add(searchpanel);
				searchpanel.setLayout(null);
				
				JLabel lblNewLabel = new JLabel("Search");
				lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 17));
				lblNewLabel.setBounds(98, 11, 58, 31);
				searchpanel.add(lblNewLabel);
				
				JPanel panelAddBook = new JPanel();
				panelAddBook.setBackground(SystemColor.controlHighlight);
				panelAddBook.addMouseListener(new MouseAdapter() {
					
					
					@Override
					public void mouseEntered(MouseEvent e) { //change the button color when mouse on it
						setColouronMouse(panelAddBook);
					}
					
					@Override
					public void mouseExited(MouseEvent e) { //change the button color back to normal
						reSetColouronMouse(panelAddBook);
					}
					
					
					@SuppressWarnings("deprecation")
					@Override
					public void mouseClicked(MouseEvent e) {
						new addBook_UI().show();
						dispose();
					}
					
				});
				panelAddBook.setBounds(128, 269, 430, 92);
				backgroundpanel.add(panelAddBook);
				panelAddBook.setLayout(null);
				
				JLabel lblAddBook = new JLabel("Add a Book");
				lblAddBook.setFont(new Font("Trebuchet MS", Font.BOLD, 21));
				lblAddBook.setBounds(155, 25, 139, 42);
				panelAddBook.add(lblAddBook);
				
				JPanel panelAddAuthor = new JPanel();
				panelAddAuthor.setBackground(SystemColor.controlHighlight);
				panelAddAuthor.addMouseListener(new MouseAdapter() {
					
					@Override
					public void mouseEntered(MouseEvent e) { //change the button color when mouse on it
						setColouronMouse(panelAddAuthor);
					}
					
					@Override
					public void mouseExited(MouseEvent e) { //change the button color back to normal
						reSetColouronMouse(panelAddAuthor);
					}
					
					@SuppressWarnings("deprecation")
					@Override
					public void mouseClicked(MouseEvent e) {
						new addAuthor_UI().show();
						dispose();
					}
				});
				panelAddAuthor.setLayout(null);
				panelAddAuthor.setBounds(747, 269, 430, 92);
				backgroundpanel.add(panelAddAuthor);
				
				JLabel lblAddAuthor = new JLabel("Add a Author");
				lblAddAuthor.setFont(new Font("Trebuchet MS", Font.BOLD, 21));
				lblAddAuthor.setBounds(153, 25, 139, 42);
				panelAddAuthor.add(lblAddAuthor);
				showTable();
				
				
			
				
				
				
				
				
	}
}
