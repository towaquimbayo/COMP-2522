package org.bcit.comp2522.labs.lab05;

/**
 * Observers are doing the looking, i.e., they are the subscribers.
 *
 * @author Towa Quimbayo, Alex Gibbison
 * @version 1.0
 */
public abstract class AbstractObserver {
  /**
   * Abstract method for updating the observer.
   *
   * @param msg for a String msg
   */
  public abstract void update(String msg);
}
