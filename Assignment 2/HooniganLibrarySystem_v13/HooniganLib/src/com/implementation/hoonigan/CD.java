package com.implementation.hoonigan;

import java.time.LocalDate;

/**CD class
 *  
 * A simple CD class--extended from Abstract Library_Items class
 * @author Hoonigan
 * 
 */

public class CD extends Item{
	/** Invariant Comments
	 *  - item_artist is initialized in the non-default CD Constructor. It's value can be changed, though changing it is not realistically supposed to be done.
	  */
	private String item_artist;
	
	/**CD()
	 * Default Constructor
	 */
	public CD(){
	}
	
	/**CD(String name, String type, String id, String artist)
	 * Constructor which instantiates a CD object
	 * @param name - name of CD
	 * @param type - type of item
	 * @param id - id of CD
	 * @param artist - artist of CD
	 */
	 public CD(String name, String type, String id, String artist, int libraryCode){
		item_name = name;
		item_type = type;
		item_id = id;
		item_artist = artist;
		checkedOut = false;
		returnDate = "NONE";
		libraryID = libraryCode;
	}
	 
	 public CD(String type, String id, String artist, String name, int libraryCode, boolean fromXML){
			item_name = name;
			item_type = type;
			item_id = id;
			item_artist = artist;
			checkedOut = false;
			returnDate = "NONE";
			libraryID = libraryCode;
		}
	
	/**getItem_artist()
	 * Typical getter
	 */
	public String getItem_artist() {
		return item_artist;
	}

	/**setItem_artist()
	 * Typical setter
	 */
	public void setItem_artist(String item_artist) {
		this.item_artist = item_artist;
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
	@Override
	public String toString(){
		String line = String.format("ID: %s | TITLE: %s | TYPE: %s | ARTIST: %s | STATUS: %s | LIBRARY ID: %d", 
				item_id, item_name, item_type, item_artist, status(), libraryID);
		return line;
	}
}