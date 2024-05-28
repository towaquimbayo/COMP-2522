package org.bcit.comp2522.labs.lab06;

import java.util.ArrayList;
import processing.core.PApplet;
import processing.core.PVector;

/**
 * Flocking: An implementation of Craig Reynold's Boids program to
 * simulate the flocking behavior of birds. Each boid steers itself
 * based on rules of avoidance, alignment, and coherence.
 *
 * @author Daniel Shiffman, extended and refactored by paul_bucci
 * @version 1.0
 */
public class Window extends PApplet {

  protected Player player;

  //The player size when starting the game.
  private final float playerSize = 20f;

  //The smallest size for player when restarting the game.
  private final float playerMinimumSize = 10f;

  //Enemies removal array
  private ArrayList<AbstractObserver> removeEnemy = new ArrayList<>();

  //Enemies collection
  private EnemyCollection<Enemy> enemyCollection = new EnemyCollection<>();

  /**
   * Runs before applet starts.
   */
  public void setup() {
    //Initialize player
    PVector playerPosition = new PVector(mouseX, mouseY);
    player = Player.getInstance(playerPosition, playerSize, this);

    //Initialize enemies
    //Create something smaller than the player character
    for (int i = 0; i < 20; i++) {
      float enemySize = (float) ((Math.random() * 11f) + 10f);
      PVector enemyPosition = new PVector(random(width), random(height));
      PVector enemyDirection = new PVector(random(-1f, 1f), random(-1f, 1f)).normalize();
      Enemy enemy = new Enemy(enemyPosition, enemyDirection, enemySize, this);
      enemyCollection.add(enemy);
    }
    //Create 2 more enemies that are bigger than the player
    for (int i = 0; i < 2; i++) {
      float enemySize = (float) ((Math.random() * 11f) + 25f);
      PVector enemyPosition = new PVector(random(width), random(height));
      PVector enemyDirection = new PVector(random(-1f, 1f), random(-1f, 1f)).normalize();
      Enemy enemy = new Enemy(enemyPosition, enemyDirection, enemySize, this);
      enemyCollection.add(enemy);
    }
  }

  public void eatEnemy(AbstractObserver a) {
    this.removeEnemy.add(a);
  }

  /**
   * Restart the game when the play gets too small.
   */
  public void restart() {
    enemyCollection = new EnemyCollection<>();
    removeEnemy = new ArrayList<>();
    this.player.setSize(playerSize);
    setup();
  }

  /**
   * Runs on each frame.
   */
  public void draw() {
    background(0);

    //Draw player
    player.draw(this);
    player.move(this);

    //Draw enemies
    for (Enemy a : enemyCollection) {
      a.draw(this);
    }
    for (Enemy a : enemyCollection) {
      a.move(this);
    }

    //Collide behaviour among all enemies
    for (Enemy a : enemyCollection) {
      for (Enemy b : enemyCollection) {
        if (a.collided(b)) {
          a.collideBehaviour();
        }
      }
    }

    //Collide behaviour between player and enemies
    for (Enemy a : enemyCollection) {
      if (player.collided(a)) {
        player.collideBehaviour(a);
      }
    }

    //Remove enemy
    for (AbstractObserver a : removeEnemy) {
      enemyCollection.remove((Enemy) a);
    }
    removeEnemy.clear();

    //Enemies sizes change based on the distance
    for (Enemy a : enemyCollection) {
      a.distanceBehaviour();
    }

    //Restart the game if play's size gets too small
    if (player.getSize() < playerMinimumSize) {
      restart();
    }
  }

  public void settings() {
    size(640, 360);
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