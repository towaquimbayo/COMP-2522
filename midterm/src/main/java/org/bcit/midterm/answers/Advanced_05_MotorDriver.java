package org.bcit.midterm.answers;

public class Advanced_05_MotorDriver {
  private float power;

  public Advanced_05_MotorDriver(int angle, int direction, float speed) {
    float p = angle * direction * speed;
    setPower(p);
  }

  public void setPower(float p) {
    this.power = p;
  }
}
