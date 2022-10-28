package library_system.UI;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import library_system.homeUI;
import library_system.home_menu;
import library_system.sqlConnection;
import library_system.updateUI;

public class updateAuthorUI extends JFrame {

	private JPanel contentPane;
	Connection connection = null; //create the connection variable
	private JTextField textField_ID;
	private JTextField textField_Name;
	private JTextField textField_Year;
	private JTable table;

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
				
				JPanel panelAuthorSelectSearch = new JPanel();
				panelAuthorSelectSearch.setLayout(null);
				panelAuthorSelectSearch.setBackground(SystemColor.inactiveCaptionBorder);
				panelAuthorSelectSearch.setBounds(450, 11, 380, 49);
				panelbackground.add(panelAuthorSelectSearch);
				
				JLabel lblAuthors = new JLabel("Authors");
				lblAuthors.setFont(new Font("Trebuchet MS", Font.BOLD, 22));
				lblAuthors.setBounds(161, 11, 87, 27);
				panelAuthorSelectSearch.add(lblAuthors);
				
				JPanel panelMembersSelectSearch = new JPanel();
				panelMembersSelectSearch.setLayout(null);
				panelMembersSelectSearch.setBackground(SystemColor.controlHighlight);
				panelMembersSelectSearch.setBounds(867, 11, 380, 49);
				panelbackground.add(panelMembersSelectSearch);
				
				JLabel lblMembers = new JLabel("Members");
				lblMembers.setFont(new Font("Trebuchet MS", Font.BOLD, 22));
				lblMembers.setBounds(152, 11, 92, 27);
				panelMembersSelectSearch.add(lblMembers);
				
				JPanel panelLendingSelectSearch = new JPanel();
				panelLendingSelectSearch.setLayout(null);
				panelLendingSelectSearch.setBackground(SystemColor.controlHighlight);
				panelLendingSelectSearch.setBounds(36, 11, 380, 49);
				panelbackground.add(panelLendingSelectSearch);
				
				JLabel lblBooks = new JLabel("Books");
				lblBooks.setFont(new Font("Trebuchet MS", Font.BOLD, 22));
				lblBooks.setBounds(145, 11, 63, 27);
				panelLendingSelectSearch.add(lblBooks);
				
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
				panelSearch.setLayout(null);
				panelSearch.setBackground(SystemColor.menu);
				panelSearch.setBounds(540, 43, 252, 55);
				panelbody.add(panelSearch);
				
				JLabel lblSearch = new JLabel("Search");
				lblSearch.setFont(new Font("Trebuchet MS", Font.PLAIN, 20));
				lblSearch.setBounds(93, 11, 61, 24);
				panelSearch.add(lblSearch);
				
				JPanel panelUpdate = new JPanel();
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
				textField_Name.setBounds(238, 178, 246, 27);
				panelbody.add(textField_Name);
				
				JLabel lblBookName = new JLabel("Author First Name   :");
				lblBookName.setFont(new Font("Trebuchet MS", Font.BOLD, 19));
				lblBookName.setBounds(32, 178, 207, 27);
				panelbody.add(lblBookName);
				
				textField_Year = new JTextField();
				textField_Year.setFont(new Font("Trebuchet MS", Font.PLAIN, 19));
				textField_Year.setColumns(10);
				textField_Year.setBackground(SystemColor.inactiveCaptionBorder);
				textField_Year.setBounds(238, 228, 246, 27);
				panelbody.add(textField_Year);
				
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
