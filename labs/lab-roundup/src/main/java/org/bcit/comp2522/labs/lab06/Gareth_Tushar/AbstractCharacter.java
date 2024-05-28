package org.bcit.comp2522.labs.lab06.Gareth_Tushar;

import processing.core.PVector;

import java.util.Date;

/**
 * Flocking: An implementation of Craig Reynold's Boids program to
 * simulate the flocking behavior of birds. Each boid steers itself
 * based on rules of avoidance, alignment, and coherence.
 *
 * @author Gareth Ng
 * @author Tushar Ghandi
 * @version 1.0
 */

public abstract class AbstractCharacter extends PVector implements IObject,
     IDrawable, Comparable<AbstractCharacter> {
  protected PVector position;
  protected PVector direction;
  protected Date lastCollide;
  protected Window window;
  protected float speed = 1f;
  protected float width = 10f;
  protected float height = 10f;
  protected int collideDelay = 100;
  protected float distance;

  /**
   * Abstract class for interactable characters.
   *
   * @param position center point of the character
   * @param direction orientation of the character
   * @param window current scene window
   */
  public AbstractCharacter(PVector position, PVector direction, Window window) {
    this.position = position;
    this.direction = direction;
    this.window = window;
  }

  public void setDistance(float currentPos) {
    this.distance = currentPos;
  }

  public float getDistance() {
    return this.distance;
  }

  @Override
  public float getWidth() {
    return this.width;
  }

  public void setWidth(float w) {
    this.width = w;
  }

  @Override
  public float getHeight() {
    return this.height;
  }

  public void setHeight(float h) {
    this.height = h;
  }

  @Override
  public PVector getPosition() {
    return this.position;
  }

  public void setPosition(PVector pos) {
    this.position = pos;
  }

  public PVector getDirection() {
    return this.direction;
  }

  public void setDirection(PVector dir) {
    this.direction = dir;
  }

  public PVector rotate(float r) {
    this.direction.rotate(r);
    return null;
  }

  @Override
  public BoundingBox getBoundingBox() {
    return new BoundingBox(this.getPosition(), this.getWidth(), this.getHeight());
  }

  @Override
  public boolean collided(IObject c) {
    return false;
  }

 /**
  * PlayerCharacter collide behavior that removes
  * FoodCharacter upon collision.
  *
  * @param c Icollidable object
  */
  @Override
  public void collideBehaviour(IObject c) {
//   if (c instanceof EnemyCharacter) {
//    window.removeEnemy((EnemyCharacter) c);
//   }
//   this.setHeight(this.getHeight() + 2);
//   this.setWidth(this.getWidth() + 2);
  }

  /**
   * Check if character is outofbounds,
   * return a boolean.
   * @param window
   * @return true/false.
   */
  public boolean outOfBounds(Window window) {
    if ((this.position.x > window.width
        || this.position.x < 0)
        || (this.position.y > window.height
        || this.position.y < 0)) {
      return true;
    } else {
      return false;
    }
  }

  /**
   * Check if character is touching the wall.
   * return boolean.
   * @param window
   * @return true/false.
   */
  public boolean touchWall(Window window) {
    if ((this.position.x + getWidth() / 2f >= window.width || this.position.x - getWidth() / 2f <= 0)
        || (this.position.y + getHeight() / 2f >= window.height
        || this.position.y - getWidth() / 2f <= 0)) {
      return true;
    } else {
      return false;
    }
  }

}

