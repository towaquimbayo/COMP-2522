package org.bcit.comp2522.labs.lab06.andrew_jason;

public interface IComparable extends Comparable {
  @Override
  default int compareTo(Object o) {
    return 0;
  }
}
