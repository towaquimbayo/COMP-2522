package org.bcit.comp2522.exam.answers;

public class Q3_Entry<K, V> {
  protected K key;
  protected V value;
  protected Q3_Entry<K, V> next;

  public Q3_Entry(K k, V v, Q3_Entry<K, V> n) {
    this.key = k;
    this.value = v;
    this.next = n;
  }
}
