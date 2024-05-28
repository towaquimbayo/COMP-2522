package org.comp2522;

/**
 * Abstract class Collidable as an abstract superclass
 * for mouse and ball.
 */
public abstract class Collidable {
  private float xpos;
  private float ypos;
  private final float diameter;
  private float vx = 0;
  private float vy = 0;
  private final int id;
  private final Ball[] others;
  private final BouncyBubbles scene;

  Collidable(float xin, float yin, float din, int idin, Ball[] oin, BouncyBubbles scene) {
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
   * Setter method for X position.
   *
   * @param x x position
   */
  public void setX(float x) {
    xpos = x;
  }

  /**
   * Setter method for Y position.
   *
   * @param y y position
   */
  public void setY(float y) {
    ypos = y;
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
   * Getter method for vx.
   *
   * @return this.vx
   */
  public float getVx() {
    return this.vx;
  }

  /**
   * Setter method for vx.
   *
   * @param newVx new vx value
   */
  public void setVx(float newVx) {
    this.vx = newVx;
  }

  /**
   * Getter method for vy.
   *
   * @return this.vy
   */
  public float getVy() {
    return this.vy;
  }

  /**
   * Setter method for vy.
   *
   * @param newVy new y position
   */
  public void setVy(float newVy) {
    this.vy = newVy;
  }

  /**
   * Getter method for ID.
   *
   * @return this.id
   */
  public int getId() {
    return this.id;
  }

  /**
   * Getter method for array others.
   *
   * @return this.others
   */
  public Ball[] getOthers() {
    return this.others;
  }

  /**
   * Getter method for scene.
   *
   * @return this.scene
   */
  public BouncyBubbles getScene() {
    return this.scene;
  }

  /**
   * Calculates collisions between all balls.
   */
  abstract void collide();

  /**
   * Calculate new positions for this ball.
   */
  abstract void move();

  /**
   * A method that check for equals between two ball objects.
   *
   * @param obj Ball Object
   * @return true if equal, false otherwise.
   */
  public boolean equals(Ball obj) {
    boolean sameClass = this.getClass().getSimpleName() == obj.getClass().getSimpleName();
    boolean sameDiameter = this.getDiameter() == obj.getDiameter();
    boolean sameXpos = this.getX() == obj.getX();
    boolean sameYpos = this.getY() == obj.getY();
    if (sameClass && sameDiameter && sameXpos && sameYpos) {
      return true;
    }
    return false;
  }

  /**
   * Provide hashcode.
   *
   * @return int value
   */
  public int hashCode() {
    int hash = (int) (this.getDiameter() + this.getX() + this.getY());
    return hash;
  }

  /**
   * Describe a ball characteristics in strings.
   *
   * @return strings of ball characteristics
   */
  public String toString() {
    String a = String.format("Ball ID: " + this.id
        + ", X:%f; Y:%f, D:%f", this.xpos, this.ypos, this.diameter);
    return a;
  }
}





