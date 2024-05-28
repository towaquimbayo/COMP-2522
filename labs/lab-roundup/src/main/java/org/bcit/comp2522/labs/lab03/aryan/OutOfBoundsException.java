package org.bcit.comp2522.labs.lab03.aryan;

public class OutOfBoundsException extends Exception {

  /**
   * Out od Bounds Exception for food charters.
   *
   * @param s
   */
  public OutOfBoundsException(String s) {
    super(s);
    System.out.println(s);
  }
}
