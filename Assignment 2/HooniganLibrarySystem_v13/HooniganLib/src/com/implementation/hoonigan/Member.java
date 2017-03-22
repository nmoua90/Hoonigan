package com.implementation.hoonigan;

/**
 * 
 * 
 *
 */
public class Member extends Client{
	protected final boolean access = false;
	
	public Member(String firstName, String lastName, String userName, char[] password, 
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
