package org.bcit.comp2522.labs.lab03.taylor_ji;

import processing.core.PVector;

import java.util.Date;


/**
 * The Food's character on screen.
 *
 * @author paul_bucci
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
   * addNotThreadSafeFood method modifier is public ,
   * and it crashes the app because of ConcurrentModificationException.
   * addFood method modifier is private which blocks ConcurrentModificationException.
   */
  @Override
  public void collideBehaviour(ICollidable c) {
    this.bounce((float) Math.PI / 2);
    Date now = new Date();
    if (now.getTime() - this.lastSpawn.getTime() > spawnDelay) {
      this.lastSpawn = now;

      PVector newDir = PVector.random2D();

      FoodCharacter newFood = new FoodCharacter(position.copy(),
          newDir, window);
      window.addFood(newFood);

    }
  }
}
