package org.bcit.comp2522.labs.lab08;


public class MyBinarySearchTree<K extends Comparable, V extends Comparable> implements IKeyValueStore<K, V>{
  Node root;
  /**
   * Push adds an item to the implementing data structure.
   *
   * @param key   is what is used to index values
   * @param value is what is stored
   */
  @Override
  public void push(K key, V value) {
    pushIterative(key, value);
  }

  private void pushIterative(K key, V value) {
    Node n = new Node(key, value);
    Node parent = null, curr = root;
    if (root == null) {
      root = n;
      return;
    }
    while (curr != null) {
      parent = curr;
      if (key.compareTo(curr.key) <0) {
        curr = curr.left;
      } else if (key.compareTo(curr.key) > 0) {
        curr = curr.right;
      } else {
        curr.value = value;
        return;
      }
    }
    if (key.compareTo(parent.key) < 0) {
      parent.left = n;
    } else {
      parent.right = n;
    }
  }

  /**
   * Get retrieves a value given a key.
   *
   * @param key is used to index values
   * @return the value
   */
  @Override
  public <V1> V1 get(K key) {

    Node x = root;
    while (x != null) {
      int res = key.compareTo(x.key);
      if      (res < 0) x = x.left;
      else if (res > 0) x = x.right;
      else return (V1)x.value;
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
    return getFullNode(root, key);
  }

  private Node getFullNode(Node root, K key) {
    if (root == null || key.compareTo(root.key) == 0) {
      return root;
    }
    if (key.compareTo(root.key) < 0) {
      return getFullNode(root.left, key);
    }
    return getFullNode(root.right, key);
  }

  /**
   * Clear removes all items from the data structure.
   */
  @Override
  public void clear() {
    root = null;
  }
}
