package org.bcit.comp2522.labs.lab06.Tommy_Kent;

import processing.core.PVector;

import java.awt.*;

/**
 * Observables are things that can be observed. In publisher/subscriber
 * language, they are the publishers.
 *
 * @author Tommy_Nguyen
 * @author Kent_Concengco
 * @version 1.0
 */
public abstract class Observable extends AbstractCharacter {

  public Observable(float speed, float power, PVector pin, PVector dir, float din, Color cin,
                    Window win) {
    super(speed, power, pin, dir, din, cin, win);
  }

  void registerObserver(Observer o) {}

  void unregisterObserver(Observer o) {}

  void notifyObservers() {}
}
