import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.List;
import java.util.ArrayList;
import java.io.*;


/**Driver.java
 * 
 * This class parses a XML file via the DOM Parser that comes with JDK8.
 * 
 * For additional tutorial, check out:
 * 		https://www.tutorialspoint.com/java_xml/java_dom_parser.htm
 * 
 * Majority of code in this class re-used from:
 * 		http://stackoverflow.com/questions/41109610/read-xml-file-in-the-java
 * 
 * @author Nhia
 *
 */
public class Driver {
	//Main method calls xml parser
	public static void main(String[] args) throws Exception {
		xmlParser();
	}
	
	/**xmlReader() - 
	 * Parses an xml file.
	 */
	public static void xmlParser(){
		try{
			File xmlFile = new File("\\example.xml");

		    DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		    DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
		    org.w3c.dom.Document document = documentBuilder.parse(xmlFile);

		    NodeList list = document.getElementsByTagName("Developer");

		    List<Developer> developers = new ArrayList<>();

		    for (int i = 0; i < list.getLength(); i++) {
		        Node node = list.item(i);

		        if (node.getNodeType() == Node.ELEMENT_NODE) {
		            Element element = (Element) node;

		            Developer developer = new Developer(
		                element.getAttribute("Id"),
		                element.getElementsByTagName("Name").item(0).getTextContent(),
		                element.getElementsByTagName("Surname").item(0).getTextContent(),
		                Integer.parseInt(element.getElementsByTagName("Age").item(0).getTextContent())
		            );
		            developers.add(developer);
		        }
		    }

		    // at this point we have a list of developers
		    // print out all the developers
		    for (Developer developer : developers) {
		        System.out.println("ID: " + developer.getId());
		        System.out.println("Name: " + developer.getName());
		        System.out.println("Surname: " + developer.getSurname());
		        System.out.println("Age: " + developer.getAge() + "\n");
		    }
		}catch(IOException i){
			i.printStackTrace();
		}catch(SAXException s){
			s.printStackTrace();
		}catch(ParserConfigurationException p){
			p.printStackTrace();
		}
	}//end of xmlParaser()
	
}//end of Driver class