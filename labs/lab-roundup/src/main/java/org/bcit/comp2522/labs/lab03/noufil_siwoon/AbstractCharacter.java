package org.bcit.comp2522.labs.lab03.noufil_siwoon;

import processing.core.PVector;

import java.awt.*;
import java.util.Date;

/**
 * Abstract class that defines character interaction.
 *
 * @author paul_bucci & Siwoon Lim & Noufil Saqib
 * @version 1.0
 */
public abstract class AbstractCharacter implements IDrawable, ICollidable {
  protected PVector position;
  protected PVector direction;
  protected Date lastCollide;
  protected Window window;
  // multiplier for character speed
  protected static final float SPEED = 1f;
  // width of the character
  protected float width = 10f;
  // height of the character
  protected float height = 10f;
  // milliseconds before another collide can happen
  protected static final int COLLIDE_DELAY = 100;
  // Float value for bounce off walls of window
  protected static final float BOUNCE_VAL = 4f;
  protected Color color;

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
      if (now.getTime() - this.lastCollide.getTime() > COLLIDE_DELAY) {
        this.lastCollide = now;
        return true;
      }
    }
    return false;
  }

  /**
   * Gets the height.
   *
   * @return height
   */
  @Override
  public float getHeight() {
    return height;
  }

  /**
   * Gets the width.
   *
   * @return width
   */
  @Override
  public float getWidth() {
    return width;
  }

  /**
   * Gets the position.
   *
   * @return position
   */
  @Override
  public PVector getPosition() {
    return position;
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
    return ((this.position.x > window.width || this.position.x < 0)
            || (this.position.y > window.height || this.position.y < 0));
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
    this.position = this.position.add(this.direction.mult(SPEED));
    if (outOfBounds(window)) {
      bounce((float) Math.PI / BOUNCE_VAL);
    }
  }
}
