package org.bcit.comp2522.labs.lab05.RyanLee_ArtemKhan;

import java.awt.Color;
import java.util.ArrayList;

import org.bcit.comp2522.labs.lab05.*;
import processing.core.PVector;


/**
 * The Boid class manages the properties of the Boids.
 *
 * @author paul_bucci (based on Daniel Shiffman's code)
 * @author Artem Khan
 * @author Ryan Lee
 * @version 1.0
 * @Date: October 2022
 */
public class Boid extends AbstractObserver implements IMoveable {
  private PVector position;
  private PVector velocity;
  private PVector acceleration;
  private Color color;
  private float radius;
  private float maxforce;    // Maximum steering force
  private float maxspeed;    // Maximum speed

  private AbstractBehaviour behaviour;

  /**
   * Constructor for a Boid.
   *
   * @param x should be within window size limits
   * @param y should be within window size limits
   * @param color RGB colour
   */
  public Boid(float x, float y, Color color) {
    super(x, y, color);
    this.acceleration = new PVector(0, 0);
    float angle = (float) (Math.random() * Math.PI * 2);
    this.velocity = new PVector((float) Math.cos(angle), (float) Math.sin(angle));
    this.position = new PVector(x, y);
    this.color = color;
    this.radius = 2.0f;
    this.maxspeed = 2;
    this.maxforce = 0.03f;
    this.behaviour = new FlockingBehaviour(this);
  }

  /**
   * Method that updates the behaviour of the boid.
   *
   * @param home is the boid's new home.
   * @param toggled determines if the home has been clicked.
   */
  @Override
  public void update(Object home, boolean toggled) {
    if (toggled == true) {
      this.behaviour = new HomeBehaviour(this, (Home) home);
    } else {
      this.behaviour = new FlockingBehaviour(this);
    }
  }

  /**
   * Function that calls the boid's behaviour move and recalculate.
   *
   * @param window is the current window.
   * @param moveables is the array of boids.
   */
  public void run(Window window, ArrayList<IMoveable> moveables) {
    behaviour.recalculate(moveables);
    behaviour.move();
  }

  // Getters and Setters

  /**
   * Constructor for AbstractBehaviour.
   *
   * @return this behaviour.
   */
  public AbstractBehaviour getBehaviour() {
    return this.behaviour;
  }

  /**
   * Getter function for boid's colour.
   *
   * @return the colour.
   */
  @Override
  public Color getColor() {
    return color;
  }

  /**
   * Setter for the boid's acceleration.
   *
   * @param acceleration is the boid's new acceleration.
   */
  public void setAcceleration(PVector acceleration) {
    this.acceleration = acceleration;
  }

  /**
   * Setter for the boid's colour.
   *
   * @param color is the boid's new colour.
   */
  public void setColor(Color color) {
    this.color = color;
  }

}
