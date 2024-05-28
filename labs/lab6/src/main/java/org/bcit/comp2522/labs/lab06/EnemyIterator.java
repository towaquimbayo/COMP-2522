package org.bcit.comp2522.labs.lab06;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * EnemyIterator class that iterators each Enemy
 * in the ArrayList from EnemyCollection.
 *
 * @param <Enemy> for Type Enemy object
 *
 * @author Towa Quimbayo, Alex Gibbison
 * @version 1.0
 */
public class EnemyIterator<Enemy> implements Iterator<Enemy> {
  /** The ArrayList that stores all enemies. */
  private final ArrayList<Enemy> enemyList;
  /** Index value to loop through the list. */
  private int index;

  /**
   * Constructor for EnemyIterator.
   *
   * @param e for Enemy ArrayList
   */
  public EnemyIterator(ArrayList<Enemy> e) {
    this.enemyList = e;
    this.index = 0;
  }

  /**
   * hasNext method checks if there
   * are anymore enemies to be iterated.
   *
   * @return true if index is smaller than list size
   */
  @Override
  public boolean hasNext() {
    return (this.enemyList.size() > index);
  }

  /**
   * Next method returns the Enemy object from the list
   * given from the index value for the ArrayList.
   *
   * @return Enemy
   */
  @Override
  public Enemy next() {
    Enemy e = this.enemyList.get(this.index);
    index++;
    return e;
  }
}
