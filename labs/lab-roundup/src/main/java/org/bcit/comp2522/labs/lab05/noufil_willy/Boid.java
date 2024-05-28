package org.bcit.comp2522.labs.lab05.noufil_willy;

import processing.core.PVector;

import java.awt.*;
import java.util.ArrayList;


/**
 * The Boid class manages the properties of the Boids.
 *
 * @author paul_bucci (based on Daniel Shiffman's code)
 * @version 1.0
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
    this.acceleration = new PVector(0, 0);
    float angle = (float) (Math.random() * Math.PI * 2);
    this.velocity = new PVector((float) Math.cos(angle), (float) Math.sin(angle));
    this.position = new PVector(x, y);
    this.color = color;
    this.radius = 2.0f;
    this.maxspeed = 1.4f;
    this.maxforce = 0.03f;
    this.behaviour = new FlockingBehaviour(this);
  }

  /**
   * Updates the boids behaviour or state based on the message received.
   *
   * @param msg an Object
   */
  @Override
  public void update(Object msg) {
    if (msg instanceof HomeBehaviour && getBehaviour() instanceof FlockingBehaviour) {
      setBehaviour((AbstractBehaviour) msg);
    }

    if (msg instanceof FlockingBehaviour && getBehaviour() instanceof HomeBehaviour) {
      setBehaviour((AbstractBehaviour) msg);
    }

    if (msg instanceof Color) {
      setColor((Color) msg);
    }
  }

  /**
   * Recalculates the state of the Boid and moves it.
   *
   * @param window a Window
   * @param moveables an ArrayList of IMoveable
   */
  public void run(Window window, ArrayList<IMoveable> moveables) {
    behaviour.recalculate(moveables);
    behaviour.move();
  }

  // Getters and Setters
  /**
   * Returns the current behaviour.
   *
   * @return behaviour an AbstractBehaviour
   */
  public AbstractBehaviour getBehaviour() {
    return behaviour;
  }

  /**
   * Returns the radius.
   *
   * @return radius a float
   */
  public float getRadius() {
    return radius;
  }

  /**
   * Returns the maxforce.
   *
   * @return maxforce a float
   */
  public float getMaxforce() {
    return maxforce;
  }

  /**
   * Returns the maxspeed.
   *
   * @return maxspeed a float
   */
  public float getMaxspeed() {
    return maxspeed;
  }

  /**
   * Returns the current position.
   *
   * @return position a PVector
   */
  public PVector getPosition() {
    return position;
  }

  /**
   * Returns the current velocity.
   *
   * @return velocity a PVector
   */
  public PVector getVelocity() {
    return velocity;
  }

  /**
   * Returns the current acceleration.
   *
   * @return acceleration a PVector
   */
  public PVector getAcceleration() {
    return acceleration;
  }

  /**
   * Returns the current color.
   *
   * @return color a Color
   */
  @Override
  public Color getColor() {
    return color;
  }

  /**
   * Sets the behaviour to the specified value.
   *
   * @param behaviour an AbstractBehaviour
   */
  public void setBehaviour(AbstractBehaviour behaviour) {
    this.behaviour = behaviour;
  }

  /**
   * Sets the radius to the specified value.
   *
   * @param radius a float
   */
  public void setRadius(float radius) {
    this.radius = radius;
  }

  /**
   * Sets the maxspeed to the specified value.
   *
   * @param maxspeed a float
   */
  public void setMaxspeed(float maxspeed) {
    this.maxspeed = maxspeed;
  }

  /**
   * Sets the maxforce to the specified value.
   *
   * @param maxforce a float
   */
  public void setMaxforce(float maxforce) {
    this.maxforce = maxforce;
  }

  /**
   * Sets the position to the specified value.
   *
   * @param position PVector
   */
  public void setPosition(PVector position) {
    this.position = position;
  }

  /**
   * Sets the velocity to the specifid value.
   *
   * @param velocity a PVector
   */
  public void setVelocity(PVector velocity) {
    this.velocity = velocity;
  }

  /**
   * Sets the acceleration to the specified value.
   *
   * @param acceleration a PVector
   */
  public void setAcceleration(PVector acceleration) {
    this.acceleration = acceleration;
  }

  /**
   * Sets the color to the specified value.
   *
   * @param color a Color
   */
  public void setColor(Color color) {
    this.color = color;
  }
}
