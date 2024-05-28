package org.bcit.comp2522.labs.lab06.Stanley_Brandon;

import java.awt.*;
import java.lang.Math;
import processing.core.PVector;

/**
 * Abstract class for an observer object.
 */
public abstract class Observer implements Comparable<Observable>, IMoveable,
        IDrawable, ICollidable {

  protected PVector position;

  protected PVector direction;

  protected Window window;
  protected float speed = 1f;
  protected float radius = 10f;
  protected Color color;
  int level = 1;

  protected AbstractBehaviour behaviour;

  public int getLevel() {
    return level;
  }

  public void setLevel(int level) {
    this.level = level;
  }

  /**
   * Checks if an observer object is out of bounds.
   *
   * @param window is the processing window
   *
   * @return true if out of bounds, otherwise false
   */
  public boolean outOfBounds(Window window) {
    if ((this.position.x + (this.getRadius() / 2) >= window.width - 0
            || this.position.x - (this.getRadius() / 2) <= 0)
            || (this.position.y + (this.getRadius() / 2) >= window.height - 00
            || this.position.y - (this.getRadius() / 2) <= 0)) {
      return true;
    } else {
      return false;
    }
  }


  /**
   * Checks if the observer collides with another ICollidable object.
   *
   * @param c is an ICollidable object
   *
   * @return true if collision occurs, otherwise false
   */
  public boolean collided(ICollidable c) {
    boolean output = false;
    if (c instanceof Enemy enemyInstance) {
      PVector enPos = enemyInstance.getPosition();
      float  enRad = enemyInstance.getRadius();

      if ((Math.abs(this.position.x - enPos.x) <= (this.radius + enRad) / 2)
              && (Math.abs(this.position.y - enPos.y) <= (this.radius + enRad) / 2)
          && !this.position.equals(enPos)) {

        output = true;
      }
    }
    return output;
  }

  /**
   * Handles collision between an observer and an ICollidable.
   *
   * @param c is an ICollidable
   */
  public void collideBehaviour(ICollidable c) {
    if (c instanceof Enemy enemyTemp) {
      if (this.collided(enemyTemp)) {
        this.behaviour.bounce((float) Math.PI / 4);
        PVector newDirect = enemyTemp.getDirection().mult(-1f);
        enemyTemp.setDirection(newDirect);
      }
    }
  }

  /**
   * Updates an observer object.
   */
  public void update() {
    System.out.println("Behaviour updated for " + this);
  }

  public void setBehaviour(AbstractBehaviour behaviour) {
    this.behaviour = behaviour;
  }

  public AbstractBehaviour getBehaviour() {
    return this.behaviour;
  }


  @Override
  public abstract int compareTo(Observable o);
}
