package labs;

/**
 * file: Driver_lab3a.java
 * author: Samantha Berry
 * course: MSCS 630
 * assignment: lab 3
 * due date: March 14, 2021
 * version: 1.0
 *
 * This file contains the cofModDet method for lab3.
 */
public class Driver_lab3a {
  public static void main(String[] args) {
  }

  /**
   * cofModDet
   *
   * This function computes determinate of
   * the given input matrix in a given modulo .
   *
   * Parameters:
   *   m: the modulo under which all calculations are preformed, m > 0.
   *   A: a matrix with size > 0.
   *
   * Return value: an integer of the determinate of A in modulo m.
   */
  public static int cofModDet(int m, int[][] A){
    int width = A.length;
    int result = 0;
    if (width == 1){
      return Math.floorMod(A[0][0],m);
    }
    else if (width == 2){
      return Math.floorMod(Math.floorMod(A[0][0],m)*Math.floorMod(A[1][1],m)
          - Math.floorMod(A[1][0],m)*Math.floorMod(A[0][1],m),m);
    }
    else{

      for (int cofa = 0; cofa < width; cofa++){
        int[][] mid = generateSubArray(A, width, cofa);
        result += Math.pow(-1.0, 1.0+cofa+1.0)*Math.floorMod(A[0][cofa],m)
            * Math.floorMod(cofModDet(m,mid),m);
      }
    }
    return Math.floorMod(result,m);
  }

  /**
   * generateSubArray
   *
   * This function generates a subarray of a given 2D array
   *
   * Parameters:
   *   A: an 2D array with size >0
   *   n: an integer for the size of the new array horizontally
   *   cofa: an integer for the row of the cofactor (skipped line)
   *
   * Return value: an integer of the determinate of A in modulo m.
   */
  private static int[][] generateSubArray (int A[][], int n, int cofa){
    int[][] m = new int[n-1][];
    for (int k=0; k<(n-1); k++)
      m[k] = new int[n-1];

    for (int i=1; i<n; i++){
      int j1=0;
      for (int j=0; j<n; j++){
        if(j != cofa) {
          m[i - 1][j1] = A[i][j];
          j1++;
        }
      }
    }
    return m;
  }


}
