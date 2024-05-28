package org.bcit.comp2522.labs.lab06.Stanley_Brandon;

import processing.core.PVector;

/**
 * Represents the normal enemy movement when an enemy is not within the player's sphere of
 * influence and upon initialization of an enemy.
 */
public class DefaultBehaviour extends AbstractBehaviour {

  Enemy enemy;

  /**
   * Constructor for DefaultBehaviour, creates a DefaultBehaviour object.
   *
   * @param enemy the enemy with this behaviour.
   */
  public DefaultBehaviour(Enemy enemy) {
    this.enemy = enemy;
  }

  @Override
  public void move(Window window) {

    enemy.setPosition(enemy.getPosition().add(enemy.getDirection().mult(enemy.getSpeed())));

    if (enemy.outOfBounds(window)) {
      bounce((float) Math.PI / 10f);
    }
  }

  @Override
  public void bounce(float f) {
    enemy.getDirection().rotate(f);
  }

}
