package com.gui.hoonigan;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**AccountRecoveryFrame class
 * This class creates a frame, that allows a user to enter their first and last name. Their name is then searched, in hopes of finding their 
 * account within the system.
 * @author Hoonigan
 *
 */
public class AccountRecoveryFrame{
	private JFrame frame = new JFrame("Account Recovery");
	private JPanel contentPane;
	private JTextField firstNameField;
	private JTextField lastNameField;

	/**
	 * Create the frame.
	 */
	public AccountRecoveryFrame() {
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		frame.setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblFirstName = new JLabel("First Name");
		lblFirstName.setFont(new Font("Tw Cen MT", Font.PLAIN, 13));
		lblFirstName.setBounds(54, 127, 80, 14);
		contentPane.add(lblFirstName);
		
		JLabel lblLastName = new JLabel("Last Name");
		lblLastName.setFont(new Font("Tw Cen MT", Font.PLAIN, 13));
		lblLastName.setBounds(54, 168, 80, 14);
		contentPane.add(lblLastName);
		
		firstNameField = new JTextField();
		firstNameField.setBounds(160, 125, 234, 20);
		contentPane.add(firstNameField);
		firstNameField.setColumns(10);
		
		lastNameField = new JTextField();
		lastNameField.setBounds(160, 166, 234, 20);
		contentPane.add(lastNameField);
		lastNameField.setColumns(10);
		
		JLabel lblAccountRecovery = new JLabel("Account Recovery");
		lblAccountRecovery.setFont(new Font("Tw Cen MT", Font.PLAIN, 20));
		lblAccountRecovery.setBounds(147, 24, 165, 23);
		contentPane.add(lblAccountRecovery);
		
		JLabel lblPromptName = new JLabel("Please enter your First and Last Name below");
		lblPromptName.setFont(new Font("Tw Cen MT", Font.ITALIC, 15));
		lblPromptName.setBounds(83, 82, 275, 20);
		contentPane.add(lblPromptName);
		
		JButton btnSearchAccount = new JButton("Search For My Account");
		btnSearchAccount.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				IdentifyYourselfFrame goToSelectAccountScreen = new IdentifyYourselfFrame();
				goToSelectAccountScreen.setVisible();
				closeFrame();
			}
		});
		btnSearchAccount.setFont(new Font("Tw Cen MT", Font.PLAIN, 13));
		btnSearchAccount.setBounds(130, 216, 165, 23);
		contentPane.add(btnSearchAccount);
	
		frame.setLocationRelativeTo(null);
				
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