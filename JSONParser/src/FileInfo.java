import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FileInfo {

	@SerializedName("library_items")
	@Expose
	private List<LibraryItems> libraryItems = new ArrayList<LibraryItems>();

	public List<LibraryItems> getLibraryItems() {
		return libraryItems;
	}

	public void setLibraryItems(List<LibraryItems> libraryItems) {
		this.libraryItems = libraryItems;
	}

}