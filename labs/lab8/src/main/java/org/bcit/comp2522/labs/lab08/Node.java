package org.bcit.comp2522.labs.lab08;

/**
 * Node class.
 *
 * @param <K> for key
 * @param <V> for value
 *
 * @author towaquimbayo
 * @version 1.0
 */
public class Node<K, V> implements Comparable<K> {
  /** Key generic variable. */
  K key;
  /** Value generic variable. */
  V value;
  /** Children of generic nodes. */
  Node<K, V>[] children;
  /** Parent of generic Node. */
  Node<K, V> parent;
  /** Left child node. */
  Node<K, V> left;
  /** Right child node. */
  Node<K, V> right;

  /**
   * Constructor for Node.
   *
   * @param p for parent
   * @param key for key
   * @param value for value
   */
  public Node(Node<K, V> p, K key, V value) {
    this.children = new Node[2];
    this.key = key;
    this.value = value;
  }

  /**
   * ToString method.
   *
   * @return string message
   */
  public String toString() {
    return "(" + this.key + ", " + this.value + ")";
  }

  /**
   * CompareTo method for comparing Nodes.
   *
   * @param key the object to be compared.
   * @return -1 if this node key is greater, 1 if compared Key is greater, 0 if equal
   */
  @Override
  public int compareTo(K key) {
    if (this.key instanceof Integer) {
      if (((Integer) this.key) > ((Integer) key)) {
        return -1;
      } else if (((Integer) this.key) < ((Integer) key)) {
        return 1;
      }
    } else if (this.key instanceof String) {
      if (Integer.parseInt((String) this.key) > Integer.parseInt((String) key)) {
        return -1;
      } else if (Integer.parseInt((String) this.key) < Integer.parseInt((String) key)) {
        return 1;
      }
    }
    return 0;
  }
}
