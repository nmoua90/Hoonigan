package com.gui.hoonigan;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import com.google.common.collect.Multimap;
import com.implementation.hoonigan.*;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**CheckOutFrame class
 * This class creates a frame, which allows the user to search the Library for items, and check the item(s) out
 * @author Hoonigan
 *
 */
public class CheckOutFrame{
	private SingletonInformationExpert globalVariables = SingletonInformationExpert.getInstance();
	private JFrame frame = new JFrame("Library Search & Checkout");
	private JPanel contentPane;
	private JLabel lblSearchWhatType;
	private JLabel lblSearchById;
	private JTextField idSearchField;
	private JLabel lblSearchByTitle;
	private JTextField titleSearchField;
	private JButton btnSearchID;
	private JLabel lblCurrentUser;
	private JTextField currentUserTextField;
	private String currentUserName = globalVariables.getCurrentUser().getUserName();
	private DefaultTableModel tableModel;
	private String selectedComboBoxChoice = "BOOK";
	private List<String> userSelections = new ArrayList<String>();
	private JButton btnSearchByMedia;
	private String chosenItem;
	
	/**
	 * Create the frame.
	 */
	public CheckOutFrame(){
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setBounds(150, 400, 1000, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		frame.setContentPane(contentPane);
		contentPane.setLayout(null);
		
		if(globalVariables.getLibraryItemList().isEmpty()){
			JOptionPane.showMessageDialog(null, "The library database is empty! Search results will NOT return anything.", 
					"Warning!", JOptionPane.INFORMATION_MESSAGE);
		}
		
		String columns[] = {"ITEM ID", "LIBRARY ID", "ON-SHELF?", "TYPE", "TITLE", "ARTIST", "VOLUME"};
		
		//Set your default table, initially, you have only columns, and 0 rows
		tableModel = new DefaultTableModel(columns, 0){
			//Make all cells uneditable - this is optional
			@Override
		    public boolean isCellEditable(int row, int column) {
		       return false;
		    }
		};
				
		JComboBox mediaOptionsBox = new JComboBox();
		frame.setLocationRelativeTo(mediaOptionsBox);
		mediaOptionsBox.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				JComboBox cb = (JComboBox) e.getSource();
		        selectedComboBoxChoice = new String((String) cb.getSelectedItem());
				}
			});
		mediaOptionsBox.setModel(new DefaultComboBoxModel(new String[] {"BOOK", "MAGAZINE", "DVD", "CD"}));
		mediaOptionsBox.setFont(new Font("Tw Cen MT", Font.PLAIN, 13));
		mediaOptionsBox.setBounds(274, 56, 92, 20);
				
		contentPane.add(mediaOptionsBox);
				
		lblSearchWhatType = new JLabel("Search By What Type Of Media");
		lblSearchWhatType.setFont(new Font("Tw Cen MT", Font.PLAIN, 15));
		lblSearchWhatType.setBounds(25, 59, 206, 14);
		contentPane.add(lblSearchWhatType);
				
		lblSearchById = new JLabel("Search By ID");
		lblSearchById.setFont(new Font("Tw Cen MT", Font.PLAIN, 15));
		lblSearchById.setBounds(25, 100, 110, 14);
		contentPane.add(lblSearchById);
				
		idSearchField = new JTextField();
		idSearchField.setBounds(274, 98, 268, 20);
		contentPane.add(idSearchField);
		idSearchField.setColumns(10);
				
		lblSearchByTitle = new JLabel("Search By Title");
		lblSearchByTitle.setFont(new Font("Tw Cen MT", Font.PLAIN, 15));
		lblSearchByTitle.setBounds(25, 140, 110, 14);
		contentPane.add(lblSearchByTitle);
				
		titleSearchField = new JTextField();
		titleSearchField.setBounds(274, 138, 268, 20);
		contentPane.add(titleSearchField);
		titleSearchField.setColumns(10);
				
		btnSearchID = new JButton("SEARCH BY ITEM ID");
		btnSearchID.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				SearchResultsFrame searchByID = new SearchResultsFrame(idSearchField.getText());
				if(searchByID.displayIdSearchResults()){
					searchByID.setVisible();
				}
			}
		});
		btnSearchID.setFont(new Font("Tw Cen MT", Font.PLAIN, 15));
		btnSearchID.setBounds(600, 89, 260, 37);
		contentPane.add(btnSearchID);
				
		lblCurrentUser = new JLabel("Current User");
		lblCurrentUser.setFont(new Font("Tw Cen MT", Font.PLAIN, 15));
		lblCurrentUser.setBounds(25, 21, 110, 14);
		contentPane.add(lblCurrentUser);
				
		currentUserTextField = new JTextField();
		currentUserTextField.setEditable(false);
		currentUserTextField.setBounds(274, 19, 268, 20);
		contentPane.add(currentUserTextField);
		currentUserTextField.setColumns(10);
		currentUserTextField.setText(currentUserName);
		
		JButton btnSearchTitle = new JButton("SEARCH BY TITLE");
		btnSearchTitle.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				SearchResultsFrame searchByTitle = new SearchResultsFrame(titleSearchField.getText());
				if(searchByTitle.displayTitleSearchResults()){
					searchByTitle.setVisible();
				}else{
					JOptionPane.showMessageDialog(null, "No results found!", "Sorry!", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		btnSearchTitle.setFont(new Font("Tw Cen MT", Font.PLAIN, 15));
		btnSearchTitle.setBounds(600, 153, 260, 37);
		contentPane.add(btnSearchTitle);
		
		JButton btnDisplayAllItems = new JButton("DISPLAY ALL ITEMS");
		btnDisplayAllItems.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				SearchResultsFrame displayAllItems = new SearchResultsFrame("");
				if(displayAllItems.displayAllItems()){
					displayAllItems.setVisible();
				}else{
					JOptionPane.showMessageDialog(null, "No results found!", "Sorry!", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		btnDisplayAllItems.setFont(new Font("Tw Cen MT", Font.PLAIN, 15));
		btnDisplayAllItems.setBounds(147, 202, 260, 37);
		contentPane.add(btnDisplayAllItems);
		
		btnSearchByMedia = new JButton("SEARCH BY MEDIA TYPE");
		btnSearchByMedia.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				SearchResultsFrame searchByMedia = new SearchResultsFrame(selectedComboBoxChoice);
				if(searchByMedia.displayTypeResults()){
					searchByMedia.setVisible();
				}else{
					JOptionPane.showMessageDialog(null, "No results found!", "Sorry!", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		btnSearchByMedia.setFont(new Font("Tw Cen MT", Font.PLAIN, 15));
		btnSearchByMedia.setBounds(600, 23, 260, 37);
		contentPane.add(btnSearchByMedia);	
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