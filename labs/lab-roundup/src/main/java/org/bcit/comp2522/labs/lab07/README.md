# Lab-07
Today is the project kick-off. We'll start with a small parsing exercise for the first half, then move into project ideation for the second half. You will likely not complete all of the TODOs before the project kick-off begins.

## TODO 1: Create a new class for parsing a single JSON string.

```
String str = "{ \"name\": \"Alice\", \"age\": 20 }";
JSONObject obj = new JSONObject(str);
String n = obj.getString("name");
int a = obj.getInt("age");
System.out.println(n + " " + a);  // prints "Alice 20"
```

The above is an example of how [JSON parsing](http://theoryapp.com/parse-json-in-java/) works. Your job will be to recreate a small version of the above `JSONObject` function. JSON is a standard for defining objects that is used by many programming languages, including Javascript, Java, and Python. We'll be using a version of JSON which is a little lighter. You have key-value pairs which can be strings or numbers:

```
{
  "key1": "value",
  "key2": 1,
  "key3": 22.99
}
```

And you can have arrays of primitive values:

```
{
  "my_int_array": [1, 2, 3, 4],
  "my_float_array": [1.0, 2.3, 4.4],
  "my_string_array": ["hello", "world"]
}
```

Define a new class called `MyJSONParser`. You'll start with making a parser which simply takes `String str = "{ \"name\": \"Alice\", \"age\": 20 }";`, has the same methods (`getString` and `getInt`) produces the same output as above.

There are special characters you'll have to watch out for in the above string:
```
{}
""
:
,
```
Write out the rules for parsing on a piece of paper. Get the checked by Paul. You can assume for now that `Object`s do not contain other `Object`s, and that you don't need to worry about arrays or floats. For example, when you see the `{`, you know that the parsing has begun and you should probably initialize a key-value store of some kind.

## TODO 2: Loop through the string character by character.
Now build your object character-by-character from the above string according to your parsing rules (i.e., you can't use pattern-matching). It's OK to not yet generalize your code. You must read one character at a time from the string.

## TODO 3: Generalize your code.
Now update your code to accept any number of key-value pairs from a given string, where the keys are only `String`s and the values are only `String`s or `int`s. Must accept arbitrary values. You're strongly encouraged to use test-driven development for this part, but it is not strictly required.

## TODO 4: Read from a file.
Using the File I/O examples from lecture, now make it so that you can parse strings from a local file. Once again, you must parse character-by-character, not using pattern matching. You're strongly encouraged to use test-driven development for this part, but it is not strictly required.

## TODO 5: Update values to include arrays and floats.
Now add more special characters and parsing rules. Remember to keep things small, clean, and clear. To get full marks for this part, you'll need to explain how your parser works. Note: Paul will be marking this by sending out an arbitrary test string in the next lab, so make up your own examples and test your code with them. You're strongly encouraged to use test-driven development for this part, but it is not strictly required.

## TODO 6 (Bonus): Account for two levels of nested objects.
The JSON standard includes many levels of possible object nesting. Start with a single level of nesting, i.e., a key can point to a value which is also an object, but that object cannot contain another key-value pair where a value is also an object. Use this as a test string:

```
{
  "name": "Alice",
  "co-workers": [
    {
      "name": "Bob"
      "age": 62
    },
    {
      "name": "Gonzo",
      "age": 44
    }
  ]
}

```

## TODO 7 (Bonus): Account for arbitrary levels of nested objects.
Write your code to include an arbitrary level of nested objects.

# Marking
- TODO 1: 10 points
- TODO 2: 15 points
- TODO 3: 25 points
- TODO 4: 15 points
- TODO 5: 20 points

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
