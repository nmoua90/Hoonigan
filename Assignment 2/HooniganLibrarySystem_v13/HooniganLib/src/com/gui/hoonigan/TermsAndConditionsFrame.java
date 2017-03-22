package com.gui.hoonigan;


import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JScrollPane;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ClassLoader;
import java.net.URL;

/**TermsAndConditionsFrame class
 * This class creates a frame, which displays the Terms and Conditions of the Hoonigan Library. The Terms and Conditions were modified from the following free source:
 * https://media.termsfeed.com/pdf/terms-and-conditions-template.pdf
 * @author Hoonigan
 *
 */
public class TermsAndConditionsFrame{
	private JFrame frame = new JFrame("Terms and Conditions");
	
	TermsAndConditionsFrame(){
		frame.setSize(650,400);
		frame.setResizable(false);
		
		//TEXT AREA
		JTextArea textArea = new JTextArea();
		frame.setLocationRelativeTo(textArea);
		textArea.setSize(400,400);    
		textArea.setLineWrap(true);
		textArea.setEditable(false);
		textArea.setVisible(true);
		
		//This string contains Terms and Conditions text. The String be saved as a text file, and then read every time instead of having this 
			//long String variable. The file will read if packaged with the .txt file prior to the /src directory, reading from a hard coded 
			//file source of "fileName.txt") such as:
				//BufferedReader reader = new BufferedReader(new FileReader("Terms.txt"));
		//However, the following approach will not be seen in the classpath if the files are exported to a JAR file. Therefore at this
			//current time, Terms and Conditions is set as this String variable.
		String termsAndConditions = "Terms and Conditions (\"Terms\")\nLast updated: 3/18/2017\n\nPlease read these Terms and Conditions\n"
				+ "(\"Terms\", \"Terms and Conditions\") carefully before registering as a new Hoonigan user.\n\nHOONIGAN LIBRARIES reserves "
				+ "all rights to restrict your membership as it sees fit. Your access to and use of the Service is conditioned on your "
				+ "acceptance of and compliance with these Terms. These Terms apply to all visitors, users and others who access the library. "
				+ "By accessing or using the library services you agree to be bound by these Terms. If you disagree with any part of the "
				+ "terms then you may not access our services.\n\nFEES:\n\nIf your check-in date exceeds its matching check-out date for our "
				+ "library items, we reserve the right to fine you a fee depending on the media charge and number of days late.\n\nHOONIGAN "
				+ "LIBRARIES shall not be responsible or liable, directly or indirectly, for any damage or loss caused or alleged to be "
				+ "caused by or in connection with use of or reliance at any of our media items. We reserve the right, at our sole "
				+ "discretion, to modify or replace these Terms at any time. If a revision is material we will try to provide at least 90 "
				+ "days' notice prior to any new terms taking effect. What constitutes a material change will be determined at our sole "
				+ "discretion.";
		
		/**You could read the Terms and Conditions from file as such:
		 * try{
			//The reader will not read the source file below if this Program is packaged within a JAR. Needs to be changed to the classpath.
			BufferedReader reader = new BufferedReader(new FileReader("Terms.txt"));
			String contentLine = reader.readLine();
			   while (contentLine != null) {
				  termsAndConditions += contentLine + "\n";
			      contentLine = reader.readLine();
			   }
			reader.close();
		}catch(IOException e){
			textArea.setText("READ ERROR: TERMS AND CONDITIONS NOT LOADED");
		}
		 */
		
		//Set the Terms and Conditions to screen
		textArea.setText(termsAndConditions);
		
	    JScrollPane scroll = new JScrollPane (textArea);
	    scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	    scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		
	    frame.add(scroll);
	    frame.setVisible(true);
	    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	
	public void setVisible(){
		frame.setVisible(true);
	}
	
}