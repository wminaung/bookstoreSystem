package bookStoreSystem;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;

import java.sql.Connection;
import java.sql.PreparedStatement;

import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class RegisterUser extends JFrame {

	private JPanel contentPane;
	private JTextField txt1;
	private JTextField txt2;
	private JTextField txt3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterUser frame = new RegisterUser();
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
	public RegisterUser() {
		setTitle("Register User");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 455, 336);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(5, 74, 145));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Name : ");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBackground(new Color(255, 255, 255));
		lblNewLabel.setBounds(58, 68, 71, 20);
		contentPane.add(lblNewLabel);

		JLabel lblEmail = new JLabel("Email :");
		lblEmail.setForeground(Color.WHITE);
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblEmail.setBackground(Color.WHITE);
		lblEmail.setBounds(58, 110, 71, 20);
		contentPane.add(lblEmail);

		JLabel lblPassword = new JLabel("Password : ");
		lblPassword.setForeground(Color.WHITE);
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblPassword.setBackground(Color.WHITE);
		lblPassword.setBounds(58, 152, 71, 20);
		contentPane.add(lblPassword);

		JLabel lblRole = new JLabel(" Role : ");
		lblRole.setForeground(Color.WHITE);
		lblRole.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblRole.setBackground(Color.WHITE);
		lblRole.setBounds(58, 200, 71, 20);
		contentPane.add(lblRole);

		txt1 = new JTextField();
		txt1.setBounds(203, 69, 157, 20);
		contentPane.add(txt1);
		txt1.setColumns(10);

		txt2 = new JTextField();
		txt2.setColumns(10);
		txt2.setBounds(203, 111, 157, 20);
		contentPane.add(txt2);

		txt3 = new JTextField();
		txt3.setColumns(10);
		txt3.setBounds(203, 153, 157, 20);
		contentPane.add(txt3);

		JCheckBox adminCheckBox = new JCheckBox("admin");
		adminCheckBox.setFont(new Font("Tahoma", Font.BOLD, 13));
		adminCheckBox.setForeground(Color.WHITE);
		adminCheckBox.setBackground(new Color(5, 74, 145));
		adminCheckBox.setBounds(208, 200, 97, 23);
		contentPane.add(adminCheckBox);

		JButton btnNewButton = new JButton("Register");
		btnNewButton.setBounds(161, 242, 89, 23);
		contentPane.add(btnNewButton);
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String inputName = txt1.getText();
				String inputEmail = txt2.getText();
				String inputPassword = txt3.getText();
				int checkAdmin = adminCheckBox.isSelected() ? 0 : 1;
				
				if(inputName.equals("") || inputEmail.equals("") || inputPassword.equals("") ) {
					JOptionPane.showMessageDialog(null, "registration can't be null", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				Connection con;
				try {
					con = DbConnection.initializeDatabase();
					String sql = "INSERT INTO users (name, email, password, role) values(?,?,?,?)";
					PreparedStatement stmt = con.prepareStatement(sql);
					stmt.setString(1, inputName);
					stmt.setString(2, inputEmail);
					stmt.setString(3, inputPassword);
					stmt.setInt(4, checkAdmin);
					stmt.executeUpdate();
					JOptionPane.showMessageDialog(null, "Registration Succeeded!");
					txt1.setText("");
					txt2.setText("");
					txt3.setText("");
					adminCheckBox.setSelected(false);
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
	
	// >>>end construct<<<	
	}
	
//	///// refresh ////////
//	private static void refresh(RegisterUser sau) {
//		sau.setVisible(false);
//		sau = new RegisterUser();
//		sau.setVisible(true);
//	}
	
	// >>>end class<<<
}
