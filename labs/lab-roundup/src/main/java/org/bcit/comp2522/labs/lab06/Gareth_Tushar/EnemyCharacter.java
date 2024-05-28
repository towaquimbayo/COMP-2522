package org.bcit.comp2522.labs.lab06.Gareth_Tushar;

import processing.core.PVector;

import java.awt.*;

/**
 * The Enemy's character on screen.
 *
 * @author Gareth Ng, Tushar Ghandi
 * @version 1.0 October 6, 2022
 */

public class EnemyCharacter extends AbstractCharacter implements IObserver {

  private EnemyPower power;
  int fillColour = 255;
  Color color;

 /**
  * Constructor for FoodCharacter.
  *
  * @param position centrepoint position
  * @param direction unit vector pointing in direction of movement
  * @param window current scene window
  */
  public EnemyCharacter(PVector position, PVector direction, Window window) {
    super(position, direction, window);
    power = new EnemyPower();
    color = new Color(0, 255, 255);
  }

  public EnemyPower getPower() {
    return this.power;
  }


 /**
  * Move the enemy character.
  * @param window
  */
  public void move(Window window) {
    this.position = this.position.add(this.direction.mult(speed));
    if (outOfBounds(window)) {
      rotate((float) Math.PI / 8f);
    }
  }

 /**
  * Draw the enemy character.
  * @param window
  */
 @Override
  public void draw(Window window) {
    window.fill(this.fillColour);
    window.ellipse(this.position.x, this.position.y, this.width, this.height);
  }

 /**
  * Stub
  * @param c Icollidable object
  */
 @Override
  public void collideBehaviour(IObject c) {

  }

 /**
  * Observer pattern function. Msg contains player's
  * coordinates.
  * @param msg
  */
 @Override
  public void update(Object msg) {
    PlayerCharacter player = (PlayerCharacter) msg;
    PVector playerPosition = player.getPosition();
    PVector follow = PVector.sub(playerPosition, this.getPosition());

    if (this.compareTo(player) == 1) {
      this.setDirection(follow.normalize().normalize());
    } else if (this.compareTo(player) == -1 && !this.outOfBounds(window)) {
      this.setDirection(follow.rotate((float) Math.PI).normalize());
    } else {
      if (touchWall(window)) {
        this.direction.rotate((float) Math.PI / 4f);
      }
      this.setPosition(
          this.getPosition().add(
          this.getDirection().mult(speed)
      )
      );
    }

  }

  @Override
  public int compareTo(AbstractCharacter o) {
    PlayerCharacter thisPlayer = (PlayerCharacter) o;
    int powerE = this.power.getCount();
    int powerP = thisPlayer.getPower().getCount();

    if (powerE > powerP) {
      return 1;
    } else if (powerE < powerP) {
      return -1;
    } else {
      return 0;
    }
  }
}
