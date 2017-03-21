package example;
import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.io.*;
import java.util.HashMap;

/**OriginalXMLParser.java
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
public class XMLParser {
	private File xmlFile;
	private int libraryID;
	private Map<String, Developer> xmlLibraryMap;
	
	XMLParser(File xmlFile, int libraryID){
			this.xmlFile = xmlFile;
			this.libraryID = libraryID;
			this.xmlLibraryMap = new HashMap<String, Developer>();
	}
	
	/**xmlReader() - 
	 * Parses an xml file.
	 */
	public void parse(){
		try{
		    DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		    DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
		    org.w3c.dom.Document document = documentBuilder.parse(xmlFile);

		    NodeList list = document.getElementsByTagName("Developer");

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
		            xmlLibraryMap.put(developer.getId(), developer);
		        }
		    }
		}catch(IOException i){
			i.printStackTrace();
		}catch(SAXException s){
			s.printStackTrace();
		}catch(ParserConfigurationException p){
			p.printStackTrace();
		}
	}//end of xmlParaser()

	public File getXmlFile() {
		return xmlFile;
	}

	public void setXmlFile(File xmlFile) {
		this.xmlFile = xmlFile;
	}

	public int getLibraryID() {
		return libraryID;
	}

	public void setLibraryID(int libraryID) {
		this.libraryID = libraryID;
	}

	public Map<String, Developer> getXmlLibraryMap() {
		return xmlLibraryMap;
	}

	public void setXmlLibraryMap(Map<String, Developer> xmlLibraryMap) {
		this.xmlLibraryMap = xmlLibraryMap;
	}
	
}//end of Driver class