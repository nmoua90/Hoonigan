package com.gui.hoonigan;


import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import com.implementation.hoonigan.*;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

/**CheckOutFrame class
 * This class creates a frame, which allows the user to search the Library for items, and check the item(s) out
 * @author Hoonigan
 *
 */
public class CheckOutFrame{
	private SingletonInformationExpert globalVariables = SingletonInformationExpert.getInstance();
	private JFrame frame = new JFrame("Library Search & Checkout");
	private JPanel contentPane;
	private JTable displayTable;
	private JLabel lblSearchResults;
	private JLabel lblSearchWhatType;
	private JLabel lblSearchById;
	private JTextField idSearchField;
	private JLabel lblSearchByTitle;
	private JTextField titleSearchField;
	private JButton btnSearchID;
	private JButton btnCheckOut;
	private JLabel lblCurrentUser;
	private JTextField currentUserTextField;
	private Client currentUser = globalVariables.getCurrentUser();
	private String currentUserName = globalVariables.getCurrentUser().getUserName();
	private DefaultTableModel tableModel;
	private String selectionRowChoice;
	private String comboBoxChoice = "ALL";
	
	/**
	 * Create the frame.
	 */
	public CheckOutFrame(){
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setBounds(150, 400, 1010, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		frame.setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setBounds(10, 277, 974, 200);
		contentPane.add(scrollPane);
		
		String columns[] = {"MEDIA ID", "LIBRARY ID", "ON-SHELF?", "TYPE", "TITLE", "ARTIST", "VOLUME"};
		
		//Set your default table, initially, you have only columns, and 0 rows
		tableModel = new DefaultTableModel(columns, 0){
			//Make all cells uneditable - this is optional
			@Override
		    public boolean isCellEditable(int row, int column) {
		       return false;
		    }
		};
				
		//Create a new JTable, and put your default Table to initialize it
		displayTable = new JTable(tableModel);
		displayTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		frame.setLocationRelativeTo(displayTable);
				
		//Put your table in the Scroll Pane
		scrollPane.setViewportView(displayTable);	
		
		displayTable.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
			public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
				//For getValueAt(), First param is the row selected, Second param is column you want -> the 0th column has media ID
				if (!evt.getValueIsAdjusting()) {//This line prevents double events
					selectionRowChoice += displayTable.getValueAt(displayTable.getSelectedRow(), 0);
			    }
	        }
	    });
		
		//End of table
	
		lblSearchResults = new JLabel("Search Results");
		lblSearchResults.setFont(new Font("Tw Cen MT", Font.PLAIN, 16));
		lblSearchResults.setBounds(448, 239, 127, 14);
		contentPane.add(lblSearchResults);
				
		JComboBox mediaOptionsBox = new JComboBox();
		mediaOptionsBox.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				JComboBox cb = (JComboBox) e.getSource();
				String selectedChoice = (String) cb.getSelectedItem();
				comboBoxChoice = selectedChoice;
				}
			});
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
				
		lblSearchByTitle = new JLabel("Search By Title");
		lblSearchByTitle.setFont(new Font("Tw Cen MT", Font.PLAIN, 15));
		lblSearchByTitle.setBounds(25, 140, 110, 14);
		contentPane.add(lblSearchByTitle);
				
		titleSearchField = new JTextField();
		titleSearchField.setBounds(274, 138, 268, 20);
		contentPane.add(titleSearchField);
		titleSearchField.setColumns(10);
				
		btnSearchID = new JButton("SEARCH BY ID");
		btnSearchID.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				runSearch(0);
			}
		});
		btnSearchID.setFont(new Font("Tw Cen MT", Font.PLAIN, 15));
		btnSearchID.setBounds(666, 29, 260, 37);
		contentPane.add(btnSearchID);
				
		btnCheckOut = new JButton("CHECK THIS ITEM OUT");
		btnCheckOut.setFont(new Font("Tw Cen MT", Font.PLAIN, 15));
		btnCheckOut.setBounds(666, 148, 260, 37);
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
		currentUserTextField.setText(currentUserName);
		
		JButton btnSearchTitle = new JButton("SEARCH BY TITLE");
		btnSearchTitle.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				runSearch(1);
			}
		});
		btnSearchTitle.setFont(new Font("Tw Cen MT", Font.PLAIN, 15));
		btnSearchTitle.setBounds(666, 89, 260, 37);
		contentPane.add(btnSearchTitle);
		
		JButton btnDisplayAllItems = new JButton("DISPLAY ALL ITEMS");
		btnDisplayAllItems.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				runSearch(2);
			}
		});
		btnDisplayAllItems.setFont(new Font("Tw Cen MT", Font.PLAIN, 15));
		btnDisplayAllItems.setBounds(666, 204, 260, 52);
		contentPane.add(btnDisplayAllItems);
		
	}
	
	/**
	 * Search by ID if buttonPress = 0, Search by Title if buttonPress = 1, Return all = 2
	 */
	public void runSearch(int buttonPress)	{
		List<Item> searchResults;
		
		//Either the value in the ID Search textbox, or the Title in the in the Title textbox
		String searchOption;
		
		// Clear the search results table 
		DefaultTableModel model = (DefaultTableModel)displayTable.getModel();
		for(int i = model.getRowCount() - 1; i >= 0; i--)
			model.removeRow(i);
		
		if(buttonPress == 0){
			//Search by ID
			searchOption = idSearchField.getText();

			//Get initial List, that contains Items with matching IDs
			searchResults = globalVariables.searchForItem(buttonPress, searchOption);
			
			displayFilteredResults(searchResults);
			
		}else if(buttonPress == 1){
			//Search by Title
			searchOption = titleSearchField.getText();
			
			//Get initial List, that contains Items with matching IDs
			searchResults = globalVariables.searchForItem(buttonPress, searchOption);
			
			displayFilteredResults(searchResults);
		}		
	}//end of method
	
	/**
	 * 
	 * @param searchResults
	 */
	public void displayFilteredResults(List<Item> searchResults){
		//Filter the List if necessary
		if(comboBoxChoice.compareTo("BOOK") == 0){
			searchResults = globalVariables.filterByType("BOOK", searchResults);
			//If BOOK filter, traverse through your ArrayList, and write only shared variables
			for(int i = 0; i<searchResults.size(); i++){
				tableModel.addRow(new Object[] {
						searchResults.get(i).getItem_id(),
						searchResults.get(i).getLibraryID(),
						searchResults.get(i).status(),
						searchResults.get(i).getItem_type(),
						searchResults.get(i).getItem_name(),
						((BookItem) searchResults.get(i)).getItem_author()
					});
				}
			}
		else if(comboBoxChoice.compareTo("MAGAZINE") == 0){
			searchResults = globalVariables.filterByType("MAGAZINE", searchResults);
			//If MAGAZINE filter, traverse through your ArrayList, and write only shared variables
			for(int i = 0; i<searchResults.size(); i++){
				tableModel.addRow(new Object[] {
						searchResults.get(i).getItem_id(),
						searchResults.get(i).getLibraryID(),
						searchResults.get(i).status(),
						searchResults.get(i).getItem_type(),
						searchResults.get(i).getItem_name(),
						"",
						((Magazine) searchResults.get(i)).getVolume(),
				});
			}
		}
		else if(comboBoxChoice.compareTo("DVD") == 0){
			searchResults = globalVariables.filterByType("DVD", searchResults);
			//If DVD filter, traverse through your ArrayList, and write only shared variables
			for(int i = 0; i<searchResults.size(); i++){
				tableModel.addRow(new Object[] {
						searchResults.get(i).getItem_id(),
						searchResults.get(i).getLibraryID(),
						searchResults.get(i).status(),
						searchResults.get(i).getItem_type(),
						searchResults.get(i).getItem_name()
				});
			}
		}
		else if(comboBoxChoice.compareTo("CD") == 0){
			searchResults = globalVariables.filterByType("CD", searchResults);
			//If CD filter, traverse through your ArrayList, and write only shared variables
			for(int i = 0; i<searchResults.size(); i++){
				tableModel.addRow(new Object[] {
						searchResults.get(i).getItem_id(),
						searchResults.get(i).getLibraryID(),
						searchResults.get(i).status(),
						searchResults.get(i).getItem_type(),
						searchResults.get(i).getItem_name(),
						((CD) searchResults.get(i)).getItem_artist()
				});
			}
		}else{
			//If no filter, traverse through your ArrayList, and write only shared variables
			for(int i = 0; i<searchResults.size(); i++){
				tableModel.addRow(new Object[] {
						searchResults.get(i).getItem_id(),
						searchResults.get(i).getLibraryID(),
						searchResults.get(i).status(),
						searchResults.get(i).getItem_type(),
						searchResults.get(i).getItem_name()
				});
			}
		}
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