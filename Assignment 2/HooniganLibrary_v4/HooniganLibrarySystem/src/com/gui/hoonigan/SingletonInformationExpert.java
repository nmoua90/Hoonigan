package com.gui.hoonigan;

import com.implementation.hoonigan.*;
import java.util.Map;
import java.util.HashMap;

/**SingletonInformationExpert
 * Singleton pattern & Information Expert pattern. Makes all fields Global with the usage of getters/setters. Fields need to be global so
 * all GUI frames can have underlying access to all relevant fields.
 * @author Hoonigan
 *
 */
public class SingletonInformationExpert {
	//At first, there is no instance of this class
	private static SingletonInformationExpert instance = null;
			
	//Hashmap for admin user accounts [userName, password]
	private static Map<String, char[]> adminList = new HashMap<String, char[]>();
		
	//HashMap for regular user accounts [userName, password]
	private static Map<String, char[]> memberList = new HashMap<String, char[]>();
		
	//HashMap for allLibraryItems [ID, Item]
	private static Map<String, Item> libraryItemList;
	
	//Json Parser to be used at later date
	private static JsonParser jsonParser;
	
	//XML Parser to be used at later date
	private static XMLParser xmlParser;
	
	//Might need more global variables

	/**SingletonInformationExpert()
	 * Default constructor is disabled. No allowance to call the Constructor in Singleton Pattern.
	 */
	private void SingletonInformationExpert(){
	}
		
	/**getInstance()
	 * Implements the Singleton pattern, by only allowing one instance of this class.
	 * @return one, and only one instance of this class.
	 */
	public static SingletonInformationExpert getInstance(){
		if(instance==null){
			instance = new SingletonInformationExpert();
		}
		return instance;
	}

	/**
	 * 
	 * @return
	 */
	public static Map<String, char[]> getAdminList() {
		return adminList;
	}

	/**
	 * 
	 * @param adminList
	 */
	public static void setAdminList(Map<String, char[]> adminList) {
		SingletonInformationExpert.adminList = adminList;
	}

	/**
	 * 
	 * @return
	 */
	public static Map<String, char[]> getMemberList() {
		return memberList;
	}

	/**
	 * 
	 * @param memberList
	 */
	public static void setMemberList(Map<String, char[]> memberList) {
		SingletonInformationExpert.memberList = memberList;
	}

	/**
	 * 
	 * @return
	 */
	public static Map<String, Item> getLibraryItemList() {
		return libraryItemList;
	}

	/**
	 * 
	 * @param libraryItemList
	 */
	public static void setLibraryItemList(Map<String, Item> libraryItemList) {
		SingletonInformationExpert.libraryItemList = libraryItemList;
	}

	/**
	 * 
	 * @return
	 */
	public static JsonParser getJsonParser() {
		return jsonParser;
	}

	/**
	 * 
	 * @param jsonParser
	 */
	public static void setJsonParser(JsonParser jsonParser) {
		SingletonInformationExpert.jsonParser = jsonParser;
	}

	/**
	 * 
	 * @return
	 */
	public static XMLParser getXmlParser() {
		return xmlParser;
	}

	/**
	 * 
	 * @param xmlParser
	 */
	public static void setXmlParser(XMLParser xmlParser) {
		SingletonInformationExpert.xmlParser = xmlParser;
	}

	/**
	 * 
	 * @param instance
	 */
	public static void setInstance(SingletonInformationExpert instance) {
		SingletonInformationExpert.instance = instance;
	}
			
	
}
