package org.bcit.comp2522.labs.lab04.Towa;

/**
 * OutOfBoundsException class that prints a message
 * alerting user for when any out of bounds exceptions.
 *
 * @author Towa Quimbayo
 * @version 1.0, September 29, 2022
 */
public class OutOfBoundsException extends Exception {

  /**
   * Constructor for OutOfBoundsException.
   */
  public OutOfBoundsException(String s) {
    System.out.println(s);
  }
}
