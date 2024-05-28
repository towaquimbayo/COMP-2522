package org.bcit.comp2522.exam.answers;

public class Q1_Node<K, V> {
  private K key;
  private V value;
  protected Q1_Node<K, V> next; // DONT USE

  public Q1_Node(K k, V val) {
    this.key = k;
    this.value = val;
  }
}
