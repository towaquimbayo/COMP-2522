package org.bcit.comp2522.labs.lab06;

/**
 * Define the basic functions of the node list.
 *
 * @param <T> as input object.
 */
public class Node<T> {
  T data;
  Node<T> next;

  public Node(T data, Node<T> next) {
    this.data = data;
    this.next = next;
  }

  public void setData(T data) {
    this.data = data;
  }

  public void setNext(Node<T> next) {
    this.next = next;
  }

  public T getData() {
    return data;
  }

  public Node<T> getNext() {
    return next;
  }
}
