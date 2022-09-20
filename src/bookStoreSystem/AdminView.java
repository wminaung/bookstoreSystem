package bookStoreSystem;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.sql.SQLException;



import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.ImageIcon;


public class AdminView extends JFrame {
	private int id;
	private JPanel contentPane;
	private JTable table;
	private JPopupMenu popupMenu;
	private JMenuItem menuItemUpdate;
	private JMenuItem menuItemDelete;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public AdminView(boolean isAdmin, int user_id) {
		setTitle("Admin View Page");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 728, 486);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(5, 74, 145));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPopupMenu popupMenu_1 = new JPopupMenu();
		addPopup(contentPane, popupMenu_1);

		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Refresh");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { ////////// refresh pop up ///////////
				refresh(AdminView.this, isAdmin, user_id);
			}
		});
		popupMenu_1.add(mntmNewMenuItem_1);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(50, 49, 599, 340);
		scrollPane.setOpaque(true);
		scrollPane.getViewport().setBackground(new Color(5, 74, 145));
		scrollPane.setBackground(new Color(5, 74, 145));
		contentPane.add(scrollPane);

		popupMenu = new JPopupMenu();

		// Object<String> String = {"ID", "Author", "Title", "Release Date", "Price",
		// "Qty" ,"Update" , "Delete"};
		table = new JTable(); // create table that no model
		table.setOpaque(true);
		table.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "ID", "Author", "Title", "Release Date", "Price", "Qty" }));

		menuItemUpdate = new JMenuItem("Update");
		popupMenu.add(menuItemUpdate);

		menuItemDelete = new JMenuItem("Delete");
		popupMenu.add(menuItemDelete);
		scrollPane.setViewportView(table);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 125, 22);
		contentPane.add(menuBar);

		JMenu mnNewMenu = new JMenu("Book");
		menuBar.add(mnNewMenu);

		JMenuItem menuItemViewAllBooks = new JMenuItem("View All Books");
		mnNewMenu.add(menuItemViewAllBooks);

		JMenuItem menuItemInsertBook = new JMenuItem("Insert Book");
		menuItemInsertBook.addActionListener(new ActionListener() { ///////// Book Insert //////////
			public void actionPerformed(ActionEvent e) {
				BookInsert bookInsert = new BookInsert();
				bookInsert.setVisible(true);
			}
		});
		mnNewMenu.add(menuItemInsertBook);

		JMenuItem mntmNewMenuItem = new JMenuItem("");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { ////////// refresh menu ///////////
				refresh(AdminView.this, isAdmin, user_id);
			}
		});
		mntmNewMenuItem.setToolTipText("refresh");
		
		JMenu mnNewMenu_1 = new JMenu("users");
		menuBar.add(mnNewMenu_1);
		
		JMenuItem showAllUsersMenuItem = new JMenuItem("Show All Users");
		showAllUsersMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ShowAllUsers sau = new ShowAllUsers();
				sau.setVisible(true);
			}
		});
		mnNewMenu_1.add(showAllUsersMenuItem);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Logout");
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				////////// LOGOUT ////////////
				AdminView.this.setVisible(false);
				Main m = new Main();
				m.setVisible(true);
				
			}
		});
		mnNewMenu_1.add(mntmNewMenuItem_2);
		mntmNewMenuItem.setIcon(new ImageIcon(
				"C:\\Users\\Qung\\eclipse-workspace\\BookStoreSystem\\public\\image\\refresh-button.png"));
		menuBar.add(mntmNewMenuItem);

		/////// table event
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				if (table.getSelectedRow() != -1) {
					addPopup(table, popupMenu);

					int row = table.getSelectedRow();
					id = Integer.parseInt((String) table.getValueAt(row, 0)); // get table id column
					
				}
			}
		});

		menuItemUpdate.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				BookUpdate bookUpdate = new BookUpdate(id);
				bookUpdate.setVisible(true);

			}
		});

		menuItemDelete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int result = JOptionPane.showConfirmDialog(null, "Areyou sure Want to delete this items");
				if(result == JOptionPane.OK_OPTION) {
					Connection con;
					try {
						con = DbConnection.initializeDatabase();
						String sql = "DELETE FROM books WHERE id="+id;
						PreparedStatement stmt = con.prepareStatement(sql);
						stmt.executeUpdate();
						JOptionPane.showMessageDialog(null, "you are deleted item that id is "+ id);
						refresh(AdminView.this, isAdmin, user_id);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
			}
		});

		Connection con;
		try {
			con = DbConnection.initializeDatabase();
			PreparedStatement stmt = con.prepareStatement("select * from books");
			ResultSet rs = stmt.executeQuery();

			String[] row = new String[6];
			while (rs.next()) {
				row[0] = String.valueOf(rs.getInt("id"));
				row[1] = rs.getString("author");
				row[2] = rs.getString("title");
				row[3] = rs.getString("releaseDate");
				row[4] = String.valueOf(rs.getDouble("price"));
				row[5] = String.valueOf(rs.getInt("qty"));
				((DefaultTableModel) table.getModel()).addRow(row);

			}
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		/// go viewBook page
		menuItemViewAllBooks.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewBook viewBook = new ViewBook(isAdmin, user_id);
				viewBook.setVisible(true);
				AdminView.this.setVisible(false);
			}
		});

		// >>>end construct<<<
	}

	private static void refresh(AdminView av, boolean isAdmin, int user_id) {
		av.setVisible(false);
		av = new AdminView(isAdmin, user_id);
		av.setVisible(true);
	}

	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}

			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}

			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}

		});
	}
}
