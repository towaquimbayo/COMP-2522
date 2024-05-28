package org.bcit.com2522.labs.lab03;

import java.awt.Color;
import processing.core.PVector;

import java.util.Vector;

/**
 * Mouse class that implements IDrawable for TODO 5.
 */
public class Mouse implements IDrawable {

  private static Mouse mouse;
  private final PVector position;
  private final Color color;
  private final float width = 20f;
  private final PlayerCharacter player;

  /**
   * Constructor for Mouse.
   *
   * @param player the player character
   */
  Mouse(PlayerCharacter player) {
    this.position = new PVector();
    this.color = new Color(0, 100, 200);
    this.player = player;
  }

  public BoundingBox getBoundingBox(Window window) {
    return new BoundingBox(new PVector(window.mouseX, window.mouseY), width, width);
  }

  /**
   * Method for changing the player direction based on
   * a series of vectors.
   */
  public void changePlayerDirection() {
    PVector playerPosCopy = player.position.copy();
    player.direction.set(position.add(playerPosCopy.mult(-1f)).normalize());
  }

  /**
   * Drawing the window.
   *
   * @param window the window
   */
  @Override
  public void draw(Window window) {
    window.fill(color.getRed(), color.getGreen(), color.getBlue());
    position.set(window.mouseX, window.mouseY);
    window.ellipse(position.x, position.y, width, width);
  }
}