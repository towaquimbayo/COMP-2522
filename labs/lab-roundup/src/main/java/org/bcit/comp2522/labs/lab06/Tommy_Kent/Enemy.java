package org.bcit.comp2522.labs.lab06.Tommy_Kent;

import processing.core.PVector;

import java.awt.*;

/**
 * Enemy character that chases/avoids the player.
 *
 * @author Tommy_Nguyen
 * @author Kent_Concengco
 * @version 1.0
 */
public class Enemy extends Observer {

  private float distanceFromSource;

  public Enemy(float speed, float power, PVector pin, PVector dir, float din, Color cin, Window win) {
    super(speed, power, pin, dir, din, cin, win);
  }

  private void avoidPlayer(PVector pos) {
    PVector enemyPos = this.position.copy();
    this.direction = (pos.add(enemyPos.mult(-1f)).mult(-1f)).normalize();
  }

  private void chasePlayer(PVector pos) {
    PVector enemyPos = this.position.copy();
    this.direction = pos.add(enemyPos.mult(-1f)).normalize();
  }

  /**
   * Checks the distance between itself and an abstractCharacter.
   *
   * @param c the AbstractCharacter
   */
  public void updateDistanceMetric(AbstractCharacter c) {
    PVector sourcePos = c.getPosition().copy();
    this.distanceFromSource = sourcePos.dist(this.position) - (this.diameter / 2f)
            - (c.getDiameter() / 2f);
  }

  public float getDistanceFromSource() {
    return distanceFromSource;
  }

  @Override
  public void update(PVector p, float power) {
    if (power >= this.power) {
      avoidPlayer(p);
    } else {
      chasePlayer(p);
    }
  }

  @Override
  public void collideEffect(ICollidable c) {
    if (c instanceof Player && c.getPower() > this.power) {
      window.addDeadEnemyQueue(this);
    }
  }
}
