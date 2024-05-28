package org.bcit.midterm.answers;

public class Advanced_05_Radio_Receiver {
  private Advanced_05_Controller controller;
  private String command;
  public Advanced_05_Radio_Receiver() {
    command = "hello";
    controller = new Advanced_05_Controller(command, this);
  }

  public void sendSignal() {

  }

  public void receiveCommand() {

  }
}
