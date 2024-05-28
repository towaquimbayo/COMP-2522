package ca.bcit.comp2522.labs.lab04;

import java.awt.Color;
import processing.core.PVector;

/**
 * Defines the box for collision for any screen item.
 *
 * @author Towa Quimbayo
 * @version 1.0, September 29, 2022
 */
public class BoundingBox implements IDrawable {
  // Top right PVector value
  private final PVector topRight;
  // Top left PVector value
  private final PVector topLeft;
  // Bottom right PVector value
  private final PVector bottomRight;
  // Bottom left PVector value
  private final PVector bottomLeft;
  // Top value
  private final float top;
  // Right value
  private final float right;
  // Bottom value
  private final float bottom;
  // Left value
  private final float left;
  // Color object value
  private final Color color;
  // Two Floating Value
  private static final float TWO = 2;
  // Red Color HEX value
  private static final int TWO_FIFTY_FIVE = 255;

  /**
   * BoundingBox constructor.
   *
   * @param pos centre point position
   * @param width width of object
   * @param height height of object
   */
  public BoundingBox(PVector pos, float width, float height) {
    this.top = pos.y - (height / TWO);
    this.bottom = pos.y + (height / TWO);
    this.right = pos.x + (width / TWO);
    this.left = pos.x - (width / TWO);

    this.topRight = new PVector(pos.x + width / TWO, pos.y + height / TWO);
    this.topLeft = new PVector(pos.x - width / TWO, pos.y + height / TWO);
    this.bottomRight = new PVector(pos.x + width / TWO, pos.y - height / TWO);
    this.bottomLeft = new PVector(pos.x - width / TWO, pos.y - height / TWO);

    this.color = new Color(TWO_FIFTY_FIVE, 0, 0);
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
   * outside the current BoundingBox, then a collision is not
   * happening. We return the negation of that for collides.
   *
   * @param b the other bounding box
   * @return true if other bounding box is outside this one
   */
  public boolean collides(BoundingBox b) {
    return !(this.getBottom() < b.getTop()
            || this.getTop() > b.getBottom()
            || this.getRight() < b.getLeft()
            || this.getLeft() > b.getRight());
  }
}
