package org.bcit.comp2522.labs.lab08.Minji_Artem.main;

/**
 * Defines the our data structure.
 *
 * @author Paul
 * @author Artem and Wendy
 * @version 1.0
 */
public class Node<K extends Comparable<K>, V> {
  K key;
  V value;
  Node[] children;
  Node parent;

  /**
   * Constructs the node.
   *
   * @param parent is parent node
   * @param key is the key of the node
   * @param value is the value of the node
   */
  public Node(Node parent, K key, V value) {
    children = new Node[2];
    this.key = key;
    this.value = value;
  }

  /**
   * Gets the key of the current node.
   *
   * @return the key of the node
   */
  public K getKey() {
    return key;
  }

  /**
   * Gets the value of the current node.
   *
   * @return value of the node
   */
  public V getValue() {
    return value;
  }

  /**
   * Gets the children of the current node.
   *
   * @return array of children
   */
  public Node[] getChildren() {
    return this.children;
  }

  /**
   * Sets the child of the node.
   *
   * @param node is node that will be the child
   */
  public void setChild(int index, Node node) {
    this.children[index] = node;
  }

  /**
   * Sets the parent of the node.
   *
   * @param node is node that will be the parent
   */
  public void setParent(Node node) {
    this.parent = node;
  }

  /**
   * Gets the parent of the node.
   *
   * @return parent of the node.
   */
  public Node getParent() {
    return this.parent;
  }

  /**
   * Sets the value of the node.
   */
  public void setValue(V value) {
    this.value = value;
  }

}
