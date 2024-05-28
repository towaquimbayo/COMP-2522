package org.bcit.midterm.answers;

import java.util.ArrayList;

public abstract class Advanced_04_AbstractObservable {

  protected ArrayList<Advanced_04_AbstractObserver> observers;
  public abstract void registerObserver(Advanced_04_AbstractObserver obs);
  public abstract void unregisterObserver(Advanced_04_AbstractObserver obs);
  public abstract void notifyObservers(String s);
}
