package org.bcit.comp2522.labs.lab06;

import processing.core.PApplet;
import processing.core.PVector;
import processing.event.KeyEvent;

/**
 * Flocking: An implementation of Craig Reynold's Boids program to
 * simulate the flocking behavior of birds. Each boid steers itself
 * based on rules of avoidance, alignment, and coherence.
 *
 * @author Daniel Shiffman, extended and refactored by paul_bucci, Towa Quimbayo, Alex Gibbison
 * @version 1.0
 */
public class Window extends PApplet {
  /** Player Character. */
  private Player player;
  /** EnemyCollection object. */
  private EnemyCollection<Enemy> enemyCollection;
  /** Number of Enemies. */
  public static final int NUM_OF_ENEMIES = 10;
  /** Constant variable for half of the width value. */
  public static final float TWO = 2f;
  /** Starting position for all enemies. */
  public static final float TEN = 10f;
  /** Window Width. */
  public static final int WINDOW_WIDTH = 640;
  /** Window Height. */
  public static final int WINDOW_HEIGHT = 360;

  /**
   * Runs before applet starts.
   * Set up all objects.
   */
  public void setup() {
    PVector playerPos = new PVector(width / TWO, height / TWO);
    player = Player.getInstance(playerPos, this);
    player.createPlayerRadius();

    enemyCollection = new EnemyCollection<>();

    // Load up all 10 enemies in the list
    // Add every enemy to the collection and register in observer list
    for (int i = 0; i < NUM_OF_ENEMIES; i++) {
      PVector enemyPos = new PVector(TEN, TEN);
      PVector enemyDir = new PVector(random(-1f, 1f), random(-1f, 1f));
      Enemy enemy = new Enemy(enemyPos, enemyDir, this, player);
      enemyCollection.add(enemy);
      player.registerObserver(enemy);
    }
  }

  /**
   * Draw the characters on window,
   * runs on each frame.
   */
  public void draw() {
    background(0);
    player.getPlayerRadius().draw();

    enemyCollection.remove();

    // Iterate each Enemy in EnemyCollection
    for (Enemy e : enemyCollection) {
      e.draw();
      e.move();

      // Check if enemy is within the player's radius
      // If it is, then player notifies all enemies in observer
      // if not then set the color back to default.
      if (e.getBoundingBox().collides(player.getPlayerRadius().getBoundingBox())) {
        player.notifyObserver(e); // if false, rotate then true
      } else {
        e.setIsInRadius(false);
        e.setColor(Enemy.TWO_FIFTY_FIVE, Enemy.TWO_FIFTY_FIVE, Enemy.TWO_FIFTY_FIVE);
      }

      // Checks if player collides with enemy
      // If it does, do a check to see if player is weaker or
      // stronger than enemy
      if (player.getBoundingBox().collides(e.getBoundingBox())) {
        player.playerCollidesEnemy(e, enemyCollection);
      }
    }
    player.draw();
  }

  /**
   * Restart the game.
   */
  public void restartGame() {
    System.out.println("GAME OVER!");
    PVector playerPos = new PVector(width / TWO, height / TWO);
    player.reset(playerPos);
    setup();
    draw();
  }

  /**
   * The size of the window.
   */
  public void settings() {
    size(WINDOW_WIDTH, WINDOW_HEIGHT);
  }

  /**
   * Key Press event movement for player.
   *
   * @param event for KeyEvent
   */
  @Override
  public void keyPressed(KeyEvent event) {
    super.keyPressed(event);
    if (player == null) {
      return;
    }
    player.move(event);
  }

  /**
   * Main function.
   *
   * @param passedArgs arguments from command line
   */
  public static void main(String[] passedArgs) {
    String[] appletArgs = new String[]{"window"};
    Window window = new Window();
    PApplet.runSketch(appletArgs, window);
  }
}