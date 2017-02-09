import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Added comments
 * 		Future Needs:
 * 			Need to add the various fields we have on our Subclasses, such as checkedIn
 * @author Nhia
 *
 */
public class LibraryItems {

	/**
	 * If you run across a name_of_field, put the field in name_of_variable
	 * 		name_of_field		name_of_variable
	 * 		-------------		----------------
	 * 		item_name			itemName
	 * 		item_type			itemType
	 * 		item_id				itemId
	 * 		item_artist			itemArtist
	 * 		item_author			itemAuthor
	 */
	
	@SerializedName("item_name")
	@Expose
	private String itemName;
	@SerializedName("item_type")
	@Expose
	private String itemType;
	@SerializedName("item_id")
	@Expose
	private String itemId;
	@SerializedName("item_artist")
	@Expose
	private String itemArtist;
	@SerializedName("item_author")
	@Expose
	private String itemAuthor;

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getItemType() {
		return itemType;
	}

	public void setItemType(String itemType) {
		this.itemType = itemType;
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public String getItemArtist() {
		return itemArtist;
	}

	public void setItemArtist(String itemArtist) {
		this.itemArtist = itemArtist;
	}

	public String getItemAuthor() {
		return itemAuthor;
	}

	public void setItemAuthor(String itemAuthor) {
		this.itemAuthor = itemAuthor;
	}

}
