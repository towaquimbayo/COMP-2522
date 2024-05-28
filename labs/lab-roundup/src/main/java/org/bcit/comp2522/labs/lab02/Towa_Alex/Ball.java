package org.bcit.comp2522.labs.lab02.Towa_Alex;

import static processing.core.PApplet.*;

/**
 * Class for a Ball to be displayed onscreen.
 * Extend from Collidable class to inherit its values.
 * Each ball object will be within a Collidable object.
 *
 * @author towaquimbayo, alexgibbison
 * @version 1.0
 */
public class Ball extends Collidable {

  /**
   * Used to represent the number two.
   */
  public static final int TWO = 2;

  /**
   * Used to represent the number one.
   */
  public static final int ONE = 1;

  /**
   * Used to calculate and differentiate ball objects
   * in hashcode.
   */
  public static final int ONEHUNDRED = 100;

  /**
   * Calls the constructor
   * of the Collidable object and passes the values.
   *
   * @param xin for x position
   * @param yin for y position
   * @param diam for diameter
   * @param idIn for id
   * @param oin for array of Collidable Objects
   * @param scene for BouncyBubbles scene
   */
  Ball(float xin, float yin, float diam, int idIn, final Collidable[] oin, final BouncyBubbles scene) {
    super(xin, yin, diam, idIn, oin, scene);
  }

  /**
   * Calculate collisions between all balls.
   * If statement to check if the object is not an instance of the Mouse,
   * and if it is true then only bounce the ball and not the mouse object.
   */
  public final void collide() {
    for (int i = id + ONE; i < others.length; i++) {
      float dx = others[i].xpos - xpos;
      float dy = others[i].ypos - ypos;
      float distance = sqrt(dx * dx + dy * dy);
      float minDist = others[i].diameter / TWO + diameter / TWO;
      if (distance < minDist) {
        float angle = atan2(dy, dx);
        float targetX = xpos + cos(angle) * minDist;
        float targetY = ypos + sin(angle) * minDist;
        float ax = (targetX - others[i].xpos) * scene.spring;
        float ay = (targetY - others[i].ypos) * scene.spring;
        vx -= ax;
        vy -= ay;
        if (!(others[i] instanceof Mouse)) {
          others[i].setVx(others[i].vx + ax);
          others[i].setVy(others[i].vy + ay);
        }
      }
    }
  }

  /**
   * Checks the balls' diameters for equality.
   *
   * @param a for Ball object
   * @return true if both Ball objects are equal in diameter
   */
  public final boolean equals(final Ball a) {
    return (a.diameter == this.diameter);
  }

  /**
   * Sets the HashCode for each ball.
   *
   * @return the diameter value
   */
  public final int hashCode() {
    return (int) (this.diameter * ONEHUNDRED);
  }

  /**
   * Prints the ball id to the console.
   *
   * @return this.id of the ball
   */
  public final String toString() {
    return String.format("Ball: %d;", this.id);
  }

  /**
   * Calculate new positions for this ball.
   */
  public final void move() {
    vy += scene.gravity;
    xpos += vx;
    ypos += vy;
    if (xpos + diameter / TWO > scene.width) {
      xpos = scene.width - diameter / TWO;
      vx *= scene.friction;
    } else if (xpos - diameter / TWO < 0) {
      xpos = diameter / TWO;
      vx *= scene.friction;
    }
    if (ypos + diameter / TWO > scene.height) {
      ypos = scene.height - diameter / TWO;
      vy *= scene.friction;
    } else if (ypos - diameter / TWO < 0) {
      ypos = diameter / TWO;
      vy *= scene.friction;
    }
  }
}
