package org.bcit.comp2522.labs.lab04.navdeep_Lab04;

/**
 * Thrown when a character is out of bounds.
 *
 * @author Navdeep Litt
 * @version 1.1
 */
public class OutOfBoundsException extends Exception{
  /**
   * Constructor for OutOfBoundsException.
   */
  public OutOfBoundsException(String s){
    super("Out of bounds!");
  }
}

// format string to comment what was out of bounds