package org.bcit.comp2522.labs.lab05.noufil_willy;

import processing.core.PVector;

import java.awt.*;
import java.util.ArrayList;


/**
 * Home is the Flock's home position. Also draws circle.
 *
 * @author paul_bucci, Willy Liao, Noufil Saqib
 * @version 1.0
 */
public class Home implements IMoveable {
  private PVector position;
  private PVector velocity;
  private float radius;
  private Color color;

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
   * Checks for each Boid, if it has collided with itself.
   * If it collided, it will unregister the Boid from its previous
   * Flock, and register it into its own Flock.
   *
   * @param window a Window
   * @param moveables an ArrayList
   */
  @Override
  public void run(Window window, ArrayList<IMoveable> moveables) {

  }

  /**
   * Returns the radius.
   *
   * @return radius a float
   */
  @Override
  public float getRadius() {
    return radius;
  }

  /**
   * Returns the velocity.
   *
   * @return velocity a PVector
   */
  @Override
  public PVector getVelocity() {
    return velocity;
  }

  /**
   * Returns the position.
   *
   * @return position a PVector
   */
  @Override
  public PVector getPosition() {
    return position;
  }

  /**
   * Returns the color.
   *
   * @return color a Color
   */
  @Override
  public Color getColor() {
    return color;
  }
}
