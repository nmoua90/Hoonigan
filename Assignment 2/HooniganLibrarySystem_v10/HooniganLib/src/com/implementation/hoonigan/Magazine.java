package com.implementation.hoonigan;

import java.time.LocalDate;

/**Magazine class
 * 
 * A simple Magazine class--extended from Abstract Library_Items class
 * @author Hoonigan
 *
 */

public class Magazine extends Item{
	private String volume;
	
	/**Magazine()
	 * Default Constructor
	 */
	public Magazine(){
	}
	
	/**Magazine(String name, String type, String id)
	 * Constructor which instantiates a Magazine object
	 * @param name - name of Magazine
	 * @param type - type of item
	 * @param id - id of Magazine
	 */
	public Magazine(String name, String type, String id, int libraryCode){
		libraryID = libraryCode;
		this.volume = "";
		item_name = name;
		item_type = type;
		item_id = id;
		checkedOut = false;
		returnDate = "NONE";
	}
	
	/**Magazine(String name, String type, String id)
	 * Constructor which instantiates a Magazine object
	 * @param name - name of Magazine
	 * @param type - type of item
	 * @param id - id of Magazine
	 * @param volume - volume of Magazine
	 */
	public Magazine(String name, String type, String id, String volume, int libraryCode){
		libraryID = libraryCode;
		this.volume = volume;
		item_name = name;
		item_type = type;
		item_id = id;
		checkedOut = false;
		returnDate = "NONE";
	}

	/**getVolume()
	 * Simple getter
	 * @return - volume number
	 */
	public String getVolume() {
		return volume;
	}
	
	/**setVolume()
	 * Simple setter
	 * @param volume - volume nuber
	 */
	public void setVolume(String volume) {
		this.volume = volume;
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
