package com.implementation.hoonigan;

import java.awt.print.Book;
import java.util.Collection;
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

/**
 * Edited by Matthew on 3/19/2017.
 */
public class XMLParser {
	private File xmlFile;
	private int libraryID;
	private Multimap<String, Item> xmlLibraryMap;
	
	public XMLParser(File xmlFile, int libraryID){
			this.xmlFile = xmlFile;
			this.libraryID = libraryID;
			this.xmlLibraryMap = ArrayListMultimap.create();
	}
	
	
    //Would have the GUI call FileChooser() which would allow the user to select a file. That file's location gets fed
    //into xmlParser(File xmlFile).
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

                    if (element.getAttribute("type").matches("CD")){
                        CD cd = new CD(
                                element.getAttribute("type"),//Had to change these to .getAttribute because .getElementByTagName only works if tag is Capitalized. i.e. Artist or Name
                                element.getAttribute("id"),
                                element.getElementsByTagName("Artist").item(0).getTextContent(),//Had to change i to 0 for all cases.
                                element.getElementsByTagName("Name").item(0).getTextContent(),
                                libraryID,
                                true
                        );
                        xmlLibraryMap.put(cd.getItem_id(), cd);
                    }

                    else if (element.getAttribute("type").matches("DVD")){
                        DVD dvd = new DVD(
                                element.getAttribute("type"),
                                element.getAttribute("id"),
                                element.getElementsByTagName("Name").item(0).getTextContent(),
                                libraryID,
                                true
                        );
                        xmlLibraryMap.put(dvd.getItem_id(), dvd);
                    }

                    else if (element.getAttribute("type").matches("MAGAZINE")){
                        Magazine magazine = new Magazine(
                                element.getAttribute("type"),
                                element.getAttribute("id"),
                                element.getElementsByTagName("Name").item(0).getTextContent(),
                                element.getElementsByTagName("Volume").item(0).getTextContent(),
                                libraryID,
                                true
                        );
                        xmlLibraryMap.put(magazine.getItem_id(), magazine);
                    }

                    else if (element.getAttribute("type").matches("BOOK")) {
                        BookItem book = new BookItem(
                                element.getAttribute("type"),
                                element.getAttribute("id"),
                                element.getElementsByTagName("Author").item(0).getTextContent(),
                                element.getElementsByTagName("Name").item(0).getTextContent(),
                                libraryID
                                ,true
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


	public Multimap<String, Item> getXmlLibraryMap() {
		return xmlLibraryMap;
	}


	public void setXmlLibraryMap(Multimap<String, Item> xmlLibraryMap) {
		this.xmlLibraryMap = xmlLibraryMap;
	}

    
    
}//end of Driver class