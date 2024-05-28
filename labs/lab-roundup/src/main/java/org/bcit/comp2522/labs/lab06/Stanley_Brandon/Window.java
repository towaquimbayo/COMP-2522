package org.bcit.comp2522.labs.lab06.Stanley_Brandon;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import processing.core.PApplet;
import processing.core.PVector;
import processing.event.KeyEvent;


/**
 * Class containing the methods required to setup the processing window.
 */
public class Window extends PApplet {

  Player player;

  int maxEnemies = 10;

  int numEnemies = 0;

  private List<Enemy> list = new ArrayList<>();

  private ArrayList<ICollidable> collidables = new ArrayList<ICollidable>();

  private ArrayList<Enemy> addEnemyQueue = new ArrayList<>();

  private ArrayList<Enemy> removeEnemyQueue = new ArrayList<Enemy>();

  private ArrayList<IDrawable> drawables = new ArrayList<>();

  EnemyCollection enemies;

  Iterator i;

  /**
   * Runs before applet starts.
   */
  public void setup() {
    PVector playerPosition = new PVector(this.width / 2f, this.height / 2f);
    PVector playerDirection = new PVector(1, 1).normalize();
    Random r = new Random();

    int enStartWidth = 10;
    player = Player.getInstance(playerPosition, playerDirection, this);
    collidables.add(player);
    drawables.add(player);

    for (int i = 0; i < maxEnemies; i++) {

      // Generates random starting values for each enemy
      PVector position = new PVector(r.nextInt(this.width - enStartWidth) + (enStartWidth / 2),
              r.nextInt(this.height - enStartWidth) + (enStartWidth / 2));
      float randomNum = -1 + r.nextFloat() * (1 - (-1));
      PVector direction = new PVector(randomNum, randomNum).normalize();
      Enemy e = new Enemy(position, direction, this);
      list.add(e);
      addEnemy(e);
    }

    enemies = new EnemyCollection(list);
    i = enemies.iterator();

    while (i.hasNext()) {
      Enemy e = (Enemy) i.next();
      e.grow();
    }
    enemies.resetIterator();
  }

  /**
   * Checks if a key has been pressed and moves the player character
   * in the direction of the key press.
   *
   * @param event key pressed from user
   */
  public void keyPressed(KeyEvent event) {
    super.keyPressed(event);
    if (player == null) {
      return;
    }
    switch (event.getKeyCode()) {
      case RIGHT:
        if (player.getPosition().x + player.getRadius() / 2  < this.width) {
          player.setDirection(new PVector(1, 0));
          player.move(this);
        }
        break;
      case LEFT:
        if (player.getPosition().x - player.getRadius() / 2 > 0) {
          player.setDirection(new PVector(-1, 0));
          player.move(this);
        }
        break;
      case UP:
        if (player.getPosition().y - player.getRadius() / 2 > 0) {
          player.setDirection(new PVector(0, -1));
          player.move(this);
        }
        break;
      case DOWN:
        if (player.getPosition().y + player.getRadius() / 2 < this.height) {
          player.setDirection(new PVector(0, 1));
          player.move(this);
        }
        break;
      default:
        break;
    }
  }

  /**
   * Runs on each frame.
   */
  public void draw() {
    background(20);

    for (Enemy e : addEnemyQueue) {
      this.addThreadSafeEnemy(e);
    }
    addEnemyQueue.clear();

    for (Enemy e : removeEnemyQueue) {
      removeThreadSafeEnemy(e);
    }
    removeEnemyQueue.clear();

    for (ICollidable a : collidables) {
      for (ICollidable b : collidables) {
        if (a.collided(b)) {
          a.collideBehaviour(b);
        }
      }
    }

    for (Enemy e : list) {
      e.run(this);
    }

    for (IDrawable d : drawables) {
      d.draw(this);
    }


  }

  /**
   * Adds an enemy to a queue for removal from the processing window.
   *
   * @param enemy to be removed
   *
   */
  public void removeEnemy(Enemy enemy) {
    removeEnemyQueue.add(enemy);
  }

  /**
   * Safely removes an enemy object from all associated containers.
   *
   * @param enemy to be removed
   */
  public void removeThreadSafeEnemy(Enemy enemy) {

    this.collidables.remove(enemy);
    this.list.remove(enemy);
    this.enemies.remove(enemy);
    this.drawables.remove(enemy);
  }

  /**
   * Determines the size of the processing window.
   */
  public void settings() {
    size(640, 360);
  }

  /**
   * Places an enemy object into a queue of enemies to be added to the window.
   *
   * @param enemy to be added
   */
  public void addEnemy(Enemy enemy) {
    addEnemyQueue.add(enemy);
  }

  /**
   * Safely adds an enemy object to the window if number of enemies
   * is less than maxEnemies.
   *
   * @param enemy to be added
   */
  private void addThreadSafeEnemy(Enemy enemy) {
    if (this.numEnemies < maxEnemies) {
      this.numEnemies += 1;
      this.drawables.add(enemy);
      this.collidables.add(enemy);
      this.enemies.add(enemy);
    }
  }

  /**
   * Resets the game to the initial starting conditions.
   */
  public void resetGame() {
    drawables.remove(player);
    PVector playerPosition = new PVector(this.width / 2f, this.height / 2f);
    PVector playerDirection = new PVector(1, 1).normalize();
    player.setDirection(playerDirection);
    player.setPosition(playerPosition);
    player.setRadius(20f);
    player.setLevel(1);
    numEnemies = 0;

    drawables.add(player);

    Random r = new Random();
    int enStartWidth = 10;

    // Removes from EnemyCollection
    for (Enemy e : enemies) {
      removeEnemy(e);
    }

    for (int i = 0; i < maxEnemies; i++) {
      PVector position = new PVector(r.nextInt(this.width - enStartWidth) + (enStartWidth / 2),
              r.nextInt(this.height - enStartWidth) + (enStartWidth / 2));
      float randomNum = -1 + r.nextFloat() * (1 - (-1));
      PVector direction = new PVector(randomNum, randomNum).normalize();
      Enemy e = new Enemy(position, direction, this);

      addEnemy(e);
      list.add(e);
    }

    enemies = new EnemyCollection(list);
    i = enemies.iterator();

    while (i.hasNext()) {
      Enemy e = (Enemy) i.next();
      e.grow();
    }
    enemies.resetIterator();

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