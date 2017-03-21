package com.gui.hoonigan;

import com.implementation.hoonigan.*;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import com.implementation.hoonigan.Client;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**IdentifyYourselfFrame class
 * This class creates a frame, which is meant for Username/Password recovery. From the previous frame, the user has entered their full name.
 * After searching the user database with their name, a table within this class displays a list of user(s) with matching names, as well as a 
 * corresponding address. If successful, the user selects the matching row corresponding to themselves, and are sent along in the next
 * step of user/password recovery.
 * 
 * @author Hoonigan
 *
 */
public class IdentifyYourselfFrame{
	private SingletonInformationExpert globalVariables = SingletonInformationExpert.getInstance();
	private JFrame frame = new JFrame("Identify Yourself");
	private JPanel contentPane;
	private JTable displayTable;
	private Client chosenMember;
	private String addressSelection = "";

	/**
	 * Create the frame.
	 */
	public IdentifyYourselfFrame(List<Client> searchResults) {
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setBounds(100, 100, 650, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		frame.setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblSelectYourselfPrompt = new JLabel("Select Yourself From The Following List");
		lblSelectYourselfPrompt.setFont(new Font("Tw Cen MT", Font.PLAIN, 20));
		lblSelectYourselfPrompt.setBounds(171, 25, 319, 45);
		contentPane.add(lblSelectYourselfPrompt);
	

		JScrollPane scrollPane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setBounds(25, 81, 592, 227);
		contentPane.add(scrollPane);
		
		//How to Make a table
		//Name your columns
		String columns[] = {"FIRST NAME", "LAST NAME", "ADDRESS"};
				
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
		
		//Traverse through your ArrayList, and write the variables
		for(int i = 0; i<searchResults.size(); i++){
			tableModel.addRow(new Object[] {
					searchResults.get(i).getFirstName(),
					searchResults.get(i).getLastName(),
					searchResults.get(i).getStreetAddress()
			});
		}
		displayTable.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
			public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
				//For getValueAt(), First param is the row selected, Second param is column you want -> the 1st column has Last Name
				if (!evt.getValueIsAdjusting()) {//This line prevents double events
					addressSelection += displayTable.getValueAt(displayTable.getSelectedRow(), 2);
			    }
	        }
	    });
		
		//End of table
		  
		 
		JButton btnNewButton = new JButton("SELECT");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
			    for(Entry<String, Client> entry: globalVariables.getClientList().entrySet()) {
			    	if(entry.getValue().getStreetAddress().contains(addressSelection)){
			    		chosenMember = entry.getValue();
			    		globalVariables.setRestoreUserAccount(chosenMember);
						SecurityAnswerFrame goToSecurityAnswers = new SecurityAnswerFrame();
						goToSecurityAnswers.setVisible();
						closeFrame();
			    	}
			    }
			}
		});
		btnNewButton.setFont(new Font("Tw Cen MT", Font.PLAIN, 15));
		btnNewButton.setBounds(56, 348, 201, 59);
		contentPane.add(btnNewButton);
		
		JButton btnBackToPrevious = new JButton("BACK TO PREVIOUS SCREEN");
		btnBackToPrevious.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				AccountRecoveryFrame goBackToRecoveryFirstScreen = new AccountRecoveryFrame();
				goBackToRecoveryFirstScreen.setVisible();
				closeFrame();
			}
		});
		btnBackToPrevious.setFont(new Font("Tw Cen MT", Font.PLAIN, 15));
		btnBackToPrevious.setBounds(367, 348, 227, 59);
		contentPane.add(btnBackToPrevious);
	}

	/**
	 * 
	 */
	public void clearTable(){
		JScrollPane scrollPane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setBounds(25, 81, 592, 227);
		contentPane.add(scrollPane);
		
		//How to Make a table
		//Name your columns
		String columns[] = {"FIRST NAME", "LAST NAME", "ADDRESS"};
				
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
