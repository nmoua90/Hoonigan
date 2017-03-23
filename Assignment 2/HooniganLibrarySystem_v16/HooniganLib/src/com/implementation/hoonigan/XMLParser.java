package com.implementation.hoonigan;

import com.gui.hoonigan.*;
import java.awt.print.Book;
import java.util.Collection;
import java.util.Collections;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.HashMap;
import java.io.*;
import java.util.List;
import java.util.Map;

/**XMLParser class
 * @author Hoonigan
 */
public class XMLParser {
	private File xmlFile;
	private int libraryID;
	private SingletonInformationExpert globalVariables = SingletonInformationExpert.getInstance();
	
	/**
	 * 
	 * @param xmlFile
	 * @param libraryID
	 */
	public XMLParser(File xmlFile, int libraryID){
			this.xmlFile = xmlFile;
			this.libraryID = libraryID;
	}
	
	
    /**
     * 
     */
    public int parse(){
        try{
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            org.w3c.dom.Document document = documentBuilder.parse(xmlFile);

            NodeList list = document.getElementsByTagName("Item");

            for (int i = 0; i < list.getLength(); i++) {
                Node node = list.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    addItemToMultiMap(element);
                }
            }
            
            //If list is empty, return to gui a negative pass value
            if(list.getLength() == 0){
            	return -1;
            }
        }catch(Exception i){
        	//We don't care what exception we get, if we get an error return -1, and GUI will tell us the file did not load
        	return -1;
        }
        return 0;
    }//end of xmlParaser()


    /**addItemToMultiMap(GsonItem item)
	 * This method converts an Element into an Item, 
	 * @param element - the passed in DOM Node, which equals one object in an XML file
	 */
	public void addItemToMultiMap(Element element){
		Item thisItem;
		int duplicateItemID;
		String thisItemID = element.getAttribute("id");
		
		//If this ID is already in our database
		if(globalVariables.getLibraryItemList().containsKey(thisItemID)){
		
			//Get the duplicate IDs of the items already in our database
			Collection<Item> duplicateList = globalVariables.getLibraryItemList().get(thisItemID);
				
			//For all the duplicate keys, put the keys in a List
			List<Integer> uniqueIdList = new ArrayList<Integer>();
			for(Item duplicateItem : duplicateList){
				duplicateItemID = duplicateItem.getUniqueQuantityID();
				uniqueIdList.add(duplicateItemID);
			}
				
			//Find the last unique key, and pre-increment it
			int lastUniqueID = Collections.max(uniqueIdList);
			int newUniqueID = ++lastUniqueID;
				
			//Add this ID to the MultiMap based on Type
			if (element.getAttribute("type").matches("CD")){
	            CD cd = new CD(
	                    element.getAttribute("type"),
	                    element.getAttribute("id"),
	                    element.getElementsByTagName("Artist").item(0).getTextContent(),
	                    element.getElementsByTagName("Name").item(0).getTextContent(),
	                    libraryID,
	                    true,
	                    newUniqueID
	            );
	            globalVariables.addToItemsHashMap(cd);
	            thisItem = cd;
	            addToLibraryMap(thisItem);
	        }
	
	        else if (element.getAttribute("type").matches("DVD")){
	            DVD dvd = new DVD(
	                    element.getAttribute("type"),
	                    element.getAttribute("id"),
	                    element.getElementsByTagName("Name").item(0).getTextContent(),
	                    libraryID,
	                    true,
	                    newUniqueID
	            );
	            globalVariables.addToItemsHashMap(dvd);
	            thisItem = dvd;
	            addToLibraryMap(thisItem);
	        }
	
	        else if (element.getAttribute("type").matches("MAGAZINE")){
	            Magazine magazine = new Magazine(
	                    element.getAttribute("type"),
	                    element.getAttribute("id"),
	                    element.getElementsByTagName("Name").item(0).getTextContent(),
	                    element.getElementsByTagName("Volume").item(0).getTextContent(),
	                    libraryID,
	                    true,
	                    newUniqueID
	            );
	            globalVariables.addToItemsHashMap(magazine);
	            thisItem = magazine;
	            addToLibraryMap(thisItem);
	        }
	
	        else if (element.getAttribute("type").matches("BOOK")) {
	            BookItem book = new BookItem(
	                    element.getAttribute("type"),
	                    element.getAttribute("id"),
	                    element.getElementsByTagName("Author").item(0).getTextContent(),
	                    element.getElementsByTagName("Name").item(0).getTextContent(),
	                    libraryID,
	                    true,
	                    newUniqueID
	            );
	            globalVariables.addToItemsHashMap(book);
	            thisItem = book;
	            addToLibraryMap(thisItem);
	        }
		//If this ID is not in our database
		}else{
			if (element.getAttribute("type").matches("CD")){
	            CD cd = new CD(
	                    element.getAttribute("type"),
	                    element.getAttribute("id"),
	                    element.getElementsByTagName("Artist").item(0).getTextContent(),
	                    element.getElementsByTagName("Name").item(0).getTextContent(),
	                    libraryID,
	                    true,
	                    0
	            );
	            globalVariables.addToItemsHashMap(cd);
	            thisItem = cd;
	            addToLibraryMap(thisItem);
	        }

	        else if (element.getAttribute("type").matches("DVD")){
	            DVD dvd = new DVD(
	                    element.getAttribute("type"),
	                    element.getAttribute("id"),
	                    element.getElementsByTagName("Name").item(0).getTextContent(),
	                    libraryID,
	                    true,
	                    0
	            );
	            globalVariables.addToItemsHashMap(dvd);
	            thisItem = dvd;
	            addToLibraryMap(thisItem);
	        }

	        else if (element.getAttribute("type").matches("MAGAZINE")){
	            Magazine magazine = new Magazine(
	                    element.getAttribute("type"),
	                    element.getAttribute("id"),
	                    element.getElementsByTagName("Name").item(0).getTextContent(),
	                    element.getElementsByTagName("Volume").item(0).getTextContent(),
	                    libraryID,
	                    true,
	                    0
	            );
	            globalVariables.addToItemsHashMap(magazine);
	            thisItem = magazine;
	            addToLibraryMap(thisItem);
	        }

	        else if (element.getAttribute("type").matches("BOOK")) {
	            BookItem book = new BookItem(
	                    element.getAttribute("type"),
	                    element.getAttribute("id"),
	                    element.getElementsByTagName("Author").item(0).getTextContent(),
	                    element.getElementsByTagName("Name").item(0).getTextContent(),
	                    libraryID,
	                    true,
	                    0
	            );
	            globalVariables.addToItemsHashMap(book);
	            thisItem = book;
	            addToLibraryMap(thisItem);
	        }
		}//end of else
	}//end of method
	
	
	/**
	 * 
	 * @param thisItem
	 */
	public void addToLibraryMap(Item thisItem){
		//If the library is already in the system, add the item to it's map
		if(globalVariables.getLibraryList().containsKey(libraryID)){
			Library foundLibrary = globalVariables.getLibraryList().get(libraryID);
			foundLibrary.addToLibraryCatalog(thisItem);
		}
		//Else, put the library into the system, and then add the item to it's map
		else{
			Library newLibrary = new Library(libraryID);
			newLibrary.addToLibraryCatalog(thisItem);
		}
	}
	
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
	
}//end of Driver class