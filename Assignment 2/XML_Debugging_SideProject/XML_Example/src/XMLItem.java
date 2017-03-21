
public class XMLItem {
	private String name;
	private String author;
	private String artist;
	private String type;
	private String itemID;
	private String volume;
	private int libraryID;
	
	public XMLItem(String name, int libraryID){
		this.name = name;
		this.libraryID = libraryID;
	}
	
	
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public int getLibraryID() {
		return libraryID;
	}


	public void setLibraryID(int libraryID) {
		this.libraryID = libraryID;
	}


	@Override
	public String toString(){
		return(String.format("Name: %s\n Library ID:%d\n", name, libraryID));
	}
}
