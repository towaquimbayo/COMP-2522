# Lab-02
## Lab 02: Classes

This week, we'll be learning about writing Java classes. The technical definition of Java classes is [widely available on the internet](https://www.w3schools.com/java/java_classes.asp). Later, on your own time, I suggest going through a variety of internet resources to familiarize yourself with the documentation that's out there.

In Lab, we're practicing three important concepts:
- Pair programming
- Using libraries
- Reading and refactoring code

You'll be using classes and class definitions throughout the lab, so you'll become familiar as you use them. This is an example of "learning by doing." For most lab sections, this lab comes **before** the lecture. You'll try things, mess up, get confused, and have questions. Great! That's by design. I want you to come to lecture with the **embodied** experience. That makes our time in lectures much more effective.

## Pair programming
Last week during lecture, we started to practice [pair programming](https://martinfowler.com/articles/on-pair-programming.html). Today, you'll be using pair programming for the entire lab. 

Choose ONE other person to work with. One will be the Driver, the other will be the Navigator:

> The Driver is the person at the wheel, i.e. the keyboard. She is focussed on completing the tiny goal at hand, ignoring larger issues for the moment. A driver should always talk through what she is doing while doing it.
>
> The Navigator is in the observer position, while the driver is typing. She reviews the code on-the-go, gives directions and shares thoughts. The navigator also has an eye on the larger issues, bugs, and makes notes of potential next steps or obstacles.
[Source: On Pair Programming](https://martinfowler.com/articles/on-pair-programming.html)

During lecture, we practiced [reflective listening](https://www.indeed.com/career-advice/career-development/reflective-listening). Today, you don't need to exactly reproduce what the other person is saying. Instead, you should rephrase their statements in your own words to confirm that you understand. If you can't think of what to rephrase, fall back on what we practiced in lecture.

Here are the rules:

1) Drivers must talk through everything that they're thinking, planning, and doing.
2) Only drivers drive. Navigators must only provide help, advice, or feedback **after using reflective listening**. 
*If the driver seems confused and is not saying anything, prompt with a non-judgemental question or make an objective observation. For example, "you seem confused" is NOT an objective observation vs., "I've noticed that you've re-run the same code a few times without making changes," is INDEED an objective observation. You can follow up with a helpful question, e.g., "is there a variable you're trying to understand?"*
3) Stay focused on the task. Turn notifications off. It is FORBIDDEN to check your email, messages, browse, etc. while pair programming. [Set aside breaks at specific times to check your messages](https://todoist.com/productivity-methods/pomodoro-technique).

## TODO 1: Sync your local machine with this repository.
Choose one person to be the driver. You'll switch soon, so don't switch until the instructions say so. Clone this repository to your local machine.

## TODO 2: Add the Processing Jar to the classpath.
Navigate to [Processing.org](https://processing.org/download) and download the appropriate distribution for your system. Extract the files in a place that you can refer back to later. Then edit your `build.gradle` file to point to the Processing `core.jar` file:

```
dependencies {
    implementation files("C:\\Users\\<Your home folder>\\<Path to downloaded file>\\processing-4.0.1\\core\\library\\core.jar")
    testImplementation 'org.junit.jupiter:junit-jupiter-api:5.8.1'
    testRuntimeOnly 'org.junit.jupiter:junit-jupiter-engine:5.8.1'
}
```

Note that you may have different syntax for files if you're on a Unix-based system such as Linux or MacOS. Note that you may need to run the `clean` gradle command or [rebuild your caches](https://docs.gradle.org/current/userguide/build_cache.html) if you're getting import errors.

## TODO 3: Run the example code in `ProcessingWindow.java`

You should have a file called `ProcessingWindow.java` which looks like this:

```java
package org.comp2522;

import processing.core.PApplet;

public class ProcessingWindow extends PApplet{

  public void settings(){
    size(500, 500);
  }

  public void draw(){
    ellipse(mouseX, mouseY, 50, 50);
  }

  public void mousePressed(){
    background(64);
  }

  public static void main(String[] args){
    String[] processingArgs = {"processingWindow"};
    ProcessingWindow processingWindow = new ProcessingWindow();
    PApplet.runSketch(processingArgs, processingWindow);
  }
}
```

Try running it. Work through any errors you find. You should eventually see a window that looks like this:

![image](https://user-images.githubusercontent.com/3506567/188701367-2dfda1f1-26a8-4425-8e0b-c581c996816c.png)

Try changing some variables to understand what the code does. When you're confident that you understand the code, **you can switch driver and navigator. Redo TODO 1, 2, and 3 with the other person as a driver.**

## TODO 4: Understand and run `BouncyBubbles.java`.

Read through `BouncyBubbles.java` and try to understand what it's doing *at a high level*. Since `Ball.java` is referenced by `BouncyBubbles.java`, you'll have to read that through as well. With your partner, write down a high-level description of what the code does. Be succinct but precise. You should get something like this when you run:

![image](https://user-images.githubusercontent.com/3506567/188931649-96ee0647-16f4-4c96-94d2-179244924381.png)

## TODO 5: Refactor `Ball.java` to have pure Getter/Setter methods
Read through the `collide` method in `Ball.java`. You'll notice that there are two lines that break a fundamental rule of OOP: you should never directly modify the values of another object. Instead, we use "getter" and "setter" methods. Choose one person to be the driver, then change these lines and write getter and setter methods for the corresponding variables.

## TODO 6: Write an `equals` method, a `hashCode` method, and a `toString` method for `Ball.java`.
From here on out, you can switch drivers and navigators as needed.

Each class that stores state must also implement an equals method, a hashCode method, and a toString method.

The *equals* method must return true if two objects are considered equal. Consider what makes a `Ball` equal to another `Ball`.

The *hashCode* method must return an integer that represents the whole object. It must be unique for unique objects; contrapositively, it must be identical for identical objects.

The *toString* method must return a string representation of the object. It's best to make this both unique and useful.

## TODO 7: Create a new class for handling mouse interactions.
For this TODO, we are going to walk through a small version of the program design process. Your high-level goal is to write a class that handles mouse interactions with the bubbles. How exactly this works will be up to you.

1) Start by familiarizing yourself with the mouse functions in `ProcessingWindow.java`. 
2) Add a line or two to the `draw` method in `BouncyBubbles.java` to test these functions in your new environment. Do something like visualize the (x, y) position of the mouse.
3) Create a new class called `Mouse.java` and move your test code into the class.

Now you have some design decisions to make. Is a `Mouse` a `Ball`? Is a `Ball` a `Mouse`? Are they both subclasses of a not-yet-known, secret third class called `Collidable`? Discuss the different approaches and what the advantages of each would be. Consider which design follows [SOLID principles](https://www.digitalocean.com/community/conceptual_articles/s-o-l-i-d-the-first-five-principles-of-object-oriented-design). Draw them on paper first and show them to Paul.

Now start to code. Your requirements are:
1) The `Mouse` must be visible and interact with all other bouncy balls. 
2) The `Mouse` must follow the user's mouse when the user's mouse is on screen.
3) SOLID principles must be used.
4) Style guide must be followed (except for `ProcessingWindow.java` which you shouldn't modify). They'll seem like a lot today. But don't worry, they're actually quite easy to get into the rhythm of doing. Note that this includes implementing the `equals`, `hashCode` and `toString` methods for any class that stores state.

## Marking

TODOs 1-5 are 5 points each (for completion). TODO 6 is 15 points (one per function for completion). TODO 7 is 35 points and will be marked for completion as well as the effectiveness of your design choices. 15 points are for style, although if the style is poor enough for one of the previous TODOs, then further marks may be taken off.

## Submission requirements
This lab must be completed and pushed to GitHub Classroom at or before 23:59:59 the night before lab.

I will mark the final commit pushed to GitHub before this time.
Push often, and please make sure your commit comments are short and clear and specific. 
When you commit, please tell me EXACTLY what you did. Start with a verb in present tense. What does this commit do? Implement? Test? Debug? Add? Remove? Fix? Write? Complete? Refactor? Polish?

You must not ignore Checkstyle warnings or the code style warnings produced by IntelliJ.

You must style your code so that it does not generate any warnings.

When code is underlined in red, IntelliJ is telling us there is an error. Mouse over the error to (hopefully!) learn more.

When code is underlined in a different colour, we are being warned about something. We can click the warning triangle in the upper right hand corner of the editor window, or we can mouse over the warning to learn more. If the warning seems silly, tell me about it, and I will investigate and possibly ask the class to modify some settings to eliminate it once and for all!

## Code style requirements that you must observe:
1. Your code must compile.
2. Your code must not generate any IntelliJ code warnings or problems.
3. Your code must execute without crashing.
4. Your code must not generate any Checkstyle complaints (unless they are complains I have specifically said you can ignore).
5. Don’t squeeze your code together. Put a blank space on either side of your operands, for example. I will be assessing the readability and clarity of your code.
6. All of your program classes must be in package ca.bcit.comp2522.xxx (replace xxx as required by the assignment, lab, quiz, etc.). For example, today’s work should go in the ca.bcit.comp2522.labs.lab0X package.
7. All classes require Javadoc comments including the @author tag AND the @version tag. Class comments go after the package and import statements. There should be no blank lines between a class comment and the class it describes.
8. Public constants require Javadoc comments, private constants do not. Constants should be the first thing in your class.
9. Constants should be static and final, are often public, and should be followed by instance variables.
10. Instance variables have private or protected visibility, never public or default visibility.
11. Public and protected methods require Javadoc comments including @param tag(s)the @return tag, and the @throws tag where needed (we won’t worry about throws until we talk about exceptions in depth).
12. A method’s comment must begin with verbs describing what the method does, i.e., Calculates, Returns, Sets, Prints, etc. Note that we use present tense in Java – Returns, not Return. Prints, not Print.
13. The @return and @param tags go AFTER the description.
14. Private methods require non-Javadoc comments (the comments that start with a slash and a single asterisk).
15. Do comment complicated logical blocks of code inside methods with sparse, clear inline comments.
16. Do not use magic numbers (you must use constants instead). Remember that a magic number is any numeric literal. A constant can be local in a method (use the final keyword with it) or class-level (make it static and ALL_CAPS).
17. All method parameters that are object references must be made final (so we don’t forget parameters are passed by value):
(a) Nice to prevent erroneous assignments, and necessary if parameter is referenced by inner class, but that is perhaps a little advanced for now.
(b) References made final mean that the reference, once pointing to an object, cannot be changed to point at a different object.
18. Consider making your methods final:
(a) Making a method final prevents subclasses (those that inherit the method) from changing its meaning.
(b) Final methods are more efficient (the methods become inline, thus avoiding the stack and generating overhead).
19. Data and methods that work together must be encapsulated in the same class.
20. Code duplication must be minimized.
21. The values of local variables that are primitives are set when they are declared, and local variables are not declared until they are needed.
22. Every class that stores state needs an equals method, a hashCode method, and a toString method.
23. In general, we enforce a fairly hard maximum method length of 20 lines of code. Aim for 10 (excluding braces).
