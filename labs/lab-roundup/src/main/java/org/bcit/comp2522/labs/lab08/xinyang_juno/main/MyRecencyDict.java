package org.bcit.comp2522.labs.lab08.xinyang_juno.main;

/**
 * This program guarantees that the most recently accessed items
 * are the fastest to access again.
 *
 * @param <K> is what is used to index values
 * @param <V> is what is stored
 */
public class MyRecencyDict<K extends Comparable<K>, V> implements IKeyValueStore<K, V> {

  Node head;
  Node right;
  Node left;
  Node tail;

  /**
   * Construct a MyRecencyDict object.
   */
  public MyRecencyDict() {
    head = null;
    right = null;
    left = null;
    tail = null;
  }

  /**
   * Push adds an item to the implementing data structure.
   *
   * @param key   is what is used to index values
   * @param value is what is stored
   */
  @Override
  public void push(K key, V value) {
    Node node = new Node(key, value);
    if (head == null) {
      this.head = node;
      this.tail = head;
    } else {
      tail.setRight(node);
      tail = node;
      node.setLeft(tail);
      tail.setLeft(node);
    }
  }

  /**
   * Get retrieves a value given a key.
   *
   * @param key is used to index values
   * @return the value
   */
  @Override
  public <V> V get(K key) {
    V value = null;
    Node walk = head;
    while (walk != null) {
      int compare = key.compareTo((K) walk.key);
      if (compare == 0) {
        value = (V) walk.getValue();
        break;
      } else if (compare > 0) {
        walk = walk.getRight();
      }
    }
    return value;
  }

  /**
   * Gets the whole node rather than the value.
   *
   * @param key is used to index the values (and nodes)
   */
  @Override
  public Node getNode(K key) {
    Node node = null;
    Node walk = head;
    while (walk != null) {
      int compare = key.compareTo((K) walk.key);
      if (compare == 0) {
        node = walk;
        reorder(node, node.getLeft());
        break;
      } else if (compare > 0) {
        walk = walk.getRight();
      }
    }
    return node;
  }

  /**
   * Recorder the linked list so that the most recently accessed
   * items are the fastest to access again.
   *
   * @param node
   * @param prevNode
   */
  public void reorder(Node node, Node prevNode) {
    Node tempRight;
    if (node == head) {
      return;
    } else {
      if (node.getRight() == null) { // if the node is the tail
        node.setRight(head);
        node.setLeft(null);
      } else {
        tempRight = node.getRight();
        prevNode.setRight(tempRight);
        node.setRight(head);
        node.setLeft(null);
        head.setLeft(node);
      }
      head = node;
    }
  }

  /**
   * Clear removes all items from the data structure.
   */
  @Override
  public void clear() {
    head = null;
  }
}
