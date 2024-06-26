package org.bcit.comp2522.labs.lab05.RyanLee_ArtemKhan;

import java.awt.Color;

import org.bcit.comp2522.labs.lab05.AbstractRenderer;
import processing.core.PVector;


/**
   * BoidRenderer will draw any given object as a Boid.
   *
   * @author paul_bucci
   * @author Artem Khan
   * @author Ryan Lee
   * @Date: October 2022
   * @version 1.0
 */
public class BoidRenderer extends AbstractRenderer {

  /**
   * Function that renders moveables as boids.
   *
   * @param window is the current window to render in.
   * @param moveable is the boid being rendered.
   */

  @Override
  public void render(Window window, IMoveable moveable) {
    borders(window, (Boid) moveable);
    Color c = moveable.getColor();
    // Draw a triangle rotated in the direction of velocity
    PVector position = moveable.getPosition();
    float radius = moveable.getRadius();
    float theta = moveable.getVelocity().heading() + window.radians(90);
    window.fill(200, 100);
    window.stroke(c.getRed(), c.getGreen(), c.getGreen());
    window.pushMatrix();
    window.translate(position.x, position.y);
    window.rotate(theta);
    window.beginShape(window.TRIANGLES);
    window.vertex(0, -radius * 2);
    window.vertex(-radius, radius * 2);
    window.vertex(radius, radius * 2);
    window.endShape();
    window.popMatrix();
  }

  // Wraparound
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
