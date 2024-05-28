import static processing.core.PApplet.atan2;
import static processing.core.PApplet.cos;
import static processing.core.PApplet.sin;
import static processing.core.PApplet.sqrt;

/**
 * Class for a Ball to be displayed onscreen.
 *
 * @author Simrat Grewal & Aryan Jand
 * @version 1.0.0
 */
public class Ball extends Collidable {

  Ball(float xin, float yin, int idin, float din, Collidable[] oin, BouncyBubbles scene) {
    super(xin, yin, idin, din, oin, scene);
  }

  @Override
  void draw() {
    scene.noStroke();
    scene.fill(255, 204);
    scene.ellipse(getXpos(), getYpos(), getHitbox(), getHitbox());
  }


  /**
   * Calculate new positions for this ball.
   */
  @Override
  public void move() {
    setVy(getVy() + scene.gravity);
    xpos += getVx();
    ypos += getVy();
    if (xpos + getHitbox() / 2 > scene.width) {
      xpos = scene.width - getHitbox() / 2;
      setVx(getVx() * scene.friction);
    } else if (xpos - getHitbox() / 2 < 0) {
      xpos = getHitbox() / 2;
      setVx(getVx() * scene.friction);
    }
    if (ypos + getHitbox() / 2 > scene.height) {
      ypos = scene.height - getHitbox() / 2;
      setVy(getVy() * scene.friction);
    } else if (ypos - getHitbox() / 2 < 0) {
      ypos = getHitbox() / 2;
      setVy(getVy() * scene.friction);
    }
  }

  @Override
  public void collide() {
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
        setVx(getVx() - ax);
        setVy(getVy() - ay);

        // Collide with everything but a mouse
        if (others[i].getClass().getSimpleName() != "Mouse") {
          others[i].setVx(others[i].getVx() + ax);
          others[i].setVy(others[i].getVy() + ay);
        }
      }
    }
  }

  /**
   * Checks if this ball is equal to another ball.
   *
   * @param x Other ball
   * @return Whether the balls are equal
   */
  public boolean equals(Ball x) {
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
    return String.format("Ball with x: %f y: %f, diameter: %f", getXpos(),
            getYpos(), getHitbox());
  }
}
