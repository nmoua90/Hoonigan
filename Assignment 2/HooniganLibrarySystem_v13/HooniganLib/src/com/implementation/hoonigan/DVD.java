package com.implementation.hoonigan;

import java.time.LocalDate;

/**DVD class
 *  
 * A simple DVD class--extended from Abstract Library_Items class
 * @author Hoonigan
 * 
 */

public class DVD extends Item{
	/**DVD()
	 * Default Constructor
	 */
	public DVD(){
	}
	
	/**DVD(String name, String type, String id)
	 * Constructor which instantiates a DVD object
	 * @param name - name of DVD
	 * @param type - type of item
	 * @param id - id of DVD
	 */
	public DVD(String name, String type, String id, int libraryCode){
		item_name = name;
		item_type = type;
		item_id = id;
		checkedOut = false;
		returnDate = "NONE";
		libraryID = libraryCode;
	}
	
	public DVD(String type, String id, String name, int libraryCode, boolean fromXML){
		item_name = name;
		item_type = type;
		item_id = id;
		checkedOut = false;
		returnDate = "NONE";
		libraryID = libraryCode;
	}
	
	/**checkOut()
	 * This method sets the checkedOut field to true, resets the returnDate, and sets the returnDate as needed.
	 */
	public void checkOut(){
		if(checkedOut == false){
			//If you're going to check it out, calculate the current day
			returnDate = LocalDate.now().toString();
			checkedOut = true;
			
			//Added x days to the current day, to get the return date
			returnDate = LocalDate.parse(returnDate).plusDays(7).toString();
		}
	}
	
	/**toString()
	 * Typical overriden toString() method. Edited to fit with Text Based UI needs.
	 */
	public String toString(){
		String line = String.format("ID: %s | TITLE: %s | TYPE: %s | STATUS: %s | LIBRARY ID: %d", 
				item_id, item_name, item_type, status(), libraryID);
		return line;
	}
}
