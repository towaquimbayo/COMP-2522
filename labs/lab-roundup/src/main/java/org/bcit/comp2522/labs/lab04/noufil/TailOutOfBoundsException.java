package org.bcit.comp2522.labs.lab04.noufil;

public class TailOutOfBoundsException extends OutOfBoundsException {

  public TailOutOfBoundsException() {
    super("The tail of the player is out of bounds.");
  }
}
