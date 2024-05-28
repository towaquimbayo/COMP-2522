package org.bcit.comp2522.labs.lab07;

/**
 * Parses json file ./text/input.txt.
 *
 * @author Tracy Ly, Jackie Ma, Alex Kong
 * @version 1.0
 */
public class Main {

  /**
   * Runs program.
   *
   * @param args command line arguments
   */
  public static void main(String[] args) {
    MyJSONParser obj = new MyJSONParser();
    obj.parseJsonFromFile();
    obj.print();
  }
}