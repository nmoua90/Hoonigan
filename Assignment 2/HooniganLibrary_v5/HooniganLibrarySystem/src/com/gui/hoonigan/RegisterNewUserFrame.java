package com.gui.hoonigan;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JButton;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

/**RegisterNewUserFrame class
 * This class creates a frame that allows the User to register a new Hoonigan Account.
 * @author Hoonigan
 *
 */
public class RegisterNewUserFrame{
	private JFrame frame = new JFrame("New User Registration");
	private JPanel contentPane;
	private JTextField userFirstNameField;
	private JTextField userLastNameField;
	private JTextField userSecurityQuestionTextField;
	private JTextField userSecurityAnswerTextField;
	private JPasswordField initialPasswordField;
	private JPasswordField passwordCheckField;
	private JTextField addressField;
	private JTextField userNameField;
	private int userAgree = 0;
	
	public RegisterNewUserFrame() {
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setBounds(100, 100, 500, 600);
		frame.setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		frame.setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblRegisterNewUser = new JLabel("Register New User");
		lblRegisterNewUser.setFont(new Font("Tw Cen MT", Font.PLAIN, 20));
		lblRegisterNewUser.setBounds(173, 21, 153, 28);
		contentPane.add(lblRegisterNewUser);
		
		JLabel lblFirstName = new JLabel("First Name");
		lblFirstName.setFont(new Font("Tw Cen MT", Font.PLAIN, 13));
		lblFirstName.setBounds(53, 84, 70, 14);
		contentPane.add(lblFirstName);
		
		JLabel lblLastName = new JLabel("Last Name");
		lblLastName.setFont(new Font("Tw Cen MT", Font.PLAIN, 13));
		lblLastName.setBounds(53, 119, 61, 14);
		contentPane.add(lblLastName);
		
		userFirstNameField = new JTextField();
		userFirstNameField.setBounds(123, 82, 312, 20);
		contentPane.add(userFirstNameField);
		userFirstNameField.setColumns(10);
		
		userLastNameField = new JTextField();
		userLastNameField.setBounds(123, 117, 312, 20);
		contentPane.add(userLastNameField);
		userLastNameField.setColumns(10);
		
		JLabel lblSecurityQuestion = new JLabel("Security Question");
		lblSecurityQuestion.setFont(new Font("Tw Cen MT", Font.PLAIN, 13));
		lblSecurityQuestion.setBounds(17, 193, 96, 14);
		contentPane.add(lblSecurityQuestion);
		
		JLabel lblSecurityAnswer = new JLabel("Your Answer");
		lblSecurityAnswer.setFont(new Font("Tw Cen MT", Font.PLAIN, 13));
		lblSecurityAnswer.setBounds(43, 230, 70, 14);
		contentPane.add(lblSecurityAnswer);
		
		userSecurityQuestionTextField = new JTextField();
		userSecurityQuestionTextField.setBounds(123, 191, 312, 20);
		contentPane.add(userSecurityQuestionTextField);
		userSecurityQuestionTextField.setColumns(10);
		
		userSecurityAnswerTextField = new JTextField();
		userSecurityAnswerTextField.setBounds(123, 228, 312, 20);
		contentPane.add(userSecurityAnswerTextField);
		userSecurityAnswerTextField.setColumns(10);
		
		JRadioButton rdbtnAgreeToTerms = new JRadioButton("I agree to the terms and conditions ", false);
		rdbtnAgreeToTerms.addItemListener(new ItemListener() {
		    public void itemStateChanged(ItemEvent event) {
		    	int state = event.getStateChange();
		        if (state == ItemEvent.SELECTED) {
		        	userAgree = 1;
		        } else if (state == ItemEvent.DESELECTED) {
		        	userAgree = 0;
		        }
		    }
		});
		rdbtnAgreeToTerms.setFont(new Font("Tw Cen MT", Font.PLAIN, 13));
		rdbtnAgreeToTerms.setBounds(17, 406, 228, 23);
		contentPane.add(rdbtnAgreeToTerms);
		
		JLabel lblIAgreeToTerms = new JLabel("New Users must agree to the Hoonigan Library's User Terms and Conditions");
		lblIAgreeToTerms.setFont(new Font("Tw Cen MT", Font.BOLD | Font.ITALIC, 13));
		lblIAgreeToTerms.setBounds(42, 367, 412, 28);
		contentPane.add(lblIAgreeToTerms);
		
		JButton btnReadUserTerms = new JButton("Read User Terms and Conditions");
		btnReadUserTerms.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				TermsAndConditionsFrame frame = new TermsAndConditionsFrame();
				frame.setVisible();
			}
		});
		btnReadUserTerms.setFont(new Font("Tw Cen MT", Font.PLAIN, 13));
		btnReadUserTerms.setBounds(255, 406, 219, 23);
		contentPane.add(btnReadUserTerms);
		
		JButton btnCreateNewAccount = new JButton("Create New Account");
		btnCreateNewAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String passwordInitial = "";
				String passwordCheck = "";
				
				for(char letter : initialPasswordField.getPassword())
					passwordInitial += letter;
				
				for(char letter : passwordCheckField.getPassword())
					passwordCheck += letter;
				
				if(userAgree == 1){
					if(userFirstNameField.getText().compareTo("") == 0 || userLastNameField.getText().compareTo("") == 0 || 
							addressField.getText().compareTo("") == 0 || userSecurityQuestionTextField.getText().compareTo("") == 0 || 
							userSecurityAnswerTextField.getText().compareTo("") == 0 || passwordCheck.compareTo("") == 0 || 
							passwordInitial.compareTo("") == 0 || userNameField.getText().compareTo("") == 0)
					{
						JOptionPane.showMessageDialog(null, "You must fill in all fields", "Error!", 
								JOptionPane.ERROR_MESSAGE);
					}
					else if(passwordInitial.compareTo(passwordCheck) == 0){
						closeFrame();
						OpeningFrame frame = new OpeningFrame();
						frame.setVisible();
						//Call the function that creates a new User ID. 
						//Return the new User ID to the user
						String userId = userNameField.getText();	//Change this to the actual randomized user ID
						String successMessage = String.format("You are now a HOONIGAN LIBRARY member!\n\nThe following is your User ID."
								+ " Log in by using this User ID, combined with the password you chose!\n\nUser ID: %s", 
								userId);
						JOptionPane.showMessageDialog(null, successMessage, "Congratulations!", 
								JOptionPane.PLAIN_MESSAGE);
					}
					else
						JOptionPane.showMessageDialog(null, "Your passwords don't match!", "Error!", 
								JOptionPane.ERROR_MESSAGE);
				}else{
					JOptionPane.showMessageDialog(null, "You must agree to the terms to register", "Sorry!", 
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnCreateNewAccount.setFont(new Font("Tw Cen MT", Font.PLAIN, 13));
		btnCreateNewAccount.setBounds(152, 469, 189, 66);
		contentPane.add(btnCreateNewAccount);
		
		JLabel lblEnterPassword = new JLabel("Enter Password");
		lblEnterPassword.setFont(new Font("Tw Cen MT", Font.PLAIN, 13));
		lblEnterPassword.setBounds(28, 302, 86, 14);
		contentPane.add(lblEnterPassword);
		
		JLabel lblReenterPassword = new JLabel("Re-enter Password");
		lblReenterPassword.setFont(new Font("Tw Cen MT", Font.PLAIN, 13));
		lblReenterPassword.setBounds(10, 338, 103, 14);
		contentPane.add(lblReenterPassword);
		
		initialPasswordField = new JPasswordField();
		initialPasswordField.setBounds(123, 300, 312, 20);
		contentPane.add(initialPasswordField);
		
		passwordCheckField = new JPasswordField();
		passwordCheckField.setBounds(123, 336, 312, 20);
		contentPane.add(passwordCheckField);
		
		JLabel lblAddress = new JLabel("Address");
		lblAddress.setFont(new Font("Tw Cen MT", Font.PLAIN, 13));
		lblAddress.setBounds(67, 156, 46, 14);
		contentPane.add(lblAddress);
		
		addressField = new JTextField();
		addressField.setBounds(123, 154, 312, 20);
		contentPane.add(addressField);
		addressField.setColumns(10);
		
		JLabel lblDesiredUsername = new JLabel("Desired Username");
		lblDesiredUsername.setFont(new Font("Tw Cen MT", Font.PLAIN, 13));
		lblDesiredUsername.setBounds(17, 267, 103, 14);
		contentPane.add(lblDesiredUsername);
		
		userNameField = new JTextField();
		userNameField.setBounds(123, 265, 312, 20);
		contentPane.add(userNameField);
		userNameField.setColumns(10);
	}
	
	/**
	 * 
	 * @param attemptedUserName
	 * @return
	 */
	public String checkValidUserName(String attemptedUserName){
		//Check the hashmap for the username, if it doesn't exist, the attemptedUserName is valid
			//if valid -> return the attemptedUserName
			//if invalid -> return "", reset the userName field and send a pop-up, saying the username is already taken
		return "";
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