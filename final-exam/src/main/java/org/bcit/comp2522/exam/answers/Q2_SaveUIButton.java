package org.bcit.comp2522.exam.answers;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Q2_SaveUIButton implements Q2_SaveOperation {
  @Override
  public void save() {
    try {
      Q2_FileWriter q2_fileWriter = new Q2_FileWriter("save.txt", "save ui button");
      q2_fileWriter.fileWriter.close();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
