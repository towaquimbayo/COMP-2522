package org.bcit.comp2522.labs.lab06.Tommy_Kent;

import processing.core.PApplet;
import processing.core.PVector;

import java.awt.*;
import java.util.ArrayList;
import java.util.Date;

/**
 * A simple game where a Player is controlled using a mouse.
 * The goal is to wipe out the Enemies while avoiding
 * enemies stronger than the Player.
 *
 * @author Tommy_Nguyen
 * @author Kent_Concengco
 * @version 1.0
 */
public class Window extends PApplet {
  private final EnemyCollection<Enemy> enemies = new EnemyCollection<>();
  private final EnemyCollection<Enemy> deadEnemies = new EnemyCollection<>();
  private final ArrayList<AbstractCharacter> characters = new ArrayList<>();
  private final ArrayList<ICollidable> collidables = new ArrayList<>();
  private final float[] powerUpInterval = {10000, 70000, 50000};
  private final int[] numEnemiesPerLevel = {7, 15, 20};
  private final int[][] enemyPowerPerLevel = {{1, 2}, {1, 5}, {1, 10}};
  private final float[] enemySpeedPerLevel = {1f, 3f, 5f};
  private final float enemyPowerGainPerLevel = 3f;
  private final int maxLevel = 2;
  private final float charDiameter = 10f;
  private int level = 0;
  private Player player;
  private Date lastPowerUptime;
  private boolean inGame = true;

  /**
   * Runs before applet starts.
   */
  public void setup() {
    // Init enemies
    Color enemyColor = new Color(255, 0, 0);
    for (int i = 0; i < numEnemiesPerLevel[level]; i++) {
      PVector enemyPos = new PVector(random(width), random(height));
      PVector enemyDir = new PVector(random(-1f, 1f), random(-1f, 1f)).normalize();
      Enemy enemy = new Enemy(1, random(enemyPowerPerLevel[level][0], enemyPowerPerLevel[level][1]),
              enemyPos, enemyDir, charDiameter, enemyColor, this);
      addEnemy(enemy);
    }
    lastPowerUptime = new Date();

    // Init player as Singleton
    float initPlayerPower = enemyPowerPerLevel[level][1];
    float initPlayerSpd = enemySpeedPerLevel[level] * 2f;
    PVector playerPos = new PVector(width / 2f, height / 2f);
    PVector playerDir = new PVector(1, 1).normalize();
    Color playerColor = new Color(0, 255, 255);
    Player player = Player.getInstance(initPlayerSpd, initPlayerPower, playerPos, playerDir,
        charDiameter, playerColor, this);
    addPlayer(player);
    player.setPower(initPlayerPower);
    player.setDiameter(charDiameter + initPlayerPower);
  }

  /**
   * Runs on each frame.
   */
  public void draw() {
    if (inGame) {
      background(0);
      fill(255, 255, 255);
      textSize(25);
      text(level != maxLevel ? "Level " + (level + 1) : "Final level", 10, 30);
      for (Enemy d : deadEnemies) {
        removeEnemy(d);
      }
      deadEnemies.clear();

      for (Enemy e : enemies) {
        e.updateDistanceMetric(player);
      }

      Date now = new Date();
      if (now.getTime() - lastPowerUptime.getTime() > powerUpInterval[level]) {
        lastPowerUptime = now;
        float tempPowerGain = enemyPowerGainPerLevel;
        for (Enemy e : enemies) {
          e.powerGain(tempPowerGain);
          tempPowerGain = tempPowerGain * 0.5f;
        }
      }

      player.scanEnemies();
      player.notifyObservers();

      for (AbstractCharacter c : characters) {
        c.move();
        c.draw();
      }
      player.redirect(new PVector(mouseX, mouseY));

      for (ICollidable c : collidables) {
        if (player.isCollided(c)) {
          c.collideEffect(player);
          player.collideEffect(c);
        }
      }

      if (enemies.size() == 0) {
        level++;
        if (level > maxLevel) {
          inGame = false;
          level = 0;
        }
        resetWindow();
      }

    } else {
      level = 0;
      resetWindow();
      inGame = true;
    }
  }

  /**
   * Adds a player to the window.
   *
   * @param player the player character
   */
  public void addPlayer(Player player) {
    this.player = player;
    characters.add(player);
    collidables.add(player);
  }

  /**
   * Adds an enemy to all of relevant arrays.
   *
   * @param enemy the enemy character
   */
  public void addEnemy(Enemy enemy) {
    enemies.add(enemy);
    characters.add(enemy);
    collidables.add(enemy);
  }

  /**
   * Removes an enemy from all of relevant arrays.
   *
   * @param enemy the enemy character
   */
  public void removeEnemy(Enemy enemy) {
    enemies.remove(enemy);
    characters.remove(enemy);
    collidables.remove(enemy);
  }

  /**
   * Adds a dead enemy to the queue to be removed in the
   * next call of the draw function.
   *
   * @param enemy a dead enemy
   */
  public void addDeadEnemyQueue(Enemy enemy) {
    deadEnemies.add(enemy);
  }

  /**
   * Sets the state of the game to reset.
   */
  public void resetGame() {
    inGame = false;
  }

  /**
   * Resets the game by clearing the collections
   * as well as calling the setup function again.
   */
  private void resetWindow() {
    enemies.clear();
    characters.clear();
    deadEnemies.clear();
    collidables.clear();
    player.getObservers().clear();
    setup();
  }

  public void settings() {
    size(500, 500);
  }

  /**
   * Returns the Window's EnemyCollection.
   *
   * @return enemies the collection of enemies
   */
  public EnemyCollection<Enemy> getEnemies() {
    return enemies;
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