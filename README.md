# Hoonigan

## TASKS
- [ ] Have all group members write their name in the HelloWorld.java file
- [ ] Learn how to use GIT
- [x] Edit the Header File 

## Quick Tutorial on How to Use Git
	> Fork this Repository onto your Git account by Clicking In the Upper Right Corner
	
	> Open GitBash and navigate to your directory of choice via the "cd" keyword
	
	> When you're in the Folder you want to use, type the following:
		* git clone https://github.com/nmoua90/Hoonigan.git
	
	> That's it... (Edit files as you please)
	
	> When you're done editing, type the following
		* git add .
		* git commit -m 'I edited some files'
		* git push origin master
	
	
## Useful GitBash commands [And In-Depth Tutorial]
	* IMO, easiest way to use GitHub is with GitBash command line
		* https://git-scm.com/downloads
		
	* This is how Git + GitHub works
	
	* REPOSITORIES = The Git Folders where you store files
		* When you have ONE REPOSITORY
			* You will have one corresponding LOCAL REPOSITORY on your computer
			* You will have one corresponding GITHUB REPOSITORY on your Github Account
		* It's useful to know that: You can create your own personal REPOSITORY 
		* It's useful to know that: You can FORK someone else's personal REPOSITORY
	
	* FORKING = Copy all the files in SOMEONE ELSE'S GITHUB REPOSITORY, and put those files onto a REPOSITORY ON YOUR Github Account
		* Cool stuff: After you FORK, you will have a corresponding GITHUB REPOSITORY on your Github Account
		* Remember though: We said that when you have a REPOSITORY, you need (1) LOCAL REPOSITORY and (1) GITHUB REPOSITORY
		* This means that after FORKING, you have (1) GITHUB REPOSITORY, but you're still missing the (1) LOCAL REPOSITORY
			* How do you fix this?
	
	* CLONING = Taking all the files in a GITHUB REPOSITORY and copy them into a LOCAL REPOSITORY on your machine
		* Hey, that solves our issues
		* So... how do you CLONE in GitBash:
			* Navigate to directory of your choice and type:
				* git clone [Github_repository_url]
			* The above command clones the GITHUB REPOSITORY URL onto your LOCAL REPOSITORY
	
	* Now that you have (1) LOCAL REPOSITORY and (1) GITHUB REPOSITORY
		* You can edit the files in your LOCAL REPOSITORY as you wish
		* When you're done editing your LOCAL FILES, you'll want to update them
		* In order to UPDATE YOUR LOCAL FILES, type the following:
			* git add .
			* git commit -m 'Add a message, that tells wht updates you did.'
		* After you've updated your local files, you might want to UPDATE YOUR GITHUB REPOSITORY files
		* This is when you PUSH your LOCAL REPOSITORY files onto GITHUB REPOSITORY (AKA: Update the ONLINE files -> to match your LOCAL files)
		* To do this, type:
			* git push origin master
	
	* That's it!