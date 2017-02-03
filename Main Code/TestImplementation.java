import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.google.gson.Gson;
import java.util.List;
import java.util.ArrayList;

/** THIS CLASS WILL NOT BE PART OF OUR FINISHED PROGRAM
 * 
 * This Class tests to make sure that our JSON file parses correctly into Library_Items Objects [and its 4 Subclasses of Book, CD, DVD, Magazine]
 * 		Once parsed, the Library_Items are  stored in an ArrayList
 * 
 * @author Nhia
 *
 */
public class TestImplementation {
	public static void main(String[] args) {
		try{
			//Read Json file, change Directory of File as needed
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
