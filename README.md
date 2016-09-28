This repository contains the Java code for our 2016 competition robot.

### Prerequisites

There are several things that must be done one time before you can build the software in this team repository. Few if any of these are pre-installed on most computers, so unless you know otherwise you will likely need to install all of these. The [FRC documentation](https://wpilib.screenstepslive.com/s/4485/m/13503/l/145002-installing-eclipse-c-java) covers installing the JDK, Eclipse, and the WPILib plugsins.

1. **Install the JDK** - If you do not yet have the Java Developmemt Kit (JDK) version 8 installed, download and run the [official JDK 8 platform installer](http://www.oracle.com/technetwork/java/javase/downloads/index.html). This should set everything up on all platforms, and from a command line you should be able to ask the Java compiler (`javac`) to report its version by running `javac -version` and verifying it outputs something like `javac 1.8.0_66` (or newer). 
1. **Install Eclipse** - If you don't have Eclipse **Mars** installed, download and run the latest [Eclipse Standard installer](http://www.eclipse.org/downloads/). Only Mars is certified to work with the WPILib plugins.
1. **Install the WPILib Eclipse plugins** - Follow the instructions on the [FRC documentation](https://wpilib.screenstepslive.com/s/4485/m/13503/l/145002-installing-eclipse-c-java) page for "Installing the development plugins - Option 1: Online Install". This will install the WPILib Eclipse plugins into your Eclipse environment. Be sure to turn on Automatic Updates
1. **Install Git** - The easiest way to install Git is to follow [these instructions](https://help.github.com/articles/set-up-git). You can check it's installed correctly by opening a standard terminal (or the `Git Bash` terminal on Windows), typing `git --version`, and verifying the output is something like `git version 2.2.1` (or newer).
1. **Install Ant** - Ant is required to compile, run all unit tests, and deploy robot code from a terminal. First determine if you already have Ant installed: open a terminal and type `ant -version`. If that outputs a version of Ant, then it is already installed and you can skip the rest of this step. If it is not installed, then [download Ant](http://ant.apache.org/bindownload.cgi) and follow the installation instructions [for Windows](http://www.nczonline.net/blog/2012/04/12/how-to-install-apache-ant-on-windows/) or, on OS X, the easiest way is to [install Homebrew](http://brew.sh), open up a terminal, and type `brew install ant`. (If you have MacPorts installed, you can install Ant with `sudo port install apache-ant`.)
1. **Install Strongback** - [Strongback](http://strongback.org) is a library that makes it easier to write robot code that uses the WPILib for Java library and is easily unit testable. The [Using Strongback](https://www.gitbook.com/book/strongback/using-strongback/details) online book goes into a lot of detail about what Strongback is, how to [install and initialize it](https://strongback.gitbooks.io/using-strongback/content/getting_started.html), how to use its components, and how to write unit tests.
1. **Create account on GitHub.com** - You will need an account on [GitHub.com](http://github.com) and you will need to be added to the [FRC-4931 team organization on GitHub](https://github.com/frc-4931). You can request access for your new account by emailing `frc4931` at `gmail.com` and including your full name and GitHub username. 

### Getting the code

Once you have your development environment set up, you'll want to fork this repository (using the "Fork" button on this page at GitHub.com) and then clone your fork.

First, open a terminal and go to the directory in which you want the Git repository (a directory) to be placed.

```
$ git clone https://github.com/{your_username}/2016-Robot
$ cd 2016-Robot
$ git remote add upstream https://github.com/frc-4931/2016-Robot.git
$ git fetch upstream
$ git branch --set-upstream-to=upstream/master master
$ git pull upstream master
```

Your local Git repository is now connected to both your fork (e.g., `origin`) and the team's shared repository (e.g., `upstream`).

Start Eclipse, and choose *File->Switch Workspace->Other*, in the dialog pick the directory that contains the `2016-Robot` directory and click OK. Eclipse will switch to that workspace, and it should open up with no projects.

Then, choose *File->New->Other* and in the dialog type "Robot", then pick the new robot project, give the project a name, and when asked type in "4931" for the team number, and click okay. This will initialize the WPILib plugins for this workspace and set up a bunch of stuff. When that finishes, select the new project in the "Package Explorer" tab, right-mouse-click, and delete the project. (Unfortunately we have to do this every time we create a new Eclipse workspace so that the WPILib can properly initialize itself.)

Finally, go back to your Git shell (in the `2016-Robot` directory) and type on Windows:

    strongback.bat new-project -e -p com.myteam.robot -r MyRobotProject
    
or on OS X or Linux:

    strongback.sh new-project -e -p com.myteam.robot -r MyRobotProject

This command tells Strongback to set up the Eclipse workspace with the appropriate libraries.

Now, go back to Eclipse, and in the same workspace we created before (and created a temporary robot project) import the project(s) in your `2016-Robot` directory, as described [here](https://github.com/frc-4931/2014/wiki/Java#set-up-eclipse). You can either choose to _not_ import the `LiveMap` and `NetworkTableServer` projects, or if you import them you can remove them from your workspace.

Your Eclipse workspace should have a `CompetitionRobot` project and should show now errors.

### Build locally

Eclipse should try to automatically compile your code when it starts, and it will automatically compile in the background as you make changes. Eclipse provides tools to quickly run one or more unit tests, and you can even use Eclipse to deploy your code.

However, we encourage you to learn how to compile the code using a terminal:

    $ cd CompetitionRobot
    $ ant compile

You can compile and run all of the tests, too:

    $ ant clean test

and, when ready, deploy the code to the robot (if you're connected to the robot's network):

    $ ant deploy

Regardless of whether you compile and run tests within Eclipse or at a terminal, you are expected to compile and run *all unit tests* before creating a pull-request.

### Make your changes

To work with the softwareAs with the other repository, you'll follow the same [development process](https://github.com/frc-4931/2014/wiki/Java-Development-Steps) for each of the issues you'll work on. The basics are:

#### Create an issue

First, [create an issue](https://github.com/frc-4931/2016-Robot/issues) that describes your task.

#### Update your 'master' branch

```
$ git checkout master
$ git pull upstream
```

Make sure that everything compiles and all tests pass:

    $ ant clean test

Now you can make your changes using a topic branch.

#### Create a topic branch

A *topic branch* is where you do all your work associated with a specific topic (or issue). You'll have to pick a name that makes sense, but sometimes its easiest to base it on the issue number. For example, if your issue number is 999, then you might use "issue-999" for a topic branch:
```
$ git checkout -b issue-999
```

This command creates the new topic branch with that name, and checks you out onto that branch.

#### Make and test your changes for the issue

Make your changes to the code in Eclipse (which compiles automatically upon save), and optionally compile using the command line:

```
$ ant compile
```

If possible, test your changes on the robot (first make sure you're connected to the robot's network):

```
$ ant deploy run
```

#### Commit your changes

Once you're happy with your changes, commit them to the history on your (local) topic branch:

```
$ git commit -m "Issue 999 - A useful message that summarizes what you did" .
```

and then push your topic branch up to your fork:

```
$ git push origin issue-999
```

#### Create a pull-request

In your browser go to `https://github.com/{your_username}/2016-Robot` and create a pull-request for your topic branch. The mentors/reviewers will get a notification, and will review your code. See our [documentation](https://github.com/frc-4931/2014/wiki/Java-Development-Steps#step-8-push-to-github-and-create-a-pull-request) for more detail.

Then switch back to the `master` branch:

    $ git checkout master

If you are asked to make more changes to your PR, change to your branch:

    $ git checkout issue-999

then make the changes, run the unit tests, commit your changes (using a good description message), and push your changes:

    $ ant clean test
    $ git commit -m "Issue 999 - Additional changes as requested" .
    $ git push origin issue-999

This will automatically update the PR with your latest changes.

#### Get the latest code

As the team committers review and merge pull requests from you and other developers into the team's repository, you'll occassionally want to pull those changes from the team's repository (aka, "upstream") into your own local repository. First, make sure you're on the `master` branch, and then fetch all changes and pull the changes on `master` into your branch:

    $ git checkout master
    $ git pull upstream

Eclipse should automatically detect the changes and recompile, though you may want to run the unit tests to be sure everything works.

