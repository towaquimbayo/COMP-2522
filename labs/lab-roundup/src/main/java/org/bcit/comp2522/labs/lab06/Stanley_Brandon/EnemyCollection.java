package org.bcit.comp2522.labs.lab06.Stanley_Brandon;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * EnemyCollection class contains the iterator implementation for the enemies being drawn.
 */
public class EnemyCollection implements Iterable<Enemy> {

  List<Enemy> enemyList;

  int current = 0;

  /**
   * Creates a new EnemyCollection, takes in a List Object of enemies.
   *
   * @param list of Enemies.
   */
  public EnemyCollection(List<Enemy> list) {
    this.enemyList = list;
  }

  /**
   * Removes an enemy from the EnemyCollecion ArrayList.
   *
   * @param e an enemy object.
   */
  public void remove(Enemy e) {
    enemyList.remove(e);
  }

  /**
   * Adds an enemy to the EnemyCollection ArrayList.
   *
   * @param e an enemy object.
   */
  public void add(Enemy e) {
    enemyList.add(e);
  }

  /**
   * Method resets the current number of enemies to 0 for iteration purposes.
   */
  public void resetIterator() {
    current = 0;
  }

  /**
   * Creates a new iterator object to iterate through the EnemyCollection.
   *
   * @return EnemyIterator
   */
  public Iterator<Enemy> iterator() {
    return new EnemyIterator();
  }

  /**
   * Class contains methods from the Iterator interface.
   */
  class EnemyIterator implements Iterator<Enemy> {
    int size = enemyList.size();

    @Override
    public boolean hasNext() {
      return (current < size);
    }

    @Override
    public Enemy next() {
      if (!hasNext()) {
        throw new NoSuchElementException();
      }

      Enemy e = enemyList.get(current);
      current++;
      return e;
    }
  }

}
