# Lab-03
## Lab-03: Polymorphism
This lab is all about [polymorphism](http://underpop.online.fr/j/java/help/polymorphism-java-game.html.gz). As said in lecture last week, a lot of Java programming actually ends up being data design. This lab will help illustrate that.

Start by making sure you can run the file. Use the arrow keys to direct the red `PlayerCharacter`.

## TODO 1: Draw a UML Diagram of the classes in the file.
Using [UML syntax](http://websites.umich.edu/~eecs381/handouts/UMLNotationSummary.pdf), draw a diagram that represents the class structure in this lab. You'll note some special kinds of classes are present:
- `AbstractCharacter` is an abstract class. They have some specification, but certain methods are undeclared.
- `ICollidable` and `IDrawable` are interfaces. No methods are declared.

The difference between Abstract Classes and Interfaces may seem subtle at first, but they're used very differently. As you can see from the lab, the Abstract Class reduces code duplication in `PlayerCharacter` and `FoodCharacter`. Those subclasses only have to implement the methods for which they differ.

Interfaces may at first seem to encourage MORE code duplication. But they're not for reducing code duplication. Instead, note that logic such as this is possible only because we have interfaces:

```java
    for (ICollidable a : collidables) {
      for (ICollidable b : collidables) {
        if (a.collided(b)) {
          a.collideBehaviour(b);
        }
      }
    }
```

Rather than knowing anything about how each `ICollidable` is implemented, all we need to know is that they implement all of the methods promised by the interface. We can then have this:

```java
public class BoundingBox implements IDrawable {...}
```

Where a BoundingBox can be drawn, but not interacted with. Can you think of other examples of things that might appear on a screen, but the characters would not interact with it?

Let's take a moment to notice the coupling of the different classes:
- `PlayerCharacter` and `Tail` are tightly-coupled through a compositional relationship.
- `BoundingBox` is tightly coupled with `PVector` but loosely-coupled with `Window`. It is only coupled in the `draw` method, rather than needing a reference in a field. It is therefore highly-reusable.

## TODO 2: Make it so that food multiplies on collision.
This TODO teaches about the importance of the `private` and `public` access modifiers and when to use them. You'll note that they enforce **good design descisions** and as such are useful tools.

Start by modifying the `collideBehaviour` in `FoodCharacter`. You will want to instantiate a new `FoodCharacter` that has the same position and a different direction than `this`. Hint: use `PVector.copy()`.

Then add the new `FoodCharacter` to `Window` using the `addNotThreadSafeFood` method and run it. It should blow up. Why?

Now, modify to use the `addFood` method. Why does this not blow up?

Write down the answer to both "why" questions in a Javadoc comment in `collideBehaviour` in about 1-3 sentences.

## TODO 3: Create a `removeFood` method.
Now, copying the logic for `addFood`, add a `removeFood` method in `Window`. Run it to make sure that you aren't throwing any errors. 

## TODO 4: Make it so that food disappears on collision with the `PlayerCharacter`.
Using your above logic, now modify the `collideBehaviour` in `PlayerCharacter` such that the food is removed when the player collides with it, and the player also increases in size. 

## TODO 5: Add a `Mouse` class that implements `IDrawable` but not `ICollidable`.
Similar to the last Lab, we want to draw a `Mouse` object on the screen. But, unlike last Lab, this time we want it to be drawable but not bounce off of any of the onscreen elements. Make the `Mouse` draw a shape below the actual location of the mouse. Now, make it so that when the player clicks the mouse, the `PlayerCharacter` is reoriented towards the mouse. 

Hint: `PVectorA.add(PVectorB.mult(-1f)).normalize()` will give you a unit `PVector` that is pointing from `PVectorB` to `PVectorA` that you can use for direction.

## TODO 6: Create and modify classes that allow for interactions.
Design and implement a clicking system such that when you click on a `FoodCharacter`, it disappears. Use SOLID principles, aiming for loose-coupling and abstraction where appropriate. Points will be given for usability and good program design. Feel free to refactor anything you think is necessary for your design, but it's not necessary to refactor any bad design decisions that I've made (I'm sure there's a few). 

# Marking
- TODO 1: 20 points
- TODO 2: 10 points
- TODO 3: 10 points
- TODO 4: 10 points
- TODO 5: 15 points
- TODO 6: 20 points

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
