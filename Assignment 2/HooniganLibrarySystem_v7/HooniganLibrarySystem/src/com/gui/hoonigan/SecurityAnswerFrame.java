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
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**SecurityAnswerFrame class
* This class creates a frame, which fetches the user's Security Question, and prompts them for an answer. Gives the user three chances to answer correctly, 
* otherwise locks their account.
*/
public class SecurityAnswerFrame{
	private JFrame frame = new JFrame("Security Answer");
	private JPanel contentPane;
	private JTextField questionField;
	private JTextField answerField;
	private JButton btnSubmitAnswer;

	/**
	 * Create the frame.
	 */
	public SecurityAnswerFrame() {
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setBounds(100, 100, 500, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		frame.setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblAnswerPrompt = new JLabel("Answer correctly to gain access to your account");
		lblAnswerPrompt.setFont(new Font("Tw Cen MT", Font.PLAIN, 20));
		lblAnswerPrompt.setBounds(57, 31, 402, 39);
		contentPane.add(lblAnswerPrompt);
		
		questionField = new JTextField();
		questionField.setEditable(false);
		questionField.setBounds(22, 121, 437, 29);
		contentPane.add(questionField);
		questionField.setColumns(10);
		
		answerField = new JTextField();
		answerField.setBounds(22, 212, 437, 29);
		contentPane.add(answerField);
		answerField.setColumns(10);
		
		JLabel lblYourAnswer = new JLabel("Your Answer");
		lblYourAnswer.setFont(new Font("Tw Cen MT", Font.PLAIN, 15));
		lblYourAnswer.setBounds(191, 181, 83, 14);
		contentPane.add(lblYourAnswer);
		
		JLabel lblYourQuestion = new JLabel("Question");
		lblYourQuestion.setFont(new Font("Tw Cen MT", Font.PLAIN, 15));
		lblYourQuestion.setBounds(200, 96, 67, 14);
		contentPane.add(lblYourQuestion);
		
		btnSubmitAnswer = new JButton("Submit Answer");
		btnSubmitAnswer.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//if correct give popup with username + password
					closeFrame();
					JOptionPane.showMessageDialog(null, "User Name: \n\n Password: ", "Here are your credentials!", 
							JOptionPane.INFORMATION_MESSAGE);
				//if incorrect, warn user that they only have 2 more tries to guess right
				//JOptionPane.showMessageDialog(NewUser.this, "You have n attempts left to answer correctly, before
				//your account is locked.", "Incorrect answer!", JOptionPane.ERROR_MESSAGE);
			}
		});
		btnSubmitAnswer.setFont(new Font("Tw Cen MT", Font.PLAIN, 15));
		btnSubmitAnswer.setBounds(142, 285, 178, 39);
		contentPane.add(btnSubmitAnswer);
		
		frame.setLocationRelativeTo(btnSubmitAnswer);
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
