package org.bcit.midterm.answers;

public class Advanced_06_Controller {
  protected String data;
  private Advanced_06_Engine engine;
  private Advanced_06_Brakes brakes;
  private int speed;
  private int gasOutput;
  public Advanced_06_Controller() {
    engine = new Advanced_06_Engine();
    brakes = new Advanced_06_Brakes();
    data = "";
    speed = 0;
  }

  public void run() {
    engine.accelerate(this);
    engine.decelerate(this);
    engine.decelerate(this);
    brakes.decelerate(this);
  }

  public void setCurrentGasOutput(int g){
    this.gasOutput = g;
  }

  public void setCurrentSpeed(int s) {
    this.speed = s;
  }

  public String returnData() {
    return "some data";
  }
}
