package org.bcit.comp2522.labs.lab08.xinyang_juno.main;

public class MyBinarySearchTree<K extends Comparable<K>, V> implements IKeyValueStore<K, V> {

  Node root;

  public MyBinarySearchTree() {
    root = null;
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
    if (root == null) {
      this.root = node;
    } else {
      Node walk = root;
      Node temp = walk;
      while (walk != null) {
        if (key.compareTo((K) walk.key) <= 0) {
          temp = walk.getLeft();
          if (temp == null) {
            walk.setLeft(node);
            break;
          }
          walk = walk.getLeft();
        } else {
          temp = walk.getRight();
          if (temp == null) {
            walk.setRight(node);
            break;
          }
          walk = walk.getRight();
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
    V value = null;
    if (root == null) {
      return null;
    } else {
      Node walk = root;
      Node temp = walk;
      while (walk != null) {
        int compare = key.compareTo((K) walk.key);
        if (compare == 0) {
          value = (V) walk.getValue();
          break;
        } else if (compare < 0) {
          temp = walk.getLeft();
        }
        else {
          temp = walk.getRight();
        }
        walk = temp;
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
    if (root == null) {
      return null;
    } else {
      Node walk = root;
      Node temp = walk;
      while (walk != null) {
        int compare = key.compareTo((K) walk.key);
        if (compare == 0) {
          node = walk;
          break;
        } else if (compare < 0) {
          temp = walk.getLeft();
        }
        else {
          temp = walk.getRight();
        }
        walk = temp;
      }
    }
    return node;
  }

  /**
   * Clear removes all items from the data structure.
   */
  @Override
  public void clear() {
    root = null;
  }
}
