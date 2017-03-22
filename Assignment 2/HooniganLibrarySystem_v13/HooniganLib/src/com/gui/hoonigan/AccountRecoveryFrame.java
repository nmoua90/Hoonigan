package com.gui.hoonigan;


import java.util.List;
import java.util.Map;

import com.implementation.hoonigan.*;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

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
	private SingletonInformationExpert globalVariables = SingletonInformationExpert.getInstance();
	private JFrame frame = new JFrame("Account Recovery");
	private JPanel contentPane;
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
		
		JLabel lblLastName = new JLabel("Last Name");
		lblLastName.setFont(new Font("Tw Cen MT", Font.PLAIN, 13));
		lblLastName.setBounds(36, 135, 80, 14);
		contentPane.add(lblLastName);
		
		lastNameField = new JTextField();
		lastNameField.setBounds(126, 133, 286, 20);
		contentPane.add(lastNameField);
		lastNameField.setColumns(10);
		
		JLabel lblAccountRecovery = new JLabel("Account Recovery");
		lblAccountRecovery.setFont(new Font("Tw Cen MT", Font.PLAIN, 20));
		lblAccountRecovery.setBounds(147, 24, 165, 23);
		contentPane.add(lblAccountRecovery);
		
		JLabel lblPromptName = new JLabel("Please enter your Last Name below");
		lblPromptName.setFont(new Font("Tw Cen MT", Font.ITALIC, 15));
		lblPromptName.setBounds(113, 89, 234, 20);
		contentPane.add(lblPromptName);
		
		JButton btnSearchAccount = new JButton("Search For My Account");
		btnSearchAccount.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try{
					List<Client> searchResults = globalVariables.searchForUser(lastNameField.getText());
					if(searchResults.isEmpty()){
						JOptionPane.showMessageDialog(null, "There are no matching names in our database!", 
								"Sorry!", JOptionPane.WARNING_MESSAGE);
					}else if(lastNameField.getText().compareTo("") == 0){
						JOptionPane.showMessageDialog(null, "Please enter your name!", 
								"Error!", JOptionPane.WARNING_MESSAGE);
					}else{
						IdentifyYourselfFrame goToSelectAccountScreen = new IdentifyYourselfFrame(searchResults);
						goToSelectAccountScreen.setVisible();
						closeFrame();
					}
					
					
				}catch(NullPointerException ex){
					ex.printStackTrace();
					//JOptionPane.showMessageDialog(null, "Woops! We ran into an error while searching for you!", "Error", 
					//		JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		btnSearchAccount.setFont(new Font("Tw Cen MT", Font.PLAIN, 13));
		btnSearchAccount.setBounds(36, 186, 165, 23);
		contentPane.add(btnSearchAccount);
		
		JButton btnGoBack = new JButton("Back To Previous Screen");
		btnGoBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				OpeningFrame backtrack = new OpeningFrame();
				backtrack.setVisible();
				closeFrame();
			}
		});
		btnGoBack.setFont(new Font("Tw Cen MT", Font.PLAIN, 13));
		btnGoBack.setBounds(247, 186, 165, 22);
		contentPane.add(btnGoBack);
	
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