Hoonigan ReadMe
--------------------------------------------
FOR REFERENCE

(1) 'hoonigan.zip' contains source files & GSON jar

(2) 'documentation.zip' contains JavaDocs

(3) 'UML & Sequence Diagrams.zip' contains UML and Sequence Diagram of The HOONIGAN LIBRARY PROGRAM

--------------------------------------------
BACKGROUND INFORMATION

(1) The HOONIGAN LIBRARY PROGRAM runs on JAVA 

(2) The HOONIGAN LIBRARY PROGRAM source files are linked through a Package called 'hoonigan'

(3) To run the HOONIGAN LIBRARY PROGRAM, all java source files included in 'hoonigan.zip' file must be stored in your source folder under a corresponding 'hoonigan' directory

(4) The HOONIGAN LIBRARY PROGRAM uses GSON to parse incoming JSON files

(5) The required and appropriate GSON jar file needed for our library has been included within our 'hoonigan.zip' file
 
(6) The HOONIGAN LIBRARY PROGRAM was written using the ECLIPSE IDE

(7) JavaDocs DOCUMENTATION for the HOONIGAN LIBRARY PROGRAM can be founded in 'Documentation.zip'

--------------------------------------------
HOW TO RUN OUR PROGRAM

(1) Using a JAVA IDE, import our Zip File as an Archive File

(2) Import our included GSON jar file into your build path

(3) Open InitializeProgram.java in an IDE/Text Editor

(4) Navigate to the init() method

(5) Change the directory assignment of the variable 'reader', to your JSON file's directory location

	EXAMPLE: 
		reader = new BufferedReader(new FileReader("yourFileGoesHere.json"));

(6) Run the UI, by executing the Main Method found in LibraryUI.java