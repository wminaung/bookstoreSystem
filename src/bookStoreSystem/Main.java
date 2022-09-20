package bookStoreSystem;

import java.awt.EventQueue;
import java.awt.Image;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Main extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
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
	public Main() {
		setTitle("Book Store Main Page");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 527, 375);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(5, 74, 145));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblOnlineBookStoreimg = new JLabel("");
		Image img = new ImageIcon("C:\\Users\\Qung\\Desktop\\My Java Lib\\ONline-book-store.jpg").getImage().getScaledInstance(360, 215,  Image.SCALE_SMOOTH);
		ImageIcon imgicon = new ImageIcon(img);
		lblOnlineBookStoreimg.setIcon(imgicon);
		lblOnlineBookStoreimg.setBounds(70, 11, 433, 201);
		lblOnlineBookStoreimg.setSize(360, 215);
		contentPane.add(lblOnlineBookStoreimg);
		
		JButton btnAdmin = new JButton("Admin");
		btnAdmin.setBounds(104, 267, 89, 23);
		contentPane.add(btnAdmin);
		
		JButton btnStaff = new JButton("Staff");
		btnStaff.setBounds(300, 267, 89, 23);
		contentPane.add(btnStaff);
		

		///////////////////////// admin btn ////////////////////////////
		btnAdmin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				///////////////////////////////
				AdminLogin admin = new AdminLogin();
				admin.setVisible(true);
				Main.this.setVisible(false);
			}
		});
		
		///////////////////// staff btn /////////////////////////////
		btnStaff.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//////////////////////////
				StaffLogin sl = new StaffLogin();
				sl.setVisible(true);
				Main.this.setVisible(false);
			}
		});
		
		// >> end construct <<
	}
	// >> end class <<
}
