package org.bcit.comp2522.labs.lab06.Tommy_Kent;

import processing.core.PVector;

import java.awt.*;

/**
 * Observers are updated upon the notification of Observable
 * , i.e. they are the subscribers.
 *
 * @author Tommy_Nguyen
 * @author Kent_Concengco
 * @version 1.0
 */
public abstract class Observer extends AbstractCharacter {

  public Observer(float speed, float power, PVector pin, PVector dir, float din, Color cin,
                  Window win) {
    super(speed, power, pin, dir, din, cin, win);
  }

  void update(PVector p, float power) {}
}
