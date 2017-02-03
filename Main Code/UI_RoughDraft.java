import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import com.google.gson.Gson;
import java.util.Scanner;

/** EDIT FREELY, SINCE THIS IS JUST A ROUGH DRAFT
 * 
 * 	This class is a template on the conditionals we need if we're using a Text Based Program
 * 		1. Ask the user if they want to CHECK IN or CHECK OUT [or TERMINATE]
 * 		2. What item are they CHECKING IN or CHECKING OUT?
 * 		3. Do check_in(), or check_out()
 * 		4. Reloop program
 * 
 *
 */
public class UI_RoughDraft {
	public static void main(String[] args){
		
		try{
			Scanner input = new Scanner(System.in);
			
			//Note: Json file loading should occur a level below this UI class [User should not see this]
			BufferedReader br = new BufferedReader(new FileReader("C:/hello.json"));
			
			//Note: Instantiating Parser should occur a level below this UI class [User should not see this]
			Parser myParser = new Parser(br);
			
			
			//[User should also not see this loop below]
			//while keepRunning is true
				//Prompt user for checkin/checkout input
				try{
					System.out.print("Would you like to check out [enter 0], check in [enter 1], or quit [enter 2]: ");
					int checkStatus = input.nextInt();
					
						
					//Prompt user for search input
					System.out.print("Enter the ID for the item would you like to select: ");
					String searchId = input.nextLine();
					
					//PSEUDO CODE
					//Search through the data structure for the id
						//If library_item with correct id found
							//If checkInStatus = 0 [Check Out]
								//library_item[foundIndex].checkOut()
							//If checkInStatus = 1 [Check In]
								//library_item[foundIndex].checkIn()
						//Else library_item not found
							//Display message
				}catch(Exception e){
					//Change Exception Parameter to specific Proper Exception for mishandled inputs
				}
				
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}
	}
}
