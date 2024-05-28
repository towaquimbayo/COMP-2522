# Lab-01
## Lab 01: Getting started

Welcome to a new term! If you haven't yet, it's time to start you programming setup. This page will introduce you to our course technologies. Importantly, it also will introduce you to the documentation you'll need to start solving your own build problems.

The idea is for you to try this on your own first. It's important to develop your own problem-solving skills early. Take note of what you, personally, need to learn. It will be different for everyone: I'm a build-first-and-ask-questions-later kind of person, but many people are not.

### TODO 1: Install IDE and JDK.
Download and install the [Intellij IDE](https://www.jetbrains.com/idea/) and the latest [Java SDK](https://www.oracle.com/java/technologies/downloads/) (version 17 or 18 should be fine).

### TODO 2: Create a GitHub Repository.
If you're reading this, you should already have a GitHub account. Sign in and [follow the instructions here](https://docs.github.com/en/repositories/creating-and-managing-repositories/creating-a-new-repository) to create a new repo (short for repository). Keep your repo empty for now.

### TODO 3: Create a Gradle Project in Intellij.
Gradle is a build system that we will be using for the term. It manages many of the configuration files that are needed to manage software deployments.

Follow **Step 1** of the [Getting Started with Gradle guide](https://www.jetbrains.com/help/idea/getting-started-with-gradle.html). You can follow more than Step 1 if you'd like to learn more about Gradle, but it is not required for the lab. **Make sure that "Create Git Repository" is checked**. Initialize your repo with starter code.

### TODO 4: Connect your repo to GitHub.
Connect the remote GitHub repository to your local Git repository. Note the difference: GitHub is a hosting platform for remote repositories whereas Git is the Version Control System (VCS) that we're using. [Follow the guide here](https://www.jetbrains.com/idea/guide/tutorials/creating-a-project-from-github/adding-updating-remotes/).

### TODO 5: Refactor your package name.
Following the [instructions here](https://www.jetbrains.com/help/idea/rename-refactorings.html), refactor your package name to `comp2522`.

### TODO 6: Push a unique "Hello, world!" greeting.
In the `Main.java` under the `main` method, change the "Hello, world!" greeting to something unique, such as your favourite animal or baseball team. Add to your current commit and push to the remote branch by [following these instructions](https://www.jetbrains.com/help/idea/commit-and-push-changes.html). Always make sure to write an informative commit message!

Ensure that your change is reflected on your remote repository.

### TODO 7: Install and use helpful plugins.

[Learn how to install plugins here](https://www.jetbrains.com/help/idea/managing-plugins.html). Install the following:
+ Checkstyle-IDEA by Jamie Shiell. Install it, and reboot IntelliJ if required.
+ SpotBugs by Tagir Valeev. Install this too, and reboot IntelliJ if required.
+ PMDPlugin by Amit Dev. Install it and (you guessed it) reboot if required.

### TODO 8: Fix style errors.

![checkstyle](https://user-images.githubusercontent.com/3506567/187993163-4bbedf60-30d3-445b-9c15-041061b9f765.png)

Now run CheckStyle using the Google Checks rules. You should see a few style errors show up. Fix them. You'll have to add a comment like this:

```java
/**
* Your comment here.
*/
```

Change the comment to something more descriptive.

You may need to [update the indention settings](https://www.jetbrains.com/help/idea/reformat-and-rearrange-code.html#tabs_and_indents). Style is very important in coding and following style is **absolutely mandatory** in this class. Check out this resource if you want to learn more about [Google style](https://google.github.io/styleguide/javaguide.html).

### Grading Scheme (out of 100)
This lab will be checked visually at the beginning of the next lab. Each TODO is worth 12.5 points for a total of 100.
