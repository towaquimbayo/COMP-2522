package org.bcit.comp2522.labs.lab03.aryan;

public class TailOutOfBoundsException extends OutOfBoundsException {
  public TailOutOfBoundsException() {
    super("The player character's tail went out of bounds");
  }
}
