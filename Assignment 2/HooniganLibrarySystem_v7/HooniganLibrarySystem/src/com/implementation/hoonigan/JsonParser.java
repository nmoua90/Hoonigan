package com.implementation.hoonigan;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import com.google.gson.Gson;
import com.google.gson.*;

/**Parser class
 * 
 * This class reads a JSON file, and parses it with GSON. The parsed output is written to a HashMap containing <ID, Item>.
 * @author Hoonigan
 * 
 */

public class JsonParser {	
	/**Invariant Comments
	 * - gson is a JSON Parser, it is instantiated and initialized only once. It's value never changes after initial initialization.
	 * - br is a streaming text reader, which reads some character input stream. It reads a JSON file in this class, and its value never changes beyond the initial reading.
	 * - gsonItemsArray is an instance of a class that contains parsing rules on how to parse into an HashMap that will hold LibraryItems. Once parsing is completed, this variable is never changed or used again.
	 * - libraryMap is a HashMap that is instantiated with 10 buckets, and a a hash function containing 0.75F. libraryMap is filled with elements within parse(), and after it is filled, its value does not change.
	 */
	private Gson gson;
	private BufferedReader br;
	private GsonItemsList gsonItemsArray;
	private Map<String, Item> libraryMap;
	private int libraryID;
	
	/**Parser(BufferedReader br)
	 * This is a constructor which initializes a GSON Parser, reads JSON file location, and parses the Item found in the JSON file into a HashMap.
	 * @param br is the location of the passed in JSON FILE
	 */
	public JsonParser(BufferedReader br, int libraryID){
		this.gson = new Gson();
		this.br = br;
		this.gsonItemsArray = gson.fromJson(br, GsonItemsList.class);
		this.libraryMap = new HashMap<String, Item>(10, 0.75F);
		this.libraryID = libraryID;
	}
	
	/** parse()
	 * 	This method parses a json file, into a HashMap of Item(s)
	 */
	public void parse(){
		try{
			//If the ArrayList from GsonItemsList is not empty, for all elements in array, put all elements to HashMap
			if(gsonItemsArray != null){
				Item temp = null;
				for (GsonItem item : gsonItemsArray.getAllGsonItems()){
					String deletedErroneousSpaces = item.getItemType().toUpperCase();
					deletedErroneousSpaces = deletedErroneousSpaces.replaceAll("\\s", "");
					
					if(deletedErroneousSpaces.compareToIgnoreCase("DVD") == 0){
						temp = new DVD(item.getItemName(), item.getItemType().toUpperCase(), item.getItemId(), libraryID);
						libraryMap.put(item.getItemId(), temp);
					}
					else if(deletedErroneousSpaces.compareToIgnoreCase("Magazine") == 0){
						temp = new Magazine(item.getItemName(), item.getItemType().toUpperCase(), item.getItemId(), libraryID);
						libraryMap.put(item.getItemId(), temp);
					}
					else if(deletedErroneousSpaces.compareToIgnoreCase("Book") == 0){
						temp = new BookItem(item.getItemName(), item.getItemType().toUpperCase(), item.getItemId(), item.getItemAuthor(), libraryID);
						libraryMap.put(item.getItemId(), temp);
					}
					else if(deletedErroneousSpaces.compareToIgnoreCase("CD") == 0){
						temp = new CD(item.getItemName(), item.getItemType().toUpperCase(), item.getItemId(), item.getItemArtist(), libraryID);
						libraryMap.put(item.getItemId(), temp);
					}
					else{
						//If item.getItemType() is NOT one of CD/DVD/Magazine/Book, store these values in the error log
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
	
	/**getLibraryMap()
	 * Simple getter method, which returns a HashMap containing all Items
	 * @return a HashMap containing all Items
	 */
	public Map<String, Item> getLibraryMap(){
		return this.libraryMap;
	}
	
	/**setLibraryMap()
	 * Simple setter method, which sets a HashMap containing Items
	 * @param li - the HashMap being passed in
	 */
	public void setlibraryMap(Map<String, Item> li){
		this.libraryMap = li;
	}
	
}