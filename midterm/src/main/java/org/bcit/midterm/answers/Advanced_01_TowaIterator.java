package org.bcit.midterm.answers;

import java.util.HashMap;
import java.util.Iterator;

public class Advanced_01_TowaIterator<String> implements Iterator<String> {

  protected HashMap<String, String> iteratorMap;

  private int index;

  public Advanced_01_TowaIterator(HashMap<String, String> hm) {
    this.iteratorMap = hm;
    this.index = 0;
  }

  @Override
  public boolean hasNext() {
    return (this.iteratorMap.size() > index);
  }

  @Override
  public String next() {
    String s = this.iteratorMap.get(index);
    index++;
    return s;
  }
}
