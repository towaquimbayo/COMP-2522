package org.bcit.comp2522.labs.lab03.kent_linh;


/**
 * Implementing classes are able to collide with each other.
 *
 * @author paul_bucci
 * @author kent_concengco, linh_nguyen
 * @version 1.0
 * @version 2.0 September 28, 2022
 */
public interface ICollidable {
  boolean collided(ICollidable c);

  float getWidth();

  float getHeight();

  BoundingBox getBoundingBox();

  void collideBehaviour(ICollidable c);

}
