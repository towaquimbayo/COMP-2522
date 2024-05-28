package org.bcit.comp2522.labs.lab08.xinyang_juno.main;

public interface IKeyValueStore<K, V> {
  /**
   * Push adds an item to the implementing data structure.
   *
   * @param key is what is used to index values
   * @param value is what is stored
   */
  void push(K key, V value);

  /**
   * Get retrieves a value given a key.
   *
   * @param key is used to index values
   * @return the value
   * @param <V> the type of the value
   */
  <V> V get(K key);

  /**
   * Gets the whole node rather than the value.
   *
   * @param key is used to index the values (and nodes)
   */
  Node getNode(K key);

  /**
   * Clear removes all items from the data structure.
   */
  void clear();
}
