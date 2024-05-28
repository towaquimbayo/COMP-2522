package org.bcit.comp2522.labs.lab08;

public class MyNode<K, V> {
    K key;
    V value;
    MyNode prev, next;

    public MyNode(K key, V value) {

      this.key = key;
      this.value = value;
    }
}
