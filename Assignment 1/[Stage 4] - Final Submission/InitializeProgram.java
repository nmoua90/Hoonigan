package hoonigan;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

/**InitializeProgram class
 * 
 * This class instantiates a Parser object, which reads a JSON file, Parses the JSON file, and stores the Elements within the JSON file into a HashMap.
 * The class also contains a search method which searches the HashMap for a String ID number, and a method which instantiates a text-based interface.
 * @author Hoonigan
 * 
 */
public class InitializeProgram {
	/**Invariant Comments
	 * - reader is a BufferedReader which is initially set to null. It is used to read a text stream from a JSON file within the init() method. After being initialized, its value never changes. 
	 * - libraryMap	is a HashMap containing Library_Items. It is initially set to null, and is filled within the init() method. After being initialized, its contained elements do not change, though the fields of those elements may. 
	 * - userInput	is a Scanner from the Java Library, simply used to ask for user inputs throughout this class. It's value can be reused and reassigned an infinite amount of times.
	 */
	private Map<String, Library_Items> libraryMap;
	private BufferedReader reader;
	private Parser parser;
	private Scanner userInput;
	
	/**InitializeProgram()
	 * Default Constructor which sets fields
	 */
	InitializeProgram(){
		reader = null;
		parser = null;
		libraryMap = null;
		userInput = new Scanner(System.in);
	}
	
	/**init()
	 * This method initializes the majority of needed resources within the Library program. It reads a JSON file, Parses the JSON file, and stores the 
	 * elements into a HashMap.
	 * 
	 * Instructor's Note:
	 * Input JSON file is hardcoded. To change File Location, change the JSON location found in the 'reader' variable in the init() method below.
	 * 
	 */
	public void init(){
		try{
			//Read Json file, change Directory of File as needed
			reader = new BufferedReader(new FileReader("C:/hello.json"));
			
			//Create Parser instance which reads our Json file
			parser = new Parser(reader);
			
			//Parse our Json file and store it in a HashMap
			parser.parse();
			
			//Get a HashMap with our Library_Items elements
			libraryMap = parser.getLibraryMap();
		}
		
		catch(FileNotFoundException e){
			System.out.println("Couldn't find your JSON file in the specified directory. Please recheck.");
		}
	}
	
	/**getlibraryMap
	 * Simple getter method.	
	 * @return libraryMap - A HashMap containing Library_Items 
	 */
	public Map<String, Library_Items> getlibraryMap(){
		return libraryMap;
	}
	
	/** search(String id)
	 * 	This method searches the Hash Map data structure with an ID field, and returns one Library_Items if both ID fields match
	 * 	@param id - The id of a Library_Items
	 * 	@return found - The Library_Items that was found with a matching id (Returns NULL if no Library_Items was found)
	 */
	public Library_Items search(String id){
		Library_Items found = null;
		found = parser.searchForItem(id);
		return found;
	}
	
	/**getAllLibraryElements()
	 * This method returns all elements in the HashMap as a String, using an iterator
	 * @return line - a String containing all elements of the HashMap
	 */
	public String getAllLibraryElements(){
		String line = "";
		
		Iterator<String> itr = libraryMap.keySet().iterator();
		
		while(itr.hasNext()){
			Library_Items li = libraryMap.get(itr.next());
			line += li.toString() + "\n";
		}
		
		return line;
	}
	
	/** printWelcome()
	 * 	This method returns a string containing an Introductory Message, as well as every HashMap elements toString() method.		
	 * 	@return line - A string that contains an Introductory Software Message, as well as every HashMap elements toString() method
	 */
	public String printWelcome(){
		String line = ("WELCOME! CHECK OUT THE CURRENT STATE OF OUR LIBRARY COLLECTION!\n" + 
		"----------------------------\n" + getAllLibraryElements() + 
		"----------------------------\n");
		return line;
	}
	

	/** userOption()
	 * 	This method asks the user for an integer input, where 0 = user wants to check-out, and 1 = user wants to check-in
	 * 	@return checkStatus - an integer, that represents whether the user wants to check-out or check-in
	 */
	public int userOption(){
		int checkStatus = -1;
		try{
			System.out.print("Would you like to check out [enter 0], check in [enter 1], or quit [enter anything else]: ");
			checkStatus = userInput.nextInt();
		}catch(InputMismatchException e){
			checkStatus = 69;
		}
		return checkStatus;
	}
	
	/** searchQueryResult(int checkStatus)
	 * 	This method asks the user for a String input, which will be used as a key in searching the Library_Items HashMap for a matching element.
	 * 	@param checkStatus - an integer input; Where 0 = user wants to check out, and 1 = user wants to check-in
	 * 	@return found - a Library_Items object within the HashMap (returns NULL if no object was found)
	 */
	public Library_Items searchQueryResult(int checkStatus){
		Library_Items found = null;
		
		try{
			if(checkStatus == 0 || checkStatus == 1){
				//Ask user for ID, search for ID
				System.out.print("\nType in the ID of your item: ");
				String searchID = userInput.next();
				found = search(searchID);

				//If the item was not found, print a message saying so
				if(found == null)
					System.out.println("\nSorry! That item doesn't exist!");
			}
		}catch(NullPointerException | InputMismatchException e){
			System.out.println("Invalid entry! Enter an integer.");
		}
		
		return found;
	}
	
	/**doLibraryOperations(int checkStatus, Library_Items found)
	 * This method performs either a CheckOut or CheckIn of a Library Item. What Item is used, and what method is called upon it depends on the 
	 * parameters that were passed in.
	 * @param checkStatus - an integer input; Where 0 = user wants to check out, and 1 = user wants to check-in.
	 * @param found - a Library_Items object that was found by searchQueryResult(int checkStatus)
	 */
	public void doLibraryOperations(int checkStatus, Library_Items found){
		try{
			//If user entered checkout/checkin
			if(checkStatus == 0 || checkStatus == 1){
				//If user wants to checkout, check validity of that command
				if(checkStatus == 0){
					if(found.isCheckedOut())
						System.out.println("\nSorry! " + found.getItem_name() + " is already checked out!\n");
					else{
						found.checkOut();
						System.out.println("\nSuccessfully checked out " + found.getItem_name() + "! The Item is due on " + 
						found.returnDate() + ".");
					}
				}
				//If user wants to checkin, check if that is a valid/invalid option
				else{
					if(!found.isCheckedOut()){
						System.out.println("\nWhat! We've already got a copy of " + found.getItem_name() + 
							" in our Library! Maybe you're checking in at the wrong place!");
					}else{
						found.checkIn();
						System.out.println("\nSuccessfully checked in " + found.getItem_name() + "!");
					}
				}
			}//end of initial if block
		}catch(InputMismatchException | NullPointerException e){}
	}

	/**askUserToContinue()
	 * This method asks the user if they would like to run the program again.
	 * @return runProgram - an integer, where a value of 1 represents that the user would like to run the program again.
	 */
	public int askUserToContinue(){
		int runProgram = -1;
		
		try{
			System.out.println("\n-----------------------------");
			System.out.print("\nCan we do anything more for you?\n\n"
					+ "[Enter anything else to quit] and [Enter 1] if you would like to checkout/checkin another item: ");
			
			//Need this line to clear \n character
			userInput.nextLine();				
			runProgram = userInput.nextInt();
			
			//Reset Library Header Format if Program reloops
			if(runProgram == 1)
				System.out.println("\n-----------------------------");
			
		}catch(InputMismatchException e){
			System.out.println("\n-----------------------------");
		}
		return runProgram;
	}
	
	
	/**runProgram()
	 * This method calls the init() method, which initializes the entire program. 
	 * It then runs a loop which calls various other methods which do the following:
	 * 		(1) Greets the user
	 *  	(2) Asks if they would like to Check-In or Check-Out
	 *  	(3) Queries the HashMap data structure of Libraries
	 *  	(4) Performs the Check-In and Checkout methods accordingly
	 *  	(5) Asks the user if they would like to run the loop again
	 */
	public void runProgram(){
		//Initialize our entire program/classes
		init();
		
		//By default, running the program is true
		int runProgram = 1;
			
		//While the user wants to keep running the program
		while(runProgram == 1){
			//Print welcome message & current status of all library items
			System.out.println(printWelcome());
				
			//Ask for the user to enter 0 for checkout, or 1 for check-in
			int choice = userOption();
				
			//Prompt for an ID#, and return a Library Item if it is in the HashMap
			Library_Items queryResult = searchQueryResult(choice);
				
			//Do Checkout or Checkin Methods, based on the user's input and the found Library Item
			doLibraryOperations(choice, queryResult);
			
			//Exit program if user wants to quit
			if(choice != 0 && choice != 1 && choice != -1)
				break;
			
			//Ask user if they want to run the program again
			runProgram = askUserToContinue();
		}
		
		//Close Scanner, print farewell message
		userInput.close();
		System.out.println("\nGoodbye Then!");	
	}

}