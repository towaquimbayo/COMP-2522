package org.bcit.comp2522.exam.answers;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

public class Q1_CostlyLinkedListIterator<K, V> implements Iterator<K> {
  private int index;
  private LinkedList<Q1_Node<K, V>> myList;

  public Q1_CostlyLinkedListIterator(LinkedList<Q1_Node<K, V>> list) {
    index = 0;
    myList = list;
  }

  @Override
  public boolean hasNext() {
    return false;
  }

  @Override
  public K next() {
    return null;
  }
}
