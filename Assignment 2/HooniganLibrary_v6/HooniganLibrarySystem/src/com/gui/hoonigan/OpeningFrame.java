package com.gui.hoonigan;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**OpeningFrame class
 * This class creates a frame, which prompts the user for login information. Also contains a user recovery option, and user registry option.
 * @author Hoonigan
 *
 */
public class OpeningFrame{
	//private User activeUser;
	//temp
	private String userName;
	private JFrame frame = new JFrame("The Hoonigan Library System");
	private JPanel contentPane;
	private JTextField userIDField;
	private JPasswordField passwordField;

	/**
	 * Create the frame.
	 */
	public OpeningFrame() {
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 500, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		frame.setContentPane(contentPane);
		contentPane.setLayout(null);
		
		userIDField = new JTextField();
		frame.setLocationRelativeTo(userIDField);
		userIDField.setBounds(137, 88, 233, 20);
		contentPane.add(userIDField);
		userIDField.setColumns(10);
		
		JLabel lblEnterUserId = new JLabel("User Name");
		lblEnterUserId.setFont(new Font("Tw Cen MT", Font.BOLD, 14));
		lblEnterUserId.setBounds(60, 91, 72, 14);
		contentPane.add(lblEnterUserId);
	
		frame.setLocationRelativeTo(passwordField);
		passwordField = new JPasswordField();
		passwordField.setBounds(137, 128, 233, 20);
		contentPane.add(passwordField);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tw Cen MT", Font.BOLD, 14));
		lblPassword.setBounds(70, 131, 57, 14);
		contentPane.add(lblPassword);
		
		JLabel lblHooniganLibrarySystem = new JLabel("HOONIGAN LIBRARY SYSTEM");
		lblHooniganLibrarySystem.setFont(new Font("Tw Cen MT", Font.PLAIN, 20));
		lblHooniganLibrarySystem.setBounds(112, 32, 245, 14);
		contentPane.add(lblHooniganLibrarySystem);
		
		JButton btnLogIn = new JButton("LOG IN");
		btnLogIn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(isAdmin()){
					userName = userIDField.getText();
					AdminFrame adminLogin = new AdminFrame(userName);
					adminLogin.setVisible();
					closeFrame();
				}
				else{
					userName = userIDField.getText();
					UserFrame crashScreen = new UserFrame(userName);
					crashScreen.setVisible();
					closeFrame();
					//check if userID, and password are valid
						//ie: search the HashMap with the key [userID], check the key value with password using compareTo()
						//if the key matches = correct password and user ID
							//open new frame for library app
					//if no userID [userID = null] -> return popup saying there is no account
					//if userID exists but password is wrong -> return popup saying password is wrong
				}
			}
		});
		btnLogIn.setFont(new Font("Tw Cen MT", Font.PLAIN, 13));
		btnLogIn.setBounds(197, 169, 89, 23);
		contentPane.add(btnLogIn);
		
		JButton btnRegister = new JButton("REGISTER");
		btnRegister.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				RegisterNewUserFrame registerScreen = new RegisterNewUserFrame();
				registerScreen.setVisible();
			}
		});
		btnRegister.setFont(new Font("Tw Cen MT", Font.PLAIN, 13));
		btnRegister.setBounds(81, 256, 89, 23);
		contentPane.add(btnRegister);
		
		JLabel lblRegister = new JLabel("New User? Register Now!");
		lblRegister.setFont(new Font("Tw Cen MT", Font.PLAIN, 14));
		lblRegister.setBounds(55, 231, 165, 14);
		contentPane.add(lblRegister);
		
		JLabel lblForgotYourId = new JLabel("Forgot Your ID or Password?");
		lblForgotYourId.setFont(new Font("Tw Cen MT", Font.PLAIN, 14));
		lblForgotYourId.setBounds(282, 229, 174, 14);
		contentPane.add(lblForgotYourId);
		
		JButton btnUserRecovery = new JButton("ACCOUNT RECOVERY");
		btnUserRecovery.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				AccountRecoveryFrame recoveryFrame = new AccountRecoveryFrame();
				recoveryFrame.setVisible();
			}
		});
		btnUserRecovery.setFont(new Font("Tw Cen MT", Font.PLAIN, 13));
		btnUserRecovery.setBounds(282, 255, 164, 23);
		contentPane.add(btnUserRecovery);
	}//end of constructor
	
	/**
	 * 
	 * @return
	 */
	private boolean isAdmin(){
		String enteredID = userIDField.getText();
		if(enteredID.compareTo("admin") == 0)
			return true;
		return false;
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