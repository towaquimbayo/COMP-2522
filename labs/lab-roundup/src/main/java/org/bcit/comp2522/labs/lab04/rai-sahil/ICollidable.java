package org.bcit.comp2522.labs.lab04;

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

  PVector getDirection();

  void setDirection(PVector direction);

  float getWidth();

  float getHeight();

  BoundingBox getBoundingBox();

  boolean collideBehaviour(ICollidable c);

  void setAlreadyCollided(boolean b);

  boolean towardsSouth(Window window);

  boolean towardsNorth(Window window);

  boolean towardsEast(Window window);

  boolean towardsWest(Window window);
}
