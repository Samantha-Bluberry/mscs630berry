package aesproject;

import java.io.*;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * file: AESDriver.java
 * author: Samantha Berry
 * course: MSCS 630
 * assignment: Final Project
 * due date: May 30, 2021
 * version: 1.0
 *
 * This file contains the main function for my final project.
 * It handles all user input and file interaction.
 *
 */
public class AESDriver {
  public static void main(String[] args){
    boolean notDone = true;
    Scanner myInput = new Scanner(System.in);
    String mainFolderLoc = "input/";

    AESCipher aes = new AESCipher();

    while (notDone){
      int enDe = 0;
      boolean keyLoop = false;
      System.out.println("AES Batch Encryption and Decryption");
      System.out.println("Program by Samantha Berry");
      System.out.println("Please make sure the input folder is placed correctly in the main program directory");
      System.out.println(" ");
      System.out.println("Are you preforming [E]ncryption or [D]ecryption?");

      String enInput = myInput.nextLine();


      enInput = enInput.toUpperCase();
      if(enInput.length() == 0 ){
        System.out.println("Please enter an input");
      } else if(enInput.substring(0,1).equals("E")){
        enDe = 1;
        keyLoop = true;
      } else if(enInput.substring(0,1).equals("D")){
        enDe = 2;
        keyLoop = true;
      } else {
        System.out.println("Please enter a valid command, [E] or [D].");
      }

      while (keyLoop){
        int keySel = 0;
        String key = "";

        if(enDe == 1) {
          System.out.println("Encryption Selected, How do you want to enter your key?");
          System.out.println("[A] manually, [B] from a file, [C] randomly generated.");

          String keyInput = myInput.nextLine();
          keyInput = keyInput.toUpperCase();

          if(keyInput.length() == 0){
            System.out.println("Please enter an Input");
          } else if (keyInput.substring(0, 1).equals("A")) {
            keySel = 1;
          } else if (keyInput.substring(0, 1).equals("B")) {
            keySel = 2;
          } else if (keyInput.substring(0, 1).equals("C")) {
            keySel = 3;
          }
        } else if(enDe == 2) {
          System.out.println("Decryption Selected, How do you want to enter your key?");
          System.out.println("[A] manually, [B] from a file");

          String keyInput = myInput.nextLine();
          keyInput = keyInput.toUpperCase();

          if(keyInput.length() == 0){
            System.out.println("Please enter an Input");
          } else if (keyInput.substring(0, 1).equals("A")) {
            keySel = 1;
          } else if (keyInput.substring(0, 1).equals("B")) {
            keySel = 2;
          }
        }

        if(keySel == 1){
          System.out.println("Enter a valid key");
          String keyIn = myInput.nextLine();

          if(keyIn.length() == 32 && keyIn.matches("[0-9A-F]+")){
            key = keyIn;
            System.out.println("Valid key entered.");
          } else {
            System.out.println("Invalid key entered, please select another method or input valid key");
          }
        } else if (keySel == 2){
          System.out.println("Reading key...");

          try {
            String keyIn = "";
            File keyFile = new File(mainFolderLoc+"key/key.txt");
            InputStream is = new FileInputStream(keyFile);
            byte[] buffer = new byte[(int)keyFile.length()];
            is.read(buffer);
            is.close();

            keyIn = new String(buffer);
            keyIn = keyIn.trim();

            System.out.println("Key: " + keyIn);

            if(keyIn.length() == 32 && keyIn.matches("[0-9A-F]+")){
              System.out.println("Valid key entered.");
              key = keyIn;
            } else {
              System.out.println("Invalid key entered, please select another method or input valid key");
            }
          }
          catch(FileNotFoundException ex) {
            System.out.println(
                "Unable to open file.");
          }
          catch(IOException ex) {
            System.out.println(
                "Error reading file.");
            // Or we could just do this:
            // ex.printStackTrace();
          }

        } else if (keySel == 3){
          System.out.println("Generating key...");

          key = AESCipher.genHexString(16);
          System.out.println("Key generated: " + key);
          System.out.println("Writing key to file...");

          try {

            byte[] buffer = key.getBytes();

            BufferedWriter outputStream =
                new BufferedWriter(new FileWriter(mainFolderLoc+"key/key.txt"));

            outputStream.write(key);

            // Always close files.
            outputStream.close();

            System.out.println("Wrote " + buffer.length +
                " bytes");
          }
          catch(IOException ex) {
            System.out.println(
                "Error writing file");
            // Or we could just do this:
            // ex.printStackTrace();
          }
        } else {
          System.out.println("{Please Enter a valid input");
        }

        if(!key.equals("")){
          if(enDe == 1){
            System.out.println("Preforming Encryption...");

            File inputPath = new File(mainFolderLoc+"plaintext");
            File[] fileList = inputPath.listFiles();

            for(File file:fileList){
              String fileName = file.getName();
              String[] outputHex = new String[1];
              String result = "";

              try {
                BufferedReader br =
                    new BufferedReader(new FileReader(mainFolderLoc+"plaintext/"+fileName));

                result = br.lines()
                    .collect(Collectors.joining("\n"));

                br.close();

                //System.out.println(result);
                String[] inputHex = AESCipher.stringToHex(result);
                outputHex = new String[inputHex.length+1];
                String inVector = AESCipher.genHexString(16);

                outputHex[0] =
                    aes.AES(AESCipher.genHexString(16),key,inVector);
                inVector = outputHex[0];

                for (int i = 0; i < inputHex.length;i++){
                  outputHex[i+1] = aes.AES(inputHex[i],key,inVector);
                  inVector = outputHex[i+1];
                }


                //System.out.println(" ");
                //System.out.println(toWrite);
              }
              catch(FileNotFoundException ex) {
                System.out.println(
                    "Unable to open file.");
              }
              catch(IOException ex) {
                System.out.println(
                    "Error reading file.");
                // Or we could just do this:
                // ex.printStackTrace();
              }

              try {
                File writeFile =
                    new File(mainFolderLoc+"cyphertext/"+fileName);

                if(!writeFile.exists()){
                  writeFile.createNewFile();
                }

                BufferedWriter bw =
                    new BufferedWriter(new FileWriter(writeFile));

                for(int i = 0; i < outputHex.length; i++){
                  bw.write(outputHex[i]);
                }
                bw.close();
              }
              catch(IOException ex) {
                System.out.println(
                    "Error writing file");
                // Or we could just do this:
                // ex.printStackTrace();
              }

            }
            System.out.println("Encryption Completed.");
            keyLoop = false;
            notDone = false;

          }else if(enDe == 2){
            System.out.println("Preforming Decryption...");

            File outputPath = new File(mainFolderLoc+"cyphertext");
            File[] fileList = outputPath.listFiles();

            for(File file:fileList){
              String fileName = file.getName();
              String toWrite = "";
              String result = "";
              String line = null;

              try {
                BufferedReader br =
                    new BufferedReader(new FileReader(mainFolderLoc+"cyphertext/"+fileName));

                result = br.lines()
                    .collect(Collectors.joining(System.lineSeparator()));

                br.close();

                //System.out.println(result);


                String inVector = AESCipher.genHexString(32);
                String[] outputHex = new String[result.length()/32];

                for(int i = 0; i < outputHex.length;i++){
                  outputHex[i] = result.substring(i*32,(i*32)+32);
                }

                for (int i = 0; i < outputHex.length;i++) {
                  String temp = outputHex[i];
                  outputHex[i] = aes.decryption(outputHex[i], key, inVector);
                  inVector = temp;
                }


                String finalOut = AESCipher.hexToString(outputHex);

                finalOut = finalOut.substring(16);

                toWrite = finalOut;
              }
              catch(FileNotFoundException ex) {
                System.out.println(
                    "Unable to open file.");
              }
              catch(IOException ex) {
                System.out.println(
                    "Error reading file.");
                // Or we could just do this:
                // ex.printStackTrace();
              }

              try {
                byte[] bytes = toWrite.getBytes();
                // Assume default encoding.
                FileOutputStream fos =
                    new FileOutputStream(mainFolderLoc+"plaintext/"+fileName);
                fos.write(bytes);
                fos.close();

              }
              catch(IOException ex) {
                System.out.println(
                    "Error writing file");
                // Or we could just do this:
                // ex.printStackTrace();
              }

            }
            System.out.println("Decryption Completed.");
            keyLoop = false;
            notDone = false;
          }
        }
      }
    }

  }
}
