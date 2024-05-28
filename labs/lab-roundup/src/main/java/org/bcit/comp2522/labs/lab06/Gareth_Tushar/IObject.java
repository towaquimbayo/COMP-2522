package org.bcit.comp2522.labs.lab06.Gareth_Tushar;

import processing.core.PVector;

/**
 * Object interface.
 */
public interface IObject {

  boolean collided(IObject c);

  PVector getPosition();

  float getWidth();

  float getHeight();

  BoundingBox getBoundingBox();

  void collideBehaviour(IObject c);
}
