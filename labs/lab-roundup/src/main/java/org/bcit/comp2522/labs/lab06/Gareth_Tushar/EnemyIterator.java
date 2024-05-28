package org.bcit.comp2522.labs.lab06.Gareth_Tushar;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * This is the custom iterator class.
 * @param <EnemyCharacter>
 */
public class EnemyIterator<EnemyCharacter> implements Iterator {

  private int indexPosition = 0;

  List<EnemyCharacter> internalList;

  public EnemyIterator(List<EnemyCharacter> internalList) {
    this.internalList = internalList;
  }

  /**
   * Check if the arraylist contains an object,
   * return boolean.
   * @return true/false.
   */
  @Override
  public boolean hasNext() {
    if (indexPosition >= internalList.size()) {
      return false;
    } else {
      return true;
    }

  }

  /**
   * Get the next object in the array.
   * @return
   */
  @Override
  public EnemyCharacter next() {
    if (!hasNext()) {
      throw new NoSuchElementException();
    }
    EnemyCharacter val = this.internalList.get(indexPosition);
    indexPosition++;
    return val;
  }
}
