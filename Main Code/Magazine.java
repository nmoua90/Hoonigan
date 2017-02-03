import java.time.LocalDate;

/** Magazine
 * 
 * A simple Magazine class--extended from Abstract Library_Items class
 *
 */

public class Magazine extends Library_Items{
	//Various fields
	private String item_name;
	private String item_type;
	private String item_id;
	private String returnDate;
	private boolean checkedOut;
	
	//Default constructor
	Magazine(){
	}
	
	//Constructor
	Magazine(String name, String type, String id){
		item_name = name;
		item_type = type;
		item_id = id;
		checkedOut = false;
		returnDate = LocalDate.now().toString();
	}

	//Typical Getters and Setters
	public String getItem_name() {
		return item_name;
	}

	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}

	public String getItem_type() {
		return item_type;
	}

	public void setItem_type(String item_type) {
		this.item_type = item_type;
	}

	public String getItem_id() {
		return item_id;
	}

	public void setItem_id(String item_id) {
		this.item_id = item_id;
	}
	
	//Returns corresponding boolean value based on if Object is checked out
	public boolean isCheckedOut(){
		return checkedOut;
	}
	
	//Sets checkout value to true if checked out
	public void checkOut(){
		if(checkedOut == false)
			checkedOut = true;
	}
	
	//Sets checkout value to false if checked out
	public void checkIn(){
		if(checkedOut == true)
			checkedOut = false;
	}
	 
	//Sets returnDate to currentDate + 7 days
	public String returnDate(){
		return LocalDate.parse(returnDate).plusDays(7).toString();
	}
	
	@Override
	public String toString(){
		String line = String.format("%s, %s, %s, %s, %b", item_name, item_type, item_id, returnDate, checkedOut);
		return line;
	}
}
