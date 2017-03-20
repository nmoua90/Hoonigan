package com.implementation.hoonigan;

import com.gui.hoonigan.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;

import javax.swing.JFrame;
import java.util.List;
import java.util.ArrayList;

import com.gui.hoonigan.AdminFrame;

/**LibrarySearch class
 * 
 * This class takes in a HashMap containing all Library Items, and contains methods which perform various searches.
 * 
 * @author Hoonigan
 * 
 */
public class Library{
	/**Invariant Comments
	 * - libraryMap	is a HashMap containing Library_Items. It is initially set to null, and is instantiated within the constructor().
	 */
	private Map<String, Item> libraryMap;
	
	/**InitializeProgram()
	 * Default Constructor which sets fields
	 */
	public Library(Map<String, Item> libraryMap){
		this.libraryMap = libraryMap;
	}
	
	/**searchForItem(String searchId)
	 * This method searches a HashMap using a search field, and returns an array of matching Items found
	 * @param searchByWhatField - If 0, search by ID. If 1, search by Title. If 2, search by Item Type.
	 * @return foundItems, a List of Item objects found. If no matches, empty list is returned.
	 */
	public List<Item> searchForItem(int searchByWhatField, String searchId){
		//Create Iterator with type Key, and Item container to be returned
		Iterator<String> mySearchingItr = libraryMap.keySet().iterator();
		
		//Create container to hold one current item [once we start traversing]
		Item li;
		
		//Create ArrayList to store matching search elements
		List<Item> foundItems = new ArrayList<Item>();
		
		//Determine what you are searching for
		//If you are searching by ID
		if(searchByWhatField == 0){
			//Search through entire collection
			while(mySearchingItr.hasNext()){
				//load current library_item into a variable
				li = libraryMap.get(mySearchingItr.next()); 
				
				//check to see if you found the searchId, by checking if current items.getItemID == searchId
				if(li.getItem_id().compareToIgnoreCase(searchId) == 0)
					foundItems.add(li);
			}
		}
		//If you are searching by Title
		else if(searchByWhatField == 1){
			//Search through entire collection
			while(mySearchingItr.hasNext()){
				//load current library_item into a variable
				li = libraryMap.get(mySearchingItr.next()); 
				
				//check to see if you found the searchId, by checking if current items.getItemID == searchId
				if(li.getItem_name().compareToIgnoreCase(searchId) == 0)
					foundItems.add(li);
			}
		}
		//If you are searching by Item Type
		else{
			//Search through entire collection
			while(mySearchingItr.hasNext()){
				//load current library_item into a variable
				li = libraryMap.get(mySearchingItr.next()); 
				
				//check to see if you found the searchId, by checking if current items.getItemID == searchId
				if(li.getItem_type().compareToIgnoreCase(searchId) == 0)
					foundItems.add(li);
			}
		}
		
		//Return the found library_item [or null if nothing found]
		return foundItems;
	}
	

}