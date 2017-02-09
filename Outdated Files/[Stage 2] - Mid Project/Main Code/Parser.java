import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.*;

/** Parser
 * 
 * 	This class instantiates the Gson parser, and reads a JSON file that is passed in through Parser(). It has a method
 * 		which returns an ArrayList<Library_Items> of all parsed elements.
 *
 */

//need to change after we decide on hashmap or not
public class Parser {	
	/** Declaring fields
	 * 		- Gson is a Json object
	 *		- BufferedReader is a Class that reads some character input stream
	 *		- FileInfo is a class that contains an ArrayList that holds LibraryItems
	 */
	private Gson gson;
	private BufferedReader br;
	private FileInfo fInfo;
	private List<Library_Items> libraryList;	//replace with HashMap (?)
	
	/**
	 * 
	 * @param br is the location of the passed in JSON FILE
	 */
	Parser(BufferedReader br){
		this.gson = new Gson();
		this.br = br;
		this.fInfo = gson.fromJson(br, FileInfo.class);
		this.libraryList = new ArrayList<Library_Items>();
	}
	
	//Getters and Setters for Data Structure that holds Sub-Classes of Library_Items
	public List<Library_Items> getLibraryList(){
		return this.libraryList;
	}
	
	public void setLibraryList(List<Library_Items> li){
		this.libraryList = li;
	}
	
	/** parse()
	 * 		Parses a json file, and returns an ArrayList of Library_Items
	 */
	public List<Library_Items> parse(){	
		/** Try the following:
		 * 		- Our BufferedReader reads the Json file at FileReader(Location) 
		 * 		- Our FileInfo array is == (whatever_the_reader_read, parsed_by_the_FileInfo_rules)
		 */
		try{

			//If the array of libraryItems is not empty, for all elements in array, add all elements to our Data Structure
				//Note: if we change the Data Structure to a HashMap, make sure the the 'add' methods below work
			if(fInfo != null){
				for (HowToParseLibraryItems lItems : fInfo.getLibraryItems()){
					if(lItems.getItemType().compareToIgnoreCase("DVD") == 0){
						libraryList.add(new DVD(lItems.getItemName(), lItems.getItemType().toUpperCase(), lItems.getItemId()));
					}
					else if(lItems.getItemType().compareToIgnoreCase("Magazine") == 0){
						libraryList.add(new Magazine(lItems.getItemName(), lItems.getItemType().toUpperCase(), lItems.getItemId()));
					}
					else if(lItems.getItemType().compareToIgnoreCase("Book") == 0){
						libraryList.add(new Book(lItems.getItemName(), lItems.getItemType().toUpperCase(), lItems.getItemId(), lItems.getItemAuthor()));
					}
					else if(lItems.getItemType().compareToIgnoreCase("CD") == 0){
						libraryList.add(new CD(lItems.getItemName(), lItems.getItemType().toUpperCase(), lItems.getItemId(), lItems.getItemArtist()));
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
		
		return libraryList;
	}

}
