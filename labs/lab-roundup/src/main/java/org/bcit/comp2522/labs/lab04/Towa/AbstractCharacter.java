package org.bcit.comp2522.labs.lab04.Towa;

import processing.core.PVector;

import java.awt.*;
import java.util.Date;

/**
 * Abstract class that defines character interaction.
 *
 * @author Towa Quimbayo
 * @version 1.0, September 29, 2022
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
  // Four Floating Value
  private static final float FOUR = 4f;
  // convenience for asymmetric collision behaviour
  boolean alreadyCollided = false;
  // Color of the object
  protected Color color;

  /**
   * Abstract class for intractable characters.
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
   * Set already collided to true if collided.
   *
   * @param alreadyCollided for checking if already collided.
   */
  @Override
  public void setAlreadyCollided(boolean alreadyCollided) {
    this.alreadyCollided = alreadyCollided;
  }

  /**
   * Set Width of the character.
   *
   * @param width for width
   */
  public void setWidth(float width) {
    this.width = width;
  }

  /**
   * Set Height of the character.
   *
   * @param height for height
   */
  public void setHeight(float height) {
    this.height = height;
  }

  /**
   * Set Position of the character.
   *
   * @param position for position
   */
  public void setPosition(PVector position) {
    this.position = position.copy();
  }

  /**
   * Get Direction of the character.
   *
   * @return a copy of this character's direction
   */
  @Override
  public PVector getDirection() {
    return this.direction.copy();
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
   * Getter for the height.
   *
   * @return height
   */
  @Override
  public float getHeight() {
    return height;
  }

  /**
   * Getter for the width.
   *
   * @return width
   */
  @Override
  public float getWidth() {
    return width;
  }

  /**
   * Getter for the position.
   *
   * @return position
   */
  @Override
  public PVector getPosition() {
    return this.position.copy();
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
   * Compare the two objects for equality.
   *
   * @param a for Abstract Character
   * @return true if both objects are equal in width and height
   */
  public boolean equals(final AbstractCharacter a) {
    return ((int) a.width == (int) this.width && (int) a.height == (int) this.height);
  }

  /**
   * Sets the hashcode for the AbstractCharacter.
   *
   * @return the value of width * height
   */
  public int hashCode() {
    return ((int) this.width * (int) this.height);
  }

  /**
   * toString method for printing the Character.
   *
   * @return String value for the sum of Position X and Y, Width and Height
   */
  public String toString() {
    return String.format("Character: %d;", (int) this.position.x + (int) this.position.y
            + (int) this.width + (int) this.height);
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
   * @param a amount to rotate in radians (fractions of Pi)
   */
  public void bounce(float a) {
    this.setDirection(this.getDirection().rotate(a));

    float newDirectionX = Math.round(this.getDirection().x);
    float newDirectionY = Math.round(this.getDirection().y);

    PVector newDirectionVector = new PVector(newDirectionX, newDirectionY);

    this.setDirection(newDirectionVector);
  }

  /**
   * Sets the direction by creating a copy of the new PVector direction.
   *
   * @param direction for PVector direction value
   */
  public void setDirection(PVector direction) {
    this.direction = direction.copy();
  }

  /**
   * Moves the character by speed amount in current direction.
   *
   * @param window current scene window
   */
  public void move(Window window) throws CharacterOutOfBoundsException, TailOutOfBoundsException {
    if (outOfBounds(window)) {
      throw new CharacterOutOfBoundsException();
    } else {
      if (touchWall(window)) {
        this.direction.rotate((float) Math.PI / FOUR);
      }
      this.setPosition(this.getPosition().add(this.getDirection().mult(SPEED)));
    }
  }

  /**
   * Checks whether a character is touching a wall.
   *
   * @param window the current scene
   * @return true if a character is touching the wall
   */
  public boolean touchWall(Window window) {
    return (this.getBoundingBox().getRight() >= window.width
            || this.getBoundingBox().getLeft() <= 0
            || this.getBoundingBox().getBottom() >= window.height
            || this.getBoundingBox().getTop() <= 0);
  }
}
