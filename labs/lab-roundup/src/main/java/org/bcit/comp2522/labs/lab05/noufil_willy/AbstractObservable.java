package org.bcit.comp2522.labs.lab05.noufil_willy;

import java.util.ArrayList;

/**
 * Observables are things that can be observed. In publisher/subscriber
 * language, they are the publishers.
 *
 * @author paul_bucci
 * @version 1.0
 */
public abstract class AbstractObservable {
  ArrayList<AbstractObserver> observers;

  public abstract void registerObserver(AbstractObserver observer);

  public abstract void unregisterObserver(AbstractObserver observer);

  public abstract void notifyObservers(Object msg);
}
