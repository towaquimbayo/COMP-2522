package org.bcit.comp2522.labs.lab03.alexg_alexk;

import processing.core.PVector;

import java.awt.*;

/**
 * Circle that tracks the cursor within the window.
 * When mouse is clicked, the player character changes direction to go toward the cursor.
 *
 * @author Alex Kong, Alex Gibbison
 * @version 1.0
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
   * @param window the window
   * @param player the player character
   */
  private Mouse(Window window, PlayerCharacter player) {
    this.position = new PVector(window.mouseX, window.mouseY);
    this.color = new Color(0, 100, 200);
    this.player = player;
  }

  /**
   * Constructor for Mouse. Uses singleton code from PlayerCharacter.
   *
   * @param window the window that is running
   * @param player the player character
   * @return the single instance of mouse
   */
  public static Mouse getInstance(Window window, PlayerCharacter player) {
    if (mouse == null) {
      mouse = new Mouse(window, player);
    }
    return mouse;
  }

  public BoundingBox getBoundingBox(Window window) {
    return new BoundingBox(new PVector(window.mouseX, window.mouseY), width, width);
  }

  /**
   * Changes the direction of player character to face towards the cursor.
   */
  public void changePlayerDirection() {
    PVector playerPosCopy = player.position.copy();
    player.direction.set(position.add(playerPosCopy.mult(-1f)).normalize());
  }

  /**
   * Draws the circle that tracks the cursor.
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
