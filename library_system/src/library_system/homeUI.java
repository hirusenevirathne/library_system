package library_system;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.mysql.cj.protocol.Resultset;

import net.proteanit.sql.DbUtils;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.SystemColor;

import javax.management.loading.PrivateClassLoader;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JTextPane;
import javax.swing.JTable;
import javax.swing.JScrollPane;

public class homeUI extends JFrame {

	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					homeUI frame = new homeUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	Connection connection = null;

		
	//method for mouse on action
	public void setColouronMouse( JPanel panel) { //create a method to change button color when mouse is on it
		panel.setBackground(new java.awt.Color(115, 163, 239));		
	}
	public void reSetColouronMouse( JPanel panel) {//create a method to change original button color when mouse is not on it
		panel.setBackground(SystemColor.controlHighlight);		
	}
	@SuppressWarnings("finally")
	public String getCount(String tableName ) {
		
		//take the book count
		String count = null;
		try {
			String queryString = "SELECT COUNT(*) \r\n"
					+ "FROM " +tableName+ ";";
			PreparedStatement pStatement = connection.prepareStatement(queryString);
			ResultSet rsResultset = pStatement.executeQuery(); 
			rsResultset.next();
			count = rsResultset.getString(1);
			//System.out.println(count);
			return count;
			
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			return count;
		}
		
				
		//end of taking book count
		
	}
	
	
	/**
	 * Create the frame.
	 */
	public homeUI() {
		
		//connect with the sql server
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
		
		JPanel total_bookplane = new JPanel();
		total_bookplane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) { //change the button color when mouse on it
				setColouronMouse(total_bookplane);
			}
			
			@Override
			public void mouseExited(MouseEvent e) { //change the button color back to normal
				reSetColouronMouse(total_bookplane);
			}
			@Override
			public void mouseClicked(MouseEvent e) { //load the data from the sql server and show it in jtabale
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
		});
		total_bookplane.setBounds(49, 189, 335, 146);
		backgroundpanel.add(total_bookplane);
		total_bookplane.setBackground(SystemColor.controlHighlight);
		total_bookplane.setLayout(null);
		
		
		String bookCount = getCount("books"); // take the count of all books in QSL server
		
		JLabel lblbookCount = new JLabel(bookCount);
		lblbookCount.setFont(new Font("Tw Cen MT", Font.BOLD, 32));
		lblbookCount.setBounds(147, 73, 124, 42);
		total_bookplane.add(lblbookCount);
		
		JLabel lbltotbooks = new JLabel("Total No Of Books");
		lbltotbooks.setFont(new Font("Trebuchet MS", Font.BOLD, 21));
		lbltotbooks.setBounds(75, 32, 196, 49);
		total_bookplane.add(lbltotbooks);
		
		JPanel total_memberplane = new JPanel();
		total_memberplane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) { //change the button color when mouse on it
				setColouronMouse(total_memberplane);
			}
			
			@Override
			public void mouseExited(MouseEvent e) { //change the button color back to normal
				reSetColouronMouse(total_memberplane);
			}
			@Override
			public void mouseClicked(MouseEvent e) { //load the data from the sql server and show it in jtabale
				try {
					
					String queryString = "SELECT \r\n"
							+ "Mem_ID AS \"Member ID\",\r\n"
							+ "F_name AS \"First Name\",\r\n"
							+ "L_name AS \"Last Name\",\r\n"
							+ "Contact_no \"Contact No\"\r\n"
							+ "FROM members;";
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
		total_memberplane.setLayout(null);
		total_memberplane.setBackground(SystemColor.controlHighlight);
		total_memberplane.setBounds(481, 189, 335, 146);
		backgroundpanel.add(total_memberplane);
		
		
		String memberCount = getCount("members"); // take the count of all books in QSL server
		JLabel lblmemberCount = new JLabel(memberCount);
		lblmemberCount.setFont(new Font("Tw Cen MT", Font.BOLD, 32));
		lblmemberCount.setBounds(142, 73, 124, 42);
		total_memberplane.add(lblmemberCount);
		
		JLabel lbltotMembers = new JLabel("Total No Of Members");
		lbltotMembers.setFont(new Font("Trebuchet MS", Font.BOLD, 21));
		lbltotMembers.setBounds(67, 32, 224, 49);
		total_memberplane.add(lbltotMembers);
		
		JPanel total_lendbookplane = new JPanel();
		total_lendbookplane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) { //change the button color when mouse on it
				setColouronMouse(total_lendbookplane);
			}
			
			@Override
			public void mouseExited(MouseEvent e) { //change the button color back to normal
				reSetColouronMouse(total_lendbookplane);
			}
			@Override
			public void mouseClicked(MouseEvent e) { //load the data from the sql server and show it in jtabale
				try {
					
					String queryString = "SELECT \r\n"
							+ "book_ID AS \"Book ID\",\r\n"
							+ "Mem_ID AS \"Member ID\",\r\n"
							+ "lend_date AS \"Lend Date\",\r\n"
							+ "resubmit_date AS \"Resubmit Date\"\r\n"
							+ "FROM lending;";
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
		total_lendbookplane.setLayout(null);
		total_lendbookplane.setBackground(SystemColor.controlHighlight);
		total_lendbookplane.setBounds(906, 189, 335, 146);
		backgroundpanel.add(total_lendbookplane);
		
		
		
		String lendCount = getCount("lending"); // take the count of all books in QSL server
		JLabel lbllendCOunt = new JLabel(lendCount);
		lbllendCOunt.setFont(new Font("Tw Cen MT", Font.BOLD, 32));
		lbllendCOunt.setBounds(142, 73, 124, 42);
		total_lendbookplane.add(lbllendCOunt);
		
		JLabel lbllendbook = new JLabel("Total lend Books");
		lbllendbook.setFont(new Font("Trebuchet MS", Font.BOLD, 21));
		lbllendbook.setBounds(67, 32, 224, 49);
		total_lendbookplane.add(lbllendbook);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(49, 382, 1190, 291);
		backgroundpanel.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		//Back to Home BUtton ENDS 
	}
}
