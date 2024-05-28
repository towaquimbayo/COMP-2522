package org.bcit.comp2522.labs.lab03.kent_linh;

import processing.core.PVector;

import java.awt.*;

/**
 * The Player's main character on screen. It also serves as an example of
 * a Singleton design pattern. Strictly speaking, as designed here, it
 * violates the Open-Closed principle, but I'm including it regardless
 * so that we can talk about Singletons with a real example.
 *
 * @author paul_bucci
 * @author kent concengco, linh nguyen
 * @version 1.0 September 14, 2022
 * @version 2.0 September 28, 2022
 */
public class PlayerCharacter extends AbstractCharacter {
  private static PlayerCharacter playerCharacter;
  private final float increaseSizeFactor = 1f;
  private final float maxSize = 50f;
  private final float tailFactor = -5f;
  private final int tailCount = 3;
  private Tail tail;
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
   * Removes the food that collided with playerCharacter
   * and increases size if it hasn't reached maxSize.
   *
   * @param c as object implementing ICollidable
   */
  @Override
  public void collideBehaviour(ICollidable c) {
    if (c instanceof FoodCharacter food) {
      window.removeFood(food);
      if (this.getHeight() <= maxSize) {
        setSize(this.getHeight() + increaseSizeFactor);
      }
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
    window.fill(color.getRed(), color.getGreen(), color.getBlue());
    window.ellipse(this.position.x,
        this.position.y,
        this.width,
        this.height);
  }

  /**
   * Adds the instance of food that is clicked by the mouse
   * to the removeFoodQueue, which will delete it in the
   * next draw() function call.
   *
   * @param mouse as mouse
   */
  public void clicked(Mouse mouse) {
    PVector mousePos = new PVector(mouse.getPosition().x, mouse.getPosition().y);
    PVector playerPos = new PVector(this.position.x, this.position.y);
    this.direction = mousePos.add(playerPos.mult(-1f)).normalize();
  }

}
