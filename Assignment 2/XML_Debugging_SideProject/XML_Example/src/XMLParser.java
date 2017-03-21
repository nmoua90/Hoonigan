
import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.io.*;
import java.util.HashMap;

/**XMLParser.java
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
	private Map<String, Item> xmlLibraryMap;
	
	XMLParser(File xmlFile, int libraryID){
			this.xmlFile = xmlFile;
			this.libraryID = libraryID;
			this.xmlLibraryMap = new HashMap<String, Item>();
	}
	
	/**xmlReader() - 
	 * Parses an xml file.
	 */
	public void parse(){
		try{
		    DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		    DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
		    org.w3c.dom.Document document = documentBuilder.parse(xmlFile);

		    NodeList list = document.getElementsByTagName("Item");
		    
		    for (int i = 0; i < list.getLength(); i++) {
		        Node node = list.item(i);

		        if (node.getNodeType() == Node.ELEMENT_NODE) {
		            Element element = (Element) node;
		            
		            if (element.getElementsByTagName("type").item(i).getTextContent().compareToIgnoreCase("CD") == 0){
                		
                        CD cd = new CD(
                                //Create a new CD constructor for this xml order. This constructor only called for xml
                                //Same for all other Items
                                element.getElementsByTagName("type").item(i).getTextContent(),
                                element.getElementsByTagName("id").item(i).getTextContent(),
                                element.getElementsByTagName("Artist").item(i).getTextContent(),
                                element.getElementsByTagName("Name").item(i).getTextContent(),
                                libraryID
                        );
                        xmlLibraryMap.put(cd.getItem_id(), cd);
                    }

                    else if (element.getElementsByTagName("type").item(i).getTextContent().compareToIgnoreCase("DVD") == 0){
                        DVD dvd = new DVD(
                                element.getElementsByTagName("type").item(i).getTextContent(),
                                element.getElementsByTagName("id").item(i).getTextContent(),
                                element.getElementsByTagName("Name").item(i).getTextContent(),
                                libraryID
                        );
                        xmlLibraryMap.put(dvd.getItem_id(), dvd);
                    }

                    else if (element.getElementsByTagName("type").item(i).getTextContent().compareToIgnoreCase("MAGAZINE") == 0){
                        Magazine magazine = new Magazine(
                                element.getElementsByTagName("type").item(i).getTextContent(),
                                element.getElementsByTagName("id").item(i).getTextContent(),
                                element.getElementsByTagName("Name").item(i).getTextContent(),
                                element.getElementsByTagName("Volume").item(i).getTextContent(),
                                libraryID
                        );
                        xmlLibraryMap.put(magazine.getItem_id(), magazine);
                    }

                    else if (element.getElementsByTagName("type").item(i).getTextContent().compareToIgnoreCase("BOOK") == 0){
                    	BookItem book = new BookItem(
                    			element.getElementsByTagName("type").item(i).getTextContent(),
                                element.getElementsByTagName("id").item(i).getTextContent(),
                                element.getElementsByTagName("Author").item(i).getTextContent(),
                                element.getElementsByTagName("Name").item(i).getTextContent(),
                                libraryID
                        );
                    	xmlLibraryMap.put(book.getItem_id(), book);
                    }    
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

	public Map<String, Item> getXmlLibraryMap() {
		return xmlLibraryMap;
	}

	public void setXmlLibraryMap(Map<String, Item> xmlLibraryMap) {
		this.xmlLibraryMap = xmlLibraryMap;
	}
	
}//end of Driver class