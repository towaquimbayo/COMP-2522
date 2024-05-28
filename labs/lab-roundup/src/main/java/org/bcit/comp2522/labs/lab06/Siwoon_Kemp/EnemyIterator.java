package org.bcit.comp2522.labs.lab06;

import java.util.Iterator;

/**
 * The enemy collection iterator.
 *
 * @param <T> as object type.
 */
public class EnemyIterator<T> implements Iterator<T> {

  Node<T> current;

  public EnemyIterator(EnemyCollection<T> list) {
    current = list.getHead();
  }

  @Override
  public boolean hasNext() {
    return current != null;
  }

  @Override
  public T next() {
    T data = current.getData();
    current = current.getNext();
    return data;
  }

}
