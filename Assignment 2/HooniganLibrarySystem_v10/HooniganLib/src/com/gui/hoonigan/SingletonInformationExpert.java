package com.gui.hoonigan;


import com.implementation.hoonigan.*;
import java.util.Collection;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**SingletonInformationExpert
 * Singleton pattern & Information Expert pattern. Makes all fields Global with the usage of getters/setters. Fields need to be global so
 * all GUI frames can have underlying access to all relevant fields.
 * @author Hoonigan
 *
 */
public class SingletonInformationExpert {
	//At first, there is no instance of this class
	private static SingletonInformationExpert instance = null;
			
	//HashMap with all Libraries
	private static Map<Integer, Library> libraryList = new HashMap<Integer, Library>();
	
	//HashMap with all User accounts [userName, password]
	private static Map<String, Client> clientList = new HashMap<String, Client>();
	
	//HashMap with only Admin accounts [userName, password]
	private static Map<String, Client> adminList = new HashMap<String, Client>();		
		
	//HashMap for allLibraryItems [ID, Item]
	private static Multimap<String, Item> libraryItemList = ArrayListMultimap.create();
	
	//User Storage Variables
	private static Client restoreUserAccount;
	
	private static Client currentUser;

	/**SingletonInformationExpert()
	 * Default constructor is disabled. No allowance to call the Constructor in Singleton Pattern.
	 */
	private void SingletonInformationExpert(){
	}
		
	/**getInstance()
	 * Implements the Singleton pattern, by only allowing one instance of this class.
	 * @return one, and only one instance of this class.
	 */
	public static SingletonInformationExpert getInstance(){
		if(instance==null){
			instance = new SingletonInformationExpert();
		}
		return instance;
	}

	/**
	 * Create default Admin for system
	 */
	public static void instantiateDefaultAdmin(){
		Admin defaultAdmin = new Admin("admin", "admin", "admin", "admin".toCharArray(), "admin", "admin", "admin");
		clientList.put(defaultAdmin.getUserName(), defaultAdmin);
		adminList.put(defaultAdmin.getUserName(), defaultAdmin);
	}
	
	/**
	 * 
	 * @return
	 */
	public static Multimap<String, Item> getLibraryItemList() {
		return libraryItemList;
	}

	/**
	 * 
	 * @param libraryItemList
	 */
	public static void setLibraryItemList(Multimap<String, Item> libraryItemList) {
		SingletonInformationExpert.libraryItemList = libraryItemList;
	}

	/**
	 * 
	 * @param instance
	 */
	public static void setInstance(SingletonInformationExpert instance) {
		SingletonInformationExpert.instance = instance;
	}
			
	/**
	 * 
	 */
	public static void addToLibraryHashMap(Item libItem){
		libraryItemList.put(libItem.getItem_id(), libItem);
	}
	
	/**
	 * 
	 */
	public static void addToLibraryHashMap(Client client){
		clientList.put(client.getUserName(), client);
	}
	
	/**
	 * 
	 */
	public static void addToLibraryHashMap(Library library){
		libraryList.put(library.getLibraryID(), library);
	}

	/**
	 * 
	 * @return
	 */
	public static Map<Integer, Library> getLibraryList() {
		return libraryList;
	}

	/**
	 * 
	 * @param libraryList
	 */
	public static void setLibraryList(Map<Integer, Library> libraryList) {
		SingletonInformationExpert.libraryList = libraryList;
	}

	/**
	 * 
	 * @return
	 */
	public static Map<String, Client> getClientList() {
		return clientList;
	}

	/**
	 * 
	 * @param clientList
	 */
	public static void setClientList(Map<String, Client> clientList) {
		SingletonInformationExpert.clientList = clientList;
	}
	
	/**
	 * This method checks if a username is valid, and returns the Client if it is valid, and null otherwise.
	 * @param userName - the username of the client.
	 * @return the client's account if it exists, null otherwise.
	 */
	public Client getClientAccount(String userName){
		if(clientList.containsKey(userName)){
			return clientList.get(userName);
		}
		return null;
	}
	
	/**
	 * Checks if questionaire answer is correct.
	 * @return
	 */
	public boolean checkPassword(String username, char[] enteredPassword){
		if(userExists(username)){
			String enteredPW = new String(enteredPassword);
			String savedPW = new String(clientList.get(username).getPassword());
			
			if(enteredPW.compareTo(savedPW) == 0){
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * This method checks if a admin username is valid, and returns true if it is valid, and false otherwise.
	 * @return status of admin
	 */
	public boolean isAdmin(String userName){
		if(adminList.containsKey(userName)){
			return true;
		}
		return false;
	}
	
	/**
	 * 
	 * @return
	 */
	public static Client getRestoreUserAccount() {
		return restoreUserAccount;
	}

	/**
	 * 
	 * @param restoreUserAccount
	 */
	public static void setRestoreUserAccount(Client restoreUserAccount) {
		SingletonInformationExpert.restoreUserAccount = restoreUserAccount;
	}

	/**
	 * Checks if username is already in the system.
	 * @param userName
	 * @return
	 */
	public boolean userExists(String userName){
		if(clientList.containsKey(userName)){
			return true;
		}
		return false;
	}

	/**
	 * Search for a user account based on name
	 * @param lastName
	 * @return
	 */
	public List<Client> searchForUser(String lastName){
		List<Client> foundMembers = new ArrayList<Client>();
		
	    for(Entry<String, Client> entry: clientList.entrySet()) {
	    	if(entry.getValue().getLastName().contains(lastName)){
		        foundMembers.add(entry.getValue());
	    	}
	    }
		return foundMembers;
	}
	
	/**
	 * Search for a user account based on an address, and an ArrayList
	 * @param lastName
	 * @return
	 */
	public Client searchByAddress(String address, List<Client> myList){
		Client current;
		
		for(int i = 0; i<myList.size(); i++){
			current = myList.get(i);
			if(current.getStreetAddress().compareToIgnoreCase(address) == 0){
				return current;
			}
		}
		
		return null;
	}
	
	
	/**searchForItem(String searchId)
	 * This method searches a HashMap using a search field, and returns a List of matching Items found
	 * @param searchByWhatField - If 0, search by ID. If 1, search by Title. If 2, search by Item Type.
	 * @return foundItems, a List of Item objects found. If no matches, empty list is returned.
	 */
	public List<Item> searchForItem(int searchByWhatField, String searchId){
		//Create Iterator with Key values
		Iterator<String> mySearchingItr = libraryItemList.keySet().iterator();
		
		//Create ArrayList to store matching search elements
		List<Item> foundItems = new ArrayList<Item>();
		
		//Determine what you are searching for
		//If you are searching by ID
		if(searchByWhatField == 0){
			for(Item item : libraryItemList.values()) {
				//check to see if you found the searchId, by checking if current items.getItemID == searchId
				if(item.getItem_id().compareToIgnoreCase(searchId) == 0)
					foundItems.add(item);
			}
		}
		//If you are searching by Title
		else if(searchByWhatField == 1){
			for(Item item : libraryItemList.values()) {
				//check to see if you found the searchId, by checking if current items.getItemID == searchId
				if(item.getItem_name().compareToIgnoreCase(searchId) == 0)
					foundItems.add(item);
			}
		}
		//If you are searching by Item Type
		else if(searchByWhatField == 2){
			for(Item item : libraryItemList.values()) {
				//check to see if you found the searchId, by checking if current items.getItemID == searchId
				if(item.getItem_type().compareToIgnoreCase(searchId) == 0)
					foundItems.add(item);
			}
		//You want all the items of the library
		}else{
			for(Item item : libraryItemList.values()) {
					foundItems.add(item);
			}
		}
		
		//Return the found library_item [or null if nothing found]
		return foundItems;
	}

	/**
	 * Filters a List containing Items by the Item Type
	 * @return
	 */
	public List<Item> filterByType(String itemType, List<Item> myList){
		List<Item> filteredResults = new ArrayList<Item>();
		
		if(itemType.compareToIgnoreCase("BOOK") == 0){
			for(Item item : myList){
				if(item.getItem_type().compareTo("BOOK") == 0)
					filteredResults.add(item);
			}
		}
		else if(itemType.compareToIgnoreCase("MAGAZINE") == 0){
			for(Item item : myList){
				if(item.getItem_type().compareTo("MAGAZINE") == 0)
					filteredResults.add(item);
			}
		}
		else if(itemType.compareToIgnoreCase("CD") == 0){
			for(Item item : myList){
				if(item.getItem_type().compareTo("CD") == 0)
					filteredResults.add(item);
			}
		}	
		else if(itemType.compareToIgnoreCase("DVD") == 0){
			for(Item item : myList){
				if(item.getItem_type().compareTo("DVD") == 0)
					filteredResults.add(item);
			}
		}
		
		return filteredResults;
	}
	
	/**
	 * 
	 * @return
	 */
	public static Map<String, Client> getAdminList() {
		return adminList;
	}

	/**
	 * 
	 * @param adminList
	 */
	public static void setAdminList(Map<String, Client> adminList) {
		SingletonInformationExpert.adminList = adminList;
	}

	/**
	 * 
	 * @return
	 */
	public static Client getCurrentUser() {
		return currentUser;
	}

	/**
	 * 
	 * @param currentUser
	 */
	public static void setCurrentUser(Client currentUser) {
		SingletonInformationExpert.currentUser = currentUser;
	}
	
	
}
