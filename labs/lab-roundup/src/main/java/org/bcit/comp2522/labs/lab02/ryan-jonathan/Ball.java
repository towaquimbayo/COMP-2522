package org.bcit.comp2522.labs.lab02.ryan_src.main.java.org.comp2522;

import java.util.Objects;

import static processing.core.PApplet.atan2;
import static processing.core.PApplet.cos;
import static processing.core.PApplet.sin;
import static processing.core.PApplet.sqrt;

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
   * Getter method for X velocity.
   *
   * @return this.vx
   */
  public float getVx() {
    return this.vx;
  }

  /**
   * Setter method for X velocity.
   *
   * @set this.vx
   */
  public void setVx(float newVx) {
    this.vx = newVx;
  }

  /**
   * Getter method for Y Velocity.
   *
   * @return this.vy
   */
  public float getVy() {
    return this.vy;
  }

  /**
   * Setter method for Y velocity.
   *
   * @set this.vy
   */
  public void setVy(float newVy) {
    this.vy = newVy;
  }

  /**
   * Returns and prints a String containing information about the ball object.
   *
   * @param ball object given by user
   * @return A string with the diameter, xvelocity, yvelocity,
   * x and y coordinates of the ball object.
   */
  public String toString(Ball ball) {
    float diam = ball.getDiameter();
    float xvel = ball.getVx();
    float yvel = ball.getVy();
    float x = ball.getX();
    float y = ball.getY();
    String ballString = "Diameter: " + diam + "\n x velocity: " + xvel +
        "\n y velocity " + yvel + "\n x position: " + x + "\n y position: " + y;
    System.out.println(ballString);
    return ballString;
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
        others[i].setVx(others[i].getVx() + ax);
        others[i].setVy(others[i].getVy() + ay);
      }
    }
  }

  /**
   * Calculate new positions for this ball.
   */

  /**
   * our equals method will return true if the
   * diameters are equal.
   * @return boolean
   */
  public boolean equals(Ball ball1, Ball ball2) {
    boolean equivalent = false;
    if (ball1.getDiameter() == ball2.getDiameter()){
      equivalent = true;
    }
    return equivalent;
  }



  @Override
  public int hashCode() {
    return Objects.hash(diameter);
  }

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

}
