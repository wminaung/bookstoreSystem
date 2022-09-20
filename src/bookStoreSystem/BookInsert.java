package bookStoreSystem;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JSeparator;

public class BookInsert extends JFrame {
	boolean isBrowse = false;
	FileInputStream fis;
	private JPanel contentPane;
	private JTextField txt1;
	private JTextField txt2;
	private JTextField txt3;
	private JTextField txt4;
	private JTextField txt5;
	boolean isDouble = true;
	boolean isInt = true;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					BookInsert frame = new BookInsert();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public BookInsert() {
		setTitle("Insert Book");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 384, 389);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(5, 74, 145));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Insert Book");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Cascadia Code", Font.BOLD, 16));
		lblNewLabel.setBounds(131, 11, 117, 27);
		contentPane.add(lblNewLabel);

		JLabel lblAuthor = new JLabel("Author :");
		lblAuthor.setForeground(new Color(255, 255, 255));
		lblAuthor.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblAuthor.setBounds(25, 73, 95, 27);
		contentPane.add(lblAuthor);

		JLabel lblTitle = new JLabel("Title :");
		lblTitle.setForeground(Color.WHITE);
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTitle.setBounds(25, 110, 95, 27);
		contentPane.add(lblTitle);

		JLabel lblReleaseDate = new JLabel("Release Date :");
		lblReleaseDate.setForeground(Color.WHITE);
		lblReleaseDate.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblReleaseDate.setBounds(25, 148, 95, 27);
		contentPane.add(lblReleaseDate);

		JLabel lblPrice = new JLabel("Price :");
		lblPrice.setForeground(Color.WHITE);
		lblPrice.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblPrice.setBounds(25, 186, 95, 27);
		contentPane.add(lblPrice);

		JLabel lblQty = new JLabel("Qty :");
		lblQty.setForeground(Color.WHITE);
		lblQty.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblQty.setBounds(25, 224, 95, 27);
		contentPane.add(lblQty);

		JButton btnBrowse = new JButton("browse");
		btnBrowse.setFont(new Font("Segoe Script", Font.BOLD, 11));
		btnBrowse.setBounds(186, 270, 75, 17);
		contentPane.add(btnBrowse);

		JButton btnSubmit = new JButton("Submit");
		btnSubmit.setFont(new Font("Cambria", Font.BOLD, 11));
		btnSubmit.setBounds(136, 316, 89, 23);
		contentPane.add(btnSubmit);

		txt1 = new JTextField();
		txt1.setBounds(177, 77, 139, 20);
		contentPane.add(txt1);
		txt1.setColumns(10);

		txt2 = new JTextField();
		txt2.setColumns(10);
		txt2.setBounds(177, 114, 139, 20);
		contentPane.add(txt2);

		txt3 = new JTextField();
		txt3.setColumns(10);
		txt3.setBounds(177, 152, 139, 20);
		contentPane.add(txt3);

		txt4 = new JTextField();
		txt4.setColumns(10);
		txt4.setBounds(177, 190, 139, 20);
		contentPane.add(txt4);

		txt5 = new JTextField();
		txt5.setColumns(10);
		txt5.setBounds(177, 228, 139, 20);
		contentPane.add(txt5);

		JSeparator separator = new JSeparator();
		separator.setBackground(new Color(0, 0, 255));
		separator.setForeground(new Color(0, 0, 0));
		separator.setBounds(29, 298, 304, 10);
		contentPane.add(separator);
		
		JLabel lblImage = new JLabel("Image :");
		lblImage.setForeground(Color.WHITE);
		lblImage.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblImage.setBounds(100, 263, 89, 27);
		contentPane.add(lblImage);

		// browse image
		btnBrowse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter("*.images", "png", "jpg", "gif", "jfif");
				chooser.addChoosableFileFilter(filter);
				int isChoose = chooser.showSaveDialog(null);
				if (isChoose == JFileChooser.APPROVE_OPTION) {
					String path = chooser.getSelectedFile().getAbsolutePath();
					File file = new File(path);
					try {
						fis = new FileInputStream(file);
						isBrowse = true;
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else {
					isBrowse = false;
					JOptionPane.showMessageDialog(null, "You need to Select image", "warning",
							JOptionPane.WARNING_MESSAGE);
				}

			}
		});

		// in submit
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] input = { txt1.getText(), txt2.getText(), txt3.getText(), txt4.getText(), txt5.getText() };

				try {
					Connection con = DbConnection.initializeDatabase();
					String sql = "INSERT INTO books (author,title,releaseDate,price,qty,image) VALUES (?,?,?,?,?,?)";
					PreparedStatement stmt = con.prepareStatement(sql);
					stmt.setString(1, input[0]);
					stmt.setString(2, input[1]);
					stmt.setString(3, input[2]);
					try {
						Double.parseDouble(input[3]);
					} catch (Exception e2) {
						// TODO: handle exception
						isDouble = false;
					}
					try {
						Integer.parseInt(input[4]);
					} catch (Exception e2) {
						isInt = false;
					}

					if(isDouble && isInt) {
						stmt.setDouble(4, Double.parseDouble(input[3]));
						stmt.setInt(5, Integer.parseInt(input[4]));
						try {
							stmt.setBytes(6, fis.readAllBytes());
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
							JOptionPane.showMessageDialog(null, "Put right Image such as \"png\", \"jpg\", \"gif\", \"jfif\" " );
						}
						stmt.executeUpdate();
						JOptionPane.showMessageDialog(null, "You are Successfully Inserted ", "Success", JOptionPane.PLAIN_MESSAGE);
						BookInsert.this.setVisible(false);
						BookInsert bi = new BookInsert();
						bi.setVisible(true);
					}else {
						JOptionPane.showMessageDialog(null, "something wroung Please Try Again!!","Error", JOptionPane.ERROR_MESSAGE);
					}

				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});

		// >>>end construct<<<
	}
}
