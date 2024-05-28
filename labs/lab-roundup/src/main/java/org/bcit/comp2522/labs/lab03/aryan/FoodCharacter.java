package org.bcit.comp2522.labs.lab03.aryan;

import java.util.Date;
import processing.core.PVector;

/**
 * The Food's character on screen.
 *
 * @author paul_bucci, Aryan Jand, & Ammarra Hong
 * @version 2.0
 */
public class FoodCharacter extends AbstractCharacter {
  Date lastSpawn;
  int spawnDelay;
  int fillColour = 255;

  /**
   * Constructor for FoodCharacter.
   *
   * @param position  centrepoint position
   * @param direction unit vector pointing in direction of movement
   * @param window    current scene window
   */
  public FoodCharacter(PVector position, PVector direction, Window window) {
    super(position, direction, window);
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
   * Collides different Food Character object in the Window.
   * By determining the new position and direction of the Food Character objects.
   * Why does addNotThreadSafeFood blow up?
   * When using this method in the program it stops working because
   * it is trying to change the drawables, characters, and colidables Arraylist
   * to create more Food Characters
   * objects at the same time when the program is using it to run them in the window.
   * Why does addFood method not blow up?
   * The addFood method does crash the program because it uses another Arraylist called addFoodQueue
   * which iterates though a for each loop populating the other arrays.
   * After which it displays in the window.
   *
   * @param c unused
   */
  @Override
  public void collideBehaviour(ICollidable c) {
    Date now = new Date();
    if (now.getTime() - this.lastSpawn.getTime() > spawnDelay) {
      this.lastSpawn = now;
      this.bounce((float) Math.PI / 2);
      PVector position = this.position.copy();
      PVector direction = this.direction.copy().rotate(180);

      FoodCharacter fc = new FoodCharacter(position, direction, window);
      this.window.addFood(fc);
    }
  }
}
