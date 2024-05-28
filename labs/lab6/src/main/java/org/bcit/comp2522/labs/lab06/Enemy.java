package org.bcit.comp2522.labs.lab06;

import java.awt.Color;
import processing.core.PVector;

/**
 * Enemy character that combats the player.
 * Each enemy is stored in Enemy collection
 * and compares with Player in strength (width).
 *
 * @author Towa Quimbayo, Alex Gibbison
 * @version 1.0
 */
public class Enemy extends AbstractObserver implements Comparable<Player>, Drawable {
  /** Position of Enemy. */
  private PVector position;
  /** Direction of Enemy. */
  private PVector direction;
  /** Width of Enemy. */
  private float width;
  /** Window for Enemy. */
  protected final Window window;
  /** Boolean variable for if Enemy is in player radius. */
  private boolean isInRadius;
  /** Color of Enemy. */
  private Color color;
  /** Player Character. */
  private final Player player;
  /** Constant variable for division of PI. */
  public static final float FOUR = 4f;
  /** Constant variable for the constant growth in width of Enemy. */
  public static final int TWENTY_THOUSAND = 20000;
  /** Constant variable for speed of Enemy. */
  public static final float SPEED = 1f;
  /** Constant variable for half of the width value. */
  public static final float TWO = 2f;
  /** Constant for minimum random width value. */
  public static final float FIVE = 5f;
  /** Constant for maximum random width value. */
  public static final float THIRTY = 30f;
  /** Constant for changing Color of Enemy. */
  public static final int TWO_FIFTY_FIVE = 255;

  /**
   * Constructor for Enemy.
   */
  public Enemy(PVector pos, PVector dir, Window win, Player p) {
    this.position = pos;
    this.direction = dir;
    this.window = win;
    this.player = p;
    this.width = this.window.random(FIVE, THIRTY);
    this.setColor(TWO_FIFTY_FIVE, TWO_FIFTY_FIVE, TWO_FIFTY_FIVE);
    this.setIsInRadius(false);
  }

  /**
   * Getter for Width.
   *
   * @return this.width of Enemy
   */
  public float getWidth() {
    return this.width;
  }

  /**
   * Setter for IsInRadius.
   *
   * @param isRadius true of false is set
   */
  public void setIsInRadius(Boolean isRadius) {
    this.isInRadius = isRadius;
  }

  /**
   * Setter for Color.
   *
   * @param r for red
   * @param g for green
   * @param b for blue
   */
  public void setColor(int r, int g, int b) {
    this.color = new Color(r, g, b);
  }

  /**
   * Bounding box method to check
   * if player is within the boundary.
   */
  private boolean isOutOfBounds() {
    return (this.position.x < 0 || this.position.x > window.width
            || this.position.y < 0 || this.position.y > window.height);
  }

  /**
   * Gets the Bounding Box for collision detection.
   *
   * @return an on-the-fly BoundingBox
   */
  public BoundingBox getBoundingBox() {
    return new BoundingBox(this.position, this.width, this.width, this.window);
  }

  /**
   * Moves the Enemy and adds the speed accordingly
   * to the direction PVector.
   * If its outOfBounds, rotate the Enemy by 45 degrees.
   * Call the grow() method to allow the Enemy to grow in
   * width every frame.
   */
  public void move() {
    this.position = this.position.add(this.direction.mult(SPEED));
    if (isOutOfBounds()) {
      this.direction.rotate((float) Math.PI / FOUR);
    }
    this.grow();
  }

  /**
   * The enemy's width is growing every frame
   * by measuring the distance from player's
   * position to its own position.
   */
  private void grow() {
    float distanceX = Math.abs(this.position.x - this.player.getPosition().x);
    float distanceY = Math.abs(this.position.y - this.player.getPosition().y);
    this.width += (distanceX + distanceY) / (TWENTY_THOUSAND);
  }

  /**
   * Draw the Enemy, shift the position of where the rectangle
   * is drawn because it's always drawn from top left corner.
   */
  @Override
  public void draw() {
    window.fill(color.getRed(), color.getGreen(), color.getBlue());
    window.rect(this.position.x - (width / TWO),
            this.position.y - (width / TWO), this.width, this.width);
  }

  /**
   * Update method from Observable pattern (AbstractObserver).
   * Receive the message from Player of its position and
   * compare for each Enemy if its weaker or stronger than Player.
   * If Enemy is weaker than Player, rotate the Enemy and avoid the player,
   * else if Enemy is stronger than Player then change its direction to
   * point towards the Player.
   * Red color for Strong enemy, Green color for Weaker enemy.
   *
   * @param pos for a position of player
   * @param e for AbstractObserver
   */
  @Override
  public void update(PVector pos, AbstractObserver e) {
    if ((((Enemy) e).compareTo(this.player) > 0) && (!((Enemy) e).isInRadius)) {
      // Change to green since Enemy is weaker than Player and rotate
      ((Enemy) e).color = new Color(0, TWO_FIFTY_FIVE, 0);
      ((Enemy) e).direction.rotate((float) (Math.PI / FOUR));
      isInRadius = true;
    } else if (((Enemy) e).compareTo(this.player) == 0) {
      // Change to red since Enemy is stronger than Player, then point its direction
      ((Enemy) e).color = new Color(TWO_FIFTY_FIVE, 0, 0);
      PVector target = new PVector(this.player.getPosition().x, this.player.getPosition().y);
      PVector enemy = new PVector(((Enemy) e).position.x, ((Enemy) e).position.y);
      ((Enemy) e).direction = target.add(enemy.mult(-1f)).normalize();
    }
  }

  /**
   * Compare each Enemy with the Player in width,
   * if Enemy is stronger than Player, then return 0,
   * if weaker than Player then return 1.
   *
   * @param p the Player to be compared.
   * @return 1 if weaker, 0 if stronger
   */
  @Override
  public int compareTo(Player p) {
    if (p.getRadius() >= this.width) {
      return 1;
    }
    return 0;
  }

  /**
   * Equals method to check equality between 2 enemies.
   *
   * @param e for Enemy
   * @return true if equal
   */
  public boolean equals(Enemy e) {
    return (((int) this.width == (int) e.width)
            && (this.position.x == e.position.x)
            && (this.position.y == e.position.y));
  }

  /**
   * Hashcode method for Enemy.
   *
   * @return hashcode for Enemy
   */
  public int hashCode() {
    return (int) ((this.position.x + this.position.y) * this.width);
  }

  /**
   * toString method for Enemy.
   *
   * @return a string message on Enemy position and width
   */
  public String toString() {
    return "Enemy Width: " + this.width + ", Position: " + this.position;
  }
}
