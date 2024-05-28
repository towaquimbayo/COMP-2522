package org.bcit.comp2522.labs.lab04.navdeep_Lab04;

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
  // convenience for asymetric collision behaviour
  boolean alreadyCollided = false;

  /**
   * Abstract class for interactable characters.
   *
   * @param position  center point of the character
   * @param direction orientation of the character
   * @param window    current scene window
   */
  public AbstractCharacter(PVector position, PVector direction, Window window) {
    this.position = position;
    this.direction = direction;
    this.window = window;
    this.lastCollide = new Date();
  }

  @Override
  public void setAlreadyCollided(boolean alreadyCollided) {
    this.alreadyCollided = alreadyCollided;
  }

  public void setWidth(float width) {
    this.width = width;
  }

  public void setHeight(float height) {
    this.height = height;
  }

  public void setPosition(PVector position) {
    this.position = position.copy();
  }

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
      if (now.getTime() - this.lastCollide.getTime() > this.collideDelay) {
        this.lastCollide = now;
        return true;
      }
    }
    return false;
  }

  @Override
  public float getHeight() {
    return height;
  }

  @Override
  public float getWidth() {
    return width;
  }


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
   * @param a amount to rotate in radians (fractions of Pi)
   */
  public void bounce(float a) {
    this.setDirection(this.getDirection().rotate(a));
  }

  public void setDirection(PVector direction) {
    this.direction = direction.copy();
  }

  /**
   * Moves the character by speed amount in current direction.
   *
   * @param window current scene window
   */
  public void move(Window window) throws CharacterOutOfBoundsException {
    if (outOfBounds(window)) {
      throw new CharacterOutOfBoundsException();
    } else {
      if (touchWall(window)) {
        this.direction.rotate((float) Math.PI / 4f);
      }
      this.setPosition(
          this.getPosition().add(
              this.getDirection().mult(speed)
          )
      );
    }
  }

  /**
   * Checks whether a character is touching a wall.
   * TODO: finish this method to pass the given tests.
   *
   * @param window the current scene
   * @return true if a character is touching the wall
   */

  public boolean touchWall(Window window){
    if ((this.position.x == window.width || this.position.x <= 0)
        || (this.position.y == window.height || this.position.y <= 0)) {
      return true;
    } else if (((this.position.x + this.getWidth() / 2f) == window.width
              || (this.position.x - this.getWidth() / 2f) <= 0)
              || (this.position.y + this.getHeight() / 2f) == window.height
              || (this.position.y - this.getHeight() / 2f) <= 0) {
      return true;
    } else{
    return false;
  }

}

  /** Checks for objects direction for North.
   *
   * @param window is the current scene
   * @return true if object goes north
   */
 public boolean goNorth(Window window){
    if ((this.direction.x == 0)
        && (this.direction.y == -1)) {
      return true;
    } else {
      return false;
    }
 }

  /** Checks for objects direction for South.
   *
   * @param window is the current scene
   * @return true if object goes south
   */
  public boolean goSouth(Window window){
    if ((this.direction.x == 0)
        && (this.direction.y == 1)) {
      return true;
    } else {
      return false;
    }
  }

  /** Checks for objects direction for East.
   *
   * @param window is the current scene
   * @return true if object goes East
   */
  public boolean goEast(Window window){
    if ((this.direction.x == -1)
        && (this.direction.y == 0)) {
      return true;
    } else {
      return false;
    }
  }

  /** Checks for objects direction for West.
   *
   * @param window is the current scene
   * @return true if object goes west
   */
  public boolean goWest(Window window){
    if ((this.direction.x == 1)
        && (this.direction.y == 0)) {
      return true;
    } else {
      return false;
    }
  }

  /** Checks for objects direction for NorthEast.
   *
   * @param window is the window processor
   * @return true if object goes north east
   */
  public boolean goNorthEast(Window window){
    if ((this.direction.x == -1)
      && (this.direction.y == -1)) {
      return true;
    } else {
      return false;
    }
  }

  /** Checks for objects direction for NorthWest.
   *
   * @param window is the window processor
   * @return true if object goes north west
   */
  public boolean goNorthWest(Window window) {
    if ((this.direction.x == 1)
      && (this.direction.y == -1)) {
      return true;
    } else {
      return false;
    }
  }
}