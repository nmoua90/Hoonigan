package com.implementation.hoonigan;

/**
 * This class describes undefined objects that are read from JSON/XML files.
 * @author Hoonigan
 *
 */
public class UndefinedItem extends Item{
	
	/**
	 * 
	 */
	public UndefinedItem(){
	}
	
	/**
	 * 
	 */
	public UndefinedItem(String name, String type, String id, int libraryID){
		this.item_name = name;
		this.item_type = type;
		this.item_id = id;
		this.libraryID = libraryID;
	}
	
	/**
	 * Does nothing if called, only implemented due to inheritence constraints.
	 */
	public void checkOut(){}
	
	/**
	 * Does nothing if called, only implemented due to inheritence constraints.
	 */
	@Override
	public String toString(){ return""; }
}
