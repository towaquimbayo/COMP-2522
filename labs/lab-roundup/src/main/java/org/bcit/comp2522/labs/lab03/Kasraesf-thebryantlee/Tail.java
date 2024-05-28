package org.bcit.com2522.labs.lab03;

import java.awt.Color;
import processing.core.PVector;

/**
 * Smaller circles on the outside of the PlayerCharacter. Points in the
 * opposite direction that the PlayerCharacter is going in.
 *
 * @author paul_bucci
 * @version 1.0
 */
public class Tail implements IDrawable {
  private Tail tail;
  private final float width;
  private final float height;
  private final PVector position;
  private final int count;
  private final Color color;
  private final float scaleFactor = 0.9f;

  /**
   * Constructor for Tail.
   *
   * @param positionIn parent's position
   * @param direction parent's direction (first will be -1*parent's direction)
   * @param widthIn parent's width
   * @param heightIn parent's height
   * @param countIn parent's count
   */
  public Tail(PVector positionIn, PVector direction, float widthIn, float heightIn, int countIn) {
    this.width = widthIn * scaleFactor;
    this.height = heightIn * scaleFactor;
    this.position = positionIn.add(direction);
    this.count = countIn - 1;
    this.color = new Color(100, 0, 0);
    if (this.count > 0) {
      this.tail = new Tail(
        this.position.copy(),
        direction.copy(),
        this.width,
        this.height,
        this.count
      );
    }


  }

  @Override
  public void draw(Window window) {
    if (this.tail != null) {
      this.tail.draw(window);
    }
    window.fill(color.getRed(), color.getGreen(), color.getBlue());
    window.ellipse(this.position.x, this.position.y, this.width, this.height);
  }
}
