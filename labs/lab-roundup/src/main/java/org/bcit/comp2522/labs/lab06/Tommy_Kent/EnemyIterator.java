package org.bcit.comp2522.labs.lab06.Tommy_Kent;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * An iterator that iterators through the EnemyCollection
 * based on the distance metric.
 *
 * @param <Enemy> the enemy
 * @author Tommy_Nguyen
 * @author Kent_Concengco
 * @version 1.0
 */
public class EnemyIterator<Enemy> implements Iterator<Enemy> {

  ArrayList<Enemy> list;

  int currIndex;

  /**
   * Creates an instance of EnemyIterator where the enemies are sorted based on their
   * distanceMetric.
   *
   * @param list the list of the enemies
   */
  public EnemyIterator(ArrayList<Enemy> list) {
    this.list = list;
    list.sort((o1, o2) -> {
      org.bcit.comp2522.labs.lab06.Enemy e1 = (org.bcit.comp2522.labs.lab06.Enemy) o1;
      org.bcit.comp2522.labs.lab06.Enemy e2 = (org.bcit.comp2522.labs.lab06.Enemy) o2;
      return (int) (e1.getDistanceFromSource() - e2.getDistanceFromSource());
    });
    currIndex = 0;
  }

  @Override
  public boolean hasNext() {
    return currIndex < list.size();
  }

  @Override
  public Enemy next() {
    Enemy temp = list.get(currIndex);
    this.currIndex++;
    return temp;
  }
}
