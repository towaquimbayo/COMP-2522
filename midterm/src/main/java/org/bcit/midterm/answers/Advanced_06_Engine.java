package org.bcit.midterm.answers;

public class Advanced_06_Engine {

  private String data;
  public Advanced_06_Engine() {
    data = "";
  }

  public void accelerate(Advanced_06_Controller controller) {
    controller.setCurrentGasOutput(5);
    data = controller.returnData();
    controller.data = this.returnData();
  }

  public void decelerate(Advanced_06_Controller controller) {
    controller.setCurrentGasOutput(5);
    data = controller.returnData();
    controller.data = this.returnData();
  }

  public String returnData() {
    return "here is engine data";
  }
}
