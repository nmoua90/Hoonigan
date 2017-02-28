Quick Tutorial on How to Use Git 
------------------------------------
Fork this Repository onto your Git account by Clicking In the Upper Right Corner
	
Open GitBash and navigate to your directory of choice via the "cd" keyword
	
When you're in the Folder you want to use, Clone your Forked Directory by typing the following:

		git clone https://github.com/yourUserName/Hoonigan.git
	
Then, change your directory into the newly Cloned Folder, and sync the Folder with my Master Github Repository:

		cd directoryOfYourCloneFolder
		git remote add upstream https://github.com/nmoua90/Hoonigan.git
	
That's it... (Edit files as you please)
	
When you're done editing, you need to push the updates to your local repo by typing the following:

		git add .
		git commit -m 'I edited some files'

To push updates to your Github repo:

		git push origin master
		
To download the newest files from the lastest version of our Main Master branch:

		git pull upstream master
		
ADDITIONAL GIT TUTORIAL(S)
----------
PERSONAL TUTORIAL:
(https://github.com/nmoua90/Hoonigan/blob/master/Various%20Tutorials/Git%20Tutorial%20Readme/Tutorial.md)

HOW TO FORK:
(https://github.com/LearnFrontEnd/fork-me)

HOW TO UPDATE YOUR FORK USING GITBASH:
(https://gist.github.com/CristinaSolana/1885435)

HOW TO UPDATE YOUR FORK USING GITHUB WEBSITE:
(http://www.hpique.com/2013/09/updating-a-fork-directly-from-github/)