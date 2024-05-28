package ca.bcit.comp2522.labs.lab04;

/**
 * TailOutOfBoundsException class extending
 * from OutOfBoundsException parent class which is
 * thrown when tail from player is out of bounds.
 *
 * @author Towa Quimbayo
 * @version 1.0, September 29, 2022
 */
public class TailOutOfBoundsException extends OutOfBoundsException {
  /**
   * Constructor for TailOutOfBoundsException.
   */
  public TailOutOfBoundsException() {
    super("The tail from player went out of bounds.");
  }
}
