package org.bcit.comp2522.labs.lab06.Gareth_Tushar;

import processing.core.PVector;

import java.awt.*;
import java.util.ArrayList;

/**
 * The Player's main character on screen. It also serves as an example of
 * a Singleton design pattern.
 *
 * @author Gareth Ng, Tushar Ghandi
 * @version 1.0
 */
public class PlayerCharacter extends AbstractCharacter implements IObservable {

  private static PlayerCharacter playerChar;
  Color color;
  private PlayerPower power;
  private int radius;
  ArrayList<IObject> obj; //An ArrayList for all the enemy.

  /**
   * Constructor
   * @param position
   * @param direction
   * @param window
   */
  public PlayerCharacter(PVector position, PVector direction, Window window) {
    super(position, direction, window);
    this.color = new Color(0, 200, 0);
    this.obj = new ArrayList<>();
    power = new PlayerPower();
    radius = 10;
  }

  /**
   * Getter.
   * @return
   */
  public PlayerPower getPower() {
    return this.power;
  }

  public static PlayerCharacter getInstance(PVector position, PVector direction, Window window) {
    if (playerChar == null) {
      playerChar = new PlayerCharacter(position, direction, window);
    }
    return playerChar;
  }

  /**
   * Add enemy to IObject arraylist.
   * @param e
   */
  public void addEnemy(EnemyCharacter e) {
    obj.add(e);
  }

  /**
   * Remove enemy from IObject arraylist.
   * @param e
   */
  public void removeEnemy(EnemyCharacter e) {
    obj.remove(e);
  }

  /**
   * This function check if enemy character is in range,
   * if in range, return true or false.
   * @param enemy
   * @return
   */
  public boolean inRange(EnemyCharacter enemy) {
    if (enemy.getDistance() < 200) {
      return true;
    } else {
      return false;
    }

  }

  /**
   * This function moves the player.
   * @param window
   */
  public void move(Window window) {
    this.position = this.position.add(this.direction.mult(speed));
    if (outOfBounds(window)) {
      rotate((float) Math.PI / 8f);
    }
  }

  /**
   * This function draws the player.
   * @param window
   */
  @Override
  public void draw(Window window) {
    window.fill(color.getRed(), color.getGreen(), color.getBlue());
    window.ellipse(this.position.x, this.position.y, this.width, this.height);

  }

  /**
   * Here are the function for observer pattern.
   * @param observer
   */
  @Override
  public void registerObserver(IObserver observer) {
    EnemyCharacter newEnemy = (EnemyCharacter) observer;
    addEnemy(newEnemy);
    notifyObservers();
  }

  @Override
  public void unregisterObserver(IObserver observer) {
    EnemyCharacter oldEnemy = (EnemyCharacter) observer;
    removeEnemy(oldEnemy);
  }

  @Override
  public void notifyObservers() {
    for (IObject o : obj) {
      if (o instanceof EnemyCharacter e) {
        e.update(this);
      }

    }
  }

  /**
   * Stub
   * @param o the object to be compared.
   * @return
   */
  @Override
  public int compareTo(AbstractCharacter o) {
    return 0;
  }

  /**
   * Stub
   * @param c
   * @return
   */
  @Override
  public boolean collided(IObject c) {
    return false;
  }

 /**
  * PlayerCharacter collide behavior that removes
  * FoodCharacter upon collision.
  *
  * @param c Icollidable object
  */
 @Override
 public void collideBehaviour(IObject c) {
   return;
 }

}


