package com.implementation.hoonigan;

import java.awt.print.Book;
import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.io.*;
import java.util.List;

/**
 * @author Hoonigan
 */
public class XMLParser {
	private int libraryID;
	
	/**XMLParser()
	 * default constructor
	 */
	public XMLParser(){}
	
	/**XMLParser(int libraryID)
	 * Constructor, sets libaryID
	 * @param libraryID - the library ID from where the items are being parsed.
	 */
	public XMLParser(int libraryID){
		this.libraryID = libraryID;
	}
	
    //Would have the GUI call FileChooser() which would allow the user to select a file. That file's location gets fed
    //into xmlParser(File xmlFile).
    public void parseFile(File xmlFile){
        try{
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            org.w3c.dom.Document document = documentBuilder.parse(xmlFile);

            NodeList list = document.getElementsByTagName("Item");

            List<Item> xmlLibraryItems = new ArrayList<Item>();

            for (int i = 0; i < list.getLength(); i++) {
                Node node = list.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;

                    if (element.getElementsByTagName("type").item(i).getTextContent() == "CD"){
                        CD cd = new CD(
                                //Create a new CD constructor for this xml order. This constructor only called for xml
                                //Same for all other Items
                                element.getElementsByTagName("Name").item(i).getTextContent(),
                                element.getElementsByTagName("type").item(i).getTextContent(),
                                element.getElementsByTagName("id").item(i).getTextContent(),
                                element.getElementsByTagName("Artist").item(i).getTextContent(),
                                libraryID
                        );
                        xmlLibraryItems.add(cd);
                    }

                    else if (element.getElementsByTagName("type").item(i).getTextContent() == "DVD"){
                        DVD dvd = new DVD(
                                element.getElementsByTagName("type").item(i).getTextContent(),
                                element.getElementsByTagName("id").item(i).getTextContent(),
                                element.getElementsByTagName("Name").item(i).getTextContent(),
                                libraryID
                        );
                        xmlLibraryItems.add(dvd);
                    }

                    else if (element.getElementsByTagName("type").item(i).getTextContent() == "MAGAZINE"){
                        Magazine magazine = new Magazine(
                                element.getElementsByTagName("Name").item(i).getTextContent(),
                                element.getElementsByTagName("type").item(i).getTextContent(),
                                element.getElementsByTagName("id").item(i).getTextContent(),
                                element.getElementsByTagName("Volume").item(i).getTextContent(),
                                libraryID
                        );
                        xmlLibraryItems.add(magazine);
                    }

                    else if (element.getElementsByTagName("type").item(i).getTextContent() == "BOOK"){
                        BookItem book = new BookItem(
                        		element.getElementsByTagName("Name").item(i).getTextContent(),
                                element.getElementsByTagName("type").item(i).getTextContent(),
                                element.getElementsByTagName("id").item(i).getTextContent(),
                                element.getElementsByTagName("Author").item(i).getTextContent(),
                                libraryID
                        );
                        xmlLibraryItems.add(book);
                    }
                }//end of if statement
            }//end of for statement
        }catch(IOException i){
            i.printStackTrace();
        }catch(SAXException s){
            s.printStackTrace();
        }catch(ParserConfigurationException p){
            p.printStackTrace();
        }
    }//end of xmlParaser()

}//end of Driver class