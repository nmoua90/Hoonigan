package com.gui.hoonigan;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.implementation.hoonigan.Client;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Map;

import javax.swing.JPasswordField;

public class DeleteUserFrame extends JFrame {
	private SingletonInformationExpert globalVariables = SingletonInformationExpert.getInstance();
	private JPanel contentPane;
	private JTextField deleteThisUser;
	private JPasswordField passwordField;


	/**
	 * Create the frame.
	 */
	public DeleteUserFrame() {
		setTitle("Delete User Screen");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 515, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblPrompt1 = new JLabel("For safety purposes, please manually enter the USERNAME of the \r\n");
		lblPrompt1.setFont(new Font("Tw Cen MT", Font.BOLD | Font.ITALIC, 15));
		lblPrompt1.setBounds(63, 22, 389, 23);
		contentPane.add(lblPrompt1);
		
		JLabel lblPrompt2 = new JLabel("member to be deleted. Then re-enter your password before clicking DELETE.");
		lblPrompt2.setFont(new Font("Tw Cen MT", Font.BOLD | Font.ITALIC, 15));
		lblPrompt2.setBounds(29, 56, 460, 23);
		contentPane.add(lblPrompt2);
		this.setLocationRelativeTo(lblPrompt2);
		
		JLabel lblUsernameToBeDeleted = new JLabel("Username to be deleted");
		lblUsernameToBeDeleted.setFont(new Font("Tw Cen MT", Font.PLAIN, 15));
		lblUsernameToBeDeleted.setBounds(25, 121, 175, 14);
		contentPane.add(lblUsernameToBeDeleted);
		
		deleteThisUser = new JTextField();
		deleteThisUser.setBounds(242, 119, 235, 20);
		contentPane.add(deleteThisUser);
		deleteThisUser.setColumns(10);
		
		JLabel lblEnterYourPassword = new JLabel("Enter your password");
		lblEnterYourPassword.setFont(new Font("Tw Cen MT", Font.PLAIN, 15));
		lblEnterYourPassword.setBounds(25, 168, 146, 14);
		contentPane.add(lblEnterYourPassword);
		
		JButton btnDelete = new JButton("DELETE USER");
		btnDelete.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				deleteMember();
			}	
		});
		btnDelete.setFont(new Font("Tw Cen MT", Font.PLAIN, 15));
		btnDelete.setBounds(182, 214, 156, 23);
		contentPane.add(btnDelete);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(242, 166, 235, 20);
		contentPane.add(passwordField);
	}
	
	/**
	 * 
	 */
	public void deleteMember(){
		Client currentAdmin = globalVariables.getCurrentUser();
		
		//Password fields
		char[] currentPW = currentAdmin.getPassword();
		String password = new String(currentPW);
		String passCheck = new String(passwordField.getPassword());
		
		//User to be Deleted
		Client deleteThisMember = globalVariables.getClientAccount(deleteThisUser.getText());
		
		//List of all users
		Map<String, Client> userList = globalVariables.getClientList();
		
		if(passCheck == ""){
			JOptionPane.showMessageDialog(null, "Please enter a password!", "Empty Password", JOptionPane.WARNING_MESSAGE);
		}
		//If password is correct
		else if(password.compareTo(passCheck) == 0){
			//Delete the username if it exists in the database
			if(deleteThisMember != null){
				if(globalVariables.isAdmin(deleteThisMember.getUserName())){
					JOptionPane.showMessageDialog(null, "You can't delete admins!", "Failure", JOptionPane.WARNING_MESSAGE);
				}
				else{
					userList.remove(deleteThisMember.getUserName());
					JOptionPane.showMessageDialog(null, "The user was deleted!", "Success!", JOptionPane.INFORMATION_MESSAGE);
					setVisible(false);
					dispose();
				}
			}else{
				JOptionPane.showMessageDialog(null, "That user doesn't exist!", "Failure", JOptionPane.WARNING_MESSAGE);
			}
		}
		//Password is wrong
		else
			JOptionPane.showMessageDialog(null, "You entered the wrong password.!", "Wrong Password", JOptionPane.WARNING_MESSAGE);
	}//end of method
	
}
