package org.bcit.comp2522.labs.lab05.Lester_Betty_src;

import processing.core.PVector;

import java.awt.*;
import java.util.ArrayList;

/**
 * Home is the Flock's home position. Also draws circle.
 *
 * @author paul_bucci
 * @version 1.0
 */
public class Home implements Imoveable {
  private final PVector position;
  private final PVector velocity;
  private final float radius;
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
   * Updates Home's position in Window (unused)
   *
   * @param window the Window which contains IMoveable object
   * @param moveables All IMoveables in window
   */
  @Override
  public void run(Window window, ArrayList<Imoveable> moveables) {}

  /**
   * Get the Home's radius
   *
   * @return Home's radius as a float
   */
  @Override
  public float getRadius() {
    return radius;
  }

  /**
   * Get the Home's velocity
   *
   * @return Home's velocity as a PVector
   */
  @Override
  public PVector getVelocity() {
    return velocity;
  }

  /**
   * Get the Home's position
   *
   * @return Home's position as a PVector
   */
  @Override
  public PVector getPosition() {
    return position;
  }

  /**
   * Get the Home's Color
   *
   * @return Home's color as a Color
   */
  @Override
  public Color getColor() {
    return color;
  }
}
