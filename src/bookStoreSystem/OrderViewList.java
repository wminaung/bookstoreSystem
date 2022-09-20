package bookStoreSystem;



import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.Vector;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JComboBox;

public class OrderViewList extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField searchTextField;
	int choose = 1;

	/**
	 * Create the frame.
	 */

	public OrderViewList(boolean isAdmin) {
		setTitle("Order listing");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 673, 371);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(5, 74, 145));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Order List");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(267, 11, 174, 28);
		contentPane.add(lblNewLabel);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(42, 77, 568, 210);
		contentPane.add(scrollPane);

		String[] allCol = { "ID", "Seller", "Book Name", "Qty", "Total Price", "Date" };

		table = new JTable();
		table.setModel(new DefaultTableModel(new Object[][] {}, allCol) {
		
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		});
		scrollPane.setViewportView(table);
		DefaultTableModel model = ((DefaultTableModel) table.getModel());

		
		searchTextField = new JTextField();
		searchTextField.setBounds(42, 46, 144, 20);
		contentPane.add(searchTextField);
		searchTextField.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel(" with : ");
		lblNewLabel_1.setForeground(Color.LIGHT_GRAY);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1.setBounds(206, 49, 56, 14);
		contentPane.add(lblNewLabel_1);

		// allCol -> { "ID", "Seller", "Book Name", "Qty", "Total Price", "Date" }
		JComboBox comboBox = new JComboBox(allCol);
		comboBox.setBounds(272, 44, 104, 22);
		contentPane.add(comboBox);
		comboBox.setSelectedItem("Seller");

		Vector<Vector<String>> allRow = new Vector<>(); /////////////// all row

		Connection con;
		try {
			con = DbConnection.initializeDatabase();
			String sql = "SELECT * FROM orders ORDER BY id DESC";
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {

				int orderId = rs.getInt("id");
				int qty = rs.getInt("qty");
				double total = rs.getDouble("total_price");
				Date d = rs.getDate("order_date");
				String date = String.valueOf(d);
				String rsSql = "SELECT users.name AS seller, books.title AS title FROM orders JOIN users JOIN books ON orders.seller_id=users.id AND orders.book_id=books.id WHERE orders.id="
						+ orderId;
				PreparedStatement rsStmt = con.prepareStatement(rsSql);
				ResultSet rsRs = rsStmt.executeQuery();
				String seller = null;
				String title = null;
				while (rsRs.next()) {
					seller = rsRs.getString("seller");
					title = rsRs.getString("title");
				}
				// "ID", "Seller", "Book Name", "Qty", "Total Price", "Date"
				String[] r = { String.valueOf(orderId), seller, title, String.valueOf(qty), String.valueOf(total),
						date };
				Vector<String> row = new Vector<>();
				for (String rr : r) {
					row.add(rr);
				}

				allRow.add(row);
			}

			for (int i = 0; i < allRow.size(); i++) {
				model.addRow(allRow.get(i));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		searchTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {

				choose = comboBox.getSelectedIndex();

				String searchText = searchTextField.getText().trim();
				boolean isSpace = false;

				if (searchText.equals("".trim())) {
					isSpace = true;
				}

				for (int i = 0; i < allRow.size(); i++) {
					for (int j = 0; j < allCol.length; j++) {
						if (allRow.get(i).get(j).toLowerCase().equals(searchText.toLowerCase())) {
							model.setRowCount(0);
						}

					}

				}

				for (int i = 0; i < allRow.size(); i++) {

					if (isSpace) {
						isSpace = false;
						model.setRowCount(0);
					}

					if (searchText.equals("".trim())) {

						model.addRow(allRow.get(i));
					}

					Vector<String> row = new Vector<>();
					row.add(allRow.get(i).get(choose)); ////////////// comboBox index ///////////////

					for (int j = 0; j < row.size(); j++) {
						if (row.get(j).trim().toLowerCase().equals(searchText.toLowerCase())) {
							model.addRow(allRow.get(i));

						}

					}

				}

			}
		});

		// >>>end construct<<<
	}
}
