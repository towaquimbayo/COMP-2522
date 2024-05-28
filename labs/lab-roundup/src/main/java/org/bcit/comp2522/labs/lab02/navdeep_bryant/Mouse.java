package org.bcit.comp2522.labs.lab02.navdeep_bryant;

import processing.core.PApplet;

/** Mouse class.
 *
 * @author Navdeep Litt
 * @version 1.0
 */
public class Mouse extends PApplet {

  public void settings() {
    size(640, 360);
  }

  /** Main function.
   *
   * @param passedArgs arguments from command line
   */
  public static void main(String[] passedArgs) {

    String[] appletArgs = new String[] {"Mouse"};
    Mouse mouse = new Mouse();
    PApplet.runSketch(appletArgs, mouse);
  }

}
