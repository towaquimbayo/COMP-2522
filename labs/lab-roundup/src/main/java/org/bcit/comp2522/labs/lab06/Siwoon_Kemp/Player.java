package org.bcit.comp2522.labs.lab06;

import java.awt.Color;
import java.util.ArrayList;
import processing.core.PVector;

/**
 * This class defines the player singleton.
 */
public class Player extends AbstractObservable implements Comparable {
  private static Player player;
  private PVector position;
  private float size;
  private Window window;
  private Color color = Color.white;


  private Player(PVector position, float size, Window window) {
    this.position = position;
    this.size = size;
    this.window = window;
    this.observers = new ArrayList<>();
  }

  /**
   * Get an instance for the singleton.
   *
   * @param position as PVector.
   *
   * @param size as float.
   *
   * @param window as Window.
   *
   * @return a player instance as singleton.
   */
  public static Player getInstance(PVector position, float size, Window window) {
    if (player == null) {
      player = new Player(position, size, window);
    }
    return player;
  }

  public float getSize() {
    return this.size;
  }

  public void setSize(float size) {
    this.size = size;
  }

  public PVector getPosition() {
    return this.position;
  }

  void move(Window window) {
    this.window = window;
    this.position.x = window.mouseX;
    this.position.y = window.mouseY;
  }

  @Override
  public int compareTo(Object o) {
    return (int) (this.size - ((Enemy) o).getSize());
  }

  /**
   * Check if the player collides with other enemy.
   *
   * @param other as Enemy object.
   *
   * @return boolean.
   */
  public boolean collided(Enemy other) {
    if (this.equals(other)) {
      return false;
    } else if (this.position.dist(other.getPosition())
            <= ((this.size / 2) + (other.getSize() / 2))) {
      return true;
    }
    return false;
  }

  /**
   * The behaviour of the player character after touching the enemies.
   *
   * @param a as other enemies.
   */
  public void collideBehaviour(AbstractObserver a) {
    //Eat enemies
    if (compareTo(a) >= 0) {
      window.eatEnemy(a);
      //Increase in size
      this.size *= 1.07;
    } else if (compareTo(a) < 0) {
      //Decrease in size
      this.size *= 0.995;
    }
  }

  void draw(Window window) {
    window.fill(this.color.getRGB());
    window.ellipse(this.position.x, this.position.y, this.size, this.size);
    notifyObservers();
  }

  @Override
  public void registerObserver(AbstractObserver observer) {
    this.observers.add(observer);
  }

  @Override
  public void unregisterObserver(AbstractObserver observer) {
    this.observers.remove(observer);
    observer.update();
  }

  @Override
  public void notifyObservers() {
    for (AbstractObserver a : observers) {
      a.update(this.position, this.size);
    }
  }
}
