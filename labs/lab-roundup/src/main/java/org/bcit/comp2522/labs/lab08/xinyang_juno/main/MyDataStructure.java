package org.bcit.comp2522.labs.lab08.xinyang_juno.main;

/**
 * MyDataStructure<K,V> class is our customized data structure that imitates hast table.
 *
 * @param <K> K as key
 * @param <V> V as value
 */
public class MyDataStructure<K, V> implements IKeyValueStore<K, V> {
  // HASH_TABLE_CAPACITY defines the capacity of the hash table
  int HASH_TABLE_CAPACITY = 100001;
  myNode[] data = new myNode[HASH_TABLE_CAPACITY];

  /**
   * getHash(K key) returns a unique hashcode
   *
   *  @param key as a key
   * @return int as a new hash
   */
  private int getHash(K key) {
    return (int)key % HASH_TABLE_CAPACITY;
  }

  /**
   * Push adds an item to the implementing data structure.
   *
   * @param key   is what is used to index values
   * @param value is what is stored
   */
  @Override
  public void push(K key, V value) {
    if (key != null) {
      // Make a hash for the given key
      int hash = getHash(key);
      // Validation check for redundant value
      while (data[hash] != null && !data[hash].key.equals(key)) {
        // Make a hash
        hash = (hash + 1) % HASH_TABLE_CAPACITY;
      }
      data[hash] = new myNode(key, value);
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
    if (key != null) {
      int hash = getHash(key);
      // Validation check for redundant value
      while (data[hash] != null && !data[hash].key.equals(key)) {
        hash = (hash + 1) % HASH_TABLE_CAPACITY;
      }
      return data[hash] != null ? (V)data[hash].value : null;
    }
    return null;
  }

  /**
   * Gets the whole node rather than the value.
   *
   * @param key is used to index the values (and nodes)
   */
  @Override
  public Node getNode(K key) {
    return null;
  }

  /**
   * Clear removes all items from the data structure.
   */
  @Override
  public void clear() {
  }

  /**
   * myNode class
   */
  public static class myNode<String, V> {
    public String  key;
    public V value;

    /**
     * myNode class constructor that takes String and V as input
     * @param key
     * @param value
     */
    public myNode(String key, V value) {
      this.key = key;
      this.value = value;
    }
  }
}
