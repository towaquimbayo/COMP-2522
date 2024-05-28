package org.bcit.comp2522.labs.lab04;

public class OutofBoundsException extends Exception{

    public OutofBoundsException(String s) {
        super(String.format("Out Of Bounds exception: %s", s));
    }
}
