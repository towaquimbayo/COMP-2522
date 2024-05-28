package org.bcit.midterm.answers;

public class Advanced_04_Towa {
  // Advanced Question 04 (15 estimated points, 2 TODOs)
  // Idea: tests your ability to create and use the Observer pattern.

  // README FIRST
  // I will ONLY be reading from the org.bcit.midterm.answers package.
  // TODO 0: Create a copy of this file in the answers package.
  // Put your answers in new classes that are labeled with the
  // question label, e.g., Advanced_04_myClass.

  // TODO 1: Create the classes and methods such that the Observer
  // pattern is used. Make it so that the Observers print the
  // message that receive on each update ALONG WITH their unique ID.
  public static void main(String[] args) {
    Advanced_04_Towa_Observable observable = new Advanced_04_Towa_Observable();
    Advanced_04_Towa_Observer observer1 = new Advanced_04_Towa_Observer();
    Advanced_04_Towa_Observer observer2 = new Advanced_04_Towa_Observer();
    Advanced_04_Towa_Observer observer3 = new Advanced_04_Towa_Observer();
    observable.registerObserver(observer1);
    observable.registerObserver(observer2);
    observable.registerObserver(observer3);
    observable.notifyObservers("Message");
  }
}
