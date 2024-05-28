package org.bcit.comp2522.labs.lab06;

import java.util.ArrayList;

/**
 * AbstractObservable class that implements
 * Observable pattern for Player.
 *
 * @author Towa Quimbayo, Alex Gibbison
 * @version 1.0
 */
public abstract class AbstractObservable {

  /** ArrayList of Abstract observers. */
  protected ArrayList<AbstractObserver> observers;

  /**
   * Registering an observer.
   *
   * @param obs for Enemy
   */
  public abstract void registerObserver(AbstractObserver obs);

  /**
   * Unregistering an observer.
   *
   * @param obs for Enemy
   */
  public abstract void unregisterObserver(AbstractObserver obs);

  /** Notifying an observer.
   *
   * @param obs for Enemy
   */
  public abstract void notifyObserver(AbstractObserver obs);
}
