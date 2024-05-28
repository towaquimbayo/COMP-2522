package org.bcit.comp2522.labs.lab06.Stanley_Brandon;

import processing.core.PVector;

/**
 * Interface that sets up a movable object.
 */
public interface IMoveable {

  PVector getPosition();

  PVector getDirection();

  float getSpeed();

  float getRadius();

  void setPosition(PVector p);

  void setDirection(PVector d);

  void setSpeed(float s);

  void setRadius(float r);

  void run(Window window);


}
