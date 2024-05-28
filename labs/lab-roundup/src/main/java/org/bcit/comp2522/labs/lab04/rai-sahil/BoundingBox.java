package org.bcit.comp2522.labs.lab04;

import processing.core.PVector;

import java.awt.*;

/**
 * Defines the box for collision for any screen item.
 *
 * @author paul_bucci
 * @version 1.0
 */
public class BoundingBox implements IDrawable {
  private final PVector topRight;
  private final PVector topLeft;
  private final PVector bottomRight;
  private final PVector bottomLeft;
  private final float top;
  private final float right;
  private final float bottom;
  private final float left;
  private final Color color;

  /**
   * BoundingBox constructor.
   *
   * @param pos centre point position
   * @param width width of object
   * @param height height of object
   */
  public BoundingBox(PVector pos, float width, float height) {
    this.top = pos.y - (height / 2f);
    this.bottom = pos.y + (height / 2f);
    this.right = pos.x + (width / 2f);
    this.left = pos.x - (width / 2f);

    this.topRight = new PVector(pos.x + width / 2f, pos.y + height / 2f);
    this.topLeft = new PVector(pos.x - width / 2f, pos.y + height / 2f);
    this.bottomRight = new PVector(pos.x + width / 2f, pos.y - height / 2f);
    this.bottomLeft = new PVector(pos.x - width / 2f, pos.y - height / 2f);

    this.color = new Color(255, 0, 0);
  }

  /**
   * If needed, the bounding box can be drawn.
   *
   * @param window current scene window
   */
  @Override
  public void draw(Window window) {
    window.stroke(color.getRed(), color.getGreen(), color.getBlue());
    window.line(this.topLeft.x, this.topLeft.y, this.topRight.x, this.topRight.y);
    window.line(this.topRight.x, this.topRight.y, this.bottomRight.x, this.bottomRight.y);
    window.line(this.bottomRight.x, this.bottomRight.y, this.bottomRight.x, this.bottomRight.y);
    window.line(this.topLeft.x, this.topLeft.y, this.bottomLeft.x, this.bottomLeft.y);
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

  /**
   * Processing coordinates are "top-left", i.e., Y increases in the
   * down direction. If the BoundingBox of the other character is
   * outside of the current BoundingBox, then a collision is not
   * happening. We return the negation of that for collides.
   *
   * @param b the other bounding box
   * @return true if other bounding box is outside this one
   */
  public boolean collides(BoundingBox b) {
    boolean collided = !(
        this.getBottom() < b.getTop()
        || this.getTop() > b.getBottom()
        || this.getRight() < b.getLeft()
        || this.getLeft() > b.getRight()
    );
    return collided;
  }

}
