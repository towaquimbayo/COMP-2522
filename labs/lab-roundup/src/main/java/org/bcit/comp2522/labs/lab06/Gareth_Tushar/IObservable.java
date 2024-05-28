package org.bcit.comp2522.labs.lab06.Gareth_Tushar;

/**
 * Observable interface class, use for
 * Player class.
 */
public interface IObservable {
  void registerObserver(IObserver observer);
  void unregisterObserver(IObserver observer);
  void notifyObservers();

}
