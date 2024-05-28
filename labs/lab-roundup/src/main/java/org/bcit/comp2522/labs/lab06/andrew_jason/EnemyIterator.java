package org.bcit.comp2522.labs.lab06.andrew_jason;

import java.util.Iterator;
import java.util.List;

public class EnemyIterator<E> implements Iterator<E> {

  List<E> list;

  int position = 0;

  public EnemyIterator(List<E> list) {
    this.list = list;
  }


  @Override
  public boolean hasNext() {
    return false;
  }

  @Override
  public E next() {
    return null;
  }
}
