package org.bcit.comp2522.labs.lab05.Lester_Betty_src;

import processing.core.PVector;

import java.awt.*;

/**
 * HomeRenderer draws the IMovable as a Home.
 *
 * @author paul_bucci
 * @version 1.0
 */
public class HomeRenderer extends AbstractRenderer {
  @Override
  public void render(Window window, Imoveable moveable) {
    Color c = moveable.getColor();
    window.fill(c.getRed(), c.getGreen(), c.getGreen());
    PVector pos = moveable.getPosition();
    float rad = moveable.getRadius();
    window.ellipse(pos.x, pos.y, rad, rad);
  }
}
