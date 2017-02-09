import java.util.Map;
import java.util.Set;
import java.util.HashMap;
import java.util.Iterator;

public class LearnHashMap {
	public static void main(String[] args){
		
		/** Create Hashmap with two parameters<>
		 * 
		 * 		Parameter 1 [String]:
		 * 			Is the KEY VALUE
		 * 			KEY will have value of String [Because Library_Item.item_id = String]
		 * 
		 *		Parameter 2 [Library_Items]:
		 *			Is the DATA TYPE that the Hashmap will contain
		 *
		 * 	What are the other parameters? ---> (10, 0.75F)
		 * 
		 * 		Parameter 1 [10]:
		 * 			Is the number_of_buckets/size of the Hashmap
		 * 			So we have 10 buckets
		 * 		Parameter 2 [0.75F]
		 * 			0.75F = Float of 0.75
		 * 			Is the modulo_value that defines our Hash Function
		 * 				Such as: Key%Modulo_value
		 * 			So our Hash Method is == Key%0.75F
		 * 
		 */
		Map<String, Library_Items> thisIsAHashMapVariable = new HashMap<String, Library_Items>(10, 0.75F);
		
		//For demonstration, create a bunch of Library_Items
		Library_Items DVD_Object = new DVD("Pirates of the Carribean", "DVD", "az983");
		Library_Items CD_Object = new CD("Best of Lionel Richie", "CD", "898ds", "Lionel Richie");
		Library_Items magazine_Object = new Magazine("Vogue: Special Fat Guy Edition", "Magazine", "4534w");
		Library_Items book_Object = new Book("Spongebob SquarePaints Wild Adventure", "Book", "sa342", "HP Lovecraft");
		
		
		/**	Put all of our Library_Items OBJECTS into the Hashmap, using a KEY [String item_id]
		 * 		Parameter1 = Your Key
		 * 		Parameter2 = Data Type of Object being stored
		 */
		thisIsAHashMapVariable.put(DVD_Object.getItem_id(), DVD_Object);
		thisIsAHashMapVariable.put(CD_Object.getItem_id(), CD_Object);
		thisIsAHashMapVariable.put(magazine_Object.getItem_id(), magazine_Object);
		thisIsAHashMapVariable.put(book_Object.getItem_id(), book_Object);
		
		//How to traverse data through a SET
				/** Now that all of your Library_Items are in a HashMap, you need a way to traverse the elements of the HashMap
				 * 		One way to do this is by generating a SET from the Hashmap where:
				 * 			Parameter1 = Your Key
				 * 			Parameter2 = Data Type of Object being stored
				 * 			Note: 
				 * 				Notice that LHS = RHS when [basically]:
				 * 					Set = ourHashMapObject.return_a_set_of_this_hashMap();
				 */
				Set<Map.Entry<String, Library_Items>> ourEntireListOfEntries = thisIsAHashMapVariable.entrySet();
				
				/** Now we have all of our elements in a HashMap AND a SET
				 * 		Since we can't traverse a HashMap...
				 * 		Let's traverse a SET of our elements
				 */

				System.out.println("The following is a result of traversing with an enhanced for Loop using a SET.\n");
				
				for(Map.Entry<String, Library_Items> libraryElement : ourEntireListOfEntries){
					System.out.println("The Key Is: " + libraryElement.getKey());		//Print the key of element n
					Library_Items li = libraryElement.getValue();		//Create container to put an object into
					System.out.println(li.toString() + "\n");					//The object's toString method prints out metadata
				}
		
		System.out.println("\n\nThe following is a result of traversing with an iterator.\n");
				
		//How to traverse data through an Iterator [easier]
			/** You can also traverse the SET with an iterator 
			 * 	
			 */
			Iterator<String> itr = thisIsAHashMapVariable.keySet().iterator();
			while(itr.hasNext()){
				Library_Items li = thisIsAHashMapVariable.get(itr.next());
				System.out.println(li.toString());
			}
		
			
		//You can search for a key by using the following searchForItem(hashMap, id) method that I wrote below
		Library_Items mySearchResult = searchForItem(thisIsAHashMapVariable, "898ds");
		
		
		//Since "898ds" belongs to a CD ID for Lionel Richie, by searching with a valid key, we should get a valid result
		System.out.println("\n\nThese are your search results:\n");
		System.out.println(mySearchResult);
		
		//Now try searching for something invalid, which should get us a NULL value
		mySearchResult = searchForItem(thisIsAHashMapVariable, "839482394823");
		System.out.println(mySearchResult);
		
	}

	/** This method searches a HashMap and returns a Library_Item if found
	 * 
	 * @param myMap - Pass in your HashMap
	 * @param searchId - Pass in the searching Id of the Library_Item
	 * @return a Library_Item object if found, NULL if not found
	 */
	public static Library_Items searchForItem(Map<String, Library_Items> myMap, String searchId){
		//Create Iterator with type Key, and Library_Items container to be returned
		Iterator<String> mySearchingItr = myMap.keySet().iterator();
		Library_Items li;
		Library_Items found = null;
		
		//Search through entire collection
		while(mySearchingItr.hasNext()){
			//load current library_item into a variable
			li = myMap.get(mySearchingItr.next()); 
			
			//check to see if you found the searchId, by checking if current items.getItemID == searchId
			if(li.getItem_id().compareTo(searchId) == 0)
				found = li;
			
		}

		//Return the found library_item [or null if nothing found]
		return found;
	}
	
}
