package org.bcit.comp2522.labs.lab04.noufil;

import processing.core.PVector;

import java.awt.Window;
import java.awt.*;

/**
 * The Player's main character on screen. It also serves as an example of
 * a Singleton design pattern. Strictly speaking, as designed here, it
 * violates the Open-Closed principle, but I'm including it regardless
 * so that we can talk about Singletons with a real example.
 *
 * @author paul_bucci
 * @version 1.0
 */
public class PlayerCharacter extends AbstractCharacter {
  private static PlayerCharacter playerCharacter;
  private static final float TAIL_FACTOR = -5f;
  private static final int TAIL_COUNT = 3;
  Color color;

  private PlayerCharacter(PVector position, PVector direction, java.awt.Window window) {
    super(position, direction, window);
    this.color = new Color(200, 0, 0);
  }

  /**
   * Constructor for PlayerCharacter.
   *
   * @param position current centrepoint position
   * @param direction unit vector (length of 1) pointing in current direction
   * @param window scene window
   * @return returns new PlayerCharacter
   */
  public static PlayerCharacter getInstance(PVector position, PVector direction, java.awt.Window window) {
    if (playerCharacter == null) {
      playerCharacter = new PlayerCharacter(position, direction, window);
    }
    return playerCharacter;
  }

  /**
   * Detects collision.
   *
   * @param c a collidable object
   */
  @Override
  public void collideBehaviour(ICollidable c) {
    if (c instanceof FoodCharacter) {
      this.window.removeFood((FoodCharacter) c);
    }
    this.setHeight(this.getHeight() + 1);
    this.setWidth(this.getWidth() + 1);
  }

  /**
   * Draws the player's character.
   *
   * @param window current scene window
   */
  @Override
  public void draw(Window window) {
    Tail tail;

    tail = new Tail(
      this.position.copy(),
      this.direction.copy().mult(TAIL_FACTOR),
      this.width,
      this.height,
      TAIL_COUNT
    );
    tail.draw(window);
    window.fill(color.getRed(), color.getGreen(), color.getGreen());
    window.ellipse(this.position.x,
        this.position.y,
        this.width,
        this.height);
  }

}
