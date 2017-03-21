package example;
import java.io.File;
import java.util.Map;
import java.util.Set;

/**
 * 
 * @author Nhia
 *
 */
public class Main{
	public static void main(String[] args){
		//Get the directory of a XML file
		File xmlFile = new File("example.xml"); 
		
		//Pass the XML file to the Constructor, along with an integer [this is the Library ID]
		XMLParser xParser1 = new XMLParser(xmlFile , 1);
		
		//Parse the XML file you passed in
		xParser1.parse();
		
		//Get the parsed XML file, and store it into a Map with (key = Developer ID, value = Developer object)
		Map<String, Developer> myMap = xParser1.getXmlLibraryMap();
		
		//Create a set of the entire Map
		Set<Map.Entry<String, Developer>> mySet = myMap.entrySet();
		
		//Loop over the Set, and print out each employee
		for(Map.Entry<String, Developer> employees : mySet){
			System.out.println(employees.toString());
		}
	}
}
