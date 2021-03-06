package labs;

import java.util.HashMap;
import java.util.Arrays;

/**
 * file: Driver_lab1.java
 * author: Samantha Berry
 * course: MSCS 630
 * assignment: lab 1
 * due date: March 7, 2021
 * version: 1.0
 *
 * This file contains the str2int method.
 */
public class Driver_lab1 {
  public static void main(String[] args) {

  }

  /**
   * str2int
   *
   * This function computes the numeral cypher
   * of the given input.
   *
   * Parameters:
   *   plainText: the input text, contains no special characters.
   *
   * Return value: an array containing the numeral cypher output.
   */
  public int[] str2int(String plainText) {
    HashMap<String, String> letterMatch = new HashMap<>();
    letterMatch.put("a", "0");
    letterMatch.put("b", "1");
    letterMatch.put("c", "2");
    letterMatch.put("d", "3");
    letterMatch.put("e", "4");
    letterMatch.put("f", "5");
    letterMatch.put("g", "6");
    letterMatch.put("h", "7");
    letterMatch.put("i", "8");
    letterMatch.put("j", "9");
    letterMatch.put("k", "10");
    letterMatch.put("l", "11");
    letterMatch.put("m", "12");
    letterMatch.put("n", "13");
    letterMatch.put("o", "14");
    letterMatch.put("p", "15");
    letterMatch.put("q", "16");
    letterMatch.put("r", "17");
    letterMatch.put("s", "18");
    letterMatch.put("t", "19");
    letterMatch.put("u", "20");
    letterMatch.put("v", "21");
    letterMatch.put("w", "22");
    letterMatch.put("x", "23");
    letterMatch.put("y", "24");
    letterMatch.put("z", "25");
    letterMatch.put(" ", "26");
	
    String cryptInput = plainText;
    cryptInput = cryptInput.toLowerCase();

    String stringOutput = "";

    for (int i = 0; i < cryptInput.length();i++) {
      String tempChar = String.valueOf(cryptInput.charAt(i));
      String letterOutput = letterMatch.get(tempChar);
      if(letterOutput != null) {
        stringOutput += letterOutput+" ";
      }
    }
    stringOutput = stringOutput.trim();
    return Arrays.stream(stringOutput.split(" "))
        .mapToInt(Integer::parseInt).toArray();
    }
	
}

