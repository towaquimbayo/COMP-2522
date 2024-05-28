package org.bcit.comp2522.labs.lab06;

import java.util.ArrayList;
import processing.core.PVector;
import processing.event.KeyEvent;

/**
 * Create only one instance of a player to follow the singleton
 * rule to prevent any further instantiation of the player. Only
 * one copy of the player exists.
 *
 * @author Towa Quimbayo, Alex Gibbison
 * @version 1.0
 */
public class Player extends AbstractObservable implements Comparable<Enemy>, Drawable {
  /** PVector position. */
  private PVector position;
  /** Radius of Player. */
  private float radius;
  /** Window for Player. */
  private final Window window;
  /** Load a singleton of a player instance to prevent further instantiation. */
  private static Player player;
  /** Player's seek radius. */
  private PlayerRadius playerRadius;
  /** Constant variable for moving the player. */
  public static final float TEN = 10f;
  /** Constant variable for radius of Player. */
  public static final float RADIUS_VAL = 25f;
  /** Constant variable for color of Player. */
  public static final int FIFTY = 50;
  /** Constant variable for color of Player. */
  public static final int TWO_FIFTY_FIVE = 255;

  /**
   * Constructor for Player.
   */
  private Player(PVector pos, Window win) {
    position = pos;
    window = win;
    radius = RADIUS_VAL;
    observers = new ArrayList<>();
  }

  /**
   * Getter for Position.
   *
   * @return this.position
   */
  public PVector getPosition() {
    return position;
  }

  /**
   * Setter for position.
   *
   * @param pos for position
   */
  public void setPosition(PVector pos) {
    position = pos;
  }

  /**
   * Getter for Radius.
   *
   * @return this.radius
   */
  public float getRadius() {
    return radius;
  }

  /**
   * Setter for Radius.
   *
   * @param r radius value
   */
  public void setRadius(float r) {
    radius = r;
  }

  /**
   * Getter for PlayerRadius.
   *
   * @return this.playerRadius
   */
  public PlayerRadius getPlayerRadius() {
    return playerRadius;
  }

  /**
   * Instantiate the PlayerRadius.
   */
  public void createPlayerRadius() {
    playerRadius = new PlayerRadius(window, this);
  }

  /**
   * Returns a reference to the player instance
   * to follow the singleton rule.
   *
   * @return player
   */
  public static Player getInstance(PVector position, Window window) {
    if (player == null) {
      player = new Player(position, window);
    }
    return player;
  }

  /**
   * Reset the width for Player.
   */
  private void resetWidth() {
    radius = RADIUS_VAL;
  }

  /**
   * Reset the position for Player.
   *
   * @param pos for position
   */
  private void resetPosition(PVector pos) {
    setPosition(pos);
  }

  /**
   * Reset the player when game over.
   *
   * @param pos for position
   */
  public void reset(PVector pos) {
    resetPosition(pos);
    resetWidth();
  }

  /**
   * Bounding box method to check
   * if player is within the boundary.
   */
  protected boolean isOutOfBounds() {
    return (position.x < 0 || position.x > window.width
            || position.y < 0 || position.y > window.height);
  }

  /**
   * Gets the Bounding Box for collision detection.
   *
   * @return an on-the-fly BoundingBox
   */
  public BoundingBox getBoundingBox() {
    return new BoundingBox(position, radius, radius, window);
  }

  /**
   * Move the player by KeyEvent from Window.
   *
   * @param event for KeyEvent
   */
  public void move(KeyEvent event) {
    if (event.getKeyCode() == window.RIGHT) {
      position.x += TEN;
      if (isOutOfBounds()) {
        position.x = window.width;
      }
    } else if (event.getKeyCode() == window.LEFT) {
      position.x -= TEN;
      if (isOutOfBounds()) {
        position.x = 0f;
      }
    } else if (event.getKeyCode() == window.UP) {
      position.y -= TEN;
      if (isOutOfBounds()) {
        position.y = 0f;
      }
    } else if (event.getKeyCode() == window.DOWN) {
      position.y += TEN;
      if (isOutOfBounds()) {
        position.y = window.height;
      }
    }
  }

  /**
   * Draw the Player on window.
   */
  @Override
  public void draw() {
    window.fill(FIFTY, FIFTY, TWO_FIFTY_FIVE);
    window.ellipse(position.x, position.y, radius, radius);
  }

  /**
   * Register the observer in the list.
   *
   * @param obs for Enemy
   */
  @Override
  public void registerObserver(AbstractObserver obs) {
    observers.add(obs);
  }

  /**
   * Unregister the observer from the list.
   *
   * @param obs for Enemy
   */
  @Override
  public void unregisterObserver(AbstractObserver obs) {
    observers.remove(obs);
  }

  /**
   * Notify the observers in the list.
   *
   * @param obs for Enemy
   */
  @Override
  public void notifyObserver(AbstractObserver obs) {
    observers.forEach(observer -> observer.update(position, obs));
  }

  /**
   * Compare with an enemy strength and check
   * if its stronger or weaker than player.
   * If enemy is stronger than player then
   * reset the game, if weaker than player then
   * player gains a unit of strength.
   *
   * @param e the Enemy to be compared.
   * @return 1 if player is stronger, 0 if weaker
   */
  @Override
  public int compareTo(Enemy e) {
    if (radius >= e.getWidth()) {
      return 1;
    }
    return 0;
  }

  /**
   * CollideBehavior for when Player collides with Enemy.
   *
   * @param e for Enemy
   * @param enemyCollection for EnemyCollection
   */
  public void playerCollidesEnemy(Enemy e, EnemyCollection<Enemy> enemyCollection) {
    if (this.compareTo(e) > 0) {
      setRadius(getRadius() + e.getWidth());
      unregisterObserver(e);
      enemyCollection.addRemoveEnemyQueue(e);
      if (enemyCollection.getSize() <= 1) {
        System.out.println("Congratulations, You WON!");
      }
    } else {
      window.restartGame();
    }
  }

  /**
   * ToString Method for Player.
   *
   * @return string message of Player
   */
  public String toString() {
    return "Player Width: " + getRadius() + ", Position: " + getPosition();
  }
}
