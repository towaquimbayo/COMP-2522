package org.bcit.comp2522.labs.lab04.navdeep_Lab04;

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

  void collideBehaviour(ICollidable c);

  void setAlreadyCollided(boolean b);

  public boolean goNorth(Window window);

  public boolean goSouth(Window window);

  public boolean goEast(Window window);

  public boolean goWest(Window window);

  public boolean goNorthEast(Window window);

  public boolean goNorthWest(Window window);

}
