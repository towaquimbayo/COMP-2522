package org.bcit.comp2522.labs.lab05.Andrew_Jason;

/**
 * Observers are doing the looking, i.e., they are the subscribers.
 *
 * @author paul_bucci
 * @version 1.0
 */
public abstract class AbstractObserver {
  public abstract void update(Object msg);
}
