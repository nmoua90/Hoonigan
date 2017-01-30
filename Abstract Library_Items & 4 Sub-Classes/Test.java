import java.time.LocalDate;

/**
 * This class tests our 4 main classes + Abstract Library_Items class
 * 		- Using the inputs from our assignment sheet as TEST INPUTS, I tested out classes. They all work as expected.
 * 		- Look over the results to double-check.
 * @author Nhia
 *
 */
public class Test{
	public static void main(String[] args){
		//Test for CD
		CD beatles = new CD("Yellow Submarine","CD","48934j","Beatles");
		System.out.println("Item Name: " + beatles.getItem_name());
		System.out.println("Item Type: " + beatles.getItem_type());
		System.out.println("Item ID: " + beatles.getItem_id());
		System.out.println("Item Artist: " + beatles.getItem_artist());
		System.out.println("CD Return Date = 7 days after this one: " + beatles.returnDate());
		System.out.println("Checkout should be false: " + beatles.isCheckedOut());
		beatles.checkOut(); //beatles checked out: makes checkedOut = true
		System.out.println("Checkout should be true: " + beatles.isCheckedOut());
		beatles.checkIn(); //beatles checked back in: makes checkedOut = false
		System.out.println("Checkout should be false: " + beatles.isCheckedOut() + "\n");
		
		//Test for Book
		Book monteCristo = new Book("The Count of Monte Cristo","Book","1adf4","Alexandre Dumas");
		System.out.println("Item Name: " + monteCristo.getItem_name());
		System.out.println("Item Type: " + monteCristo.getItem_type());
		System.out.println("Item ID: " + monteCristo.getItem_id());
		System.out.println("Item Author: " + monteCristo.getItem_author());
		System.out.println("Book Return Date = 21 days after this one: " + monteCristo.returnDate());
		System.out.println("Checkout should be false: " + monteCristo.isCheckedOut());
		monteCristo.checkOut(); //monteCristo checked out: makes checkedOut = true
		System.out.println("Checkout should be true: " + monteCristo.isCheckedOut());
		monteCristo.checkIn(); //monteCristo checked back in: makes checkedOut = false
		System.out.println("Checkout should be false: " + monteCristo.isCheckedOut() + "\n");
				
		//Test for Magazine
		Magazine cat = new Magazine("Cat Fancy v23","Magazine","1a545");
		System.out.println("Item Name: " + cat.getItem_name());
		System.out.println("Item Type: " + cat.getItem_type());
		System.out.println("Item ID: " + cat.getItem_id());
		System.out.println("CD Return Date = 7 days after this one: " + cat.returnDate());
		System.out.println("Checkout should be false: " + cat.isCheckedOut());
		cat.checkOut(); //cat checked out: makes checkedOut = true
		System.out.println("Checkout should be true: " + cat.isCheckedOut());
		cat.checkIn(); //cat checked back in: makes checkedOut = false
		System.out.println("Checkout should be false: " + cat.isCheckedOut() + "\n");
		
		//Test for DVD
		DVD tStory = new DVD("Toy Story","DVD","85545");
		System.out.println("Item Name: " + tStory.getItem_name());
		System.out.println("Item Type: " + tStory.getItem_type());
		System.out.println("Item ID: " + tStory.getItem_id());
		System.out.println("CD Return Date = 7 days after this one: " + tStory.returnDate());
		System.out.println("Checkout should be false: " + tStory.isCheckedOut());
		tStory.checkOut(); //toy story checked out: makes checkedOut = true
		System.out.println("Checkout should be true: " + tStory.isCheckedOut());
		tStory.checkIn(); //toy story checked back in: makes checkedOut = false
		System.out.println("Checkout should be false: " + tStory.isCheckedOut() + "\n");	
	}
	
	
}

