package org.bcit.comp2522.labs.lab05.Lester_Betty_src;

/**
 * Observers are doing the looking, i.e., they are the subscribers.
 *
 * @author paul_bucci
 * @author Betty Nguyen, Lester Shun
 * @version 1.0
 * @version October 9, 2022
 */
public abstract class AbstractObserver {
  public abstract void update(AbstractBehaviour behaviour);
}
