package org.bcit.comp2522.labs.lab03.taylor_ji;

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

  void setWidth(float widthIn);

  void setHeight(float heightIn);

  BoundingBox getBoundingBox();

  void collideBehaviour(ICollidable c);

}
