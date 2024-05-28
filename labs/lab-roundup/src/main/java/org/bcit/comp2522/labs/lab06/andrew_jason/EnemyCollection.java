package org.bcit.comp2522.labs.lab06.andrew_jason;

import processing.core.PVector;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;

public class EnemyCollection<T> implements Iterable<T> {

  List<T> enemies = new ArrayList<>();

  public void collectionSort() {
    Collections.sort(enemies, new DistanceComparator());
  }

  @Override
  public void forEach(Consumer<? super T> action) {
    Iterable.super.forEach(action);
  }

  public void add(T newEnemy) {
    enemies.add(newEnemy);
  }

  public void remove(T enemy) {
    enemies.remove(enemy);
  }

  @Override
  public Iterator<T> iterator() {
    return new EnemyIterator<T>(enemies);
  }

  public class EnemyIterator<E> implements Iterator<E> {

    List<E> listOfEnemies;

    int index = 0;

    public EnemyIterator(List<E> list) {
      this.listOfEnemies = list;
    }

    @Override
    public boolean hasNext() {
      return index < listOfEnemies.size() - 1;
    }

    @Override
    public E next() {
      E nextEnemy = this.listOfEnemies.get(index);
      index++;
      return nextEnemy;
    }
  }
}
