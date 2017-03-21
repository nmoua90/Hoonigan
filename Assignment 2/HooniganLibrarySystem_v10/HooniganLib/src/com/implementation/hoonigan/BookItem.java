package com.implementation.hoonigan;

import java.time.LocalDate;

/**Books class
 *  
 * A simple Book class--extended from Abstract Library_Items class
 * @author Hoonigan
 * 
 */
public class BookItem extends Item{
	/** Invariant Comments
	 *  - item_author is initialized in the non-default CD Constructor. It's value can be changed, though changing it is not realistically supposed to be done.
	 */ 
	private String item_author;
	
	public BookItem(){
	}
	
	/**Book(String name, String type, String id, String author)
	 * Constructor which instantiates a Book object
	 * @param name - name of Book
	 * @param type - type of item
	 * @param id - id of Book
	 * @param libraryCode - the libraryID associated with this item
	 * @param author - artist of Book
	 */
	public BookItem(String name, String type, String id, String author, int libraryCode){
		libraryID = libraryCode;
		item_name = name;
		item_type = type;
		item_id = id;
		item_author = author;
		checkedOut = false;
		returnDate = "NONE";
	}

	/**getItem_author()
	 * Typical getter
	 */
	public String getItem_author() {
		return item_author;
	}

	/**setItem_author()
	 * Typical setter
	 */
	public void setItem_author(String item_author) {
		this.item_author = item_author;
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
			returnDate = LocalDate.parse(returnDate).plusDays(21).toString();
		}
	}
	
	/**toString()
	 * Typical overriden toString() method. Edited to fit with Text Based UI needs.
	 */
	public String toString(){
		String line = String.format("ID: %s | TITLE: %s | TYPE: %s | AUTHOR: %s | STATUS: %s | | LIBRARY ID: %d", 
				item_id, item_name, item_type, item_author, status(), libraryID);
		return line;
	}
}
