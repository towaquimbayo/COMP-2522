package org.bcit.com2522.labs.lab03;

import java.util.Date;
import processing.core.PVector;

/**
 * The Food's character on screen.
 *
 * @author paul_bucci
 * @author Tommy Nguyen
 * @author Jashanjot Singh
 * @version 1.0
 */
public class FoodCharacter extends AbstractCharacter implements IClickable {
  Date lastSpawn;
  int spawnDelay;
  int fillColour = 255;

  private Mouse mouse;
  private final float top;
  private final float bottom;
  private final float left;
  private final float right;

  /**
   * Constructor for FoodCharacter.
   *
   * @param position centrepoint position
   * @param direction unit vector pointing in direction of movement
   * @param window current scene window
   */
  public FoodCharacter(PVector position, PVector direction, Window window) {
    super(position, direction, window);
    this.top = position.y - (height / 2f);
    this.bottom = position.y + (height / 2f);
    this.left = position.x - (width / 2f);
    this.right = position.x + (width / 2f);
    this.lastSpawn = new Date();
    this.spawnDelay = 2000;
  }

  /**
   * Draws the Food character.
   *
   * @param window current scene window.
   */
  @Override
  public void draw(Window window) {
    window.fill(this.fillColour);
    window.rect(this.position.x,
        this.position.y,
        this.width,
        this.height);
  }

  /**
   * When we use the "addNotThreadSafeFood" method, it messes up the ArrayList iterator and doesn't
   * havea correct way to handle the new Food added in. The "addFood" method works because it adds
   * it into queue instead of forcing itself into the window screen to pop up.
   */
  @Override
  public void collideBehaviour(ICollidable c) {
    this.bounce((float) Math.PI / 2);
    Date now = new Date();
    if (now.getTime() - this.lastSpawn.getTime() > spawnDelay) {
      this.lastSpawn = now;
      // TODO: finish the collision behaviour for food
      this.direction.rotate(90);
      FoodCharacter fc = new FoodCharacter(this.position.copy(), this.direction.copy(),
              this.window);
      fc.bounce((float) Math.PI / 2);
      window.addFood(fc);
    }
  }

  /**
   * Implementing method from IClickable to check if the mouse is inside the food character.
   *
   * @param window scene window
   */
  @Override
  public void clicked(Window window) {
    if (left <= window.mouseX && window.mouseX <= right) {
      if (top <= window.mouseY && window.mouseY <= bottom) {
        window.removeFood(this);
      }
    }
  }
}
