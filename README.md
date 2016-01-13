This repository contains the Java code for our 2016 competition robot.

### Get set up

If you already have [your development environment](https://github.com/frc-4931/2014/wiki/Java) set up (e.g., installed Git, Java 8, Eclipse, Ant, etc), you'll want to fork this repository (using the "Fork" button on this page at GitHub.com) and then clone your fork.

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

Import the project(s) in your `fork-2016-Robot` directory into Eclipse, as described [here](https://github.com/frc-4931/2014/wiki/Java#set-up-eclipse).

### Make your changes

As with the other repository, you'll follow the same [development process](https://github.com/frc-4931/2014/wiki/Java-Development-Steps) for each of the issues you'll work on. The basics are:

#### Create an issue

First, [create an issue](https://github.com/frc-4931/2016-Robot/issues) that describes your task.

#### Update your 'master' branch

```
$ git checkout master
$ git pull upstream
```

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
