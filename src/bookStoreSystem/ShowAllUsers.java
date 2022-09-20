package bookStoreSystem;

import java.awt.Color;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import javax.swing.border.EmptyBorder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;

public class ShowAllUsers extends JFrame {
	Connection con;
	private JPanel contentPane;
	private JTable table;
	private JMenuBar menuBar;
	private JMenu mnNewMenu;
	private JMenuItem registerMenuItem;
	private JLabel lblNewLabel;
	private JLabel lblEmail;
	private JLabel lblPassword;
	private JLabel lblRole;
	private JTextField txt1;
	private JTextField txt2;
	private JTextField txt3;
	private JCheckBox chckbxNewCheckBox;
	private JButton updateBtn;
	private JButton deleteBtn;


	
	int selectedRowId ;
	private JSeparator separator_1;
	private JMenuItem refreshMenuItem;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ShowAllUsers frame = new ShowAllUsers();
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
	public ShowAllUsers() {
		setTitle("Show All Users");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 817, 385);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(5, 74, 145));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(32, 39, 315, 272);
		contentPane.add(scrollPane);

		table = new JTable();
		table.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "ID", "Name", "Email", "Role" }));
		scrollPane.setViewportView(table);

		menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 101, 22);
		contentPane.add(menuBar);

		mnNewMenu = new JMenu("Users");
		menuBar.add(mnNewMenu);

		registerMenuItem = new JMenuItem("Register");
		mnNewMenu.add(registerMenuItem);
		
		refreshMenuItem = new JMenuItem("");
		refreshMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				refresh(ShowAllUsers.this);
			}
		});
		refreshMenuItem.setIcon(new ImageIcon("C:\\Users\\Qung\\eclipse-workspace\\BookStoreSystem\\public\\image\\refresh-button.png"));
		menuBar.add(refreshMenuItem);

		lblNewLabel = new JLabel("Name : ");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel.setBounds(442, 72, 68, 16);
		contentPane.add(lblNewLabel);

		lblEmail = new JLabel("Email : ");
		lblEmail.setForeground(Color.WHITE);
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblEmail.setBounds(442, 118, 68, 16);
		contentPane.add(lblEmail);

		lblPassword = new JLabel("Password : ");
		lblPassword.setForeground(Color.WHITE);
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblPassword.setBounds(442, 165, 68, 16);
		contentPane.add(lblPassword);

		lblRole = new JLabel("Role : ");
		lblRole.setForeground(Color.WHITE);
		lblRole.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblRole.setBounds(442, 206, 68, 16);
		contentPane.add(lblRole);

		txt1 = new JTextField();
		txt1.setBounds(566, 71, 170, 20);
		contentPane.add(txt1);
		txt1.setColumns(10);

		txt2 = new JTextField();
		txt2.setColumns(10);
		txt2.setBounds(566, 117, 170, 20);
		contentPane.add(txt2);

		txt3 = new JTextField();
		txt3.setColumns(10);
		txt3.setBounds(566, 164, 170, 20);
		contentPane.add(txt3);

		chckbxNewCheckBox = new JCheckBox("Admin");
		chckbxNewCheckBox.setForeground(Color.WHITE);
		chckbxNewCheckBox.setFont(new Font("Tahoma", Font.BOLD, 13));
		chckbxNewCheckBox.setBackground(new Color(5, 74, 145));
		chckbxNewCheckBox.setBounds(566, 204, 97, 23);
		contentPane.add(chckbxNewCheckBox);

		updateBtn = new JButton("Update");
		updateBtn.setFont(new Font("Tahoma", Font.BOLD, 12));
		updateBtn.setBounds(485, 273, 89, 23);
		contentPane.add(updateBtn);

		deleteBtn = new JButton("Delete");
		deleteBtn.setFont(new Font("Tahoma", Font.BOLD, 12));
		deleteBtn.setForeground(Color.WHITE);
		deleteBtn.setBackground(Color.RED);
		deleteBtn.setBounds(609, 273, 89, 23);
		contentPane.add(deleteBtn);

		try {
			 con = DbConnection.initializeDatabase();
			PreparedStatement stmt = con.prepareStatement("SELECT * FROM users");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				String role = rs.getInt("role") == 0 ? "Admin" : "staff";

				((DefaultTableModel) table.getModel())
						.addRow(new Object[] { rs.getInt("id"), rs.getString("name"), rs.getString("email"), role });
			}
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		registerMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegisterUser ru = new RegisterUser();
				ru.setVisible(true);
			}
		});

	

		
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(413, 26, 9, 309);
		contentPane.add(separator);
		
		separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setBounds(590, 273, 9, 24);
		contentPane.add(separator_1);
	
	
		///////////////////////////
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				if (table.getSelectedRow() != -1) {
					int row = table.getSelectedRow();
					
					int id = (int) table.getValueAt(row, 0); // get table id column
					selectedRowId = id;
				

					try {
						con = DbConnection.initializeDatabase();
						PreparedStatement stmt = con.prepareStatement("SELECT * FROM users WHERE id=" + id);
						ResultSet rs = stmt.executeQuery();
						String name = null;
						String email = null;
						String passwrod = null;
						int role = 1;
						while (rs.next()) {
							name = rs.getString("name");
							email = rs.getString("email");
							passwrod = rs.getString("password");
							role = rs.getInt("role");
						}
						txt1.setText(name);
						txt2.setText(email);
						txt3.setText(passwrod);
						boolean isSelected = role == 0 ? true : false;
						chckbxNewCheckBox.setSelected(isSelected);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				

				}
				
			
			}
		});

//////////////// update button ////////////////
	
		updateBtn.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				int id = selectedRowId;
				
				if(selectedRowId != 0) {
					String name = txt1.getText();
					String email = txt2.getText();
					String password = txt3.getText();
					int role = chckbxNewCheckBox.isSelected() == true ? 0 : 1;
					try {
						con = DbConnection.initializeDatabase();
						String sql = "UPDATE users SET name=?,email=?,password=?,role=? WHERE id=?";
						PreparedStatement stmt = con.prepareStatement(sql);
						stmt.setString(1, name);
						stmt.setString(2, email);
						stmt.setString(3, password);
						stmt.setInt(4, role);
						stmt.setInt(5, id);
						stmt.executeUpdate();
						JOptionPane.showMessageDialog(null, id + "is Updated Successfully!");
						refresh(ShowAllUsers.this);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}else {
					JOptionPane.showMessageDialog(null, "You need to select first for update","error", JOptionPane.ERROR_MESSAGE);
				
				}
				
				
			}
		});
		
		
		////////// delete button /////////////////
		deleteBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id = selectedRowId;
				if(selectedRowId != 0) {
					try {
						con = DbConnection.initializeDatabase();
						String sql = "DELETE FROM users WHERE id=?";
						PreparedStatement stmt = con.prepareStatement(sql);
						stmt.setInt(1, id);
						stmt.executeUpdate();
						JOptionPane.showMessageDialog(null, id + " is Deleted successfully!");
					
						refresh(ShowAllUsers.this);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}else {
					JOptionPane.showMessageDialog(null, "You need to select first to delete","error", JOptionPane.ERROR_MESSAGE);
				
				}
			}
		});
		

		// >>>end construct<<<
	}

	///// refresh ////////
	private static void refresh(ShowAllUsers sau) {
		sau.setVisible(false);
		sau = new ShowAllUsers();
		sau.setVisible(true);
	}
	
// >>>end class<<<
}
