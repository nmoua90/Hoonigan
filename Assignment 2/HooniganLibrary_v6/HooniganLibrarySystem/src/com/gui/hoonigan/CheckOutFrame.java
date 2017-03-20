package com.gui.hoonigan;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**CheckOutFrame class
 * This class creates a frame, which allows the user to search the Library for items, and check the item(s) out
 * @author Hoonigan
 *
 */
public class CheckOutFrame{
	private JFrame frame = new JFrame("Library Search & Checkout");
	private JPanel contentPane;
	private JTable displayTable;
	private JLabel lblSearchResults;
	private JLabel lblSearchWhatType;
	private JLabel lblSearchById;
	private JTextField idSearchField;
	private JLabel lblSearchByAuthor;
	private JLabel lblSearchByTitle;
	private JTextField authorTextField;
	private JTextField titleTextField;
	private JButton btnSearch;
	private JButton btnCheckOut;
	private JLabel lblCurrentUser;
	private JTextField currentUserTextField;
	
	/**
	 * Create the frame.
	 */
	public CheckOutFrame(String currentUser) {//Change parameter -> User currentUser in Final Implementation
		//this.currentUser = currentUser;
		
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setBounds(150, 400, 1010, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		frame.setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setBounds(10, 277, 974, 200);
		contentPane.add(scrollPane);
		
		// output table
				displayTable = new JTable();

				displayTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

				// Set the model of the search results table that defines the layout of the data
				displayTable.setModel(new DefaultTableModel(
					new Object[][] {
						{null, null, null, null, null},
						{null, null, null, null, null},
						{null, null, null, null, null},
						{null, null, null, null, null},
						{null, null, null, null, null},
					},
					new String[] {
						"ID", "TYPE", "TITLE", "VOLUME", "ARTIST", "AVAILABILITY"
					}
				));
				scrollPane.setViewportView(displayTable);		
				
				lblSearchResults = new JLabel("Search Results");
				lblSearchResults.setFont(new Font("Tw Cen MT", Font.PLAIN, 16));
				lblSearchResults.setBounds(448, 239, 127, 14);
				contentPane.add(lblSearchResults);
				
				JComboBox mediaOptionsBox = new JComboBox();
				mediaOptionsBox.setModel(new DefaultComboBoxModel(new String[] {"ALL", "BOOK", "MAGAZINE", "DVD", "CD"}));
				mediaOptionsBox.setFont(new Font("Tw Cen MT", Font.PLAIN, 13));
				mediaOptionsBox.setBounds(274, 56, 92, 20);
				
				contentPane.add(mediaOptionsBox);
				
				lblSearchWhatType = new JLabel("Search By What Type Of Media");
				lblSearchWhatType.setFont(new Font("Tw Cen MT", Font.PLAIN, 15));
				lblSearchWhatType.setBounds(25, 59, 206, 14);
				contentPane.add(lblSearchWhatType);
				
				lblSearchById = new JLabel("Search By ID");
				lblSearchById.setFont(new Font("Tw Cen MT", Font.PLAIN, 15));
				lblSearchById.setBounds(25, 100, 110, 14);
				contentPane.add(lblSearchById);
				
				idSearchField = new JTextField();
				idSearchField.setBounds(274, 98, 268, 20);
				contentPane.add(idSearchField);
				idSearchField.setColumns(10);
				
				lblSearchByAuthor = new JLabel("Search By Author");
				lblSearchByAuthor.setFont(new Font("Tw Cen MT", Font.PLAIN, 15));
				lblSearchByAuthor.setBounds(25, 146, 110, 14);
				contentPane.add(lblSearchByAuthor);
				
				lblSearchByTitle = new JLabel("Search By Title");
				lblSearchByTitle.setFont(new Font("Tw Cen MT", Font.PLAIN, 15));
				lblSearchByTitle.setBounds(25, 186, 110, 14);
				contentPane.add(lblSearchByTitle);
				
				authorTextField = new JTextField();
				authorTextField.setBounds(274, 144, 268, 20);
				contentPane.add(authorTextField);
				authorTextField.setColumns(10);
				
				titleTextField = new JTextField();
				titleTextField.setBounds(274, 180, 268, 20);
				contentPane.add(titleTextField);
				titleTextField.setColumns(10);
				
				btnSearch = new JButton("SEARCH");
				btnSearch.setFont(new Font("Tw Cen MT", Font.PLAIN, 15));
				btnSearch.setBounds(666, 29, 260, 37);
				contentPane.add(btnSearch);
				
				btnCheckOut = new JButton("CHECK THIS ITEM OUT");
				btnCheckOut.setFont(new Font("Tw Cen MT", Font.PLAIN, 15));
				btnCheckOut.setBounds(666, 89, 260, 37);
				contentPane.add(btnCheckOut);
				
				lblCurrentUser = new JLabel("Current User");
				lblCurrentUser.setFont(new Font("Tw Cen MT", Font.PLAIN, 15));
				lblCurrentUser.setBounds(25, 21, 110, 14);
				contentPane.add(lblCurrentUser);
				
				currentUserTextField = new JTextField();
				currentUserTextField.setEditable(false);
				currentUserTextField.setBounds(274, 19, 268, 20);
				contentPane.add(currentUserTextField);
				currentUserTextField.setColumns(10);
				currentUserTextField.setText(currentUser);
				frame.setLocationRelativeTo(null);
				frame.setVisible(true);	
	}
	
	/**
	 * 
	 */
	public void closeFrame(){
		frame.setVisible(false);
		frame.dispose();
	}
	
	/**
	 * 
	 */
	public void setVisible(){
		frame.setVisible(true);
	}
	
}