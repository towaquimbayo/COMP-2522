/**
 * Represents an object which can collide with other objects.
 */
public abstract class Collidable {
  protected float xpos;
  protected float ypos;

  protected float vx;
  protected float vy;

  protected int id;

  protected float hitbox;

  protected Collidable[] others;

  BouncyBubbles scene;

  /**
   * Constructor initializes the object.
   *
   * @param xin x initial
   * @param yin y initial
   * @param idin id initial
   * @param hitbox hitbox
   * @param othersIn other collidables
   * @param scene scene on which object will be drawn
   *
   * @author Simrat Grewal & Aryan Jand
   * @version 1.0.0
   */
  public Collidable(float xin, float yin, int idin, float hitbox, Collidable[] othersIn, BouncyBubbles scene) {
    this.setXpos(xin);
    this.setYpos(yin);
    this.id = idin;
    this.setHitbox(hitbox);
    this.others = othersIn;
    this.scene = scene;
  }

  abstract void draw();

  abstract void move();

  abstract void collide();

  public float getXpos() {
    return xpos;
  }

  public void setXpos(float xpos) {
    this.xpos = xpos;
  }

  public float getYpos() {
    return ypos;
  }

  public void setYpos(float ypos) {
    this.ypos = ypos;
  }

  public float getVx() {
    return vx;
  }

  public void setVx(float vx) {
    this.vx = vx;
  }

  public float getVy() {
    return vy;
  }

  public void setVy(float vy) {
    this.vy = vy;
  }

  public float getHitbox() {
    return hitbox;
  }

  public void setHitbox(float hitbox) {
    this.hitbox = hitbox;
  }
}
