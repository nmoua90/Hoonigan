----------------------------------------------------------------------------------------------------------------------------
HOONIGAN WINDOWS VERSION README
----------------------------------------------------------------------------------------------------------------------------

----------------------------------------------------------------------------------------------------------------------------
HOW TO RUN THE GUI AS ADMIN?
----------------------------------------------------------------------------------------------------------------------------

To login with Admin credentials, login with the following Username & Password:
	Username: admin 
	Password: admin 

----------------------------------------------------------------------------------------------------------------------------
WHAT'S IN THIS DIRECTORY?
----------------------------------------------------------------------------------------------------------------------------

This directory includes the following:
	(1) HooniganWindowsVersion.zip:
			- Contains Source Code for the project
			
	(2) JavaDoc folder:
			- Contains documentation for the project (In JavaDoc format)
			
	(3) Data Files folder:
			- Contains Test files used for the project
			
			- The JSON format found in version3LoadLibrary.json is the latest JSON format we support
				- Note: We still support our original JSON formats
				
	(4) HooniganWindows.jar
			- Runs our Software GUI without the need to compile
			
	(5) 20170426233451_STATE.json
			- A saved state of our previous Library's Catalog
			
	(6) 20170426233451_CLIENTS.json
			- A saved state of our previous Library's Users 
			
----------------------------------------------------------------------------------------------------------------------------
WHAT'S NEW?
----------------------------------------------------------------------------------------------------------------------------

	(1)	This version of Hoonigan Windows includes a new field for Library Items called "checkoutable"
			- checkoutable is a catch-all support term used to identify Items that are unshelved, lost, or reference only
			
			- Users should be aware to include the checkoutable field when writing JSON files <and to write the files with 
				the format found in version3LoadLibrary.JSON
					The format is found below:
						 {
							 - Optional Author/Artist field for certain Items 
							 "item_name":,
							 "item_type":,
							 "item_id":,
							 "returnDate":,
							 "checkedOut":",
							 "libraryID":,
							 "isLate":,
							 "fee":,
							 "uniqueQuantityID":,
							 "checkedOutBy":,
							 "status":,
							 "checkoutable":
						}
				
				- If an Item is not "checkoutable", members of the Library can reset the Item's status back to Checkable 
				  within the SearchResultsFrame.
			
	(2) Serialization for the State of the Library is now supported. Serialization occurs by writing all relevant metadata 
		to the local directory of the project, via two JSON files.
	
			(1) - The first JSON file: 20170426233451_STATE.json
					- Is a saved state of our previous Library's Catalog
					
			(2) - The second JSON file: 20170426233451_CLIENTS.json 
					- Is a saved state of our previous Library's Users 
						
			- Aside from metadata being saved, serialization is implemented by writing the exact time when Serialization 
			  occured, to the two output JSON filenames.
					- For example: "20170426233451"
					
			- Reloading of the two Serialized JSON files occurs simultaneously when a member loads a _STATE.json file
					- However, the two JSON files must be in the same Directory as the Project for the Program to recognize 
					  that they are Serialization files
					
----------------------------------------------------------------------------------------------------------------------------
END OF README 
----------------------------------------------------------------------------------------------------------------------------