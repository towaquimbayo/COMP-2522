package org.bcit.comp2522.labs.lab07.noufil;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MyJSONParser {
  String unformatted;
  String formatted;
  Map<String, String> data;

  /**
   * Constructor for the class
   */
  public MyJSONParser(String path) {
    this.unformatted = readFile(path);
    this.formatted = "";
    this.data = new HashMap<>();
    parseData();
  }

  /**
   * Reads a file and returns a string of all characters in the file.
   *
   * @param path the file path
   * @return a string of file content
   */
  public String readFile(String path) {
    FileInputStream inFile;

    try {
      ArrayList<Byte> contents = new ArrayList<>();
      inFile = new FileInputStream(path);
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

      return new String(barr);
    } catch (FileNotFoundException e) {
      System.out.println("File does not exist");
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    return null;
  }

  /**
   * Format the inputted string and add it to the hashmap.
   */
  public void parseData() {
    // Removes unnecessary characters (quotes, newline, carriage return)
    for (int i = 1; i < unformatted.length() - 1; i++) {
      char c = unformatted.charAt(i);

      if ((c != '\"') && (c != '\'') && (c != '\n') && (c != '\r')) {
        if ((c == ' ') && (unformatted.charAt(i + 1) == ' ')) {
          continue;
        }

        formatted += c;
      }
    }

    ArrayList<String> keyValuePairs = new ArrayList<>();
    StringBuilder s = new StringBuilder();

    boolean isInArray = false;
    boolean isInNestedObject = false;

    for (int i = 0; i < formatted.length(); i++) {
      char c = formatted.charAt(i);

      // Checks if it is in an array or nested object
      if ((c == '[') && (formatted.charAt(i + 2) != '{')) {
        isInArray = true;
      } else if ((c == ']') && (formatted.charAt(i - 2) != '}')) {
        isInArray = false;
      } else if ((c == '[') && (formatted.charAt(i + 2) == '{')) {
        isInNestedObject = true;
      } else if ((c == ']') && (formatted.charAt(i - 2) == '}')) {
        isInNestedObject = false;
      }

      // Adds all key-value pairs to an ArrayList
      if ((c != ',') || (isInArray) || (isInNestedObject)) {
        // wouldn't work if key or value has a semicolon
        if ((c == ':') && (isInNestedObject)) {
          c = ';';
        }

        s.append(c);
      } else {
        keyValuePairs.add(String.valueOf(s));
        s.setLength(0);
      }

      if ((i == formatted.length() - 1)) {
        keyValuePairs.add(String.valueOf(s));
        s.setLength(0);
      }
    }

    // Separates keys and values where colon (:) is found
    for (String pair : keyValuePairs) {
      StringBuilder key = new StringBuilder();
      StringBuilder value = new StringBuilder();

      boolean isInKey = true;

      for (int i = 0; i < pair.length(); i++) {
        char c = pair.charAt(i);

        if ((c == ':')) {
          isInKey = false;
          continue;
        }

        if (isInKey) {
          key.append(c);
        } else {
          value.append(c);
        }
      }

//      System.out.println(pair);

      // Adds the keys and values to hashmap
      data.put(String.valueOf(key).trim(), String.valueOf(value).trim());
    }
  }

  /**
   * Check if value is int.
   *
   * @param s A value string.
   * @return true if it is int else false
   */
  public boolean isInt(String s) {
    try {
      Integer.parseInt(s);
    } catch (NumberFormatException e) {
      return false;
    }

    return true;
  }

  /**
   * Check if value is float.
   *
   * @param s A value string.
   * @return true if it is float else false
   */
  public boolean isFloat(String s) {
    try {
      Float.parseFloat(s);
    } catch (NumberFormatException e) {
      return false;
    }

    int hasDecimal = 0;

    for (int i = 0; i < s.length(); i++) {
      if (s.charAt(i) == '.') {
        hasDecimal++;
      }
    }

    return (hasDecimal > 0);
  }

  /**
   * Get the string associated with a key.
   *
   * @param key A key string.
   * @return A string which is the value.
   */
  public String getString(String key) {
    String value = this.data.get(key);

    if (value != null) {
      return value;
    } else {
      return "nothing";
    }
  }

  /**
   * Get the int value associated with a key.
   *
   * @param key A key string.
   * @return The integer value.
   */
  public int getInt(String key) {
    final String value = this.data.get(key);

    return Integer.parseInt(value);
  }

  /**
   * Get the float value associated with a key.
   *
   * @param key A key string.
   * @return The float value.
   */
  public float getFloat(String key) {
    final String value = this.data.get(key);

    return Float.parseFloat(value);
  }

  /**
   * Get all elements of array as string value associated with a key.
   *
   * @param key A key string.
   * @return The string value.
   */
  public String getArray(String key) {
    String value = this.data.get(key);

    if (value != null) {
      StringBuilder result = new StringBuilder();

      for (int i = 0; i < value.length(); i++) {
        char c = value.charAt(i);

        if ((c != '[') && (c != ']')) {
          result.append(c);
        }
      }

      return String.valueOf(result);
    } else {
      return "This key does not exist.";
    }
  }

  /**
   * Get single element of array as value associated with a key.
   *
   * @param key A key string.
   * @return The float value.
   */
  public Object getArray(String key, int index) {
    String value = this.data.get(key);
    StringBuilder element = new StringBuilder();

    if (value != null) {
      ArrayList<String> elements = new ArrayList<>();
      StringBuilder s = new StringBuilder();

      for (int i = 1; i < value.length() - 1; i++) {
        char c = value.charAt(i);

        if ((c != ',')) {
          s.append(c);
        } else {
          elements.add(String.valueOf(s).trim());
          s.setLength(0);
        }

        if ((i == value.length() - 2)) {
          elements.add(String.valueOf(s).trim());
          s.setLength(0);
        }
      }

      if (index >= elements.size()) {
        element.append("This index does not exist in the array");
      } else {
        element.append(elements.get(index));
      }

      // Returns the type of value
      if (isFloat(String.valueOf(element))) {
        return Float.parseFloat(String.valueOf(element));
      } else if (isInt(String.valueOf(element))) {
        return Integer.parseInt(String.valueOf(element));
      } else {
        return String.valueOf(element);
      }
    } else {
      return "This key does not exist.";
    }
  }

  public static void main(String[] passedArgs) {
    String str = "input.txt";
    MyJSONParser obj = new MyJSONParser(str);

    String s = obj.getString("key1");
    int i = obj.getInt("key2");
    float f = obj.getFloat("key3");
    int a = (int) obj.getArray("key4", 1);

    System.out.println("Key1: " + s);
    System.out.println("Key2: " + i);
    System.out.println("Key3: " + f);
    System.out.println("Key4: " + a);
  }
}