package ca.bcit.comp2522.labs.lab03;

import java.awt.Color;
import java.util.Date;
import processing.core.PVector;

/**
 * The Food's character on screen.
 *
 * @author Towa, Dinuja
 * @version 1.0
 */
public class FoodCharacter extends AbstractCharacter {
  // Date for last spawn
  Date lastSpawn;
  // Spawn Delay value
  protected static final int SPAWN_DELAY = 2000;
  // 90 degrees
  protected static final int NINETY_DEGREES = 90;
  // White HEX Code value
  protected static final int TWO_FIFTY_FIVE = 255;
  // Two int value
  private static final int TWO = 2;

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
    window.rect(this.position.x,
        this.position.y,
        this.width,
        this.height);
  }

  /**
   * CollideBehavior Method for Food.
   * Moved the bounce method inside the if statement to prevent
   * the food to cling together when collided.
   *
   * @param c for Collidable object
   */
  @Override
  public void collideBehaviour(ICollidable c) {
    Date now = new Date();
    if (now.getTime() - this.lastSpawn.getTime() > SPAWN_DELAY) {
      this.bounce((float) Math.PI / TWO);
      this.lastSpawn = now;
      PVector newDirection = direction.copy();
      PVector newPos = position.copy();
      FoodCharacter fc = new FoodCharacter(newPos, newDirection.rotate(NINETY_DEGREES), window);
      window.addFood(fc);
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
