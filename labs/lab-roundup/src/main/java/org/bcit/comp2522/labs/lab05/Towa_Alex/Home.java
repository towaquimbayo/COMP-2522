package org.bcit.comp2522.labs.lab05.Towa_Alex;

import processing.core.PVector;

import java.awt.*;
import java.util.ArrayList;

/**
 * Home is the Flock's home position. Also draws circle.
 *
 * @author Towa Quimbayo, Alex Gibbison
 * @version 1.0
 */
public class Home implements IMoveable {
  /** PVector position. */
  protected PVector position;
  /** PVector velocity. */
  private final PVector velocity;
  /** Instance variable for radius of home. */
  private final float radius;
  /** Instance variable for color of home. */
  private final Color color;

  /**
   * Constructor for Home.
   *
   * @param position on window screen
   * @param velocity of the home (if they should move)
   * @param radius size of the Home for drawing, colliding and clicking
   * @param color RGB color
   */
  public Home(PVector position, PVector velocity, float radius, Color color) {
    this.position = position;
    this.velocity = velocity;
    this.radius = radius;
    this.color = color;
  }

  /**
   * Run method for home to run on window.
   *
   * @param window for window
   * @param moveables for IMoveable objects
   */
  @Override
  public void run(Window window, ArrayList<IMoveable> moveables) {}

  /**
   * Get Radius method for returning radius of home.
   *
   * @return radius of home
   */
  @Override
  public float getRadius() {
    return this.radius;
  }

  /**
   * Get velocity method for returning velocity of home.
   *
   * @return for velocity
   */
  @Override
  public PVector getVelocity() {
    return this.velocity;
  }

  /**
   * Get position method for returning position of home.
   *
   * @return for position
   */
  @Override
  public PVector getPosition() {
    return this.position;
  }

  /**
   * Get color method for returning color of home.
   *
   * @return for color
   */
  @Override
  public Color getColor() {
    return this.color;
  }

  /**
   * Equals method for home.
   *
   * @param h for home
   * @return true if both home positions are equal
   */
  public boolean equals(Home h) {
    return (h.position == this.position);
  }

  /**
   * Hashcode method for Home.
   *
   * @return the product of position x,y and radius values
   */
  public int hashCode() {
    return (int) (this.position.x * this.position.y * this.radius);
  }

  /**
   * toString method to print this Home.
   *
   * @return this home's x position and y position and color
   */
  public String toString() {
    return String.format("X Position: " + (int) this.position.x
            + " Y Position: " + (int) this.position.y
            + " Color: " + this.color);
  }
}
