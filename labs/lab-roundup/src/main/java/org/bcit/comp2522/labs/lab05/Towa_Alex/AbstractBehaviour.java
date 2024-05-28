package org.bcit.comp2522.labs.lab05.Towa_Alex;

import java.util.ArrayList;

/**
 * AbstractBehaviour class. Concrete instantiations will provide a
 * behaviour for IMoveables relative to other IMoveables.
 *
 * @author Towa Quimbayo, Alex Gibbison
 * @version 1.0
 */
public abstract class AbstractBehaviour {
  /**
   * Abstract method for Recalculate.
   *
   * @param moveables for IMoveable list
   */
  public abstract void recalculate(ArrayList<IMoveable> moveables);

  /**
   * Abstract method for move.
   */
  public abstract void move();
}
