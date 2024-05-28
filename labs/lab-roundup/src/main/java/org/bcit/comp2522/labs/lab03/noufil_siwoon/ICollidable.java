package org.bcit.comp2522.labs.lab03.noufil_siwoon;

import processing.core.PVector;

/**
 * Implementing classes are able to collide with each other.
 *
 * @author paul_bucci
 * @version 1.0
 */
public interface ICollidable {
  boolean collided(ICollidable c);

  PVector getPosition();

  float getWidth();

  float getHeight();

  BoundingBox getBoundingBox();

  void collideBehaviour(ICollidable c);

}
