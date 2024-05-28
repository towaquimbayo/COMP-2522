package org.bcit.comp2522.labs.lab05.RyanLee_ArtemKhan;

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

  public abstract void registerObserver(AbstractObserver observer);

  public abstract void unregisterObserver(AbstractObserver observer);

  public abstract void notifyObservers();
}
