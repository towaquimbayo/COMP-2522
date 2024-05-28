package org.bcit.comp2522.labs.lab08;
/**
 * This class is to Implement a binary search tree,

 *
 * @author Ryan Lee + Anh Nguyen
 *
 * @version 1 Nov 5 2022
 * @param <K> K type
 *
 * @param <V> V type
 *
 */
public class MyDataStructure<K extends Comparable, V extends Comparable> implements CustomKeyValStore<K, V> {
  MyNode head, tail = null;
  /**
   * Push adds an item to the implementing data structure.
   *
   * @param key   is what is used to index values
   * @param value is what is stored
   */
  @Override
  public void push(K key, V value) {
    MyNode n = new MyNode(key, value);
    if (head == null) {
      head = tail = n;
      head.prev = null;
      tail.next = null;
    } else {
      tail.next = n;
      tail = n;
      tail.next = null;
    }
  }

  /**
   * Get retrieves a value given a key.
   *
   * @param key is used to index values
   * @return the value
   */
  @Override
  public <V1> V1 get(K key) {
    if (head == null) {
      System.out.println("The list is empty");
      return null;
    }
    MyNode curr = head;
    while (curr != null) {
      if (key.compareTo(curr.key) == 0) {
        return (V1) curr.value;
      }
      curr = curr.next;
    }
    System.out.println("The element you're looking for does not exist");
    return null;
  }

  /**
   * Gets the whole node rather than the value.
   *
   * @param key is used to index the values (and nodes)
   */
  @Override
  public MyNode getNode(K key) {
    if (head == null) {
      System.out.println("The list is empty");
      return null;
    }
    MyNode curr = head;
    while (curr != null) {
      if (key.compareTo(curr.key) == 0) {
        return curr;
      }
    }
    System.out.println("The element you're looking for does not exist");
    return null;
  }

  /**
   * Clear removes all items from the data structure.
   */
  @Override
  public void clear() {
    head = null;
    tail = null;
  }
}
