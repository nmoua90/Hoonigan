import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.google.gson.Gson;

/**
 * Edited Parser to have fields, constructor, conditionals to check for idType
 * @author Nhia
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
	
	/**
	 * 
	 * @param br is the location of the passed in JSON FILE
	 */
	Parser(BufferedReader br){
		this.gson = new Gson();
		this.br = br;
		this.fInfo = gson.fromJson(br, FileInfo.class);
	}
	
	/** parse()
	 * 		Parses a json file
	 */
	public void parse(){	
		/** Try the following:
		 * 		- Our BufferedReader reads the Json file at FileReader(Location) 
		 * 		- Our FileInfo array is == (whatever_the_reader_read, parsed_by_the_FileInfo_rules)
		 */
		try{

			//If the array is not empty, for all items in array, print all the fields with a print statement
			if (fInfo != null){
				for (LibraryItems lItems : fInfo.getLibraryItems()){
					if(lItems.getItemType().compareToIgnoreCase("DVD") == 0){
						String line = String.format("Name: %s\n Type: %s\n ID: %s\n", lItems.getItemName(), lItems.getItemType(), 
								lItems.getItemId());
						System.out.println(line);
					}
					else if(lItems.getItemType().compareToIgnoreCase("Magazine") == 0){
						String line = String.format("Name: %s\n Type: %s\n ID: %s\n", lItems.getItemName(), lItems.getItemType(), 
								lItems.getItemId());
						System.out.println(line);
					}
					else if(lItems.getItemType().compareToIgnoreCase("Book") == 0){
						String line = String.format("Name: %s\n Type: %s\n ID: %s\n Author: %s\n", lItems.getItemName(), 
								lItems.getItemType(), lItems.getItemId(), lItems.getItemAuthor());
						System.out.println(line);
					}
					else if(lItems.getItemType().compareToIgnoreCase("CD") == 0){
						String line = String.format("Name: %s\n Type: %s\n ID: %s\n Artist: %s\n", lItems.getItemName(), 
								lItems.getItemType(), lItems.getItemId(), lItems.getItemArtist());
						System.out.println(line);
					}
				}
			}
		}catch(Exception e){ //Changed from the original FileNotFoundException... because of unknown error... Help?
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

}
