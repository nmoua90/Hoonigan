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

When you have both a:

**1. LOCAL REPOSITORY**

**2. GITHUB REPOSITORY**

Edit files in your LOCAL REPOSITORY as needed

When done, you'll want to save your changes

In order to UPDATE YOUR LOCAL FILES, type the following:
			
			git add .
			git commit -m 'Add a message, that tells wht updates you did.'
	
After you've updated your local files, PUSH the local updates onto YOUR GITHUB REPOSITORY

To do this, type:
			
			git push origin master
