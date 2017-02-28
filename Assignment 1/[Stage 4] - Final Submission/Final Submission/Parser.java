package hoonigan;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import com.google.gson.Gson;
import com.google.gson.*;

/**Parser class
 * 
 * This class instantiates the Gson parser, and reads a JSON file that is passed in through Parser().
 * @author Hoonigan
 * 
 */

public class Parser {	
	/**Invariant Comments
	 * - gson is a JSON Parser, it is instantiated and initialized only once. It's value never changes after initial initialization.
	 * - br is a streaming text reader, which reads some character input stream. It reads a JSON file in this class, and its value never changes beyond the initial reading.
	 * - fInfo is an instance of a class that contains parsing rules on how to parse into an HashMap that will hold LibraryItems. Once parsing is completed, this variable is never changed or used again.
	 * - libraryMap is a HashMap that is instantiated with 10 buckets, and a a hash function containing 0.75F. libraryMap is filled with elements within parse(), and after it is filled, its value does not change.
	 */
	private Gson gson;
	private BufferedReader br;
	private FileInfo fInfo;
	private Map<String, Library_Items> libraryMap;	
	
	/**Parser(BufferedReader br)
	 * This is a constructor which initializes a GSON Parser, reads JSON file location, and parses the Library_Items found in the JSON file into a HashMap.
	 * @param br is the location of the passed in JSON FILE
	 */
	Parser(BufferedReader br){
		this.gson = new Gson();
		this.br = br;
		this.fInfo = gson.fromJson(br, FileInfo.class);
		this.libraryMap = new HashMap<String, Library_Items>(10, 0.75F);
	}
	
	/**getLibraryMap()
	 * Simple getter method, which returns a HashMap containing all Library_Items
	 * @return a HashMap containing all Library_Items
	 */
	public Map<String, Library_Items> getLibraryMap(){
		return this.libraryMap;
	}
	
	/**setLibraryMap()
	 * Simple setter method, which sets a HashMap containing Library_Items
	 * @param li - the HashMap being passed in
	 */
	public void setlibraryMap(Map<String, Library_Items> li){
		this.libraryMap = li;
	}
	
	/** parse()
	 * 	This method parses a json file, and returns an HashMap of Library_Items
	 */
	public void parse(){
		try{
			//If the array of libraryItems is not empty, for all elements in array, add all elements to HashMap
			if(fInfo != null){
				Library_Items temp = null;
				for (HowToParseLibraryItems lItems : fInfo.getLibraryItems()){
					if(lItems.getItemType().compareToIgnoreCase("DVD") == 0){
						temp = new DVD(lItems.getItemName(), lItems.getItemType().toUpperCase(), lItems.getItemId());
						libraryMap.put(lItems.getItemId(), temp);
					}
					else if(lItems.getItemType().compareToIgnoreCase("Magazine") == 0){
						temp = new Magazine(lItems.getItemName(), lItems.getItemType().toUpperCase(), lItems.getItemId());
						libraryMap.put(lItems.getItemId(), temp);
					}
					else if(lItems.getItemType().compareToIgnoreCase("Book") == 0){
						temp = new Book(lItems.getItemName(), lItems.getItemType().toUpperCase(), lItems.getItemId(), lItems.getItemAuthor());
						libraryMap.put(lItems.getItemId(), temp);
					}
					else if(lItems.getItemType().compareToIgnoreCase("CD") == 0){
						temp = new CD(lItems.getItemName(), lItems.getItemType().toUpperCase(), lItems.getItemId(), lItems.getItemArtist());
						libraryMap.put(lItems.getItemId(), temp);
					}
					else{
						//If lItems.getItemType() is NOT one of CD/DVD/Magazine/Book, do nothing 
					}
				}
			}else
				System.out.println("Your input JSON File is empty.");
		}catch(JsonParseException  e){ //Changed from the original FileNotFoundException... (May be an issue if File Not Found?)
			e.printStackTrace();
		}
		
		finally{
			if (br != null) {
				try{
					br.close();
				}
				catch (IOException e) {
					e.printStackTrace();
				}
			}	
		}
		
	}
	
	/**searchForItem(String searchId)
	 * This method searches a HashMap using a searchId, and returns a Library_Item if found
	 * @param searchId - Pass in the searching Id of the Library_Item
	 * @return a Library_Item object if found, NULL if not found
	 */
	public Library_Items searchForItem(String searchId){
		//Create Iterator with type Key, and Library_Items container to be returned
		Iterator<String> mySearchingItr = libraryMap.keySet().iterator();
		Library_Items li;
		Library_Items found = null;
		
		//Search through entire collection
		while(mySearchingItr.hasNext()){
			//load current library_item into a variable
			li = libraryMap.get(mySearchingItr.next()); 
			
			//check to see if you found the searchId, by checking if current items.getItemID == searchId
			if(li.getItem_id().compareTo(searchId) == 0)
				found = li;
		}

		//Return the found library_item [or null if nothing found]
		return found;
	}

}