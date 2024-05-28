package org.bcit.comp2522.labs.lab06.Stanley_Brandon;

import java.awt.Color;
import processing.core.PVector;


/**
 * The Player's main character on screen. It follows the singleton design pattern, where there is
 * only 1 instance of the player per class, the sole instance of the class can be accessed, there
 * will only be 1 instance across our classes (in window), and only be instantiated in window.
 *
 * @author Brandon Chan
 * @author Stanley Chow
 * @version 1.0
 */
public class Player extends Observable {

  // must be static, we want to share this player variable with other classes (ie window)
  private static Player player;


  /**
   * Private constructor for player restricts the number of instances of player that can be
   * created.
   *
   * @param position player position
   * @param direction player direction
   * @param window scene window
   */
  private Player(PVector position, PVector direction, Window window) {
    this.position = position;
    this.direction = direction;
    this.window = window;
    this.color = new Color(0, 255, 255);
  }

  /**
   * Creates a single instance of a player that can be accessed in window if it does not already
   * exist.
   *
   * @param position player position
   * @param direction player direction
   * @param window scene window
   * @return player
   */
  public static Player getInstance(PVector position, PVector direction, Window window) {
    if (player == null) {
      player = new Player(position, direction, window);
    }
    return player;
  }

  /**
   * Handles movement of a player singleton.
   *
   * @param window is the processing window
   */
  public void move(Window window) {
    this.position = this.position.add(this.direction.mult(speed));
  }


  /**
   * Draws the player's character.
   *
   * @param window current scene window
   */
  public void draw(Window window) {
    window.fill(color.getRed(), color.getBlue(), color.getGreen());
    window.ellipse(this.position.x, this.position.y, this.radius, this.radius);
  }

  /**
   * Compares the level of the player to an observer object.
   *
   * @param o the object to be compared.
   * @return -1 if the player is a lower level, 1 if equal to higher
   */
  @Override
  public int compareTo(Observer o) {
    if (this.level < o.getLevel()) {
      return -1;
    } else if (this.level >= o.getLevel()) {
      return 1;
    } else {
      return 0;
    }
  }

  @Override
  public void collideBehaviour(ICollidable c) {
    if (this.compareTo((Observer) c) > 0) {
      this.window.removeEnemy((Enemy) c);
      level++;
      radius += 10f;
    }

    if (this.compareTo((Observer) c) < 0) {
      this.window.resetGame();
    }
  }

  @Override
  public void run(Window window) {
    move(window);

  }
}
