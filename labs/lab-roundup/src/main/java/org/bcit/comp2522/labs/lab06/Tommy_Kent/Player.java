package org.bcit.comp2522.labs.lab06.Tommy_Kent;

import processing.core.PVector;

import java.awt.*;
import java.util.ArrayList;

/**
 * Player character class that fights other enemies.
 *
 * @author Tommy_Nguyen
 * @author Kent_Concengco
 * @version 1.0
 */
public class Player extends Observable {
  private final float broadcastRadius = 50f;
  private final ArrayList<Observer> observers;
  private static Player player;

  public ArrayList<Observer> getObservers() {
    return observers;
  }

  private Player(float speed, float power, PVector pin, PVector dir, float din, Color cin,
                 Window win) {
    super(speed, power, pin, dir, din, cin, win);
    this.observers = new ArrayList<>();
  }

  /**
   * Gets the instance of the Player.
   *
   * @param speed the speed of the Player
   * @param power the power of the Player
   * @param playerPos the position of the Player
   * @param playerDir the direction of the Player
   * @param charDiameter the character diameter of the Player
   * @param playerColor the color of the Player
   * @param window the window which the Player resides in
   * @return the player
   */
  public static Player getInstance(float speed, float power, PVector playerPos, PVector playerDir,
                                   float charDiameter, Color playerColor, Window window) {
    if (player == null) {
      player = new Player(speed, power, playerPos, playerDir, charDiameter, playerColor, window);
    }

    return player;
  }

  /**
   * Redirects the player to the specified position.
   *
   * @param pos as target position
   */
  public void redirect(PVector pos) {
    PVector playerPos = this.position.copy();
    this.direction = pos.add(playerPos.mult(-1f)).normalize();
  }

  /**
   * Registers if there are any enemies within the broadcast radius of the Player,
   * and unregisters enemies that leave the broadcast radius.
   */
  public void scanEnemies() {
    for (Enemy e : window.getEnemies()) {
      if (this.position.dist(e.getPosition()) <= broadcastRadius + this.diameter / 2f) {
        registerObserver(e);
      } else {
        unregisterObserver(e);
      }
    }
  }

  @Override
  public void collideEffect(ICollidable c) {
    if (this.power >= c.getPower()) {
      powerGain(c.getPower() / 2f);
    } else {
      window.resetGame();
    }
  }

  @Override
  public void registerObserver(Observer o) {
    observers.add(o);
  }

  @Override
  public void unregisterObserver(Observer o) {
    observers.remove(o);
  }

  @Override
  public void notifyObservers() {
    for (Observer o : observers) {
      o.update(this.position.copy(), this.power);
    }
  }
}
