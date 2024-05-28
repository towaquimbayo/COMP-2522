package org.bcit.comp2522.labs.lab08.xinyang_juno.main;

public class Node<K, V> {
  K key;
  V value;
  Node left;
  Node right;

  public Node(K key, V value) {
    this.key = key;
    this.value = value;
    left = null;
    right = null;
  }

  public K getKey() {
    return key;
  }

  public void setKey(K key) {
    this.key = key;
  }

  public Node getLeft() {
    return left;
  }

  public void setLeft(Node left) {
    this.left = left;
  }

  public Node getRight() {
    return right;
  }

  public void setRight(Node right) {
    this.right = right;
  }

  public V getValue() {
    return value;
  }
}


