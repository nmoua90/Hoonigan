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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
	private JFrame frame = new JFrame("Identify Yourself");
	private JPanel contentPane;
	private JTable displayTable;

	/**
	 * Create the frame.
	 */
	public IdentifyYourselfFrame() {
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
		
		// output table
		displayTable = new JTable();
		frame.setLocationRelativeTo(displayTable);

		displayTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		// Set the model of the search results table that defines the layout of the data
		displayTable.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
			},
			new String[] {
				"FIRST NAME", "LAST NAME", "ADDRESS"
			}
		));
		scrollPane.setViewportView(displayTable);	
		
		JButton btnNewButton = new JButton("SELECT");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				SecurityAnswerFrame goToSecurityAnswers = new SecurityAnswerFrame();
				goToSecurityAnswers.setVisible();
				closeFrame();
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
