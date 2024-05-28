package org.bcit.comp2522.labs.lab02.Taylor_Nash;

import static processing.core.PApplet.atan2;
import static processing.core.PApplet.cos;
import static processing.core.PApplet.sin;
import static processing.core.PApplet.sqrt;

/**
 * Class for a Ball to be displayed onscreen.
 * One sentence high-level description:
 * Each ball has its own x position and y position and
 * if balls collide then make them move in certain ways.
 *
 * @author Nash and Taylor
 * @version 2022 September
 *
 */
public class Ball implements AbstractCollidable {
  private float xpos;
  private float ypos;
  private final float diameter;
  private float vx = 0;
  private float vy = 0;
  private final int id;
  private final Ball[] others;
  private final BouncyBubbles scene;

  Ball(float xin, float yin, float din, int idin, Ball[] oin, BouncyBubbles scene) {
    this.xpos = xin;
    this.ypos = yin;
    this.diameter = din;
    this.id = idin;
    this.others = oin;
    this.scene = scene;
  }

  /**
   * Getter method for X position.
   *
   * @return this.xPos
   */
  public float getX() {
    return this.xpos;
  }

  /**
   * Getter method for Y Position.
   *
   * @return this.yPos
   */
  public float getY() {
    return this.ypos;
  }

  /**
   * Getter method for diameter.
   *
   * @return this.diameter
   */
  public float getDiameter() {
    return this.diameter;
  }

  /**
   * Setter method for X Position.
   *
   * @param xpos X Position to be set
   */
  public void setX(float xpos) {
    this.xpos = xpos;
  }

  /**
   * Setter method for Y Position.
   *
   * @param ypos Y Position to be set
   */
  public void setY(float ypos) {
    this.ypos = ypos;
  }

  /**
   * Calculates collisions between all balls.
   */
  public void collide() {
    for (int i = id + 1; i < others.length; i++) {
      float dx = others[i].xpos - xpos;
      float dy = others[i].ypos - ypos;
      float distance = sqrt(dx * dx + dy * dy);
      float minDist = others[i].diameter / 2 + diameter / 2;
      if (distance < minDist) {
        float angle = atan2(dy, dx);
        float targetX = xpos + cos(angle) * minDist;
        float targetY = ypos + sin(angle) * minDist;
        float ax = (targetX - others[i].xpos) * scene.spring;
        float ay = (targetY - others[i].ypos) * scene.spring;
        vx -= ax;
        vy -= ay;
        others[i].setX(others[i].getX() + ax);
        others[i].setY(others[i].getY() + ay);
      }
    }
  }

  /**
   * Calculate new positions for this ball.
   */
  public void move() {
    vy += scene.gravity;
    xpos += vx;
    ypos += vy;
    if (xpos + diameter / 2 > scene.width) {
      xpos = scene.width - diameter / 2;
      vx *= scene.friction;
    } else if (xpos - diameter / 2 < 0) {
      xpos = diameter / 2;
      vx *= scene.friction;
    }
    if (ypos + diameter / 2 > scene.height) {
      ypos = scene.height - diameter / 2;
      vy *= scene.friction;
    } else if (ypos - diameter / 2 < 0) {
      ypos = diameter / 2;
      vy *= scene.friction;
    }
  }

  /**
   * Determine whether two balls are identical.
   *
   * @param b used for comparing other ball object.
   * @return true for two balls are identical, false the two balls are not identical
   */
  public boolean equals(Ball b) {
    boolean sameX = this.xpos == b.xpos;
    boolean sameY = this.ypos == b.ypos;
    boolean sameDiameter = this.diameter == b.diameter;
    return sameX && sameY && sameDiameter;
  }

  /**
   * Calculate hash.

   * @return hash that represents unique number of a ball.
   */
  public int hashCode() {
    return (int) (this.xpos + this.ypos + this.diameter);
  }

  /**
   * Print out String s containing a ball's x position, y position, and diameter.

   * @return s that describes a ball's x position, y position, and diameter.
   */
  public String toString() {
    return String.format("Ball X:%f; Y:%f, D:%f", this.xpos, this.ypos, this.diameter);
  }

}
