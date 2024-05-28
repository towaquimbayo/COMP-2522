package org.bcit.comp2522.labs.lab03.kent_linh;

import processing.core.PVector;

/**
 * Implementing classes are able to move.
 *
 * @author kent concengco, linh nguyen
 * @version 1.0 September 28, 2022
 */
public interface IMovable {

  void move(Window window);

  PVector getPosition();
}
