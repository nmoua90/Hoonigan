import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Added comments
 * @author Nhia
 *
 */
public class FileInfo {

	/**
	 * If the parser runs across @SerializedName (AKA: An item called 'library_items')
	 * Put the values called library_items in the variable under @Expose... where
	 * 		expose = ArrayList<LibraryItems> libraryItems
	 */
	
	@SerializedName("library_items")
	@Expose
	private List<LibraryItems> libraryItems = new ArrayList<LibraryItems>();

	
	/**
	 * Simple getters and setters
	 * 
	 */
	public List<LibraryItems> getLibraryItems() {
		return libraryItems;
	}

	public void setLibraryItems(List<LibraryItems> libraryItems) {
		this.libraryItems = libraryItems;
	}

}