# Lab-11

This week we are looking at async callbacks from data sources. This repository contains example code for connecting to MongoDB asynchronously. You can learn more about MongoDB's async library in their [Reactive Streams documentation](https://www.mongodb.com/docs/drivers/reactive-streams/). 

There are a few things to note here:
- This is the ASYNC library for MongoDB. If you want a synchronous version, check out their regular [Java Driver documentation](https://www.mongodb.com/docs/drivers/java/sync/current/).
- MongoDB is a JSON-like database, so you address everything as key-value pairs (not SQL)
- You'll need to update your `build.gradle` file and rebuild to include this line:
```
dependencies {
  // ... other stuff
  implementation 'org.mongodb:mongodb-driver-reactivestreams:4.8.0'
}
```

## TODO 1: Run the starter code
Take a look at the starter code in this repo and run it. This code connects to the same database instance for everyone in the class. To reduce conflicts, you should update this line:

`MongoCollection<Document> collection = database.getCollection("test");`

And replace `"test"` with your team name (or anything else unique).

## TODO 2: Consider using MongoDB in your application
Not everyone will be using MongoDB, since they might be using other asynchronous tasks. But if you need to fulfill the requirement of async and persistent data, this is a good solution. 

# Project TODOs
This week you should have shown off a MVP. Now is the time to iterate. 

## TODO 1: Create new GitHub issues for any new features you will implement
Create and assign new issues on GitHub so that group members are aware of the tasks that they need to complete.

## TODO 2: Get ready for the demo
Next week during lab you will be presenting a demo. It's not the final product, but it should be extremely close to the final product. Prepare the following:
- A runnable version of your project that others can interact with
- A short verbal explanation of what you were planning to accomplish
- Be ready to show off your code and answer questions about it
- Be ready to ask questions and critique other people's work

Demos are a common practice in computer science. You will commonly have a product that is not yet finished, but you need to show it off to managers, clients, investors, coworkers. Nobody expects a demo to be a finished product, but they do expect to be able to see key features working. It's not a formal presentation (don't prepare slides) as much as a way to get fast feedback. Critique should always be helpful and with skin in the game. If you could have done it better, you better be prepared to prove it through actually working on their project. Otherwise, critique should be in the realm of asking helpful questions about implementation. Curiosity is the key metric.
