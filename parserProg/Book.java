/**
 * Created by Matthew on 3/19/2017.
 */
import java.time.LocalDate;

/**Book class
 *
 * A simple Book class--extended from Abstract Library_Items class
 * @author Hoonigan
 *
 */
public class Book extends Library_Items{
    /** Invariant Comments
     * 	- item_name is initialized in the non-default CD Constructor. It's value can be changed, though changing it is not realistically supposed to be done.
     *	- item_type is initialized in the non-default CD Constructor. It's value can be changed, though changing it is not realistically supposed to be done.
     *  - item_id is initialized in the non-default CD Constructor. It's value can be changed, though changing it is not realistically supposed to be done.
     *  - item_author is initialized in the non-default CD Constructor. It's value can be changed, though changing it is not realistically supposed to be done.
     *  - returnDate is initially set to 'NONE'. When checkOut() is called, returnDate's value changes accordingly. returnDate's value is expected to be changed regularly as needed.
     *  - checkedOut is initially set to false, and is able to be set to either true or false depending on whether checkIn() or checkOut() modify it. checkedOut's value is expected to change
     *  regularly as needed, but cannot be changed if the changing value is already the current value. For example, if the value is false, trying to change the value to false will not yield an actual reassignment.
     */
    private String item_name;
    private String item_type;
    private String item_id;
    private String item_author;
    private String returnDate;
    private boolean sisterLibrary;
    private boolean checkedOut;

    /**Book()
     * Default Constructor
     */
    Book(){
    }

    /**Book(String name, String type, String id, String author)
     * Constructor which instantiates a Book object
     * @param name - name of Book
     * @param type - type of item
     * @param id - id of Book
     * @param author - artist of Book
     */
    Book(String name, String type, String id, String author){
        item_name = name;
        item_type = type;
        item_id = id;
        item_author = author;
        checkedOut = false;
        sisterLibrary = false;
        returnDate = "NONE";
    }

    /**Book(String type, String id, String author, String name, boolean sisterLibrary)
     * Constructor which instantiates a Book object from XML parser
     * @param type
     * @param id
     * @param author
     * @param name
     * @param sisterLibrary
     */
    Book(String type, String id, String author, String name, boolean sisterLibrary){
        item_type = type;
        item_id = id;
        item_author = author;
        item_name = name;
        checkedOut = false;
        this.sisterLibrary = sisterLibrary;
        returnDate = "NONE";
    }

    /**getItem_name()
     * Typical getter
     */
    public String getItem_name() {
        return item_name;
    }

    /**setItem_name()
     * Typical setter
     */
    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    /**getItem_type()
     * Typical getter
     */
    public String getItem_type() {
        return item_type;
    }

    /**setItem_type()
     * Typical setter
     */
    public void setItem_type(String item_type) {
        this.item_type = item_type;
    }

    /**getItem_id()
     * Typical getter
     */
    public String getItem_id() {
        return item_id;
    }

    /**setItem_id()
     * Typical setter
     */
    public void setItem_id(String item_id) {
        this.item_id = item_id;
    }

    /**getItem_author()
     * Typical getter
     */
    public String getItem_author() {
        return item_author;
    }

    /**setItem_author()
     * Typical setter
     */
    public void setItem_author(String item_author) {
        this.item_author = item_author;
    }

    /**isCheckedOut()
     * This method returns a boolean value based on whether an item is checked out
     * @return a boolean based on whether an item is checked out
     */
    public boolean isCheckedOut(){
        return checkedOut;
    }

    /**isSisterLibraryItem()
     * This method returns a boolean value based on whether an item belongs to the sister library or not.
     * @return a boolean based on whether an item belongs to the sister library or not.
     */
    public boolean isSisterLibraryItem(){
        return sisterLibrary;
    }

    /**checkOut()
     * This method sets the checkedOut field to true, resets the returnDate, and sets the returnDate as needed.
     */
    public void checkOut(){
        if(checkedOut == false){
            //If you're going to check it out, calculate the current day
            returnDate = LocalDate.now().toString();
            checkedOut = true;

            //Added x days to the current day, to get the return date
            returnDate = LocalDate.parse(returnDate).plusDays(21).toString();
        }
    }

    /**checkIn()
     * This method sets the checkedOut field to false.
     */
    public void checkIn(){
        if(checkedOut == true){
            checkedOut = false;
            returnDate = "NONE";
        }
    }

    /**returnDate()
     * This method returns a checkout date that corresponds to 21 days past the current day.
     * @return a String which represents a date
     */
    public String returnDate(){
        return returnDate.toString();
    }

    /**status()
     * This method returns a String based on the availability of an item
     * @return a string based on the availability of an item
     */
    public String status() {
        if(this.isCheckedOut()) {
            return "Checked out, due: " + this.returnDate;
        }
        else {
            return "Available";
        }
    }

    /**location()
     * This method returns a String based on which library the item belongs to
     * @return a string based on which library an item belongs to
     */
    public String location() {
        if(this.isSisterLibraryItem()){
            return "This item belongs to the sister library";
        }
        else
            return "This item does not belong to the sister library";
    }

    /**toString()
     * Typical overriden toString() method. Edited to fit with Text Based UI needs.
     */
    public String toString(){
        String line = String.format("ID: %s | TITLE: %s | TYPE: %s | AUTHOR: %s | STATUS: %s | LOCATION: %s",
                item_id, item_name, item_type, item_author, status(), location());
        return line;
    }
}
