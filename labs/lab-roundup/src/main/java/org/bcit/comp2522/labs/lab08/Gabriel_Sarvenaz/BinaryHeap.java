package org.bcit.comp2522.labs.lab08.Gabriel_Sarvenaz;

/**
 * Based on this code example: https://www.geeksforgeeks.org/binary-heap/
 * @param <K>
 * @param <V>
 */
public class BinaryHeap<K extends Comparable<K>, V> implements IKeyValueStore<K, V> {

  private static final int MAX_SIZE = 15000;
  private Node<K, V> [] heap;
  private int size;

  public BinaryHeap() {
    heap = new Node [MAX_SIZE];
    size = 0;
  }

  /**
   * Returns the index of the parent node
   */
  public Node<K, V> parent(int i) {
    return heap[(i - 1) / 2];
  }

  // Return the index of the left child
  public Node<K, V> leftChild(int i ){
    return heap[2*i + 1];
  }

  // Return the index of the right child
  public Node<K, V> rightChild(int i) {
    return heap[2*i + 2];
  }


  /**
   * Push adds an item to the implementing data structure.
   *
   * @param key   is what is used to index values
   * @param value is what is stored
   */
  @Override
  public void push(K key, V value) {
    if (size >= MAX_SIZE) {
//      System.out.println("Heap is full, cannot insert");
      return;
    }

    // first insert the time at the last position of the array
    // and move it up
    heap[size] = new Node(null, key, value);
    size = size + 1;

    // move up until the heap property satisfies
    int i = size - 1;
    while (i != 0 && heap[((i - 1) / 2)].key.compareTo(heap[i].key)<0) {
      Node temp = heap[i];
      heap[i] = heap[(i - 1) / 2];
      heap[(i - 1) / 2] = temp;
      i = ((i - 1) / 2);
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
    return null;
  }

  public <V> V heapGet(K key) {
    for (int i = 0; i < 15000; i++) {
      if (heap[i].key == key) {
        return (V) heap[i].value;
      }
    }
    return (V)heap[0].value;
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
}
