/**
 * Created by Matthew on 3/19/2017.
 */
/**Library_Items abstract class
 *
 * An abstract class, which extends into four Subclasses: Magazine, Book, CD, DVD
 * @author Hoonigan
 *
 */

abstract class Library_Items {
    //default Constructor
    Library_Items(){
    }

    //typical Getters and Setters
    public abstract String getItem_name();
    public abstract void setItem_name(String item_name);
    public abstract String getItem_type();
    public abstract void setItem_type(String item_type);
    public abstract String getItem_id();
    public abstract void setItem_id(String item_id);

    //change checkout values
    public abstract void checkOut();
    public abstract void checkIn();

    //returns checkOut status of an item
    public abstract boolean isCheckedOut();

    //returns what library an item belongs to
    public abstract boolean isSisterLibraryItem();

    //returns returnDate of checkedOut items
    public abstract String returnDate();

    //returns String information about checkOut status
    public abstract String status();

    //returns the location information of an item
    public abstract String location();

    //Typical toString method
    @Override
    public abstract String toString();
}