package com.gui.hoonigan;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.google.common.collect.Multimap;
import com.implementation.hoonigan.Client;
import com.implementation.hoonigan.Item;

import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Map;
import javax.swing.JTextField;
import javax.swing.JLabel;

public class TestFrame extends JFrame {

	SingletonInformationExpert globalVariables = SingletonInformationExpert.getInstance();
	private JPanel contentPane;
	private JTextField intChoiceField;
	private JTextField searchField;
	private JTextField itemField;
	
	/**
	 * Create the frame.
	 */
	public TestFrame() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		getContentPane().setLayout(null);
		
		JButton btnDoSomething = new JButton("Print library items in the system\r\n");
		btnDoSomething.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Multimap<String, Item> map = globalVariables.getLibraryItemList();
				// Iterating over entire Mutlimap
				for(Item values : map.values()) {
					System.out.println(values);
				}
			}
		});
		contentPane.setLayout(null);
		btnDoSomething.setBounds(20, 23, 307, 50);
		getContentPane().add(btnDoSomething);
		
		JButton btnNewButton = new JButton("Print all users in the system");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Map<String, Client> map = globalVariables.getClientList();
		
				for (Map.Entry<String,Client> entry : map.entrySet()) {
				  String key = entry.getKey();
				  Client value = entry.getValue();
				  System.out.println("Username: " + key + "\nUser's last name is: " + value.getLastName());
				}


			}
		});
		btnNewButton.setBounds(20, 104, 307, 42);
		contentPane.add(btnNewButton);
		
		JButton Search = new JButton("Search for Items with search field");
		Search.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				String searchTerm = searchField.getText();
				String typeTerm = itemField.getText();
				int intSearchKey = Integer.valueOf(intChoiceField.getText());
				List<Item> myResults = globalVariables.searchForItem(intSearchKey, searchTerm);
				for(int i = 0; i<myResults.size(); i++){
					System.out.println(myResults.get(i));
				}
				//Return searchbyItems()
				//Search by 0 [id], with 
			}
		});
		Search.setBounds(20, 172, 193, 50);
		contentPane.add(Search);
		
		intChoiceField = new JTextField();
		intChoiceField.setBounds(20, 230, 86, 20);
		contentPane.add(intChoiceField);
		intChoiceField.setColumns(10);
		
		JLabel lblEnterTo = new JLabel("Enter 0 to search by ID, 1 to search by Title");
		lblEnterTo.setBounds(129, 233, 211, 14);
		contentPane.add(lblEnterTo);
		
		searchField = new JTextField();
		searchField.setBounds(337, 126, 86, 20);
		contentPane.add(searchField);
		searchField.setColumns(10);
		
		JLabel lblSearchField = new JLabel("Search Field");
		lblSearchField.setBounds(337, 101, 86, 14);
		contentPane.add(lblSearchField);
		
		itemField = new JTextField();
		itemField.setBounds(337, 38, 86, 20);
		contentPane.add(itemField);
		itemField.setColumns(10);
		
		JLabel lblTypeOfItem = new JLabel("Type of Item");
		lblTypeOfItem.setBounds(338, 11, 85, 14);
		contentPane.add(lblTypeOfItem);
		
		JButton btnSearchByType = new JButton("search by type");
		btnSearchByType.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				String typeTerm = itemField.getText();
				
				List<Item> myResults = globalVariables.searchForItem(2, typeTerm);
				for(int i = 0; i<myResults.size(); i++){
					System.out.println(myResults.get(i));
				}
			}
		});
		btnSearchByType.setBounds(248, 186, 175, 23);
		contentPane.add(btnSearchByType);
		
		
	}
}
