package ca.bcit.comp2522.labs.lab04;

import java.awt.Color;
import processing.core.PVector;

/**
 * Smaller circles on the outside of the PlayerCharacter. Points in the
 * opposite direction that the PlayerCharacter is going in.
 *
 * @author Towa Quimbayo
 * @version 1.0, September 29, 2022
 */
public class Tail implements IDrawable {
  // Tail object
  private Tail tail;
  // Width Value
  private final float width;
  // Height value
  private final float height;
  // PVector position values
  private final PVector position;
  // Color object
  private final Color color;
  // Scale factor constant
  private static final float SCALE_FACTOR = 0.9f;
  // Hex code for Dark Red
  protected static final int HUNDRED = 100;

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
    this.width = widthIn * SCALE_FACTOR;
    this.height = heightIn * SCALE_FACTOR;
    this.position = positionIn.add(direction);
    int count = countIn - 1;
    this.color = new Color(HUNDRED, 0, 0);
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
   * @param window for window scene
   */
  @Override
  public void draw(Window window) {
    if (this.tail != null) {
      this.tail.draw(window);
    }
    window.fill(color.getRed(), color.getGreen(), color.getBlue());
    window.ellipse(this.position.x, this.position.y, this.width, this.height);

    checkBound(window);
  }

  /**
   * Check if tail is out of bounds.
   *
   * @param window for window scene
   */
  private void checkBound(Window window) {
    if ((this.position.x > window.width || this.position.x < 0)
            || (this.position.y > window.height || this.position.y < 0)) {
      try {
        throw new TailOutOfBoundsException();
      } catch (Exception e) {
        System.out.println("Tail is out of bounds.");
      }
    }
  }
}
