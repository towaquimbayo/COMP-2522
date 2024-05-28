package org.bcit.comp2522.exam.questions;

import java.io.*;

public class Q2_Instructions {
  /**
   * Q2. Design patterns (33 points)
   * This question tests your ability to use design patterns for both
   * refactoring and code creation. You will be using both Chain of
   * Responsibility and Command. You will be marked on:
   *   - Following the requirements
   *   - Completing the question TODOs
   *   - Good style, i.e., clearly written code and coherent methods
   * Comments are not strictly required but they are appreciated.
   * There will also be marks available for creativity. If you finish this question
   * and have non-trivial ideas about how to extend this class, I will consider
   * giving bonus marks for unique ideas and solutions.
   *
   * TODO: Copy this file and all Q2 files into a package called 'org.bcit.comp2522.exam.answers'
   * TODO: If you create other files, they must be prepended with 'Q2_'
   *
   * TODO: Refactor the Q2 classes to use the Command pattern.
   * TODO: Add a Chain of Responsibility with the following requirements:
   * - There must be a new class for each item in the chain
   * - The chain is Save -> Timestamp -> WriteFile
   * - The save command must take an arbitrary string and initiate the chain of responsibility to save into an arbitrary file location.
   * - The output string must have a timestamp given by a SEPARATE timestamp class
   * - The file writer must be in a SEPARATE class that ONLY takes a filename and string and writes it to file.
   */

  public static void main(String[] args) {
    Q2_SaveKeyboardShortcut ks = new Q2_SaveKeyboardShortcut();
    Q2_SaveMenuItem mi = new Q2_SaveMenuItem();
    Q2_SaveUIButton uib = new Q2_SaveUIButton();
    ks.save();
    mi.save();
    uib.save();
  }
}
