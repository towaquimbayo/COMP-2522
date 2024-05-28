package org.bcit.comp2522.labs.lab08;

import java.util.LinkedList;

/**
 * Recency Dictionary class.
 *
 * @param <K> for key
 * @param <V> for value
 *
 * @author towaquimbayo
 * @version 1.0
 */
public class MyRecencyDict<K, V> implements IKeyValueStore<K, V> {
  /** Head of node. */
  Node<K, V> head;
  /** linkedList for recency dictionary. */
  LinkedList<Node<K, V>> recencyDictionary;

  /**
   * Constructor that sets head to null.
   */
  public MyRecencyDict() {
    this.head = null;
    this.recencyDictionary = new LinkedList<Node<K, V>>();
  }

  /**
   * Push adds an item to the implementing data structure.
   *
   * @param key   is what is used to index values
   * @param value is what is stored
   */
  @Override
  public void push(K key, V value) {
    Node<K, V> node = new Node<K, V>(null, key, value);
    if (this.head == null) {
      this.head = node;
      this.recencyDictionary.addFirst(head);
    } else {
      if (this.recencyDictionary.contains(node)) {
        this.recencyDictionary.remove((int) key);
      }
      this.recencyDictionary.addFirst(node);
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
    Node<K, V> currNode = this.recencyDictionary.getFirst();
    for (Node<K, V> node : recencyDictionary) {
      if (node.key.equals(key)) {
        currNode = node;
      }
    }
    return currNode.value;
  }

  /**
   * Gets the whole node rather than the value.
   *
   * @param key is used to index the values (and nodes)
   */
  @Override
  public Node<K, V> getNode(K key) {
    return this.recencyDictionary.get((int) key);
  }

  /**
   * Clear removes all items from the data structure.
   */
  @Override
  public void clear() {
    this.recencyDictionary = null;
  }

  public static void main(String[] args) {
    MyRecencyDict<Integer, Integer> myRecencyDict = new MyRecencyDict<Integer, Integer>();
    myRecencyDict.push(2, 20);
    myRecencyDict.push(3, 30);
    myRecencyDict.push(4, 20);
    myRecencyDict.push(5, 30);
    myRecencyDict.push(6, 20);
    myRecencyDict.push(1, 10);

    System.out.println(myRecencyDict.recencyDictionary);
  }
}
