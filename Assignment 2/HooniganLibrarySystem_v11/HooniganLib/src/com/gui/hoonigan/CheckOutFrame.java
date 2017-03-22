package com.gui.hoonigan;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import com.google.common.collect.Multimap;
import com.implementation.hoonigan.*;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collection;
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
	private String currentUserName = globalVariables.getCurrentUser().getUserName();
	private DefaultTableModel tableModel;
	private String selectedChoice = "BOOK";
	private List<String> userSelections = new ArrayList<String>();
	private JButton btnSearchByMedia;
	
	/**
	 * Create the frame.
	 */
	public CheckOutFrame(){
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setBounds(150, 400, 1210, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		frame.setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setBounds(10, 277, 974, 200);
		contentPane.add(scrollPane);
		
		String columns[] = {"ITEM ID", "LIBRARY ID", "ON-SHELF?", "TYPE", "TITLE", "ARTIST", "VOLUME"};
		
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
					userSelections.add(new String(displayTable.getValueAt(displayTable.getSelectedRow(), 0).toString()));
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
		        selectedChoice = new String((String) cb.getSelectedItem());
				}
			});
		mediaOptionsBox.setModel(new DefaultComboBoxModel(new String[] {"BOOK", "MAGAZINE", "DVD", "CD"}));
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
				
		btnSearchID = new JButton("SEARCH BY ITEM ID");
		btnSearchID.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				runIdSearch();
			}
		});
		btnSearchID.setFont(new Font("Tw Cen MT", Font.PLAIN, 15));
		btnSearchID.setBounds(600, 89, 260, 37);
		contentPane.add(btnSearchID);
				
		btnCheckOut = new JButton("CHECK THIS ITEM OUT");
		btnCheckOut.setFont(new Font("Tw Cen MT", Font.PLAIN, 15));
		btnCheckOut.setBounds(924, 89, 247, 37);
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
				runTitleSearch();
			}
		});
		btnSearchTitle.setFont(new Font("Tw Cen MT", Font.PLAIN, 15));
		btnSearchTitle.setBounds(600, 153, 260, 37);
		contentPane.add(btnSearchTitle);
		
		JButton btnDisplayAllItems = new JButton("DISPLAY ALL ITEMS");
		btnDisplayAllItems.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				displayAllItems();
			}
		});
		btnDisplayAllItems.setFont(new Font("Tw Cen MT", Font.PLAIN, 15));
		btnDisplayAllItems.setBounds(600, 21, 260, 37);
		contentPane.add(btnDisplayAllItems);
		
		btnSearchByMedia = new JButton("SEARCH BY MEDIA TYPE");
		btnSearchByMedia.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				runTypeSearch();
			}
		});
		btnSearchByMedia.setFont(new Font("Tw Cen MT", Font.PLAIN, 15));
		btnSearchByMedia.setBounds(921, 21, 250, 37);
		contentPane.add(btnSearchByMedia);
		
	}
	
	/**
	 * 
	 */
	public void displayAllItems(){
		// Clear the search results table 
		DefaultTableModel model = (DefaultTableModel)displayTable.getModel();
		for(int i = model.getRowCount() - 1; i >= 0; i--)
			model.removeRow(i);
				
		Multimap<String, Item> map = globalVariables.getLibraryItemList();
		if(map.isEmpty()){
			JOptionPane.showMessageDialog(null, "No results found!", "Sorry!", JOptionPane.INFORMATION_MESSAGE);
		}
		
		// Iterating over entire Mutlimap
		for(Item values : map.values()) {
			tableModel.addRow(new Object[] {
				values.getItem_id(),
				values.getLibraryID(),
				"",
				values.getItem_type(),
				values.getItem_name()
			});
		}
		
	}

	/**
	 * 
	 */
	public void runTypeSearch(){
		// Clear the search results table 
		DefaultTableModel model = (DefaultTableModel)displayTable.getModel();
		for(int i = model.getRowCount() - 1; i >= 0; i--)
			model.removeRow(i);
					
		List<Item> myResults = globalVariables.searchForItem(2, selectedChoice);
		if(myResults.isEmpty()){
			JOptionPane.showMessageDialog(null, "No results found!", "Sorry!", JOptionPane.INFORMATION_MESSAGE);
		}
		
		//Traverse through your ArrayList, and write the variables
		for(int i = 0; i<myResults.size(); i++){
				tableModel.addRow(new Object[] {
					myResults.get(i).getItem_id(),
					myResults.get(i).getLibraryID(),
					myResults.get(i).status(),
					myResults.get(i).getItem_type(),
					myResults.get(i).getItem_name()
			});
		}
	}
	/**
	 * 
	 */
	public void runTitleSearch()	{
		// Clear the search results table 
		DefaultTableModel model = (DefaultTableModel)displayTable.getModel();
		for(int i = model.getRowCount() - 1; i >= 0; i--)
			model.removeRow(i);
		
		String searchForThisTitle = titleSearchField.getText();
		
		List<Item> myResults = globalVariables.searchForItem(1, searchForThisTitle);
		if(myResults.isEmpty()){
			JOptionPane.showMessageDialog(null, "No results found!", "Sorry!", JOptionPane.INFORMATION_MESSAGE);
		}
		
		//Traverse through your ArrayList, and write the variables
		for(int i = 0; i<myResults.size(); i++){
			tableModel.addRow(new Object[] {
					myResults.get(i).getItem_id(),
					myResults.get(i).getLibraryID(),
					myResults.get(i).status(),
					myResults.get(i).getItem_type(),
					myResults.get(i).getItem_name()
			});
		}
	}//end of method
	
	/**
	 * 
	 */
	public void runIdSearch()	{
		// Clear the search results table 
		DefaultTableModel model = (DefaultTableModel)displayTable.getModel();
		for(int i = model.getRowCount() - 1; i >= 0; i--)
			model.removeRow(i);
		
		String searchForThisID = idSearchField.getText();
		
		List<Item> myResults = globalVariables.searchForItem(0, searchForThisID);
		if(myResults.isEmpty()){
			JOptionPane.showMessageDialog(null, "No results found!", "Sorry!", JOptionPane.INFORMATION_MESSAGE);
		}
		/**
		 * for(int i = 0; i<myResults.size(); i++){
			System.out.println(myResults.get(i));
		}
		 * 
		 */
		
		//Traverse through your ArrayList, and write the variables
		for(int i = 0; i<myResults.size(); i++){
			tableModel.addRow(new Object[] {
					myResults.get(i).getItem_id(),
					myResults.get(i).getLibraryID(),
					myResults.get(i).status(),
					myResults.get(i).getItem_type(),
					myResults.get(i).getItem_name()
			});
		}
	}//end of method
	
	
	/**
	 * Selects the user the user chose.
	 */
	public void selectThisItem(){
		boolean eligibleToBeCheckedOut = false;
		
		//The last String in the list is the user's option
		String userSelectedThis = userSelections.get(userSelections.size()-1);
		
		//Find the item map, we know that the item exists--otherwise it wouldn't have been in the table
		Collection<Item> itemSelected = globalVariables.getLibraryItemList().get(userSelectedThis);
		for(Item item : itemSelected){
			//System.out.println(item.getClass());
		}
		
		
		/**
		 * // Iterating over entire Mutlimap
		  for(Item values : globalVariables.getLibraryItemList().values()) {
			  if(values.getItem_id().compareToIgnoreCase(userSelectedThis) == 0)
				  if(values.getItem_type().compareToIgnoreCase("BookItem") == 0){
					  BookItem book = (BookItem) values;
					  book.getFee();
				  }
				  System.out.println(values.getItem_id());
		  }
		 * 
		 */
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