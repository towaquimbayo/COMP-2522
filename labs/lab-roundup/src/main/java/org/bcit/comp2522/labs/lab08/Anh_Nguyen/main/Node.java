package org.bcit.comp2522.labs.lab08;

/**
 * This class is Node.
 *
 * @author Ryan Lee + Anh Nguyen
 * @version 1 Nov 5 2022
 *
 * @param <K> K
 *
 * @param <V> V
 *
 */
public class Node<K, V> {
  K key;
  V value;
  Node left, right;
  int timeStamp;

  /**
   * Constructor of Node.
   *
   * @param key key
   *
   * @param value value
   *
   */
  public Node(K key, V value) {

    this.key = key;
    this.value = value;
  }
}
