package org.bcit.comp2522.labs.lab04;

/**
 * Thrown when a character is out of bounds.
 *
 * @author paul_bucci
 * @version 1.0
 */
public class CharacterOutOfBoundsException extends OutofBoundsException {

  OutofBoundsException o = new OutofBoundsException("Hello");

  /**
   * Constructor for CharacterOutOfBoundsException.
   */
  public CharacterOutOfBoundsException(String s) {
    super("A Character went out of bounds");
  }


}
