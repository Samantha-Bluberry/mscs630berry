package labs;

/**
 * file: Driver_lab3b.java
 * author: Samantha Berry
 * course: MSCS 630
 * assignment: lab 3
 * due date: March 14, 2021
 * version: 1.0
 *
 * This file contains the getHexMatP method for lab3.
 */
public class Driver_lab3b {
  public static void main(String[] args) {
  }

  /**
   * getHexMatP
   *
   * This function computes a hex matrix containing the ASCII values of the
   * input string, with extra spaces filled with the substitution char.
   *
   * Parameters:
   *   s: the substitution character, used to fill in extra spaces when
   *      p.size > 16
   *   p: the string to be transformed into the hex matrix, size <= 16.
   *
   * Return value: an 4x4 matrix containing the ASCII values of p in hex.
   */
  public static int[][] getHexMatP(char s, String p ){
    int[][] output = new int[4][4];
    int padChar = s;

    int arrayCounter = 0;
    for (int j = 0; j < output[0].length; j++){
      for (int i = 0; i < output.length; i++){
        if (arrayCounter > p.length()){
          output[i][j] = padChar;
        }
        else{
          output[i][j] = p.charAt(arrayCounter);
          arrayCounter++;
        }
      }
    }
    return output;
  }
}
