package com.implementation.hoonigan;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import com.gui.hoonigan.*;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

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
	private int libraryID;
	private int duplicateItemID;
	private SingletonInformationExpert globalVariables = SingletonInformationExpert.getInstance();
	
	/**Parser(BufferedReader br)
	 * This is a constructor which initializes a GSON Parser, reads JSON file location, and parses the Item found in the JSON file into a HashMap.
	 * @param br is the location of the passed in JSON FILE
	 */
	public JsonParser(BufferedReader br, int libraryID){
		this.gson = new Gson();
		this.br = br;
		this.gsonItemsArray = gson.fromJson(br, GsonItemsList.class);
		this.libraryID = libraryID;
	}
	
	/** parse()
	 * 	This method parses a json file, into a HashMap of Item(s)
	 */
	public int parse(){
		int ans = 0;
		try{
			//If the ArrayList from GsonItemsList is not empty, for all elements in array, put all elements to HashMap
			if(gsonItemsArray != null){
				for (GsonItem item : gsonItemsArray.getAllGsonItems()){
					String typeWhiteSpacesFiltered = item.getItemType().toUpperCase();
					typeWhiteSpacesFiltered = typeWhiteSpacesFiltered.replaceAll("\\s", "");
					
					//Turn the GsonItem -> Item, and put them in the global map, as well as the library specific map
					if(typeWhiteSpacesFiltered.compareToIgnoreCase("DVD") == 0){
						//Add to global map of items
						addItemToMultiMap("DVD", item);
					}
					else if(typeWhiteSpacesFiltered.compareToIgnoreCase("Magazine") == 0){
						addItemToMultiMap("Magazine", item);
					}
					else if(typeWhiteSpacesFiltered.compareToIgnoreCase("Book") == 0){
						addItemToMultiMap("Book", item);
					}
					else if(typeWhiteSpacesFiltered.compareToIgnoreCase("CD") == 0){
						addItemToMultiMap("CD", item);
					}
					else{
						Item thisItem;
						thisItem = new UndefinedItem(item.getItemName(), item.getItemType().toUpperCase(), item.getItemId(), libraryID);
						globalVariables.addToErrorMap(thisItem);
						ans = -2;
					}
				}
			}else{//Your JSON file is empty
				return -1;
			}
		}catch(JsonParseException  e){
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
		return ans;
	}
	
	/**addItemToMultiMap(GsonItem item)
	 * This method converts a GsonItem into an Item, 
	 * @param item - a GsonItem [defined as one item block from the json file] that is being passed in straight from I/O
	 * @param itemType - the Item Type <BookItem, CD, DVD, Magazine> of the GsonItem being passed in
	 */
	public void addItemToMultiMap(String itemType, GsonItem item){
		Item thisItem;
		
		//If this ID is already in our database
		if(globalVariables.getLibraryItemList().containsKey(item.getItemId())){
			//Get the duplicate IDs of the items already in our database
			Collection<Item> duplicateList = globalVariables.getLibraryItemList().get(item.getItemId());
			
			//For all the duplicate keys, put the keys in a List
			List<Integer> uniqueIdList = new ArrayList<Integer>();
			for(Item duplicateItem : duplicateList){
				duplicateItemID = duplicateItem.getUniqueQuantityID();
				uniqueIdList.add(duplicateItemID);
			}
			
			//Find the last unique key, and pre-increment it
			int lastUniqueID = Collections.max(uniqueIdList);
			int newUniqueID = ++lastUniqueID;
			
			//Add this ID to the MultiMap based on Type
			if(itemType.compareToIgnoreCase("DVD") == 0){
				thisItem = new DVD(item.getItemName(), item.getItemType().toUpperCase(), item.getItemId(), libraryID, newUniqueID);
				globalVariables.addToItemsHashMap(thisItem);
			}
			else if(itemType.compareToIgnoreCase("Magazine") == 0){
				thisItem = new Magazine(item.getItemName(), item.getItemType().toUpperCase(), item.getItemId(), libraryID, newUniqueID);
				globalVariables.addToItemsHashMap(thisItem);
			}
			else if(itemType.compareToIgnoreCase("Book") == 0){
				thisItem = new BookItem(item.getItemName(), item.getItemType().toUpperCase(), item.getItemId(), 
						item.getItemAuthor(), libraryID, newUniqueID);
				globalVariables.addToItemsHashMap(thisItem);
			}
			else if(itemType.compareToIgnoreCase("CD") == 0){
				thisItem = new CD(item.getItemName(), item.getItemType().toUpperCase(), item.getItemId(), item.getItemArtist(), 
						libraryID, newUniqueID);
				globalVariables.addToItemsHashMap(thisItem);
			}
			
		//If this ID is not in our database
		}else{
			//Add this ID to the MultiMap
			//Add this ID to the MultiMap based on Type
			if(itemType.compareToIgnoreCase("DVD") == 0){
				thisItem = new DVD(item.getItemName(), item.getItemType().toUpperCase(), item.getItemId(), libraryID, 0);
				globalVariables.addToItemsHashMap(thisItem);
			}
			else if(itemType.compareToIgnoreCase("Magazine") == 0){
				thisItem = new Magazine(item.getItemName(), item.getItemType().toUpperCase(), item.getItemId(), libraryID, 0);
				globalVariables.addToItemsHashMap(thisItem);
			}
			else if(itemType.compareToIgnoreCase("Book") == 0){
				thisItem = new BookItem(item.getItemName(), item.getItemType().toUpperCase(), item.getItemId(), 
						item.getItemAuthor(), libraryID, 0);
				globalVariables.addToItemsHashMap(thisItem);
			}
			else{
				thisItem = new CD(item.getItemName(), item.getItemType().toUpperCase(), item.getItemId(), item.getItemArtist(), 
						libraryID, 0);
				globalVariables.addToItemsHashMap(thisItem);
			}
			
			//If the library is already in the system, add the item to it's map
			if(globalVariables.getLibraryList().containsKey(libraryID)){
				Library foundLibrary = globalVariables.getLibraryList().get(libraryID);
				foundLibrary.addToLibraryCatalog(thisItem);
			}
			//Else, put the library into the system, and then add the item to it's map
			else{
				Library newLibrary = new Library(libraryID);
				newLibrary.addToLibraryCatalog(thisItem);
			}
		}
	}
	
}