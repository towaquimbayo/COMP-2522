package org.bcit.comp2522.labs.lab08.Minji_Artem.main;

/**
 * Defines the recency dictionary object.
 *
 * @author Paul
 * @author Artem and Wendy
 * @version 1.0
 */
public class MyRecencyDict<K extends Comparable<K>, V> implements IKeyValueStore<K, V> {

  private static final int TOP_TEN = 10;
  private Node root;
  private Node[] recentArray;

  public MyRecencyDict() {
    recentArray = new Node[0];
  }

  /**
   * Push adds an item to the implementing data structure.
   *
   * @param key   is what is used to index values
   * @param value is what is stored
   */
  @Override
  public void push(K key, V value) {
    if (recentArray.length == 0) {
      recentArray = new Node[1];
    }
    boolean hasNull = false;
    int x = 0;
    for (int i = 0; i < recentArray.length; i++) {
      if (recentArray[i] == null) {
        hasNull = true;
        break;
      }
    }
    if (!hasNull) {
      Node[] temp = new Node[recentArray.length + 1];
      for (int i = 0; i < recentArray.length; i++) {
        temp[i] = recentArray[i];
      }
      recentArray = temp;
    }
    while (recentArray[x] != null) {
      x++;
    }
    recentArray[x] = new Node(null, key, value);
  }

  /**
   * Get retrieves a value given a key.
   * everytime it gets the value, it creates a new array,
   * sets the value at that index to that value, and copies
   * the existing array to the new array. Lastly, it assigns
   * the new array to the global array.
   *
   * @param key is used to index values
   * @return the value
   */
  @Override
  public <V> V get(K key) {
    int length = recentArray.length;
    boolean found = false;
    for (int i = 0; i < TOP_TEN; i++) {
      if (key.compareTo((K) recentArray[i].getKey()) == 0) {
        return (V) recentArray[i].getValue();
      }
    }
    Node[] recent = new Node[length + 1];
    for (int i = 1; i < recentArray.length; i++) {
      if (key.compareTo((K) recentArray[i].getKey()) == 0) {
        recent[0] = recentArray[i];
        found = true;
        break;
      }
    }
    for (int j = 0; j < recentArray.length; j++) {
      recent[j + 1] = recentArray[j];
    }
    recentArray = recent;
    if (found) {
      return (V) recentArray[0].getValue();
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
    recentArray = new Node[0];
  }

  /**
   * Adds the node at the start of the array.
   *
   * @param node is the node to be added
   */
  public void addAtTheStart(Node node) {
    if (root == null) {
      root = node;
    } else {
      Node temp = root;
      root = node;
      root.setChild(0, temp);
    }
  }
}
