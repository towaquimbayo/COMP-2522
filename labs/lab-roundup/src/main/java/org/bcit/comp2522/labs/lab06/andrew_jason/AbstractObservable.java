package org.bcit.comp2522.labs.lab06.andrew_jason;

import org.bcit.comp2522.labs.lab06.AbstractObserver;

import java.util.Collection;

public abstract class AbstractObservable {
  Collection<org.bcit.comp2522.labs.lab06.AbstractObserver> observers;

  public abstract void registerObserver(org.bcit.comp2522.labs.lab06.AbstractObserver observer);

  public abstract void unregisterObserver(AbstractObserver observer);

  public abstract void notifyObservers();
}
