package org.bcit.comp2522.labs.lab08.noufil.main;

import org.bcit.comp2522.labs.lab08.IKeyValueStore;
import org.bcit.comp2522.labs.lab08.Node;

public class MyBinarySearchTree<K, V> implements IKeyValueStore<K, V> {
  private Node<K, V> root;

  public MyBinarySearchTree() {}

  /**
   * Push adds an item to the binary search tree.
   *
   * @param key   is what is used to index values
   * @param value is what is stored
   */
  @Override
  public void push(K key, V value) {
    Node<K, V> newNode = new Node<> (key, value);

    if (root == null) {
      this.root = newNode;
    } else {
      Node<K, V> currNode = this.root;
      Node<K, V> parent;

      while (true) {
        parent = currNode;

        if (currNode.compareTo(key) < 0) {
          currNode = currNode.left;

          if (currNode == null) {
            parent.left = newNode;
            newNode.parent = parent;
            return;
          }
        } else {
          currNode = currNode.right;

          if (currNode == null) {
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
    Node<K, V> currNode = this.root;

    while (true) {
      if (currNode.compareTo(key) < 0) {
        currNode = currNode.left;
      } else if (currNode.compareTo(key) > 0) {
        currNode = currNode.right;
      } else if (currNode.compareTo(key) == 0) {
        return currNode.value;
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
    Node<K, V> currNode = root;

    while (true) {
      if (currNode.compareTo(key) < 0) {
        currNode = currNode.left;
      } else if (currNode.compareTo(key) > 0) {
        currNode = currNode.right;
      } else if (currNode.compareTo(key) == 0) {
        return currNode;
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
