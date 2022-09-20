package bookStoreSystem;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class BookUpdate extends JFrame {
	 int id;
	private JPanel contentPane;
	private Connection con;
	private FileInputStream fis;
	private byte [] ok;
	private JTextField txtTitle;
	private JTextField txtRD;
	private JTextField txtAuthor;
	private JTextField txtPrice;
	private JTextField txtQty;
	private boolean isBrowse = false;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public BookUpdate(int id) {
		this.id = id;
		setTitle("Book Update Page");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 622, 391);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(5, 74, 145));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblIcon = new JLabel("");
		lblIcon.setBounds(10, 45, 133, 200);
		contentPane.add(lblIcon);

		JLabel lblNewLabel_1_1_1 = new JLabel("Title");
		lblNewLabel_1_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1_1_1.setBounds(227, 91, 140, 31);
		contentPane.add(lblNewLabel_1_1_1);

		JLabel lblNewLabel_1_1_1_2 = new JLabel("Release Date");
		lblNewLabel_1_1_1_2.setForeground(Color.WHITE);
		lblNewLabel_1_1_1_2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1_1_1_2.setBounds(227, 133, 140, 31);
		contentPane.add(lblNewLabel_1_1_1_2);

		JLabel lblNewLabel_1_1_1_4 = new JLabel("Quantity");
		lblNewLabel_1_1_1_4.setForeground(Color.WHITE);
		lblNewLabel_1_1_1_4.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1_1_1_4.setBounds(227, 214, 140, 31);
		contentPane.add(lblNewLabel_1_1_1_4);

//		lbl1.setText((String) data.get(1));
//		lbl2.setText((String) data.get(2));
//		lbl3.setText((String) data.get(3));

		JButton btnUpdate = new JButton("Update");
		btnUpdate.setBounds(213, 295, 89, 23);
		contentPane.add(btnUpdate);

		txtTitle = new JTextField();
		txtTitle.setBounds(377, 97, 137, 20);
		contentPane.add(txtTitle);
		txtTitle.setColumns(10);

		txtRD = new JTextField();
		txtRD.setColumns(10);
		txtRD.setBounds(377, 139, 137, 20);
		contentPane.add(txtRD);

		JLabel lblNewLabel_1_1_1_1 = new JLabel("Author");
		lblNewLabel_1_1_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1_1_1_1.setBounds(227, 45, 140, 31);
		contentPane.add(lblNewLabel_1_1_1_1);

		txtAuthor = new JTextField();
		txtAuthor.setColumns(10);
		txtAuthor.setBounds(377, 45, 137, 20);
		contentPane.add(txtAuthor);

		JLabel lblPricesss = new JLabel("Price");
		lblPricesss.setForeground(Color.WHITE);
		lblPricesss.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblPricesss.setBounds(227, 175, 140, 31);
		contentPane.add(lblPricesss);

		txtPrice = new JTextField();
		txtPrice.setColumns(10);
		txtPrice.setBounds(377, 181, 137, 20);
		contentPane.add(txtPrice);

		txtQty = new JTextField();
		txtQty.setColumns(10);
		txtQty.setBounds(377, 220, 137, 20);
		contentPane.add(txtQty);

		JButton btnBrowse = new JButton("browse");

		btnBrowse.setBounds(32, 253, 89, 23);
		contentPane.add(btnBrowse);

		//////////

		try {
			con = DbConnection.initializeDatabase();
			String sql = "Select * from books where id=" + id;
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				 ok = rs.getBytes("image");
				Image img = new ImageIcon(ok).getImage().getScaledInstance(133, 200, Image.SCALE_SMOOTH);
				lblIcon.setIcon(new ImageIcon(img));
				// name , title , released , price , qty
				txtAuthor.setText(rs.getString("author"));
				txtTitle.setText(rs.getString("title"));
				txtRD.setText(rs.getString("releaseDate"));
				txtPrice.setText(rs.getDouble("price") + "");
				txtQty.setText(rs.getInt("qty") + "");

			}

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		/////////////////
		btnBrowse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				////////////
				JFileChooser chooser = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter("*.images", "png", "jpg", "gif", "jfif");
				chooser.addChoosableFileFilter(filter);
				int result = chooser.showSaveDialog(null);
				if (result == JFileChooser.APPROVE_OPTION) {
					String path = chooser.getSelectedFile().getAbsolutePath();
					File file = new File(path);
					isBrowse = true;
					try {
						fis = new FileInputStream(file);
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}else if(result == JOptionPane.CANCEL_OPTION || result == JOptionPane.CLOSED_OPTION) {
					isBrowse = false;
				}

			}
		});

		//////////////////
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//////////////
				try {
					con = DbConnection.initializeDatabase();
					String sql = "UPDATE books SET author=?, title=?, releaseDate=?, price=?, qty=?, image=? WHERE id="
							+ id;
					PreparedStatement stmt = con.prepareStatement(sql);
					stmt.setString(1, txtAuthor.getText());
					stmt.setString(2, txtTitle.getText());
					stmt.setString(3, txtRD.getText());
					stmt.setDouble(4, Double.parseDouble(txtPrice.getText()));
					stmt.setInt(5, Integer.parseInt(txtQty.getText()));
				//	System.out.println(fis.readAllBytes().length);
					if(isBrowse) {
						stmt.setBytes(6,  fis.readAllBytes());
					}else {
						stmt.setBytes(6, ok);
					}
					

					 stmt.executeUpdate();
					int scd = JOptionPane.showConfirmDialog(null, "Are You sure? Want to Update");
					if (scd == JOptionPane.YES_OPTION) {
						JOptionPane.showMessageDialog(null, "Successfully Updated");
						BookUpdate.this.setVisible(false);
						BookUpdate bu = new BookUpdate(id);
						bu.setVisible(true);
					}

				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});


		// >>>end construct<<<
	}
}
