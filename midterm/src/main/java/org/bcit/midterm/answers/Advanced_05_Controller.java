package org.bcit.midterm.answers;

public class Advanced_05_Controller {
  private String command;
  private Advanced_05_Radio_Receiver receiver;
  private Advanced_05_MotorController motorController;
  private float speed;
  public Advanced_05_Controller(String s, Advanced_05_Radio_Receiver r) {
    receiveCommand(s);
    poll(r);
    this.speed = 0f;
    motorController = new Advanced_05_MotorController(this, 30, 1, 100.5f);
  }

  public void receiveCommand(String s) {
    this.command = s;
  }

  public void poll(Advanced_05_Radio_Receiver r) {
    receiver = r;
  }

  public void setSpeed(float s) {
    this.speed = s;
  }
}
