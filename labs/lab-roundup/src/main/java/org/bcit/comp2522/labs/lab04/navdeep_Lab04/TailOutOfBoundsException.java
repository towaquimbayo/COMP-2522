package org.bcit.comp2522.labs.lab04.navdeep_Lab04;

/**
 * Thrown when a characters tail is out of bounds.
 *
 * @author Navdeep Litt
 * @version 1.1
 */
public class TailOutOfBoundsException extends Exception{
  /**
   * Constructor for OutOfBoundsException.
   */
  public TailOutOfBoundsException(){
    super("Tail is out of bounds!");
  }
}