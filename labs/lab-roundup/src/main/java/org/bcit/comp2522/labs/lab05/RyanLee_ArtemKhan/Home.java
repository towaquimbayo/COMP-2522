package org.bcit.comp2522.labs.lab05.RyanLee_ArtemKhan;

import java.awt.Color;
import java.util.ArrayList;

import org.bcit.comp2522.labs.lab05.BoundingBox;
import org.bcit.comp2522.labs.lab05.IMoveable;
import org.bcit.comp2522.labs.lab05.ITouchable;
import org.bcit.comp2522.labs.lab05.Window;
import processing.core.PVector;


/**
 * Home is the Flock's home position. Also draws circle.
 *
 * @author paul_bucci
 * @version 1.0
 */
public class Home implements IMoveable, ITouchable {
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


  @Override
  public void run(Window window, ArrayList<IMoveable> moveables) {

  }

  @Override
  public float getRadius() {
    return radius;
  }

  @Override
  public PVector getVelocity() {
    return velocity;
  }

  @Override
  public PVector getPosition() {
    return position;
  }

  @Override
  public Color getColor() {
    return color;
  }

  @Override
  public BoundingBox getBoundingBox() {
    return new BoundingBox(this.getPosition(), this.getRadius());
  }
}
