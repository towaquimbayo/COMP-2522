package ca.bcit.comp2522.labs.lab02;

import static processing.core.PApplet.atan2;
import static processing.core.PApplet.cos;
import static processing.core.PApplet.sin;
import static processing.core.PApplet.sqrt;

/**
 * Mouse class that inherits Collidable values and methods. Defines the state
 * and behaviours of the mouse, or cursor.
 *
 * @author towaquimbayo, alexgibbison
 * @version 1.0
 */
public class Mouse extends Collidable {

  /**
   * Used to represent the number two.
   */
  public static final int TWO = 2;

  /**
   * Mouse Constructor that will pass the values into the Collidable constructor.
   *
   * @param xin for x position
   * @param yin for y position
   * @param diam for diameter
   * @param idIn for id
   * @param oin for array of Collidable objects
   * @param scene for BouncyBubbles scene
   */
  public Mouse(float xin, float yin, float diam, int idIn, Collidable[] oin, BouncyBubbles scene) {
    super(xin, yin, diam, idIn, oin, scene);
  }

  /**
   * Collide method will calculate collisions between all balls.
   * If statement to check if the object is an instance of the Ball,
   * and if it is true then only bounce the ball and not the mouse object.
   */
  public final void collide() {
    for (int i = id + 1; i < others.length; i++) {
      float dx = others[i].xpos - xpos;
      float dy = others[i].ypos - ypos;
      float distance = sqrt(dx * dx + dy * dy);
      float minDist = others[i].diameter / TWO + diameter / TWO;
      if (distance < minDist) {
        float angle = atan2(dy, dx);
        float targetX = xpos + cos(angle) * minDist;
        float targetY = ypos + sin(angle) * minDist;
        float ax = (targetX - others[i].xpos) * scene.spring;
        float ay = (targetY - others[i].ypos) * scene.spring;
        vx -= ax;
        vy -= ay;
        if (others[i] instanceof Ball) {
          others[i].setVx(others[i].getVx() + ax);
          others[i].setVy(others[i].getVy() + ay);
        }
      }
    }
  }

  /**
   * Move class that will the set the
   * x and y position every frame
   * accordingly to the cursor position.
   */
  public final void move() {
    setX(scene.mouseX);
    setY(scene.mouseY);
  }
}
