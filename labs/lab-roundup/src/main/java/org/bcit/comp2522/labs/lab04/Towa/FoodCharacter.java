package org.bcit.comp2522.labs.lab04.Towa;

import processing.core.PVector;

import java.awt.*;
import java.util.Date;

/**
 * The Food's character on screen.
 *
 * @author Towa Quimbayo
 * @version 1.0, September 29, 2022
 */
public class FoodCharacter extends AbstractCharacter {
  // Date for last spawn
  Date lastSpawn;
  // Spawn Delay value
  protected static final int SPAWN_DELAY = 2000;
  // White HEX Code value
  protected static final int TWO_FIFTY_FIVE = 255;

  /**
   * Constructor for FoodCharacter.
   *
   * @param position centrepoint position
   * @param direction unit vector pointing in direction of movement
   * @param window current scene window
   */
  public FoodCharacter(PVector position, PVector direction, Window window) {
    super(position, direction, window);
    this.lastSpawn = new Date();
    this.color = new Color(0, TWO_FIFTY_FIVE, TWO_FIFTY_FIVE);
  }

  /**
   * Draws the Food character.
   *
   * @param window current scene window.
   */
  @Override
  public void draw(Window window) {
    window.fill(color.hashCode());
    window.rect(this.position.x, this.position.y, this.width, this.height);
  }

  /**
   * CollideBehavior Method for Food.
   *
   * @param c for Collidable object
   */
  @Override
  public void collideBehaviour(ICollidable c) {
    Date now = new Date();
    this.bounce((float) Math.PI);
    if (now.getTime() - this.lastSpawn.getTime() > SPAWN_DELAY) {
      this.lastSpawn = now;
      float r = window.random((float) Math.PI);
      FoodCharacter f = new FoodCharacter(this.getPosition(),
              this.getDirection().rotate(r), window);
      window.addFood(f);
    }
  }

  /**
   * Compare the two FoodCharacter objects for equality.
   *
   * @param a for FoodCharacter Character
   * @return true if both objects are equal in width and height
   */
  @Override
  public boolean equals(final AbstractCharacter a) {
    return ((int) a.width == (int) this.width && (int) a.height == (int) this.height);
  }

  /**
   * Sets the hashcode for the FoodCharacter.
   *
   * @return the value of width * height
   */
  @Override
  public int hashCode() {
    return ((int) this.width * (int) this.height);
  }

  /**
   * toString method for printing the FoodCharacter.
   *
   * @return String value for the sum of Position X and Y, Width and Height
   */
  @Override
  public String toString() {
    return String.format("FoodCharacter: %d;", (int) this.position.x + (int) this.position.y
            + (int) this.width + (int) this.height);
  }
}
