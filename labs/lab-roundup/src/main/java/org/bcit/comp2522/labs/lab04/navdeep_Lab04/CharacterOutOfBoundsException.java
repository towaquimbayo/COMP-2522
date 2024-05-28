package org.bcit.comp2522.labs.lab04.navdeep_Lab04;

/**
 * Thrown when a character is out of bounds.
 *
 * @author paul_bucci
 * @version 1.0
 */
public class CharacterOutOfBoundsException extends OutOfBoundsException {
  /**
   * Constructor for CharacterOutOfBoundsException.
   */
  public CharacterOutOfBoundsException() {
    super("A character went out of bounds.");
  }
}
