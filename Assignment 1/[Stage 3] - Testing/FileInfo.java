package hoonigan;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**FileInfo class
 * 
 * This class takes an array found in a JSON file, and parses the elements into an ArrayList, based on the parsing rules found in the HowToParseLibraryItems class.
 * @author Hoonigan
 * 
 */
public class FileInfo {
	/**Invariant comments
	 * - libraryItems is an ArrayList that parses based on the SerializedName 'library_items'. If parsed, the library_elements are stored in an ArrayList of type HowToParseLibraryItems. Once fully parsed, the number of elements in the Array never increase, and are never removed.
	 */
	@SerializedName("library_items")
	@Expose
	private List<HowToParseLibraryItems> libraryItems = new ArrayList<HowToParseLibraryItems>();

	/**getLibraryItems()
	 * Simple getter method
	 * @return libraryItems - An ArrayList holding Library Items
	 */
	public List<HowToParseLibraryItems> getLibraryItems() {
		return libraryItems;
	}

	/**setLibraryItems()
	 * Simple setter method
	 * @param libraryItems - The ArrayList holding Library Items that needs to be altered
	 */
	public void setLibraryItems(List<HowToParseLibraryItems> libraryItems) {
		this.libraryItems = libraryItems;
	}

}