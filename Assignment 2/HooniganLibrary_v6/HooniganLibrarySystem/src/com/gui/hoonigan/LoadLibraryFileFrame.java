package com.gui.hoonigan;
import com.implementation.hoonigan.*;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileFilter;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class LoadLibraryFileFrame{

	private JFrame frame = new JFrame("Load a mass Library JSON/XML file.");
	private JPanel contentPane;
	private JTextField libraryIDTextField;

	/**
	 * Create the frame.
	 */
	public LoadLibraryFileFrame() {
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		frame.setContentPane(contentPane);
		contentPane.setLayout(null);
		
		libraryIDTextField = new JTextField();
		libraryIDTextField.setBounds(148, 139, 276, 20);
		contentPane.add(libraryIDTextField);
		libraryIDTextField.setColumns(10);
		frame.setLocationRelativeTo(libraryIDTextField);
		
		JLabel lblEnterTheLibrary = new JLabel("Enter the Library ID");
		lblEnterTheLibrary.setFont(new Font("Tw Cen MT", Font.PLAIN, 15));
		lblEnterTheLibrary.setBounds(10, 141, 127, 14);
		contentPane.add(lblEnterTheLibrary);
		
		JLabel lblPrompt1 = new JLabel("In order to keep track of the origin of media items, enter the ");
		lblPrompt1.setFont(new Font("Tw Cen MT", Font.PLAIN, 15));
		lblPrompt1.setBounds(33, 22, 391, 34);
		contentPane.add(lblPrompt1);
		
		JLabel lblPrompt2 = new JLabel("Library ID from which the input data originates from.");
		lblPrompt2.setFont(new Font("Tw Cen MT", Font.PLAIN, 15));
		lblPrompt2.setBounds(71, 67, 328, 14);
		contentPane.add(lblPrompt2);
		
		JButton btnLoadFile = new JButton("Load JSON/XML File");
		btnLoadFile.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try{
					int libraryID = Integer.valueOf(libraryIDTextField.getText());
					initFileChooser(libraryID);
					closeFrame();
				}catch(NumberFormatException e){
					JOptionPane.showMessageDialog(null, "Your Library ID is invalid", "Error!", 
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnLoadFile.setFont(new Font("Tw Cen MT", Font.PLAIN, 15));
		btnLoadFile.setBounds(118, 199, 187, 23);
		contentPane.add(btnLoadFile);	
	}
	

	/**initFileChooser()
	 * Initialize a FileChooser to select an XML or JSON file
	 */
	public void initFileChooser(int libraryID){
		JFileChooser chooser = new JFileChooser();
		File workingDirectory = new File(System.getProperty("user.dir"));
		
		chooser.setCurrentDirectory(workingDirectory);
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		chooser.setAcceptAllFileFilterUsed(false);
		chooser.addChoosableFileFilter(new FileFilter()
		{
				//In the FileChooser window, specifically show files that end with '.xml' or '.json'
				@Override
				public boolean accept(File f) {
					return f.isDirectory() || f.getName().toLowerCase().endsWith(".xml") || f.getName().toLowerCase().endsWith(".json");
				}

				@Override
				public String getDescription() {
					return "XML or JSON Files";
				}
		});
		
		switch (chooser.showOpenDialog(null)){
			case JFileChooser.APPROVE_OPTION:
				try(BufferedReader br = new BufferedReader(new FileReader(chooser.getSelectedFile()))) {
					if(chooser.getSelectedFile().getName().toLowerCase().endsWith(".xml")){
						//parse with XML parser
						File xmlFile = chooser.getSelectedFile();
						XMLParser xParser = new XMLParser(libraryID);
						xParser.parseFile(xmlFile);
						JOptionPane.showMessageDialog(null, "XML file was loaded!", "Success!", JOptionPane.PLAIN_MESSAGE);
					}else{
						//parse with JSON parser
						JsonParser jParser = new JsonParser(br, libraryID);
						jParser.parse();
						JOptionPane.showMessageDialog(null, "JSON file was loaded!", "Success!", JOptionPane.PLAIN_MESSAGE);
					}
				}catch(IOException | NumberFormatException | ArrayIndexOutOfBoundsException e) {
					JOptionPane.showMessageDialog(null, "Failed to read file", "Error", JOptionPane.ERROR_MESSAGE);
				}
		}
	}//end of initFileChooser
	
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
