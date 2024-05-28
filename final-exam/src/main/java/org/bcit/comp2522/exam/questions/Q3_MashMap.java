package org.bcit.comp2522.exam.questions;

public class Q3_MashMap {
  /**
   * Q3. MashMap (33 points)
   *
   * This question tests your ability to create a data structure that
   * is a modified version of a Hash Map. You will be marked on:
   * - Following the requirements
   * - Completing the question TODOs
   * - Good style, i.e., clearly written code and coherent methods
   * There will also be marks available for creativity. If you finish this question
   * and have non-trivial ideas about how to extend this class, I will consider
   * giving bonus marks for unique ideas and solutions.
   *
   * TODO: Copy this file into a package called 'org.bcit.comp2522.exam.answers'
   * TODO: If you create other files, they must be prepended with "Q3_"
   *
   * A MashMap is like a HashMap, with some important distinctions.
   * Here are the requirements:
   * - A MashMap allows multiple threads to read data simultaneously
   * - A MashMap enforces one-at-a-time write operations
   * - A MashMap ensures that each bucket never holds more than 2x the smallest bucket by resizing and rehashing
   * - The above requirement holds if and only if each bucket has at least 10 items
   * - The hashing algorithm should ensure a relatively even distribution of items
   *
   * TODO: Initialize the MashMap with a standard HashMap structure
   * TODO: Implement the push and get methods, including hashing where needed
   * TODO: Implement concurrent read operations
   * TODO: Implement thread-blocking write operations
   * TODO: Demonstrate your solution by writing two threads that attempt read and write from the MashMap simultaneously
   */
}
