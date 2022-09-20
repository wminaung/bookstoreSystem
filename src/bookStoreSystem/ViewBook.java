package bookStoreSystem;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;

import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JScrollPane;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;


import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.Font;

public class ViewBook extends JFrame {

	int item = 0;
	ImageIcon img;
	Image imgg ;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the frame.
	 */
	public ViewBook(boolean isAdmin, int user_id) {
		setBackground(new Color(0, 128, 128));
		setTitle("View Book Page");
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 677, 507);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(5, 74, 145));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(5, 74, 145));

		panel.setLayout(new GridLayout(3, 5, 20,20)); ///////////// layout need dynamic ///
		JScrollPane scrollPane = new JScrollPane(panel); //, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
		//JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
		scrollPane.setBounds(57, 46, 1216, 688);
		scrollPane.setOpaque(true);
		scrollPane.getHorizontalScrollBar().setUnitIncrement(20);
		scrollPane.getVerticalScrollBar().setUnitIncrement(20);
		contentPane.add(scrollPane);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 153, 28);
		contentPane.add(menuBar);
		
		JMenu mnNewMenu = new JMenu("User");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Admin Panel");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdminView av = new AdminView(isAdmin, user_id);
				av.setVisible(true);
				ViewBook.this.setVisible(false);
			}
		});
		mnNewMenu.add(mntmNewMenuItem);
		/////////////////////////////////////////////////////
		
		if(isAdmin) {
			mntmNewMenuItem.setEnabled(isAdmin);
		}else {
			mntmNewMenuItem.setEnabled(isAdmin);
		}
		/////////////////////////////////////////////////////
		
		JMenu mnNewMenu_1 = new JMenu("Order");
		menuBar.add(mnNewMenu_1);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Order List");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OrderViewList ovl = new OrderViewList(isAdmin);
				ovl.setVisible(true);
			}
		});
		mnNewMenu_1.add(mntmNewMenuItem_1);
		
		
		JButton btnNewButton = new JButton("Logout");
		btnNewButton.setFocusable(false);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//////////////////////// LOGOUT //////////////////////////
				
				ViewBook.this.setVisible(false);
				Main main = new Main();
				main.setVisible(true);
			}
		});
		menuBar.add(btnNewButton);
		
	
		
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBackground(Color.RED);
		menuBar.add(separator);
		
		JLabel lblNewLabel = new JLabel("Book List");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setBounds(600, 14, 216, 21);
		contentPane.add(lblNewLabel);

		Connection con;
		try {
			con = DbConnection.initializeDatabase();
			String sql = "select * from books";
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			int j = 0;
			while (rs.next()) {
				j += 1;
				JLabel lblBook = new JLabel();
				lblBook.setBackground(Color.WHITE);
				 img = new ImageIcon(rs.getBytes("image"));
				  imgg = img.getImage().getScaledInstance(200, 300,
								 Image.SCALE_SMOOTH);
				lblBook.setIcon(new ImageIcon(imgg));
				lblBook.setBounds(200, 300, 86, 30);
				lblBook.setHorizontalAlignment(JLabel.CENTER);
				// contentPane.add(lblBook);
				int id = rs.getInt("id");
				String author = rs.getString("author");
				String title = rs.getString("title");
				String releaseDate = rs.getString("releaseDate");
				double price = rs.getDouble("price");
				int qty = rs.getInt("qty");

				panel.add(lblBook);
			//	lblBook.setToolTipText(title);
				lblBook.setOpaque(true);
				lblBook.setBackground(new Color(5, 74, 145));
				// lblBook.setBounds(98, 113, 200, 300);
				// lblBook.setAlignmentX(JLabel.CENTER_ALIGNMENT);
				lblBook.addMouseListener(new MouseAdapter() {
					@Override
					public void mousePressed(MouseEvent e) {
						// System.out.println( " " +author +" "+ title +" "+ releaseDate +" "+ price + "
						// "+ qty);
						ArrayList<String> bookData = new ArrayList<>();
						bookData.add(String.valueOf(id));
						bookData.add(author);
						bookData.add(title);
						bookData.add(releaseDate);
						bookData.add(String.valueOf(price));
						bookData.add(String.valueOf(qty));
						BookDetail bookDetail = new BookDetail(bookData , isAdmin, user_id);
						bookDetail.setVisible(true);
						lblBook.setBackground(Color.WHITE);
					}

					@Override
					public void mouseEntered(MouseEvent e) {
						lblBook.setBackground(new Color(15, 54, 115));
						
						
					}

					@Override
					public void mouseExited(MouseEvent e) {
						lblBook.setBackground(new Color(5, 74, 145));
						
					}

					@Override
					public void mouseReleased(MouseEvent e) {
						lblBook.setBackground(new Color(15, 54, 115));
						
					}

//					@Override
//					public void mousePressed(MouseEvent e) {
//						
//					}

				});
			}
			item = j;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// >>>end construct<<<
	}
}
