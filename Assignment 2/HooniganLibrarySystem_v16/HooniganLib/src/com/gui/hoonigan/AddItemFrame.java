package com.gui.hoonigan;

import com.implementation.hoonigan.*;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**AddItemFrame
 * This class creates a frame, which allows an Administrator to manually add an item to the library.
 * @author Hoonigan
 *
 */
public class AddItemFrame{
	private SingletonInformationExpert globalVariables = SingletonInformationExpert.getInstance();
	private JFrame frame = new JFrame("Admin Window: Add Individual Item To The Library");
	private String selectedChoice = "BOOK";
	private JPanel contentPane;
	private JTextField idField;
	private JTextField artistField;
	private JTextField nameField;
	private JTextField volumeField;
	private JTextField currentUserField;
	private JTextField libIDfield;
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
		        selectedChoice = new String((String) cb.getSelectedItem());
			}
		});
		mediaOptionsBox.setModel(new DefaultComboBoxModel(new String[] {"BOOK", "MAGAZINE", "CD", "DVD"}));
		mediaOptionsBox.setBounds(392, 83, 102, 20);
		contentPane.add(mediaOptionsBox);
		
		JLabel lblPromptForBox = new JLabel("Media Type Of Item Being Added");
		lblPromptForBox.setFont(new Font("Tw Cen MT", Font.PLAIN, 15));
		lblPromptForBox.setBounds(45, 85, 211, 15);
		contentPane.add(lblPromptForBox);
		
		idField = new JTextField();
		idField.setBounds(147, 118, 347, 20);
		contentPane.add(idField);
		idField.setColumns(10);
		
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
		
		artistField = new JTextField();
		artistField.setBounds(146, 153, 348, 20);
		contentPane.add(artistField);
		artistField.setColumns(10);
		
		nameField = new JTextField();
		nameField.setBounds(146, 185, 348, 20);
		contentPane.add(nameField);
		nameField.setColumns(10);
		
		volumeField = new JTextField();
		volumeField.setBounds(146, 215, 348, 20);
		contentPane.add(volumeField);
		volumeField.setColumns(10);
		
		JButton btnAddNewItem = new JButton("Add New Item To Library");
		btnAddNewItem.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try{
					addItem();
					JOptionPane.showMessageDialog(null, "You added an item to the library!", 
							"Success!", JOptionPane.INFORMATION_MESSAGE);
				}catch(NumberFormatException ex){
					JOptionPane.showMessageDialog(null, "Enter only integers for the Library ID field!", 
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
		
		libIDfield = new JTextField();
		libIDfield.setBounds(147, 251, 347, 20);
		contentPane.add(libIDfield);
		libIDfield.setColumns(10);
	}
	
	/**
	 * 
	 */
	public void addItem(){
		String name = new String(nameField.getText());
		String id = new String(idField.getText());
		String artist = new String(artistField.getText());
		String volume = new String(volumeField.getText());
		int libraryCode = Integer.valueOf(libIDfield.getText());
		int duplicateItemID;
		
		//If this ID is already in our database
		if(globalVariables.getLibraryItemList().containsKey(id)){
			//Get the duplicate IDs of the items already in our database
			Collection<Item> duplicateList = globalVariables.getLibraryItemList().get(id);
					
			//For all the duplicate keys, put the keys in a List
			List<Integer> uniqueIdList = new ArrayList<Integer>();
			for(Item duplicateItem : duplicateList){
				duplicateItemID = duplicateItem.getUniqueQuantityID();
				uniqueIdList.add(duplicateItemID);
			}
					
			//Find the last unique key, and pre-increment it
			int lastUniqueID = Collections.max(uniqueIdList);
			int newUniqueID = ++lastUniqueID;
		
			//Add to map based on type
			if(selectedChoice.compareToIgnoreCase("BOOK") == 0){
				if(name.isEmpty() || id.isEmpty() || artist.isEmpty() || libIDfield.getText().isEmpty())
					missingFieldsMessage();
				else
					globalVariables.addToItemsHashMap(new BookItem(name, selectedChoice, id, artist, libraryCode, newUniqueID));
			}
			else if(selectedChoice.compareToIgnoreCase("CD") == 0){
				if(name.isEmpty() || id.isEmpty() || artist.isEmpty() || libIDfield.getText().isEmpty())
					missingFieldsMessage();
				else
					globalVariables.addToItemsHashMap(new CD(name, selectedChoice, id, artist, libraryCode, newUniqueID));
			}
			else if(selectedChoice.compareToIgnoreCase("MAGAZINE") == 0){
				if(name.isEmpty() || id.isEmpty() || libIDfield.getText().isEmpty())
					missingFieldsMessage();
				else
					globalVariables.addToItemsHashMap(new Magazine(name, selectedChoice, id, volume, libraryCode, newUniqueID));
			}
			else{
				if(name.isEmpty() || id.isEmpty() || libIDfield.getText().isEmpty())
					missingFieldsMessage();
				else
					globalVariables.addToItemsHashMap(new DVD(name, selectedChoice, id, libraryCode, newUniqueID));
			}
		
		//Else, if item is not in our list yet	
		}else{
			if(selectedChoice.compareToIgnoreCase("BOOK") == 0){
				if(name.isEmpty() || id.isEmpty() || artist.isEmpty() || libIDfield.getText().isEmpty())
					missingFieldsMessage();
				else
				globalVariables.addToItemsHashMap(new BookItem(name, selectedChoice, id, artist, libraryCode, 0));
			}
			else if(selectedChoice.compareToIgnoreCase("CD") == 0){
				if(name.isEmpty() || id.isEmpty() || artist.isEmpty() || libIDfield.getText().isEmpty())
					missingFieldsMessage();
				else
					globalVariables.addToItemsHashMap(new CD(name, selectedChoice, id, artist, libraryCode, 0));
			}
			else if(selectedChoice.compareToIgnoreCase("MAGAZINE") == 0){
				if(name.isEmpty() || id.isEmpty() || libIDfield.getText().isEmpty())
					missingFieldsMessage();
				else
					globalVariables.addToItemsHashMap(new Magazine(name, selectedChoice, id, volume, libraryCode, 0));
			}
			else{
				if(name.isEmpty() || id.isEmpty() || libIDfield.getText().isEmpty())
					missingFieldsMessage();
				else
					globalVariables.addToItemsHashMap(new DVD(name, selectedChoice, id, libraryCode, 0));
			}
		}
	}
	
	/**
	 * 
	 */
	public void missingFieldsMessage(){
		JOptionPane.showMessageDialog(null, "There are missing fields!", 
				"Error!", JOptionPane.ERROR_MESSAGE);
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
