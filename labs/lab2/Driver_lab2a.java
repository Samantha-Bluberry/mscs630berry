package labs;

/**
 * file: Driver_lab2a.java
 * author: Samantha Berry
 * course: MSCS 630
 * assignment: lab 2
 * due date: March 14, 2021
 * version: 1.0
 *
 * This file contains the euclidAlg method for lab2.
 */
public class Driver_lab2a {
  public static void main(String[] args) {
  }

  /**
   * euclidAlg
   *
   * This function computes the greatest common divisor of a and b
   * where a,b > 0 and a > b
   *
   * Parameters:
   *   a: first long
   *   b: second long
   *
   * Return value: the greatest common divisor as a long
   */
  public static long euclidAlg(long a, long b){
    if (b == 0) return a;
    else return euclidAlg(b, a % b);
  }
}
