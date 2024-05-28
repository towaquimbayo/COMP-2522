package org.bcit.comp2522.labs.lab03.kent_linh;

import processing.core.PVector;

import java.awt.*;


/**
 * The shape following the mouse pointer on screen.
 *
 * @author kent concengco, linh nguyen
 * @version 1.0 September 28, 2022
 */
public class Mouse implements IDrawable, IMovable {

  private PVector position;

  private final float diameter;

  private final float offSet = 10f;

  private final Color color = new Color(0, 200, 0);

  Mouse(final PVector pos, float d) {
    this.position = new PVector(pos.x, pos.y + offSet);
    this.diameter = d;
  }

  @Override
  public void draw(Window window) {
    window.fill(color.getRed(), color.getGreen(), color.getBlue());
    window.ellipse(this.position.x, this.position.y, this.diameter, this.diameter);
  }

  @Override
  public PVector getPosition() {
    return position;
  }

  public void move(Window window) {
    this.position = new PVector(window.mouseX, window.mouseY + offSet);
  }
}
