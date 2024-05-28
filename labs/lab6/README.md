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

