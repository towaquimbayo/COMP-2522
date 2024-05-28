package org.bcit.comp2522.exam.answers;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Q2_FileWriter {
  protected FileWriter fileWriter;
  public Q2_FileWriter(String name, String msg) throws IOException {
    fileWriter = null;
    try {
      fileWriter = new FileWriter(name, true);
      BufferedWriter bw = new BufferedWriter(fileWriter);
      bw.write(msg);
      bw.newLine();
      bw.close();
    } catch (IOException e) {
      throw new RuntimeException(e.getMessage());
    }
  }
}
