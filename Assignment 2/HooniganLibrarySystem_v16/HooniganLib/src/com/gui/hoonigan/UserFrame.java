package com.gui.hoonigan;


import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.implementation.hoonigan.Client;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextField;

/**UserFrame class
 * This class creates a frame, which allows the User to either proceed to checking in an item, checking out an item, or logging out.
 * @author Hoonigan
 *
 */
public class UserFrame{
	private SingletonInformationExpert globalVariables = SingletonInformationExpert.getInstance();
	private JFrame frame = new JFrame("User Selection");
	private JPanel contentPane;
	private JTextField currentUserTextField;
	private Client currentUser = globalVariables.getCurrentUser();
	private String currentUserName = globalVariables.getCurrentUser().getUserName();

	/**
	 * Create the frame.
	 */
	public UserFrame() { 
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 500, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		frame.setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblWelcomeMessage = new JLabel("Welcome to the Hoonigan Library! Please select an option!");
		frame.setLocationRelativeTo(lblWelcomeMessage);
		lblWelcomeMessage.setFont(new Font("Tw Cen MT", Font.PLAIN, 15));
		lblWelcomeMessage.setBounds(71, 69, 365, 35);
		contentPane.add(lblWelcomeMessage);
		
		JButton btnCheckIn = new JButton("I want to check-in item(s)");
		btnCheckIn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				CheckInFrame checkInScreen = new CheckInFrame();
				checkInScreen.setVisible();
			}
		});
		btnCheckIn.setFont(new Font("Tw Cen MT", Font.PLAIN, 13));
		btnCheckIn.setBounds(138, 123, 223, 47);
		contentPane.add(btnCheckIn);
		
		JButton btnCheckOut = new JButton("I want to check-out item(s)");
		btnCheckOut.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				//If the user doesn't owe any fees, let them go to the next screen
					CheckOutFrame enterLibrary = new CheckOutFrame();
					enterLibrary.setVisible();
				//If the owner fees, deny them from going to the next screen, prompt them for their overdue fees...sucka
			}
		});
		btnCheckOut.setFont(new Font("Tw Cen MT", Font.PLAIN, 13));
		btnCheckOut.setBounds(138, 199, 223, 53);
		contentPane.add(btnCheckOut);
		
		JButton btnLogOut = new JButton("Log Out");
		btnLogOut.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				OpeningFrame startOver = new OpeningFrame();
				startOver.setVisible();
				closeFrame();
			}
		});
		btnLogOut.setFont(new Font("Tw Cen MT", Font.PLAIN, 13));
		btnLogOut.setBounds(138, 281, 223, 53);
		contentPane.add(btnLogOut);
		
		JLabel lblCurrentUser = new JLabel("Current User");
		lblCurrentUser.setFont(new Font("Tw Cen MT", Font.PLAIN, 15));
		lblCurrentUser.setBounds(20, 25, 78, 14);
		contentPane.add(lblCurrentUser);
		
		currentUserTextField = new JTextField();
		currentUserTextField.setEditable(false);
		currentUserTextField.setBounds(128, 23, 331, 20);
		contentPane.add(currentUserTextField);
		currentUserTextField.setColumns(10);
		currentUserTextField.setText(currentUserName);
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