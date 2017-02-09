package hoonigan;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**HowToParseLibraryItems class
 * 
 * This class defines the Parsing Rules, which will determine how the GSON Parser parses the JSON file
 * @author Hoonigan
 * 
 */
public class HowToParseLibraryItems {
	/**Invariant Comments
	 * - itemName is a String that will be parsed based on the Parser recognizing the SerializedName "item_name".
	 * - itemType is a String that will be parsed based on the Parser recognizing the SerializedName "item_type".
	 * - itemId is a String that will be parsed based on the Parser recognizing the SerializedName "item_id".
	 * - itemArtist is a String that will be parsed based on the Parser recognizing the SerializedName "item_artist".
	 * - itemAuthor is a String that will be parsed based on the Parser recognizing the SerializedName "item_author".
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

	/**getItemName()
	 * Simple getter method
	 * @return the item name
	 */
	public String getItemName() {
		return itemName;
	}

	/**setItemName()
	 * Simple setter method
	 * @param itemName - the item name to be changed
	 */
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	/**getItemType()
	 * Simple getter method
	 * @return the item type
	 */
	public String getItemType() {
		return itemType;
	}

	/**setItemType()
	 * Simple setter method
	 * @param itemType - the item type to be changed
	 */
	public void setItemType(String itemType) {
		this.itemType = itemType;
	}

	/**getItemId()
	 * Simple getter method
	 * @return the item id
	 */
	public String getItemId() {
		return itemId;
	}

	/**setItemId()
	 * Simple setter method
	 * @param itemId - the item id to be changed
	 */
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	/**getItemArtist()
	 * Simple getter method
	 * @return the item artist
	 */
	public String getItemArtist() {
		return itemArtist;
	}

	/**setItemArtist()
	 * Simple setter method
	 * @param itemArtist - the item artist to be changed
	 */
	public void setItemArtist(String itemArtist) {
		this.itemArtist = itemArtist;
	}

	/**getItemAuthor()
	 * Simple getter method
	 * @return the item author
	 */
	public String getItemAuthor() {
		return itemAuthor;
	}

	/**setItemAuthor()
	 * Simple setter method
	 * @param itemAuthor - the item author to be changed
	 */
	public void setItemAuthor(String itemAuthor) {
		this.itemAuthor = itemAuthor;
	}

}