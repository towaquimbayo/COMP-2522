package org.bcit.comp2522.labs.lab06.andrew_jason;

public class AbstractStrength implements Comparable<AbstractStrength> {

  public int value;

  public int getStrength() {
    return value;
  }

  public void setStrength(int s) {
    this.value = s;
  }

  @Override
  public int compareTo(AbstractStrength o) {
    if (this.getStrength() > o.getStrength()) {
      return 1;
    } else if (o.getStrength() > this.getStrength()) {
      return -1;
    } else {
      return 0;
    }
  }
}
