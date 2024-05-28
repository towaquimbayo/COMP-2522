package org.bcit.comp2522.labs.lab07;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Custom JSON Parser to parse a string.
 *
 * @author Tracy Ly, Jackie Ma, Alex Kong
 * @version 1.0
 */
public class  MyJSONParser {

  String str;
  int index = 0;
  HashMap<String, Object> pairs;
  HashSet<Character> digits;

  /**
   * Constructor.
   */
  public MyJSONParser() {
    readFromFile();
    digits = new HashSet<>();
    pairs = new HashMap<>();
    initDigits();
  }

  public String getString(String s) {
    return (String) pairs.get(s);
  }

  public int getInt(String s) {
    return (int) pairs.get(s);
  }

  public float getFloat(String s) {
    return (float) pairs.get(s);
  }

  public ArrayList<Object> getArray(String s) {
    return (ArrayList<Object>) pairs.get(s);
  }

  public void print() {
    System.out.println(pairs);
  }

  // copied from SL-07 and refactored to store the result in this.str
  private void readFromFile() {
    FileInputStream inFile;
    try {
      ArrayList<Byte> contents = new ArrayList<Byte>();
      inFile = new FileInputStream("./text/input.txt");
      int chunk;
      while ((chunk = inFile.read()) != -1) {
        contents.add((byte) chunk);
      }
      inFile.close();

      byte[] barr = new byte[contents.size()];

      int i = 0;
      for (Byte b : contents) {
        barr[i] = b;
        i++;
      }

      String s = new String(barr);

      this.str = s;
    } catch (FileNotFoundException e) {
      System.out.println(e);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  private void initDigits() {
    digits.add('0');
    digits.add('1');
    digits.add('2');
    digits.add('3');
    digits.add('4');
    digits.add('5');
    digits.add('6');
    digits.add('7');
    digits.add('8');
    digits.add('9');
  }

  /**
   * Parse ./text/input.txt
   */
  public void parseJsonFromFile() {
    readFromFile();
    parseJson();
  }

  public void parseJsonFromString(String str) {
    this.str = str;
    parseJson();
  }

  private void parseJson() {
    while (index < str.length()) {
      char c = str.charAt(index);
      switch (c) {
        case '\"':
          capturePair();
          break;
        case '}':
          return;
        default:
          index++;
      }
    }
  }

  private MyJSONParser captureObject() {
    MyJSONParser inner = new MyJSONParser();
    String innerObj = "";
    while (str.charAt(index) != '}') {
      innerObj += str.charAt(index);
      index++;
    }
    innerObj += str.charAt(index);
    inner.parseJsonFromString(innerObj);
    index++; // increment past '}'
    return inner;
  }

  private ArrayList<Object> captureArray() {
    index++;
    char c = str.charAt(index);
    ArrayList<Object> arr = new ArrayList<>();
    while (str.charAt(index) != ']') {
      while (arraySkipChars()) {
        index++;
      }
      arr.add(captureValue());
      while (arraySkipChars()) {
        index++;
      }
    }
    return arr;
  }

  private boolean arraySkipChars() {
    switch (str.charAt(index)) {
      case ' ':
      case '\n':
      case '\r':
      case ',':
        return true;
      default:
        return false;
    }
  }

  /**
   * Capture a key value pair into pairs hashmap.
   *
   * @precondition i needs to be the index of a \" preceding a key
   */
  private void capturePair() {
    index++;
    String key = captureKey();
    // skip whitespace and colon
    while (str.charAt(index) == ' ' || str.charAt(index) == ':') {
      index++;
    }
    Object value = captureValue();
    pairs.put(key, value);
  }

  private String captureKey() {
    String key = "";
    while (str.charAt(index) != '\"') {
      key += str.charAt(index);
      index++;
    }
    index++;
    return key;
  }

  private Object captureValue() {
    char c = str.charAt(index);
    switch (c) {
      case '\"':
        return (Object) captureStringValue();
      case '[':
        return captureArray();
      case '{':
        return captureObject();
      case '-':
        index++;
        return (Object) captureNumberValue(true);
      default:
        return (Object) captureNumberValue(false);
    }
  }

  private String captureStringValue() {
    String value = "";
    index++;
    while (str.charAt(index) != '\"') {
      value += str.charAt(index);
      index++;
    }
    index++;
    return value;
  }

  /**
   * returns a parsed float or integer.
   *
   * @param isNegative whether the number is negative or not
   * @return either an integer or a float
   */
  private Object captureNumberValue(boolean isNegative) {
    int j = 0;
    int k = index;
    int total = 0;
    float floatTotal = 0f;
    int floatExp = 0;
    boolean isFloat = false;
    while (digits.contains(str.charAt(k)) || str.charAt(k) == '.') {
      if (str.charAt(k) == '.') {
        floatExp = k - index;
        isFloat = true;
      }
      j++; // increment exponent
      k++; // iterate character
    }
    int ascii = 48;
    int base = 10;
    while (digits.contains(str.charAt(index)) || str.charAt(index) == '.') {
      if (isFloat && str.charAt(index) != '.') {
        floatTotal += (float) (((int) str.charAt(index) - ascii) * Math.pow(base, --floatExp));
      } else if (str.charAt(index) != '.') {
        // convert ascii char to int and accumulate by place value
        total += ((int) str.charAt(index) - ascii) * Math.pow(base, --j);
      }
      index++; // iterate character
    }
    if (str.charAt(index) != ']') {
      index++;
    }
    if (isFloat) {
      return isNegative ? floatTotal * -1 : floatTotal;
    }
    return isNegative ? total * -1 : total;
  }

  public String toString() {
    return pairs.toString();
  }
}

