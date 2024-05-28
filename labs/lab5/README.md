# Lab 05
## Lab-05: Sequence Design
Lab 05 is all about tracing calls through a system. We'll use a combination of [UML Sequence Diagrams](https://en.wikipedia.org/wiki/Sequence_diagram) and [UML Communication Diagrams](https://en.wikipedia.org/wiki/Communication_diagram) to describe the different behaviours of the system. You'll add classes to the existing structure. Then you'll implement a version of the [Observer Design Pattern](https://en.wikipedia.org/wiki/Observer_pattern).

## TODO 1: Create a UML Communication Diagram
Describe the relationship between the different classes during a `draw` cycle. You can use `ctrl-click` to quickly navigate between method calls. For a communication diagram, you DO NOT need to write every method that is called, just the methods that communicate between objects. Show your diagram to Paul.

## TODO 2: Create a UML Sequence Diagram
Now that you have an overview of the different methods, just look at the sequence between a `Boid` and the `FlockingBehaviour`. You need to draw the lifecycle of every method for this diagram, but you do not need to include internal logic, and you ONLY LABEL the communication between classes. Like with every UML diagram, you can get too detailed if you include every possible method, so instead focus on the ones that are essential to the `FlockingBehaviour` working properly.

## TODO 3: Design a new concrete `HomeBehaviour`
Before starting to code, use any combination of communication and sequence diagrams to design a new behaviour. The new behaviour should extend `AbstractBehaviour` and make it so that the `Boid`s point towards their `Flock`'s `Home`.

## TODO 4: Implement `notifyObservers` and `update`
When you click on a `Flock`'s `Home`, it should toggle the `Flock` behaviour between `HomeBehaviour` and `FlockingBehaviour`. Use the [Observer Pattern](https://en.wikipedia.org/wiki/Observer_pattern) strictly for this. Note: you are allowed to add parameters to the `Observer` methods.

## TODO 5: Implement `registerObserver` and `unregisterObserver`
Now comes the hard part. Implementing un/registration will take some refactoring. Make it so that when a Boid touches a `Home`, it is unregistered from its current `Flock` and added to the touched `Flock`, and its colour and behaviour is also updated to match the flock. The requirements for this TODO is that you keep to the `Observer` pattern as if it is a distributed system, i.e., the only message-passing should follow the `notifyObservers` and `update` paradigm. You can create different notifications and updates if needed, but you can't make them more interdependent than the Observer pattern already requires. You are allowed to refactor anything that you need to as long as this requirement remains, as long as you keep with SOLID design principles.


# Marking
- TODO 1: 10 points
- TODO 2: 10 points
- TODO 3: 20 points
- TODO 4: 20 points
- TODO 5: 25 points

15 points are for style, although if style is poor enough to be unreadable for the previous TODOs, then points will be docked there as well.

## Submission Requirements

This lab must be completed and pushed to GitHub Classroom at or before 23:59:59 the night before lab.

I will mark the final commit pushed to GitHub before this time. Push often, and please make sure your commit comments are short and clear and specific. When you commit, please tell me EXACTLY what you did. Start with a verb in present tense. What does this commit do? Implement? Test? Debug? Add? Remove? Fix? Write? Complete? Refactor? Polish?

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
