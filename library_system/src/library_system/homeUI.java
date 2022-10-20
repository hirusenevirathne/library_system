package library_system;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class homeUI extends JFrame {

	private JPanel contentPane;

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

	/**
	 * Create the frame.
	 */
	public homeUI() {
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
		//Back to Home BUtton ENDS 
	}

}
