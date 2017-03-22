package com.implementation.hoonigan;

import com.gui.hoonigan.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;

import javax.swing.JFrame;
import java.util.List;
import java.util.ArrayList;

import com.gui.hoonigan.AdminFrame;
import java.util.Collection;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

/**Library class
 * Simple Library class.
 * @author Hoonigan
 * 
 */
public class Library{
	private int libraryID;
	private Multimap<String, Item> libraryCatalogMap;
	
	/**Library()
	 * Default constructor
	 */
	public Library(){}
	
	/**Library(int libraryID)
	 * Constructor only takes in an ID. Catalog will be added at another time.
	 * @param libraryID - ID of library
	 */
	public Library(int libraryID){
		this.libraryID = libraryID;
	}
	
	/**Library(int libraryID, Map<String, Item> libraryCatalogMap)
	 * Constructor takes in an ID, and catalog is also added.
	 * @param libraryID - ID of library
	 * @param libraryCatalogMap - HashMap containing all of this Library's media
	 */
	public Library(int libraryID, Multimap<String, Item> libraryCatalogMap){
		this.libraryCatalogMap = libraryCatalogMap;
		this.libraryID = libraryID;
	}

	/**getLibraryID()
	 * Typical getter
	 * @return libraryID of this library
	 */
	public int getLibraryID() {
		return libraryID;
	}

	/**setLibraryID(int libraryID)
	 * Typical setter
	 * @param libraryID - set this library's ID
	 */
	public void setLibraryID(int libraryID) {
		this.libraryID = libraryID;
	}

	/**getLibraryCatalogMap()
	 * Tyical getter
	 * @return - HashMap containing all of this library's media items
	 */
	public Multimap<String, Item> getLibraryCatalogMap() {
		return libraryCatalogMap;
	}

	/**setLibraryCatalogMap(Map<String, Item> libraryCatalogMap)
	 * Typical setter
	 * @param libraryCatalogMap - HashMap containing all of this library's media items
	 */
	public void setLibraryCatalogMap(Multimap<String, Item> libraryCatalogMap) {
		this.libraryCatalogMap = libraryCatalogMap;
	}

	/**addToLibraryCatalog(Item item)
	 * This method adds an Item element to the Library's catalog
	 * @param item - the new Item to be entered into the Library catalog
	 */
	public void addToLibraryCatalog(Item item){
		this.libraryCatalogMap.put(item.getItem_id(), item);
	}
}