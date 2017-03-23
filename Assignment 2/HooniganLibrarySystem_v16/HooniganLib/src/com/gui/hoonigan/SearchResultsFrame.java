package com.gui.hoonigan;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

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
public class SearchResultsFrame{
	private SingletonInformationExpert globalVariables = SingletonInformationExpert.getInstance();
	private JFrame frame = new JFrame("Search Results");
	private JPanel contentPane;
	private JTable displayTable;
	private JLabel lblSearchResults;
	private String currentUserName = globalVariables.getCurrentUser().getUserName();
	private DefaultTableModel tableModel;
	private String selectedChoice = "BOOK";
	private List<String> userPrimaryIDSelections = new ArrayList<String>();
	private List<String> userUniqueIDSelections = new ArrayList<String>();
	private String chosenItemID;
	private String chosenUniqueID;
	private String passedInSearchField;
	private String lastItemIDChosen;
	private String lastItemUniqueIDChosen;
	
	/**
	 * Create the frame.
	 */
	public SearchResultsFrame(String searchField){
		passedInSearchField = new String(searchField);
		
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setBounds(150, 400, 1210, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		frame.setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setBounds(10, 59, 1160, 402);
		contentPane.add(scrollPane);
		
		String columns[] = {"ITEM ID", "UNIQUE ID", "LIBRARY ID", "AVAILABLE WHEN?", "TYPE", "TITLE", "ARTIST", "VOLUME"};
		
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
		
		//Listen to changes
		displayTable.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
			public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
				//For getValueAt(), First param is the row selected, Second param is column you want -> the 0th column has media ID
				if (!evt.getValueIsAdjusting()) {//This line prevents double events
					chosenItemID = new String(displayTable.getValueAt(displayTable.getSelectedRow(), 0).toString());
					chosenUniqueID = new String(displayTable.getValueAt(displayTable.getSelectedRow(), 1).toString());
					userPrimaryIDSelections.add(chosenItemID);
					userUniqueIDSelections.add(chosenUniqueID);
					lastItemIDChosen = new String(userPrimaryIDSelections.get(userPrimaryIDSelections.size()-1));
					lastItemUniqueIDChosen = new String(userUniqueIDSelections.get(userUniqueIDSelections.size()-1));
			    }
	        }
	    });
		
		//End of table
	
		lblSearchResults = new JLabel("Search Results");
		lblSearchResults.setFont(new Font("Tw Cen MT", Font.PLAIN, 16));
		lblSearchResults.setBounds(535, 24, 127, 14);
		contentPane.add(lblSearchResults);
		
		JButton btnCheckItemOut = new JButton("Check Item Out");
		btnCheckItemOut.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				checkItemOut();
			}//end of listener
		});
		btnCheckItemOut.setFont(new Font("Tw Cen MT", Font.PLAIN, 15));
		btnCheckItemOut.setBounds(490, 487, 198, 35);
		contentPane.add(btnCheckItemOut);
	
	}
	
	/**
	 * 
	 */
	public void checkItemOut(){
		//Set various containers
		String returnDate;
		BookItem book;
		CD cd;
		DVD dvd;
		Magazine mag;
		
		//If user has selected something to check out
		if(lastItemIDChosen != null && lastItemUniqueIDChosen != null){
			Item thisItem = selectThisItem(lastItemIDChosen, lastItemUniqueIDChosen);
			
			//Check if the item is not available
			if(thisItem.isCheckedOut()){
				JOptionPane.showMessageDialog(null, "You can't check out an item that's already checked out!", 
						"Sorry!", JOptionPane.WARNING_MESSAGE);
			}
			//If item is available
			else{
				//Current user
				Client user = globalVariables.getCurrentUser();
				
				//Add to user's checkout list
				user.addToCheckedOutList(thisItem);
				
				//Check it out of the library
				thisItem.checkOut();
				thisItem.setCheckedOutBy(user.getUserName());
				
				//Get the return date based on type
				if(thisItem instanceof BookItem){
					book = (BookItem) thisItem;
					returnDate = new String(book.returnDate().toString());
				}
				else if(thisItem instanceof CD){
					cd = (CD) thisItem;
					returnDate = new String(cd.returnDate().toString());
				}else if(thisItem instanceof DVD){
					dvd = (DVD) thisItem;
					returnDate = new String(dvd.returnDate().toString());
				}else{
					mag = (Magazine) thisItem;
					returnDate = new String(mag.returnDate().toString());
				}
				
				closeFrame();
				
				JOptionPane.showMessageDialog(null, user.getUserName() + ", you checked out " + thisItem.getItem_name() + 
						". The item is due on " + returnDate, "Success!", JOptionPane.WARNING_MESSAGE);
				}
			}
	}
	/**
	 * 
	 */
	public boolean displayAllItems(){
		Multimap<String, Item> map = globalVariables.getLibraryItemList();
		if(map.isEmpty()){
			return false;
		}
		
		writeResultsForMapStructures(map);
		
		return true;
	}

	/**
	 * 
	 */
	public boolean displayTypeResults(){
		List<Item> myResults = new ArrayList<Item>(globalVariables.searchForItem(2, passedInSearchField));
		if(myResults.isEmpty()){
			return false;
		}
		
		//Pass in a List<Item>
		writeResults(myResults);
		
		return true;
	}
	/**
	 * 
	 */
	public boolean displayTitleSearchResults()	{
		String searchForThisTitle = new String(passedInSearchField);
		
		List<Item> myResults = new ArrayList<Item>(globalVariables.searchForItem(1, searchForThisTitle));
		if(myResults.isEmpty()){
			return false;
		}
		
		//Pass in a List<Item>
		writeResults(myResults);
		
		return true;
	}//end of method
	
	/**
	 * 
	 */
	public boolean displayIdSearchResults()	{
		String searchForThisID = new String(passedInSearchField);
		
		List<Item> myResults = new ArrayList<Item>(globalVariables.searchForItem(0, searchForThisID));
		if(myResults.isEmpty()){
			JOptionPane.showMessageDialog(null, "No results found!", "Sorry!", JOptionPane.INFORMATION_MESSAGE);
			return false;
		}
		
		//Pass in a List<Item>
		writeResults(myResults);
		
		return true;
	}//end of method
	
	
	/**
	 * Selects the user the user chose.
	 */
	public Item selectThisItem(String primaryID, String secondaryID){
		boolean eligibleToBeCheckedOut = false;
		int uniqueIDField = Integer.valueOf(secondaryID);
		
		//Find the item map, we know that the item exists--otherwise it wouldn't have been in the table
		Collection<Item> itemSelected = globalVariables.getLibraryItemList().get(primaryID);
		for(Item item : itemSelected){
			if(item.getUniqueQuantityID() == uniqueIDField && item.isCheckedOut() == false){
				return item;
			}
		}
		
		return null;
	}
	
	
	/**
	 * 
	 * @param myResults
	 */
	public void writeResults(List<Item> myResults){
		//Traverse through your ArrayList, and write the variables
		for(int i = 0; i<myResults.size(); i++){
			if(myResults.get(i).getItem_type().compareToIgnoreCase("CD") == 0){
				CD cd = (CD) myResults.get(i);
				tableModel.addRow(new Object[] {
						cd.getItem_id(),
						cd.getUniqueQuantityID(),
						cd.getLibraryID(),
						cd.status(),
						cd.getItem_type(),
						cd.getItem_name(),
						cd.getItem_artist()
				});
			}else if(myResults.get(i).getItem_type().compareToIgnoreCase("BOOK") == 0){
				BookItem book = (BookItem) myResults.get(i);
				tableModel.addRow(new Object[] {
						book.getItem_id(),
						book.getUniqueQuantityID(),
						book.getLibraryID(),
						book.status(),
						book.getItem_type(),
						book.getItem_name(),
						book.getItem_author()
				});
			}else if(myResults.get(i).getItem_type().compareToIgnoreCase("DVD") == 0){
				DVD dvd = (DVD) myResults.get(i);
				tableModel.addRow(new Object[] {
						dvd.getItem_id(),
						dvd.getUniqueQuantityID(),
						dvd.getLibraryID(),
						dvd.status(),
						dvd.getItem_type(),
						dvd.getItem_name()
				});
			}else{
				Magazine mag = (Magazine) myResults.get(i);
				tableModel.addRow(new Object[] {
						mag.getItem_id(),
						mag.getUniqueQuantityID(),
						mag.getLibraryID(),
						mag.status(),
						mag.getItem_type(),
						mag.getItem_name(),
						"",
						mag.getVolume()
				});
			}
		}//end of for loop
	}
	
	public void writeResultsForMapStructures(Multimap<String, Item> map){
		
		// Iterating over entire Mutlimap
		for(Item values : map.values()) {
			if(values.getItem_type().compareToIgnoreCase("CD") == 0){
				CD cd = (CD) values;
				tableModel.addRow(new Object[] {
						cd.getItem_id(),
						cd.getUniqueQuantityID(),
						cd.getLibraryID(),
						cd.status(),
						cd.getItem_type(),
						cd.getItem_name(),
						cd.getItem_artist()
				});
			}else if(values.getItem_type().compareToIgnoreCase("BOOK") == 0){
				BookItem book = (BookItem) values;
				tableModel.addRow(new Object[] {
						book.getItem_id(),
						book.getUniqueQuantityID(),
						book.getLibraryID(),
						book.status(),
						book.getItem_type(),
						book.getItem_name(),
						book.getItem_author()
				});
			}else if(values.getItem_type().compareToIgnoreCase("DVD") == 0){
				DVD dvd = (DVD) values;
				tableModel.addRow(new Object[] {
						dvd.getItem_id(),
						dvd.getUniqueQuantityID(),
						dvd.getLibraryID(),
						dvd.status(),
						dvd.getItem_type(),
						dvd.getItem_name()
				});
			}else{
				Magazine mag = (Magazine) values;
				tableModel.addRow(new Object[] {
						mag.getItem_id(),
						mag.getUniqueQuantityID(),
						mag.getLibraryID(),
						mag.status(),
						mag.getItem_type(),
						mag.getItem_name(),
						"",
						mag.getVolume()
				});
			}
		}
	}//end of method
	
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