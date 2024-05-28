package org.bcit.comp2522.labs.lab02.navdeep_bryant;

import static processing.core.PApplet.*;

/**
 * Class for a Ball to be displayed onscreen.
 */
public class Ball {
  private float xpos;
  private float ypos;
  private final float diameter;
  private float vx = 0;
  private float vy = 0;
  private final int id;
  private Ball[] others;
  private BouncyBubbles scene;

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
   * Setter method for X position.
   *
   * @param x variable used to set x position.
   */
  public void setX(float x) {
    float setterX = x;

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
   * Setter method for y position.
   *
   * @param y variable used to set y position.
   */
  public void setY(float y) {
    float setterY = y;
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

  /** Comparing two values against new objects and returns true if the strings are equal, and false if not.
   *
   * @param b is an instance of the ball object
   * @return true if xpos, ypos, and diameter are the same. False otherwise.
   */
  public boolean equals(Ball b) {
    boolean sameX = this.xpos == b.xpos;
    boolean sameY = this.ypos == b.ypos;
    boolean sameDiameter = this.diameter == b.diameter;
    if (sameX && sameY && sameDiameter) {
      return true;
    }
    return false;
  }

  /**
   * A value that represents the whole object.
   *
   * @return hsh as an int value.
   */
  public int hashCode() {
    int hsh = (int) (this.xpos + this.ypos + this.diameter);
    return hsh;
  }

  /** Returns details of x, y and diameter of the circle.
   *
   * @return formatted string displaying details about the falling balls.
   */
  public String toString() {
    String s = "Circle XPos: " + getX() + ", YPos: " + getY() + ", diameter " + getDiameter();
    return s;
  }
}

