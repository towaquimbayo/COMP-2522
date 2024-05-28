package org.bcit.comp2522.labs.lab03.alexg_alexk;

import processing.core.PVector;

import java.awt.*;

/**
 * The Player's main character on screen. It also serves as an example of
 * a Singleton design pattern. Strictly speaking, as designed here, it
 * violates the Open-Closed principle, but I'm including it regardless
 * so that we can talk about Singletons with a real example.
 *
 * @author paul_bucci Alex Kong Alex Gibbison
 * @version 1.0
 */
public class PlayerCharacter extends AbstractCharacter {
  private static PlayerCharacter playerCharacter;
  private Tail tail;
  private final float tailFactor = -5f;
  private int tailCount = 3;
  Color color;

  private PlayerCharacter(PVector position, PVector direction, Window window) {
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
  public static PlayerCharacter getInstance(PVector position, PVector direction, Window window) {
    if (playerCharacter == null) {
      playerCharacter = new PlayerCharacter(position, direction, window);
    }
    return playerCharacter;
  }

  /**
   * Removes food when the player collides with it and increases the size of the player.
   *
   * @param c the other collidable
   */
  @Override
  public void collideBehaviour(ICollidable c) {
    // TODO: finish the collision behaviour for the Player.
    if (c instanceof FoodCharacter) {
      window.removeFood((FoodCharacter) c);
      playerCharacter.tailCount += 1;
    }
  }

  /**
   * Draws the player's character.
   *
   * @param window current scene window
   */
  @Override
  public void draw(Window window) {
    this.tail = new Tail(
      this.position.copy(),
      this.direction.copy().mult(this.tailFactor),
      this.width,
      this.height,
      this.tailCount
    );
    tail.draw(window);
    window.fill(color.getRed(), color.getGreen(), color.getGreen());
    window.ellipse(this.position.x,
        this.position.y,
        this.width,
        this.height);
  }

}
