package org.bcit.comp2522.labs.lab06.Stanley_Brandon;

import java.awt.*;
import processing.core.PVector;


/**
 * Abstract class for an Observable object.
 */
public abstract class Observable implements IMoveable, ICollidable,
        IDrawable, Comparable<Observer> {
  protected PVector position;

  protected PVector direction;

  protected Window window;

  // starting speed
  protected float speed = 8.0f;

  protected float radius = 25f;

  Color color;
  int level = 1;


  public PVector getPosition() {
    return this.position;
  }

  public PVector getDirection() {
    return this.direction;
  }

  public float getSpeed() {
    return this.speed;
  }

  public float getRadius() {
    return this.radius;
  }

  public void setPosition(PVector position) {
    this.position = position;
  }


  public void setDirection(PVector direction) {
    this.direction = direction;
  }

  public void setSpeed(float speed) {
    this.speed = speed;
  }

  public void setRadius(float radius) {
    this.radius = radius;
  }

  public int getLevel() {
    return level;
  }

  public void setLevel(int level) {
    this.level = level;
  }

  /**
   * Checks if the observable object has collided.
   *
   * @param c ICollidable that may have collided with this
   *
   * @return true if collision occurs, otherwise false
   */
  @Override
  public boolean collided(ICollidable c) {
    boolean output = false;
    if (c instanceof Enemy enemyInstance) {
      PVector enPos = enemyInstance.getPosition();
      float  enRad = enemyInstance.getRadius();

      if ((Math.abs(this.position.x - enPos.x) <= (this.radius + enRad) / 2)
              && (Math.abs(this.position.y - enPos.y) <= (this.radius + enRad) / 2)) {

        output = true;
      }
    }
    return output;
  }

  /**
   * Registers an observer to a behaviour.
   *
   * @param o observer object
   * @param behaviour to be registered to
   */
  public void registerObserver(Observer o, AbstractBehaviour behaviour) {
    o.setBehaviour(behaviour);
    notifyObservers(o);
  }

  /**
   * Unregisters an observer, returning to its default behaviour.
   *
   * @param o observer object
   */
  public void unregisterObserver(Observer o) {
    DefaultBehaviour d = new DefaultBehaviour((Enemy) o);
    o.setBehaviour(d);
    notifyObservers(o);

  }

  /**
   * Notifies observer that its behaviour as been changed.
   *
   * @param o observer to be notified
   */
  public void notifyObservers(Observer o) {
    o.update();

  }


  public abstract int compareTo(Observer o);
}
