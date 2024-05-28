package org.bcit.comp2522.labs.lab08.noufil.main;

public class Node<K, V> {
  K key;
  V value;
  Node<K, V> parent;
  Node<K, V> left;
  Node<K, V> right;

  public Node(K key, V value) {
    this.parent = null;
    this.key = key;
    this.value = value;
    this.left = null;
    this.right = null;
  }

  public int compareTo(K k) {
    if (k instanceof String) {
      if (Integer.parseInt((String) this.key) > Integer.parseInt((String) k)) {
        return 1;
      } else if (Integer.parseInt((String) this.key) < Integer.parseInt((String) k)) {
        return -1;
      }
    } else if (k instanceof Integer) {
      if ((int) this.key > (int) k) {
        return 1;
      } else if ((int) this.key < (int) k) {
        return -1;
      }
    }

    return 0;
  }
}
