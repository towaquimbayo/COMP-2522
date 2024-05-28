package org.bcit.comp2522.exam.questions;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Q2_SaveKeyboardShortcut extends Q2_KeyboardShortcut {
  public void save() {
    FileWriter fw = null;
    try {
      fw = new FileWriter("save.txt", true);
      BufferedWriter bw = new BufferedWriter(fw);
      bw.write("save keyboard shortcut");
      bw.newLine();
      bw.close();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
