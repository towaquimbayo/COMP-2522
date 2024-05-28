package org.bcit.comp2522.labs.lab05.Lester_Betty_src;

import processing.core.PVector;

import java.awt.*;
import java.util.ArrayList;

/**
 * The Boid class manages the properties of the Boids.
 *
 * @author paul_bucci (based on Daniel Shiffman's code)
 * @author Betty Nguyen, Lester Shun
 * @version 1.0
 * @version October 9, 2022
 */
public class Boid extends AbstractObserver implements Imoveable {
  private PVector position;
  private PVector velocity;
  private PVector acceleration;
  private Color color;
  private float radius;
  private float maxforce;    // Maximum steering force
  private float maxspeed;    // Maximum speed
  private AbstractBehaviour behaviour;
  private final Home home;

  /**
   * Constructor for a Boid.
   *
   * @param x should be within window size limits
   * @param y should be within window size limits
   * @param color RGB colour
   */
  public Boid(float x, float y, Color color, Home home) {
    this.acceleration = new PVector(0, 0);
    float angle = (float) (Math.random() * Math.PI * 2);
    this.velocity = new PVector((float) Math.cos(angle), (float) Math.sin(angle));
    this.position = new PVector(x, y);
    this.color = color;
    this.radius = 2.0f;
    this.maxspeed = 2;
    this.maxforce = 0.03f;
    this.home = home;
    this.behaviour = new FlockingBehaviour(this);
  }

  /**
   * Updates the behaviour of the boid. If the current behaviour is home behaviour, the
   * behaviour will change to flocking behaviour. If the current behaviour is flocking
   * behaviour, behaviour will change to home behaviour.
   *
   * @param behaviour the behaviour of the boid as an AbstractBehaviour
   */
  @Override
  public void update(AbstractBehaviour behaviour) {
    // TODO: update this Boid's behaviour
    if (behaviour instanceof HomeBehaviour) {
      this.behaviour = new FlockingBehaviour(this);
    } else if (behaviour instanceof FlockingBehaviour) {
      this.behaviour = new HomeBehaviour(this, this.home);
    }
  }

  /**
   * Update's the Boid's position and velocity based on behaviour.
   *
   * @param window the Window which contains IMoveable object
   * @param moveables All IMoveables in window
   */
  public void run(Window window, ArrayList<Imoveable> moveables) {
    behaviour.recalculate(moveables);
    behaviour.move();
  }

  // Getters and Setters

  /**
   * Gets the current behaviour of the boid.
   *
   * @return Current behaviour of the boid as Behaviour
   */
  public AbstractBehaviour getBehaviour() {
    return behaviour;
  }

  /**
   * Gets the radius of the boid.
   *
   * @return Radius of the boid as Float
   */
  public float getRadius() {
    return radius;
  }

  /**
   * Gets the max force of the boid.
   *
   * @return Max force of the boid as Float
   */
  public float getMaxforce() {
    return maxforce;
  }

  /**
   * Gets the max speed of the boid.
   *
   * @return Max speed of the boid as Float
   */
  public float getMaxspeed() {
    return maxspeed;
  }

  /**
   * Gets the current position of the boid.
   *
   * @return Position of the boid as PVector
   */
  public PVector getPosition() {
    return position;
  }

  /**
   * Gets the current velocity of the boid.
   *
   * @return Velocity of the boid as PVector
   */
  public PVector getVelocity() {
    return velocity;
  }

  /**
   * Gets the current acceleration of the boid.
   *
   * @return Acceleration of the boid as PVector
   */
  public PVector getAcceleration() {
    return acceleration;
  }

  /**
   * Gets the current color of the boid.
   *
   * @return Color of the boid as Color
   */
  @Override
  public Color getColor() {
    return color;
  }

  /**
   * Sets the behaviour of the boid to input behaviour.
   *
   * @param behaviour New behaviour as an AbstractBehaviour
   */
  public void setBehaviour(AbstractBehaviour behaviour) {
    this.behaviour = behaviour;
  }

  /**
   * Sets the radius of the Boid.
   *
   * @param radius New radius as a Float
   */
  public void setRadius(float radius) {
    this.radius = radius;
  }

  /**
   * Sets the max speed of the boid.
   *
   * @param maxspeed New max speed as Float
   */
  public void setMaxspeed(float maxspeed) {
    this.maxspeed = maxspeed;
  }

  /**
   * Sets the max force of the boid.
   *
   * @param maxforce New max force as Float
   */
  public void setMaxforce(float maxforce) {
    this.maxforce = maxforce;
  }

  /**
   * Sets the position of the boid.
   *
   * @param position New position as PVector
   */
  public void setPosition(PVector position) {
    this.position = position;
  }

  /**
   * Sets the velocity of the boid.
   *
   * @param velocity New velocity as PVector
   */
  public void setVelocity(PVector velocity) {
    this.velocity = velocity;
  }

  /**
   * Sets the acceleration of the boid.
   *
   * @param acceleration New acceleration as PVector
   */
  public void setAcceleration(PVector acceleration) {
    this.acceleration = acceleration;
  }

  /**
   * Sets the color of the boid.
   *
   * @param newColour New color as Color
   */
  public void setColour(Color newColour) {
    this.color = newColour;
  }
}
