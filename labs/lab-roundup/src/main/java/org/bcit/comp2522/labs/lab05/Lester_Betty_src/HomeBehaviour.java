package org.bcit.comp2522.labs.lab05.Lester_Betty_src;

import processing.core.PVector;

import java.util.ArrayList;

/**
 * Defines how each Boid reacts to their Flock's Home when their behaviour
 * is set to HomeBehaviour. When Boids are taking on HomeBehaviour, they
 * will move towards the Home and gather around it, while keeping some
 * space apart.
 *
 * @author Betty Nguyen, Lester Shun
 * @version 1.0
 * @version October 9, 2022
 */
public class HomeBehaviour extends AbstractBehaviour {
  Home home;

  /**
   * Constructor for the home behaviour.
   *
   * @param boid the boid object
   * @param home the home object
   */
  public HomeBehaviour(Boid boid, Home home) {
    super(boid);
    this.home = home;
  }

  /**
   * We accumulate a new acceleration each time based on three rules:
   * Separation, alignment, and cohesion.
   *
   * @param moveables usually Boids
   */
  @Override
  public void recalculate(ArrayList<Imoveable> moveables) {
    PVector coh = cohesion();   // Cohesion
    // Arbitrarily weight these forces
    coh.mult(1.0f);
    // Add the force vectors to acceleration
    applyForce(coh);
  }

  /**
   * Cohesion
   * For the average position (i.e. center) of all nearby boids,
   * calculate steering vector towards that position
   *
   * @return PVector updated steering vector
   */
  public PVector cohesion() {
    return seek(home.getPosition());  // Steer towards the position
  }
}
