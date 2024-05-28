package org.bcit.comp2522.labs.lab05;

import java.awt.Color;
import processing.core.PVector;

/**
 * BoidRenderer will draw any given object as a Boid.
 *
 * @author Towa Quimbayo, Alex Gibbison
 * @version 1.0
 */
public class BoidRenderer extends AbstractRenderer {
  /** Constant for window fill 200. */
  public static final int TWO_HUNDRED = 200;
  /** Constant for window fill 2. */
  public static final int TWO = 2;
  /** Constant for radian. */
  public static final int NINETY = 90;

  /**
   * Render Boid.
   *
   * @param window for window
   * @param moveable for IMoveable object
   */
  @Override
  public void render(Window window, IMoveable moveable) {
    borders(window, (Boid) moveable);
    Color c = moveable.getColor();
    // Draw a triangle rotated in the direction of velocity
    PVector position = moveable.getPosition();
    float radius = moveable.getRadius();
    float theta = moveable.getVelocity().heading() + window.radians(NINETY);
    window.fill(TWO_HUNDRED, (float) (TWO_HUNDRED / TWO));
    window.stroke(c.getRed(), c.getGreen(), c.getGreen());
    window.pushMatrix();
    window.translate(position.x, position.y);
    window.rotate(theta);
    window.beginShape(window.TRIANGLES);
    window.vertex(0, -radius * TWO);
    window.vertex(-radius, radius * TWO);
    window.vertex(radius, radius * TWO);
    window.endShape();
    window.popMatrix();
  }

  /**
   * Borders to wrap around.
   *
   * @param window for window
   * @param boid for boid
   */
  private void borders(Window window, Boid boid) {
    PVector position = boid.getPosition();
    float radius = boid.getRadius();
    if (position.x < -radius) {
      position.x = window.width + radius;
    }
    if (position.y < -radius) {
      position.y = window.height + radius;
    }
    if (position.x > window.width + radius) {
      position.x = -radius;
    }
    if (position.y > window.height + radius) {
      position.y = -radius;
    }
  }
}
