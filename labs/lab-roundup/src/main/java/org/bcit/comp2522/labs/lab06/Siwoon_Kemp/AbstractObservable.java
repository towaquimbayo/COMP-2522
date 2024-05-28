package org.bcit.comp2522.labs.lab06;

import java.util.ArrayList;

/**
 * The AbstractObservable class that defines the objects that can be observed.
 */
public abstract class AbstractObservable {

  ArrayList<AbstractObserver> observers;

  public abstract void registerObserver(AbstractObserver observer);

  public abstract void unregisterObserver(AbstractObserver observer);

  public abstract void notifyObservers();

}
