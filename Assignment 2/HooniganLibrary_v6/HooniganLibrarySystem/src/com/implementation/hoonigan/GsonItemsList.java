package com.implementation.hoonigan;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**FileInfo class
 * 
 * This class takes the Gson Items found in a JSON file, and parses the elements into an ArrayList, based on the parsing rules found in the 
 * GsonItems class.
 * @author Hoonigan
 * 
 */
public class GsonItemsList{
	/**Invariant comments
	 * - libraryItems is an ArrayList that parses based on the SerializedName 'library_items'. If parsed, the library_elements are stored in an ArrayList of type HowToParseLibraryItems. Once fully parsed, the number of elements in the Array never increase, and are never removed.
	 */
	@SerializedName("library_items")
	@Expose
	private List<GsonItem> libraryItems = new ArrayList<GsonItem>();

	/**getAllGsonItems()
	 * Simple getter method
	 * @return libraryItems - An ArrayList holding Gson Items
	 */
	public List<GsonItem> getAllGsonItems() {
		return libraryItems;
	}

	/**setLibraryItems()
	 * Simple setter method
	 * @param libraryItems - The ArrayList holding Gson Items that needs to be altered
	 */
	public void setLibraryItems(List<GsonItem> libraryItems) {
		this.libraryItems = libraryItems;
	}

}