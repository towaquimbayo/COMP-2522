# Lab-06
## Lab 06: Interactivity

For this lab, you'll take your first shot at coding your own game. You're allowed to use anything directly from your own implementation of previous labs (or code that I've provided). The lab is also designed to be an exercise in using a good number of the concepts that we've addressed in lecture so far, therefore should be good practice for the midterm.

The basic idea is: you have a `Player` and `Enemies`. The `Player` can only eat an `Enemy` if the `Player` is stronger than the `Enemy`. Every so often, all `Enemy`s grow in strength in order of distance from the `Player`. You win by eating all the `Enemies`.

## TODO 1: Implement the following UML Diagram
Your code must, at a minimum, implement the following UML Diagram. You are free at add methods, attributes, super/subclasses, but whatever you add shouldn't violate the basic structure shown here. Don't worry about the inner logic yet, just focus on the structure.

![UML Diagram](https://github.com/COMP2522/Lab-06/blob/d10ddea13ec06c2603b21b89b0e049fe5df921ab/gfx/Lab06.drawio.png)

## TODO 2: Make the `Player` a Singleton.
Make the `Player` follow the [Singleton](https://en.wikipedia.org/wiki/Singleton_pattern) design pattern. This ensures that there is only ever one copy of the `Player` on screen.

## TODO 3: Make a `draw()` method
Using your best polymorphic design, create a `draw()` method to render the `Player` and the `Enemy`s. Top points for concise code that follows SOLID polymorphic design principles.

## TODO 4: Make the `Player` move via user interaction.
Choosing either mouse or keyboard input, make the player responsive to user input.

## TODO 5: Implement `Comparable` in `Player` and `Enemy`.
Using SOLID principles, make it so that you can directly compare a `Player` and an `Enemy`. You may need to introduce more classes to do this properly. Make sure that the `compareTo` method from `Comparable` is the way you are achieving the comparison.

Then, using `compareTo`, make it so that `Player < Enemy` results in a restart of the game, and `Player >= Enemy` results in the `Player` gaining a unit of strength (however you've defined it).

## TODO 6: Implement `Iterable` in `EnemyCollection`.
Now implement the functionality for each `Enemy` growing in strength relative to the distance from the `Player`. The idea with this is that you should implement `Iterator` and `Iterable` such that the distance metric is used to impose an order on the `EnemyCollection`. You'll also have to create an `Iterator` within `EnemyCollection` to achieve this.

## TODO 7: Implement the `move()` method for `Enemy` using `Observer` and `Observable`
The `Player` should broadcast its position to each `Enemy` within a small radius. Each stronger `Enemy` should move towards the `Player` and each weaker `Enemy` should avoid the `Player`. Achieve this via the [Observer pattern](https://en.wikipedia.org/wiki/Observer_pattern). The default `Enemy` behaviour is up to you.


# Marking
- TODO 1: 5 points
- TODO 2: 5 points
- TODO 3: 10 points
- TODO 4: 10 points
- TODO 5: 10 points
- TODO 6: 20 points
- TODO 7: 25 points

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
