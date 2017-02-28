import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.google.gson.Gson;
import java.util.List;
import java.util.ArrayList;

/** THIS CLASS WILL NOT BE PART OF OUR FINISHED PROGRAM
 * 
 * 	This Class tests to make sure that our JSON file correctly parses into Library_Items Objects [and its 4 Subclasses of Book, CD, DVD, Magazine]
 * 		Once parsed, the Library_Items are stored in an ArrayList
 * 
 * 	MAIN STATEMENT:
 * 		CURRENTLY: Implementation of source files will CORRECTLY read JSON files for the following cases:
 * 			(1) JSON file with correctly expected formatting (4 Entries)
 * 			(2) JSON file with correctly expected formatting AND only one item_type [Ex: Only a list of CDs]
 * 			(3) JSON file with correctly expected formatting BUT ALSO contains misspelling for item_type [Ex: 'DV ']
 * 			(4) JSON file contains nothing
 * 			(5) JSON file with correctly expected formatting AND contains duplicate entries
 * 			(6) JSON file with correctly expected formatting and more than expected [4] entries [n Entries > 4 Entries]
 * 			(7) JSON file with correctly expected formatting BUT ALSO contains incorrect upper/lower case for item_type [DVD vs dVd]
 * 
 * 		CURRENTLY: Program crashes when:
 * 			(7) JSON file incorrectly formatted according to JSON standards: MalformedJsonException
 * 
 * 	For reference [Testing JSON Files]:
 * 		(1) hello.json 
 * 				- Json example found on the back of our assignment sheet
 * 						- CURRENT STATUS: PASS
 * 		(2) test1.json
 * 				- Contains nothing but one type (only CDs)
 *   					- CURRENT STATUS: PASS
 * 		(3) test2.json
 * 				- Contains misspelling in type ("DVD" misspelled as "DV ")
 * 						- CURRENT STATUS: PASS
 * 		(4) test3.json
 * 				- Contains nothing in file
 * 						- CURRENT STATUS: PASS
 * 		(5) test4.json
 * 				- Contains duplicate entry
 * 						- CURRENT STATUS: PASS
 * 		(6) test5.json
 * 				- Contains various entries [8 entries]
 * 						- CURRENT STATUS: PASS
 *		(7) test6.json
 *				- Contains incorrect casing for item_type fields (EX: DVD is spelled 'dVd')
 *						- CURRENT STATUS: PASS
 *		(8) test7.json
 *				- Contains malformatted JSON file
 *						- CURRENT STATUS: FAIL
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
