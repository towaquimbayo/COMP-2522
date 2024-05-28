package org.bcit.comp2522.labs.lab06;

import processing.core.PVector;

/**
 * AbstractObserver class that implements
 * Observable pattern for Enemy.
 *
 * @author Towa Quimbayo, Alex Gibbison
 * @version 1.0
 */
public abstract class AbstractObserver {

  /**
   * Update each observer.
   *
   * @param pos for a position of player
   * @param e for AbstractObserver
   */
  public abstract void update(PVector pos, AbstractObserver e);
}
