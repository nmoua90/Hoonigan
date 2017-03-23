package com.implementation.hoonigan;

import java.util.ArrayList;
import java.util.List;

/**Client class
 * Abstract class, is the base foundation for Users within the Hoonigan System.
 * @author Hoonigan
 *
 */
public abstract class Client {
	protected String firstName;
	protected String lastName;
	protected String userName;
	protected char[] password;
	protected String streetAddress;
	protected String securityQuestion;
	protected String securityAnswer;
	protected List<Item> itemsCheckedOut = new ArrayList<Item>();
	
	/**Client()
	 * Default constructor
	 */
	public Client(){
	}

	/**
	 * 
	 * @return
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * 
	 * @param firstName
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * 
	 * @return
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * 
	 * @param lastName
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * 
	 * @return
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * 
	 * @param userName
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * 
	 * @return
	 */
	public char[] getPassword() {
		return password;
	}

	/**
	 * 
	 * @param password
	 */
	public void setPassword(char[] password) {
		this.password = password;
	}

	/**
	 * 
	 * @return
	 */
	public String getStreetAddress() {
		return streetAddress;
	}

	/**
	 * 
	 * @param streetAddress
	 */
	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}

	/**
	 * 
	 * @return
	 */
	public String getSecurityQuestion() {
		return securityQuestion;
	}

	/**
	 * 
	 * @param securityQuestion
	 */
	public void setSecurityQuestion(String securityQuestion) {
		this.securityQuestion = securityQuestion;
	}

	/**
	 * 
	 * @return
	 */
	public String getSecurityAnswer() {
		return securityAnswer;
	}

	
	/**
	 * 
	 * @return
	 */
	public List<Item> getItemsCheckedOut() {
		return itemsCheckedOut;
	}

	/**
	 * 
	 * @param itemsCheckedOut
	 */
	public void setItemsCheckedOut(List<Item> itemsCheckedOut) {
		this.itemsCheckedOut = itemsCheckedOut;
	}
	
	/**
	 * 
	 * @param item
	 */
	public void addToCheckedOutList(Item item){
		this.itemsCheckedOut.add(item);
	}

	
	/**removeFromCheckedOutList(Item item)
	 * 
	 * @param item - the item being checked in
	 * @return 0 if user failed to check an item in, else return 1 if user suceeded in checking an item in
	 */
	public int removeFromCheckedOutList(Item item){
		for(int i = 0; i<itemsCheckedOut.size(); i++){
			if(itemsCheckedOut.get(i).equals(item)){
				if(itemsCheckedOut.get(i).getUniqueQuantityID() == item.getUniqueQuantityID()){
					itemsCheckedOut.remove(i);
					return 1;
				}
			}
		}
		return 0;
	}
	
	/**
	 * 
	 * @return
	 */
	public abstract boolean getAccess();
		
}
