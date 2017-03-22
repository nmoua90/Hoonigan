package com.implementation.hoonigan;


/**
 * 
 * @author Hoonigan
 *
 */
public class Admin extends Client{
	protected final boolean access = true;
	
	public Admin(String firstName, String lastName, String userName, char[] password, 
			String streetAddress, String securityQuestion, String securityAnswer){
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.password = password;
		this.streetAddress = streetAddress;
		this.securityAnswer = securityAnswer;
		this.securityQuestion = securityQuestion;
	}

	/**
	 * 
	 * @return
	 */
	public boolean getAccess() {
		return access;
	}
		
}