package org.bcit.comp2522.labs.lab03.taylor_ji;

import processing.core.PVector;

public class Mouse implements IDrawable {

  private PVector location;
  private float diameter = 10;

  @Override
  public void draw(Window window) {
    window.circle(window.mouseX, window.mouseY, diameter);
    location = new PVector(window.mouseX, window.mouseY);
  }

  public PVector getPosition() {
    return this.location;
  }

  public float getDiameter() {
    return this.diameter;
  }
}
