package org.bcit.comp2522.labs.lab07.Towa_Alex;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Parse a file formatted as JSON structure and converts to JSON object.
 * Reads from a file and converts.
 *
 * @author Towa Quimbayo, Alex Gibbison
 * @version 1.0
 */
public class MyJSONParser {
  /** String that stores the strings in the file. */
  private final String parseString;
  /** Hashmap that stores the key, value pairs. */
  private final HashMap<String, Object> map;
  /** JSON Special Characters array. */
  public static final char[] SPECIAL_CHARS = {'{', '}', '"', ':', ',', ' ', '\r', '\n'};
  /** Array special characters array. */
  public static final char[] ARRAY_CHARS = {'[', ']'};

  /**
   * JSON Parser constructor.
   *
   * @param s for path to file.
   */

  public MyJSONParser(String s) {
    this.parseString = readFile(s);
    map = new HashMap<>();
    parseString();
  }

  /**
   * Read file method that converts to string from file.
   *
   * @param path for String file path
   * @return the string in file
   */
  private String readFile(String path) {
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
      System.out.println("Error" + e);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    return null;
  }

  /**
   * Parse function that creates a JSON object.
   */
  public void parseString() {
    String key = "";
    String val = "";
    ArrayList<Character> list = new ArrayList<>();

    for (int i = 0; i < this.parseString.length(); i++) {
      boolean isSpecial = false;
      boolean isArray = false;
      char c = this.parseString.charAt(i);
      for (char specialChar : SPECIAL_CHARS) {
        if (c == specialChar) {
          isSpecial = true;
          break;
        }
      }
      // if its array open close bracket
      if (c == ARRAY_CHARS[0]) {
        isArray = true;
      }

      if (!isSpecial && !isArray) {
        list.add(c);
      } else if (isArray) {
        String arrayValues = String.valueOf(c);
        int count = i + 1;

        // open bracket to close bracket
        while (this.parseString.charAt(count) != ARRAY_CHARS[1]) {
          arrayValues += this.parseString.charAt(count);
          count++;
        }
        arrayValues += ARRAY_CHARS[1]; // add closing bracket
        i = count + 1; // move the loop index to next character after closing bracket
        val = arrayValues;
      } else {
        if ((!key.equals("") && !val.equals("")) || (i == this.parseString.length() - 1)) {
          if (i == this.parseString.length() - 1) {
            for (char character : list) {
              val += character;
            }
            list.clear();
          }
          this.map.put(key, val);
          key = "";
          val = "";
        } else {
          if (key.equals("")) {
            for (char character : list) {
              key += character;
            }
            list.clear();
          } else {
            for (char character : list) {
              val += character;
            }
            list.clear();
          }
        }
      }
    }
  }

  /**
   * Get String method from passing key.
   *
   * @param key for key
   * @return the String value from the key
   */
  public String getString(String key) {
    return (String) this.map.get(key);
  }

  /**
   * Get Int method from passing key.
   *
   * @param key for key
   * @return the int value from the key
   */
  public int getInt(String key) {
    return Integer.parseInt((String) this.map.get(key));
  }

  /**
   * Get float method from passing key.
   *
   * @param key for key
   * @return the float value from the key
   */
  public float getFloat(String key) {
    return Float.parseFloat((String) this.map.get(key));
  }

  /**
   * Get array method from passing key.
   *
   * @param key for key
   * @return the array value from key
   */
  public Object[] getArray(String key) {
    String strArr = ((String) this.map.get(key)).trim();
    ArrayList<Object> list = new ArrayList<>();
    String value;

    if (strArr.length() < 1) {
      System.out.println("Empty Value");
      return new Object[0];
    }

    for (int i = 0; i < strArr.length(); i++) {
      char c = strArr.charAt(i);
      if (c >= '0' && c <= '9') { // For Integer and Float Types
        value = String.valueOf(c);
        boolean hasDecimal = false;
        for (int j = i + 1; j < strArr.length(); j++) {
          char nextChar = strArr.charAt(j);
          if ((nextChar >= '0' && nextChar <= '9') || nextChar == '.') {
            if (nextChar == '.') {
              if (!hasDecimal) {
                hasDecimal = true;
                value += nextChar;
              } else {
                System.out.println("Invalid float value.");
              }
            } else {
              value += nextChar; // if next char is a valid int then concatenate
              if (j + 1 >= strArr.length()) {
                i = j; // j is index of non int
                j = strArr.length(); // break loop
              }
            }
          } else {
            i = j; // j is index of non int
            j = strArr.length(); // break loop
          }
        }
        list.add(value);
      } else if (c == '"') { // for Strings
        value = "";
        for (int j = i + 1; j < strArr.length(); j++) {
          char nextChar = strArr.charAt(j);
          if (nextChar != '"') {
            value += nextChar;
          } else {
            if (value.length() > 0) {
              list.add(value);
            }
            i = j; // j is index of non int
            j = strArr.length(); // break loop
          }
        }
      } else if (c == '\'') {
        value = "";
        for (int j = i + 1; j < strArr.length(); j++) {
          char nextChar = strArr.charAt(j);
          if (nextChar == '\\') {
            char escapeSequenceChar = strArr.charAt(j + 1);
            value += nextChar + "" + escapeSequenceChar;
            nextChar = strArr.charAt(j + 2);
            j += 2;
          }
          if (nextChar != '\'') {
            value += nextChar;
          } else {
            if (value.length() > 0) {
              list.add(value);
            }
            i = j; // j is index of non int
            j = strArr.length(); // break loop
          }
        }
      }
    }

    // Add each integer to array
    Object[] arr = new Object[list.size()];
    int count = 0;
    for (Object n : list) {
      arr[count] = n;
      count++;
    }

    return arr;
  }

  /**
   * Main method.
   *
   * @param args for string arguments
   */
  public static void main(String[] args) {
    MyJSONParser obj = new MyJSONParser("./text/input.txt");

    System.out.println("String: ");
    String key1 = obj.getString("key1");
    System.out.println(key1);

    System.out.println("\nInteger: ");
    int key2 = obj.getInt("key2");
    System.out.println(key2);

    System.out.println("\nFloat: ");
    float key3 = obj.getFloat("key3");
    System.out.println(key3);

    System.out.println("\nInteger Array: ");
    Object[] intArr = obj.getArray("my_int_array");
    for (Object x : intArr) {
      System.out.print(x + " ");
    }

    System.out.println("\n\nFloat Array: ");

    Object[] floatArr = obj.getArray("my_float_array");
    for (Object x : floatArr) {
      System.out.print(x + " ");
    }

    System.out.println("\n\nString Array: ");

    Object[] stringArr = obj.getArray("my_string_array");
    for (Object x : stringArr) {
      System.out.print(x + " ");
    }

    System.out.println("\n\nChar Array: ");

    Object[] charArr = obj.getArray("my_char_array");
    for (Object x : charArr) {
      System.out.print(x + " ");
    }
  }
}