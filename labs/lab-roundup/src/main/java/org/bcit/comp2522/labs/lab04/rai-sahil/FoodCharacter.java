package org.bcit.comp2522.labs.lab04;

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

  FoodCharacter foodCharacter;

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
    this.spawnDelay = 1000;
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
   * TODO: Add Javadoc.
   *
   * @return
   */
  @Override
  public boolean collideBehaviour(ICollidable c) {
    this.bounce((float) Math.PI / 4f);
    Date now = new Date();
    if (now.getTime() - this.lastSpawn.getTime() > spawnDelay) {
      this.lastSpawn = now;
      float r = window.random((float) Math.PI);
      FoodCharacter f = new FoodCharacter(this.getPosition(), this.getDirection().rotate(r), window);
      window.addFood(f);
      return true;
    } else {
      return false;
    }
  }

  public boolean playerSizeCheck(Window window){
    if((foodCharacter.position.x == window.width/2f)
            && (foodCharacter.position.y == window.height/2f)){
      return true;
    } else {
      return false;
    }
  }

}
