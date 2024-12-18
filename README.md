# ZenTask

## Authors of the Project 
1. Iguehi Akhimien - GitHub username: iguehi.akhimien
2. Jenna Zhang - GitHub username: stickycorner 
3. Prisha Patel - GitHub username: PrishaPatel24
4. Liza Kochel - GitHub username: lizakochel

## Summary of the Project
This project focuses on building a productivity app that allows the user to take notes, make a checklist list, and add events to their calendar.
This project was made to serve students, working professionals or anyone who want to better manage life.
Life can be hectic. Our goal is to create peace for the user, allowing them to manage life from one point. 
From the onset of the project, the vision was to find a way to make a student's productivity convenient and all in one place.
So, the authors of the project created a "one-stop" life-management dashboard. 

### User Benefits:
* As a user, you can create a note within the program (although not save it permanently) to probably note down things you do not want to forget. 
To help you, we have included a couple of features, such as translating text to a preferred language and completing your notes using Artificial Intelligence.
* As a user, you can also make a checklist of all your important tasks or things you do not want to procrastinate.
To keep track of things, you can tick off tasks as you go about completing them.
* As a user, you can see your upcoming events at a view while planning to complete your tasks on your checklist or when you are planning time to revisit what you noted down. 

**ZenTask brings everything together!**

## Table of Contents
1. Features of the Software ([Jump to Features of the Software](#features-of-the-software))
2. Installation Instructions ([Jump to Installation Instructions](#installation-instructions))
3. Usage Guide ([Jump to Usage Guide](#usage-guide))
4. License ([Jump to License](#license))
5. Feedback ([Jump to Feedback](#feedback))
6. Contributions ([Jump to Contributions](#contributions))


## Features of the Software
There are three major components of this software: Calendar, Checklist and Notes.
So, upon running the program, the user first sees "Welcome to your Dashboard" 

![Click here](images/dashboard.png) 

On the left of the Dashboard, the user sees three tabs: Calendar, Notes and Checklist.

### Calendar
The user can easily migrate to the Calendar tab by clicking on it. 

![Click here](images/navigate_calendar.png) 

Now, the user is on the Calendar tab. They are prompter to log in their __Google Account__. 

![Click here](images/view_calendar.png)

This displays the upcoming events the user has added in on their Google calendar.
The details displayed are the event summary, location, start time, and description.

__Note regarding Calendar Usage:__ Since we are unable to release our private project credentials, you should add your
own service account credentials in a json file somewhere in the project and add the path to the file in the CalendarRequest section.
Change the SERVICE_ACCOUNT_KEY_FILE attribute to the path and the calendar should be properly
integrated to use your new own Google project credentials.

### Notes
The user can also go to the Notes tab by clicking the menu on the left of their view. 

![Click here](images/navigate_notes.png)

Now, the user is on the Notes tab.
On the left side of the user's view, they can create a new note by simply typing into the provided area. 

![Click here](images/create-note.png)

The user also has the option of uploading text from an already existing file. 

![Click here](images/uploadnote.png)  

As additional functionalities, the user can translate existing text on their notes to either French, Spanish, Russian.
Also, the user can prompt AI to complete the note based on what the user has written. 

![Click here](images/translateandAI.png)

### Checklist
The user can move to the Checklist tab by clicking the menu on the left of their view. 

![Click here](images/navigate_checklist.png)

Now, the user is on the Checklist tab. 
On the user's view, the user will see add task. 

![Click here](images/view_checklist.png)

This will allow the user to make a new checklist or add new tasks to an already existing checklist.


## Installation Instructions
__Note:__ the program can be run on any operating systems.
As the user, you should ensure that their IDE is working and already setup. If you need any help setting up, 
[visit here](https://www.jetbrains.com/help/idea/getting-started.html). The program can be run on any OS.
To run the program, there are two options:
1. __Through the IDE:__
   1. First, you have to clone the repo, by using [this URL](https://github.com/PrishaPatel24/ZenTask.git).  
   2. Then, run the MainNoteApplication found in the src/main/java/app of the project through the IDE.
2. __Through the terminal:__
   1. First, you have to clone the repo, by using [this URL](https://github.com/PrishaPatel24/ZenTask.git). 
   2. Navigate to the directory where the MainNoteApplication is on, in your File Manager 
   (ensure the directory is like .../ZenTask/src/main/java/app). 
   3. Then, open the terminal there. 
   4. In you terminal, compile the program by using `javac MainNoteApplication.java`
   5. Then, run the application, using `java MainNoteApplication`
   6. You should see your Dashboard. 
    ![View](images/dashboard.png)

## Usage Guide
To use the software, the user should navigate the tabs using the menu found on the left of the view. 

![View](images/menu.png)

## License
To view the License consistent with the details and use of this program, please refer to the file name **LICENSE** in the project directory.

## Feedback
As a user, you can give feedback on the software, using [this link](https://forms.gle/mA2Q9dh3G84v8RdW7).
Please, take your time to carefully fill out the form and should expect a follow-up with 2-3 business days.

## Contributions
All contributions to the project are welcome.
To contribute to the project, you have to follow these steps:
1. Make a fork of the [project](https://github.com/PrishaPatel24/ZenTask) on GitHub by right-clicking _Fork_ found on the top right of the GitHub page.
Then, create a new fork.

![View](images/create-fork.png)

3. Then, clone the repo and work from your IDE.
4. Do not forget to add a branch protection rule, especially if you are working with other people. Ensure everyone has their branch. 
Then, they will need to make a pull request on your own fork of the repository ([do this through the IDE](https://www.jetbrains.com/help/idea/work-with-github-pull-requests.html#create-pull-request)), 
explaining in detail what changes they have made, and perform a [code review](https://swimm.io/learn/code-reviews/ultimate-10-step-code-review-checklist).
5. To add your contributions to this project, you have to make a merge request. 
6. To make a merge request, first navigate to the back to [this project's repository](https://github.com/PrishaPatel24/ZenTask).
7. Then, on top of the GitHub page, click on _Pull Requests_.
   
    ![View](images/pull-request.png)
   
9. Click on the green button on the left of the GitHub page to create a _New pull request_.
    
    ![View](images/new-pull-request.png)
   
11. Then, set the base repository to [this project's repository](https://github.com/PrishaPatel24/ZenTask) and set base to _main_.
12. Similarly, set the head repository to be your fork of the repository and set base to main.
13. Then, click on the green button, _Create pull request_.
    
    ![View](images/create-pull-request.png)
    
15. In the pull request, add a title that properly summarizes the content of the "contribution". 
Also, add a proper description that contain what changes you made or things you added, what files your worked on and why this contribution is meaningful and beneficial. 
