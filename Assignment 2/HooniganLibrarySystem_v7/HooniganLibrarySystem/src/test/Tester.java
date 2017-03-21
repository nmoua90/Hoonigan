package test;
import com.implementation.hoonigan.*;

public class Tester {
	public static void main(String[] args){
		BookItem book = new BookItem("Joe's Adventure", "BOOK", "8392de", "Joe King", 5);
		DVD dvd = new DVD("Joe's DVD", "Dvd", "9898fsd", 3);
		Magazine mag = new Magazine("Joe's Magmag", "magazine", "9898sdf", 5);
		CD cd = new CD("CD of JOE", "cd", "898", "Joe King", 5);
		CD cd2 = new CD("CD of JOE Volume 2", "cd", "898", "Joe Kings",1);
		
		//Equals method works
		System.out.println(cd.equals(cd2));		//should be true [equals has been overriden to check id = id]
		System.out.println(cd2.equals(mag));	//should be false
		
		//Testing setters
		System.out.println(dvd.getItem_id());	//should be 9898fsd
		dvd.setItem_id("90439");				//should change id
		System.out.println(dvd.getItem_id());	//should be 90439
		
		//Testing toString() + getters
		System.out.println(book);				//toString() still works [though we may not need in this implementation anymore]
		System.out.println(dvd);
		System.out.println(mag);
		System.out.println(cd);
		System.out.println(cd2);
	}
}

