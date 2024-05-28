package org.bcit.comp2522.labs.lab06;

import processing.core.PVector;

/**
 * PlayerRadius class for Player's seek radius.
 *
 * @author Towa Quimbayo, Alex Gibbison
 * @version 1.0
 */
public class PlayerRadius implements Drawable {
  /** Seek Radius for player. */
  private final float seekRadius;
  /** Window for PlayerRadius. */
  private final Window window;
  /** Player character. */
  private final Player player;
  /** Size of radius. */
  private float size;
  /** Constant variable for default size of seek radius. */
  public static final float HUNDRED = 100f;
  /** Constant variable for default color of radius. */
  public static final int DEFAULT_COLOR = 180;
  /** Constant variable to get half the radius. */
  public static final float TWO = 2f;
  /** Border Radius of the seekRadius. */
  public static final float BORDER_RADIUS = 15f;

  /**
   * Constructor for PlayerRadius.
   *
   * @param win for Window
   * @param p for Player
   */
  public PlayerRadius(Window win, Player p) {
    this.window = win;
    this.player = p;
    this.seekRadius = HUNDRED;
  }

  /**
   * Getter for seekRadius.
   *
   * @return this.seekRadius
   */
  private float getSeekRadius() {
    return this.seekRadius;
  }

  /**
   * Gets the Bounding Box for collision detection.
   *
   * @return an on-the-fly BoundingBox
   */
  public BoundingBox getBoundingBox() {
    PVector newPos = new PVector(this.player.getPosition().x, this.player.getPosition().y);
    return new BoundingBox(newPos, this.size, this.size, this.window);
  }

  /**
   * Draw the PlayerRadius on window.
   */
  @Override
  public void draw() {
    this.size = getSeekRadius() + this.player.getRadius();
    this.window.fill(DEFAULT_COLOR, DEFAULT_COLOR, DEFAULT_COLOR);
    this.window.rect(this.player.getPosition().x - (this.size / TWO),
            this.player.getPosition().y - (this.size / TWO),
            this.size, this.size, BORDER_RADIUS);
  }
}
