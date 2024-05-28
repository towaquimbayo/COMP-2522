package org.bcit.comp2522.labs.lab02.Towa_Alex;

/**
 * Collidable abstract class that
 * will be inherited by Ball and Mouse.
 *
 * @author towaquimbayo, alexgibbison
 * @version 1.0
 */
public abstract class Collidable {
  protected float xpos;
  protected float ypos;
  protected final int id;
  protected final float diameter;
  protected float vx = 0;
  protected float vy = 0;
  protected Collidable[] others;
  protected BouncyBubbles scene;

  /**
   * Collidable Constructor.
   *
   * @param xin for x position
   * @param yin for y position
   * @param diam for diameter
   * @param idIn for id
   * @param oin for array of Collidable objects
   * @param scene for BouncyBubbles scene
   */
  public Collidable(float xin, float yin, float diam,
                     int idIn, final Collidable[] oin, final BouncyBubbles scene) {
    this.xpos = xin;
    this.ypos = yin;
    this.diameter = diam;
    this.id = idIn;
    this.others = oin;
    this.scene = scene;
  }

  /**
   * Getter method for X position.
   *
   * @return this.xPos
   */
  public final float getX() {
    return this.xpos;
  }

  /**
   * Setter method for X position.
   *
   * @param x for x position
   */
  public final void setX(float x) {
    this.xpos = x;
  }

  /**
   * Getter method for Y Position.
   *
   * @return this.yPos
   */
  public final float getY() {
    return this.ypos;
  }

  /**
   * Setter method for Y position.
   *
   * @param y for y position
   */
  public final void setY(float y) {
    this.ypos = y;
  }

  /**
   * Getter method for Diameter.
   *
   * @return this.diameter
   */
  public final float getDiameter() {
    return this.diameter;
  }

  /**
   * Getters for vx position.
   *
   * @return this.vx for velocity x
   */
  public final float getVx() {
    return this.vx;
  }

  /**
   * Getters for vy position.
   *
   * @return this.vy for velocity y
   */
  public final float getVy() {
    return this.vy;
  }

  /**
   * Setters for vx position.
   *
   * @param a for velocity x
   */
  public final void setVx(float a) {
    this.vx = a;
  }

  /**
   * Setters for vy position.
   *
   * @param a for velocity y
   */
  public final void setVy(float a) {
    this.vy = a;
  }

  /**
   * Move Abstract Method.
   */
  public abstract void move();

  /**
   * Calculates collisions between all balls.
   */
  public abstract void collide();
}
