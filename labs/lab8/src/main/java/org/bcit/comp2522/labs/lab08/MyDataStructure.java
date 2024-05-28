package org.bcit.comp2522.labs.lab08;

import java.util.HashMap;

/**
 * My custom data structure class.
 *
 * @param <K> for key
 * @param <V> for value
 *
 * @author towaquimbayo
 * @version 1.0
 */
public class MyDataStructure<K, V> implements IKeyValueStore<K, V> {
  /** Hashmap where key stores the value of the element and value stores the indexes. */
  private final HashMap<K, V> myDsHash;

  /**
   * Constructor for my custom data structure.
   */
  public MyDataStructure() {
    this.myDsHash = new HashMap<K, V>();
  }

  /**
   * Push adds an item to the implementing data structure.
   *
   * @param key   is what is used to index values
   * @param value is what is stored
   */
  @Override
  public void push(K key, V value) {
    if (this.myDsHash.get(key) != null) {
      return;
    }
    this.myDsHash.put(key, value);
  }

  /**
   * Get retrieves a value given a key.
   *
   * @param key is used to index values
   * @return the value
   */
  @Override
  public V get(K key) {
    return this.myDsHash.get(key);
  }

  /**
   * Gets the whole node rather than the value.
   *
   * @param key is used to index the values (and nodes)
   */
  @Override
  public Node<K, V> getNode(K key) {
    return null;
  }

  /**
   * Clear removes all items from the data structure.
   */
  @Override
  public void clear() {
    this.myDsHash.clear();
  }

  /**
   * Main Method.
   *
   * @param args for program arguments.
   */
  public static void main(String[] args) {
    MyDataStructure<Integer, String> ds = new MyDataStructure<Integer, String>();
    for (int i = 0; i < 100; i++) {
      ds.push(i, String.format("%d", i));
    }

    for (int i = 0; i < 100; i++) {
      System.out.println(i + " " + ds.get(i));
    }
  }
}
