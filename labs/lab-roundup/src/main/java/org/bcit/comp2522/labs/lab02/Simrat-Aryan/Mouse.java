import static processing.core.PApplet.atan2;
import static processing.core.PApplet.cos;
import static processing.core.PApplet.sin;
import static processing.core.PApplet.sqrt;

/**
 * A collidable which follows the cursor and only collides with other balls.
 *
 * @author Simrat Grewal & Aryan Jand
 * @version 1.0.0
 */
public class Mouse extends Collidable {
  public Mouse(float xin, float yin, int idin, float hitbox,
               Collidable[] othersIn, BouncyBubbles scene) {
    super(xin, yin, idin, hitbox, othersIn, scene);
  }

  @Override
  public void draw() {
    scene.noStroke();
    scene.fill(0xffff0000, 204);
    scene.ellipse(getXpos(), getYpos(), getHitbox(), getHitbox());
  }

  @Override
  public void move() {
    setXpos(scene.mouseX);
    setYpos(scene.mouseY);
  }

  @Override
  void collide() {
    for (int i = id + 1; i < others.length; i++) {
      float dx = others[i].xpos - xpos;
      float dy = others[i].ypos - ypos;
      float distance = sqrt(dx * dx + dy * dy);
      float minDist = others[i].getHitbox() / 2 + getHitbox() / 2;
      if (distance < minDist) {
        float angle = atan2(dy, dx);
        float targetX = xpos + cos(angle) * minDist;
        float targetY = ypos + sin(angle) * minDist;
        float ax = (targetX - others[i].xpos) * scene.spring;
        float ay = (targetY - others[i].ypos) * scene.spring;

        // Only collide with other balls
        if (others[i].getClass().getSimpleName() == "Ball") {
          others[i].setVx(others[i].getVx() + ax);
          others[i].setVy(others[i].getVy() + ay);
        }
      }
    }
  }

  /**
   * Checks if this mouse is equal to another mouse.
   *
   * @param x Other Mouse
   * @return Whether the mice are equal
   */
  public boolean equals(Mouse x) {
    return (x.getXpos() == this.getXpos()
            && x.getYpos() == this.getYpos()
            && x.getHitbox() == this.getHitbox());
  }

  /**
   * Gets the hashcode value of the object.
   *
   * @return hashcode of the object.
   */
  @Override
  public int hashCode() {
    return (int) (this.getXpos() + this.getYpos() + this.getHitbox());
  }

  /**
   * Returns the string representation of the object.
   *
   * @return String representation of the object.
   */
  @Override
  public String toString() {
    return String.format("Mouse with x: %f y: %f, diameter: %f", getXpos(),
            getYpos(), getHitbox());
  }
}
