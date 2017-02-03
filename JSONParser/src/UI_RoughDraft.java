import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import com.google.gson.Gson;
import java.util.Scanner;

/**
 * This class is a template on the conditionals we need if we're using a Text Based Program
 * 		1. Ask the user if they want to CHECK IN or CHECK OUT [or TERMINATE]
 * 		2. What item are they CHECKING IN or CHECKING OUT?
 * 		3. Do check_in(), or check_out()
 * 		4. Reloop program
 * 
 * @author Nhia
 *
 */
public class UI_RoughDraft {
	public static void main(String[] args){
		
		try{
			Scanner input = new Scanner(System.in);
			
			//Json file loading should occur a level below this UI class 
			BufferedReader br = new BufferedReader(new FileReader("C:/hello.json"));
			
			//Instantiating Parser should occur a level below this UI class 
			Parser myParser = new Parser(br);
			
			//Prompt user for input
			System.out.print("Would you like to check out [enter 0] or check in [enter 1]: ");
			int answer = input.nextInt();
			
			//Based on user input, do correct task
			if((answer != 0) && (answer != 1))
				System.out.println("Enter valid integer");
			else if(answer == 0){
				//Replace with actual method
				System.out.println("Do the checkOut(itemId)");
			}
			else if(answer == 1){
				//Replace with actual method
				System.out.println("Do the checkIn(itemId)");
			}
			
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}
	}
}
