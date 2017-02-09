import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/** FileInfo
 * 
 *	This class takes an array found in a JSON file, and parses the elements into an ArrayList, based on the parsing rules found 
 *		in the HowToParseLibraryItems class.
 *	
 */
public class FileInfo {

	/**
	 * If the parser runs across a field called @SerializedName 
	 * Put the values called @SerializedName under the @Expose variable,
	 * 	 where: expose = ArrayList<HowToParseLibraryItems> libraryItems
	 */
	
	@SerializedName("library_items")
	@Expose
	private List<HowToParseLibraryItems> libraryItems = new ArrayList<HowToParseLibraryItems>();

	
	/**
	 * Simple getters and setters
	 * 
	 */
	public List<HowToParseLibraryItems> getLibraryItems() {
		return libraryItems;
	}

	public void setLibraryItems(List<HowToParseLibraryItems> libraryItems) {
		this.libraryItems = libraryItems;
	}

}