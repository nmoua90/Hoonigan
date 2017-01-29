abstract class Library_Items {
    //Default constructor
    Library_Items(){
    }

    //Typical Getters and Setters
    public abstract String getItem_name();
    public abstract void setItem_name(String item_name);

    public abstract String getItem_type();
    public abstract void setItem_type(String item_type);

    public abstract int getItem_id();
    public abstract void setItem_id(int item_id);

    //if the book is in the library it is false if it is checked out then true
    public abstract boolean IsCheckedOut();

    /**
     * Return date for item is set when the item is checked out of library
     * check out period Books = 3 weeks , other items = 1 week
     */
     public abstract String returnDate();

    //Typical toString method
    @Override
    public abstract String toString();
}
