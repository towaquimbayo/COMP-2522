package ca.bcit.comp2522.labs.lab03;

import processing.core.PVector;

/**
 * Implementing classes are able to collide with each other.
 *
 * @author Towa, Dinuja
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
