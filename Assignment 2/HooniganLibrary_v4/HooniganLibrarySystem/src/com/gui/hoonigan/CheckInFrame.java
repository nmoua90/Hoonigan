package com.gui.hoonigan;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
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
	private JFrame frame = new JFrame("User Check-In");
	private JPanel contentPane;
	private JTable displayTable;
	private JLabel lblYourCheckedoutItems;
	private JButton btnCheckIn;
	private JTextField currentUserField;
	private JLabel lblCurrentUser;
	
	/**
	 * Create the frame.
	 */
	public CheckInFrame(String currentUser) {//Change parameter -> User currentUser in Final Implementation
		//this.currentUser = currentUser;
		
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setBounds(100, 100, 550, 350);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		frame.setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setBounds(31, 106, 475, 137);
		contentPane.add(scrollPane);
		
		// output table
		displayTable = new JTable();
		frame.setLocationRelativeTo(displayTable);

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
		currentUserField.setText(currentUser);
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
