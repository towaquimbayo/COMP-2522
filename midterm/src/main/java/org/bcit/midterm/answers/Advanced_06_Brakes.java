package org.bcit.midterm.answers;

public class Advanced_06_Brakes {
  private String data;
  public Advanced_06_Brakes() {
    data = "";
  }

  public void decelerate(Advanced_06_Controller controller) {
    controller.setCurrentGasOutput(5);
    controller.setCurrentSpeed(75);
    data = controller.returnData();
    controller.data = this.returnData();
  }

  public String returnData() {
    return "here is some brakes";
  }
}
