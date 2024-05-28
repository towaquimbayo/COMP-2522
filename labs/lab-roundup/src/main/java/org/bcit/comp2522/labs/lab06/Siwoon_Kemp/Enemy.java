package org.bcit.comp2522.labs.lab06;

import java.awt.Color;
import java.util.Date;
import processing.core.PVector;

/**
 * The enemy class.
 */
public class Enemy extends AbstractObserver implements Comparable {

  /**
   * Size of the enemy.
   */
  private float size;

  /**
   * Position of the enemy.
   */
  private PVector position;

  /**
   * Moving direction of the enemy.
   */
  private PVector direction;

  /**
   * The original size of the enemy.
   */
  private float originalSize;

  /**
   * Moving speed of the enemy.
   */
  private float speed = 1f;

  /**
   * Original color of the enemy.
   */
  private Color color = Color.orange;

  /**
   * This window.
   */
  private Window window;

  /**
   * Record last collide time.
   */
  private Date lastCollide;

  /**
   * Delay the collision.
   */
  private int collideDelay = 130;

  /**
   * Enemy constructor.
   *
   * @param position as PVector.
   *
   * @param direction as PVector.
   *
   * @param size as float.
   *
   * @param window as Window.
   *
   */
  public Enemy(PVector position, PVector direction, float size, Window window) {
    this.position = position;
    this.direction = direction;
    this.size = size;
    this.window = window;
    this.originalSize = size;
    this.lastCollide = new Date();
  }

  /**
   * Setter for size.
   *
   * @param size as float.
   *
   */
  public void setSize(float size) {
    this.size = size;
  }

  /**
   * Setter for colour.
   *
   * @param color as color object.
   *
   */
  public void setColour(Color color) {
    this.color = color;
  }

  /**
   * Bounce that changes the direction of the enemy.
   *
   * @param b as float.
   */
  public void bounce(float b) {
    this.direction.rotate(b);
  }

  /**
   * Check if collision happens.
   *
   * @param other as Enemy object.
   *
   * @return boolean.
   */
  public boolean collided(Enemy other) {
    if (this.equals(other)) {
      return false;
    } else if (this.position.dist(other.getPosition())
            <= ((this.size / 2) + (other.getSize() / 2))) {
      Date now = new Date();
      if (now.getTime() - this.lastCollide.getTime() > this.collideDelay) {
        this.lastCollide = now;
        return true;
      }
    }
    return false;
  }

  /**
   * Getter for size.
   *
   * @return size as float.
   */
  public float getSize() {
    return this.size;
  }

  /**
   * Getter for position.
   *
   * @return position as PVector.
   */
  public PVector getPosition() {
    return this.position;
  }

  /**
   * The behaviour of the enemy when collision happens.
   */
  public void collideBehaviour() {
    this.bounce((float) Math.PI / 2f);
  }

  /**
   * The size of the enemy changes based on the distance to the player.
   */
  public void distanceBehaviour() {
    float distance = this.position.dist(new PVector(window.mouseX, window.mouseY));
    if (distance < 100) {
      float index = ((1 / distance) * 100);
      if (index >= 1.4f) {
        index = 1.4f;
      }
      this.size = originalSize * index;
    }
  }

  /**
   * Check if the enemy is out of window.
   *
   * @param window as Window.
   *
   * @return boolean.
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
   * Move the enemy.
   *
   * @param window as Window.
   */
  void move(Window window) {
    this.position = this.position.add(this.direction.mult(speed));
    if (outOfBounds(window)) {
      bounce((float) Math.PI / 4f);
    }
    //Check the distance to the player
    if (this.position.dist(window.player.getPosition()) <= 100) {
      window.player.registerObserver(this);
    } else if (this.position.dist(window.player.getPosition()) > 100) {
      window.player.unregisterObserver(this);
    }
  }

  /**
   * Draw the enemy.
   *
   * @param window as Window.
   */
  void draw(Window window) {
    window.fill(this.color.getRGB());
    window.ellipse(this.position.x, this.position.y, this.size, this.size);
  }

  /**
   * Compare the enemy with the player.
   *
   * @param o the object to be compared.
   *
   * @return int.
   */
  @Override
  public int compareTo(Object o) {
    return (int) (this.size - ((Player) o).getSize());
  }

  /**
   * Update the direction of the enemy.
   *
   * @param position as PVector.
   *
   * @param size as float.
   */
  @Override
  public void update(PVector position, float size) {
    if (this.size < size) {
      //Enemy is escaping
      this.direction = this.position.copy().add(position.copy().mult(-1f)).normalize();
      this.color = Color.green;
    } else if (this.size > size) {
      //Enemy is approaching
      this.direction = position.copy().add(this.position.copy().mult(-1f)).normalize();
      this.color = Color.red;
    }
  }

  /**
   * Update the color of the enemy.
   * Reset it to the original color.
   */
  @Override
  public void update() {
    this.color = Color.orange;
  }
}
