package org.bcit.comp2522.labs.lab04.Towa;

/**
 * CharacterOutOfBoundsException class that extends from
 * OutOfBoundsException class which is thrown
 * when a character is out of bounds.
 *
 * @author Towa Quimbayo
 * @version 1.0, September 29, 2022
 */
public class CharacterOutOfBoundsException extends OutOfBoundsException {

  /**
   * Constructor for CharacterOutOfBoundsException.
   */
  public CharacterOutOfBoundsException() {
    super("A food character went out of bounds.");
  }
}
