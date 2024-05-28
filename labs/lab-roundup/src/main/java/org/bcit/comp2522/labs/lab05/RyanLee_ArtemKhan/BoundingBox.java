package org.bcit.comp2522.labs.lab05.RyanLee_ArtemKhan;

import processing.core.PVector;

/**
 * Defines the box the boid's home.
 *
 * @author Artem Khan
 * @author Ryan Lee
 * @Date: October 2022
 * @version 1.0
 */
public class BoundingBox {
  private final PVector topRight;
  private final PVector topLeft;
  private final PVector bottomRight;
  private final PVector bottomLeft;
  private final float top;
  private final float right;
  private final float bottom;
  private final float left;

  /**
   * BoundingBox constructor.
   *
   * @param pos centre point position
   * @param radius radius of object
   */
  public BoundingBox(PVector pos, float radius) {
    this.top = pos.y - radius;
    this.bottom = pos.y + radius;
    this.right = pos.x + radius;
    this.left = pos.x - radius;

    this.topRight = new PVector(pos.x + radius, pos.y + radius);
    this.topLeft = new PVector(pos.x - radius, pos.y + radius);
    this.bottomRight = new PVector(pos.x + radius, pos.y - radius);
    this.bottomLeft = new PVector(pos.x - radius, pos.y - radius);
  }

  /**
   * Gets the top boundary of the bounding box.
   *
   * @return top
   */
  public float getTop() {
    return this.top;
  }

  /**
   * Gets the bottom boundary of the bounding box.
   *
   * @return bottom
   */
  public float getBottom() {
    return this.bottom;
  }

  /**
   * Gets the left boundary of the bounding box.
   *
   * @return left
   */
  public float getLeft() {
    return this.left;
  }

  /**
   * Gets the right boundary of the bounding box.
   *
   * @return right
   */
  public float getRight() {
    return right;
  }
}
