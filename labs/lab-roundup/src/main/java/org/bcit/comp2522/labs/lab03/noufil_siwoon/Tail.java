package org.bcit.comp2522.labs.lab03.noufil_siwoon;

import processing.core.PVector;

import java.awt.*;

/**
 * Smaller circles on the outside of the PlayerCharacter. Points in the
 * opposite direction that the PlayerCharacter is going in.
 *
 * @author paul_bucci & Siwoon Lim & Noufil Saqib
 * @version 1.0
 */
public class Tail implements IDrawable {
  private Tail tail;
  private final float width;
  private final float height;
  private final PVector position;
  private final Color color;
  private static final float SCALE_FACTOR = 0.9f;
  private static final int COLOR_HEX = 100;

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
    final int count;
    final float scaleFactor = SCALE_FACTOR;

    this.width = widthIn * scaleFactor;
    this.height = heightIn * scaleFactor;
    this.position = positionIn.add(direction);
    count = countIn - 1;
    this.color = new Color(COLOR_HEX, 0, 0);
    if (count > 0) {
      this.tail = new Tail(
        this.position.copy(),
        direction.copy(),
        this.width,
        this.height,
        count
      );
    }


  }

  /**
   * Draws the tail for PlayerCharacter.
   *
   * @param window for Window
   */
  @Override
  public void draw(Window window) {
    if (this.tail != null) {
      this.tail.draw(window);
    }
    window.fill(color.getRed(), color.getGreen(), color.getBlue());
    window.ellipse(this.position.x, this.position.y, this.width, this.height);
  }
}
