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
	
	/**
	 * Create the frame.
	 */
	public TestFrame() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 700, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		getContentPane().setLayout(null);
		
		JButton btnDoSomething = new JButton("Print all library items in the system\r\n");
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
		
		JButton btnNewButton_1 = new JButton("Print all read failed Items");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Multimap<String, Item> map = globalVariables.getErrorList();
				
				// Iterating over entire Mutlimap
				for(Item values : map.values()) {
					  System.out.println(values);
				}
			}
		});
		btnNewButton_1.setBounds(20, 173, 307, 50);
		contentPane.add(btnNewButton_1);
		
		
	}
}
