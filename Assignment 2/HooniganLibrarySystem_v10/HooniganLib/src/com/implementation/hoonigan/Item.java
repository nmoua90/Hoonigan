package com.implementation.hoonigan;

/**Library_Items abstract class
 * 
 * An abstract class, which extends into four Subclasses: Magazine, Book, CD, DVD
 * @author Hoonigan
 *
 */

abstract public class Item{
	protected String item_name;
	protected String item_type;
	protected String item_id;
	protected String returnDate;
	protected boolean checkedOut;
	protected int libraryID;
	
    /**Item()
     * Default constructor.
     */
    public Item(){
    }
    
    /**getLibraryID();
     * Typical getter
     * @return the ID of the Library this item is from
     */
    public int getLibraryID() {
		return libraryID;
	}


    /**setLibraryID(int LibraryID)
     * Typical setter
     * @param libraryID: The ID of the Library this item is from
     */
	public void setLibraryID(int libraryID) {
		this.libraryID = libraryID;
	}

	/**getItem_name()
	 * Typical getter
	 */
	public String getItem_name() {
		return item_name;
	}

	/**setItem_name()
	 * Typical setter
	 */
	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}

	/**getItem_type()
	 * Typical getter
	 */
	public String getItem_type() {
		return item_type;
	}

	/**setItem_type()
	 * Typical setter
	 */
	public void setItem_type(String item_type) {
		this.item_type = item_type;
	}

	/**getItem_id()
	 * Typical getter
	 */
	public String getItem_id() {
		return item_id;
	}

	/**setItem_id()
	 * Typical setter
	 */
	public void setItem_id(String item_id) {
		this.item_id = item_id;
	}

 	/**isCheckedOut()
 	 * This method returns a boolean value based on whether an item is checked out
 	 * @return a boolean based on whether an item is checked out
 	 */
 	public boolean isCheckedOut(){
 		return checkedOut;
 	}
 	
	/**checkIn()
	 * This method sets the checkedOut field to false.
	 */
	public void checkIn(){
		if(checkedOut == true){
			checkedOut = false;
			returnDate = "NONE";
		}
	}
	
	/**returnDate()
	 * This method returns a checkout date that corresponds to 21 days past the current day.
	 * @return a String which represents a date
	 */
	public String returnDate(){
		return returnDate.toString();
	}
	
	/**status()
	 * This method returns a String based on the availability of an item
	 * @return a string based on the availability of an item
	 */
	public String status() {
		if(this.isCheckedOut()) {
			return "Checked out, due: " + this.returnDate;
		}
		else {
			return "Available";
		}
	}
 	
	/**equals(Object obj)
	 * Compares one item with another, based on Item IDs.
	 */
	@Override
	public boolean equals(Object obj){
		if (obj == null) {
	        return false;
	    }
		
		Item paramItem = (Item) obj;
		
		if(paramItem.item_id.compareToIgnoreCase(item_id) == 0){
			return true;
		}
		
		return false;
	}
    /**checkOut()
     * Method returns a checkOut date for an item. Abstract implementation must be changed for each specific object/item.
     */
    public abstract void checkOut();
    
    /**isSisterLibraryItem()
     * This method returns a boolean value based on whether an item belongs to the sister library or not.
     * If libraryID == 0, the item is local. If libraryID != 0, the item came from another library.
     * @return a boolean based on whether an item belongs to the sister library or not.
     */
    public boolean isSisterLibraryItem(){
        if(libraryID != 0){
        	return true;
        }
        return false;
    }
    
    /**toString()
     * Method returns ???
     */
    @Override
    public abstract String toString();
}
