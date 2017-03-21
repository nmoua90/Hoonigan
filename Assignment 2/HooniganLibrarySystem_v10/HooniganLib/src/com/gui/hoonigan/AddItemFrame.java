package com.gui.hoonigan;


import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.implementation.hoonigan.Client;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**AddItemFrame
 * This class creates a frame, which allows an Administrator to manually add an item to the library.
 * @author Hoonigan
 *
 */
public class AddItemFrame{
	private SingletonInformationExpert globalVariables = SingletonInformationExpert.getInstance();
	private JFrame frame = new JFrame("Admin Window: Add Individual Item To The Library");
	private JPanel contentPane;
	private JTextField itemIDField;
	private JTextField artistTextField;
	private JTextField titleTextField;
	private JTextField volumeTextField;
	private JTextField currentUserField;
	private JTextField libIdTxtField;
	private Client currentUser = globalVariables.getCurrentUser();
	private String currentUserName = globalVariables.getCurrentUser().getUserName();

	/**
	 * Create the frame.
	 */
	public AddItemFrame() {
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setBounds(100, 100, 580, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		frame.setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JComboBox mediaOptionsBox = new JComboBox();
		mediaOptionsBox.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				JComboBox cb = (JComboBox) e.getSource();
		        String selectedChoice = (String) cb.getSelectedItem();
		        System.out.println(selectedChoice);
			}
		});
		mediaOptionsBox.setModel(new DefaultComboBoxModel(new String[] {"BOOK", "MAGAZINE", "CD", "DVD"}));
		mediaOptionsBox.setBounds(392, 83, 102, 20);
		contentPane.add(mediaOptionsBox);
		
		JLabel lblPromptForBox = new JLabel("Media Type Of Item Being Added");
		lblPromptForBox.setFont(new Font("Tw Cen MT", Font.PLAIN, 15));
		lblPromptForBox.setBounds(45, 85, 211, 15);
		contentPane.add(lblPromptForBox);
		
		itemIDField = new JTextField();
		itemIDField.setBounds(147, 118, 347, 20);
		contentPane.add(itemIDField);
		itemIDField.setColumns(10);
		
		JLabel lblItemId = new JLabel("Item ID");
		lblItemId.setFont(new Font("Tw Cen MT", Font.PLAIN, 15));
		lblItemId.setBounds(45, 120, 72, 14);
		contentPane.add(lblItemId);
		
		JLabel lblAuthor = new JLabel("Artist/Author");
		lblAuthor.setFont(new Font("Tw Cen MT", Font.PLAIN, 15));
		lblAuthor.setBounds(45, 155, 94, 14);
		contentPane.add(lblAuthor);
		
		JLabel lblVolume = new JLabel("Volume");
		lblVolume.setFont(new Font("Tw Cen MT", Font.PLAIN, 15));
		lblVolume.setBounds(45, 217, 46, 14);
		contentPane.add(lblVolume);
		
		JLabel lblTitle = new JLabel("Title");
		lblTitle.setFont(new Font("Tw Cen MT", Font.PLAIN, 15));
		lblTitle.setBounds(45, 187, 46, 14);
		contentPane.add(lblTitle);
		
		artistTextField = new JTextField();
		artistTextField.setBounds(146, 153, 348, 20);
		contentPane.add(artistTextField);
		artistTextField.setColumns(10);
		
		titleTextField = new JTextField();
		titleTextField.setBounds(146, 185, 348, 20);
		contentPane.add(titleTextField);
		titleTextField.setColumns(10);
		
		volumeTextField = new JTextField();
		volumeTextField.setBounds(146, 215, 348, 20);
		contentPane.add(volumeTextField);
		volumeTextField.setColumns(10);
		
		JButton btnAddNewItem = new JButton("Add New Item To Library");
		btnAddNewItem.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//If DVD or Magazine, there is no required author/artist 
					if(itemIDField.getText().compareTo("") == 0 ||
							titleTextField.getText().compareTo("") == 0){
						
					}
				//If other item, author/artist is also required
					if(artistTextField.getText().compareTo("") == 0 || itemIDField.getText().compareTo("") == 0 ||
							titleTextField.getText().compareTo("") == 0){
						JOptionPane.showMessageDialog(null, "There are missing fields [Volume is not a required field].", 
								"Error!", JOptionPane.ERROR_MESSAGE);
					}
			}
		});
		btnAddNewItem.setFont(new Font("Tw Cen MT", Font.PLAIN, 15));
		btnAddNewItem.setBounds(176, 332, 211, 37);
		contentPane.add(btnAddNewItem);
		frame.setLocationRelativeTo(btnAddNewItem);
		
		currentUserField = new JTextField();
		currentUserField.setEditable(false);
		currentUserField.setBounds(47, 39, 455, 20);
		contentPane.add(currentUserField);
		currentUserField.setColumns(10);
		currentUserField.setText(currentUserName);
		
		JLabel lblCurrentUser = new JLabel("Current User");
		lblCurrentUser.setFont(new Font("Tw Cen MT", Font.PLAIN, 15));
		lblCurrentUser.setBounds(245, 14, 94, 14);
		contentPane.add(lblCurrentUser);
		
		JLabel lblLibraryId = new JLabel("Library ID");
		lblLibraryId.setFont(new Font("Tw Cen MT", Font.PLAIN, 15));
		lblLibraryId.setBounds(45, 253, 78, 14);
		contentPane.add(lblLibraryId);
		
		libIdTxtField = new JTextField();
		libIdTxtField.setBounds(147, 251, 347, 20);
		contentPane.add(libIdTxtField);
		libIdTxtField.setColumns(10);
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
