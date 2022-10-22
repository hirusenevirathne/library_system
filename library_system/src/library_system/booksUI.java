package library_system;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

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

public class booksUI extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldSearch;
	private JTable table;

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
					@Override
					public void keyReleased(KeyEvent e) { //add function for search button
						
						String serchString = "";
						
						try {
							serchString = textFieldSearch.getText();
							String queryString = "SELECT \r\n"
									+ "book_ID AS \"Book ID\",\r\n"
									+ "B_name AS \"Book Name\",\r\n"
									+ "A_F_name AS \"Author First Name\",\r\n"
									+ "A_L_name AS \"Author Last Name\",\r\n"
									+ "Other_details AS \"Other Details\",\r\n"
									+ "state AS \"Book States\"\r\n"
									+ "FROM books B\r\n"
									+ "JOIN author A\r\n"
									+ "	ON B.Author_ID = A.Author_id\r\n"
									+ "WHERE A_F_name LIKE \"%"+serchString+"%\"\r\n"
									+ "ORDER BY B_name;\r\n"
									+ "";
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
				});
				textFieldSearch.setBounds(55, 173, 523, 47);
				backgroundpanel.add(textFieldSearch);
				textFieldSearch.setColumns(10);
				
				JScrollPane scrollPane = new JScrollPane();
				scrollPane.setBounds(22, 479, 1238, 205);
				backgroundpanel.add(scrollPane);
				
				table = new JTable();
				scrollPane.setViewportView(table);
				//Back to Home BUtton ENDS 
				
				
	}
}
