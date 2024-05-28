package org.bcit.comp2522.labs.lab03.alexg_alexk;

import processing.core.PVector;

import java.util.Date;

import static java.lang.Math.random;


/**
 * The Food's character on screen.
 *
 * @author paul_bucci Alex Kong Alex Gibbison
 * @version 1.0
 */
public class FoodCharacter extends AbstractCharacter {
  Date lastSpawn;
  int spawnDelay;
  int fillColour = 255;

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
    // milliseconds
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
   * Handles collision between two collidable objects.
   *
   * Using addThreadSafeFood doesn't work because the method is private.
   * Using addFood works because the method public.
   *
   * @param c the other collidable object
   */
  @Override
  public void collideBehaviour(ICollidable c) {

    Date now = new Date();
    if (now.getTime() - this.lastSpawn.getTime() > spawnDelay && c instanceof FoodCharacter) {
      this.bounce((float) Math.PI / 2);
      this.lastSpawn = now;
      // random direction between -1f and 1f
      PVector newFoodDirection = new PVector((float) (random() * 2 - 1),
          (float) (random() * 2 - 1)).normalize();
      FoodCharacter newFood =
          new FoodCharacter(this.position.copy(), newFoodDirection, this.window);
      this.window.addFood(newFood);
    }
  }

}
