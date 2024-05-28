package org.bcit.comp2522.labs.lab06.Stanley_Brandon;

import java.awt.Color;
import processing.core.PVector;

/**
 * Represents the Enemies being drawn in the window. Enemy's superclass is Observer, and inherits
 * methods and instance data from that class.
 */
public class Enemy extends Observer {

  /**
   * Constructor for Enemy, creates a new enemy object.
   *
   * @param position the new enemy's position vector.
   * @param direction the new enemy's direction vector.
   * @param window the frame where the enemy will be drawn.
   */
  public Enemy(PVector position, PVector direction, Window window) {
    this.position = position;
    this.direction = direction;
    this.window = window;
    this.color = new Color(245, 1, 60);
    this.behaviour = new DefaultBehaviour(this);

  }

  @Override
  public PVector getPosition() {
    return position;
  }

  @Override
  public PVector getDirection() {
    return direction;
  }

  @Override
  public float getSpeed() {
    return speed;
  }

  @Override
  public float getRadius() {
    return radius;
  }

  @Override
  public void setPosition(PVector p) {
    this.position = p;
  }

  @Override
  public void setDirection(PVector d) {
    this.direction = d;
  }

  @Override
  public void setSpeed(float s) {
    this.speed = s;
  }

  @Override
  public void setRadius(float r) {
    this.speed = r;
  }

  public void draw(Window window) {
    window.fill(color.getRed(), color.getBlue(), color.getGreen());
    window.ellipse(this.position.x, this.position.y, this.radius, this.radius);
  }

  @Override
  public int compareTo(Observable o) {
    if (this.level <= o.getLevel()) {
      return -1;
    } else if (this.level > o.getLevel()) {
      return 1;
    } else {
      return 0;
    }
  }

  /**
   * Method changes each enemy's size and level based on its distance with the player object
   * during initialization of the game.
   */
  public void grow() {
    PVector playerPos = Player.getInstance(new PVector(0, 0),
            new PVector(0, 0), this.window).position;
    float distanceBetween = PVector.dist(playerPos, this.position);
    int startingLevel = 0;
    float startingRad = 10f;

    if (distanceBetween < 200) {
      startingLevel++;
      startingRad *= 2;
    } else if (distanceBetween >= 200 && distanceBetween <= 275) {
      startingLevel += 2;
      startingRad *= 3;
    } else if (distanceBetween >= 276 && distanceBetween <= 350) {
      startingLevel += 3;
      startingRad *= 4;
    } else if (distanceBetween > 350) {
      startingLevel += 4;
      startingRad *= 5;
    }

    level = startingLevel;
    radius = startingRad;
  }

  public void run(Window window) {
    behaviour.move(window);
    recalculateBehaviour();
  }


  /**
   * Method checks if an enemy is within a player's sphere of influence.
   *
   * @return true if enemy is near a player, false if enemy is not close to a player.
   */
  public boolean nearPlayer() {
    PVector playerPos = Player.getInstance(new PVector(0, 0),
            new PVector(0, 0), this.window).getPosition();

    if (Math.abs(this.position.x - playerPos.x) <= 50
            && Math.abs(this.position.y - playerPos.y) <= 50) {
      return true;
    } else {
      return false;
    }
  }

  /**
   * Method changes the enemy's behaviour based on the enemy's level, the player's level and
   * the enemy's distance from the player (whether the enemy is in the sphere of influence).
   */
  public void recalculateBehaviour() {
    Player player =  Player.getInstance(new PVector(0, 0),
            new PVector(0, 0), this.window);
    int playerLevel = player.getLevel();

    if (nearPlayer() && this.level > playerLevel) {

      ChasingBehaviour c = new ChasingBehaviour(this);
      player.registerObserver(this, c);

    } else if (nearPlayer() && this.level <= playerLevel) {

      FleeingBehaviour f = new FleeingBehaviour(this);
      player.registerObserver(this, f);
    } else if (this.behaviour instanceof DefaultBehaviour == false){

      player.unregisterObserver(this);
    }
  }
}
