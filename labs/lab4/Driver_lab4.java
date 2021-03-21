package labs;
import java.util.Scanner;

/**
 * file: Driver_lab4.java
 * author: Samantha Berry
 * course: MSCS 630
 * assignment: lab 4
 * due date: March 21, 2021
 * version: 1.0
 *
 * This file interprets input keys for AESCipher and outputs them.
 */
public class Driver_lab4 {
  public static void main(String args[]){
    Scanner inputScanner = new Scanner(System.in);
    AESCipher myCipher = new AESCipher();
    System.out.println("Please enter a 16 byte key:");
    String input = inputScanner.nextLine();
    String[] output = myCipher.aesRoundKeys(input);

    for(int i = 0; i < output.length; i++){
      System.out.print("Round: " + i + "\n");
      System.out.print("Key:      " + output[i] + "\n\n");
    }
  }
}
