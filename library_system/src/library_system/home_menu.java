package library_system;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.SystemColor;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class home_menu extends JFrame {

	private JPanel contentPane;
	private JLabel lbldeleteico;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					home_menu frame = new home_menu();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	
	public void setColouronMouse( JPanel panel) { //create a method to change button color when mouse is on it
		panel.setBackground(new java.awt.Color(115, 163, 239));		
	}
	public void reSetColouronMouse( JPanel panel) {//create a method to change original button color when mouse is not on it
		panel.setBackground(SystemColor.controlHighlight);		
	}
	
	
	/**
	 * Create the frame.
	 */
	public home_menu() {
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
		
		JButton btnLogOut = new JButton("Log Out");
		btnLogOut.setForeground(SystemColor.textHighlight);
		btnLogOut.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnLogOut.setBackground(SystemColor.controlHighlight);
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnLogOut.setBounds(1182, 21, 89, 23);
		topblue_panel.add(btnLogOut);
		
		JLabel lbllibrarysystem = new JLabel("Library Management System");
		lbllibrarysystem.setForeground(Color.WHITE);
		lbllibrarysystem.setFont(new Font("Times New Roman", Font.BOLD, 40));
		lbllibrarysystem.setBounds(34, 0, 624, 99);
		topblue_panel.add(lbllibrarysystem);
		
		JPanel homeButton = new JPanel();
		homeButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				setColouronMouse(homeButton);
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				reSetColouronMouse(homeButton);
			}
		});
		homeButton.setBackground(SystemColor.controlHighlight);
		homeButton.setBounds(120, 200, 164, 143);
		backgroundpanel.add(homeButton);
		
		JLabel lblhomeico = new JLabel("");
		lblhomeico.setIcon(new ImageIcon(home_menu.class.getResource("/main/images/Bf home-50.png")));
		
		JLabel lblHome = new JLabel("Home");
		lblHome.setForeground(SystemColor.textHighlight);
		lblHome.setFont(new Font("Tahoma", Font.BOLD, 13));
		GroupLayout gl_homeButton = new GroupLayout(homeButton);
		gl_homeButton.setHorizontalGroup(
			gl_homeButton.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_homeButton.createSequentialGroup()
					.addContainerGap(58, Short.MAX_VALUE)
					.addGroup(gl_homeButton.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_homeButton.createSequentialGroup()
							.addGap(10)
							.addComponent(lblHome, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE))
						.addComponent(lblhomeico))
					.addGap(46))
		);
		gl_homeButton.setVerticalGroup(
			gl_homeButton.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_homeButton.createSequentialGroup()
					.addGap(35)
					.addComponent(lblhomeico, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblHome)
					.addContainerGap(30, Short.MAX_VALUE))
		);
		homeButton.setLayout(gl_homeButton);
		
		JPanel bookButton = new JPanel();
		bookButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				setColouronMouse(bookButton);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				reSetColouronMouse(bookButton);
			}
		});
		bookButton.setBackground(SystemColor.controlHighlight);
		bookButton.setBounds(420, 200, 164, 143);
		backgroundpanel.add(bookButton);
		
		JLabel lblBooks = new JLabel("Books");
		lblBooks.setForeground(SystemColor.textHighlight);
		lblBooks.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		JLabel lblNewLabel_2 = new JLabel("");
		
		JLabel lblbookico = new JLabel("");
		lblbookico.setIcon(new ImageIcon(home_menu.class.getResource("/main/images/books.png")));
		GroupLayout gl_bookButton = new GroupLayout(bookButton);
		gl_bookButton.setHorizontalGroup(
			gl_bookButton.createParallelGroup(Alignment.TRAILING)
				.addGap(0, 164, Short.MAX_VALUE)
				.addGroup(gl_bookButton.createSequentialGroup()
					.addContainerGap(58, Short.MAX_VALUE)
					.addGroup(gl_bookButton.createParallelGroup(Alignment.LEADING, false)
						.addGroup(Alignment.TRAILING, gl_bookButton.createSequentialGroup()
							.addGap(10)
							.addComponent(lblBooks, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
							.addGap(46))
						.addGroup(gl_bookButton.createSequentialGroup()
							.addComponent(lblNewLabel_2)
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(lblbookico, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
							.addGap(56))))
		);
		gl_bookButton.setVerticalGroup(
			gl_bookButton.createParallelGroup(Alignment.LEADING)
				.addGap(0, 143, Short.MAX_VALUE)
				.addGroup(gl_bookButton.createSequentialGroup()
					.addGap(35)
					.addGroup(gl_bookButton.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblbookico, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblBooks)
					.addContainerGap(30, Short.MAX_VALUE))
		);
		bookButton.setLayout(gl_bookButton);
		
		JPanel memberButton = new JPanel();
		memberButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				setColouronMouse(memberButton);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				reSetColouronMouse(memberButton);
			}
		});
		memberButton.setBackground(SystemColor.controlHighlight);
		memberButton.setBounds(713, 200, 164, 143);
		backgroundpanel.add(memberButton);
		
		JLabel lblMembers = new JLabel("Members");
		lblMembers.setForeground(SystemColor.textHighlight);
		lblMembers.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		JLabel lblNewLabel_3 = new JLabel("");
		
		JLabel lblmembersico = new JLabel("");
		lblmembersico.setIcon(new ImageIcon(home_menu.class.getResource("/main/images/members.png")));
		GroupLayout gl_memberButton = new GroupLayout(memberButton);
		gl_memberButton.setHorizontalGroup(
			gl_memberButton.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_memberButton.createSequentialGroup()
					.addContainerGap(52, Short.MAX_VALUE)
					.addGroup(gl_memberButton.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, gl_memberButton.createSequentialGroup()
							.addComponent(lblmembersico, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblNewLabel_3)
							.addGap(46))
						.addGroup(Alignment.TRAILING, gl_memberButton.createSequentialGroup()
							.addComponent(lblMembers, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE)
							.addGap(36))))
		);
		gl_memberButton.setVerticalGroup(
			gl_memberButton.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_memberButton.createSequentialGroup()
					.addGap(35)
					.addGroup(gl_memberButton.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel_3, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblmembersico, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblMembers)
					.addContainerGap(30, Short.MAX_VALUE))
		);
		memberButton.setLayout(gl_memberButton);
		
		JPanel searchButton = new JPanel();
		searchButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				setColouronMouse(searchButton);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				reSetColouronMouse(searchButton);
			}
		});
		searchButton.setBackground(SystemColor.controlHighlight);
		searchButton.setBounds(987, 200, 164, 143);
		backgroundpanel.add(searchButton);
		
		JLabel lblSearch = new JLabel("Search");
		lblSearch.setForeground(SystemColor.textHighlight);
		lblSearch.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		JLabel lblNewLabel_4 = new JLabel("");
		
		JLabel lbsearchico = new JLabel("");
		lbsearchico.setIcon(new ImageIcon(home_menu.class.getResource("/main/images/search.png")));
		GroupLayout gl_searchButton = new GroupLayout(searchButton);
		gl_searchButton.setHorizontalGroup(
			gl_searchButton.createParallelGroup(Alignment.TRAILING)
				.addGap(0, 164, Short.MAX_VALUE)
				.addGroup(gl_searchButton.createSequentialGroup()
					.addContainerGap(58, Short.MAX_VALUE)
					.addGroup(gl_searchButton.createParallelGroup(Alignment.LEADING, false)
						.addComponent(lblSearch, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_searchButton.createSequentialGroup()
							.addComponent(lblNewLabel_4)
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(lbsearchico, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)))
					.addGap(56))
		);
		gl_searchButton.setVerticalGroup(
			gl_searchButton.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_searchButton.createSequentialGroup()
					.addGap(35)
					.addGroup(gl_searchButton.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel_4, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
						.addComponent(lbsearchico, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblSearch)
					.addContainerGap(30, Short.MAX_VALUE))
		);
		searchButton.setLayout(gl_searchButton);
		
		JPanel updateButton = new JPanel();
		updateButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				setColouronMouse(updateButton);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				reSetColouronMouse(updateButton);
			}
		});
		updateButton.setBackground(SystemColor.controlHighlight);
		updateButton.setBounds(987, 427, 164, 143);
		backgroundpanel.add(updateButton);
		
		JLabel lblUpdate = new JLabel("Update");
		lblUpdate.setForeground(SystemColor.textHighlight);
		lblUpdate.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		JLabel lblNewLabel_5 = new JLabel("");
		
		JLabel lblupdateico = new JLabel("");
		lblupdateico.setIcon(new ImageIcon(home_menu.class.getResource("/main/images/update.png")));
		GroupLayout gl_updateButton = new GroupLayout(updateButton);
		gl_updateButton.setHorizontalGroup(
			gl_updateButton.createParallelGroup(Alignment.TRAILING)
				.addGap(0, 164, Short.MAX_VALUE)
				.addGroup(gl_updateButton.createSequentialGroup()
					.addContainerGap(58, Short.MAX_VALUE)
					.addGroup(gl_updateButton.createParallelGroup(Alignment.LEADING, false)
						.addComponent(lblUpdate, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_updateButton.createSequentialGroup()
							.addComponent(lblNewLabel_5)
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(lblupdateico, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)))
					.addGap(56))
		);
		gl_updateButton.setVerticalGroup(
			gl_updateButton.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_updateButton.createSequentialGroup()
					.addGap(35)
					.addGroup(gl_updateButton.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel_5, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblupdateico, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblUpdate)
					.addContainerGap(30, Short.MAX_VALUE))
		);
		updateButton.setLayout(gl_updateButton);
		
		JPanel deleteButton = new JPanel();
		deleteButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				setColouronMouse(deleteButton);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				reSetColouronMouse(deleteButton);
			}
		});
		deleteButton.setBackground(SystemColor.controlHighlight);
		deleteButton.setBounds(713, 427, 164, 143);
		backgroundpanel.add(deleteButton);
		
		JLabel lblDelete = new JLabel("Delete");
		lblDelete.setForeground(SystemColor.textHighlight);
		lblDelete.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		JLabel lblNewLabel_6 = new JLabel("");
		
		lbldeleteico = new JLabel("");
		lbldeleteico.setIcon(new ImageIcon(home_menu.class.getResource("/main/images/remove.png")));
		GroupLayout gl_deleteButton = new GroupLayout(deleteButton);
		gl_deleteButton.setHorizontalGroup(
			gl_deleteButton.createParallelGroup(Alignment.TRAILING)
				.addGap(0, 164, Short.MAX_VALUE)
				.addGroup(gl_deleteButton.createSequentialGroup()
					.addContainerGap(58, Short.MAX_VALUE)
					.addGroup(gl_deleteButton.createParallelGroup(Alignment.LEADING, false)
						.addComponent(lblDelete, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_deleteButton.createSequentialGroup()
							.addComponent(lblNewLabel_6)
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(lbldeleteico, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)))
					.addGap(56))
		);
		gl_deleteButton.setVerticalGroup(
			gl_deleteButton.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_deleteButton.createSequentialGroup()
					.addGap(35)
					.addGroup(gl_deleteButton.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel_6, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
						.addComponent(lbldeleteico, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblDelete)
					.addContainerGap(32, Short.MAX_VALUE))
		);
		deleteButton.setLayout(gl_deleteButton);
		
		JPanel receivebookButton = new JPanel();
		receivebookButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				setColouronMouse(receivebookButton);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				reSetColouronMouse(receivebookButton);
			}
		});
		receivebookButton.setBackground(SystemColor.controlHighlight);
		receivebookButton.setBounds(420, 427, 164, 143);
		backgroundpanel.add(receivebookButton);
		
		JLabel lblReceivebBooks = new JLabel("Receive Books");
		lblReceivebBooks.setForeground(SystemColor.textHighlight);
		lblReceivebBooks.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		JLabel lblNewLabel_7 = new JLabel("");
		
		JLabel lblreceivebookico = new JLabel("");
		lblreceivebookico.setIcon(new ImageIcon(home_menu.class.getResource("/main/images/recieve.png")));
		GroupLayout gl_receivebookButton = new GroupLayout(receivebookButton);
		gl_receivebookButton.setHorizontalGroup(
			gl_receivebookButton.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_receivebookButton.createSequentialGroup()
					.addContainerGap(37, Short.MAX_VALUE)
					.addGroup(gl_receivebookButton.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_receivebookButton.createSequentialGroup()
							.addComponent(lblNewLabel_7)
							.addGap(18)
							.addComponent(lblreceivebookico, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
							.addGap(59))
						.addGroup(Alignment.TRAILING, gl_receivebookButton.createSequentialGroup()
							.addComponent(lblReceivebBooks)
							.addGap(34))))
		);
		gl_receivebookButton.setVerticalGroup(
			gl_receivebookButton.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_receivebookButton.createSequentialGroup()
					.addGap(35)
					.addGroup(gl_receivebookButton.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel_7, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblreceivebookico, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblReceivebBooks)
					.addContainerGap(30, Short.MAX_VALUE))
		);
		receivebookButton.setLayout(gl_receivebookButton);
		
		JPanel lendbookButton = new JPanel();
		lendbookButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				setColouronMouse(lendbookButton);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				reSetColouronMouse(lendbookButton);
			}
		});
		lendbookButton.setBackground(SystemColor.controlHighlight);
		lendbookButton.setBounds(120, 427, 164, 143);
		backgroundpanel.add(lendbookButton);
		
		JLabel lblLendBooks = new JLabel("Lend Books");
		lblLendBooks.setForeground(SystemColor.textHighlight);
		lblLendBooks.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		JLabel lblNewLabel_8 = new JLabel("");
		
		JLabel lbllendbookico = new JLabel("");
		lbllendbookico.setIcon(new ImageIcon(home_menu.class.getResource("/main/images/borrow.png")));
		GroupLayout gl_lendbookButton = new GroupLayout(lendbookButton);
		gl_lendbookButton.setHorizontalGroup(
			gl_lendbookButton.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_lendbookButton.createSequentialGroup()
					.addContainerGap(44, Short.MAX_VALUE)
					.addGroup(gl_lendbookButton.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, gl_lendbookButton.createSequentialGroup()
							.addComponent(lbllendbookico, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblNewLabel_8)
							.addGap(46))
						.addGroup(Alignment.TRAILING, gl_lendbookButton.createSequentialGroup()
							.addComponent(lblLendBooks, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
							.addGap(34))))
		);
		gl_lendbookButton.setVerticalGroup(
			gl_lendbookButton.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_lendbookButton.createSequentialGroup()
					.addGap(35)
					.addGroup(gl_lendbookButton.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel_8, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
						.addComponent(lbllendbookico, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblLendBooks)
					.addContainerGap(30, Short.MAX_VALUE))
		);
		lendbookButton.setLayout(gl_lendbookButton);
	}
}
