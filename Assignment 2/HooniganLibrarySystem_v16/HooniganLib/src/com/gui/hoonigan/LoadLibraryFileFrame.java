package com.gui.hoonigan;

import java.util.Collection;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.google.gson.JsonSyntaxException;
import com.google.gson.stream.MalformedJsonException;
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
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**LoadLibraryFileFrame
 * This class creates the frame, where an ADMIN can load a mass Library file [in JSON/XML format].
 * @author Hoonigan
 *
 */
public class LoadLibraryFileFrame{
	private SingletonInformationExpert globalVariables = SingletonInformationExpert.getInstance();
	private JFrame frame = new JFrame("Load a mass Library JSON/XML file.");
	private JPanel contentPane;
	private JTextField libraryIDTextField;
	private Client currentUser = globalVariables.getCurrentUser();
	private String currentUserName = globalVariables.getCurrentUser().getUserName();

	/**LoadLibraryFileFrame()
	 * Constructor. Creates the frame.
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
						callXMLParser(chooser, libraryID);
						
					}else{
						callJsonParser(br, libraryID);
					}
				}catch(IOException | NumberFormatException | ArrayIndexOutOfBoundsException | 
						JsonSyntaxException e) {
					JOptionPane.showMessageDialog(null, "Failed to read file", "Error", JOptionPane.ERROR_MESSAGE);
				}
		}
	}//end of initFileChooser
	

	/**callXMLParser(JFileChooser chooser, int libraryID)
	 * Calls the XML Parser and parses a XML file. Updates Global variable HashMap containing Library Items with recently parsed items.
	 * @param chooser - JFileChooser reader passed in from method that instantiates FileChooser
	 * @param libraryID - the libraryID the batch of JSON files are coming from
	 */
	private void callXMLParser(JFileChooser chooser, int libraryID){
		//You have an XML file
		File xmlFile = chooser.getSelectedFile();
		
		//Send the XML file to the class with the Parser
		XMLParser xParser = new XMLParser(xmlFile, libraryID);
		
		//Parse the XML File
		int errorStatus = xParser.parse();
			
		//Display whether Loading of XML was a success
		displayErrorLoadingMessage(errorStatus);
	}
	
	/**callJsonParser(BufferedReader br, int libraryID)
	 * Calls the JSON Parser and parses a JSON file. Updates Global variable HashMap containing Library Items with recently parsed items.
	 * @param br - Buffered reader passed in from JFileChooser
	 * @param libraryID - the libraryID the batch of JSON files are coming from
	 */
	private void callJsonParser(BufferedReader br, int libraryID) {
		//Create JSON parser class
		JsonParser jParser = new JsonParser(br, libraryID);
		
		//Parse Json file
		int errorStatus = jParser.parse();
				
		//Display whether Loading of XML was a success
		displayErrorLoadingMessage(errorStatus);
	}

	
	/**displayErrorLoadingMessage(int error)
	 * Displays a pop-up, either telling the user of a successive file load, or a failure of file load.
	 * @param error - the code responding to successful load. 0 for successive load, -1 for failed load.
	 */
	public void displayErrorLoadingMessage(int error){
		if(error == 0){
			JOptionPane.showMessageDialog(null, "File was loaded!", "Success!", JOptionPane.PLAIN_MESSAGE);
		}else if(error == -1){
			JOptionPane.showMessageDialog(null, "File did not read properly. Check if file format is valid", 
					"Failure!", JOptionPane.WARNING_MESSAGE);
		}else{
			JOptionPane.showMessageDialog(null, "Some items in the file were read, but others are misformatted.", 
					"Failure!", JOptionPane.WARNING_MESSAGE);
		}
	}

	/**closeFrame()
	 * Close the frame.
	 */
	public void closeFrame(){
		frame.setVisible(false);
		frame.dispose();
	}
	
	/**setvisible()
	 * Make the frame visible.
	 */
	public void setVisible(){
		frame.setVisible(true);
	}
		
}
