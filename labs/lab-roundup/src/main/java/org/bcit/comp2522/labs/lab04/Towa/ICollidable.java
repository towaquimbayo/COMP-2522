package org.bcit.comp2522.labs.lab04.Towa;

import processing.core.PVector;

/**
 * Implementing classes are able to collide with each other.
 *
 * @author Towa Quimbayo
 * @version 1.0, September 29, 2022
 */
public interface ICollidable {
  /**
   * Collided Abstract method.
   *
   * @param c for Collidable
   */
  boolean collided(ICollidable c);

  /**
   * Get Position Abstract method.
   */
  PVector getPosition();

  /**
   * Get Direction Abstract method.
   */
  PVector getDirection();

  /**
   * Set Direction Abstract method.
   *
   * @param direction for PVector direction
   */
  void setDirection(PVector direction);

  /**
   * Get Width Abstract method.
   */
  float getWidth();

  /**
   * Get Height Abstract method.
   */
  float getHeight();

  /**
   * Get BoundingBox Abstract method.
   */
  BoundingBox getBoundingBox();

  /**
   * CollideBehavior Abstract method.
   *
   * @param c for Collidable
   */
  void collideBehaviour(ICollidable c);

  /**
   * SetAlreadyCollided Abstract method.
   *
   * @param b for true if collided
   */
  void setAlreadyCollided(boolean b);
}
