import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.google.gson.Gson;

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
			myParser.parse();
		
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}
	}
}
