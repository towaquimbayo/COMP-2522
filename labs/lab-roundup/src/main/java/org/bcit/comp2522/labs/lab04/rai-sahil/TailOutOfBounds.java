package org.bcit.comp2522.labs.lab04;

public class TailOutOfBounds extends OutofBoundsException{
    public TailOutOfBounds(String s){
        super("Tail went out of bounds");
    }
}
