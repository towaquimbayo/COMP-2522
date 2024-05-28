package org.bcit.comp2522.labs.lab08.Gabriel_Sarvenaz;

public class MyBinarySearchTree<K extends Comparable<K>, V> implements IKeyValueStore<K, V>{

  Node <K, V> head;


  /**
   * Push adds an item to the implementing data structure.
   *
   * @param key   is what is used to index values
   * @param value is what is stored
   */
  @Override
  public void push(K key, V value) {

    head = recursivePush(head, key, value);
  }

  private Node<K,V> recursivePush(Node<K, V> node, K key, V value) {
    // If there is no head, make a new head node.
    if (node == null) {
      return new Node(head, key, value);

      // If there is a head node, check left. If there is a node left,
      // recursion iterates down the tree. After iteration, it will reach a left or right node
      // that is null and return a new head node.
    } else if ( key.compareTo(node.key) < 0) {
      node.left = recursivePush(node.left, key, value);
      return node;
    } else if (key.compareTo(node.key) > 0) {
      node.right = recursivePush(node.right, key, value);
      return node;
    }
    // return the base case node pointer.
    return node;
  }

  /**
   * Get retrieves a value given a key.
   *
   * @param key is used to index values
   * @return the value
   */
  @Override
  public <V> V get(K key) {
    return (V) recursiveGet(head, key);
  }

  public <V> V recursiveGet(Node<K, V> node1, K key){
    if (node1 == null){
      return null;
    } else if ( key.compareTo(node1.key) < 0) {
      node1.left.value = recursiveGet(node1.left, key);
      return node1.left.value;
    } else if (key.compareTo(node1.key) > 0) {
      node1.right.value = recursiveGet(node1.right, key);
      return node1.right.value;
    }
  // return the base case node pointer.
    return node1.value;

  }

  /**
   * Gets the whole node rather than the value.
   *
   * @param key is used to index the values (and nodes)
   */
  @Override
  public Node getNode(K key) {
    return recursiveGetNode(head, key);
  }

  public Node<K, V>  recursiveGetNode(Node<K, V> node1, K key){
    if (node1 == null){
      return null;
    } else if ( key.compareTo(node1.key) < 0) {
      node1.left.value = recursiveGet(node1.left, key);
      return node1.left;
    } else if (key.compareTo(node1.key) > 0) {
      node1.right.value = recursiveGet(node1.right, key);
      return node1.right;
    }
    // return the base case node pointer.
    return node1;

  }

  /**
   * Clear removes all items from the data structure.
   */
  @Override
  public void clear() {
    this.head = null;
  }
}
