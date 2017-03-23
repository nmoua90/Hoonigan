package com.gui.hoonigan;


import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import com.implementation.hoonigan.BookItem;
import com.implementation.hoonigan.CD;
import com.implementation.hoonigan.Client;
import com.implementation.hoonigan.DVD;
import com.implementation.hoonigan.Item;
import com.implementation.hoonigan.Magazine;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.swing.JTextField;

/**CheckInFrame class
 * This class allows users to check in library items.
 * @author Hoonigan
 *
 */
public class CheckInFrame{
	private SingletonInformationExpert globalVariables = SingletonInformationExpert.getInstance();
	private JFrame frame = new JFrame("User Check-In");
	private JPanel contentPane;
	private JTable displayTable;
	private JLabel lblYourCheckedoutItems;
	private JButton btnCheckIn;
	private JTextField currentUserField;
	private JLabel lblCurrentUser;
	private Client currentUser = globalVariables.getCurrentUser();
	private String currentUserName = globalVariables.getCurrentUser().getUserName();
	private DefaultTableModel tableModel;
	private List<String> userPrimaryIDSelections = new ArrayList<String>();
	private List<String> userUniqueIDSelections = new ArrayList<String>();
	private String chosenItemID;
	private String chosenUniqueID;
	private String passedInSearchField;
	private String lastItemIDChosen;
	private String lastItemUniqueIDChosen;
	private List<Item> usersCheckoutList;
	/**
	 * Create the frame.
	 */
	public CheckInFrame() {
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setBounds(100, 100, 550, 350);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		frame.setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setBounds(31, 106, 475, 137);
		contentPane.add(scrollPane);

		//How to Make a table
		//Name your columns
		String columns[] = {"ITEM ID", "UNIQUE ID", "TITLE","DUE-DATE", "LATE?" ,"FEE"};
						
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
				  		
		lblYourCheckedoutItems = new JLabel("Your Checked-out Items");
		lblYourCheckedoutItems.setFont(new Font("Tw Cen MT", Font.PLAIN, 20));
		lblYourCheckedoutItems.setBounds(31, 72, 204, 23);
		contentPane.add(lblYourCheckedoutItems);
		
		btnCheckIn = new JButton("Check In This Item");
		btnCheckIn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				checkItemIn();
			}
		});
		btnCheckIn.setFont(new Font("Tw Cen MT", Font.PLAIN, 15));
		btnCheckIn.setBounds(183, 261, 159, 23);
		contentPane.add(btnCheckIn);
		
		currentUserField = new JTextField();
		currentUserField.setEditable(false);
		currentUserField.setBounds(141, 32, 365, 20);
		contentPane.add(currentUserField);
		currentUserField.setColumns(10);
		
		lblCurrentUser = new JLabel("Current User");
		lblCurrentUser.setFont(new Font("Tw Cen MT", Font.PLAIN, 15));
		lblCurrentUser.setBounds(31, 34, 100, 14);
		currentUserField.setText(currentUserName);
		contentPane.add(lblCurrentUser);
		
		//Write your results
		usersCheckoutList = globalVariables.getCurrentUser().getItemsCheckedOut(); 
		if(usersCheckoutList.isEmpty()){
			JOptionPane.showMessageDialog(null, "You don't have any items that you've checked out!", 
					"Hey there!", JOptionPane.WARNING_MESSAGE);
		}else{
			writeResults(usersCheckoutList);
		}
		
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
						cd.getItem_name(),
						cd.status(),
						cd.isLate(),
						cd.getFee()
				});
			}else if(myResults.get(i).getItem_type().compareToIgnoreCase("BOOK") == 0){
				BookItem book = (BookItem) myResults.get(i);
				tableModel.addRow(new Object[] {
						book.getItem_id(),
						book.getUniqueQuantityID(),
						book.getItem_name(),
						book.status(),
						book.isLate(),
						book.getFee()
				});
			}else if(myResults.get(i).getItem_type().compareToIgnoreCase("DVD") == 0){
				DVD dvd = (DVD) myResults.get(i);
				tableModel.addRow(new Object[] {
						dvd.getItem_id(),
						dvd.getUniqueQuantityID(),
						dvd.getItem_name(),
						dvd.status(),
						dvd.isLate(),
						dvd.getFee()
				});
			}else{
				Magazine mag = (Magazine) myResults.get(i);
				tableModel.addRow(new Object[] {
						mag.getItem_id(),
						mag.getUniqueQuantityID(),
						mag.getItem_name(),
						mag.status(),
						mag.isLate(),
						mag.getFee()
				});
			}
		}//end of for loop
	}
	
	/**
	 * Selects the user the user chose.
	 */
	public Item selectThisItem(String primaryID, String secondaryID){
		boolean eligibleToBeCheckedOut = false;
		int uniqueIDField = Integer.valueOf(secondaryID);
		
		//Find the item map, we know that the item exists--otherwise it wouldn't have been in the table
		Collection<Item> itemSelected = globalVariables.getLibraryItemList().get(primaryID);
		for(Item item : itemSelected){
			if(item.getUniqueQuantityID() == uniqueIDField && item.isCheckedOut() == true){
				return item;
			}
		}
		
		return null;
	}
	
	/**
	 * 
	 */
	public void checkItemIn(){
		//Set various containers
		String returnDate;
		BookItem book;
		CD cd;
		DVD dvd;
		Magazine mag;
		
		//If user has selected something to check out
		if(lastItemIDChosen != null && lastItemUniqueIDChosen != null){
			Item thisItem = selectThisItem(lastItemIDChosen, lastItemUniqueIDChosen);
			
			//Current user
			Client user = globalVariables.getCurrentUser();

			//Check it into the library
			thisItem.checkIn();
					
			closeFrame();
				
			JOptionPane.showMessageDialog(null, user.getUserName() + ", you checked " + thisItem.getItem_name() + 
					" in!", "Success!", JOptionPane.INFORMATION_MESSAGE);
			
			
			//Add to user's checkout list
			user.removeFromCheckedOutList(thisItem);
			
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
