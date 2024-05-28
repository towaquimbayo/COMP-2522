# Lab-04
## Lab 04: Error Handling, Unit Testing, and Test-Driven Development
This lab is all about [error handling](https://www.w3schools.com/java/java_try_catch.asp), [unit testing](https://github.com/junit-team/junit4/wiki), and [test-driven development](https://www.ibm.com/garage/method/practices/code/practice_test_driven_development/). These are three separate but related ideas that this lab will help to explain and give you practice with.

The code today is very similar to the code from last week. However, there are a number of changes. And, since this lab release overlaps with other labs, I have not included any `Mouse` click or interaction logic. 

A big difference is that I have included a new `Exception` class and a suite of tests under `/test`. There is an example of fully-running tests under `BoundingBoxTest` for you to look at and use for your own solutions. There are also tests that should not yet pass under `AbstractCharacterTest`.

## TODO 1: Run all tests and `Window`.
Start simply by making sure that you can run the tests. Right click on the `test` folder to run all tests. You should see that two tests failed and nine passed.

Next, run `Window` and see what's different. You'll see that I've added an exception. Draw the call stack that handles the exception.

## TODO 2: Handle the `OutOfBoundsException` better.
Exceptions are the preferred way to implement error handling. The reason for this is that you can pre-specify the possible failure conditions for your code, and then decide what you would like to do about it. For example, a piece of `Food` going out of bounds is not a critical failure condition, so I chose to handle the error by simply printing to the console that an error had occurred.

However, there may be cases where a random `FoodCharacter` is instantiated within an illegal boundary and just get stuck throwing errors forever. Implement the logic to remove the `FoodCharacters` that throw the `OutOfBoundsException` in `Window`.

## TODO 3: Create a new `Exception`.
The `Tail` should really also not go out of bounds. Create an `OutOfBoundsException` class which extends `Exception`. Then, create a `TailOutOfBoundsException` which extends `OutOfBoundsException`. Then modify `CharacterOutOfBoundsException` to extend `OutOfBoundsException`.

You'll see that each class can pass along a string. Make a more verbose error message for each, so that debugging is easier. Pass arguments in the constructors for the subclasses. Full marks will be given for clear error messages that give enough detail that someone can debug easily.

NOTE: YOU DO NOT HAVE TO ACTUALLY MAKE THE TAIL NOT GO OUT OF BOUNDS. Just write an exception.

## TODO 4: Get the tests to run.
Now we'll do some test-driven development. The tests describe the behaviour that we'd like to see for the character. At a high level, we want the wall bounces to happen when the edge of the character touches the wall, not the middle. The tests as written give you the boundary conditions. Finish the implementation of `touchWall` such that all tests pass.

This is a common way of specifying code for developers. Although for the labs so far we've been using very visual classes, typically you just get a description of the inputs, outputs, and success/failure conditions. Test-driven development means that as long as the tests pass, you've written "correct" code.

## TODO 5: Write tests for simple bouncing behaviour.
As many of you have noticed, the bouncing behaviour for `FoodCharacters` is OK...but not great. This is a perfect time to use tests! Write tests that give specific values for the following cases of collision:
- One `FoodCharacter` is moving North, the other South.
- One `FoodCharacter` is moving East, the other West.

Start by drawing out the behaviour you expect. Then, determine the relevant `position` and `direction` vectors that will correspond to these collisions. Then determine the output direction vectors (there are only output direction vectors for collisions, not positions).

## TODO 6: Write tests for complex bouncing behaviour.
Now, do the same as *TODO 5* but with more complex cases:
- One `FoodCharacter` is moving North, the other East.
- One `FoodCharacter` is moving North, the other West.
- One `FoodCharacter` is moving South, the other East.
- One `FoodCharacter` is moving South, the other West.
- One `FoodCharacter` is moving North East, the other North West.

Add any other test cases you need to figure this out.

## TODO 7: Make your own tests run. 
Time to do some test-driven development on yourself. You're free to implement whatever you'd like in terms of adding/refactoring methods, as long as the original intent is preserved.

# Marking
- TODO 1: 5 points
- TODO 2: 5 points
- TODO 3: 5 points
- TODO 4: 10 points
- TODO 5: 15 points
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
