package org.bcit.comp2522.labs.lab06.Stanley_Brandon;

import processing.core.PVector;

/**
 * Represents fleeing behaviour for enemies. This behaviour will be active when the enemy's level
 * is less than the player's level, and the enemh is within the player's sphere of influence.
 */
public class FleeingBehaviour extends AbstractBehaviour {

  Enemy enemy;

  public FleeingBehaviour(Enemy enemy) {
    this.enemy = enemy;
  }

  @Override
  public void move(Window window) {
    enemy.setDirection(seek());
    enemy.setPosition(enemy.getPosition().add(enemy.getDirection().mult(enemy.getSpeed())));

    if (enemy.outOfBounds(window)) {
      bounce((float) Math.PI / 8f);

    }
  }

  @Override
  public void bounce(float f) {
    enemy.getDirection().rotate(f);
  }

  /**
   * Method takes the player's position and calculates the new direction for an enemy chasing the
   * player.
   *
   * @return desired P vector pointing towards the player.
   */
  public PVector seek() {

    PVector playerPos = Player.getInstance(new PVector(0, 0),
            new PVector(0, 0), enemy.window).getPosition();
    // A vector pointing from the position to the target
    PVector desired = PVector.sub(playerPos, enemy.getPosition());
    // Scale to maximum speed
    desired.normalize();
    desired.mult(-1f);

    return desired;
  }
}
