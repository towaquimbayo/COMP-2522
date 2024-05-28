package org.bcit.comp2522.labs.lab03.noufil_siwoon;

import processing.core.PVector;

import java.awt.*;
import java.util.ArrayList;

/**
 * The Player's main character on screen. It also serves as an example of
 * a Singleton design pattern. Strictly speaking, as designed here, it
 * violates the Open-Closed principle, but I'm including it regardless
 * so that we can talk about Singletons with a real example.
 *
 * @author paul_bucci & Siwoon Lim & Noufil Saqib
 * @version 1.0
 */
public class PlayerCharacter extends AbstractCharacter {
  private static PlayerCharacter playerCharacter;
  private static final int COLOR_HEX = 200;
  private static final float TAIL_FACTOR = -5f;
  private static final int TAIL_COUNT = 3;

  private PlayerCharacter(PVector position, PVector direction, Window window) {
    super(position, direction, window);
    this.color = new Color(COLOR_HEX, 0, 0);
  }

  /**
   * Constructor for PlayerCharacter.
   *
   * @param position current center-point position
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
   * TODO: Add Javadoc.
   *
   * @param c the FoodCharacter that collides with PlayerCharacter
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

  /**
   * Rotates the direction of the player character to the mouse.
   *
   * @param pplayer the player character PVector
   * @param pmouse the mouse PVector
   */
  public void bringPlayerToMouse(PVector pplayer, PVector pmouse) {
    this.direction = pmouse.add(pplayer.mult(-1f)).normalize();
  }

  /**
   * Removes the food character when it is clicked on.
   *
   * @param mouse the mouse object
   * @param characters the arraaylist of all objects that implement AbstractCharacter
   */
  public void removeFoodOnClick(Mouse mouse, ArrayList<AbstractCharacter> characters) {
    float d = mouse.diameter / 2;
    for (AbstractCharacter c : characters) {
      if (c instanceof FoodCharacter
              && ((mouse.xpos + d) > (c.position.x - c.getWidth())
              && (mouse.xpos - d) < (c.position.x + c.getWidth())
              && (mouse.ypos + d) > (c.position.y - c.getHeight())
              && (mouse.ypos - d) < (c.position.y + c.getHeight()))) {
        window.removeFood((FoodCharacter) c);
        break;
      }
    }
  }

}
