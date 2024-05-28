package org.bcit.comp2522.labs.lab03.taylor_ji;

import processing.core.PVector;

import java.util.Date;

/**
 * Abstract class that defines character interaction.
 *
 * @author paul_bucci
 * @version 1.0
 */
public abstract class AbstractCharacter implements IDrawable, ICollidable {
  protected PVector position;
  protected PVector direction;
  protected Date lastCollide;
  protected Window window;
  // multiplier for character speed
  protected float speed = 1f;
  // width of the character
  protected float width = 10f;
  // height of the character
  protected float height = 10f;
  // milliseconds before another collide can happen
  protected int collideDelay = 100;

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
    this.lastCollide = new Date();
  }

  /**
   * Gets the bounding box for collision detection.
   *
   * @return an on-the-fly BoundingBox
   */
  @Override
  public BoundingBox getBoundingBox() {
    return new BoundingBox(this.getPosition(), this.getWidth(), this.getHeight());
  }

  /**
   * Returns true if the BoundingBoxes intersect.
   *
   * @param other another onscreen collidable object
   * @return true if this collides with other
   */
  @Override
  public boolean collided(ICollidable other) {
    if (this.equals(other)) {
      return false;
    }

    BoundingBox otherBounds = other.getBoundingBox();
    boolean collides = getBoundingBox().collides(otherBounds);
    Date now = new Date();
    if (collides) {
      if (now.getTime() - this.lastCollide.getTime() > this.collideDelay) {
        this.lastCollide = now;
        return true;
      }
    }
    return false;
  }

  /**
   * Getter method for this.height.
   *
   * @return this.height
   */
  @Override
  public float getHeight() {
    return this.height;
  }

  /**
   * Getter method for this.width.
   *
   * @return this.width
   */
  @Override
  public float getWidth() {
    return this.width;
  }

  /**
   * Getter method for this.position.
   *
   * @return this.position
   */
  @Override
  public PVector getPosition() {
    return this.position;
  }

  /**
   * Setter method for this.width.
   *
   * @param widthIn new width input
   */
  @Override
  public void setWidth(float widthIn) {
    this.width = widthIn;
  }

  /**
   * Setter method for this.height.
   *
   * @param heightIn new height input
   */
  @Override
  public void setHeight(float heightIn) {
    this.height = heightIn;
  }

  /**
   * Rotates the direction vector.
   *
   * @param a angle of rotation in radians (units of Pi)
   */
  public void rotate(float a) {
    this.direction.rotate(a);
  }

  /**
   * Checks to see if the character is out of the window bounds.
   *
   * @param window the current window
   * @return true if the centre point of the character is out of bounds.
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
   * Rotates the direction vector.
   *
   * @param b amount to rotate in radians (fractions of Pi)
   */
  public void bounce(float b) {
    this.direction.rotate(b);
  }

  /**
   * Moves the character by speed amount in current direction.
   *
   * @param window current scene window
   */
  public void move(Window window) {
    this.position = this.position.add(this.direction.mult(speed));
    if (outOfBounds(window)) {
      bounce((float) Math.PI / 4f);
    }
  }
}
