package bookStoreSystem;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class AdminLogin extends JFrame {

	private JPanel contentPane;
	private JTextField txt1;
	private JTextField txt2;
	private JPasswordField pwd;
	boolean isAdmin = false;

	/**
	 * Create the frame.
	 */
	public AdminLogin() {
		setTitle("Admin Login Page");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(5, 74, 145));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Admin Login");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Cascadia Code", Font.BOLD, 21));
		lblNewLabel.setBounds(134, 27, 196, 30);
		contentPane.add(lblNewLabel);

		JLabel lblName = new JLabel("Name : ");
		lblName.setForeground(Color.WHITE);
		lblName.setFont(new Font("Cascadia Code", Font.PLAIN, 12));
		lblName.setBounds(36, 76, 86, 30);
		contentPane.add(lblName);

		JLabel lblEmail = new JLabel("Email :");
		lblEmail.setForeground(Color.WHITE);
		lblEmail.setFont(new Font("Cascadia Code", Font.PLAIN, 12));
		lblEmail.setBounds(36, 117, 86, 30);
		contentPane.add(lblEmail);

		JLabel lblPassword = new JLabel("Password :");
		lblPassword.setForeground(Color.WHITE);
		lblPassword.setFont(new Font("Cascadia Code", Font.PLAIN, 12));
		lblPassword.setBounds(36, 158, 86, 30);
		contentPane.add(lblPassword);

		txt1 = new JTextField();
		txt1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					txt2.requestFocus();
				}
			}
		});
		txt1.setBounds(189, 82, 163, 20);
		contentPane.add(txt1);
		txt1.setColumns(10);

		txt2 = new JTextField();
		txt2.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					pwd.requestFocus();
				}
			}
		});
		txt2.setColumns(10);
		txt2.setBounds(189, 123, 163, 20);
		contentPane.add(txt2);

		JButton btnLogin = new JButton("Login");
		btnLogin.setBounds(181, 211, 89, 23);
		contentPane.add(btnLogin);

		pwd = new JPasswordField();
		pwd.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					btnLogin.requestFocus();
				}
			}
		});
		pwd.setBounds(189, 164, 163, 20);
		contentPane.add(pwd);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(new Color(230, 230, 250));
		menuBar.setBounds(0, 0, 49, 20);
		contentPane.add(menuBar);
		
		JButton btnNewButton = new JButton("<<");
		btnNewButton.setFocusable(false);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/////// BACK TO MAIN PAGE ///////
				AdminLogin.this.setVisible(false);
				Main main = new Main();
				main.setVisible(true);
			}
		});
		btnNewButton.setToolTipText("Back");
		menuBar.add(btnNewButton);




		//////////////////////// if login ////////////////////////////
		btnLogin.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					login(txt1, txt2, pwd, isAdmin, AdminLogin.this);
					return;
				}
			}
		});

		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//////////// LOGIN BTN ////////////
				login(txt1, txt2, pwd, isAdmin, AdminLogin.this);
				return;
			}
		});
		// >>>end construct<<<
	}

	public static void login(JTextField txt1, JTextField txt2, JPasswordField pwd, boolean isAdmin, AdminLogin al) {

		try {
			Connection con = DbConnection.initializeDatabase();
			String sql = "select * from users where role=0";
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();

			String name = txt1.getText();
			String email = txt2.getText();
			String password = String.valueOf(pwd.getPassword());
			boolean isValid = true;
			while (rs.next()) {
				int user_id = rs.getInt("id");
				String usrName = rs.getString("name");
				String usrEmail = rs.getString("email");
				String usrPass = rs.getString("password");

				if (usrName.equals(name) && usrEmail.equals(email) && usrPass.equals(password)) {
					System.out.println("you are now login");
					// go CURD operation page
					isAdmin = true;
					AdminView adminView = new AdminView(isAdmin, user_id);
					adminView.setVisible(true);
					al.setVisible(false);
					return;

				} else {
					isValid = false;
				}

			}
			if (!isValid) {
				JOptionPane.showMessageDialog(null, "You are something wroung", "error", JOptionPane.ERROR_MESSAGE);
			}

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();

		}

	}
}
