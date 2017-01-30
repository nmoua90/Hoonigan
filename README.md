# Hoonigan 

TASKS
----------
- [ ] Have all group members write their name in the HelloWorld.java file
- [ ] Learn how to use GIT
- [x] Edit the Header File 

Quick Tutorial on How to Use Git 
------------------------------------
Fork this Repository onto your Git account by Clicking In the Upper Right Corner
	
Open GitBash and navigate to your directory of choice via the "cd" keyword
	
When you're in the Folder you want to use, type the following:

		git clone https://github.com/nmoua90/Hoonigan.git
	
That's it... (Edit files as you please)
	
When you're done editing, type the following

		git add .
		git commit -m 'I edited some files'
		git push origin master
	
	
## Useful GitBash commands [And In-Depth Tutorial] ##
In my opinion, the easiest way to use GitHub is with GitBash command line

		https://git-scm.com/downloads
	
##REPOSITORIES: The Git Folders where you store files

For every REPOSITORY:

1. You will have one corresponding LOCAL REPOSITORY on your computer

2. You will have one corresponding GITHUB REPOSITORY on your Github Account
	
##FORKING: Copying SOMEONE ELSE'S GITHUB REPOSITORY and putting them on YOUR GITHUB 

After you FORK, you will have a corresponding GITHUB REPOSITORY on your Github Account

However, you're still missing the matching LOCAL REPOSITORY

##CLONING: Taking all the files in a GITHUB REPOSITORY and copying them onto a LOCAL REPOSITORY

Clone into your local machine by navigating to directory of your choice and typing:
			
			git clone [Github_repository_url]
		
##PUSHING: Uploading your LOCAL FILES onto YOUR GITHUB REPOSITORY

When you have both:

1. LOCAL REPOSITORY

2. GITHUB REPOSITORY

You can edit the files in your LOCAL REPOSITORY as you wish

When you're done editing your LOCAL FILES, you'll want to update them

In order to UPDATE YOUR LOCAL FILES, type the following:
			
			git add .
			git commit -m 'Add a message, that tells wht updates you did.'
	
After you've updated your local files, you might want to UPDATE YOUR GITHUB REPOSITORY files

To do this, type:
			
			git push origin master
	
That's it!