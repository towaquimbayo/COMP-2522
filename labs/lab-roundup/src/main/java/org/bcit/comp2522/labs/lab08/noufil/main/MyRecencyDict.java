package org.bcit.comp2522.labs.lab08.noufil.main;

import org.bcit.comp2522.labs.lab08.IKeyValueStore;
import org.bcit.comp2522.labs.lab08.Node;

import java.util.LinkedList;
import java.util.ListIterator;

public class MyRecencyDict<K, V> implements IKeyValueStore<K, V> {
  private Node<K, V> root;
  public LinkedList<Node> recency;

  public MyRecencyDict() {
    this.recency = new LinkedList<>();
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

    if (this.root == null) {
      this.root = newNode;
      this.recency.addFirst(root);
    } else {
      this.recency.addFirst(newNode);
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
    Node<K, V> currNode = this.recency.getFirst();
    ListIterator<Node> iterator = this.recency.listIterator();

    while (!currNode.key.equals(key) && iterator.hasNext()) {
      currNode = (Node<K, V>) iterator.next();
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
    Node<K, V> currNode = this.recency.getFirst();
    ListIterator<Node> iterator = this.recency.listIterator();

    while (!currNode.key.equals(key) && iterator.hasNext()) {
      currNode = (Node<K, V>) iterator.next();
    }

    return currNode;
  }

  /**
   * Clear removes all items from the data structure.
   */
  @Override
  public void clear() {
    this.recency = null;
  }
}
