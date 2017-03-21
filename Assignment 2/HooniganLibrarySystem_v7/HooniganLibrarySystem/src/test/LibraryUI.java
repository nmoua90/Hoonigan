package test;

import com.implementation.hoonigan.*;

/**LibraryUI class
 * Used to run our UI, but now just Tests to see that the old UI works when working across packages.
 * 
 */

public class LibraryUI{
	/**main(String[] args)
	 * This main method initializes the Library program, and runs it.	
	 * @param args - a String of arguments
	 */
	public static void main(String[] args){
		InitializeProgram startProgram = new InitializeProgram();
		startProgram.runProgram();
	}
	
}
