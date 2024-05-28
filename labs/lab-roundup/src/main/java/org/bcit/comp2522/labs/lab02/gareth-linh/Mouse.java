package org.comp2522;

import static processing.core.PApplet.*;
import static processing.core.PApplet.sin;

/**Ball
 * Class for the Mouse to be displayed onscreen.
 */
public class Mouse extends Collidable {

  /**
   * Mouse Constructor.
   *
   * @param xin x input
   * @param yin y input
   * @param din diamter input
   * @param idin id input
   * @param oin array input
   * @param scene n/a
   */
  Mouse(float xin, float yin, float din, int idin, Ball[] oin, BouncyBubbles scene) {
    super(xin, yin, din, idin, oin, scene);
  }

  /**
   * Collide function that provide collision
   * properties to the mouse object.
   */
  @Override
  void collide() {
    for (int i = 0; i < getOthers().length; i++) {
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

  @Override
  void move() {

  }
}
