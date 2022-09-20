package bookStoreSystem;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.JSpinner;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JSeparator;

public class BookDetail extends JFrame {

	private JPanel contentPane;
	int id;
	int qty;
	double price ;
	double totalPrice;
	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public BookDetail(ArrayList<String> data, boolean isAdmin,int user_id) {

		id = Integer.parseInt((String) data.get(0));

		setTitle("Book Detail Pane");
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

		JLabel lbl1 = new JLabel("New label");
		lbl1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lbl1.setForeground(new Color(255, 255, 255));
		lbl1.setBounds(374, 22, 140, 31);
		contentPane.add(lbl1);

		JLabel lblNewLabel_1_1 = new JLabel("Author");
		lblNewLabel_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1_1.setBounds(227, 22, 140, 31);
		contentPane.add(lblNewLabel_1_1);

		JLabel lblNewLabel_1_1_1 = new JLabel("Title");
		lblNewLabel_1_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1_1_1.setBounds(227, 64, 140, 31);
		contentPane.add(lblNewLabel_1_1_1);

		JLabel lbl2 = new JLabel("New label");
		lbl2.setForeground(Color.WHITE);
		lbl2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lbl2.setBounds(374, 64, 140, 31);
		contentPane.add(lbl2);

		JLabel lblNewLabel_1_1_1_2 = new JLabel("Release Date");
		lblNewLabel_1_1_1_2.setForeground(Color.WHITE);
		lblNewLabel_1_1_1_2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1_1_1_2.setBounds(227, 106, 140, 31);
		contentPane.add(lblNewLabel_1_1_1_2);

		JLabel lblNewLabel_1_1_1_4_1 = new JLabel("Buy");
		lblNewLabel_1_1_1_4_1.setForeground(Color.WHITE);
		lblNewLabel_1_1_1_4_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1_1_1_4_1.setBounds(227, 230, 140, 31);
		contentPane.add(lblNewLabel_1_1_1_4_1);

		JLabel lblNewLabel_1_1_1_4_2 = new JLabel("Price");
		lblNewLabel_1_1_1_4_2.setForeground(Color.WHITE);
		lblNewLabel_1_1_1_4_2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1_1_1_4_2.setBounds(227, 146, 140, 31);
		contentPane.add(lblNewLabel_1_1_1_4_2);

		JLabel lbl5 = new JLabel((String) null);
		lbl5.setForeground(Color.WHITE);
		lbl5.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lbl5.setBounds(374, 188, 140, 31);
		contentPane.add(lbl5);

		JLabel lbl3 = new JLabel("New label");
		lbl3.setForeground(Color.WHITE);
		lbl3.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lbl3.setBounds(374, 106, 140, 31);
		contentPane.add(lbl3);

		JLabel lblNewLabel_1_1_1_4 = new JLabel("Quantity");
		lblNewLabel_1_1_1_4.setForeground(Color.WHITE);
		lblNewLabel_1_1_1_4.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1_1_1_4.setBounds(227, 188, 140, 31);
		contentPane.add(lblNewLabel_1_1_1_4);

		JLabel lbl4 = new JLabel("");
		lbl4.setForeground(Color.WHITE);
		lbl4.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lbl4.setBounds(374, 146, 140, 31);
		contentPane.add(lbl4);

		JSpinner spinner = new JSpinner();
		spinner.setBounds(377, 236, 58, 20);
		contentPane.add(spinner);

		lbl1.setText((String) data.get(1)); // author
		lbl2.setText((String) data.get(2)); // title
		lbl3.setText((String) data.get(3)); // release date
		lbl4.setText((String) data.get(4) + " $"); // price
		lbl5.setText((String) data.get(5)); // qty

		JButton btnSell = new JButton("Sell");
		btnSell.setBounds(213, 295, 89, 23);
		contentPane.add(btnSell);

		JSeparator separator = new JSeparator();
		separator.setBounds(374, 217, 204, 2);
		contentPane.add(separator);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(374, 175, 204, 2);
		contentPane.add(separator_1);

		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(374, 135, 204, 2);
		contentPane.add(separator_2);

		JSeparator separator_4 = new JSeparator();
		separator_4.setBounds(374, 45, 204, 2);
		contentPane.add(separator_4);

		JSeparator separator_2_1 = new JSeparator();
		separator_2_1.setBounds(374, 93, 204, 2);
		contentPane.add(separator_2_1);

		//////////

		try {
			Connection con = DbConnection.initializeDatabase();
			String sql = "Select qty,image,price from books where id=" + id; // id is book_id
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				qty = rs.getInt("qty");
				price = rs.getDouble("price");
				byte[] imgB = rs.getBytes("image");
				Image img = new ImageIcon(imgB).getImage().getScaledInstance(133, 200, Image.SCALE_SMOOTH);
				ImageIcon imgIcon = new ImageIcon(img);
				lblIcon.setIcon(imgIcon);
				if (qty <= 0) {
					btnSell.setEnabled(false);
				}
			}

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		////////////////// BOOK SELL ///////////////////////////////
		btnSell.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				///////////
				int count = Integer.parseInt(spinner.getValue().toString());
				if (count > 0) {
					qty -= count;

					if (qty >= 0) {
						System.out.println("ik");
						try {
							Connection con = DbConnection.initializeDatabase();
							String sql = "UPDATE books SET qty=? WHERE id=?";
							PreparedStatement stmt = con.prepareStatement(sql);
							stmt.setInt(1, qty);
							stmt.setInt(2, id);
							data.add(5, String.valueOf(qty));
						
							int result = JOptionPane.showConfirmDialog(null, "Are you want to sure to Sell Item");
							if (result == JOptionPane.OK_OPTION) {
								stmt.executeUpdate();
								
								String orderSql = "INSERT INTO orders (seller_id,book_id,total_price,qty) VALUES (?,?,?,?)";
							PreparedStatement orderStmt =	con.prepareStatement(orderSql);
							totalPrice = price * count;
							orderStmt.setInt(1, user_id);	
							orderStmt.setInt(2, id);	
							orderStmt.setDouble(3, totalPrice);	
							orderStmt.setInt(4, count);	
							orderStmt.executeUpdate();
								JOptionPane.showMessageDialog(null, "you are successfully buy " + count + " book");
							}

						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

					} else {
						JOptionPane.showMessageDialog(null, "not enough book");
					}

				} else {
					JOptionPane.showMessageDialog(null, "Buy something please");
				}
				BookDetail.this.setVisible(false);
				BookDetail bd = new BookDetail(data, isAdmin, user_id);
				bd.setVisible(true);
			}
		});

		// >>>end construct<<<
	}
}
