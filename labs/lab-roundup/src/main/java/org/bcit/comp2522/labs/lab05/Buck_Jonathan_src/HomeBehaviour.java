package org.bcit.comp2522.labs.lab05;

import java.util.ArrayList;
import processing.core.PVector;

/**
 * The HomeBehaviour class manages the home behaviours of each boid.
 *
 * @author Buck Sin, Jonathan Liu (based on Daniel Shiffman's code)
 * @version 1.0
 */
public class HomeBehaviour extends AbstractBehaviour {

  private Boid boid;

  public HomeBehaviour(Boid boid) {
    this.boid = boid;
  }

  @Override
  public void recalculate(ArrayList<IMoveable> moveables) {

    PVector seek = seek(boid.getHomePosition());   // Seek
    PVector ali = align(moveables);      // Alignment
    PVector coh = cohesion(moveables);   // Cohesion
    // Arbitrarily weight these forces
    seek.mult(1.5f);
    ali.mult(1.0f);
    coh.mult(1.0f);
    // Add the force vectors to acceleration
    applyForce(seek);
    applyForce(ali);
    applyForce(coh);
  }

  @Override
  public void move() {
    // Update velocity
    boid.getVelocity().add(boid.getAcceleration());
    // Limit speed
    boid.getVelocity().limit(boid.getMaxspeed());
    boid.getPosition().add(boid.getVelocity());
    // Reset acceleration to 0 each cycle
    boid.getAcceleration().mult(0);
  }

  /**
   * Finds the steer PVector from the position to the target.

   * @param target the target location
   * @return a PVector for the steer value.
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

  public void applyForce(PVector force) {
    // We could add mass here if we want A = F / M
    boid.setAcceleration(boid.getAcceleration().add(force));
  }
}
