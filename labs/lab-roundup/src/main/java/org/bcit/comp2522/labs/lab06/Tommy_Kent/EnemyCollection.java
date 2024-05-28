package org.bcit.comp2522.labs.lab06.Tommy_Kent;

import java.util.ArrayList;

/**
 * The collection of the enemies.
 *
 * @param <Enemy> the enemy
 * @author Tommy_Nguyen
 * @author Kent_Concengco
 * @version 1.0
 */
public class EnemyCollection<Enemy> implements Iterable<Enemy> {

  ArrayList<Enemy> list;

  public EnemyCollection() {
    this.list = new ArrayList<>();
  }

  /**
   * Adds an enemy to the collection.
   *
   * @param enemy an Enemy character
   */
  public void add(Enemy enemy) {
    this.list.add(enemy);
  }

  /**
   * Removes an enemy to the collection.
   *
   * @param enemy an Enemy character
   */
  public void remove(Enemy enemy) {
    this.list.remove(enemy);
  }

  /**
   * Clears the EnemyCollection.
   *
   */
  public void clear() {
    this.list.clear();
  }

  /**
   * Gets the size of the EnemyCollection
   *
   * @return the number of enemies in EnemyCollection
   */
  public int size() {
    return this.list.size();
  }

  @Override
  public EnemyIterator<Enemy> iterator() {
    return new EnemyIterator<>(this.list);
  }
}
