package org.bcit.comp2522.labs.lab06;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * EnemyCollection class that stores all enemies in
 * 2 lists. Implements Iterable for iterating the lists.
 *
 * @param <Enemy> for Type Enemy object
 *
 * @author Towa Quimbayo, Alex Gibbison
 * @version 1.0
 */
public class EnemyCollection<Enemy> implements Iterable<Enemy> {

  /** ArrayList collection of enemies. */
  private final ArrayList<Enemy> enemies;
  /** ArrayList to store Enemy's that need to be removed. */
  private final ArrayList<Enemy> removeEnemyQueue;

  /**
   * Constructor for EnemyCollection.
   * Instantiate the 2 arrayLists.
   */
  public EnemyCollection() {
    this.enemies = new ArrayList<>();
    this.removeEnemyQueue = new ArrayList<>();
  }

  /**
   * Add an enemy to the collection.
   *
   * @param e for Enemy
   */
  public void add(Enemy e) {
    this.enemies.add(e);
  }

  /**
   * Adds the enemy to removeEnemyQueue arraylist
   * which measures which enemies to be removed from window.
   *
   * @param e for Enemy
   */
  public void addRemoveEnemyQueue(Enemy e) {
    this.removeEnemyQueue.add(e);
    // If size is greater than 1 then print message of remaining enemies
    // If less than 1 then Player wins.
    if (this.enemies.size() > 1) {
      System.out.println("ENEMY ELIMINATED! " + (this.enemies.size() - 1) + " Enemies Remaining!");
    }
  }

  /**
   * Remove the enemies from the collection,
   * clear the arraylist to remove from screen.
   */
  public void remove() {
    this.enemies.removeAll(this.removeEnemyQueue);
    this.removeEnemyQueue.clear();
  }

  /**
   * Return size of list.
   *
   * @return size
   */
  public int getSize() {
    return this.enemies.size();
  }

  /**
   * Iterator method that is implemented from interface to
   * iterate the collection of the arraylist.
   *
   * @return new EnemyIterator to implement this method.
   */
  @Override
  public Iterator<Enemy> iterator() {
    return new EnemyIterator<>(this.enemies);
  }
}
