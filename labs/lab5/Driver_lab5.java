package labs;
import java.util.Scanner;

/**
 * file: Driver_lab5.java
 * author: Samantha Berry
 * course: MSCS 630
 * assignment: lab 5
 * due date: April 11, 2021
 * version: 1.0
 *
 * This file interprets input and keys for AESCipher and outputs them.
 */
public class Driver_lab5 {
  public static void main(String args[]){
    Scanner inputScanner = new Scanner(System.in);
    AESCipher myCipher = new AESCipher();
    System.out.println("Please enter a 16 byte key: ");
    String key = inputScanner.nextLine();
    System.out.println("\n"+"Please enter a 16 byte plain text: ");
    String input = inputScanner.nextLine();
    String output = myCipher.AES(input, key);

    System.out.println("\n"+"CipherText:" + output);
  }

}
