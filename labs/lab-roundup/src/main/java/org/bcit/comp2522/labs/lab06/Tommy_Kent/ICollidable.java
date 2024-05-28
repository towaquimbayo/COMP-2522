package org.bcit.comp2522.labs.lab06.Tommy_Kent;

import processing.core.PVector;

/**
 * Implementing classes are able to collide with each other.
 *
 * @author Tommy_Nguyen
 * @author Kent_Concengco
 * @version 1.0
 */
public interface ICollidable {

  boolean isCollided(ICollidable c);

  void collideEffect(ICollidable c);

  PVector getPosition();

  float getDiameter();

  float getPower();
}
