## Before We Begin 
In my opinion, the easiest way to use GitHub is with GitBash command line

(https://git-scm.com/downloads)
		

##USEFUL GIT COMMANDS:

		//Help commands
		git																//list basic commands
		git config														//list defult structures 
		git config user.email											//return registered email
		git help														//list help options
		clear															//clear screen
		git status														//check status of branch/commits
		
		//Directory commands
		pwd																//show current directory
		cd																//change directory
		cd ~															//cd to default home
		cd ..															//cd to previous folder
		cd nameOfDirectory												/cd to nameOfDirectory
		ls																//list files in current directory
		ls -la															//list hidden files in current directory
		
		//Folder commands
		git init														//initialize regular folder -> git folder
		mkdir nameOfDirectory											//create directory with nameeOfDirectory

		//Stage and Commit commands
		git add yourFile.txt											//add new changes of file to stage
		git add .														//add all new changes to stage
		git commit -m 'message'											//commit with message
		git commit -am 'Updated without using a stage'					//commit while skipping stage commands
		
		//Clone/Push/Remote Github commands
		git clone gitHubURL												//clone github repo to local repo
		git remote														//get remote connection name for current directory
		git remote -v													//get remote connection url for current directory
		git remote add origin gitHubURL									//add connection for github repo to local repo
		git push -u nickNameforRepo master								//push to github repo with nickname
		
		//Github master branch commands
		git push origin master											//push local changes onto github repo
		git pull origin master											//pull new github data onto local repo
		
		//User log commands
		git log															//get your commit logs
		git log --author="Other_Person"									//get someone else's commit logs
		
		//Restore previous version commands
		git log															//check your log for previous commits, find commit number of desired file
		commit putCommitNumberHere										//ready the old file
		git checkout commitNumberHere -- nameOfRestoredFile.extension	//check out the old file, verify name of old file
		git reset HEAD fileOnStage.extension							//clear the stage of a file
		git checkout -- yourOldFile.extension							//restore file not yet committed
		
		
		//Deletion, Renaming, Moving commands
		git rm nameoffile.txt											//delete file
		git mv fileToBeMoved.txt ThisIsAFolder/							//move file to this folder
		git mv fileToBeMoved.txt ThisIsAFolder/newName.txt				//move file to this folder AND rename
		git mv 2ndfile.txt anotherfile.txt								//rename file
		
		//Difference commands
		git diff														//return difference of all files
		git diff nameOfFile.txt											//return difference of one file
		git diff --staged												//return difference of file currently on stage
		

##REPOSITORIES: The Git Folders where you store files

For every REPOSITORY:

1. You will have one corresponding LOCAL REPOSITORY on your computer

2. You will have one corresponding GITHUB REPOSITORY on your Github Account
	
##FORKING: Copying SOMEONE ELSE'S GITHUB REPOSITORY and putting it on YOUR GITHUB REPOSITORY 

After you FORK, you will have a corresponding GITHUB REPOSITORY on your Github Account

However, you're still missing the matching LOCAL REPOSITORY

##CLONING: Taking all the files in a GITHUB REPOSITORY and copying them onto a LOCAL REPOSITORY

Clone into your local machine by navigating to directory of your choice and typing:
			
			git clone [Github_repository_url]
		
##PUSHING: Uploading your LOCAL FILES onto YOUR GITHUB REPOSITORY

When you have both of the following:

			1. LOCAL REPOSITORY

			2. GITHUB REPOSITORY

You can start editing files in your LOCAL REPOSITORY as needed

When done, you'll UPDATE YOUR LOCAL FILES, by typing the following:
			
			git add .
			git commit -m 'Add a message, that tells what updates you did.'
	
Then PUSH the local updates onto YOUR GITHUB REPOSITORY via:
			
			git push origin master

			ADDITIONAL GIT TUTORIAL(S)
			

##MORE TUTORIALS:

PERSONAL TUTORIAL:
(https://github.com/nmoua90/Hoonigan/tree/master/Various%20Tutorials/GitHub%20Tutorial%20Readme)

HOW TO FORK:
(https://github.com/LearnFrontEnd/fork-me)

HOW TO UPDATE YOUR FORK USING GITBASH:
(https://gist.github.com/CristinaSolana/1885435)

HOW TO UPDATE YOUR FORK USING GITHUB WEBSITE:
(http://www.hpique.com/2013/09/updating-a-fork-directly-from-github/)