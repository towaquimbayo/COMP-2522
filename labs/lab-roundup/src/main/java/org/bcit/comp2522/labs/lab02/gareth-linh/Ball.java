package org.comp2522;

import static processing.core.PApplet.atan2;
import static processing.core.PApplet.cos;
import static processing.core.PApplet.sin;
import static processing.core.PApplet.sqrt;

/**
 * Class for a Ball to be displayed onscreen.
 */
public class Ball extends Collidable {
  /**
   * Ball Constructor.
   *
   * @param xin x position input
   * @param yin y position input
   * @param din diameter input
   * @param idin id input
   * @param oin array input
   * @param scene n/a
   */
  Ball(float xin, float yin, float din, int idin, Ball[] oin, BouncyBubbles scene) {
    super(xin, yin, din, idin, oin, scene);
  }

  /**
   * Collide function that provide collision
   * properties to the ball object.
   */
  @Override
  public void collide() {
    for (int i = getId() + 1; i < getOthers().length; i++) {
      float dx = getOthers()[i].getX() - getX();
      float dy = getOthers()[i].getY() - getY();
      float distance = sqrt(dx * dx + dy * dy);
      float minDist = getOthers()[i].getDiameter() / 2 + getDiameter() / 2;
      // Collision behaviour
      if (distance < minDist) {
        float angle = atan2(dy, dx);
        float targetX = getX() + cos(angle) * minDist;
        float targetY = getY() + sin(angle) * minDist;
        float ax = (targetX - getOthers()[i].getX()) * getScene().spring;
        float ay = (targetY - getOthers()[i].getY()) * getScene().spring;
        setVx(getVx() - ax);
        setVy(getVy() - ay);

        getOthers()[i].setVx(getOthers()[i].getVx() + ax);
        getOthers()[i].setVy(getOthers()[i].getVy() + ay);
      }
    }

  }

  /**
   * Move function that allows balls to move
   * around onscreen.
   */
  @Override
  public void move() {
    setVy(getVy() + getScene().gravity);
    setX(getX() + getVx());
    setY(getY() + getVy());
    if (getX() + getDiameter() / 2 > getScene().width) {
      setX(getScene().width - getDiameter() / 2);
      setVx(getVx() * getScene().friction);
    } else if (getX() - getDiameter() / 2 < 0) {
      setX(getDiameter() / 2);
      setVx(getVx() * getScene().friction);
    }
    if (getY() + getDiameter() / 2 > getScene().height) {
      setY(getScene().height - getDiameter() / 2);
      setVy(getVy() * getScene().friction);
    } else if (getY() - getDiameter() / 2 < 0) {
      setY(getDiameter() / 2);
      setVy(getVy() * getScene().friction);
    }
  }
}
