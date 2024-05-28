package org.bcit.comp2522.labs.lab05;

import java.util.ArrayList;

/**
 * Observables are things that can be observed. In publisher/subscriber
 * language, they are the publishers.
 *
 * @author Towa Quimbayo, Alex Gibbison
 * @version 1.0
 */
public abstract class AbstractObservable {
  /**
   * ArrayList of type AbstractObserver.
   */
  protected ArrayList<AbstractObserver> observers;

  /**
   * Abstract method for registering an observer.
   *
   * @param observer for a Boid
   */
  public abstract void registerObserver(Boid observer);

  /**
   * Abstract method for unregistering an observer.
   *
   * @param observer for a Boid
   */
  public abstract void unregisterObserver(Boid observer);

  /**
   * Abstract method for notifying an observer.
   */
  public abstract void notifyObservers();
}
