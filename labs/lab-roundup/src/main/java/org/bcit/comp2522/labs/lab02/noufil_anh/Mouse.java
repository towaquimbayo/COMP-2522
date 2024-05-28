package org.bcit.comp2522.labs.lab02.noufil_anh;

import static processing.core.PApplet.*;
import static processing.core.PApplet.sin;

public class Mouse extends Collidable{

  public Mouse(float xin, float yin, float din, int idin, Ball[] oin, BouncyBubbles scene) {
    super(xin,yin,din,idin,oin,scene);
  }

  @Override public void collide() {
    for (int i = id + 1; i < others.length; i++) {
      float dx = others[i].xpos - xpos;
      float dy = others[i].ypos - ypos;
      float distance = sqrt(dx * dx + dy * dy);
      float minDist = others[i].diameter / 2 + diameter / 2;
      if (distance < minDist) {
        float angle = atan2(dy, dx);
        float targetX = xpos + cos(angle) * minDist;
        float targetY = ypos + sin(angle) * minDist;
        float ax = (targetX - others[i].xpos) * scene.spring;
        float ay = (targetY - others[i].ypos) * scene.spring;
        others[i].setVx(others[i].getVx() + ax);
        others[i].setVy(others[i].getVy() + ay);
      }
    }
  }

  @Override public void move()
  {
    xpos = this.scene.mouseX;
    ypos = this.scene.mouseY;
  }
}
