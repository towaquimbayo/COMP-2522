package org.bcit.comp2522.labs.lab06;

import java.util.Iterator;

/**
 * Define an new EnemyCollection class that implements iterable.
 *
 * @param <T> as node.
 */
public class EnemyCollection<T> implements Iterable<T> {

  /**
   * Define the head of the list.
   */
  Node<T> head;

  /**
   * Define the tail of the list.
   */
  Node<T> tail;

  /**
   * The iterator of the list.
   *
   * @return new iterator.
   */
  @Override
  public Iterator<T> iterator() {
    return new EnemyIterator<T>(this);
  }

  /**
   * Add new element to the list.
   *
   * @param data as input.
   */
  public void add(T data) {
    Node<T> node = new Node<>(data, null);
    if (head == null) {
      tail = head = node;
    } else {
      tail.setNext(node);
      tail = node;
    }
  }

  /**
   * Remove element from the list.
   *
   * @param data as input.
   */
  public void remove(T data) {
    Node<T> currentNode = this.head;
    Node<T> previousNode = this.head;
    //If it is at the head
    if (currentNode.getData() == data) {
      this.head = head.getNext();
      return;
    }
    //Not at the head
    while (currentNode != null) {
      if (currentNode.getData() == data) {
        if (currentNode.getNext() == null) {
          this.tail = previousNode;
        }
        previousNode.setNext(currentNode.getNext());
        return;
      } else {
        previousNode = currentNode;
        currentNode = currentNode.getNext();
      }
    }
  }

  /**
   * Get the head of the list.
   *
   * @return head.
   */
  public Node<T> getHead() {
    return head;
  }

  /**
   * Get the tail of the list.
   *
   * @return tail.
   */
  public Node<T> getTail() {
    return tail;
  }
}
