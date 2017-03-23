package com.gui.hoonigan;


import com.google.gson.*;
import com.implementation.hoonigan.*;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileFilter;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**AdminFrame class
 * This class displays a frame, offering multiple admin commands, such as saving the state of the library, reading the error log, manually adding new library items, and
 * reading a XML/JSON file to mass-load the library archives. Class is accessible only to Library Administrators
 * @author Hoonigan
 *
 */
public class AdminFrame{
	private SingletonInformationExpert globalVariables = SingletonInformationExpert.getInstance();
	private JFrame frame = new JFrame("Administrative Options");
	private JPanel contentPane;
	private JTextField currentUserField;
	private Client currentUser = globalVariables.getCurrentUser();
	private String currentUserName = globalVariables.getCurrentUser().getUserName();
	private BufferedReader reader;

	/**
	 * Create the frame.
	 */
	public AdminFrame() {	
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 600, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		frame.setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnManuallyAddNew = new JButton("Manually Add New Item");
		btnManuallyAddNew.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				AddItemFrame addItem = new AddItemFrame();
				addItem.setVisible();
			}
		});
		btnManuallyAddNew.setFont(new Font("Tw Cen MT", Font.PLAIN, 13));
		btnManuallyAddNew.setBounds(322, 185, 234, 46);
		contentPane.add(btnManuallyAddNew);
		
		JButton btnAddJsonxmlLibrary = new JButton("Add JSON/XML Library Source File");
		btnAddJsonxmlLibrary.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				LoadLibraryFileFrame fileChooserScreen = new LoadLibraryFileFrame();
				fileChooserScreen.setVisible();
			}
		});
		btnAddJsonxmlLibrary.setFont(new Font("Tw Cen MT", Font.PLAIN, 13));
		btnAddJsonxmlLibrary.setBounds(24, 185, 234, 46);
		contentPane.add(btnAddJsonxmlLibrary);
		
		JButton btnSaveStateLibrary = new JButton("Save State Of The Library");
		btnSaveStateLibrary.setFont(new Font("Tw Cen MT", Font.PLAIN, 13));
		btnSaveStateLibrary.setBounds(322, 102, 238, 41);
		contentPane.add(btnSaveStateLibrary);
		
		JButton btnLoadPrevLibraryState = new JButton("Load a Previous State of The Library");
		btnLoadPrevLibraryState.setFont(new Font("Tw Cen MT", Font.PLAIN, 13));
		btnLoadPrevLibraryState.setBounds(24, 99, 241, 46);
		contentPane.add(btnLoadPrevLibraryState);
		
		JButton btnDeleteMember = new JButton("Delete Library User");
		btnDeleteMember.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				DeleteUserFrame delete = new DeleteUserFrame();
				delete.setVisible(true);
			}
		});
		btnDeleteMember.setFont(new Font("Tw Cen MT", Font.PLAIN, 13));
		btnDeleteMember.setBounds(24, 371, 234, 46);
		contentPane.add(btnDeleteMember);
		
		JButton btnGoBack = new JButton("Log Out");
		btnGoBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				OpeningFrame startOver = new OpeningFrame();
				startOver.setVisible();
				closeFrame();
			}
		});
		btnGoBack.setFont(new Font("Tw Cen MT", Font.PLAIN, 13));
		btnGoBack.setBounds(322, 371, 234, 46);
		contentPane.add(btnGoBack);
		
		JButton btnCheckIn = new JButton("Check In Books");
		btnCheckIn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				CheckInFrame bookIn = new CheckInFrame();
				bookIn.setVisible();
			}
		});
		btnCheckIn.setFont(new Font("Tw Cen MT", Font.PLAIN, 13));
		btnCheckIn.setBounds(24, 278, 234, 46);
		contentPane.add(btnCheckIn);
		
		JButton btnCheckOut = new JButton("Check Out Books");
		btnCheckOut.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				CheckOutFrame bookOut = new CheckOutFrame();
				bookOut.setVisible();
			}
		});
		btnCheckOut.setFont(new Font("Tw Cen MT", Font.PLAIN, 13));
		btnCheckOut.setBounds(318, 278, 238, 46);
		contentPane.add(btnCheckOut);
		
		currentUserField = new JTextField();
		currentUserField.setEditable(false);
		currentUserField.setBounds(24, 36, 532, 20);
		contentPane.add(currentUserField);
		currentUserField.setColumns(10);
		currentUserField.setText(currentUserName);
		frame.setLocationRelativeTo(currentUserField);
		
		JLabel lblCurrentUser = new JLabel("Current User");
		lblCurrentUser.setFont(new Font("Tw Cen MT", Font.PLAIN, 15));
		lblCurrentUser.setBounds(250, 11, 78, 14);
		contentPane.add(lblCurrentUser);
		
		JButton btnTestButton = new JButton("Test Button");
		btnTestButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				TestFrame x = new TestFrame();
				x.setVisible(true);
			}
		});
		btnTestButton.setBounds(164, 65, 291, 23);
		contentPane.add(btnTestButton);
		
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
	public void addFilepathToInformationExpert(BufferedReader reader){
		
	}
	
	/**
	 * 
	 */
	public void setVisible(){
		frame.setVisible(true);
	}
}