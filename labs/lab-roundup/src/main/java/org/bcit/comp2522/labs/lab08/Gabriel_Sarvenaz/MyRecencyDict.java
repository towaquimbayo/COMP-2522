package org.bcit.comp2522.labs.lab08.Gabriel_Sarvenaz;

public class MyRecencyDict<K extends Comparable<K>, V> implements IKeyValueStore<K, V> {
  Node <K, V> head;

  private <V> void pushTop(K key, V value) {
    Node<K, V> node = new Node (null, key, value);
    if (head.key.compareTo(key) > 0) {
      node.right = (Node<K, V>) head;
    }
    if (head.key.compareTo(key) < 0) {
      node.left = (Node<K, V>) head;
    }
  }

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

  @Override
  public <V> V get(K key) {
    Node <K, V> newNode = new Node(null, key, null);
    newNode.value = (V) recursiveGet(head, key);
    pushTop(key, newNode.value);
    return newNode.value;
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
    return null;
  }

  /**
   * Clear removes all items from the data structure.
   */
  @Override
  public void clear() {

  }
}
