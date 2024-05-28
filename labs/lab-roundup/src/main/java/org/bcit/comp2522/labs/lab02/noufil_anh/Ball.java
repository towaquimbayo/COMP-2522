package org.bcit.comp2522.labs.lab02.noufil_anh;

import static processing.core.PApplet.atan2;
import static processing.core.PApplet.cos;
import static processing.core.PApplet.sin;
import static processing.core.PApplet.sqrt;

/**
 * Class for a Ball to be displayed onscreen.
 */
public class Ball {
//  public class Ball extends Collidable {
  protected float xpos;
  protected float ypos;
  protected final float diameter;
  private float vx = 0;
  private float vy = 0;
  protected final int id;
  protected Ball[] others;
  protected BouncyBubbles scene;

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
   * Getter method for X Velocity.
   *
   * @return this.vx
   */
  public float getVx() {
    return this.vx;
  }

  /**
   * Setter method for X Velocity.
   */
  public void setVx(float vx) {
    this.vx = vx;
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
   * Setter method for X Velocity.
   */
  public void setVy(float vy) {
    this.vy = vy;
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

  public boolean equals(Ball another) {
    if(this.id == another.id) {
      return true;
    }
    return false;
  }

  public int hashCode() {
    int result = id;
    return result;
  }

  public String toString() {
    String result = String.format("Ball " + id + " with diameter " + diameter);
    return result;
  }
}
