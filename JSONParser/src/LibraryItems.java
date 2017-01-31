import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LibraryItems {

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
