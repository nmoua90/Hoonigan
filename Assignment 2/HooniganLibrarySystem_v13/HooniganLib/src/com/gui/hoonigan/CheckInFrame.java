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

import com.implementation.hoonigan.Client;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
		String columns[] = {"ID","TITLE","DUE-DATE","LATE?","FEE"};
						
		//Set your default table, initially, you have only columns, and 0 rows
		DefaultTableModel tableModel = new DefaultTableModel(columns, 0){
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
				
		/**
		 * //Traverse through your ArrayList, and write the variables
		for(int i = 0; i<searchResults.size(); i++){
			tableModel.addRow(new Object[] {
					searchResults.get(i).getUserName(),
					searchResults.get(i).getFirstName(),
					searchResults.get(i).getLastName(),
					searchResults.get(i).getStreetAddress()
			});
		}
		displayTable.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
			public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
				//For getValueAt(), First param is the row selected, Second param is column you want -> the 1st column has Last Name
				if (!evt.getValueIsAdjusting()) {//This line prevents double events
					//Make sure to record the KEY value for the hashmap
					//Record every click the user does. Each click in the table adds a user id to the list.
					userSelections.add(new String(displayTable.getValueAt(displayTable.getSelectedRow(), 0).toString()));
			    }
			}
		});
		 * 
		 */
				
		//End of table
				  		
		lblYourCheckedoutItems = new JLabel("Your Checked-out Items");
		lblYourCheckedoutItems.setFont(new Font("Tw Cen MT", Font.PLAIN, 20));
		lblYourCheckedoutItems.setBounds(31, 72, 204, 23);
		contentPane.add(lblYourCheckedoutItems);
		
		btnCheckIn = new JButton("Check In This Item");
		btnCheckIn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
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
