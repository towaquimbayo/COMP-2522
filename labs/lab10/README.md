# Lab 10
Today's lab is focused mostly on your project. It's designed to set you up for concurrency next week. You'll be implementing a synchronous callback for an [event listener](https://programming.guide/java/create-a-custom-event.html). Next week, you'll do an asynchronous callback.

## TODO 1: Understand what a callback is
Take a look at `EventListenerTemplate.java` and copy it into your project. Run it by itself. Trace the code to understand what's happening. It should look a little like the Observer pattern. You essentially have a piece of code that calls another piece of code when it is run. The idea is that an `Event` may be called at any time, and eventually we want to handle it in a separate `Thread`. For now, a synchronous callback is enough.

## TODO 2: Implement a callback in your project
Find a place in your project to implement a callback. Hint: it should be for any server interactions you will eventually have. Ensure that you have implemented a callback to handle data coming back from a server (even if it doesn't exist yet). If you haven't implemented any server calls, take what we have done in lecture by creating a stub class with dummy data.

# Project TODOs

## TODO 1: Write tests for ALL of your methods
Each method needs a test. Write a full test suite for your project. Realistically, this should be done **before** you start to actually code the logic of your methods, but it's OK if you do this after having already coded your methods. For those of you who are asking, yes, tests count towards line count.

## TODO 2: Create and assign GitHub issues for tests
Each test or small group of tests should be tracked by GitHub issues. Create and assign GitHub issues to each team member. There should be an even distribution of work.

# TODO 3: Solve the tests
Now it's time to implement your code. Many have already started on this. Continue to work on implementing project features. You should have an MVP for next week: not done, but something that clearly demonstrates your project.