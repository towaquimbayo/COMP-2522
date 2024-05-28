package org.bcit.comp2522.labs.lab03.aryan;

import java.awt.Color;
import processing.core.PVector;

/**
 * The Player's main character on screen. It also serves as an example of
 * a Singleton design pattern. Strictly speaking, as designed here, it
 * violates the Open-Closed principle, but I'm including it regardless
 * so that we can talk about Singletons with a real example.
 *
 * @author paul_bucci, Aryan Jand, & Ammarra Hong
 * @version 2.0
 */
public class PlayerCharacter extends AbstractCharacter implements IHoverable {
  private static PlayerCharacter playerCharacter;
  private Tail tail;
  private final float tailFactor = -5f;
  private final int tailCount = 3;
  Color color;

  private PlayerCharacter(PVector position, PVector direction, Window window) {
    super(position, direction, window);
    boolean boo = mouseHover(this);
    if (boo) {
      this.color = new Color(12, 255, 12, 158);
    } else {
      this.color = new Color(219, 12, 12, 158);

    }
  }

  /**
   * Constructor for PlayerCharacter.
   *
   * @param position  current centrepoint position
   * @param direction unit vector (length of 1) pointing in current direction
   * @param window    scene window
   * @return returns new PlayerCharacter
   */
  public static PlayerCharacter getInstance(PVector position, PVector direction, Window window) {
    if (playerCharacter == null) {
      playerCharacter = new PlayerCharacter(position, direction, window);
    }
    return playerCharacter;
  }

  /**
   * Removes food characters when the player characters collides with it.
   *
   * @param c food character
   */
  @Override
  public void collideBehaviour(ICollidable c) {
    if (c instanceof FoodCharacter) {
      this.window.removeFood((FoodCharacter) c);
      this.width += 1;
      this.height += 1;
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

  /**
   * Sposes to check if the mouse is hovering over the player;
   *
   * @param player IColloidal
   * @return true
   */
  @Override
  public boolean mouseHover(ICollidable player) {
    return true;
  }
}
