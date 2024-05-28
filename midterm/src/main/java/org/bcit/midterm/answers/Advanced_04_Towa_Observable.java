package org.bcit.midterm.answers;

import java.util.ArrayList;

public class Advanced_04_Towa_Observable extends Advanced_04_AbstractObservable {

  public Advanced_04_Towa_Observable() {
    this.observers = new ArrayList<>();
  }
  @Override
  public void registerObserver(Advanced_04_AbstractObserver obs) {
    this.observers.add(obs);
  }

  @Override
  public void unregisterObserver(Advanced_04_AbstractObserver obs) {
    this.observers.remove(obs);
  }

  @Override
  public void notifyObservers(String s) {
    this.observers.forEach(observer -> observer.update(s));
  }
}
