package org.bcit.comp2522.labs.lab08;

/**
 * Binary Search Tree class.
 *
 * @param <K> for key
 * @param <V> for value
 *
 * @author towaquimbayo
 * @version 1.0
 */
public class MyBinarySearchTree<K, V> implements IKeyValueStore<K, V> {
  /** Node root variable. */
  private Node<K, V> root;

  /** Constructor that sets the root to null. */
  public MyBinarySearchTree() {
    this.root = null;
  }

  /**
   * Push adds an item to the implementing data structure.
   *
   * @param key   is what is used to index values
   * @param value is what is stored
   */
  @Override
  public void push(K key, V value) {
    Node<K, V> newNode = new Node<K, V>(null, key, value);
    if (root == null) {
      root = newNode;
    } else {
      Node<K, V> currentNode = root;
      Node<K, V> parent;
      while (true) {
        parent = currentNode;
        if (currentNode.compareTo(key) < 0) { // traverse left
          currentNode = currentNode.left;
          if (currentNode == null) {
            parent.left = newNode;
            newNode.parent = parent;
            return;
          }
        } else {
          currentNode = currentNode.right; // traverse right
          if (currentNode == null) {
            parent.right = newNode;
            newNode.parent = parent;
            return;
          }
        }
      }
    }
  }

  /**
   * Get retrieves a value given a key.
   *
   * @param key is used to index values
   * @return the value
   */
  @Override
  public V get(K key) {
    Node<K, V> currentNode = this.root;
    while (true) {
      if (currentNode.compareTo(key) == 0) {
        return currentNode.value;
      }
      if (currentNode.compareTo(key) < 0) {
        currentNode = currentNode.left;
      } else {
        currentNode = currentNode.right;
      }
    }
  }

  /**
   * Gets the whole node rather than the value.
   *
   * @param key is used to index the values (and nodes)
   */
  @Override
  public Node<K, V> getNode(K key) {
    Node<K, V> currentNode = root;
    while (true) {
      if (currentNode.compareTo(key) == 0) {
        return currentNode;
      }
      if (currentNode.compareTo(key) < 0) {
        currentNode = currentNode.left;
      } else {
        currentNode = currentNode.right;
      }
    }
  }

  /**
   * Clear removes all items from the data structure.
   */
  @Override
  public void clear() {
    this.root = null;
  }
}
