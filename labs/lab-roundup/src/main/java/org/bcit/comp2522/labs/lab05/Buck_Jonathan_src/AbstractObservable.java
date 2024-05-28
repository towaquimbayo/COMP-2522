package org.bcit.comp2522.labs.lab05;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Observables are things that can be observed. In publisher/subscriber
 * language, they are the publishers.
 *
 * @author paul_bucci
 * @version 1.0
 */
public abstract class AbstractObservable {
  Collection<AbstractObserver> observers;

  public abstract void registerObserver(AbstractObserver observer, Flock flock, Window window);

  public abstract void unregisterObserver(AbstractObserver observer, Flock flock, Window window);

  public abstract void notifyObservers();
}
