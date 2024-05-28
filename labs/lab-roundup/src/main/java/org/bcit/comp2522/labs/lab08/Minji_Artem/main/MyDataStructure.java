package org.bcit.comp2522.labs.lab08.Minji_Artem.main;


/**
 * Defines the our data structure.
 *
 * @author Artem Khan and Wendy
 * @version 1.0
 */
public class MyDataStructure<K extends Comparable<K>, V> implements IKeyValueStore<K, V> {

  private static final int FINAL_SIZE = 15;
  private Node[] myDataArray;

  /**
   * Constructs the object.
   *
   */
  public MyDataStructure() {
    myDataArray = new Node[FINAL_SIZE];
  }

  /**
   * Push adds an item to the implementing data structure.
   * It first creates a hashing code for the key and finds that
   * key in the array. When it finds a match, it creates and adds the node
   * to that index. If there was already a node at that index, it takes that
   * node (and its children) and sets it as a child of the node that will get added.
   *
   * @param key   is what is used to index values
   * @param value is what is stored
   */

  @Override
  public void push(K key, V value) {
    int arrayIndex = hashing(key);
    Node head = myDataArray[arrayIndex];
    while (head != null) {
      if (key.compareTo((K) (head.getKey())) == 0) {
        head.setValue(value);
        return;
      }
      head = head.getChildren()[0];
    }
    head = myDataArray[arrayIndex];
    Node toAddNode = new Node(null, key, value);
    toAddNode.setChild(0, head);
    myDataArray[arrayIndex] = toAddNode;
  }

  /**
   * Get retrieves a value given a key. It creates a hash code
   * for the key and find an index that matches that code. It then retrieves
   * the node at that index and compares the keys. If the keys matches, it returns
   * the value. Otherwise, it repeats the cycle with the children of the node at that
   * index.
   *
   * @param key is used to index values
   * @return the value
   */
  @Override
  public <V> V get(K key) {
    int arrayIndex = hashing(key);
    Node head = myDataArray[arrayIndex];
    while (head != null) {
      if (key.compareTo((K) (head.getKey())) == 0) {
        return (V) head.getValue();
      }
      head = head.getChildren()[0];
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
    myDataArray = new Node[FINAL_SIZE];
  }

  /**
   * Creates a hash code for the provided key.
   *
   * @param key is used to index values
   * @return integer value that will be an index in the array
   */
  public int hashing(K key) {
    int hash = key.hashCode();
    int indx = hash % FINAL_SIZE;
    return indx;
  }
}
