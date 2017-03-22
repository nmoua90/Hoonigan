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
	private JFrame frame = new JFrame("Library Search & Checkout");
	private JPanel contentPane;
	private JTable displayTable;
	private JLabel lblSearchResults;
	private String currentUserName = globalVariables.getCurrentUser().getUserName();
	private DefaultTableModel tableModel;
	private String selectedChoice = "BOOK";
	private List<String> userSelections = new ArrayList<String>();
	private String chosenItem;
	private String passedInSearchField;
	private JButton btnBackToPrevious;
	
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
		
		//Listen to changes
		displayTable.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
			public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
				//For getValueAt(), First param is the row selected, Second param is column you want -> the 0th column has media ID
				if (!evt.getValueIsAdjusting()) {//This line prevents double events
					chosenItem = new String(displayTable.getValueAt(displayTable.getSelectedRow(), 0).toString());
					userSelections.add(chosenItem);
					String lastItemChosen = new String(userSelections.get(userSelections.size()-1));
					selectThisItem(lastItemChosen);
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
				
			}
		});
		btnCheckItemOut.setFont(new Font("Tw Cen MT", Font.PLAIN, 15));
		btnCheckItemOut.setBounds(234, 499, 198, 35);
		contentPane.add(btnCheckItemOut);
		
		btnBackToPrevious = new JButton("Back To Previous Page");
		btnBackToPrevious.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				CheckOutFrame backtrack = new CheckOutFrame();
				backtrack.setVisible();
			}
		});
		btnBackToPrevious.setFont(new Font("Tw Cen MT", Font.PLAIN, 15));
		btnBackToPrevious.setBounds(695, 499, 222, 35);
		contentPane.add(btnBackToPrevious);
	
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
	public void selectThisItem(String userSelectedThis){
		boolean eligibleToBeCheckedOut = false;
		
		System.out.println(userSelectedThis);
		
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
	 * @param myResults
	 */
	public void writeResults(List<Item> myResults){
		//Traverse through your ArrayList, and write the variables
		for(int i = 0; i<myResults.size(); i++){
			if(myResults.get(i).getItem_type().compareToIgnoreCase("CD") == 0){
				CD cd = (CD) myResults.get(i);
				tableModel.addRow(new Object[] {
						cd.getItem_id(),
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
						dvd.getLibraryID(),
						dvd.status(),
						dvd.getItem_type(),
						dvd.getItem_name()
				});
			}else{
				Magazine mag = (Magazine) myResults.get(i);
				tableModel.addRow(new Object[] {
						mag.getItem_id(),
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
						dvd.getLibraryID(),
						dvd.status(),
						dvd.getItem_type(),
						dvd.getItem_name()
				});
			}else{
				Magazine mag = (Magazine) values;
				tableModel.addRow(new Object[] {
						mag.getItem_id(),
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