package org.bcit.comp2522.labs.lab06.Gareth_Tushar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * This is a class that collects enemy in an arraylist using
 * iterable implementation.
 */
public class EnemyCollection implements Iterable<EnemyCharacter> {

  private List<EnemyCharacter> enemyCollection = new ArrayList<>();

  public void add(EnemyCharacter enemy) {
    enemyCollection.add(enemy);
  }

  public void remove(EnemyCharacter enemy) {
    enemyCollection.remove(enemy);
  }

  @Override
  public Iterator<EnemyCharacter> iterator() {
    return new EnemyIterator<EnemyCharacter>(enemyCollection);
  }

  /**
   * This function helps sort the collection.
   * Check DistanceComparator class for sort metrics.
   */
  public void collectionSort() {
    Collections.sort(enemyCollection, new DistanceComparator());
  }

  /**
   * Return the size of the arraylist.
   * @return
   */
  public int size() {
    return enemyCollection.size();
  }

}
