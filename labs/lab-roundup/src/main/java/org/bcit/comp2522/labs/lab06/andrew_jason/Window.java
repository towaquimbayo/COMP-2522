package org.bcit.comp2522.labs.lab06.andrew_jason;

import org.bcit.comp2522.labs.lab06.Enemy;
import org.bcit.comp2522.labs.lab06.EnemyCollection;
import org.bcit.comp2522.labs.lab06.Player;
import processing.core.PApplet;
import processing.core.PVector;
import processing.event.KeyEvent;

import java.util.ArrayList;
import java.util.Collections;


/**
 * Flocking: An implementation of Craig Reynold's Boids program to
 * simulate the flocking behavior of birds. Each boid steers itself
 * based on rules of avoidance, alignment, and coherence.
 *
 * @author Daniel Shiffman, extended and refactored by paul_bucci
 * @version 1.0
 */
public class Window extends PApplet {

  org.bcit.comp2522.labs.lab06.Player player;

  private org.bcit.comp2522.labs.lab06.EnemyCollection<org.bcit.comp2522.labs.lab06.Enemy> enemies;

  int numEnemies = 20;
  /**
   * Runs before applet starts.
   */
  public void setup() {
    // Initialize player
    PVector playerDirection = new PVector(1, 1).normalize();
    PVector playerPosition = new PVector(width / 2f, height / 2f);
    // Singleton player
    org.bcit.comp2522.labs.lab06.Player player = Player.getInstance(playerPosition, playerDirection, this);
    this.player = player;

    enemies = new EnemyCollection<org.bcit.comp2522.labs.lab06.Enemy>();
    for (int i = 0; i < numEnemies; i++) {
      PVector enemyDirection = new PVector(random(-1f, 1f), random(-1f, 1f)).normalize();
      PVector enemyPosition = new PVector(random(width), random(height));
      org.bcit.comp2522.labs.lab06.Enemy e = new org.bcit.comp2522.labs.lab06.Enemy(enemyPosition, enemyDirection, this);
      this.enemies.add(e);
      player.addEnemy(e);
    }
  }

  /**
   * Called on a key press.
   *
   * @param event is the keyboard event
   */
  @Override
  public void keyPressed(KeyEvent event) {
    super.keyPressed(event);
    if (player == null) {
      return;
    }
    switch (event.getKeyCode()) {
      case RIGHT:
        player.rotate((float) Math.PI / 8f);
        break;
      case LEFT:
        player.rotate((float) -Math.PI / 8f);
        break;
      default:
        break;
    }
  }

  /**
   * Runs on each frame.
   */
  public void draw() {
    background(10);
    player.draw(this);
    player.move();
    player.notifyObservers();

    for (org.bcit.comp2522.labs.lab06.Enemy e : enemies) {
      e.move(this);
      e.draw(this);
      float distance = e.position.dist(player.getPosition());
      e.setDistance(distance);
      if (player.eat(e)) {
        if (player.compareTo(e) >= 0) {
          enemies.remove(e);
          player.removeEnemy(e);
        } else {
          setup();
        }
      }
//      System.out.println("Enemy distance: " + distance);
//      e.setPower(e.getPower() + distance);
//      player.setPower(player.getPower() + player.compareTo(e));
    }
    enemies.collectionSort();
    int index = 0;
    float increasePower = 0f;
    for (Enemy e : enemies) {
      if (index > numEnemies / 2) {
        increasePower = 2f;
      } else {
        increasePower = 1f;
      }
      e.setPower((e.getPower() + increasePower));
//      System.out.println("Enemy Power: " + e.getPower());
      index++;
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