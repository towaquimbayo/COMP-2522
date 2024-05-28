package org.bcit.comp2522.labs.lab08.noufil.main;

import org.bcit.comp2522.labs.lab08.IKeyValueStore;
import org.bcit.comp2522.labs.lab08.Node;

import java.util.Arrays;

/**
 * MyDataStructure uses an Array to create a collection of Nodes.
 *
 * @param <K>
 * @param <V>
 */
public class MyDataStructure<K, V> implements IKeyValueStore<K, V> {
  private Node<K, V>[] list;
  private int index = 0;
  private static final int INITIAL_SIZE = 10;

  public MyDataStructure() {
    list = new Node[INITIAL_SIZE];
  }

  /**
   * Push adds an item to the implementing data structure.
   *
   * @param key   is what is used to index values
   * @param value is what is stored
   */
  @Override
  public void push(K key, V value) {
    Node<K, V> newNode = new Node<> (key, value);

    if (index == list.length) {
      increaseSize();
    }

    list[index++] = newNode;
  }

  /**
   * Get retrieves a value given a key.
   *
   * @param key is used to index values
   * @return the value
   */
  @Override
  public V get(K key) {
    if ((int) key < 0 || (int) key >= index) {
      throw new IndexOutOfBoundsException();
    }

    return list[(int) key].value;
  }

  /**
   * Gets the whole node rather than the value.
   *
   * @param key is used to index the values (and nodes)
   */
  @Override
  public Node getNode(K key) {
    if ((int) key < 0 || (int) key >= index) {
      throw new IndexOutOfBoundsException();
    }

    return list[(int) key];
  }

  /**
   * Clear removes all items from the data structure.
   */
  @Override
  public void clear() {
    Arrays.fill(list, null);
  }

  public void increaseSize() {
    int doubleSize = list.length * 2;
    list = Arrays.copyOf(list, doubleSize);
  }
}
