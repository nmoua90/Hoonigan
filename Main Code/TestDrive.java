import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.google.gson.Gson;
import java.util.List;
import java.util.ArrayList;

/**
 * Edited TestDrive to be more robust, instead of copying reused code from Parser.java
 * 		  Was able to redesign Main method via changing the implementation of the Parser class
 *		  View Parser.java comments to see changes
 * @author Nhia
 *
 */
public class TestDrive {
	public static void main(String[] args) {
		try{
			//Read Json file
			BufferedReader br = new BufferedReader(new FileReader("C:/hello.json"));
			
			//Create Parser instance which takes in our Json file
			Parser myParser = new Parser(br);
			
			//Parser object parses and prints our Library
			List<Library_Items> li = myParser.parse();
			
			//Prints out: name, type, id, [artist/author], dueDate, checkedOutStatus
				//Note: dueDate is printed correctly at this runTime, since time only changes when user selects checkOut()
			for(Library_Items items : li){
				System.out.println(items);
			}
			
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}
	}
}
