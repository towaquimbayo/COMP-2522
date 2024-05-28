package org.bcit.comp2522.exam.answers;

import java.util.ArrayList;
import java.util.List;

public class Q2_SaveCommandExecutor {
  private final List<Q2_SaveOperation> listCommands = new ArrayList<>();

  public void addCommands(Q2_SaveOperation command) {
    listCommands.add(command);
    command.save();
  }
}
