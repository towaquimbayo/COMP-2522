package org.bcit.comp2522.labs.lab08.Gabriel_Sarvenaz;

public class Node<K, V> {
  public Node<K,V> left, right;

  public K getKey() {
    return key;
  }

  public void setKey(K key) {
    this.key = key;
  }

  K key;
  V value;
  Node[] children;
  Node parent;

  public Node(Node parent, K key, V value) {
    children = new Node[0];
    this.key = key;
    this.value = value;
  }
}
