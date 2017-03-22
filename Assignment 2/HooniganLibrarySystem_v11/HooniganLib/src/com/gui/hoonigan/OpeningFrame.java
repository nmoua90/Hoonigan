package com.gui.hoonigan;

import com.implementation.hoonigan.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
	private SingletonInformationExpert globalVariables = SingletonInformationExpert.getInstance();
	private String userName;
	private JFrame frame = new JFrame("The Hoonigan Library System");
	private JPanel contentPane;
	private JTextField userIDField;
	private JPasswordField passwordField;
	private int attempts;

	/**
	 * Create the frame.
	 */
	public OpeningFrame() {
		globalVariables.instantiateDefaultAdmin();
		
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
				//If user is admin
				if(!userIDField.getText().isEmpty() && globalVariables.isAdmin(userIDField.getText())){
					//If password is valid
					if(globalVariables.checkPassword(userIDField.getText(), passwordField.getPassword())){
						Client currentUser = globalVariables.getClientList().get(userIDField.getText());
						globalVariables.setCurrentUser(currentUser);
						AdminFrame adminLogin = new AdminFrame();
						adminLogin.setVisible();
						closeFrame();
					}
					else{
						if(attempts < 3){
							JOptionPane.showMessageDialog(null, "You have " + (3-attempts) + " attempts left before the program terminates"
									, "Wrong password!", JOptionPane.ERROR_MESSAGE);
							attempts++;
						}else
							
							closeFrame();
					}
				}
				else if(!userIDField.getText().isEmpty() && globalVariables.userExists(userIDField.getText())){
					if(globalVariables.checkPassword(userIDField.getText(), passwordField.getPassword())){
						userName = userIDField.getText();
						Client currentUser = globalVariables.getClientList().get(userName);
						globalVariables.setCurrentUser(currentUser);
						UserFrame crashScreen = new UserFrame();
						crashScreen.setVisible();
						closeFrame();
					}else{
						if(attempts < 3){
							JOptionPane.showMessageDialog(null, "I'm calling the cops on you!"
									, "Wrong password!", JOptionPane.ERROR_MESSAGE);
							attempts++;
						}else
							JOptionPane.showMessageDialog(null, "I'm calling the cops on you!"
									, "Wrong password!", JOptionPane.ERROR_MESSAGE);
							closeFrame();
					}
				}else{
					JOptionPane.showMessageDialog(null, "There is no registered member with that username.", "Error!", 
							JOptionPane.ERROR_MESSAGE);
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
				closeFrame();
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
				closeFrame();
			}
		});
		btnUserRecovery.setFont(new Font("Tw Cen MT", Font.PLAIN, 13));
		btnUserRecovery.setBounds(282, 255, 164, 23);
		contentPane.add(btnUserRecovery);
	}//end of constructor
		
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