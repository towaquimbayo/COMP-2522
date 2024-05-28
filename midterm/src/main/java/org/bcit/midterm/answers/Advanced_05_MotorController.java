package org.bcit.midterm.answers;

public class Advanced_05_MotorController {
  private Advanced_05_Controller controller;
  private int angle;
  private int direction;
  private float speed;
  private Advanced_05_MotorDriver motorDriver;

  public Advanced_05_MotorController(Advanced_05_Controller c, int a, int d, float s) {
    this.controller = c;
    setAngle(a);
    setSpeed(s);
    setDirection(d);
    motorDriver = new Advanced_05_MotorDriver(this.angle, this.direction, this.speed);
  }

  public void setAngle(int a) {
    this.angle = a;
  }

  public void setDirection(int d) {
    this.direction = d;
  }

  public void setSpeed(float s) {
    this.speed = s;
    this.controller.setSpeed(s);
  }
}
