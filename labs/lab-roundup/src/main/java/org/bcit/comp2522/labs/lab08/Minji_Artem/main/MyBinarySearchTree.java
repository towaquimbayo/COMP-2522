package org.bcit.comp2522.labs.lab08.Minji_Artem.main;


/**
 * Defines binary search tree.
 *
 * @author Paul
 * @author Artem Khan and Wendy
 * @version 1.0
 */
public class MyBinarySearchTree<K extends Comparable<K>, V> implements IKeyValueStore<K, V> {

  Node root;

  /**
   * Push adds an item to the implementing data structure.
   *
   * @param key   is what is used to index values
   * @param value is what is stored
   */
  @Override
  public void push(K key, V value) {
    if (root == null) {
      root = new Node(null, key, value);
    }
    Node currentNode = root;
    while (true) {
      if (key.compareTo((K) currentNode.getKey()) < 0) {
        if (currentNode.children[0] == null) {
          Node smallerChild = new Node(currentNode, key, value);
          currentNode.children[0] = smallerChild;
          break;
        } else {
          currentNode = currentNode.children[0];
        }
      } else {
        if (currentNode.children[1] == null) {
          Node biggerChild = new Node(currentNode, key, value);
          currentNode.children[1] = biggerChild;
          break;
        } else {
          currentNode = currentNode.children[1];
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
  public <V> V get(K key) {
    Node currentNode = root;

    while (root != null) {
      int cmp = key.compareTo((K) currentNode.getKey());

      if (cmp < 0) {
        if (currentNode.children[0] != key) {
          currentNode = currentNode.children[0];
        } else {
          return (V) currentNode.children[0];
        }
      } else if (cmp > 0) {
        if (currentNode.children[1] != key) {
          currentNode = currentNode.children[1];
        } else {
          return (V) currentNode.children[1];
        }
      } else {
        return (V) currentNode.getValue();
      }
    }
    return null;
  }

  /**
   * Gets the whole node rather than the value.
   *
   * @param key is used to index the values (and nodes)
   */
  @Override
  public Node getNode(K key) {
    return null;
  }

  /**
   * Clear removes all items from the data structure.
   */
  @Override
  public void clear() {
    root = null;
  }
}
