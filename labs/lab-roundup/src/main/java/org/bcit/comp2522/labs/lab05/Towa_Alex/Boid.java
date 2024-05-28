package org.bcit.comp2522.labs.lab05.Towa_Alex;

import processing.core.PVector;

import java.awt.*;
import java.util.ArrayList;

/**
 * The Boid class manages the properties of the Boids.
 *
 * @author Towa Quimbayo, Alex Gibbison
 * @version 1.0
 */
public class Boid extends AbstractObserver implements IMoveable {
  /** PVector type for position. */
  protected PVector position;
  /** PVector type for velocity. */
  protected PVector velocity;
  /** PVector type for acceleration. */
  private PVector acceleration;
  /** Color instance variables for color. */
  private Color color;
  /** Float instance variables for Boid radius. */
  private float radius;
  /** Float instance variables for maximum steering force. */
  private float maxForce;
  /** Float instance variables for maximum speed. */
  private float maxSpeed;
  /** AbstractBehavior type for behavior of Boid. */
  protected AbstractBehaviour behaviour;
  /** Flock type for this flock. */
  private Flock flock;
  /** Constant for integer value of 2. */
  public static final int TWO = 2;
  /** Constant for radius value of 2. */
  public static final float RADIUS_VAL = 2f;
  /** Constant for maximum force value of 0.03. */
  public static final float MAX_FORCE_VAL = 0.03f;

  /**
   * Constructor for a Boid.
   *
   * @param x should be within window size limits
   * @param y should be within window size limits
   * @param color RGB colour
   * @param f for Flock
   */
  public Boid(float x, float y, Color color, Flock f) {
    this.acceleration = new PVector(0, 0);
    float angle = (float) (Math.random() * Math.PI * TWO);
    this.velocity = new PVector((float) Math.cos(angle), (float) Math.sin(angle));
    this.position = new PVector(x, y);
    this.color = color;
    this.radius = RADIUS_VAL;
    this.maxSpeed = TWO;
    this.maxForce = MAX_FORCE_VAL;
    this.flock = f;
    this.behaviour = new HomeBehaviour(this);
  }

  /**
   * Update method used for notifying observer.
   * Check which behavior its currently set to
   * and assign a new behavior.
   *
   * @param msg for a String msg
   */
  @Override
  public void update(String msg) {
    if (this.behaviour instanceof FlockingBehaviour) {
      this.behaviour = new HomeBehaviour(this);
      System.out.println("Changed to Home Behavior: " + msg);
    } else {
      this.behaviour = new FlockingBehaviour(this);
      System.out.println("Changed to Flocking Behavior: " + msg);
    }
  }

  /**
   * Run method to run each boid on the window every frame.
   *
   * @param window for window
   * @param moveables for IMovable list of objects
   */
  public void run(Window window, ArrayList<IMoveable> moveables) {
    behaviour.recalculate(this.getFlock().boids);
    behaviour.move();

    // Call this method to check if this boid hit a home
    checkBoidTouchHome();
  }

  /**
   * Method to check if this boid is touching any home
   * other than its own flock, if it does then unregister from
   * its original flock and register to the touch flock.
   * Call this in run method to check every frame while its moving.
   */
  public void checkBoidTouchHome() {
    for (Home h : this.getFlock().homeList) {
      if (this.getFlock().home != h) {
        if (this.position.x >= (h.position.x - h.getRadius())
                && this.position.x <= (h.position.x + h.getRadius())
                && this.position.y >= (h.position.y - h.getRadius())
                && this.position.y <= (h.position.y + h.getRadius())) {
          Flock oldFlock = this.getFlock();
          Flock newFlock = this.getFlock().getFlockByHome(h);
          oldFlock.unregisterObserver(this);
          newFlock.registerObserver(this);
          this.setFlock(h);
          if (this.getBehaviour() instanceof HomeBehaviour) {
            this.behaviour = new HomeBehaviour(this);
          } else {
            this.behaviour = new FlockingBehaviour(this);
          }
          this.setColor(newFlock.home.getColor());
        }
      }
    }
  }

  /**
   * Get method for behavior.
   *
   * @return this.behavior
   */
  public AbstractBehaviour getBehaviour() {
    return this.behaviour;
  }

  /**
   * Get method for radius.
   *
   * @return this.radius
   */
  public float getRadius() {
    return this.radius;
  }

  /**
   * Get method for maxForce.
   *
   * @return this.maxForce
   */
  public float getMaxForce() {
    return this.maxForce;
  }

  /**
   * Get method for maxSpeed.
   *
   * @return this.maxSpeed
   */
  public float getMaxSpeed() {
    return this.maxSpeed;
  }

  /**
   * Get method for position.
   *
   * @return this.position
   */
  public PVector getPosition() {
    return this.position;
  }

  /**
   * Get method for velocity.
   *
   * @return this.velocity
   */
  public PVector getVelocity() {
    return this.velocity;
  }

  /**
   * Get method for acceleration.
   *
   * @return this.acceleration
   */
  public PVector getAcceleration() {
    return this.acceleration;
  }

  /**
   * Get method for color.
   *
   * @return this.color
   */
  @Override
  public Color getColor() {
    return this.color;
  }

  /**
   * Get method for flock.
   *
   * @return this.flock
   */
  public Flock getFlock() {
    return this.flock;
  }

  /**
   * Set method for flock.
   *
   * @param h for Home
   */
  public void setFlock(Home h) {
    for (Flock f : this.getFlock().otherFlock) {
      if (f != this.getFlock() && f.home == h) {
        this.flock = f;
      }
    }
  }

  /**
   * Set method for behaviour.
   *
   * @param behaviour for AbstractBehaviour
   */
  public void setBehaviour(AbstractBehaviour behaviour) {
    this.behaviour = behaviour;
  }

  /**
   * Set method for radius.
   *
   * @param radius for radius
   */
  public void setRadius(float radius) {
    this.radius = radius;
  }

  /**
   * Set method for maxSpeed.
   *
   * @param maxSpeed for maxSpeed
   */
  public void setMaxSpeed(float maxSpeed) {
    this.maxSpeed = maxSpeed;
  }

  /**
   * Set method for maxForce.
   *
   * @param maxForce for maxForce
   */
  public void setMaxForce(float maxForce) {
    this.maxForce = maxForce;
  }

  /**
   * Set method for position.
   *
   * @param position for position
   */
  public void setPosition(PVector position) {
    this.position = position;
  }

  /**
   * Set method for velocity.
   *
   * @param velocity for velocity
   */
  public void setVelocity(PVector velocity) {
    this.velocity = velocity;
  }

  /**
   * Set method for color.
   *
   * @param c for color
   */
  public void setColor(Color c) {
    this.color = c;
  }

  /**
   * Set method for acceleration.
   *
   * @param acceleration for acceleration
   */
  public void setAcceleration(PVector acceleration) {
    this.acceleration = acceleration;
  }

  /**
   * Equals method for boid.
   *
   * @param b for boid
   * @return true if position and acceleration are equal
   */
  public boolean equals(Boid b) {
    return (b.position == this.position && b.acceleration == this.acceleration);
  }

  /**
   * Hashcode method for boid.
   *
   * @return the product of position x,y and acceleration x,y values
   */
  public int hashCode() {
    return (int) (this.position.x * this.position.y * this.acceleration.x * this.acceleration.y);
  }

  /**
   * toString method to print this Boid.
   *
   * @return this boid's x position and y position
   */
  public String toString() {
    return String.format("X Position: " + (int) this.position.x
            + " Y Position: " + (int) this.position.y);
  }
}
