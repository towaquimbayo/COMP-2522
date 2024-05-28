package org.bcit.comp2522.labs.lab05.noufil_willy;

import processing.core.PVector;

import java.util.ArrayList;

/**
 * HomeBehavior will make Boids point towards their home.
 *
 * @author Willy Liao, Noufil Saqib
 * @version 1.0
 */
public class HomeBehaviour extends AbstractBehaviour {
  Boid boid;
  Home home;

  /**
   * Constructor for HomeBehaviour.
   *
   * @param boid a Boid
   * @param home a Home
   */
  public HomeBehaviour(Boid boid, Home home) {
    this.boid = boid;
    this.home = home;
  }

  /**
   * We accumulate a new acceleration each time based on three rules:
   * Separation, alignment, cohesion, and home-force towards home.
   *
   * @param moveables usually Boids
   */
  @Override
  public void recalculate(ArrayList<IMoveable> moveables) {
    PVector sep = separate(moveables);   // Separation
    PVector ali = align(moveables);      // Alignment
    PVector coh = cohesion(moveables);   // Cohesion

    // Arbitrarily weight these forces
    sep.mult(1.5f);
    ali.mult(1.0f);
    coh.mult(1.0f);

    // Add the force vector towards home
    PVector homeSeek = seek(home.getPosition());
    homeSeek.mult(1.75f);

    // Add the force vectors to acceleration
    applyForce(sep);
    applyForce(ali);
    applyForce(coh);
    applyForce(homeSeek);
  }

  /**
   * Update Boid's position.
   */
  @Override
  public void move() {
    // Update velocity
    boid.getVelocity().add(boid.getAcceleration());
    // Limit speed
    boid.getVelocity().limit(boid.getMaxspeed());
    boid.getPosition().add(boid.getVelocity());
    // Reset accelertion to 0 each cycle
    boid.getAcceleration().mult(0);
  }

  /**
   * Separation: checks for nearby boids and steers away.
   *
   * @param moveables usually Boids
   * @return PVector for steering direction
   */
  public PVector separate(ArrayList<IMoveable> moveables) {
    float desiredseparation = 25.0f;
    PVector steer = new PVector(0, 0, 0);
    int count = 0;
    // For every boid in the system, check if it's too close
    for (IMoveable other : moveables) {
      float d = PVector.dist(boid.getPosition(), other.getPosition());
      // If the distance is greater than 0 and less than an arbitrary
      // amount (0 when you are yourself)
      if ((d > 0) && (d < desiredseparation)) {
        // Calculate vector pointing away from neighbor
        PVector diff = PVector.sub(boid.getPosition(), other.getPosition());
        diff.normalize();
        diff.div(d);        // Weight by distance
        steer.add(diff);
        count++;            // Keep track of how many
      }
    }
    // Average -- divide by how many
    if (count > 0) {
      steer.div((float) count);
    }

    // As long as the vector is greater than 0
    if (steer.mag() > 0) {
      // First two lines of code below could be condensed with new PVector setMag() method
      // Not using this method until Processing.js catches up
      // steer.setMag(maxspeed);

      // Implement Reynolds: Steering = Desired - Velocity
      steer.normalize();
      steer.mult(boid.getMaxspeed());
      steer.sub(boid.getVelocity());
      steer.limit(boid.getMaxforce());
    }
    return steer;
  }

  /**
   * Alignment: For every nearby boid in the system,
   * calculate the average velocity.
   *
   * @param moveables usually Boids
   * @return PVector updated steering vector
   */
  public PVector align(ArrayList<IMoveable> moveables) {
    float neighbordist = 50;
    PVector sum = new PVector(0, 0);
    int count = 0;
    for (IMoveable other : moveables) {
      float d = PVector.dist(boid.getPosition(), other.getPosition());
      if ((d > 0) && (d < neighbordist)) {
        sum.add(other.getVelocity());
        count++;
      }
    }
    if (count > 0) {
      sum.div((float) count);
      // First two lines of code below could be condensed with new PVector setMag() method
      // Not using this method until Processing.js catches up
      // sum.setMag(maxspeed);

      // Implement Reynolds: Steering = Desired - Velocity
      sum.normalize();
      sum.mult(boid.getMaxspeed());
      PVector steer = PVector.sub(sum, boid.getVelocity());
      steer.limit(boid.getMaxforce());
      return steer;
    } else {
      return new PVector(0, 0);
    }
  }

  /**
   * Cohesion
   * For the average position (i.e. center) of all nearby boids,
   * calculate steering vector towards that position
   *
   * @param moveables usually Boids
   * @return PVector updated steering vector
   */
  public PVector cohesion(ArrayList<IMoveable> moveables) {
    float neighbordist = 50;
    // Start with empty vector to accumulate all positions
    PVector sum = new PVector(0, 0);
    int count = 0;
    for (IMoveable other : moveables) {
      float d = PVector.dist(boid.getPosition(), other.getPosition());
      if ((d > 0) && (d < neighbordist)) {
        sum.add(other.getPosition()); // Add position
        count++;
      }
    }
    if (count > 0) {
      sum.div(count);
      return seek(sum);  // Steer towards the position
    } else {
      return new PVector(0, 0);
    }
  }


  /**
   * Seek: A method that calculates and applies a
   * steering force towards a target.
   * STEER = DESIRED MINUS VELOCITY
   *
   * @param target the direction to steer towards
   * @return PVector updated steering vector
   */
  public PVector seek(PVector target) {
    // A vector pointing from the position to the target
    PVector desired = PVector.sub(target, boid.getPosition());
    // Scale to maximum speed
    desired.normalize();
    desired.mult(boid.getMaxspeed());

    // Above two lines of code below could be condensed with new PVector setMag() method
    // Not using this method until Processing.js catches up
    // desired.setMag(maxspeed);

    // Steering = Desired minus Velocity
    PVector steer = PVector.sub(desired, boid.getVelocity());
    steer.limit(boid.getMaxforce());  // Limit to maximum steering force
    return steer;
  }

  /**
   * Adds the specified force to a Boids acceleration.
   *
   * @param force a PVector
   */
  public void applyForce(PVector force) {
    // We could add mass here if we want A = F / M
    boid.setAcceleration(boid.getAcceleration().add(force));
  }
}
