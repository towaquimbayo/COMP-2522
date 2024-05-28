package org.bcit.comp2522.exam.questions;

public class Q1_CostlyLinkedList<K, V> {
  /**
   * Q1. Costly Linked List (33 points)
   * A Costly Linked List is a new kind of data structure where each
   * Node costs a certain amount of money to operate on. For this question,
   * you'll create an implementation of a Costly Linked List according to
   * the given requirements below.
   *
   * This question tests your ability to create a data structure that
   * is a modified version of a linked list. You will be marked on:
   * - Following the requirements
   * - Completing the question TODOs
   * - Good style, i.e., clearly written code and coherent methods
   * Comments are not strictly required, but they are appreciated.
   * There will also be marks available for creativity. If you finish this question
   * and have non-trivial ideas about how to extend this class, I will consider
   * giving bonus marks for unique ideas and solutions.
   *
   * TODO: Copy this file into a package called 'org.bcit.comp2522.exam.answers'
   * TODO: If you create other files, they must be prepended with "Q1_"
   *
   * A Costly Linked List has the following minimum requirements:
   * - The Costly Linked List must implement Iterable
   * - The Costly Linked List must use consistent generic types (i.e., use K and V as type parameters)
   * - Nodes in the Costly Linked List have a key (K), value (V), and cost (int)
   * - The Costly Linked List maintains a current budget (int)
   * - Every push, next, and get operations with a Node must decrease the budget by the Node's cost
   * - The next operation gets the next item in the linked list and MUST be used e.g., rather than just using Node.next
   * - As above, the next operation must be called by push and get
   * - If the budget reaches zero, the CostlyLinkedList must throw a ZeroBudgetException for every push, get, and next operation.
   *  Example:

      CostlyLinkedList<String, Integer> cllist = new CostlyLinkedList<String, Integer>(100);
      cllist.push("1", 1, 1); // budget = 100 - 1 = 99
      cllist.push("2", 2, 2); // budget = 99 - 1 - 2 = 96  : why? because push would have called next once
      cllist.push("3", 3, 2); // budget = 96 - 1 - 2 - 3 = 90 : why? because push would have called next twice
      cllist.getBudget(); // 90
      cllist.get("2"); // budget = 90 - 1 - 2 = 87
      cllist.forEach((n) -> System.out.println(n.value)); // budget = 87 - 1 - 2 - 3 = 81

   * TODO: Implement the necessary fields for referencing Nodes, budget, etc.
   * TODO: Create a new exception called a ZeroBudgetException and give it a verbose error message
   * TODO: Implement get, push, and next such that they reduce the budget and throw the exception
   * TODO: Implement the Node class with the necessary fields for key, value, and cost
   * TODO: Implement the forEach/iterator such that it reduces the budget on next() and stops when next() will overrun the budget
   * TODO: Implement any helper methods or classes necessary to achieve the above
   */
}
